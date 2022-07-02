package com.web.account.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * Author: chenyang
 * Time: 2020/12/3
 * Description: 用于创建返回
 * @author aptx
 */
public class Result {
	private int code;
	private String message;
	private boolean success;
	private Object data;

	private static Result newResult(int code, String message, boolean success, Object data) {
		return new Result(code, message, success, data);
	}

	public static  String newSuccessfulResult(Object data) {
		Result ok = newResult(0, "ok", true, data);
		return JSONObject.toJSONString(ok);
	}

	public static String newFailedResult(String message) {
		Result objectResult = newResult(-10000, message, false, null);
		return JSONObject.toJSONString(objectResult);
	}

	public static  Result newFailedResult(int code, String message) {
		return newResult(code, message, false, null);
	}

	public Result(int code, String message, boolean success, Object data) {
		this.code = code;
		this.message = message;
		this.success = success;
		this.data = data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String
	toString() {
		return "Result{" +
				"code=" + code +
				", message='" + message + '\'' +
				", success=" + success +
				", data=" + data +
				'}';
	}
}
