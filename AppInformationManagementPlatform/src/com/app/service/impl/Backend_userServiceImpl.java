package com.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mapper.Backend_userMapper;
import com.app.pojo.Backend_user;
import com.app.service.Backend_userService;

@Service("backend_userService")
public class Backend_userServiceImpl implements Backend_userService {

	@Autowired
	private Backend_userMapper backend_userMapper;
	
	public Backend_user findBackend_user(Backend_user backend_user) {
		// TODO Auto-generated method stub
		return this.backend_userMapper.findBackend_user(backend_user);
	}

	public List<Backend_user> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int countByMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Backend_user> getBackend_userListByMap(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addBackend_user(Backend_user backend_user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Backend_user getBackend_userById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Backend_user ucexist(String backend_userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deleteBackend_user(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updata(Backend_user backend_user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int Backend_userUpdataPwd(Backend_user backend_user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
