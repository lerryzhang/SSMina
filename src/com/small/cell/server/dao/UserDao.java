package com.small.cell.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.small.cell.server.pojo.User;

public interface UserDao {

	List<User> listUser(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

	User getUser(String username);

	void updateUser(User user);

	void saveUser(User user);
	
	int selectUserCount();
}
