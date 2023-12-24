package com.proj.email.service;

import java.util.List;

import com.proj.email.bean.Info;

public interface UserService {
	
	Info createUser(Long id, String firstName, String lastName, String department, String altEmail);
	Info updateUser(Long id, String newPassword, String altEmail, int inboxCapacity);
	void deleteSubject(Long id);
	List<Info> getActiveUsers();
}
