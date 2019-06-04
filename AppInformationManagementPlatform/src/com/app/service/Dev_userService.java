package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.pojo.App_category;
import com.app.pojo.App_info;
import com.app.pojo.App_version;
import com.app.pojo.Data_appStatus;
import com.app.pojo.Data_flatForm;
import com.app.pojo.Dev_user;

public interface Dev_userService {

	/**
	 * 登录时，验证数据库里账号和密码是否正确
	 * 
	 * @param APKName
	 * @return
	 */
	public Dev_user findDev_user(Dev_user dev_user);

	/**
	 * 查询所有的app状态列表
	 * 
	 * @return
	 */
	public List<Data_appStatus> findAllOfAppStatus();

	/**
	 * 查询所有的所属平台
	 * 
	 * @return
	 */
	public List<Data_flatForm> findAllOfFlatForm();

	/**
	 * 查询categoryLevel1
	 * 
	 * @return
	 */
	public List<App_category> findAllOfCategoryLevel1();

	/**
	 * 查询categoryLevel 2 和 3
	 * 
	 * @return
	 */
	public List<App_category> findOfCategoryLevels(Integer id);

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param map
	 * @return
	 */
	public int countByMap(Map<String, String> map);

	/**
	 * 查询categoryLevel1Name
	 * 
	 * @return
	 */
	public String getCategoryLevel1Name(Integer categoryLevel1);

	// public String getCategoryLevel2Name(Integer categoryLevel2);
	// public String getCategoryLevel3Name(Integer categoryLevel3);

	/**
	 * 查询flatformName
	 * 
	 * @return
	 */
	public String getFlatformName(Integer flatformId);

	/**
	 * 查询statusName
	 * 
	 * @return
	 */
	public String getAppStatusName(Integer statusId);

	/**
	 * 查询VersionNo
	 * 
	 * @return
	 */
	public String getAppVersionNo(Integer versionId);

	/**
	 * 根据条件查询Dev_user
	 * 
	 * @param map
	 * @return
	 */
	public List<App_info> getApp_infoListByMap(Map<String, String> map);

	/**
	 * 添加app基础信息
	 * 
	 * @param dev_user
	 * @return
	 */
	public int addApp_info(App_info app_info);

	/**
	 * 修改app信息
	 * 
	 * @param app_info
	 * @return
	 */
	public int updataApp_info(App_info app_info);

	/**
	 * 修改app信息
	 * 
	 * @param app_info
	 * @return
	 */
	public int updataApp_version(App_version app_version);

	/**
	 * 根据id查询app信息
	 * 
	 * 查询App_info
	 * 
	 * @param id
	 * @return
	 */
	public App_info getApp_infoById(Integer id);

	/**
	 * 添加app版本信息
	 * 
	 * @param app_version
	 * @return
	 */
	public int saveAppVersion(App_version app_version);

	/**
	 * 在修改app_info的versionId之前，先查出新增的那条记录的id
	 * 
	 * @param app_version
	 * @return
	 */
	public int findApp_versionId(App_version app_version);

	/**
	 * 查询app版本信息,条件id
	 * 
	 * @param app_version
	 * @return
	 */
	public App_version findApp_versionById(Integer id);

	/**
	 * 在添加app版本信息的同时修改app_info里的versionId
	 * 
	 * @param app_info
	 * @return
	 */
	public int updataApp_infoVersionId(App_info app_info);

	/**
	 * 转到app版本添加界面or修改界面时查询出历史修改信息
	 * 
	 * @param app_version
	 * @return
	 */
	public List<App_version> findApp_versionAndAppName(Integer id);

	/**
	 * 根据id查询多条app信息
	 * 
	 * @param id
	 * @return
	 */
	public List<App_info> getApp_infoListById(Integer id);

	/**
	 * Ajax异步验证 把前端输入的APKName拿去查询数据库里是否存在
	 * 
	 * @param dev_userName
	 * @return
	 */
	public App_info verifyAPKName(String APKName);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	public int deleteDev_user(Integer id);

	/**
	 * 删除app信息
	 * 
	 * @param id
	 * @return
	 */
	public int delApp_info(Integer id);

	/**
	 * 删除app版本信息
	 * 
	 * @param id
	 * @return
	 */
	public int delApp_versionByAppId(Integer id);

}
