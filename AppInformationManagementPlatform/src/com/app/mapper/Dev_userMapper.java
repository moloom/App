package com.app.mapper;

import java.util.List;
import java.util.Map;

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
	 * 查询所有的用户列表
	 * 
	 * @return
	 */
	public List<Dev_user> findAll();

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param map
	 * @return
	 */
	public int countByMap(Map<String, String> map);

	/**
	 * 根据条件查询Dev_user
	 * 
	 * @param map
	 * @return
	 */
	public List<Dev_user> getDev_userListByMap(Map<String, String> map);

	/**
	 * 添加用户
	 * 
	 * @param dev_user
	 * @return
	 */
	public int addDev_user(Dev_user dev_user);

	/**
	 * 根据id查询用户的详细信息
	 * 
	 * @param id
	 * @return
	 */
	public Dev_user getDev_userById(String id);

	/**
	 * Ajax异步验证 把前端输入的Dev_userName拿去查询数据库里是否存在
	 * 
	 * @param dev_userName
	 * @return
	 */
	public Dev_user ucexist(String dev_userName);

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
