package com.small.cell.server.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.Status;
import com.small.cell.server.service.ParsService;
import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.MyUtils;
import com.small.cell.server.util.ObjectUtil;

@Service("parsService")
public class ParsServiceImpl implements ParsService {

	@Override
	public int getOnlineSum(Map<byte[], byte[]> map) {
		// TODO Auto-generated method stub
		int sum = 0;
		Smtp smtp = null;
		for (Entry<byte[], byte[]> entry : map.entrySet()) {
			try {
				smtp = (Smtp) ObjectUtil.bytes2Object(entry.getValue());

			} catch (Exception e) {
				return sum;
			}
			if (Status.ONLINE.equals(smtp.getStatus()))
				sum++;
		}
		return sum;
	}

	@Override
	public String getRegins(List<String> list) {
		// TODO Auto-generated method stub
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			String content = list.get(i);
			temp.append("{")
					.append(MyUtils.HexStringToInteger(content.substring(0, 8)))
					.append(",")
					.append(MyUtils.HexStringToInteger(content.substring(8, 16)))
					.append(",")
					.append(MyUtils.HexStringToInteger(content
							.substring(16, 24)))
					.append(",")
					.append(MyUtils.HexStringToInteger(content
							.substring(24, 32)))
					.append(",")
					.append(MyUtils.HexStringToInteger(content
							.substring(32, 40)))
					.append(",")
					.append(MyUtils.HexStringToInteger(content
							.substring(40, 48)))
					.append(",")
					.append(MyUtils.HexStringToInteger(content
							.substring(48, 56)))
					.append(",")
					.append(MyUtils.hexStringToString(content.substring(56, 72)))
					.append(",")
					.append(MyUtils.hexStringToString(content.substring(72, 84)))
					.append(",")
					.append(MyUtils.hexStringToString(content.substring(84, 88)))
					.append("}");

		}
		return temp.toString();
	}

	@Override
	public String getSmtpState(String content) {
		// TODO Auto-generated method stub
		StringBuffer temp = new StringBuffer();

		temp.append("{")
				.append(MyUtils.HexStringToInteger(content.substring(0, 2)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(2, 4)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(4, 6)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(6, 8)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(8, 10)))
				.append(",")
				.append(MyUtils.hexStringToString(content.substring(10, 26)))
				.append(",")
				.append(MyUtils.hexStringToString(content.substring(26, 42)))
				.append(",")
				.append(MyUtils.hexStringToString(content.substring(42, 58)))
				.append("}");

		return temp.toString();
	}

	@Override
	public String getImsiTransmit(String content) {
		// TODO Auto-generated method stub
		StringBuffer temp = new StringBuffer();

		temp.append("{")
				.append(MyUtils.HexStringToInteger(content.substring(0, 8)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(8, 16)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(16, 24)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(24, 32)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(32, 40)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(40, 48)))
				.append(",")
				.append(MyUtils.hexStringToString(content.substring(48, 88)))
				.append("}");

		return temp.toString();
	}

	@Override
	public String getNtp(String content) {
		// TODO Auto-generated method stub
		StringBuffer temp = new StringBuffer();

		temp.append("{")
				.append(MyUtils.HexStringToInteger(content.substring(0, 8)))
				.append(",")
				.append(MyUtils.HexStringToInteger(content.substring(8, 16)))
				.append(",")
				.append(MyUtils.hexStringToString(content.substring(16, 32)))
				.append("}");

		return temp.toString();
	}

	@Override
	public String getRegions(String key, String value) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
		Matcher matcher = pattern.matcher(value);
		//String body = "";
		StringBuffer temp = new StringBuffer();
		while (matcher.find()) {
			String region = matcher.group();
			String[] arr = region.split(",");
			/*body = String.format("%s%s%s%s%s%s%s%s", body, ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[0]))), ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[1]))), ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[2]))), ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[3]))), ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[4]))), MyUtils.strTo16(arr[5]),
					MyUtils.strTo16(arr[6]));*/
			
			
			temp.append(ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[0])))).append(ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[1])))).append(ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[2])))).append( ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[3])))).append(ByteAndStr16
					.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(arr[4])))).append(MyUtils.strTo16(arr[5])).append(MyUtils.strTo16(arr[6]));

		}
		String body=temp.toString();
		temp.setLength(0);
		temp.append(key).append(MyUtils.IntegerToString16For4(body.length() / 2)).append(body);

		//body = String.format("%s%s%s", key,
		//		MyUtils.IntegerToString16For4(body.length() / 2), body);
		return temp.toString();
	}

	@Override
	public String getRemote(String key, String value) {
		// TODO Auto-generated method stub
		//String body = "";
		StringBuffer temp = new StringBuffer();
		String[] arr = value.split(",");
		/*
		body = String.format("%s%s%s%s%s%s%s", body, MyUtils.strTo16(arr[0]),
				MyUtils.strTo16(arr[1]), ByteAndStr16.Bytes2HexString(MyUtils
						.integerTo4Bytes(Integer.parseInt(arr[2]))), Long
						.toHexString(MyUtils.ipToLong(arr[3])), ByteAndStr16
						.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
								.parseInt(arr[4]))), ByteAndStr16
						.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
								.parseInt(arr[5]))));
								
								*/
		
		
		temp.append( MyUtils.strTo16(arr[0])).append(MyUtils.strTo16(arr[1])).append(ByteAndStr16.Bytes2HexString(MyUtils
						.integerTo4Bytes(Integer.parseInt(arr[2])))).append(Long
						.toHexString(MyUtils.ipToLong(arr[3]))).append(ByteAndStr16
						.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
								.parseInt(arr[4])))).append(ByteAndStr16
						.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
								.parseInt(arr[5]))));
		
		String body=temp.toString();
		temp.setLength(0);
		temp.append(key).append(MyUtils.IntegerToString16For4(body.length() / 2)).append(body);

		//body = String.format("%s%s%s", key,
		//		MyUtils.IntegerToString16For4(body.length() / 2), body);
		return temp.toString();
	}

	@Override
	public String getNtpToClient(String key, String value) {
		// TODO Auto-generated method stub

		//String body = "";
		StringBuffer temp = new StringBuffer();
		String[] arr = value.split(",");
		/*
		body = String.format("%s%s%s%s", body, ByteAndStr16
				.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
						.parseInt(arr[0]))), ByteAndStr16
				.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
						.parseInt(arr[1]))), MyUtils.strTo16(arr[2]));
		body = String.format("%s%s%s", key,
				MyUtils.IntegerToString16For4(body.length() / 2), body);
		return body;
		*/
		
		temp.append(ByteAndStr16
				.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
						.parseInt(arr[0])))).append(ByteAndStr16
				.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
						.parseInt(arr[1])))).append(MyUtils.strTo16(arr[2]));
		
		String body=temp.toString();
		temp.setLength(0);
		temp.append(key).append(MyUtils.IntegerToString16For4(body.length() / 2)).append(body);
		return temp.toString();
		
	}

	@Override
	public String getRouter(String key,String value) {
		// TODO Auto-generated method stub
		//String body = "";
		
		StringBuffer temp = new StringBuffer();
		
		String[] arr = value.split(",");
		
		/*
		body = String.format("%s%s%s%s%s%s", body, ByteAndStr16
				.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
						.parseInt(arr[0]))), Long.toHexString(MyUtils
				.ipToLong(arr[1])), Long.toHexString(MyUtils.ipToLong(arr[2])),
				Long.toHexString(MyUtils.ipToLong(arr[3])), Long
						.toHexString(MyUtils.ipToLong(arr[4])), Long
						.toHexString(MyUtils.ipToLong(arr[5]))

		);
		return body;
		*/
		
		temp.append(ByteAndStr16
				.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
						.parseInt(arr[0])))).append(Long.toHexString(MyUtils
				.ipToLong(arr[1]))).append(Long.toHexString(MyUtils.ipToLong(arr[2]))).append(Long.toHexString(MyUtils.ipToLong(arr[3]))).append(Long
						.toHexString(MyUtils.ipToLong(arr[4]))).append(Long
						.toHexString(MyUtils.ipToLong(arr[5])));
		String body=temp.toString();
		temp.setLength(0);
		temp.append(key).append(MyUtils.IntegerToString16For4(body.length() / 2)).append(body);
		return temp.toString();
	}

	@Override
	public String getStringArray(String key, String value) {
		// TODO Auto-generated method stub
		//String body = "";
		StringBuffer temp = new StringBuffer();
		String[] strArr = value.split(",");
		for (int i = 0; i < strArr.length; i++) {
			//body = String.format("%s%s", body, MyUtils.strTo16(strArr[i]));
			temp.append(MyUtils.strTo16(strArr[i]));
		}

	//	body = String.format("%s%s%s", key,
		//		MyUtils.IntegerToString16For4(body.length() / 2), body);
		//return body;
		
		String body=temp.toString();
		temp.setLength(0);
		temp.append(key).append(MyUtils.IntegerToString16For4(body.length() / 2)).append(body);
		return temp.toString();
	}

	@Override
	public String getFrequencyTable(String key, String value) {
		// TODO Auto-generated method stub
	//	String body = "";
		StringBuffer temp = new StringBuffer();
		List<String> list = MyUtils.getAarray(value);
		for (int i = 0; i < list.size(); i++) {
		//	body = String.format("%s%s", body, ByteAndStr16
				//	.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
				//			.parseInt(list.get(i)))));
			temp.append( ByteAndStr16
						.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
							.parseInt(list.get(i)))));
		}
		//body = String.format("%s%s%s", key,
		//		MyUtils.IntegerToString16For4(body.length() / 2), body);
		//return body;
		String body=temp.toString();
		temp.setLength(0);
		temp.append(key).append(MyUtils.IntegerToString16For4(body.length() / 2)).append(body);
		return temp.toString();
	}

	@Override
	public String getIntegerArray(String key, String value) {
		// TODO Auto-generated method stub
	//	String body = "";
		StringBuffer temp = new StringBuffer();
		String[] strArr = value.split(",");
		for (int i = 0; i < strArr.length; i++) {
			//body = String.format("%s%s", body, ByteAndStr16
			//		.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
			//				.parseInt(strArr[i]))));
			
			temp.append(  ByteAndStr16
						.Bytes2HexString(MyUtils.integerTo4Bytes(Integer
								.parseInt(strArr[i]))));
		}
		//body = String.format("%s%s%s", key,
			//	MyUtils.IntegerToString16For4(body.length() / 2), body);
		//return body;
		String body=temp.toString();
		temp.setLength(0);
		temp.append(key).append(MyUtils.IntegerToString16For4(body.length() / 2)).append(body);
		return temp.toString();
	}

	@Override
	public String getString(String key, String value) {
		// TODO Auto-generated method stub
		//String body = "";
		StringBuffer temp = new StringBuffer();
		
		
		//body = String.format("%s%s%s%s", body, key, MyUtils
			//	.IntegerToString16For4(MyUtils.strTo16(value).length() / 2),
			//	MyUtils.strTo16(value));
		temp.append(key).append(MyUtils	.IntegerToString16For4(MyUtils.strTo16(value).length() / 2)).append(MyUtils.strTo16(value));
		return temp.toString();
	}

	@Override
	public String getInteger(String key, String value) {
		// TODO Auto-generated method stub
	//	String body="";
		StringBuffer temp = new StringBuffer();
		/*body = String
				.format("%s%s%s%s",
						body,
						key,
						MyUtils.IntegerToString16For4(ByteAndStr16
								.Bytes2HexString(
										MyUtils.integerTo4Bytes(Integer
												.parseInt(value)))
								.length() / 2), ByteAndStr16
								.Bytes2HexString(MyUtils
										.integerTo4Bytes(Integer
												.parseInt(value))));*/
		temp.append(key).append(MyUtils.IntegerToString16For4(ByteAndStr16
								.Bytes2HexString(
										MyUtils.integerTo4Bytes(Integer
												.parseInt(value)))
								.length() / 2)).append(ByteAndStr16
								.Bytes2HexString(MyUtils
										.integerTo4Bytes(Integer
												.parseInt(value))));
		
		return temp.toString();
	}

	@Override
	public String getIp(String key, String value) {
		// TODO Auto-generated method stub
		StringBuffer temp = new StringBuffer();
		temp.append(key).append( MyUtils.IntegerToString16For4(Long.toHexString(MyUtils.ipToLong(value)).length())).append(Long.toHexString(MyUtils.ipToLong(value)));
		return temp.toString();
	}

	
}
