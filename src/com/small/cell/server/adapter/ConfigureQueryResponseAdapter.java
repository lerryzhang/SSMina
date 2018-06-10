package com.small.cell.server.adapter;

import java.io.IOException;
import java.util.List;


import com.small.cell.server.pojo.PackageData;
import com.small.cell.server.pojo.Tlv;

import com.small.cell.server.util.TlvTools;

public class ConfigureQueryResponseAdapter {
	public static PackageData handler(PackageData packageData) {

		List<Tlv> tlvList = TlvTools.unpack(packageData.getMsgBodyBytes());
		
		
		
		
		return packageData;

	}

}
