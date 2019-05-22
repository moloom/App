package com.app.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理controller
 * 
 * 映射路径以 .html为后缀的，过滤器会进行拦截，并验证userSession是否为空！！！
 * 
 * @author moxy
 *
 */
@Controller
@RequestMapping(value = "manager")
public class ManagerController {
	/**
	 * 输出日志
	 */
	private Logger logger = Logger.getLogger(ManagerController.class);

	/**
	 * 转到登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String toLogin() {

		return "managerLogin";
	}
}
