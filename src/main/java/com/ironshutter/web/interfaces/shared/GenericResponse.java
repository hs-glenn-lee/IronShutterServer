package com.ironshutter.web.interfaces.shared;

public class GenericResponse<T> {
	
	public static final String STATUS_FAIL = "fail";
	public static final String STATUS_SUCCESS = "success";
	
	private String status = STATUS_SUCCESS;
	private String message;
	private T data;
	
	
	public GenericResponse(T data) { this.data = data; }
	
	public GenericResponse() {}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static GenericResponse<Object> getFailInstance(String failMessage) {
		GenericResponse<Object> fail = new GenericResponse<Object>();
		fail.setStatus(STATUS_FAIL);
		fail.setMessage(failMessage);
		return fail;
	}

}
