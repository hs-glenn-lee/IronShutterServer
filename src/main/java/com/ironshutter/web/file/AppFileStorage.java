package com.ironshutter.web.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface AppFileStorage {
	public AppFile store(InputStream in, AppFileContext appFileContext) throws IOException;
	public AppFile get(AppFileKey key);
	public boolean remove(AppFileKey key);
}