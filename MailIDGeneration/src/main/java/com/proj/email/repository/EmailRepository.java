package com.proj.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.email.bean.Info;

public interface EmailRepository extends JpaRepository<Info,String>{

}
