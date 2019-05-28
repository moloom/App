package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	// 创建三个全局变量，在登录成功时给他们赋值，减少代码重复
	private List<Data_appStatus> appStatus;

	private List<Data_flatForm> flatforms;

	private List<App_category> categories;// 一级分类列表

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
			session.setAttribute("devUserSession", dev_user2);
			// 登录成功后查询以下三个，并赋给全局变量，减少代码重复
			this.appStatus = this.dev_userService.findAllOfAppStatus();

			this.flatforms = this.dev_userService.findAllOfFlatForm();

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

	/**
	 * 转到信息查询界面，并查询出所有的app信息，
	 * 
	 * @param model
	 * @return
	 */
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
		map.put("flatformId", "");
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
		 * 
		 * List<Data_appStatus> appStatus =
		 * this.dev_userService.findAllOfAppStatus();
		 */
		model.addAttribute("statusList", this.appStatus);
		/**
		 * 查询所属平台,DATA_FLATFORM ok
		 * 
		 * List<Data_flatForm> flatForms =
		 * this.dev_userService.findAllOfFlatForm();
		 */
		model.addAttribute("flatFormList", this.flatforms);

		/**
		 * 一级分类
		 * 
		 * List<App_category> categories =
		 * this.dev_userService.findAllOfCategoryLevel1();
		 */
		model.addAttribute("categoryLevel1List", this.categories);

		return "developer/appinfolist";
	}

	/**
	 * 根据前台的查询条件查询，分页功能，名称映射都有
	 * 
	 * @param pageIndex
	 * @param app_infos
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAppList.html")
	public String findAppList(Integer pageIndex, App_info app_infos, Model model) {
		/**
		 * 查询app信息列表 ok
		 */
		System.out.println("\n\n\n\n\n" + app_infos);
		Map<String, String> map = new HashMap<String, String>();
		Page page = new Page();
		// 根据前台传过来的要显示第几页 计算出查询时要用到的pageMax，和pageMin
		int pageMax = 10 * pageIndex;
		int pageMin = pageMax - 10;
		page.setCurrentPageNo(1);
		page.setTotalPageCount(0);
		map.put("pageMax", Integer.toString(pageMax));
		map.put("pageMin", Integer.toString(pageMin));
		/**
		 * 判断其是否为0和null,不为0才转为字符串，
		 * 
		 * 判断 softwareName时不能加 app_infos.getSoftwareName() !=
		 * ""这个条件，否则数据库拼接字符串会报错
		 */
		if (app_infos.getSoftwareName() != null) {
			map.put("softwareName", app_infos.getSoftwareName());
		}
		if (app_infos.getStatus() != null && app_infos.getStatus() != 0) {
			map.put("status", Integer.toString(app_infos.getStatus()));
		}
		if (app_infos.getFlatformId() != null && app_infos.getFlatformId() != 0) {
			map.put("flatformId", Integer.toString(app_infos.getFlatformId()));
		}
		if (app_infos.getCategoryLevel1() != null && app_infos.getCategoryLevel1() != 0) {
			map.put("categoryLevel1", Integer.toString(app_infos.getCategoryLevel1()));
		}
		if (app_infos.getCategoryLevel2() != null && app_infos.getCategoryLevel2() != 0) {
			map.put("categoryLevel2", Integer.toString(app_infos.getCategoryLevel2()));
		}
		if (app_infos.getCategoryLevel3() != null && app_infos.getCategoryLevel3() != 0) {
			map.put("categoryLevel3", Integer.toString(app_infos.getCategoryLevel3()));
		}
		List<App_info> app_infoList = this.dev_userService.getApp_infoListByMap(map);
		/**
		 * 根据条件查询到的总记录条数
		 */
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
		model.addAttribute("flatFormList", this.flatforms);
		/**
		 * 一级分类
		 */
		model.addAttribute("categoryLevel1List", this.categories);

		return "developer/appinfolist";
	}

	/**
	 * 转到添加app基础信息界面
	 * 
	 * @param app_info
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAppinfoAdd.html")
	public String toAppinfoAdd(Model model) {
		// System.out.println("\napp_info：" + app_info);

		return "developer/appinfoadd";
	}

	/**
	 * 把前台传过来的新增app基础信息保存到数据库
	 * 
	 * 把service层mapper层写好就完事了
	 * 
	 * @param app_info
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/appinfoSave.html", method = RequestMethod.POST)
	public String appinfoSave(App_info app_info, HttpServletRequest request,
			@RequestParam(value = "attachs") MultipartFile attachs, Model model) {
		logger.debug("----------------logger----------------appinfoSave-----");
		System.out.println("\napp_info：" + app_info);
		String fileUploadError = null;// 上传错误信息
		boolean flag = true;// 判断条件// 上传文件的位置
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");

		// START 循环遍历上传的文件

		MultipartFile attach = attachs;
		// 判断文件是否为空
		if (!attach.isEmpty()) {
			// 文件上传失败
			if (attachs == null) {
				fileUploadError = "uploadFileError";

			}
			// 源文件名
			String oldFileName = attach.getOriginalFilename();
			// 源文件的后缀
			String prefix = FilenameUtils.getExtension(oldFileName);
			// 判断文件大小，不得超过500KB
			int filesize = 2000000;
			if (attach.getSize() > filesize) {
				request.setAttribute(fileUploadError, "*上传文件大小不得超过2048KB");
				flag = false;
				return "useradd";
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// 判断文件的格式
				// 改造文件名称，避免文件名重复
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(0, 1000000) + "_Personal.jpg";
				// 生成目标文件
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存上传文件
				try {
					attach.transferTo(targetFile);
				} catch (IOException e) {
					e.printStackTrace();
					request.setAttribute(fileUploadError, "*上传失败!");
					flag = false;
					return "developer/appinfoadd";
				}
				// 文件路径保存到app_info对象里
				app_info.setLogoLocPath(path + File.separator + fileName);
				// 第二个文件的路径
				// if (i == 1) {
				// app_info.setLogoPicPath(path + File.separator + fileName);
				// }

			} else {
				request.setAttribute("fileUploadError", "*上传图片格式不正确!");
				flag = false;
				return "developer/appinfoadd";
			}
		}

		// END 循环遍历上传的文件
		// 如果上传文件报错，就不保存
		if (flag) {
			System.out.println("\n\n\n\n\n\n" + app_info);
		}

		logger.debug("----------------logger-End---------------appinfoSave-----");
		return this.flatformAppList(model);
	}

	/**
	 * =================分割线=================================================================================
	 * =================分割线=================================================================================
	 * =================分割线=================================================================================
	 * 
	 * ajax动态获取
	 */

	/**
	 * 
	 * 处理ajax获取CategoryLevel2 和3的请求， ok
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
	 * 处理ajax动态加载所属平台列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findAppFlatform", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String findAppFlatform() {
		return JSONArray.toJSONString(this.flatforms);
	}

	/**
	 * 处理ajax动态加载一级分类列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findCategoryLevel1", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String findCategoryLevel1() {
		return JSONArray.toJSONString(this.categories);
	}

	/**
	 * 处理ajax动态验证--APKName是否已存在
	 * 
	 * @return
	 */
	@RequestMapping(value = "/verifyAPKName", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String verifyAPKName(String APKName) {
		HashMap<String, String> result = new HashMap<String, String>();
		System.out.println(APKName);
		// 参数APKName为空，错误提示
		if (APKName == null || APKName == "") {
			result.put("APKName", "empty");
			return JSONArray.toJSONString(result);
		}
		App_info app_info = this.dev_userService.verifyAPKName(APKName);
		// System.out.println(app_info);
		// 账号不可用，错误提示
		if (app_info != null) {
			result.put("APKName", "exist");
			return JSONArray.toJSONString(result);
		} else {// 账号可用，正确提示
			result.put("APKName", "noexist");
			return JSONArray.toJSONString(result);
		}

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
