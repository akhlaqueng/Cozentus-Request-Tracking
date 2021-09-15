package com.cozentusrt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cozentusrt.entity.*;

@Repository
public interface DepartmentRepository extends JpaRepository<DeptEntity,Integer> {
	public List<DeptEntity> findAllByPdcode(String pdcode);
	public List<DeptEntity> findAllByDcode(String dcode);
}
