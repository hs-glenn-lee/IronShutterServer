package com.ironshutter.web.interfaces.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.interfaces.exceptions.NotSignedInException;
import com.ironshutter.web.interfaces.shared.GenericResponse;

@Controller
public class AppFileController {
	
	Logger logger = LoggerFactory.getLogger(AppFileController.class);
	
	@Autowired
	AppFileServiceFacade appFileServiceFacade;
	
	@PostMapping("/upload-file")
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
	public void download(@PathVariable("id")String id, HttpServletResponse response) throws IOException {
		InputStream in = null;

		File file = null;
 
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";

            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
         
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
         
        
         
        response.setContentType(mimeType);
         
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        // response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
 
         
        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
         
        response.setContentLength((int)file.length());
        
        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(in, response.getOutputStream());

	}
	
}
