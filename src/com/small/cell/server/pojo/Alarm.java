package com.small.cell.server.pojo;

public class Alarm {
	private int id;
	private String mac;
	private String ptime;
	private String code;
	private String version;
	private String process;
	private String restart;
	private String tip;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getRestart() {
		return restart;
	}

	public void setRestart(String restart) {
		this.restart = restart;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
  
}
