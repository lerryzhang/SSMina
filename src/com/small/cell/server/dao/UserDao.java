package com.small.cell.server.dao;

import java.util.List;

import com.small.cell.server.pojo.User;

public interface UserDao {

	List<User> listUser();

	User getUser(String username);

	void updateUser(User user);

	void saveUser(User user);
}
