package com.small.cell.server.service;

import java.util.List;
import java.util.Map;

public interface ParsService {

	public int getOnlineSum(Map<byte[], byte[]> map);

	public String getRegins(List<String> list);

	public String getSmtpState(String content);

	public String getImsiTransmit(String content);

	public String getNtp(String content);

	public String getRegions(String key, String value);

	public String getRemote(String key, String value);

	public String getNtpToClient(String key, String value);

	public String getRouter(String key,String value);

	public String getStringArray(String key, String value);

	public String getFrequencyTable(String key, String value);

	public String getIntegerArray(String key, String value);

	public String getString(String key, String value);
	
	public String getInteger(String key, String value);
	
	public String getIp(String key,String value);

}
