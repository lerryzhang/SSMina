package com.small.cell.server.adapter;

import java.io.IOException;
import java.util.List;

import com.small.cell.server.pojo.FrameFlag;
import com.small.cell.server.pojo.General;
import com.small.cell.server.pojo.PackageData;
import com.small.cell.server.pojo.Para;
import com.small.cell.server.pojo.Res;
import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.Tlv;
import com.small.cell.server.pojo.TypeCode;

import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.JedisUtil;
import com.small.cell.server.util.MyExeUtil;
import com.small.cell.server.util.MyUtils;
import com.small.cell.server.util.ObjectUtil;
import com.small.cell.server.util.ReflectUtils;

import com.small.cell.server.util.TlvTools;

public class ReportRequestAdapter {
	public static PackageData handler(PackageData packageData) throws Exception {
		List<Tlv> tlvList = TlvTools.unpack(packageData.getMsgBodyBytes());
		if (tlvList.size() == 2) {
			String body = null;
			Tlv macTlv = tlvList.get(0);
			String mac = ByteAndStr16.Bytes2HexString(macTlv.getValue());
			Tlv resTlv = new Tlv();
			resTlv.setType(ByteAndStr16.HexString2Bytes(MyUtils
					.IntegerToString16For4(General.Res)));
			resTlv.setLen(ByteAndStr16.HexString2Bytes(MyUtils
					.IntegerToString16For4(Res.ResLen)));
			if (!MyUtils.valMac(mac)) {
				resTlv.setValue(ByteAndStr16.HexString2Bytes(MyUtils
						.IntegerToString16For2(Res.InvalidMac)));

			} else {

				resTlv.setValue(ByteAndStr16.HexString2Bytes(MyUtils
						.IntegerToString16For2(Res.SUCCESS)));
			}

			List<Tlv> subList = TlvTools.unpack(ByteAndStr16
					.Bytes2HexString(tlvList.get(1).getValue()));
			if (subList.size() == 0)
				resTlv.setValue(ByteAndStr16.HexString2Bytes(MyUtils
						.IntegerToString16For2(Res.InvalidFormat)));
			tlvList.addAll(subList);
			Smtp smtp = JedisUtil.hmget(Smtp.SmtpRedisKey, mac);
			ReflectUtils.setProperty(tlvList, smtp);
			JedisUtil.set(mac.getBytes(), ObjectUtil.object2Bytes(smtp));
			try {
				body = MyExeUtil.getExeRes(Para.BlowFishMode_1, String.format(
						"%s%s", macTlv, resTlv));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			packageData.setMsgBodyBytes(body);
			packageData.getMsgHeader().setMsgFrameFlag(FrameFlag.Encrypt);
			packageData.getMsgHeader().setMsgTypeCode(
					TypeCode.ReportResponse.getCode());
			packageData.getMsgHeader().setMsgLength(
					MyUtils.IntegerToString16For4(PackageData.msgHeaderLength
							+ ByteAndStr16.HexString2Bytes(body).length));

		}

		return packageData;

	}

	public static void main(String args[]) {

	}

}
