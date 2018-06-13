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
		
		System.out.println("===="+MyExeUtil.getExeRes(Para.BlowFishMode_2, "4FC28DA6A6E0A8194DBAF47BAD42D79BE47BC30CF1191AC9267210A4F98423D611FD9940DD79AE57E4408F632A172FB6D149F7D3410699DFD68B2AC3DB445898BDCDF216922D5331FD67F80D340B80A533BCBF733887A7CBC6A363F3E969187EF70F68198BC62034F5523E7436F50D9AB2A8058D94CE82AE637F3606E1F72B40254383E167F861487410FC6BC11A0A0D90DE049F587907CC959D0888E20537D24F98AE843A312AB778B6934A343EB6993EED9BB3C893BDD8"));
		
		//System.out.println("0000008A08249E9E00000000000055790000982600000000FFFFF2442D3838000000000034363030300000000000018908249F9E00000000000055790000982600000000FFFFF2442D383800000000003436303030000000".length());
	}
	

}