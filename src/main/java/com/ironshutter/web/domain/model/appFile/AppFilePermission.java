package com.ironshutter.web.domain.model.appFile;

/* 
 * Value Object
 * 
 * service(signed-in) public owner
 * 00     00     00
 * 
 * 2진수로 가장 큰 자리수 부터 채워진다. 코드가 101010 이라면  00 ... 0000 101010
 * 2진수 자리 두자리는 read(r), write(w)를 나타낸다. 리눅스 파일 퍼미션 처럼
 * 
 * service는 서비스 레벨에 따른 것
 * 예를 들어 signed-in은 로그인을 해야 받을수 있다.
 * 새로 service에 따른 접근권한이 필요하다면 큰 자리를 할당하여 약속한다
 * service(신규) service(signed-in) public owner
 * 00 10 00 11
 * 
 * 만약 service에 0이 하나라도 있으면 public 부분의 숫자는 변경되어야 한다.
 * 만약 public 하게 변경하려고 한다면 비즈니스 로직상 service관련 부분을 먼저 변경하고 변경해야한다. 
 * 
 * 코드 예
 * 00 00 00
 * 
 * */
public class AppFilePermission {
	
	/**
	 * 클라이언트 프로그램 다운로드 퍼미션 편의 상수
	 * */
	private static final Integer CLIENT_APPPLICATION_FILE_PERMISSION = 1;
	
	public static AppFilePermission getClientApplicationFilePermission () {
		return new AppFilePermission(CLIENT_APPPLICATION_FILE_PERMISSION);
	}
	
	Integer permissionCode;
	
	public AppFilePermission() {
		this.permissionCode = 3;
	}
	public AppFilePermission(Integer permissionCode) {
		this.permissionCode = permissionCode;
	}
	
}
