package com.small.cell.server.adapter;

import java.util.List;




import com.small.cell.server.pojo.PackageData;

import com.small.cell.server.pojo.Tlv;

import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.GoEasyUtil;
import com.small.cell.server.util.TlvTools;

public class ControlResponseAdapter {



	public static void handler(PackageData packageData) throws Exception {

		List<Tlv> tlvList = TlvTools.unpack(packageData.getMsgBodyBytes());
		if (tlvList.size() >= 2) {
			Tlv macTlv = tlvList.get(0);
			Tlv resTlv = tlvList.get(1);
			
			  GoEasyUtil.send(String.format(
			  "MAC��ַΪ%s���ն˿�������õ���Ӧ,�������%s,��Ӧ�����%s", ByteAndStr16
			  .Bytes2HexString(macTlv.getValue()), ByteAndStr16
			 .Bytes2HexString(resTlv.getType()), ByteAndStr16
			  .Bytes2HexString(resTlv.getValue())));
			 


		}

	}
}
