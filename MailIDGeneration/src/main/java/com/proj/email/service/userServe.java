package com.proj.email.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.email.bean.Info;
import com.proj.email.repository.EmailRepository;

@Service
public class userServe implements UserService {
	
	@Autowired
	public EmailRepository emRepo;

	@Override
    public List<Info> getAllUsers() {
        return emRepo.findAll();
    }
	
    @Override
    public Info createUser(String id, String firstName, String lastName, String department, String altEmail) {
        // Implement logic to create and save a user
        Info user = new Info();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDepartment(department); // Example department
        user.setPassword(); // You may implement password generation logic
        user.setEmail(firstName, lastName, department);
        user.setInboxCapacity(500); // Default inbox capacity
        emRepo.save(user);
        return user;
    }


    @Override
    public Info updateUser(String id, String newPassword, String altEmail, int inboxCapacity){
        Info user = emRepo.findById(id).orElse(null);
        if (user != null) {
            if (newPassword != null) {
                user.changePassword(newPassword);
            }
            if (altEmail != null) {
                user.setAltEmail(altEmail);
            }
            if (inboxCapacity > 0) {
                user.setInboxCapacity(inboxCapacity);
            }
            emRepo.save(user);
        }
        return user;
    }
	
    @Override
    public void deleteSubject(String id) {
		emRepo.deleteById(id);
	}
    
    
}
