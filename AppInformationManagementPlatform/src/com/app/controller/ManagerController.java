package com.app.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.pojo.Backend_user;
import com.app.pojo.Dev_user;
import com.app.service.Backend_userService;

/**
 * 后台管理controller
 * 
 * 映射路径以 .html为后缀的，过滤器会进行拦截，并验证userSession是否为空！！！
 * 
 * @author moxy
 *
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {
	/**
	 * 输出日志
	 */
	private Logger logger = Logger.getLogger(ManagerController.class);

	@Autowired
	private Backend_userService backend_userService;

	/**
	 * 转到登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String toLogin() {

		return "backendLogin";
	}

	/**
	 * 拿到前端提交的账号和密码到数据库里查询，为空，则提示密码或账号输入错误 转发到登录界面
	 * 
	 * 不为空，则把查出来的数据存到session里,转到main界面
	 * 
	 * @param dev_user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String login(Backend_user backend_user, Model model, HttpSession session) {
		System.out.println(backend_user);

		// return "developer/main";
		Backend_user backend_user2 = this.backend_userService.findBackend_user(backend_user);
		if (backend_user2 != null) {
			session.setAttribute("userSession", backend_user2);
			return "backend/main";
		} else {
			model.addAttribute("error", "您输入的账号密码错误请重新输入");
		}
		return "forward:/login";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("userSession");
		// redirect转发,跳转到index.jsp界面
		return "forward:/";
	}

}
