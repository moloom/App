package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.pojo.Backend_user;

public interface Backend_userMapper {
	/**
	 * 登录时，验证数据库里账号和密码是否正确
	 * 
	 * @param backend_user
	 * @return
	 */
	public Backend_user findBackend_user(Backend_user backend_user);

	/**
	 * 查询所有的用户列表
	 * 
	 * @return
	 */
	public List<Backend_user> findAll();

	/**
	 * 根据条件查询记录条数
	 * 
	 * @param map
	 * @return
	 */
	public int countByMap(Map<String, String> map);

	/**
	 * 根据条件查询Backend_user
	 * 
	 * @param map
	 * @return
	 */
	public List<Backend_user> getBackend_userListByMap(Map<String, String> map);

	/**
	 * 添加用户
	 * 
	 * @param backend_user
	 * @return
	 */
	public int addBackend_user(Backend_user backend_user);

	/**
	 * 根据id查询用户的详细信息
	 * 
	 * @param id
	 * @return
	 */
	public Backend_user getBackend_userById(String id);

	/**
	 * Ajax异步验证 把前端输入的Backend_userName拿去查询数据库里是否存在
	 * 
	 * @param Backend_userName
	 * @return
	 */
	public Backend_user ucexist(String backend_userName);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	public int deleteBackend_user(Integer id);

	/**
	 * 修改用户信息
	 * 
	 * @param Backend_user
	 * @return
	 */
	public int updata(Backend_user backend_user);

	/**
	 * 修改用户密码
	 * 
	 * @param Backend_user
	 * @return
	 */
	public int Backend_userUpdataPwd(Backend_user backend_user);
}
