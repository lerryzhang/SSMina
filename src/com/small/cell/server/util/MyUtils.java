package com.small.cell.server.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;

import com.small.cell.collections.Convert;
import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.Status;
import com.uwantsoft.goeasy.client.goeasyclient.encoder.base64.BASE64Encoder;

public class MyUtils {

	public static String parseStringFromBytes(byte[] data, int startIndex,
			int lenth) {
		return parseStringFromBytes(data, startIndex, lenth, null);
	}

	public static String parseStringFromBytes(byte[] data, int startIndex,
			int lenth, String defaultVal) {
		try {
			byte[] tmp = new byte[lenth];
			System.arraycopy(data, startIndex, tmp, 0, lenth);
			return new String(tmp, "UTF-8");
		} catch (Exception e) {

			e.printStackTrace();
			return defaultVal;
		}
	}

	public static String parseBcdStringFromBytes(byte[] data, int startIndex,
			int lenth) {
		return parseBcdStringFromBytes(data, startIndex, lenth, null);
	}

	public static String parseBcdStringFromBytes(byte[] data, int startIndex,
			int lenth, String defaultVal) {
		try {
			byte[] tmp = new byte[lenth];
			System.arraycopy(data, startIndex, tmp, 0, lenth);
			return ByteAndStr16.Bytes2HexString(tmp);
		} catch (Exception e) {

			e.printStackTrace();
			return defaultVal;
		}
	}

	public static int parseIntFromBytes(byte[] data, int startIndex, int length) {
		return parseIntFromBytes(data, startIndex, length, 0);
	}

	public static int parseIntFromBytes(byte[] data, int startIndex,
			int length, int defaultVal) {
		try {
			// 字节数大于4,从起始索引开始向后处理4个字节,其余超出部分丢弃
			final int len = length > 4 ? 4 : length;
			byte[] tmp = new byte[len];
			System.arraycopy(data, startIndex, tmp, 0, len);
			return byteToInteger(tmp);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultVal;
		}
	}

	public static byte[] getBytes(IoBuffer in) {
		byte[] data = new byte[in.limit() - in.position()];
		in.get(data);
		return data;
	}

	/**
	 * BCD字节数组===>String
	 * 
	 * @param bytes
	 * @return 十进制字符串
	 */
	public static String bcd2String(byte[] bytes) {
		StringBuilder temp = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			// 高四位
			temp.append((bytes[i] & 0xf0) >>> 4);
			// 低四位
			temp.append(bytes[i] & 0x0f);
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
				.toString().substring(1) : temp.toString();
	}

	/**
	 * 字符串==>BCD字节数组
	 * 
	 * @param str
	 * @return BCD字节数组
	 */
	public byte[] string2Bcd(String str) {
		// 奇数,前补零
		if ((str.length() & 0x1) == 1) {
			str = "0" + str;
		}

		byte ret[] = new byte[str.length() / 2];
		byte bs[] = str.getBytes();
		for (int i = 0; i < ret.length; i++) {

			byte high = ascII2Bcd(bs[2 * i]);
			byte low = ascII2Bcd(bs[2 * i + 1]);

			// TODO 只遮罩BCD低四位?
			ret[i] = (byte) ((high << 4) | low);
		}
		return ret;
	}

