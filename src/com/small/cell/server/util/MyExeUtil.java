package com.small.cell.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.small.cell.server.pojo.Para;
import com.small.cell.server.pojo.Upgrade;

public class MyExeUtil {

	public static final int SUCCESS = 0; // 表示程序执行成功

	public static final String PATH = "C:/Users/Administrator/Desktop/Debug/Win32Project2.exe"; // 要执行的语句
	public static final String PASSWORD = "6t#$z@_321";
	//public static final String PASSWORD = "abc#$321";
	

	public static final String SUCCESS_MESSAGE = "程序执行成功！";

	public static final String ERROR_MESSAGE = "程序执行出错：";

	public static String getExeRes(int mode, String strSource)
			throws IOException, InterruptedException {

		String strRes = null;

		String str = String.format("%s %s %s %s", PATH, mode, PASSWORD,
				strSource);

		Runtime runtime = Runtime.getRuntime();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					runtime.exec(str).getInputStream()));
			String line = null;
			StringBuffer b = new StringBuffer();
			while ((line = br.readLine()) != null) {
				b.append(line + "\n");
			}
			strRes = b.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRes;
	}
	
	
	public static void main(String args[]) throws IOException, InterruptedException{
		
		// getExeRes(1,"6777636E35505674716C6132314F5433");
		
		//System.out.println("500B00040000001E500C000400001332500D000101500E00100000002800009826000098260000014D500F000C00009826000098260000000650170005302E323500502100010750240004FFFFFFC550270004000002BC5028000400000000503300040000000650350004000003EA50360004000017705037000400000001503800180000000100000078706F6F6C2E6E74702E6F7267000000005039000400000000".length());
		
		System.out.println("===="+MyExeUtil.getExeRes(Para.BlowFishMode_2, "4FC28DA6A6E0A8198082346CEA17F424CA1F1CC814F949428F7C31CA9D66ACDD1854B2E7FB51DC211E09338900AD2226BCB3A8541DD642C6EF8BB90A3C6468162A1B198D68B9E1AA9C805B757A1CE999"));
		
		//System.out.println("0000008A08249E9E00000000000055790000982600000000FFFFF2442D3838000000000034363030300000000000018908249F9E00000000000055790000982600000000FFFFF2442D383800000000003436303030000000".length());
	}
	

}