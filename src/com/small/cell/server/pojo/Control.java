package com.small.cell.server.pojo;

public enum Control {

	Upgrade("����", "4001"), Restart("����", "4003"), Reset("�ָ���������", "4005"), RouterUpgrade(
			"·��������", "4007");

	// ����˽�б���
	private String code;
	private String title;

	private Control(String title, String code) {

		this.code = code;
		this.title = title;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static String getByValue(String code) {
		for (Control control : values()) {
			if (control.getCode().equals(code)) {
				return control.getTitle();
			}
		}
		return null;
	}
	
	
	public static Control getControl(String code) {
		for (Control control : values()) {
			if (control.getCode().equals(code)) {
				return control;
			}
		}
		return null;
	}

}
