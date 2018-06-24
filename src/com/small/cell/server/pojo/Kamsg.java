package com.small.cell.server.pojo;

import org.apache.mina.core.buffer.IoBuffer;

public class Kamsg {
	
	
	public static final String CONNECT_STATE = "CONNECT_STATE";
	public static final String CONNECT_START = "CONNECT_START";
	public static final String CONNECT_OK = "CONNECT_OK";
	public static final String CONNECT_NO = "CONNECT_NO";
	public static final String HeartBeat = "HeartBeat";
	
	

	public static final IoBuffer KAMSG_REQ = IoBuffer.wrap(new byte[]{0x0D, '+', 0x0A});   
    public static final IoBuffer KAMSG_REP = IoBuffer.wrap(new byte[]{0x0D, '-', 0x0A});  
}
