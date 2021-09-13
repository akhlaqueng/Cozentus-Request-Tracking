package com.cozentusrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozentusrt.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

	public UserEntity findByUemail(String email);
	public UserEntity findByUname(String userName);

}
