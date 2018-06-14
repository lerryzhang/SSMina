package com.small.cell.server.util;

import java.io.IOException;
import java.util.Arrays;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.small.cell.server.pojo.FrameFlag;
import com.small.cell.server.pojo.PackageData;
import com.small.cell.server.pojo.Para;
import com.small.cell.server.pojo.Tlv;
import com.small.cell.server.pojo.TypeCode;
import com.small.cell.server.pojo.PackageData.MsgHeader;
import com.small.cell.server.session.SessionManager;



public class Test {
    public static void main(String[] args) {
        String s = "00000011";
       
        System.out.println(Integer.valueOf(s,16));
    }
}
