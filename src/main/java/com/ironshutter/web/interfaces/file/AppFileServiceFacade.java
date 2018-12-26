package com.ironshutter.web.interfaces.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.interfaces.exceptions.NotSignedInException;

public interface AppFileServiceFacade {
	public AppFileLeafletDTO storeAsAppFile(MultipartFile multiPartFile, HttpSession session) throws IOException, NotSignedInException;
	public void streamOutAppFile(String AppFileId, OutputStream out, HttpSession session) throws FileNotFoundException;
}
