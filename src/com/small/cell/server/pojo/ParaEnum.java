package com.small.cell.server.pojo;

public enum ParaEnum {
	MAC("0001", "mac"), MODEL("0002", "model"), FW("0003", "fw"), STARTTYPE(
			"0005", "startType"), REM("0006", "rem"), ROUTERFW("000F",
			"routerFw"), IMSI("3002", "imsi"), TAC("3005", "tac"), LNGLAT(
			"3013", "lnglat");
	// 成员变量
	private String code;
	private String name;

	// 构造方法
	private ParaEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	// 普通方法
	public static String getName(String code) {
		for (ParaEnum c : ParaEnum.values()) {
			if (c.getCode().equals(code)) {
				return c.name;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}