package com.small.cell.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import com.small.cell.server.pojo.Control;
import com.small.cell.server.pojo.FrameFlag;
import com.small.cell.server.pojo.General;
import com.small.cell.server.pojo.PackageData;
import com.small.cell.server.pojo.Para;
import com.small.cell.server.pojo.Return;
import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.TypeCode;
import com.small.cell.server.pojo.Upgrade;
import com.small.cell.server.pojo.PackageData.MsgHeader;
import com.small.cell.server.session.SessionManager;
import com.small.cell.server.util.ByteAndStr16;
import com.small.cell.server.util.JedisUtil;
import com.small.cell.server.util.Layui;
import com.small.cell.server.util.MyExeUtil;
import com.small.cell.server.util.MyUtils;
import com.small.cell.server.util.PageUtils;

@Controller
@RequestMapping("/smallCell")
public class SmallCellController {
	private Logger logger = Logger.getLogger(SmallCellController.class);

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Integer configureQuery(@RequestParam("mac") String mac) {
		Smtp smtp = JedisUtil.hmget(Smtp.SmtpRedisKey, mac);
		PackageData packageData = new PackageData();
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setMsgFrameFlag(FrameFlag.Encrypt);
		msgHeader.setMsgTypeCode(TypeCode.ConfigureQueryRequest.getCode());
		msgHeader.setMsgVersion(smtp.getVersion());
		msgHeader.setMsgSeqNum(smtp.getSeqNum());
		msgHeader.setMsgLength(MyUtils
				.IntegerToString16For4(PackageData.msgHeaderLength
						+ mac.length() / 2));
		packageData.setMsgHeader(msgHeader);
		String body = String.format("%s%s%s",
				MyUtils.IntegerToString16For4(General.Mac),
				MyUtils.IntegerToString16For4(mac.length() / 2), mac);
		try {
			packageData.setMsgBodyBytes(MyExeUtil.getExeRes(
					Para.BlowFishMode_1, body));
		} catch (Exception e) {
			logger.info("exception:" + e.getMessage());
			return Return.FAIL;
		}
		IoSession session = SessionManager.getManager().get(mac);
		if (session == null) {
			logger.info("session is null!");
			return Return.FAIL;
		}
		session.write(IoBuffer.wrap(ByteAndStr16.HexString2Bytes(packageData
				.toString())));
		return Return.SUCCESS;

	}

	@RequestMapping("/list")
	@ResponseBody
	public Layui list(@RequestParam Map<String, Object> params) {
		List<Smtp> list = JedisUtil.hvals(Smtp.SmtpRedisKey);
		PageUtils pageUtil = new PageUtils(list, list.size(), 10, 1);
		return Layui.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

	@RequestMapping("/control")
	@ResponseBody
	public Integer control(HttpServletRequest request) throws IOException,
			InterruptedException {
		String url = null;
		String body = null;
		String param = request.getParameter("control");
		String mac = request.getParameter("mac");
		Smtp smtp = JedisUtil.hmget(Smtp.SmtpRedisKey, mac);
		PackageData packageData = new PackageData();
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setMsgFrameFlag(FrameFlag.Encrypt);
		msgHeader.setMsgTypeCode(TypeCode.ControlRequest.getCode());
		msgHeader.setMsgVersion(smtp.getVersion());
		msgHeader.setMsgSeqNum(smtp.getSeqNum());

		switch (Control.getByValue(param)) {
		case Upgrade:
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			url = request.getParameter("upgradeUrl");
			String version = request.getParameter("version");
			body = String.format("%s%s%s%s%s%s%s%s%s%s%s%s", Upgrade.Username
					.getCode(), MyUtils.IntegerToString16For4(MyUtils.strTo16(
					username).length() / 2), MyUtils.strTo16(username),
					Upgrade.Password.getCode(), MyUtils
							.IntegerToString16For4(MyUtils.strTo16(password)
									.length() / 2), MyUtils.strTo16(password),
					Upgrade.Url.getCode(), MyUtils
							.IntegerToString16For4(MyUtils.strTo16(url)
									.length() / 2), MyUtils.strTo16(url),
					Upgrade.Version.getCode(), MyUtils
							.IntegerToString16For4(MyUtils.strTo16(version)
									.length() / 2), MyUtils.strTo16(version));
			body = String.format("%s%s%s%s%s%s",
					MyUtils.IntegerToString16For4(General.Mac),
					MyUtils.IntegerToString16For4(mac.length() / 2), mac,
					param, MyUtils.IntegerToString16For4(body.length() / 2),
					body);

			break;
		case Restart:
			body = String.format("%s%s%s%s%s%s",
					MyUtils.IntegerToString16For4(General.Mac),
					MyUtils.IntegerToString16For4(mac.length() / 2), mac,
					param, MyUtils.IntegerToString16For2(1), "01");
			break;
		case Reset:
			body = String.format("%s%s%s%s%s%s",
					MyUtils.IntegerToString16For4(General.Mac),
					MyUtils.IntegerToString16For4(mac.length() / 2), mac,
					param, MyUtils.IntegerToString16For2(1), "01");
			break;
		case RouterUpgrade:
			url = request.getParameter("routerUrl");
			body = String.format("%s%s%s", Upgrade.Url.getCode(), MyUtils
					.IntegerToString16For4(MyUtils.strTo16(url).length() / 2),
					MyUtils.strTo16(url));
			body = String.format("%s%s%s%s%s%s",
					MyUtils.IntegerToString16For4(General.Mac),
					MyUtils.IntegerToString16For4(mac.length() / 2), mac,
					param, MyUtils.IntegerToString16For4(body.length() / 2),
					body);
			break;
		}
		body = MyExeUtil.getExeRes(Para.BlowFishMode_1, body);
		msgHeader.setMsgLength(MyUtils
				.IntegerToString16For4(PackageData.msgHeaderLength
						+ body.length() / 2));
		packageData.setMsgHeader(msgHeader);
		packageData.setMsgBodyBytes(body);
		IoSession session = SessionManager.getManager().get(mac);
		if (session == null) {
			logger.info("session is null!");
			return Return.FAIL;
		}
		session.write(IoBuffer.wrap(ByteAndStr16.HexString2Bytes(packageData
				.toString())));
		return Return.SUCCESS;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Integer update(HttpServletRequest request) throws IOException,
			InterruptedException {
		String mac=request.getParameter("mac");
		return Return.SUCCESS;
	}
}
