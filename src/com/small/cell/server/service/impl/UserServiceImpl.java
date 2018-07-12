package com.small.cell.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.small.cell.server.dao.UserDao;
import com.small.cell.server.pojo.User;
import com.small.cell.server.service.UserService;

/**
 * 
 * 
 * <p>
 * Title: UserServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author lerry
 * @date 2018-7-5 ÉÏÎç11:19:12
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	public UserDao userDao;

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userDao.getUser(username);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDao.saveUser(user);
	}

	@Override
	public int selectUserCount() {
		// TODO Auto-generated method stub
		return userDao.selectUserCount();
	}

	@Override
	public List<User> listUser(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.listUser(pageNo, pageSize);
	}

}
