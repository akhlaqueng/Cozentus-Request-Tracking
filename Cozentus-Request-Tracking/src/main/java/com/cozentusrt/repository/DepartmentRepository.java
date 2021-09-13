package com.cozentusrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cozentusrt.entity.*;

@Repository
public interface DepartmentRepository extends JpaRepository<DeptEntity,Integer> {

}
