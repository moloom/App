package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.pojo.App_category;
import com.app.pojo.App_info;
import com.app.pojo.Data_appStatus;
import com.app.pojo.Data_flatForm;
import com.app.pojo.Dev_user;

public interface Dev_userMapper {

	/**
	 * 登录时，验证数据库里账号和密码是否正确
	 * 
	 * @param dev_user
	 * @return
	 */
	public Dev_user findDev_user(Dev_user dev_user);

	/**
	 * 查询所有app状态列表
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
	 * 查询categoryLevel2
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

	/**
	 * 查询categoryLevel2Name
	 * 
	 * @return
	 */
	// public String getCategoryLevel2Name(Integer categoryLevel2);

	/**
	 * 查询categoryLevel3Name
	 * 
	 * @return
	 */
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
	 * 根据id查询用户的详细信息
	 * 
	 * @param id
	 * @return
	 */
	public Dev_user getDev_userById(String id);

	/**
	 * Ajax异步验证 把前端输入的APKName拿去查询数据库里是否存在
	 * 
	 * @param APKName
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
	 * 修改用户信息
	 * 
	 * @param dev_user
	 * @return
	 */
	public int updata(Dev_user dev_user);

	/**
	 * 修改用户密码
	 * 
	 * @param dev_user
	 * @return
	 */
	public int Dev_userUpdataPwd(Dev_user dev_user);
}
