package com.ironshutter.web.interfaces.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.application.appFile.AppFileService;
import com.ironshutter.web.domain.model.appFile.AppFile;
import com.ironshutter.web.domain.model.appFile.AppFileLeaflet;
import com.ironshutter.web.interfaces.exceptions.NotSignedInException;
import com.ironshutter.web.interfaces.sign.facade.SignServiceFacade;
import com.ironshutter.web.interfaces.sign.facade.internal.support.SignedInValue;

@Component
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
		
		AppFile appFile = appFileService.store(multiPartFile);
		AppFileLeaflet appFileLeaflet = appFile.getAppFileLeaflet();
		AppFileLeafletDTO ret = new AppFileLeafletDTO(appFileLeaflet.getId(), appFileLeaflet.getOriginFilename(), appFileLeaflet.getLength());
		return ret;
	}

	@Override
	public void streamOutAppFile(String appFileId, HttpServletResponse response, HttpSession session) throws IOException {
		Optional<AppFile> optAppFile = appFileService.getAppFile(appFileId);
		if(!optAppFile.isPresent()) {
			throw new FileNotFoundException("can't find AppFile");
		}
		
		AppFile appFile = optAppFile.get();
		File file = appFile.getFile();
		AppFileLeaflet leaflet = appFile.getAppFileLeaflet();
        
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength(leaflet.getLength().intValue());
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	}

	@Override
	public void streamOutAppFileAsOriginFilename(String appFileId, HttpServletResponse response, HttpSession session)
			throws IOException {
		Optional<AppFile> optAppFile = appFileService.getAppFile(appFileId);
		if(!optAppFile.isPresent()) {
			throw new FileNotFoundException("can't find AppFile");
		}

        File file = optAppFile.get().getFile();
        AppFileLeaflet leaflet = optAppFile.get().getAppFileLeaflet();
        
        String mimeType= URLConnection.guessContentTypeFromName(leaflet.getOriginFilename());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-disposition", "attachment; filename="+ leaflet.getOriginFilename());
        response.setContentLength(leaflet.getLength().intValue());
        
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	}
	


}
