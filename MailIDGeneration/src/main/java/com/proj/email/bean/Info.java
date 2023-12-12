package com.proj.email.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Info")
public class Info {
	
	@Id
	private String id;
	private String firstName; 
	private String lastName;
	private String password;
	private String department;
	private String email;
	private String altEmail;
	private int inboxCapacity = 500;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public Info() {
		super();
	}
	
	
	public Info(String id, String firstName, String lastName, String department, String altEmail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.altEmail = altEmail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAltEmail() {
		return altEmail;
	}
	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
	}
	
	public void setInboxCapacity(int capacity) {
		this.inboxCapacity = capacity;
	}
	
	public void setPassword() {
		String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%&*";
		char pass[] = new char[10];
		for(int i=0;i<10;i++) {
			int rand = (int)(Math.random()*passwordSet.length());
			pass[i] = passwordSet.charAt(rand);
		}
		this.password = new String(pass);
	}
	
	public void setEmail(String firstName, String lastName, String department) {
		this.altEmail = firstName.toLowerCase() + lastName.toLowerCase() + "." + department + "@liit.ac.in";
	}
	
	public void changePassword(String pass) {
		if(pass.length() < 10) { //Checks if password length is less than the default value
			System.out.println("Password must be 10 characters long.");
			changePassword(pass);
		}
		else {
			this.password = pass;
		}
	}
	
}
