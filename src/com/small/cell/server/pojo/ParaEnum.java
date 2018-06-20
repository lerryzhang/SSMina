package com.small.cell.server.pojo;

public enum ParaEnum {
	MAC("0001", "mac"), MODEL("0002", "model"), FW("0003", "fw"), STARTTYPE(
			"0005", "startType"), REM("0006", "rem"), ZL_81X3_D("0007",
			"corrModel"), ZL_81X3_E("0008", "corrModel"), ZL_81X3_F("0009",
			"corrModel"), ZL_81X3_1("000a", "corrModel"), ZL_81X3_0("000b",
			"corrModel"), ZL_81X3_3D("000c", "corrModel"), ZL_81X3_3L("000d",
			"corrModel"), ZL_81X3_ALL("000d", "corrModel"), ROUTERFW("000F",
			"routerFw"), ZL_8110("0010", "corrModel"), ZL_8700_D("0011",
			"corrModel"), ZL_8700_E("0012", "corrModel"), ZL_8700_F("0013",
			"corrModel"), ZL_8700_1("0014", "corrModel"), FDD_BAND1("0015",
			"corrModel"), ZL_8700_3D("0016", "corrModel"), ZL_8700_3L("0017",
			"corrModel"), IMSI("3002", "imsi"), BAND("3004", "band"), TAC(
			"3005", "tac"), FREPOINTS("3006", "frePoints"), REGIONS("3007",
			"regions"), PLMN("300A", "plmn"), FREPOINT("300B", "frePoint"), PCI(
			"300C", "pci"), FieldStrength("300D", "fieldStrength"), SNIFFERFRE(
			"300E", "snifferFre"), SMTPSTATE("3010", "smtpState"), IMSITRANSMIT(
			"3012", "imsiTransmit"), UPTIMESYS("3014", "uptimeSys"), IMSICAPTURETIME(
			"300F", "imsiCaptureTime"),PETIME("500B","peTime"),QTAC("500C","qtac"),
			SYN("500D","syn"),WIREPARA("500E","wirePara"),QREPOINTS("500F","qrePoints"),
			TRANPOWER("5017","tranPower"),RESPRI("5021","resPri"),//LOWLEVEL("5024","lowLevel"),
			GPSOFFTIME("5027","gpsOffTime"),GPSOFFTIMESWITCH("5028","gpsOffTimeSwitch"),LOGLEVEL("5033","logLevel"),
			STARTTAC("5035","startTac"),ENDTAC("5036","endTac"),REMSWITCH("5037","remSwitch"),NTP("5038","ntp"),
			SAMLEVEL("5039","samLevel");
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