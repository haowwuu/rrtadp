package com.rrt.adp.web;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Description TODO
 * @author Wuwuhao
 * @date 2017年7月13日
 * 
 */
public class RestResult {
	
	private CODE code;
	
	private String message;
	
	private Object data;
	
	public RestResult(CODE code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public enum CODE {
		RET_OK("0"), RET_NO("1"), RET_NOT_LOGGED_IN("2"),
		RET_NO_PRIVILEGE("3"), RET_NO_EXCEPTION("5");

		private String value;
		private CODE(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}
		@JsonValue
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public static RestResult defaultSuccessResult(Object data, String msg){
		return new RestResult(CODE.RET_OK, msg, data);
	}
	
	public static RestResult defaultFailResult(Object data, String msg){
		return new RestResult(CODE.RET_NO, msg, data);
	}
	public static RestResult defaultSuccessResult(Object data){
		return new RestResult(CODE.RET_OK, "success", data);
	}
	
	public static RestResult defaultFailResult(Object data){
		return new RestResult(CODE.RET_NO, "fail", data);
	}
	public static RestResult defaultSuccessResult(String msg){
		return defaultSuccessResult(null, msg);
	}
	public static RestResult defaultFailResult(String msg){
		return defaultFailResult(null, msg);
	}
	public static RestResult defaultSuccessResult(){
		return defaultSuccessResult(null, "success");
	}
	public static RestResult defaultFailResult(){
		return defaultFailResult(null, "fail");
	}

	public CODE getCode() {
		return code;
	}

	public void setCode(CODE code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
