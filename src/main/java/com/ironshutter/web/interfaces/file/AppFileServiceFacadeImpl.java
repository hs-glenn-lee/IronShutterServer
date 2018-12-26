package com.ironshutter.web.interfaces.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		AppFileLeaflet appFileLafLet = appFileService.store(multiPartFile);
		
		
		
		return null;
	}

	@Override
	public void streamOutAppFile(String AppFileId, OutputStream out, HttpSession session) throws FileNotFoundException {
		// TODO ... void ...
		
		
		Optional<AppFile> appFile = appFileService.get(AppFileId);
		
		if(appFile.isPresent()) {
			File file = appFile.get().getFile();
			// TODO
			new FileInputStream(file);
		}else {
			throw new FileNotFoundException("can't find AppFile");
		}
		
		
	}
	


}
