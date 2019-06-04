package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.mapper.Dev_userMapper;
import com.app.pojo.App_category;
import com.app.pojo.App_info;
import com.app.pojo.App_version;
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

	public int deleteDev_user(Integer id) {
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

	@Transactional(rollbackFor = { Exception.class })
	public App_info verifyAPKName(String APKName) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.verifyAPKName(APKName);
	}

	@Transactional(rollbackFor = { Exception.class })
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

	public App_info getApp_infoById(Integer id) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getApp_infoById(id);
	}

	public int updataApp_info(App_info app_info) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.updataApp_info(app_info);
	}

	@Transactional(rollbackFor = { Exception.class })
	public int saveAppVersion(App_version app_version) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.saveAppVersion(app_version);
	}

	@Transactional(rollbackFor = { Exception.class })
	public int updataApp_infoVersionId(App_info app_info) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.updataApp_infoVersionId(app_info);
	}

	public int findApp_versionId(App_version app_version) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.findApp_versionId(app_version);
	}

	public List<App_version> findApp_versionAndAppName(Integer id) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.findApp_versionAndAppName(id);
	}

	public List<App_info> getApp_infoListById(Integer id) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.getApp_infoListById(id);
	}

	@Transactional(rollbackFor = { Exception.class })
	public int updataApp_version(App_version app_version) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.updataApp_version(app_version);
	}

	public App_version findApp_versionById(Integer id) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.findApp_versionById(id);
	}

	@Transactional(rollbackFor = { Exception.class })
	public int delApp_info(Integer id) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.delApp_info(id);
	}

	@Transactional(rollbackFor = { Exception.class })
	public int delApp_versionByAppId(Integer id) {
		// TODO Auto-generated method stub
		return this.dev_userMapper.delApp_versionByAppId(id);
	}

}
