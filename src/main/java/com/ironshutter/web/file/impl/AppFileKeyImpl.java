package com.ironshutter.web.file.impl;

import com.ironshutter.web.file.AppFileKey;

public class AppFileKeyImpl extends AppFileKey{
	
	String k;
	
	public AppFileKeyImpl(String k) {
		this.k = k;
	}

	@Override
	public String get() {
		return this.k;
	}
	
}
