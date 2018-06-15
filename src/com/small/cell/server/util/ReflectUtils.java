package com.small.cell.server.util;


import java.lang.reflect.Method;
import java.util.List;

import com.small.cell.collections.Convert;
import com.small.cell.server.pojo.ParaEnum;
import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.Tlv;

public class ReflectUtils {


	public static Object setProperty(Object obj, String propertyName,
			Object value) throws Exception {
		// Class clazz = obj.getClass(); // 获取字节码对象
		// Field field = clazz.getDeclaredField(propertyName); // 暴力反射获取字段
		// field.setAccessible(true); // 设置访问权限
		// field.set(obj, value); // 设置值

		String strMethod = "set" + propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1);
		System.out.println("====" + strMethod);
		Method method = obj.getClass().getMethod(strMethod, String.class);
		method.invoke(obj, value);
		return obj;
	
	}

	public static Object setProperty(List<Tlv> list) throws Exception {
		Smtp smtp = new Smtp();
		for (int i = 0; i < list.size(); i++) {
			Tlv tlv = list.get(i);
			String type = ByteAndStr16.Bytes2HexString(tlv.getType());
			String param = ParaEnum.getName(type);
			if (null != param) {
				if (Convert.list.contains(param))
					if (ParaEnum.ZL_81X3_D.equals(param))
						setProperty(smtp, ParaEnum.getName(ByteAndStr16
								.Bytes2HexString(tlv.getType())), ByteAndStr16
								.Bytes2HexString(tlv.getType()));
					else

						setProperty(smtp, ParaEnum.getName(ByteAndStr16
								.Bytes2HexString(tlv.getType())), ByteAndStr16
								.Bytes2HexString(tlv.getValue()));
				else
					setProperty(smtp, ParaEnum.getName(ByteAndStr16
							.Bytes2HexString(tlv.getType())),ByteAndStr16.Bytes2HexString(tlv
									.getValue()));

			}
		}
		return smtp;

	}

	public static Object setProperty(List<Tlv> list, Smtp smtp)
			throws Exception {

		for (int i = 0; i < list.size(); i++) {
			Tlv tlv = list.get(i);
			String param = ParaEnum.getName(ByteAndStr16.Bytes2HexString(tlv
					.getType()));
			if (null != param) {
				if (Convert.list.contains(param))
					if (ParaEnum.ZL_81X3_D.getName().equals(param))
						setProperty(smtp, ParaEnum.getName(ByteAndStr16
								.Bytes2HexString(tlv.getType())), ByteAndStr16
								.Bytes2HexString(tlv.getType()));
					else

						setProperty(smtp, ParaEnum.getName(ByteAndStr16
								.Bytes2HexString(tlv.getType())), ByteAndStr16
								.Bytes2HexString(tlv.getValue()));
				else
					setProperty(smtp, ParaEnum.getName(ByteAndStr16
							.Bytes2HexString(tlv.getType())), ByteAndStr16.Bytes2HexString(tlv
									.getValue()));

			}
		}
		return smtp;

	}

	public static void main(String args[]) throws Exception {

		Smtp smtp = new Smtp();
		setProperty(smtp, ParaEnum.getName("0001"), "111");
		System.out.println("===" + smtp.getMac());

	}
}