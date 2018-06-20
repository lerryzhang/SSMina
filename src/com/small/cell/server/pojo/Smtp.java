package com.small.cell.server.pojo;

import java.io.Serializable;

import com.small.cell.server.util.MyUtils;

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
	private String band;
	private String tac;
	private String plmn; // 频点
	private String frequency;
	private String pci;
	private String fieldStrength;
	private String lnglat;
	private String corrModel;
	private String frePoints;
	private String regions;
	private String frePoint;
	private String snifferFre;
	private String imsiTime;
	private String nowfrePoint;
	private String uptimeSys;
	private String imsiCaptureTime;
	private String smtpState;
	private String imsiTransmit;
	private String status;
	private String auth;
	private String peTime;// 周期采集时间
	private String qtac;// TAC
	private String syn;// 同步模式
	private String wirePara;// 无线参数
	private String qrePoints;// 频点表
	private String tranPower;// 发射功率
	private String resPri;// 重选优先级
	private String lowLevel;// 小区最低接入电平
	private String gpsOffTime;// GPS偏置时间
	private String gpsOffTimeSwitch;// GPS偏置时间自动配置开关
	private String logLevel;// 日志级别
	private String startTac;// 起始TAC
	private String endTac;// 结束TAC
	private String remSwitch;// REM自动扫描开关
	private String ntp;// NTP
	private String samLevel;// 同频干扰抑制级别

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
		this.model = MyUtils.hexStringToString(model);
	}

	public String getFw() {
		return fw;
	}

	public void setFw(String fw) {
		this.fw = MyUtils.hexStringToString(fw);
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
		this.imsi = MyUtils.hexStringToString(imsi);
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = MyUtils.getStringFromString(MyUtils.getStrList(band, 8), 4);
	}

	public static String getSmtprediskey() {
		return SmtpRedisKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPlmn() {
		return plmn;
	}

	public void setPlmn(String plmn) {
		this.plmn = MyUtils.hexStringToString(plmn);
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = Integer.valueOf(tac, 16).toString();
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
		this.pci = Integer.valueOf(pci, 16).toString();

	}

	public String getRouterFw() {
		return routerFw;
	}

	public void setRouterFw(String routerFw) {
		this.routerFw = MyUtils.hexStringToString(routerFw);
	}

	public String getFieldStrength() {
		return fieldStrength;
	}

	public void setFieldStrength(String fieldStrength) {
		this.fieldStrength = MyUtils.hexStringToString(fieldStrength);

	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = MyUtils.getStringFromInteger(MyUtils.getStrList(lnglat,
				32), 2);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {

		this.version = Integer.valueOf(version, 16).toString();
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

	public String getFrePoints() {
		return frePoints;
	}

	public void setFrePoints(String frePoints) {

		if (frePoints == null || "".equals(frePoints)) {
			this.frePoints = frePoints;
		} else {
			this.frePoints = MyUtils.getStringFromString(MyUtils.getStrList(
					frePoints, 8), 2);
		}

	}

	public String getRegions() {
		return regions;
	}

	public void setRegions(String regions) {

		if (regions == null || "".equals(regions)) {
			this.regions = regions;
		} else {
			this.regions =

			MyUtils.getRegins(MyUtils.getStrList(regions, 88));

		}
	}

	public String getFrePoint() {
		return frePoint;
	}

	public void setFrePoint(String frePoint) {
		this.frePoint = Integer.valueOf(frePoint, 16).toString();
	}

	public String getSnifferFre() {
		return snifferFre;
	}

	public void setSnifferFre(String snifferFre) {
		this.snifferFre = snifferFre;
	}

	public String getImsiTime() {
		return imsiTime;
	}

	public void setImsiTime(String imsiTime) {
		this.imsiTime = MyUtils.hexStringToString(imsiTime);
	}

	public String getNowfrePoint() {
		return nowfrePoint;
	}

	public void setNowfrePoint(String nowfrePoint) {
		this.nowfrePoint = Integer.valueOf(nowfrePoint, 16).toString();
	}

	public String getUptimeSys() {
		return uptimeSys;
	}

	public void setUptimeSys(String uptimeSys) {

		if (uptimeSys == null || "".equals(uptimeSys)) {
			this.uptimeSys = uptimeSys;
		} else {
			this.uptimeSys = MyUtils.getStringFromInteger(MyUtils.getStrList(
					uptimeSys, 32), 2);
		}

	}

	public String getImsiCaptureTime() {
		return imsiCaptureTime;
	}

	public void setImsiCaptureTime(String imsiCaptureTime) {
		this.imsiCaptureTime = MyUtils.hexStringToString(imsiCaptureTime);
	}

	public String getSmtpState() {
		return smtpState;
	}

	public void setSmtpState(String smtpState) {
		this.smtpState = MyUtils.getSmtpState(smtpState);
	}

	public String getImsiTransmit() {
		return imsiTransmit;
	}

	public void setImsiTransmit(String imsiTransmit) {
		this.imsiTransmit = MyUtils.getImsiTransmit(imsiTransmit);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getPeTime() {
		return peTime;
	}

	public void setPeTime(String peTime) {
		this.peTime = Integer.valueOf(peTime, 16).toString();
	}

	public String getQtac() {
		return qtac;
	}

	public void setQtac(String qtac) {
		this.qtac = Integer.valueOf(qtac, 16).toString();
	}

	public String getSyn() {
		return syn;
	}

	public void setSyn(String syn) {
		this.syn =Integer.valueOf(syn,16).toString();
	}
	public String getWirePara() {
		return wirePara;
	}

	public void setWirePara(String wirePara) {
		this.wirePara = MyUtils.getStringFromString(MyUtils.getStrList(
				wirePara, 8), 4);
	}

	public String getQrePoints() {
		return qrePoints;
	}

	public void setQrePoints(String qrePoints) {
		this.qrePoints = MyUtils.getStringFromString(MyUtils.getStrList(
				qrePoints, 8), 3);
	}

	public String getTranPower() {
		return tranPower;
	}

	public void setTranPower(String tranPower) {
		this.tranPower = MyUtils.hexStringToString(tranPower);
	}

	public String getResPri() {
		return resPri;
	}

	public void setResPri(String resPri) {
		this.resPri = Integer.valueOf(resPri, 16).toString();
	}

	public String getLowLevel() {
		return lowLevel;
	}

	public void setLowLevel(String lowLevel) {
		this.lowLevel = Integer.valueOf(lowLevel, 16).toString();
	}

	public String getGpsOffTime() {
		return gpsOffTime;
	}

	public void setGpsOffTime(String gpsOffTime) {
		this.gpsOffTime = Integer.valueOf(gpsOffTime, 16).toString();
	}

	public String getGpsOffTimeSwitch() {
		return gpsOffTimeSwitch;
	}

	public void setGpsOffTimeSwitch(String gpsOffTimeSwitch) {
		this.gpsOffTimeSwitch = Integer.valueOf(gpsOffTimeSwitch, 16)
				.toString();
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = Integer.valueOf(logLevel, 16).toString();
	}

	public String getStartTac() {
		return startTac;
	}

	public void setStartTac(String startTac) {
		this.startTac = Integer.valueOf(startTac, 16).toString();
	}

	public String getEndTac() {
		return endTac;
	}

	public void setEndTac(String endTac) {
		this.endTac = Integer.valueOf(endTac, 16).toString();
	}

	public String getRemSwitch() {
		return remSwitch;
	}

	public void setRemSwitch(String remSwitch) {
		this.remSwitch = Integer.valueOf(remSwitch, 16).toString();
	}

	public String getNtp() {
		return ntp;
	}

	public void setNtp(String ntp) {
		this.ntp = MyUtils.getNtp(ntp);
	}

	public String getSamLevel() {
		return samLevel;
	}

	public void setSamLevel(String samLevel) {
		this.samLevel = Integer.valueOf(samLevel, 16).toString();
		
	}

}
