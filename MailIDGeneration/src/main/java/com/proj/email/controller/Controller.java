package com.proj.email.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.email.bean.Info;
import com.proj.email.service.UserService;

@RestController
@RequestMapping("/users")
public class Controller {

	@Autowired
	private UserService userService;
	
	@GetMapping
    public List<Info> getAllUsers() {
        return userService.getAllUsers();
    }
	
	@PostMapping
	public Info createUser(@RequestParam String id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String department, @RequestParam String altEmail) {
	    return userService.createUser(id, firstName, lastName, department, altEmail);
	}

	@PutMapping("/user/{id}")
	public Info updateUser(@PathVariable String id, @RequestParam(required = false) String newPassword,
	                           @RequestParam(required = false) String altEmail, @RequestParam(required = false) Integer inboxCapacity) {
		return userService.updateUser(id, newPassword, altEmail, inboxCapacity);
    }
	
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
	public void deleteSubject(@PathVariable String id) {
		userService.deleteSubject(id);
	}
}
