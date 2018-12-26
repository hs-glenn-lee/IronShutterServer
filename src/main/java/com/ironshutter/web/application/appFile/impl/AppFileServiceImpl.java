package com.ironshutter.web.application.appFile.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.application.appFile.AppFileService;
import com.ironshutter.web.domain.model.file.AppFileLeaflet;
import com.ironshutter.web.domain.model.file.AppFileLeafletRepository;
import com.ironshutter.web.file.AppFile;
import com.ironshutter.web.file.AppFileContext;
import com.ironshutter.web.file.AppFileKey;
import com.ironshutter.web.file.AppFileStorage;
import com.ironshutter.web.file.impl.AppFileContextLocal;
import com.ironshutter.web.file.impl.AppFileKeyImpl;
import com.ironshutter.web.support.UUIDUtil;

public class AppFileServiceImpl implements AppFileService{
	
	@Autowired
	AppFileStorage appFileStorage;
	
	@Autowired
	AppFileLeafletRepository appFileLeafletRepository;

	@Override
	public AppFileLeaflet store(MultipartFile file) throws IOException {
		AppFileContext appFileContext = new AppFileContextLocal();
		String appFileId = UUIDUtil.newUUID();

		AppFileKey appFileKey = appFileStorage.store(file.getInputStream(), appFileContext);
		AppFileLeaflet appFileLeaflet = new AppFileLeaflet(appFileId, appFileKey, file.getOriginalFilename(), file.getSize());
		
		appFileLeafletRepository.save(appFileLeaflet);
		return appFileLeaflet;
	}

	@Override
	public Optional<AppFile> get(String id) {
		Optional<AppFileLeaflet> appFileLeaflet = appFileLeafletRepository.findById(id);
		if(!appFileLeaflet.isPresent()) {
			return Optional.empty();
		}
		
		AppFile appFile = appFileStorage.get(new AppFileKeyImpl(appFileLeaflet.get().getKey()));
		return Optional.of(appFile);
	}

}
