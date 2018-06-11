package com.small.cell.server.pojo;

import java.io.Serializable;
import java.util.List;

public class Smtp implements Serializable {
	/**
	 * 
	 */

	public static final String SmtpRedisKey = "SmtpRedisKey";
	private static final long serialVersionUID = 1L;
	private String mac;
	private String version;
	private String seqNum;
	private String model;
	private String fw;
	private String startType;
	private String rem;
	private String tdd;
	private String routerFw;
	private String imsi;
	private Band[] band = new Band[3];
	private String tac;
	private String plmn; // Æµµã
	private String frequency;
	private String pci;
	private String fieldStrength;
	private String lnglat;
	private String corrModel;
	private List<FrePoint> frePoints;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFw() {
		return fw;
	}

	public void setFw(String fw) {
		this.fw = fw;
	}

	public String getStartType() {
		return startType;
	}

	public void setStartType(String startType) {
		this.startType = startType;
	}

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public String getTdd() {
		return tdd;
	}

	public void setTdd(String tdd) {
		this.tdd = tdd;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public Band[] getBand() {
		return band;
	}

	public void setBand(Band[] band) {
		this.band = band;
	}

	public String getPlmn() {
		return plmn;
	}

	public void setPlmn(String plmn) {
		this.plmn = plmn;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getPci() {
		return pci;
	}

	public void setPci(String pci) {
		this.pci = pci;
	}

	public String getRouterFw() {
		return routerFw;
	}

	public void setRouterFw(String routerFw) {
		this.routerFw = routerFw;
	}

	public String getFieldStrength() {
		return fieldStrength;
	}

	public void setFieldStrength(String fieldStrength) {
		this.fieldStrength = fieldStrength;
	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public String getCorrModel() {
		return corrModel;
	}

	public void setCorrModel(String corrModel) {
		this.corrModel = corrModel;
	}

}
