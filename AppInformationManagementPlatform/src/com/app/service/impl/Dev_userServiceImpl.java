package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mapper.Dev_userMapper;
import com.app.pojo.App_category;
import com.app.pojo.App_info;
import com.app.pojo.Data_appStatus;
import com.app.pojo.Data_flatForm;
import com.app.pojo.Dev_user;
import com.app.service.Dev_userService;

@Service("dev_userService")
public class Dev_userServiceImpl implements Dev_userService {

	@Autowired
	private Dev_userMapper dev_userMapper;

	public Dev_user findDev_user(Dev_user dev_user) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.findDev_user(dev_user);
	}

	public int countByMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.countByMap(map);
	}

	public List<App_info> getApp_infoListByMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getApp_infoListByMap(map);
	}

	public Dev_user getDev_userById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteDev_user(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updata(Dev_user dev_user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int Dev_userUpdataPwd(Dev_user dev_user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Data_appStatus> findAllOfAppStatus() {
		// TODO Auto-generated method stub
		return this.dev_userMapper.findAllOfAppStatus();
	}

	public List<Data_flatForm> findAllOfFlatForm() {
		// TODO Auto-generated method stub
		return this.dev_userMapper.findAllOfFlatForm();
	}

	public List<App_category> findAllOfCategoryLevel1() {
		// TODO Auto-generated method stub
		return this.dev_userMapper.findAllOfCategoryLevel1();
	}

	public List<App_category> findOfCategoryLevels(Integer id) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.findOfCategoryLevels(id);
	}

	public App_info verifyAPKName(String APKName) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.verifyAPKName(APKName);
	}

	public int addApp_info(App_info app_info) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.addApp_info(app_info);
	}

	public String getCategoryLevel1Name(Integer categoryLevel1) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getCategoryLevel1Name(categoryLevel1);
	}

	public String getFlatformName(Integer flatformId) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getFlatformName(flatformId);
	}

	public String getAppStatusName(Integer statusId) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getAppStatusName(statusId);
	}

	public String getAppVersionNo(Integer versionId) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getAppVersionNo(versionId);
	}

	/*public String getCategoryLevel2Name(Integer categoryLevel2) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getCategoryLevel2Name(categoryLevel2);
	}

	public String getCategoryLevel3Name(Integer categoryLevel3) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getCategoryLevel3Name(categoryLevel3);
	}*/

}
