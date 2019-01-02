package com.ironshutter.web.file.impl;

import com.ironshutter.web.file.FileKey;

public class AppFileKeyImpl extends FileKey{
	
	String k;
	
	public AppFileKeyImpl(String k) {
		this.k = k;
	}

	@Override
	public String getAsString() {
		return this.k;
	}
	
}
