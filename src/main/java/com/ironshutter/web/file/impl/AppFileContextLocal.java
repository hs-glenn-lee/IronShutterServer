package com.ironshutter.web.file.impl;

import static org.assertj.core.api.Assertions.in;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;

import com.ironshutter.web.file.AppFileContext;
import com.ironshutter.web.file.AppFileKey;
import com.ironshutter.web.support.UUIDUtil;

public class AppFileContextLocal implements AppFileContext{
	
	private LocalDate now;
	private String appFileName;
	private String appFileId;
	
	private static final String USER_HOME_DIR = System.getProperty("user.home");
	private static final String SPRTR = File.separator;
	private static final String STORAGE_ROOT = USER_HOME_DIR + SPRTR + "iron-shutter";
	
	public AppFileContextLocal(String appFileId, String originFilename) {
		now = LocalDate.now();
		this.appFileName = appFileId;
		this.appFileName = this.appFileName + "." + FilenameUtils.getExtension(originFilename);
	}
	
	@Override
	public AppFileKey getAppFileKey() {
		String keyStr = STORAGE_ROOT + SPRTR +
						now.getYear() + SPRTR +
						now.getMonthValue() + SPRTR +
						now.getDayOfMonth() + SPRTR +
						appFileName;
		AppFileKey appFileKey = new AppFileKeyImpl(keyStr);
		return appFileKey;
	}
	
	@Override
	public String getAppFileName() {
		return this.appFileName;
	}
	
	@Override
	public String getAppFileId() {
		return this.appFileId;
	}

}
