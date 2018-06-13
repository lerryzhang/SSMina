package com.small.cell.server.util;

import java.util.ArrayList;
import java.util.List;

import com.small.cell.server.pojo.Tlv;

public class TlvTools {

	public static List<Tlv> unpack(String tlv) {
		int current = 0;// 遍历数据标签
		int lenValue = 0;// L的值
		int tagLen = 0;// tag的长度
		List<Tlv> tlvList = new ArrayList<Tlv>();

		// 将string数据转换成byte
		byte[] data = ByteAndStr16.HexString2Bytes(tlv);
		while (current < data.length) {
			Tlv tlvData = new Tlv();
			tagLen = Tlv.TlvTagLength;
			if (current + tagLen > data.length) {
				System.out.println("消息体1不符合TLV格式");
				break;
			}
			tlvData.setType(MyUtils.subBytes(data, current, tagLen));
			current += tagLen;
			if (current + Tlv.TlvLenLength > data.length) {
				System.out.println("消息体2不符合TLV格式");
				break;
			}
			lenValue = MyUtils.byteToInteger(MyUtils.subBytes(data, current,
					Tlv.TlvLenLength));
			tlvData.setLen(MyUtils.subBytes(data, current, Tlv.TlvLenLength));
			current += Tlv.TlvLenLength;
			if (current + lenValue > data.length) {
				System.out.println("消息体3不符合TLV格式");
				break;
			}
			byte[] temp = MyUtils.subBytes(data, current, lenValue);
			tlvData.setValue(temp);
			current += lenValue;
			tlvList.add(tlvData);
		}
		return tlvList;
	}

	public static void main(String args[]) {

		List<Tlv> list=unpack("000100060060B3987654000800A3500B00040000001E500C000400001332500D000101500E00100000002800009826000098260000014D500F000C00009826000098260000000650170005302E323500502100010750240004FFFFFFC550270004000002BC5028000400000000503300040000000650350004000003EA50360004000017705037000400000001503800180000000100000078706F6F6C2E6E74702E6F7267000000005039000400000000");
		System.out.println("==="+list.size());

	}
}