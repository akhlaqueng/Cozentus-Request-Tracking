package com.cozentusrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozentusrt.entity.RequestsEntity;
@Repository
public interface RequestRepository extends JpaRepository<RequestsEntity, Integer> {

}
