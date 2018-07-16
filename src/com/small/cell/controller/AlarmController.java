package com.small.cell.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.small.cell.server.configure.SpringWebSocketHandler;
import com.small.cell.server.pojo.Alarm;

import com.small.cell.server.service.AlarmService;

import com.small.cell.server.util.Layui;
import com.small.cell.server.util.PageUtils;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

	@Bean
	// 这个注解会从Spring容器拿出Bean
	public SpringWebSocketHandler infoHandler() {
		return new SpringWebSocketHandler();
	}

	@Resource
	private AlarmService alarmService;

	@RequestMapping("/index")
	public String index() {
		return "alarm/alarm";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Layui list(HttpServletRequest request) {

		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<Alarm> list = alarmService.listAlarm(page, limit);
		PageUtils pageUtil = new PageUtils(list,
				alarmService.selectAlarmCount(), limit, page);
		return Layui.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

}
