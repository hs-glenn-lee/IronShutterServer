package com.ironshutter.web.interfaces.file;

import java.io.Serializable;

public class AppFileLeafletDTO implements Serializable{

	private static final long serialVersionUID = 2902409287188290693L;
	
	private String id;
	private String originFilename;
	private Long length;
	
	public AppFileLeafletDTO(String id, String originFilename, Long length) {
		this.id = id;
		this.originFilename = originFilename;
		this.length = length;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginFilename() {
		return originFilename;
	}

	public void setOriginFilename(String originFilename) {
		this.originFilename = originFilename;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}
	
}
