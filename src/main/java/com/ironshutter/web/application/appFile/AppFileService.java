package com.ironshutter.web.application.appFile;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.domain.model.file.AppFileLeaflet;
import com.ironshutter.web.file.AppFile;

public interface AppFileService {
	public AppFileLeaflet store(MultipartFile file) throws IOException;
	public Optional<AppFile> get(String id);
}
