package com.ironshutter.web.domain.model.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ironshutter.web.file.AppFileKey;

@Entity
@Table(name="app_file_leaflet")
public class AppFileLeaflet {
	
	@Id
	private String id;
	
	@Column(name="key")
	private String key;
	
	@Column(name="origin_file_name")
	private String originFilename;
	
	@Column(name="length")
	private Long length; // byte
	
	
	public AppFileLeaflet() {}
	public AppFileLeaflet(String id, AppFileKey appFileKey, String originFilename, Long length) {
		this.id = id;
		this.key = appFileKey.getAsString();
		this.originFilename = originFilename;
		this.length = length;
	}
	
	
	public String getId() {
		return id;
	}
	public String getKey() {
		return key;
	}
	public String getOriginFilename() {
		return originFilename;
	}
	public Long getLength() {
		return length;
	}
}
