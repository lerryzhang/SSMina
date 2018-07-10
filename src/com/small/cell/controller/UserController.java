package com.small.cell.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import com.small.cell.server.configure.SpringWebSocketHandler;
import com.small.cell.server.pojo.Return;

import com.small.cell.server.pojo.User;
import com.small.cell.server.service.UserService;

import com.small.cell.server.util.Layui;
import com.small.cell.server.util.PageUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Bean
	// 这个注解会从Spring容器拿出Bean
	public SpringWebSocketHandler infoHandler() {
		return new SpringWebSocketHandler();
	}

	@Resource
	private UserService userService;

	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.getUser(username);
		if (user == null)
			return Return.FAIL;
		if (password.equals(user.getPassword())) {
			HttpSession session = request.getSession(false);
			session.setAttribute("SESSION_USERNAME", username);
			user.setLastip(request.getRemoteHost());
			user.setLasttime(format.format(new Date()));
			userService.updateUser(user);
			return Return.SUCCESS;
		} else {
			return Return.FAIL;
		}

	}

	@RequestMapping("/list")
	@ResponseBody
	public Layui list() {
		List<User> list = userService.listUser();
		PageUtils pageUtil = new PageUtils(list, list.size(), 10, 1);
		return Layui.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

	@RequestMapping("/index")
	public String index() {
		return "user/user";
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, User user) {
		return "user/useradd";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request, User user) {
		userService.saveUser(user);
		return Return.SUCCESS;
	}
}
