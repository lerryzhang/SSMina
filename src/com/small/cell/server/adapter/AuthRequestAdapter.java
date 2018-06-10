package com.small.cell.server.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.small.cell.server.pojo.FrameFlag;
import com.small.cell.server.pojo.General;
import com.small.cell.server.pojo.PackageData;
import com.small.cell.server.pojo.Para;
import com.small.cell.server.pojo.Res;
import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.Tlv;
import com.small.cell.server.pojo.TypeCode;

import com.small.cell.server.session.SessionManager;
import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.JedisUtil;
import com.small.cell.server.util.MyExeUtil;
import com.small.cell.server.util.MyUtils;
import com.small.cell.server.util.ObjectUtil;
import com.small.cell.server.util.ReflectUtils;
import com.small.cell.server.util.TlvTools;

public class AuthRequestAdapter {
	public static PackageData handler(PackageData packageData, IoSession session)
			throws Exception {

		List<Tlv> tlvList = TlvTools.unpack(packageData.getMsgBodyBytes());
		Tlv macTlv = tlvList.get(0);
		String mac = ByteAndStr16.Bytes2HexString(macTlv.getValue());
		Tlv resTlv = new Tlv();
		resTlv.setType(ByteAndStr16.HexString2Bytes(MyUtils
				.IntegerToString16For4(General.Res)));
		resTlv.setLen(ByteAndStr16.HexString2Bytes(MyUtils
				.IntegerToString16For4(Res.ResLen)));
		if (!MyUtils.valMac(ByteAndStr16.Bytes2HexString(macTlv.getValue())))
			resTlv.setValue(ByteAndStr16.HexString2Bytes(MyUtils
					.IntegerToString16For2(Res.InvalidMac)));
		else {

			resTlv.setValue(ByteAndStr16.HexString2Bytes(MyUtils
					.IntegerToString16For2(Res.SUCCESS)));
			// 表明认证通过
			Smtp smtp = (Smtp) ReflectUtils.setProperty(tlvList);
			smtp.setVersion(packageData.getMsgHeader().getMsgVersion());
			smtp.setSeqNum(packageData.getMsgHeader().getMsgSeqNum());
			if (JedisUtil.get(Smtp.SmtpRedisKey) == null) {
				Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
				map.put(mac.getBytes(), ObjectUtil.object2Bytes(smtp));
				JedisUtil.hmset(Smtp.SmtpRedisKey, map);
			} else {
				JedisUtil.hmset(Smtp.SmtpRedisKey, mac, smtp);
			}
			SessionManager.getManager().set(mac, session);
		}
		String body = null;
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
		packageData.getMsgHeader().setMsgTypeCode(
				TypeCode.AuthResponse.getCode());

		packageData.getMsgHeader().setMsgFrameFlag(FrameFlag.Encrypt);
		packageData.getMsgHeader().setMsgLength(
				MyUtils.IntegerToString16For4(PackageData.msgHeaderLength
						+ ByteAndStr16.HexString2Bytes(body).length));

		return packageData;

	}
}
