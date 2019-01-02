package com.ironshutter.web.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface FileStorage {
	public FileKey store(InputStream in, FileContext appFileContext) throws IOException;
	public File get(FileKey key);
	public boolean remove(FileKey key);
}