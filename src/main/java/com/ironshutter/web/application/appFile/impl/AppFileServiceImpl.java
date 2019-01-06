package com.ironshutter.web.application.appFile.impl;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.application.appFile.AppFileService;
import com.ironshutter.web.domain.model.appFile.AppFile;
import com.ironshutter.web.domain.model.appFile.AppFileLeaflet;
import com.ironshutter.web.domain.model.appFile.AppFileLeafletRepository;
import com.ironshutter.web.file.FileContext;
import com.ironshutter.web.file.FileKey;
import com.ironshutter.web.file.FileStorage;
import com.ironshutter.web.file.impl.AppFileContextLocal;
import com.ironshutter.web.file.impl.AppFileKeyImpl;
import com.ironshutter.web.support.UUIDUtil;

@Service("appFileService")
public class AppFileServiceImpl implements AppFileService{
	
	@Autowired
	FileStorage appFileStorage;
	
	@Autowired
	AppFileLeafletRepository appFileLeafletRepository;

	@Override
	public AppFile store(MultipartFile file) throws IOException {
		String appFileLeafletId = UUIDUtil.newUUID();
		FileContext appFileContext = new AppFileContextLocal(appFileLeafletId, file.getOriginalFilename());
		
		FileKey appFileKey = appFileStorage.store(file.getInputStream(), appFileContext);

		AppFileLeaflet appFileLeaflet = new AppFileLeaflet(appFileLeafletId, appFileKey, file.getOriginalFilename(), file.getSize());
		appFileLeafletRepository.save(appFileLeaflet);
		
		AppFile appFile = new AppFile(appFileLeaflet, appFileStorage.get(appFileKey));
		return appFile;
	}

	@Override
	public Optional<AppFile> getAppFile(String id) {
		Optional<AppFileLeaflet> appFileLeaflet = appFileLeafletRepository.findById(id);
		if(!appFileLeaflet.isPresent())
			return Optional.empty();
		
		File file = appFileStorage.get(new AppFileKeyImpl(appFileLeaflet.get().getKey()));
		if(!file.exists())
			return Optional.empty();
		
		AppFile appFile = new AppFile(appFileLeaflet.get(), file);
		return Optional.of(appFile);
	}

}
