package com.small.cell.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.small.cell.server.configure.SpringWebSocketHandler;
import com.small.cell.server.pojo.Return;

@Controller
@RequestMapping("/user")
public class UserController {

	@Bean
	// 这个注解会从Spring容器拿出Bean
	public SpringWebSocketHandler infoHandler() {
		return new SpringWebSocketHandler();
	}

	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ("admin".equals(username) && "admin".equals(password)) {
			HttpSession session = request.getSession(false);
			session.setAttribute("SESSION_USERNAME", username);
			return Return.SUCCESS;
		} else {
			return Return.FAIL;
		}

	}

}
