package com.small.cell.server.service;

import java.util.List;

import com.small.cell.server.pojo.User;

public interface UserService {
	public List<User> listUser(int pageNo,int pageSize);
	public User getUser(String username);
	public void updateUser(User user);
	public void saveUser(User user) ;
	public int selectUserCount();
}
