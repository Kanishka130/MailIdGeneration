package com.proj.email.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proj.email.AccountState.AccountState;
import com.proj.email.bean.Info;

public interface EmailRepository extends JpaRepository<Info,Long>{
	List<Info> findByState(AccountState state);

    @Query("SELECT user FROM Info user WHERE user.state = 'ACTIVE'")
    List<Info> findAllActiveUsers();
}
