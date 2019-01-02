package com.ironshutter.web.interfaces.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.application.appFile.AppFileService;
import com.ironshutter.web.domain.model.file.AppFileLeaflet;
import com.ironshutter.web.file.AppFile;
import com.ironshutter.web.interfaces.exceptions.NotSignedInException;
import com.ironshutter.web.interfaces.sign.facade.SignServiceFacade;
import com.ironshutter.web.interfaces.sign.facade.internal.support.SignedInValue;

public class AppFileServiceFacadeImpl implements AppFileServiceFacade{
	
	@Autowired
	SignServiceFacade signServiceFacade;
	
	@Autowired
	AppFileService appFileService;

	@Override
	public AppFileLeafletDTO storeAsAppFile(MultipartFile multiPartFile, HttpSession session) throws IOException, NotSignedInException {
		Optional<SignedInValue> signedInValue = signServiceFacade.getSignedInValue(session);
		if(!signedInValue.isPresent()) {
			throw new NotSignedInException();
		}
		
		AppFileLeaflet appFileLeaflet = appFileService.store(multiPartFile);
		AppFileLeafletDTO ret = new AppFileLeafletDTO(appFileLeaflet.getId(), appFileLeaflet.getOriginFilename(), appFileLeaflet.getLength());
		return ret;
	}

	@Override
	public void streamOutAppFile(String appFileId, HttpServletResponse response, HttpSession session) throws IOException {
		Optional<AppFile> appFile = appFileService.getAppFile(appFileId);
		if(!appFile.isPresent()) {
			throw new FileNotFoundException("can't find AppFile");
		}

        File file = appFile.get().getFile();
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int)file.length());
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	}

	@Override
	public void streamOutAppFileAsOriginFilename(String AppFileId, HttpServletResponse response, HttpSession session)
			throws IOException {
		Optional<AppFile> appFile = appFileService.getAppFile(appFileId);
		if(!appFile.isPresent()) {
			throw new FileNotFoundException("can't find AppFile");
		}

        File file = appFile.get().getFile();
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-disposition", "attachment; filename="+ fileName);
        response.setContentLength((int)file.length());
        
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	}
	


}
