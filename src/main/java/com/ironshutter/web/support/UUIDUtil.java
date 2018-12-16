package com.ironshutter.web.support;

public class UUIDUtil {
	
	public static String newUUID() {
		String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}
