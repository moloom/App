package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.app.pojo.Page;
import com.app.pojo.App_info;
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

//	private Pages pages = new Pages();

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

		// return "developer/main";
		Dev_user dev_user2 = this.dev_userService.findDev_user(dev_user);
		if (dev_user2 != null) {
			session.setAttribute("devUserSession", dev_user);
			return "developer/main";
		} else {
			model.addAttribute("error", "您输入的账号密码错误请重新输入");
		}
		return "forward:/login";
	}

	/**
	 * 退出系统，删除session，跳转到index界面
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("userSession");
		// redirect转发，跳转到index.jsp界面
		return "forward:/";
	}

	@RequestMapping("/flatFormAppList.html")
	public String flatformAppList(Model model) {
		Map<String, String> map = new HashMap<String, String>();
		Page page = new Page();
		int pageMax = 10;
		int pageMin = 0;
		page.setCurrentPageNo(1);
		page.setTotalPageCount(0);
		;
		map.put("pageMax", Integer.toString(pageMax));
		map.put("pageMin", Integer.toString(pageMin));
		map.put("softwareName", "");
		map.put("status", "");
		map.put("flatfromId", "");
		map.put("categoryLevel1", "");
		map.put("categoryLevel2", "");
		map.put("categoryLevel3", "");

		List<App_info> app_infoList = this.dev_userService.getApp_infoListByMap(map);
		page.setTotalCount(this.dev_userService.countByMap(map));
		for (App_info app_info : app_infoList) {
			System.out.println("\n" + app_info);
		}
		System.out.println(page);
		 model.addAttribute("pages", page);

		model.addAttribute("appInfoList", app_infoList);
		return "developer/appinfolist";
	}

}
