package com.ironshutter.web.file.impl;

import static org.assertj.core.api.Assertions.in;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

import com.ironshutter.web.file.AppFileContext;
import com.ironshutter.web.file.AppFileKey;

public class AppFileContextLocal implements AppFileContext{
	
	private LocalDate now;
	
	private static final String USER_HOME_DIR = System.getProperty("user.home");
	private static final String SPRTR = File.separator;
	private static final String STORAGE_ROOT = USER_HOME_DIR + SPRTR + "iron-shutter";
	
	public AppFileContextLocal() {
		now = LocalDate.now();
	}
	
	@Override
	public AppFileKey getAppFileKey() {
		String keyStr = STORAGE_ROOT + SPRTR +
						now.getYear() + SPRTR +
						now.getMonthValue() + SPRTR +
						now.getDayOfMonth();
		AppFileKey appFileKey = new AppFileKeyImpl(keyStr);
		return appFileKey;
	}


}
