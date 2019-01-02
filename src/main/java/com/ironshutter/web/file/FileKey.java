package com.ironshutter.web.file;

/*
 * 실제 키값은 바이너리로 생각하자.
 * asString은 UTF-8으로 인코딩된 문자열
 * */
public abstract class FileKey {
	public abstract String getAsString();
}
