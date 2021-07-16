package com.uberApplication.dto;

public class ResponseDto {

	private String message;
	private String url;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ResponseDto [message=" + message + ", url=" + url + "]";
	}
	
	
}
