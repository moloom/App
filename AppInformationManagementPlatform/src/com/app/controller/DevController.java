package com.app.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.app.pojo.Page;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.app.pojo.App_category;
import com.app.pojo.App_info;
import com.app.pojo.App_version;
import com.app.pojo.Data_appStatus;
import com.app.pojo.Data_flatForm;
import com.app.pojo.Dev_user;
import com.app.service.Dev_userService;
import com.app.utils.MyFile;
import Decoder.BASE64Encoder;
import oracle.net.aso.a;

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

	private List<App_version> app_versions;

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
	 * 根据前台的查询条件查询，分页功能，名称映射都有 转到信息查询界面，并查询出所有的app信息，
	 * 查询时，先查出一级分类的名称，再返回时，循环遍历list
	 * 
	 * 在forech循环里，拿到当前的app_info对象里的l2，l3去数据库里查出它们的名称并赋给当前app_info对象的l2，l3相应的name属性里
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
		System.out.println("\n\n\n pageIndex=" + pageIndex + "\n\n" + app_infos);
		Map<String, String> map = new HashMap<String, String>();
		Page page = new Page();
		if (pageIndex == null) {
			pageIndex = 1;
		}

		// 根据前台传过来的要显示第几页 计算出查询时要用到的pageMax，和pageMin
		int pageMax = 5 * pageIndex;
		int pageMin = pageMax - 5;
		page.setCurrentPageNo(pageIndex);
		page.setTotalPageCount(0);
		map.put("pageMax", Integer.toString(pageMax));
		map.put("pageMin", Integer.toString(pageMin));
		/**
		 * 判断其是否为0和null,不为0才转为字符串，
		 * 
		 * 判断 softwareName时不能加 app_infos.getSoftwareName() !=
		 * ""这个条件，否则数据库拼接字符串会报错
		 */
		if (app_infos != null) {
			if (app_infos.getSoftwareName() != null) {
				map.put("softwareName", app_infos.getSoftwareName());
			} else {
				map.put("softwareName", "");
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

		} else {
			map.put("softwareName", "");
		}

		System.out.println("\n findAppList map:" + map.toString());
		List<App_info> app_infoList = this.dev_userService.getApp_infoListByMap(map);

		/**
		 * 根据条件查询到的总记录条数
		 */
		page.setTotalCount(this.dev_userService.countByMap(map));

		/**
		 * 遍历循环查出来的数据，because，以下几个字段还没查出来
		 * 
		 * must在查出来的数据的基础上根据相应的字段去查，再赋给当前遍历对象相应的属性
		 * 
		 * 判空，防止在增加app_info时数据的空值给报错
		 */
		for (App_info app_info : app_infoList) {

			app_info.setCategoryLevel1Name(this.dev_userService.getCategoryLevel1Name(app_info.getCategoryLevel1()));
			app_info.setCategoryLevel2Name(this.dev_userService.getCategoryLevel1Name(app_info.getCategoryLevel2()));
			app_info.setCategoryLevel3Name(this.dev_userService.getCategoryLevel1Name(app_info.getCategoryLevel3()));
			if (app_info.getFlatformId() != null) {
				app_info.setFlatformName(this.dev_userService.getFlatformName(app_info.getFlatformId()));
			}
			if (app_info.getStatus() != null) {
				app_info.setStatusName(this.dev_userService.getAppStatusName(app_info.getStatus()));
			}
			if (app_info.getVersionId() != null) {
				app_info.setVersionNo(this.dev_userService.getAppVersionNo(app_info.getVersionId()));
			}
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
	 * 转到添加app基础信息界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toAppinfoAdd.html")
	public String toAppinfoAdd() {
		// System.out.println("\napp_info：" + app_info);

		return "developer/appinfoadd";
	}

	/**
	 * 转到修改app基础信息界面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/appinfomodify.html")
	public String toAppinfoModify(Integer id, Model model) {
		// System.out.println("\napp_info：" + app_info);
		App_info app_info = this.dev_userService.getApp_infoById(id);
		model.addAttribute("appInfo", app_info);
		return "developer/appinfomodify";
	}

	/**
	 * 转到添加app版本信息界面
	 * 
	 * @param app_info
	 *            主要是为了方便传值才用对象，不为空的值只有id
	 * @return
	 */
	@RequestMapping(value = "/toAddAppVersion.html")
	public String toAddAppVersion(App_info app_info, Model model) {
		System.out.println("\n toAddAppVersion:" + app_info.getId());

		List<App_version> app_versions = this.dev_userService.findApp_versionAndAppName(app_info.getId());
		this.app_versions = app_versions;
		for (App_version app_version : app_versions) {
			System.out.println(app_version);
		}
		model.addAttribute("app_info", app_info);
		model.addAttribute("appVersionList", app_versions);
		return "developer/appversionadd";
	}

	/**
	 * 转到修改app版本信息界面
	 * 
	 * 查询当前最新版本信息并传到前端
	 * 
	 * @param vid
	 *            versionid
	 * @param aid
	 *            appinfoid
	 * @return
	 */
	@RequestMapping(value = "/toModifyAppVersion.html")
	public String toModifyAppVersion(Integer vid, Integer aid, Model model) {
		System.out.println("\n toModifyAppVersion:" + vid + "\t" + aid);
		List<App_version> app_versions = this.dev_userService.findApp_versionAndAppName(aid);
		this.app_versions = app_versions;
		// 获取最新版本信息
		for (App_version app_version : app_versions) {
			model.addAttribute("appVersion", app_version);
			System.out.println(app_version);
			break;
		}
		model.addAttribute("appVersionList", this.app_versions);
		return "developer/appversionmodify";

	}

	/**
	 * 转到查看app详细信息界面
	 * 
	 * @param id
	 *            当前App_info的id
	 * @return
	 */
	@RequestMapping(value = "/toAppView.html")
	public String toAppView(Integer id, Model model) {
		System.out.println("\n toAppView:" + id);
		List<App_version> app_versions = null;
		App_info app_info = this.dev_userService.getApp_infoById(id);
		app_info.setCategoryLevel1Name(this.dev_userService.getCategoryLevel1Name(app_info.getCategoryLevel1()));
		app_info.setCategoryLevel2Name(this.dev_userService.getCategoryLevel1Name(app_info.getCategoryLevel2()));
		app_info.setCategoryLevel3Name(this.dev_userService.getCategoryLevel1Name(app_info.getCategoryLevel3()));
		if (app_info.getFlatformId() != null) {
			app_info.setFlatformName(this.dev_userService.getFlatformName(app_info.getFlatformId()));
		}
		if (app_info.getStatus() != null) {
			app_info.setStatusName(this.dev_userService.getAppStatusName(app_info.getStatus()));
		}
		if (app_info.getVersionId() != null) {
			app_info.setVersionNo(this.dev_userService.getAppVersionNo(app_info.getVersionId()));
			app_versions = this.dev_userService.findApp_versionAndAppName(app_info.getId());
			for (App_version app_version : app_versions) {
				System.out.println(app_version);
			}

			model.addAttribute("appVersionList", app_versions);
		}
		System.out.println("\n" + app_info);

		model.addAttribute("appInfo", app_info);
		return "developer/appinfoview";
	}

	/**
	 * 修改app基础信息
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/updataApp_info.html", method = RequestMethod.POST)
	public String updataApp_info(App_info app_info, Model model) {
		logger.debug("----------------logger----------------updataApp_info-----");
		System.out.println("\napp_info：" + app_info);
		int count = this.dev_userService.updataApp_info(app_info);

		logger.debug("----------------logger-End---------------updataApp_info-----count=" + count);
		return "redirect:findAppList.html";
	}

	/**
	 * 修改app版本信息
	 * 
	 * 修改之后要修改app_info里的 软件大小!!!!
	 * 
	 * @param app_version
	 * @param attach
	 *            上传的文件名称
	 * @param model
	 * @param request
	 * @return 如果不成功则返回到toApp_versionModify方法
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/updataApp_version.html", method = RequestMethod.POST)
	public String updataApp_version(App_version app_version, Model model,
			@RequestParam(value = "attach") MultipartFile attach, HttpServletRequest request) {
		logger.debug("----------------logger----------------updataApp_version-----");
		System.out.println("\napp_version：" + app_version);
		App_info app_info = new App_info();// 修改软件大小用的
		String fileUploadError = null;// 上传错误信息
		int count = 0;// 返回的记录条数
		String prefix = "apk";// 文件后缀
		String path = request.getSession().getServletContext().getRealPath("statics");
		path = path.substring(0, path.indexOf(File.separator + "webapps")) + File.separator + "uplocalfiles";
		// 保存文件
		MyFile myFile = new MyFile(attach, app_version.getVersionNo(), path, prefix);
		System.out.println(myFile.getFileName());
		/**
		 * if 文件为空则更新其他的信息
		 * 
		 * else if myFil不为空，则保存文件并把文件名文件路径更新下
		 */
		System.out.println(attach);
		if (attach.isEmpty()) {
			app_version.setAPKLocPath(null);
			app_version.setAPKFileName(null);
			System.out.println("\n 2 app_version:" + app_version);
			count = this.dev_userService.updataApp_version(app_version);
			// 成功修改app_version之后修改app_info里的软件大小
			app_info.setId(app_version.getAppId());
			app_info.setSoftwareSize(app_version.getVersionSize());
			app_info.setModifyBy(app_version.getModifyBy());
			this.dev_userService.updataApp_info(app_info);
			logger.debug("----------------logger-End---------------updataApp_version-----count=" + count);
			return this.findAppList(null, null, model);
		} else if (myFile.getFileName() != null) {
			app_version.setAPKLocPath(myFile.getPath());
			app_version.setAPKFileName(myFile.getFileName());
			System.out.println("\n 1 app_version:" + app_version);
			count = this.dev_userService.updataApp_version(app_version);
			// 成功修改app_version之后修改app_info里的软件大小
			app_info.setId(app_version.getAppId());
			app_info.setSoftwareSize(app_version.getVersionSize());
			app_info.setModifyBy(app_version.getModifyBy());
			this.dev_userService.updataApp_info(app_info);
			// 查询当前添加的记录的id，并给app_info更新versionId
			logger.debug("----------------logger-End---------------updataApp_version-----count=" + count);
			return "redirect:findAppList.html";
		} else {
			model.addAttribute("fileUploadError", fileUploadError);
			logger.debug("----------------logger-End---------------updataApp_version-----");
			return this.toModifyAppVersion(app_version.getId(), app_version.getAppId(), model);
		}

	}

	/**
	 * 还需修改下存放文件的文件路径，放到服务里目录下的一个专门文件夹里 增加app版本信息 ok
	 * 
	 * 增加app版本
	 * 
	 * 增加的同时，要修改app_info里的版本号 versionId 和软件大小 softwareSize
	 * 
	 * @param app_version
	 * @param model
	 * @return
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/saveAppVersion.html", method = RequestMethod.POST)
	public String saveAppVersion(App_version app_version, Model model, HttpServletRequest request,
			@RequestParam(value = "a_downloadLink") MultipartFile a_downloadLink) {
		logger.debug("----------------logger----------------saveAppVersion-----");
		// System.out.println("\n app_version：" + app_version);
		int count = 0;// 添加后返回的记录条数
		App_info app_info = new App_info();
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		path = path.substring(0, path.indexOf(File.separator + "webapps")) + File.separator + "uplocalfiles";
		System.out.println("\n" + path);
		// 保存文件
		MyFile myFile = new MyFile(a_downloadLink, app_version.getVersionNo(), path, "apk");

		if (myFile.getFileName() != null) {
			// 防止添加时id为空 报错
			app_version.setId(0);

			// 新文件名保存到app_info对象里
			app_version.setAPKFileName(myFile.getFileName());
			app_version.setAPKLocPath(myFile.getPath());
			System.out.println("\n Save app_version:" + app_version);

			count = this.dev_userService.saveAppVersion(app_version);
			// 查询当前添加的记录的id，并给app_info更新versionId和更新softwareSize
			app_info.setVersionId(this.dev_userService.findApp_versionId(app_version));
			app_info.setId(app_version.getAppId());
			app_info.setSoftwareSize(app_version.getVersionSize());
			this.dev_userService.updataApp_infoVersionId(app_info);
			logger.debug("----------------logger-End---------------saveAppVersion-----" + count);
			return "redirect:findAppList.html";
		} else {
			logger.debug("----------------logger-End---------------saveAppVersion-----");
			return this.toAddAppVersion(app_info, model);
		}

	}

	/**
	 * 把前台传过来的新增app基础信息保存到数据库
	 * 
	 * 把service层mapper层写好就完事了 ok
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
		int count = 0;// 添加后返回的记录条数
		// 获取文件名后缀,图片格式很多，所以不判定具体为啥格式
		String prefix = FilenameUtils.getExtension(attachs.getOriginalFilename());
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		path = path.substring(0, path.indexOf(File.separator + "webapps")) + File.separator + "uplocalfiles";
		// START 循环遍历上传的文件

		// 保存文件
		MyFile myFile = new MyFile(attachs, app_info.getSoftwareName(), path, prefix);
		if (myFile.getFileName() != null) {
			app_info.setLogoLocPath(myFile.getPath());
			System.out.println("\n updata:" + app_info);
			// 随便给个值，添加数据时触发器会自动替换
			app_info.setId(0);
			app_info.setDevId(app_info.getCreatedBy());
			count = this.dev_userService.addApp_info(app_info);
			logger.debug("----------------logger-End---------------appinfoSave-----" + count);
			return "redirect:findAppList.html";
		} else {
			System.out.println("添加失败");
			logger.debug("----------------logger-End---------------appinfoSave-----");
			return this.toAppinfoAdd();
		}

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
	 * 先根据id查出那条数据，并删除那条数据所指的apk文件，再删除那条数据的apk链接
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delFile", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String delFile(Integer id) {
		logger.debug("----------------logger----------------delFile-----");
		HashMap<String, String> result = new HashMap<String, String>();
		App_version app_version = this.dev_userService.findApp_versionById(id);
		String apkPath = app_version.getAPKLocPath();
		// 删除文件
		File file = new File(apkPath);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				result.put("result", "success");
			} else {
				result.put("result", "failed");
			}
		} else {
			result.put("result", "empty");
		}
		app_version.setAPKLocPath("");
		app_version.setAPKFileName("");
		int count = this.dev_userService.updataApp_version(app_version);
		logger.debug("----------------logger-End---------------delFile-----" + count);
		return JSONArray.toJSONString(result);
	}

	/**
	 * 删除app信息并删除其所有的版本,并删除文件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delApp_info", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String delApp_info(Integer id) {
		logger.debug("----------------logger----------------delApp_info-----");
		System.out.println(id);
		HashMap<String, String> delresult = new HashMap<String, String>();

		App_info app_info1 = this.dev_userService.getApp_infoById(id);
		// 删除app_info的图标文件
		File file = new File(app_info1.getLogoLocPath());
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			file.delete();
		}
		if (app_info1.getVersionId() != null) {
			List<App_version> app_versions = this.dev_userService.findApp_versionAndAppName(app_info1.getId());
			for (App_version app_version : app_versions) {
				// 删除文件
				File file2 = new File(app_version.getAPKLocPath());
				// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
				if (file2.exists() && file2.isFile()) {
					file2.delete();
				}
			}
		}
		int countOfApp_info = this.dev_userService.delApp_info(id);
		int countOfApp_version = this.dev_userService.delApp_versionByAppId(id);
		if (countOfApp_info == 0) {
			delresult.putIfAbsent("delresult", "false");
		} else if (countOfApp_info != 0) {
			delresult.putIfAbsent("delresult", "true");
		}

		logger.debug("----------------logger-End---------------delApp_info-----countOfApp_info:" + countOfApp_info
				+ "\n" + "countOfApp_version:" + countOfApp_version);
		return JSONArray.toJSONString(delresult);
	}

	/**
	 * 从后端返回字符流传给前端 还需判断路径是否存在
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/img", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String img(Integer id) throws Exception {
		App_info app_info = this.dev_userService.getApp_infoById(id);
		File file = new File(app_info.getLogoLocPath());
		if (app_info.getLogoLocPath() != null) {
			String imgPath = app_info.getLogoLocPath();
			BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
			BufferedImage img = new BufferedImage(300, 150, BufferedImage.TYPE_INT_RGB);
			img = image;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", outputStream);
			BASE64Encoder encoder = new BASE64Encoder();
			String base64Img = encoder.encode(outputStream.toByteArray());
			// 返回字符流数据，所以不用JSONArray,,,注！
			return JSON.toJSONString(base64Img);
		}

		return "";
	}

	/**
	 * 拿到app_info Id先查出那条记录，再根据此记录的status来执行不同的操作
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}/sale", produces = "text/html;charset=UTF-8", method = RequestMethod.PUT)
	@ResponseBody
	public String sale(@PathVariable Integer id) throws Exception {
		logger.debug("----------------logger---------------sale-----");
		// 初始化需要的变量
		HashMap<String, String> resultMsg = new HashMap<String, String>();
		int count = 0;
		App_info app_info = this.dev_userService.getApp_infoById(id);

		// status为4是已上架，执行下架操作,2,5为未上架，故 执行上架操作
		if (app_info.getStatus() == 4) {
			// 5为已下架
			app_info.setStatus(5);
			count = this.dev_userService.updataApp_info(app_info);

		} else if (app_info.getStatus() == 2 || app_info.getStatus() == 5) {
			// 4为已上架
			app_info.setStatus(4);
			count = this.dev_userService.updataApp_info(app_info);
		}
		if (count == 1) {
			resultMsg.putIfAbsent("resultMsg", "success");
			resultMsg.putIfAbsent("errorCode", "0");
		} else if (count == 0) {
			resultMsg.putIfAbsent("resultMsg", "failed");
		} else {
			resultMsg.putIfAbsent("errorCode", "exception000001");
		}
		{

		}
		logger.debug("----------------logger-End---------------sale-----count=" + count);
		return JSONArray.toJSONString(resultMsg);
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
