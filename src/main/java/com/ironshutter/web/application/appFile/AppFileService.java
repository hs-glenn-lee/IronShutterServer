package com.ironshutter.web.application.appFile;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.domain.model.appFile.AppFile;
import com.ironshutter.web.domain.model.appFile.AppFileLeaflet;

public interface AppFileService {
	public AppFile store(MultipartFile file) throws IOException;
	public Optional<AppFile> getAppFile(String id);
}
