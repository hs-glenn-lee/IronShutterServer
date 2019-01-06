package com.ironshutter.web.interfaces.file;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.interfaces.exceptions.NotSignedInException;
import com.ironshutter.web.interfaces.shared.GenericResponse;

@RestController
@RequestMapping(value="/api")
public class AppFileController {
	
	Logger logger = LoggerFactory.getLogger(AppFileController.class);
	
	@Autowired
	AppFileServiceFacade appFileServiceFacade;
	
	@RequestMapping(value="/upload-file", method = RequestMethod.POST)
	public GenericResponse<?> singleFileUpload(@RequestParam("file") MultipartFile mpFile, HttpServletRequest request){
		AppFileLeafletDTO appFileLeaflet;
		try {
			appFileLeaflet = appFileServiceFacade.storeAsAppFile(mpFile, request.getSession());
			return new GenericResponse<AppFileLeafletDTO>(appFileLeaflet);
		} catch (NotSignedInException e) {
			return GenericResponse.getFailInstance(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return GenericResponse.getFailInstance("서버에 문제가 발생했습니다.");
		}
	}
	
	@RequestMapping(value="/download-file/{key}", method = RequestMethod.GET)
	public void download(@PathVariable("key")String id, HttpServletResponse response, HttpServletRequest request) throws IOException {
		appFileServiceFacade.streamOutAppFile(id, response, request.getSession());
	}
	
}
