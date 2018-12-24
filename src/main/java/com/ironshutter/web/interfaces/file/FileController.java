package com.ironshutter.web.interfaces.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	@RequestMapping(value="/download/{id}", method = RequestMethod.GET)
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
	
	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, Model model)
	     throws IOException {
	
	// Save file on system
	if (!file.getOriginalFilename().isEmpty()) {
	     BufferedOutputStream outputStream = new BufferedOutputStream(
	    		 new FileOutputStream(
	    				 new File("D:/SingleFileUpload", file.getOriginalFilename())));

	     FileCopyUtils.copy(file.getInputStream(), outputStream);


	     model.addAttribute("msg", "File uploaded successfully.");
	} else {
		model.addAttribute("msg", "Please select a valid file..");
	}
	
		return "fileUploadForm";
	}
 
}
