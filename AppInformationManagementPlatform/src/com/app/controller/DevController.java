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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.app.pojo.Page;
import com.alibaba.fastjson.JSONArray;
import com.app.pojo.App_category;
import com.app.pojo.App_info;
import com.app.pojo.Data_appStatus;
import com.app.pojo.Data_flatForm;
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

	private List<Data_appStatus> appStatus;

	private List<Data_flatForm> flatForms;

	private List<App_category> categories;

	// private Pages pages = new Pages();

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
			// 登录成功后查询以下三个，并赋给全局变量，减少代码重复
			this.appStatus = this.dev_userService.findAllOfAppStatus();

			this.flatForms = this.dev_userService.findAllOfFlatForm();

			this.categories = this.dev_userService.findAllOfCategoryLevel1();
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
		/**
		 * 查询app信息列表 ok
		 */
		Map<String, String> map = new HashMap<String, String>();
		Page page = new Page();
		int pageMax = 10;
		int pageMin = 0;
		page.setCurrentPageNo(1);
		page.setTotalPageCount(0);
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
		model.addAttribute("pages", page);
		model.addAttribute("appInfoList", app_infoList);
		/**
		 * 查询app状态列表，DATA_APPSTATUS ok
		 */
		List<Data_appStatus> appStatus = this.dev_userService.findAllOfAppStatus();
		model.addAttribute("statusList", appStatus);
		/**
		 * 查询所属平台,DATA_FLATFORM ok
		 */
		List<Data_flatForm> flatForms = this.dev_userService.findAllOfFlatForm();
		model.addAttribute("flatFormList", flatForms);
		/**
		 * 一级分类
		 */
		List<App_category> categories = this.dev_userService.findAllOfCategoryLevel1();
		model.addAttribute("categoryLevel1List", categories);

		return "developer/appinfolist";
	}

	@RequestMapping("/findAppList.html")
	public String findAppList(Integer pageIndex, App_info app_infos, Model model) {
		/**
		 * 查询app信息列表 ok
		 */
		System.out.println("\n\n\n\n\n" + app_infos);
		Map<String, String> map = new HashMap<String, String>();
		Page page = new Page();
		int pageMax = 10;
		int pageMin = 0;
		page.setCurrentPageNo(1);
		page.setTotalPageCount(0);
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
		model.addAttribute("pages", page);
		model.addAttribute("appInfoList", app_infoList);
		/**
		 * 查询app状态列表，DATA_APPSTATUS ok
		 */
		model.addAttribute("statusList", this.appStatus);
		/**
		 * 查询所属平台,DATA_FLATFORM ok
		 */
		model.addAttribute("flatFormList", this.flatForms);
		/**
		 * 一级分类
		 */
		model.addAttribute("categoryLevel1List", this.categories);

		return "developer/appinfolist";
	}

	/**
	 * 处理json获取CategoryLevel2 和3的请求，
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/findAllOfCategoryLevels", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String findAllOfCategoryLevels(Integer id) {
		System.out.println("\nCategoryLevel1：" + id);
		// List<App_category> app_categories =
		// this.dev_userService.findOfCategoryLevels(id);
		return JSONArray.toJSONString(this.dev_userService.findOfCategoryLevels(id));
	}

	/**
	 * 处理全局异常 在修改代码时注释掉
	 * 
	 * @param e
	 * @return
	 */
	// @ExceptionHandler(value = { RuntimeException.class })
	// public String ex(RuntimeException e) {
	// logger.debug("========logger=============" + e);
	// logger.debug("========End=======");
	// return "error";
	//

}
