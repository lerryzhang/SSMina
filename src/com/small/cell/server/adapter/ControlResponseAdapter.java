package com.small.cell.server.adapter;

import java.util.List;

import com.small.cell.server.pojo.PackageData;

import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.Tlv;

import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.JedisUtil;

import com.small.cell.server.util.ObjectUtil;
import com.small.cell.server.util.ReflectUtils;
import com.small.cell.server.util.TlvTools;

public class ControlResponseAdapter {
	public static String handler(PackageData packageData) throws Exception {

		List<Tlv> tlvList = TlvTools.unpack(packageData.getMsgBodyBytes());
		if (tlvList.size() >= 2) {
			Tlv macTlv = tlvList.get(0);
			String mac = ByteAndStr16.Bytes2HexString(macTlv.getValue());
			for (int i = 1; i < tlvList.size(); i++) {
				List<Tlv> subList = TlvTools.unpack(ByteAndStr16
						.Bytes2HexString(tlvList.get(i).getValue()));
				tlvList.addAll(subList);
			}
			Smtp smtp = JedisUtil.hmget(Smtp.SmtpRedisKey, mac);
			ReflectUtils.setProperty(tlvList, smtp);
			JedisUtil.set(mac.getBytes(), ObjectUtil.object2Bytes(smtp));
			return ByteAndStr16.Bytes2HexString(macTlv.getValue());
		}
		return null;
	}
}
