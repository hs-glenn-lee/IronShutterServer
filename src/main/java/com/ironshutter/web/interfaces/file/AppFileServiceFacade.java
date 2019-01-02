package com.ironshutter.web.interfaces.file;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.interfaces.exceptions.NotSignedInException;

public interface AppFileServiceFacade {
	public AppFileLeafletDTO storeAsAppFile(MultipartFile multiPartFile, HttpSession session) throws IOException, NotSignedInException;
	public void streamOutAppFile(String AppFileId, HttpServletResponse response, HttpSession session) throws IOException;
	public void streamOutAppFileAsOriginFilename(String AppFileId, HttpServletResponse response, HttpSession session) throws IOException;
}
