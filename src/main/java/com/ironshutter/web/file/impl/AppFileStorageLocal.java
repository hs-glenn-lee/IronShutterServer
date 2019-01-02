package com.ironshutter.web.file.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.ironshutter.web.file.FileContext;
import com.ironshutter.web.file.FileKey;
import com.ironshutter.web.file.FileStorage;

@Component
public class AppFileStorageLocal implements FileStorage{

	@Override
	public FileKey store(InputStream in, FileContext appFileContext) throws IOException {
		if(!(appFileContext instanceof AppFileContextLocal)) {
			throw new IllegalArgumentException("appFileContext must be instance of AppFileContextLocal ");
		}
		
		File targetFile = new File(appFileContext.getAppFileKey().toString());
		File targetDir = targetFile.getParentFile();
		
		targetDir.mkdirs(); // TODO need a map cache
		
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
		
		FileCopyUtils.copy(in, outputStream);
		
		return appFileContext.getAppFileKey();
	}

	@Override
	public File get(FileKey key) {
		return new File(key.getAsString());
	}

	@Override
	public boolean remove(FileKey key) {
		return new File(key.getAsString()).delete();
	}

}
