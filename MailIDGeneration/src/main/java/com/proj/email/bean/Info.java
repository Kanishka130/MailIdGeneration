package com.proj.email.bean;

import java.sql.Timestamp;
import java.util.logging.Logger;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.proj.email.AccountState.AccountState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreRemove;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

@Entity
@Table(name="Info")
@SQLDelete(sql = "UPDATE Info SET state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "state <> 'DELETED'")
@DynamicInsert
@DynamicUpdate // Logs the updated value of the updated field only and not the non-updated ones
public class Info {
	
	@Id
	@GeneratedValue(generator = "idGenerator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "idGenerator", sequenceName="idSeq", initialValue= 100, allocationSize =1)
	private Long id;
	private String firstName; 
	private String lastName;
	private String password;
	private String department;
	private String email;
	private String altEmail;
	private int inboxCapacity = 500;
	

	@Column
	@Enumerated(EnumType.STRING)
	public AccountState state = AccountState.ACTIVE;
	
	@Version
	private Integer count; //Number of times it is updated
	
	
	@CreationTimestamp
	public Timestamp creationTime;
	
	@UpdateTimestamp
	private Timestamp updateTime;
	
	public AccountState getState() {
		return state;
	}
	public void setState(AccountState state) {
		this.state = state;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public Info() {
		super();
		this.state = AccountState.ACTIVE;

	}
	
	
	public Info(Long id, String firstName, String lastName, String department, String altEmail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.altEmail = altEmail;
		this.state = AccountState.ACTIVE;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Timestamp getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
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
		this.email = firstName.toLowerCase() + lastName.toLowerCase() + "." + department + "@liit.ac.in";
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
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
	@PreRemove
    public void deleteUser() {
    	this.state = AccountState.DELETED;
    }
	
}




//Access Token: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