	public static byte ascII2Bcd(byte asc) {
		if ((asc >= '0') && (asc <= '9'))
			return (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			return (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			return (byte) (asc - 'a' + 10);
		else
			return (byte) (asc - 48);
	}

	/**
	 * 把一个整形该为byte
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static byte integerTo1Byte(int value) {
		return (byte) (value & 0xFF);
	}

	/**
	 * 把一个整形该为1位的byte数组
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static byte[] integerTo1Bytes(int value) {
		byte[] result = new byte[1];
		result[0] = (byte) (value & 0xFF);
		return result;
	}

	/**
	 * 把一个整形改为2位的byte数组
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static byte[] integerTo2Bytes(int value) {
		byte[] result = new byte[2];
		result[0] = (byte) ((value >>> 8) & 0xFF);
		result[1] = (byte) (value & 0xFF);
		return result;
	}

	/**
	 * 把一个整形改为3位的byte数组
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static byte[] integerTo3Bytes(int value) {
		byte[] result = new byte[3];
		result[0] = (byte) ((value >>> 16) & 0xFF);
		result[1] = (byte) ((value >>> 8) & 0xFF);
		result[2] = (byte) (value & 0xFF);
		return result;
	}

	/**
	 * 把一个整形改为4位的byte数组
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static byte[] integerTo4Bytes(int value) {
		byte[] result = new byte[4];
		result[0] = (byte) ((value >>> 24) & 0xFF);
		result[1] = (byte) ((value >>> 16) & 0xFF);
		result[2] = (byte) ((value >>> 8) & 0xFF);
		result[3] = (byte) (value & 0xFF);
		return result;
	}

	/**
	 * 把byte[]转化位整形,通常为指令用
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static int byteToInteger(byte[] value) {
		int result;
		if (value.length == 1) {
			result = oneByteToInteger(value[0]);
		} else if (value.length == 2) {
			result = twoBytesToInteger(value);
		} else if (value.length == 3) {
			result = threeBytesToInteger(value);
		} else if (value.length == 4) {
			result = fourBytesToInteger(value);
		} else {
			result = fourBytesToInteger(value);
		}
		return result;
	}

	/**
	 * 把一个byte转化位整形,通常为指令用
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static int oneByteToInteger(byte value) {
		return (int) value & 0xFF;
	}

	/**
	 * 把一个2位的数组转化位整形
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static int twoBytesToInteger(byte[] value) {
		// if (value.length < 2) {
		// throw new Exception("Byte array too short!");
		// }
		int temp0 = value[0] & 0xFF;
		int temp1 = value[1] & 0xFF;
		return ((temp0 << 8) + temp1);
	}

	/**
	 * 把一个3位的数组转化位整形
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static int threeBytesToInteger(byte[] value) {
		int temp0 = value[0] & 0xFF;
		int temp1 = value[1] & 0xFF;
		int temp2 = value[2] & 0xFF;
		return ((temp0 << 16) + (temp1 << 8) + temp2);
	}

	/**
	 * 把一个4位的数组转化位整形,通常为指令用
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static int fourBytesToInteger(byte[] value) {
		// if (value.length < 4) {
		// throw new Exception("Byte array too short!");
		// }
		int temp0 = value[0] & 0xFF;
		int temp1 = value[1] & 0xFF;
		int temp2 = value[2] & 0xFF;
		int temp3 = value[3] & 0xFF;
		return ((temp0 << 24) + (temp1 << 16) + (temp2 << 8) + temp3);
	}

	/**
	 * 把一个4位的数组转化位整形
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static long fourBytesToLong(byte[] value) throws Exception {
		// if (value.length < 4) {
		// throw new Exception("Byte array too short!");
		// }
		int temp0 = value[0] & 0xFF;
		int temp1 = value[1] & 0xFF;
		int temp2 = value[2] & 0xFF;
		int temp3 = value[3] & 0xFF;
		return (((long) temp0 << 24) + (temp1 << 16) + (temp2 << 8) + temp3);
	}

	/**
	 * 把一个数组转化长整形
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static long bytes2Long(byte[] value) {
		long result = 0;
		int len = value.length;
		int temp;
		for (int i = 0; i < len; i++) {
			temp = (len - 1 - i) * 8;
			if (temp == 0) {
				result += (value[i] & 0x0ff);
			} else {
				result += (value[i] & 0x0ff) << temp;
			}
		}
		return result;
	}

	/**
	 * 把一个长整形改为byte数组
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static byte[] longToBytes(long value) {
		return longToBytes(value, 8);
	}

	/**
	 * 把一个长整形改为byte数组
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static byte[] longToBytes(long value, int len) {
		byte[] result = new byte[len];
		int temp;
		for (int i = 0; i < len; i++) {
			temp = (len - 1 - i) * 8;
			if (temp == 0) {
				result[i] += (value & 0x0ff);
			} else {
				result[i] += (value >>> temp) & 0x0ff;
			}
		}
		return result;
	}

	/**
	 * 得到一个消息ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] generateTransactionID() throws Exception {
		byte[] id = new byte[16];
		System.arraycopy(integerTo2Bytes((int) (Math.random() * 65536)), 0, id,
				0, 2);
		System.arraycopy(integerTo2Bytes((int) (Math.random() * 65536)), 0, id,
				2, 2);
		System.arraycopy(integerTo2Bytes((int) (Math.random() * 65536)), 0, id,
				4, 2);
		System.arraycopy(integerTo2Bytes((int) (Math.random() * 65536)), 0, id,
				6, 2);
		System.arraycopy(integerTo2Bytes((int) (Math.random() * 65536)), 0, id,
				8, 2);
		System.arraycopy(integerTo2Bytes((int) (Math.random() * 65536)), 0, id,
				10, 2);
		System.arraycopy(integerTo2Bytes((int) (Math.random() * 65536)), 0, id,
				12, 2);
		System.arraycopy(integerTo2Bytes((int) (Math.random() * 65536)), 0, id,
				14, 2);
		return id;
	}

	/**
	 * 把IP拆分位int数组
	 * 
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public static int[] getIntIPValue(String ip) throws Exception {
		String[] sip = ip.split("[.]");
		// if (sip.length != 4) {
		// throw new Exception("error IPAddress");
		// }
		int[] intIP = { Integer.parseInt(sip[0]), Integer.parseInt(sip[1]),
				Integer.parseInt(sip[2]), Integer.parseInt(sip[3]) };
		return intIP;
	}

	/**
	 * 把byte类型IP地址转化位字符串
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public static String getStringIPValue(byte[] address) throws Exception {
		int first = oneByteToInteger(address[0]);
		int second = oneByteToInteger(address[1]);
		int third = oneByteToInteger(address[2]);
		int fourth = oneByteToInteger(address[3]);

		return first + "." + second + "." + third + "." + fourth;
	}

	/**
	 * 合并字节数组
	 * 
	 * @param first
	 * @param rest
	 * @return
	 */
	public static byte[] concatAll(byte[] first, byte[]... rest) {
		int totalLength = first.length;
		for (byte[] array : rest) {
			if (array != null) {
				totalLength += array.length;
			}
		}
		byte[] result = Arrays.copyOf(first, totalLength);

		int offset = first.length;
		for (byte[] array : rest) {
			if (array != null) {
				System.arraycopy(array, 0, result, offset, array.length);
				offset += array.length;
			}
		}
		return result;
	}

	/**
	 * 合并字节数组
	 * 
	 * @param rest
	 * @return
	 */
	public static byte[] concatAll(List<byte[]> rest) {
		int totalLength = 0;
		for (byte[] array : rest) {
			if (array != null) {
				totalLength += array.length;
			}
		}
		byte[] result = new byte[totalLength];
		int offset = 0;
		for (byte[] array : rest) {
			if (array != null) {
				System.arraycopy(array, 0, result, offset, array.length);
				offset += array.length;
			}
		}
		return result;
	}

	public static float byte2Float(byte[] bs) {
		return Float
				.intBitsToFloat((((bs[3] & 0xFF) << 24)
						+ ((bs[2] & 0xFF) << 16) + ((bs[1] & 0xFF) << 8) + (bs[0] & 0xFF)));
	}

	public static float byteBE2Float(byte[] bytes) {
		int l;
		l = bytes[0];
		l &= 0xff;
		l |= ((long) bytes[1] << 8);
		l &= 0xffff;
		l |= ((long) bytes[2] << 16);
		l &= 0xffffff;
		l |= ((long) bytes[3] << 24);
		return Float.intBitsToFloat(l);
	}

	public static int getCheckSum4JT808(byte[] bs, int start, int end) {
		if (start < 0 || end > bs.length)
			throw new ArrayIndexOutOfBoundsException(
					"getCheckSum4JT808 error : index out of bounds(start="
							+ start + ",end=" + end + ",bytes length="
							+ bs.length + ")");
		int cs = 0;
		for (int i = start; i < end; i++) {
			cs ^= bs[i];
		}
		return cs;
	}

	public static int getBitRange(int number, int start, int end) {
		if (start < 0)
			throw new IndexOutOfBoundsException("min index is 0,but start = "
					+ start);
		if (end >= Integer.SIZE)
			throw new IndexOutOfBoundsException("max index is "
					+ (Integer.SIZE - 1) + ",but end = " + end);

		return (number << Integer.SIZE - (end + 1)) >>> Integer.SIZE
				- (end - start + 1);
	}

	public static int getBitAt(int number, int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException("min index is 0,but " + index);
		if (index >= Integer.SIZE)
			throw new IndexOutOfBoundsException("max index is "
					+ (Integer.SIZE - 1) + ",but " + index);

		return ((1 << index) & number) >> index;
	}

	public static int getBitAtS(int number, int index) {
		String s = Integer.toBinaryString(number);
		return Integer.parseInt(s.charAt(index) + "");
	}

	@Deprecated
	public static int getBitRangeS(int number, int start, int end) {
		String s = Integer.toBinaryString(number);
		StringBuilder sb = new StringBuilder(s);
		while (sb.length() < Integer.SIZE) {
			sb.insert(0, "0");
		}
		String tmp = sb.reverse().substring(start, end + 1);
		sb = new StringBuilder(tmp);
		return Integer.parseInt(sb.reverse().toString(), 2);
	}

	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		System.arraycopy(src, begin, bs, 0, count);
		return bs;
	}

	public static byte[] objectToByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			bytes = byteArrayOutputStream.toByteArray();

		} catch (IOException e) {
			System.out.println("objectToByteArray failed, " + e);
		} finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					System.out.println("close objectOutputStream failed, " + e);
				}
			}
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					System.out.println("close byteArrayOutputStream failed, "
							+ e);
				}
			}

		}
		return bytes;
	}

	public static String IntegerToString16For4(int in) {

		String st = Integer.toHexString(in).toUpperCase();
		st = String.format("%4s", st);
		st = st.replaceAll(" ", "0");
		return st;

	}

	public static String IntegerToString16For2(int in) {

		String st = Integer.toHexString(in).toUpperCase();
		st = String.format("%2s", st);
		st = st.replaceAll(" ", "0");
		return st;

	}

	public static boolean valMac(String mac) {

		String patternMac = "^[A-F0-9]{2}(-[A-F0-9]{2}){5}$";
		if (!Pattern.compile(patternMac).matcher(ConvertStrToMac(mac)).find())
			return false;
		return true;

	}

	
	/*
	public static int getOnlineSum(Map<byte[], byte[]> map) throws Exception {
		int sum = 0;
		for (Entry<byte[], byte[]> entry : map.entrySet()) {
			Smtp smtp = (Smtp) ObjectUtil.bytes2Object(entry.getValue());
			if (Status.ONLINE.equals(smtp.getStatus()))
				sum++;
		}
		return sum;

	}
*/
	public static String[] getLast12Months() {

		String[] lastSixMonths = new String[6];
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1); // 要先+1,才能把本月的算进去</span>
		for (int i = 0; i < 6; i++) {
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); // 逐次往前推1个月
			lastSixMonths[5 - i] = cal.get(Calendar.YEAR) + "-"
					+ String.format("%02d", (cal.get(Calendar.MONTH) + 1));
		}

		return lastSixMonths;
	}

	public static String ConvertStrToMac(String mac) {

		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < mac.length(); i++) {
			sb.append(mac.charAt(i));
			if (i % 2 != 0 && i != mac.length() - 1) {
				sb.append("-");
			}
		}
		return sb.toString();
	}

	public static String hexStringToString(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		s = s.replace(" ", "");
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "UTF-8");
			new String();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	public static String strTo16(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * 把原始字符串分割成指定长度的字符串列表
	 * 
	 * @param inputString
	 *            原始字符串
	 * @param length
	 *            指定长度
	 * @return
	 */
	public static List<String> getStrList(String inputString, int length) {
		int size = inputString.length() / length;
		if (inputString.length() % length != 0) {
			size += 1;
		}
		return getStrList(inputString, length, size);
	}

	/**
	 * 把原始字符串分割成指定长度的字符串列表
	 * 
	 * @param inputString
	 *            原始字符串
	 * @param length
	 *            指定长度
	 * @param size
	 *            指定列表大小
	 * @return
	 */
	public static List<String> getStrList(String inputString, int length,
			int size) {
		List<String> list = new ArrayList<String>();
		for (int index = 0; index < size; index++) {
			String childStr = substring(inputString, index * length,
					(index + 1) * length);
			list.add(childStr);
		}
		return list;
	}

	/**
	 * 分割字符串，如果开始位置大于字符串长度，返回空
	 * 
	 * @param str
	 *            原始字符串
	 * @param f
	 *            开始位置
	 * @param t
	 *            结束位置
	 * @return
	 */
	public static String substring(String str, int f, int t) {
		if (f > str.length())
			return null;
		if (t > str.length()) {
			return str.substring(f, str.length());
		} else {
			return str.substring(f, t);
		}
	}

	public static String getStringFromInteger(List<String> list, int len) {
		StringBuffer temp = new StringBuffer();

		for (int i = 0; i < list.size(); i++) {
			if (i % len == 0) {
				temp.append("{").append(hexStringToString(list.get(i)))
						.append(",");

			} else if ((i + 1) % len == 0) {
				temp.append(hexStringToString(list.get(i))).append("}");
			} else {
				temp.append(hexStringToString(list.get(i))).append(",");
			}
		}
		return temp.toString();
	}

	public static String getStringFromString(List<String> list, int len) {
		StringBuffer temp = new StringBuffer();

		for (int i = 0; i < list.size(); i++) {
			if (i % len == 0) {

				// temp.append("{").append(Integer.valueOf(list.get(i), 16))
				// .append(",");

				temp.append("{").append(HexStringToInteger(list.get(i)))
						.append(",");

			} else if ((i + 1) % len == 0) {

				// temp.append(Integer.valueOf(list.get(i), 16)).append("}");

				temp.append(HexStringToInteger(list.get(i))).append("}");
			} else {

				// temp.append(Integer.valueOf(list.get(i), 16)).append(",");
				temp.append(HexStringToInteger(list.get(i))).append(",");
			}
		}
		return temp.toString();
	}

	
	public static String getRegins(List<String> list) {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			String content = list.get(i);
			temp.append("{")
					.append(HexStringToInteger(content.substring(0, 8)))
					.append(",")
					.append(HexStringToInteger(content.substring(8, 16)))
					.append(",")
					.append(HexStringToInteger(content.substring(16, 24)))
					.append(",")
					.append(HexStringToInteger(content.substring(24, 32)))
					.append(",")
					.append(HexStringToInteger(content.substring(32, 40)))
					.append(",")
					.append(HexStringToInteger(content.substring(40, 48)))
					.append(",")
					.append(HexStringToInteger(content.substring(48, 56)))
					.append(",")
					.append(hexStringToString(content.substring(56, 72)))
					.append(",")
					.append(hexStringToString(content.substring(72, 84)))
					.append(",")
					.append(hexStringToString(content.substring(84, 88)))
					.append("}");

		}
		return temp.toString();
	}
	
	

	
	public static String getSmtpState(String content) {
		StringBuffer temp = new StringBuffer();

		temp.append("{").append(HexStringToInteger(content.substring(0, 2)))
				.append(",")
				.append(HexStringToInteger(content.substring(2, 4)))
				.append(",")
				.append(HexStringToInteger(content.substring(4, 6)))
				.append(",")
				.append(HexStringToInteger(content.substring(6, 8)))
				.append(",")
				.append(HexStringToInteger(content.substring(8, 10)))
				.append(",")
				.append(hexStringToString(content.substring(10, 26)))
				.append(",")
				.append(hexStringToString(content.substring(26, 42)))
				.append(",")
				.append(hexStringToString(content.substring(42, 58)))
				.append("}");

		return temp.toString();
	}

	
	
	public static String getImsiTransmit(String content) {
		StringBuffer temp = new StringBuffer();

		temp.append("{").append(HexStringToInteger(content.substring(0, 8)))
				.append(",")
				.append(HexStringToInteger(content.substring(8, 16)))
				.append(",")
				.append(HexStringToInteger(content.substring(16, 24)))
				.append(",")
				.append(HexStringToInteger(content.substring(24, 32)))
				.append(",")
				.append(HexStringToInteger(content.substring(32, 40)))
				.append(",")
				.append(HexStringToInteger(content.substring(40, 48)))
				.append(",")
				.append(hexStringToString(content.substring(48, 88)))
				.append("}");

		return temp.toString();
	}
	


	
	public static String getNtp(String content) {
		StringBuffer temp = new StringBuffer();

		temp.append("{").append(HexStringToInteger(content.substring(0, 8)))
				.append(",")
				.append(HexStringToInteger(content.substring(8, 16)))
				.append(",")
				.append(hexStringToString(content.substring(16, 32)))
				.append("}");

		return temp.toString();
	}
	

	public static String HexStringToInteger(String str) {

		if (str.subSequence(0, 1).equals("F")) {
			return new BigInteger("FF" + str, 16).intValue() + "";
		} else {
			return new BigInteger("00" + str, 16).intValue() + "";
		}

	}

	/**
	 * 获取过去或者未来 任意天内的日期数组
	 * 
	 * @param intervals
	 *            intervals天内
	 * @return 日期数组
	 */
	public static ArrayList<String> getBeforeDays(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<String>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getPastDate(i));
		}
		return pastDaysList;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取未来 第 past 天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	public static List<String> getAarray(String str) {
		List<String> ls = new ArrayList<String>();
		Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			ls.addAll(Arrays.asList(matcher.group().split(",")));
		}
		return ls;
	}

	
	/*
	public static String getRegions(String str) {
		Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
		Matcher matcher = pattern.matcher(str);
		String body = "";
		while (matcher.find()) {
			String region = matcher.group();
			String[] arr = region.split(",");
			body = String.format("%s%s%s%s%s%s%s%s", body,
					ByteAndStr16.Bytes2HexString(integerTo4Bytes(Integer
							.parseInt(arr[0]))), ByteAndStr16
							.Bytes2HexString(integerTo4Bytes(Integer
									.parseInt(arr[1]))), ByteAndStr16
							.Bytes2HexString(integerTo4Bytes(Integer
									.parseInt(arr[2]))), ByteAndStr16
							.Bytes2HexString(integerTo4Bytes(Integer
									.parseInt(arr[3]))), ByteAndStr16
							.Bytes2HexString(integerTo4Bytes(Integer
									.parseInt(arr[4]))), strTo16(arr[5]),
					strTo16(arr[6]));

		}
		return body;

	}
	
	*/

	
	/*
	public static String getRemote(String str) {
		String body = "";
		String[] arr = str.split(",");
		body = String.format("%s%s%s%s%s%s%s", body, strTo16(arr[0]),
				strTo16(arr[1]), ByteAndStr16
						.Bytes2HexString(integerTo4Bytes(Integer
								.parseInt(arr[2]))), Long
						.toHexString(ipToLong(arr[3])), ByteAndStr16
						.Bytes2HexString(integerTo4Bytes(Integer
								.parseInt(arr[4]))), ByteAndStr16
						.Bytes2HexString(integerTo4Bytes(Integer
								.parseInt(arr[5]))));
		return body;
	}
	
	*/

	
	/*
	public static String getNtpToClient(String str) {

		String body = "";
		String[] arr = str.split(",");
		body = String.format("%s%s%s%s", body, ByteAndStr16
				.Bytes2HexString(integerTo4Bytes(Integer.parseInt(arr[0]))),
				ByteAndStr16.Bytes2HexString(integerTo4Bytes(Integer
						.parseInt(arr[1]))), strTo16(arr[2]));

		return body;
	}
	
	*/

	
	/*
	public static String getRouter(String str) {
		String body = "";
		String[] arr = str.split(",");
		body = String.format("%s%s%s%s%s%s", body, ByteAndStr16
				.Bytes2HexString(integerTo4Bytes(Integer.parseInt(arr[0]))),
				Long.toHexString(ipToLong(arr[1])), Long
						.toHexString(ipToLong(arr[2])), Long
						.toHexString(ipToLong(arr[3])), Long
						.toHexString(ipToLong(arr[4])), Long
						.toHexString(ipToLong(arr[5]))

		);
		return body;
	}
*/
	public static long ipToLong(String strIp) {
		String[] ip = strIp.split("\\.");
		return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16)
				+ (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
	}

	/**
	 * 将十进制整数形式转换成127.0.0.1形式的ip地址 将整数形式的IP地址转化成字符串的方法如下：
	 * 1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。
	 * 2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。
	 * 3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。
	 * 4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。
	 * 
	 * @param longIp
	 * @return
	 */
	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}

	public static void main(String args[]) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {

		// List<String> list =
		// getStrList("0000000000000000000000000000000000000000", 8);
		// System.out.println("===" + getStringFromInteger(list, 2));

		/*
		 * List<String> list = new ArrayList<String>(); list.add("00000001");
		 * list.add("00000001"); list.add("00000001"); list.add("00000001");
		 * String str = "FFFFFFBE";
		 */
		/*
		 * if (str.subSequence(0, 1).equals("F")) { System.out.println(new
		 * BigInteger("FF" + str, 16).intValue()); } else {
		 * System.out.println(new BigInteger("00" + str, 16).intValue()); }
		 */

		/*
		 * System.out.println("=====" + HexStringToInteger("FFFFFFBE") + "===" +
		 * HexStringToInteger("FFFFFD84") + "=====" +
		 * HexStringToInteger("000000CC"));
		 */

		/*
		 * String str="545a4c2d303130";
		 * System.out.println("====="+ByteAndStr16.Bytes2HexString
		 * (integerTo4Bytes(123)));
		 */
		//MessageDigest md5 = MessageDigest.getInstance("MD5");
		//BASE64Encoder base64en = new BASE64Encoder();

		//String newstr = base64en.encode(md5.digest("123".getBytes("utf-8")));
		//System.out.println("====" + newstr);
		
		String str="1.5";
		//System.out.println("===="+  StringUtils.leftPad(strTo16(str),10, "0").toUpperCase());
		
		System.out.println("====="+  Long.toHexString(ipToLong("192.168.1.9")));
		
		
		
		
		
		
	}

}