package com.small.cell.server.pojo;

public enum Upgrade {

	Username("0001"), Password("0002"), Url("0003"), Version("0004");

	// 定义私有变量
	private String Code;

	private Upgrade(String Code) {

		this.Code = Code;

	}

	public String getCode() {
		return Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}

	public static Upgrade getByValue(String value) {
		for (Upgrade code : values()) {
			if (code.getCode().equals(value)) {
				return code;
			}
		}
		return null;
	}

}
