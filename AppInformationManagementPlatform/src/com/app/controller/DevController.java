package com.app.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.pojo.Dev_user;
import com.app.service.Dev_userService;

/**
 * 开发者controller
 * 
 * 映射路径以 .html为后缀的，过滤器会进行拦截，并验证userSession是否为空！！！
 * 
 * @author moxy
 */
@Controller
@RequestMapping(value = "/dev")
public class DevController {
	/**
	 * 输出日志
	 */
	private Logger logger = Logger.getLogger(DevController.class);

	@Autowired
	private Dev_userService dev_userService;

	/**
	 * 转到登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String toLogin() {
		return "devLogin";
	}

	/**
	 * 拿到前端提交的账号和密码到数据库里查询，为空，则提示密码或账号输入错误，不为空，则把查出来的数据存到session里
	 * 
	 * @param dev_user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String login(Dev_user dev_user, Model model, HttpSession session) {
		System.out.println(dev_user);
		session.setAttribute("devUserSession", dev_user);
		return "developer/main";
		// Dev_user dev_user2 = this.dev_userService.findDev_user(dev_user);
		// if (dev_user2 != null) {
		// session.setAttribute("dev_userSession", dev_user2);
		// // System.out.println(session.getAttribute("user"));
		// // model.addAttribute("user",user2);
		// return "frame";
		// } else {
		// model.addAttribute("error", "您输入的账号密码错误请重新输入");
		// }
		// return "forward:/index.jsp";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("userSession");
		// redirect转发，跳转到index.jsp界面
		return "forward:/";
	}

}
