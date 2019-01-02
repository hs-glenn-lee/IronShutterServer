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
import com.ironshutter.web.file.AppFileStorage;

@Component
public class AppFileStorageLocal implements AppFileStorage{

	@Override
	public AppFileKey store(InputStream in, AppFileContext appFileContext) throws IOException {
		if(!(appFileContext instanceof AppFileContextLocal)) {
			throw new IllegalArgumentException("appFileContext must be instance of AppFileContextLocal ");
		}
		
		File targetFile = new File(appFileContext.getAppFileKey().toString());
		File targetDir = targetFile.getParentFile();
		
		targetDir.mkdirs();
		
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
		
		FileCopyUtils.copy(in, outputStream);
		
		return appFileContext.getAppFileKey();
	}

	@Override
	public AppFile get(AppFileKey key) {
		return new AppFileLocal(new File(key.getAsString()));
	}

	@Override
	public boolean remove(AppFileKey key) {
		return new File(key.getAsString()).delete();
	}

}
