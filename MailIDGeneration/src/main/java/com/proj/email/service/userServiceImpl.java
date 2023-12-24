package com.proj.email.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.email.AccountState.AccountState;
import com.proj.email.bean.Info;
import com.proj.email.repository.EmailRepository;

import jakarta.persistence.PreRemove;

@Service
public class userServiceImpl implements UserService {
	
	@Autowired
	public EmailRepository emRepo;

	@Override
    public List<Info> getActiveUsers() {
        //return emRepo.findAll();
		return emRepo.findByState(AccountState.ACTIVE);
    }
	
    @Override
    public Info createUser(Long id, String firstName, String lastName, String department, String altEmail) {
        // Implement logic to create and save a user
        Info user = new Info();
        //user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDepartment(department); // Example department
        user.setPassword(); // You may implement password generation logic
        user.setAltEmail(altEmail); 
        user.setEmail(firstName, lastName, department);
        user.setInboxCapacity(500); // Default inbox capacity
		System.out.println("Email: " + user.getEmail());
	    System.out.println("Password: " + user.getPassword());
        emRepo.save(user);
        return user;
    }


    @Override
    public Info updateUser(Long id, String newPassword, String altEmail, int inboxCapacity){
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
    public void deleteSubject(Long id) {
		//emRepo.deleteById(id);
    	Info user = emRepo.findById(id).orElse(null);
        if (user != null) {
            user.setState(AccountState.INACTIVE);
            emRepo.save(user);
        }
	}
    
    
}


