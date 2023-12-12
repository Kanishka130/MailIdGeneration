package com.proj.email.service;

import java.util.List;

import com.proj.email.bean.Info;

public interface UserService {
	
	Info createUser(String id, String firstName, String lastName, String department, String altEmail);
	Info updateUser(String id, String newPassword, String altEmail, int inboxCapacity);
	void deleteSubject(String id);
	List<Info> getAllUsers();
}
