package com.small.cell.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.small.cell.server.util.TomcatUtil;

@Controller
@RequestMapping("/sys")
public class SysController {
	@RequestMapping("/status")
	public String viewSmtp(HttpServletRequest request, Model model)
			throws IOException, InterruptedException {
		model.addAttribute("status", TomcatUtil.getTomcatStatus());
		return "sys/status";
	}

}
