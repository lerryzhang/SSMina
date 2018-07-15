package com.small.cell.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.util.DateParseException;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.small.cell.collections.Convert;

import com.small.cell.server.configure.SpringWebSocketHandler;
import com.small.cell.server.pojo.Control;
import com.small.cell.server.pojo.FrameFlag;
import com.small.cell.server.pojo.General;
import com.small.cell.server.pojo.PackageData;
import com.small.cell.server.pojo.Para;
import com.small.cell.server.pojo.Return;
import com.small.cell.server.pojo.Smtp;
import com.small.cell.server.pojo.Status;
import com.small.cell.server.pojo.TypeCode;
import com.small.cell.server.pojo.Upgrade;
import com.small.cell.server.pojo.PackageData.MsgHeader;
import com.small.cell.server.service.AlarmService;
import com.small.cell.server.service.UserService;
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

	@Bean
	// 这个注解会从Spring容器拿出Bean
	public SpringWebSocketHandler infoHandler() {
		return new SpringWebSocketHandler();
	}

	@Resource
	private UserService userService;

	@Resource
	private AlarmService alarmService;

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public String configureQuery(@RequestParam("mac") String mac) {
		String body = null;
		Smtp smtp = JedisUtil.hmget(Smtp.SmtpRedisKey, mac);
		PackageData packageData = new PackageData();
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setMsgFrameFlag(FrameFlag.Encrypt);
		msgHeader.setMsgTypeCode(TypeCode.ConfigureQueryRequest.getCode());
		msgHeader.setMsgVersion(MyUtils.IntegerToString16For4(Integer
				.parseInt(smtp.getVersion())));
		msgHeader.setMsgSeqNum(smtp.getSeqNum());
		try {
			body = MyExeUtil.getExeRes(Para.BlowFishMode_1, String.format(
					"%s%s%s", MyUtils.IntegerToString16For4(General.Mac),
					MyUtils.IntegerToString16For4(mac.length() / 2), mac));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("exception:" + e.getMessage());
			return Return.FAIL;
		}
		packageData.setMsgBodyBytes(body);
		msgHeader.setMsgLength(MyUtils
				.IntegerToString16For4(PackageData.msgHeaderLength
						+ (body.length() / 2)));
		packageData.setMsgHeader(msgHeader);

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

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<Smtp> list = JedisUtil.hvals(Smtp.SmtpRedisKey);
		request.setAttribute("list", list);
		request.setAttribute("total", list.size());
		return "index";
	}

	@RequestMapping("/termList")
	public String termList(HttpServletRequest request) {
		List<Smtp> list = JedisUtil.hvals(Smtp.SmtpRedisKey);
		request.setAttribute("list", list);
		request.setAttribute("total", list.size());
		return "terminal/term";
	}

	@RequestMapping("/updateIndex")
	public String updateIndex(HttpServletRequest request, Model model) {
		String mac = request.getParameter("mac");
		model.addAttribute("mac", mac);
		return "terminal/termUpdate";
	}

	@RequestMapping("/controlIndex")
	public String controlIndex(HttpServletRequest request, Model model) {
		String mac = request.getParameter("mac");
		model.addAttribute("mac", mac);
		return "terminal/termControl";
	}

	@RequestMapping("/console")
	public String console() {
		return "console";
	}

	/**
	 * @throws DateParseException
	 * 
	 * 
	 * @Title: getKuNameAndCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param request
	 * @param @param response
	 * @param @return 设定文件
	 * @author lerry
	 * @date 2018-7-9 上午10:30:16
	 * @return String 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getKuNameAndCount")
	@ResponseBody
	public String getKuNameAndCount(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonobject = new JSONObject();
		int ccount = 0, zhichu = 0;
		@SuppressWarnings("rawtypes")
		Map map = JedisUtil.hgetAll(Smtp.SmtpRedisKey.getBytes());
		try {
			ccount = MyUtils.getOnlineSum(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonobject.put("tcount", map.size());
		jsonobject.put("ocount", ccount);
		jsonobject.put("ghichu", zhichu);
		jsonobject.put("ucount", userService.selectUserCount());
		jsonobject.put("month", MyUtils.getLast12Months());
		List<String> pastDayList = MyUtils.getBeforeDays(7);
		jsonobject.put("week", pastDayList);
		List<Integer> countList = new ArrayList<Integer>();
		for (int i = 0; i < pastDayList.size(); i++) {
			int count = alarmService.selectAlarmCountByDay(pastDayList.get(i));
			countList.add(count);
		}
		jsonobject.put("countList", countList);
		return jsonobject.toString();

	}

	@RequestMapping("/control")
	@ResponseBody
	public String control(HttpServletRequest request) throws IOException,
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
		msgHeader.setMsgVersion(MyUtils.IntegerToString16For4(Integer
				.parseInt(smtp.getVersion())));
		msgHeader.setMsgSeqNum(smtp.getSeqNum());
		switch (Control.getControl(param)) {
		case Upgrade:
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			url = request.getParameter("upgradeUrl");
			String version = request.getParameter("version");
			smtp.setStatus(Status.UPGRADE);
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
			body = String.format("%s%s%s%s%s%s", MyUtils
					.IntegerToString16For4(General.Mac), MyUtils
					.IntegerToString16For4(mac.length() / 2), mac, param,
					MyUtils.IntegerToString16For4(body.length() / 2), body);

			break;
		case Restart:
			body = String.format("%s%s%s%s%s%s", MyUtils
					.IntegerToString16For4(General.Mac), MyUtils
					.IntegerToString16For4(mac.length() / 2), mac, param,
					MyUtils.IntegerToString16For4(1), "01");
			smtp.setStatus(Status.RESTART);
			break;
		case Reset:
			body = String.format("%s%s%s%s%s%s", MyUtils
					.IntegerToString16For4(General.Mac), MyUtils
					.IntegerToString16For4(mac.length() / 2), mac, param,
					MyUtils.IntegerToString16For4(1), "01");
			smtp.setStatus(Status.RESET);
			break;
		case RouterUpgrade:
			url = request.getParameter("routerUrl");
			smtp.setStatus(Status.ROUTERUPGRADE);
			body = String.format("%s%s%s", Upgrade.Url.getCode(), MyUtils
					.IntegerToString16For4(MyUtils.strTo16(url).length() / 2),
					MyUtils.strTo16(url));
			body = String.format("%s%s%s%s%s%s", MyUtils
					.IntegerToString16For4(General.Mac), MyUtils
					.IntegerToString16For4(mac.length() / 2), mac, param,
					MyUtils.IntegerToString16For4(body.length() / 2), body);
			break;
		}
		body = MyExeUtil.getExeRes(Para.BlowFishMode_1, body);
		msgHeader.setMsgLength(MyUtils
				.IntegerToString16For4(PackageData.msgHeaderLength
						+ (body.length() / 2)));
		packageData.setMsgHeader(msgHeader);
		packageData.setMsgBodyBytes(body);
		IoSession session = SessionManager.getManager().get(mac);
		infoHandler().sendMessageToUsers(
				new TextMessage(String.format("%s,%s", mac, Control
						.getByValue(param))));
		if (session == null) {
			logger.info("session is null!");
			return Return.FAIL;
		}
		session.write(IoBuffer.wrap(ByteAndStr16.HexString2Bytes(packageData
				.toString())));
		JedisUtil.hmset(Smtp.SmtpRedisKey, mac, smtp);

		return Return.SUCCESS;

	}

	@RequestMapping("/viewSmtp")
	public String viewSmtp(HttpServletRequest request, Model model)
			throws IOException, InterruptedException {
		String mac = request.getParameter("mac");
		Smtp smtp = JedisUtil.hmget(Smtp.SmtpRedisKey, mac);
		model.addAttribute("smtp", smtp);
		return "terminal/termView";
	}

	@SuppressWarnings( { "rawtypes", "unchecked" })
	@RequestMapping("/update")
	@ResponseBody
	public String update(HttpServletRequest request) throws IOException,
			InterruptedException {
		String mac = request.getParameter("mac");
		JSONObject jb = JSONObject.fromObject(request.getParameter("username"));
		Map map = (Map) jb;
		if (map.size() > 0) {
			String body = "";
			Smtp smtp = JedisUtil.hmget(Smtp.SmtpRedisKey, mac);
			PackageData packageData = new PackageData();
			MsgHeader msgHeader = new MsgHeader();
			msgHeader.setMsgFrameFlag(FrameFlag.Encrypt);
			msgHeader.setMsgTypeCode(TypeCode.ConfigureUpdateRequest.getCode());
			msgHeader.setMsgVersion(MyUtils.IntegerToString16For4(Integer
					.parseInt(smtp.getVersion())));
			msgHeader.setMsgSeqNum(smtp.getSeqNum());
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				String value = (String) map.get(key);
				
				System.out.println("===="+value);
				if (Convert.list1.contains(key)) {
					body = String.format("%s%s%s%s", body, key, MyUtils
							.IntegerToString16For4(MyUtils.strTo16(value)
									.length() / 2), MyUtils.strTo16(value));
				} else if (Convert.list2.contains(key)) {
					body = String
							.format(
									"%s%s%s%s",
									body,
									key,
									MyUtils
											.IntegerToString16For4(ByteAndStr16
													.Bytes2HexString(
															MyUtils
																	.integerTo4Bytes(Integer
																			.parseInt(value)))
													.length() / 2),
									ByteAndStr16.Bytes2HexString(MyUtils
											.integerTo4Bytes(Integer
													.parseInt(value))));
				} else if (Convert.list3.contains(key)) {
					String[] strArr = value.split(",");
					for (int i = 0; i < strArr.length; i++) {
						body = String.format("%s%s", body, ByteAndStr16
								.Bytes2HexString(MyUtils
										.integerTo4Bytes(Integer
												.parseInt(strArr[i]))));
					}
					body = String.format("%s%s%s", key, MyUtils
							.IntegerToString16For4(body.length() / 2), body);
				} else if (Convert.list4.contains(key)) {
					List<String> list = MyUtils.getAarray(value);
					for (int i = 0; i < list.size(); i++) {
						body = String.format("%s%s", body, ByteAndStr16
								.Bytes2HexString(MyUtils
										.integerTo4Bytes(Integer.parseInt(list
												.get(i)))));
					}
					body = String.format("%s%s%s", key, MyUtils
							.IntegerToString16For4(body.length() / 2), body);
				}

				else if (Convert.list5.contains(key)) {
					String[] strArr = value.split(",");
					for (int i = 0; i < strArr.length; i++) {
						body = String.format("%s%s", body, MyUtils
								.strTo16(strArr[i]));
					}
					body = String.format("%s%s%s", key, MyUtils
							.IntegerToString16For4(body.length() / 2), body);
				}else if (Convert.Regions.equals(key)) {
					body = MyUtils.getRegions(value);
					body = String.format("%s%s%s", key, MyUtils
							.IntegerToString16For4(body.length() / 2),body);
				}
				
				else if (Convert.Ntp.equals(key)) {
					
					body = MyUtils.getNtpToClient(value);
					body = String.format("%s%s%s", key, MyUtils
							.IntegerToString16For4(body.length() / 2),body);
				}else {
					body = String.format("%s%s%s%s", body, key, MyUtils
							.IntegerToString16For4(value.length() / 2), value);
				}
			}
			body = String.format("%s%s%s%s%s%s", MyUtils
					.IntegerToString16For4(General.Mac), MyUtils
					.IntegerToString16For4(mac.length() / 2), mac, smtp
					.getCorrModel(), MyUtils.IntegerToString16For4(body
					.length() / 2), body);
			body = MyExeUtil.getExeRes(Para.BlowFishMode_1, body);
			msgHeader.setMsgLength(MyUtils
					.IntegerToString16For4(PackageData.msgHeaderLength
							+ (body.length() / 2)));
			packageData.setMsgHeader(msgHeader);
			packageData.setMsgBodyBytes(body);
			IoSession session = SessionManager.getManager().get(mac);

			System.out.println("=====uuu=====" + packageData.toString());
			if (session == null) {
				logger.info("session is null!");
				return Return.FAIL;
			}
			session.write(IoBuffer.wrap(ByteAndStr16
					.HexString2Bytes(packageData.toString())));
		}

		return Return.SUCCESS;
	}
}
