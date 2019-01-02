package com.ironshutter.web.domain.model.appFile;

import java.io.File;

public class AppFile {
	private AppFileLeaflet appFileLeaflet;
	private File file;
	
	public AppFile(AppFileLeaflet appFileLeaflet, File file) {
		this.appFileLeaflet = appFileLeaflet;
		this.file = file;
	}

	public AppFileLeaflet getAppFileLeaflet() {
		return appFileLeaflet;
	}

	public File getFile() {
		return file;
	}

	public void setAppFileLeaflet(AppFileLeaflet appFileLeaflet) {
		this.appFileLeaflet = appFileLeaflet;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
