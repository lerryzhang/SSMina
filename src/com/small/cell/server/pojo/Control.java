package com.small.cell.server.pojo;

public enum Control {

	Upgrade("4001"), Restart("4003"), Reset("4005"), RouterUpgrade("4007");

	// 定义私有变量
	private String Code;

	private Control(String Code) {

		this.Code = Code;

	}

	public String getCode() {
		return Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}

	public static Control getByValue(String value) {
		for (Control code : values()) {
			if (code.getCode().equals(value)) {
				return code;
			}
		}
		return null;
	}

}
