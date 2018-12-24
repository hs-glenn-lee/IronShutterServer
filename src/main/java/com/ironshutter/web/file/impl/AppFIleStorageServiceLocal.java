package com.ironshutter.web.file.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.ironshutter.web.file.AppFile;
import com.ironshutter.web.file.AppFileContext;
import com.ironshutter.web.file.AppFileKey;
import com.ironshutter.web.file.AppFileStorageService;

@Component
public class AppFIleStorageServiceLocal implements AppFileStorageService{

	@Override
	public AppFile store(InputStream in, AppFileContext appFileContext) throws IOException {
		if(!(appFileContext instanceof AppFileContextLocal)) {
			throw new IllegalArgumentException("appFileContext must be instance of AppFileContextLocal ");
		}
		
		// TODO check avail to write: 폴더가 있는지 등 ..
		File target = new File(appFileContext.getAppFileKey().toString());
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(target));
		
		FileCopyUtils.copy(in, outputStream);
		
		return new AppFileLocal(target);
	}

	@Override
	public AppFile get(AppFileKey key) {
		return new AppFileLocal(new File(key.toString()));
	}

	@Override
	public boolean remove(AppFileKey key) {
		return new File(key.toString()).delete();
	}

}
