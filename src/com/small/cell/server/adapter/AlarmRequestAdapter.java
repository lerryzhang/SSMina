package com.small.cell.server.adapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.small.cell.server.pojo.Alarm;
import com.small.cell.server.pojo.FrameFlag;
import com.small.cell.server.pojo.General;
import com.small.cell.server.pojo.PackageData;
import com.small.cell.server.pojo.Para;
import com.small.cell.server.pojo.Res;
import com.small.cell.server.pojo.Tlv;
import com.small.cell.server.pojo.TypeCode;

import com.small.cell.server.service.AlarmService;

import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.MyExeUtil;
import com.small.cell.server.util.MyUtils;
import com.small.cell.server.util.ReflectUtils;
import com.small.cell.server.util.TlvTools;


@Service("alarmRequestAdapter")
public class AlarmRequestAdapter {

	@Resource
	private AlarmService alarmService;
	
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");


	// public static PackageData handler(PackageData packageData) throws
	// Exception {

	public PackageData handler(PackageData packageData) throws Exception {

		List<Tlv> tlvList = TlvTools.unpack(packageData.getMsgBodyBytes());
		List<Tlv> list = new ArrayList<Tlv>();
		Tlv macTlv = tlvList.get(0);
		Tlv resTlv = new Tlv();
		resTlv.setType(ByteAndStr16.HexString2Bytes(MyUtils
				.IntegerToString16For4(General.Res)));
		resTlv.setLen(ByteAndStr16.HexString2Bytes(MyUtils
				.IntegerToString16For4(Res.ResLen)));
		if (!MyUtils.valMac(ByteAndStr16.Bytes2HexString(macTlv.getValue())))
			resTlv.setValue(ByteAndStr16.HexString2Bytes(MyUtils
					.IntegerToString16For2(Res.InvalidMac)));
		// else if(){

		// }else if(){

		// }
		else

			resTlv.setValue(ByteAndStr16.HexString2Bytes(MyUtils
					.IntegerToString16For2(Res.SUCCESS)));

		String body = null;
		try {
			body = MyExeUtil.getExeRes(Para.BlowFishMode_1,
					String.format("%s%s", macTlv, resTlv));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 1; i < tlvList.size(); i++) {
			List<Tlv> subList = TlvTools.unpack(ByteAndStr16
					.Bytes2HexString(tlvList.get(i).getValue()));
			list.addAll(subList);
		}

		Alarm alarm = (Alarm) ReflectUtils.setAlarmProperty(list);
		alarm.setPtime(format.format(new Date()));
		alarmService.saveAlarm(alarm);
		packageData.setMsgBodyBytes(body);
		packageData.getMsgHeader().setMsgFrameFlag(FrameFlag.Encrypt);
		packageData.getMsgHeader().setMsgTypeCode(
				TypeCode.AlarmResponse.getCode());
		packageData.getMsgHeader().setMsgLength(
				MyUtils.IntegerToString16For4(PackageData.msgHeaderLength
						+ ByteAndStr16.HexString2Bytes(body).length));

		return packageData;

	}
}
