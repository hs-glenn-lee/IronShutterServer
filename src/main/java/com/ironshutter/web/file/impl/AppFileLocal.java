package com.ironshutter.web.file.impl;

import java.io.File;

import com.ironshutter.web.file.AppFile;

class AppFileLocal extends AppFile{
	
	private File file;
	
	AppFileLocal(File file) {
		this.file = file;
	}
	
	@Override
	public File getFile() {
		return this.file;
	}
}
