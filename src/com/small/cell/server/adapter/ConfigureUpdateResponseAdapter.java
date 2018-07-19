package com.small.cell.server.adapter;

import java.util.List;

import com.small.cell.server.pojo.PackageData;

import com.small.cell.server.pojo.Tlv;

import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.GoEasyUtil;

import com.small.cell.server.util.TlvTools;

public class ConfigureUpdateResponseAdapter {
	public static void handler(PackageData packageData) throws Exception {

		List<Tlv> tlvList = TlvTools.unpack(packageData.getMsgBodyBytes());
		if (tlvList.size() >= 2) {
			Tlv macTlv = tlvList.get(0);
			Tlv resTlv = tlvList.get(1);
			String mac = ByteAndStr16.Bytes2HexString(macTlv.getValue());
			String res = ByteAndStr16.Bytes2HexString(resTlv.getValue());
			GoEasyUtil.send(String.format("MAC��ַΪ%s���ն����ø�������õ���Ӧ�����Ϊ��%s", mac,
					res));
		}

	}
}
