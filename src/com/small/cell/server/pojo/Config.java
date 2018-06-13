package com.small.cell.server.pojo;

public enum Config {
	PARASEL("5001", 4),PARASELF("5002",4),PROBE("5005",4);
	// ��Ա����
	private String code;
	private int length;

	// ���췽��
	private Config(String code, int length) {
		this.code = code;
		this.length = length;
	}

	// ��ͨ����
	public static int getLength(String code) {
		for (Config c : Config.values()) {
			if (c.getCode().equals(code)) {
				return c.length;
			}
		}
		return 0;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}