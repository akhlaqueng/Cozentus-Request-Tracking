package com.cozentusrt.services;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentusrt.entity.DeptEntity;
import com.cozentusrt.repository.DepartmentRepository;
import com.cozentusrt.repository.UserRepository;



@Service
public class DeptService {
	
	@Autowired
	DepartmentRepository dptrepo;
	
	@Autowired
	UserRepository usrrepo;
	
	public String save(DeptEntity dept) {
		System.out.println(dept.getDname()+dept.getDcode()+dept.getPdcode()+dept.getDeptactive()+dept.getUid());
		List<DeptEntity> d;
		String msg;
		dept.setCreatedby(usrrepo.getById(dept.getUid()).getUfname());
		if(this.dptrepo == null) {
			if(dept.getDcode().compareTo(dept.getPdcode()) == 0) {
				dptrepo.save(dept);
				msg = "Department Saved Successfully";
			} else {
				msg = "Parent Department Doesn't Exist!! ";
			}
		}
		d = dptrepo.findAllByDcode(dept.getDcode());
		
		if(d.isEmpty()) {
			if(dept.getDcode().compareTo(dept.getPdcode()) == 0) {
				dptrepo.save(dept);
				msg = "Department Saved Successfully";
			} else {
				d = dptrepo.findAllByDcode(dept.getPdcode());
				if(d.isEmpty()) {
					msg = "Parent Department Doesn't Exist!! ";
				} else {
					msg = "Department Saved Successfully";
					dptrepo.save(dept);
				}
			}
		} else {
			msg = "Department Code already Exists!!";
		}
		
		return msg;
	}
	
	public TreeMap<Integer, String> getAllDeptId() {
		
		List<DeptEntity> getAllDept = dptrepo.findAll();
		
//		To store Departments in sorted order according to DeptID
		TreeMap<Integer, String> treeMapDeptCodes = new TreeMap<Integer, String>();
		
		for(DeptEntity codes : getAllDept) {
			
			treeMapDeptCodes.put(codes.getDid(), codes.getDcode());
		}
		
		System.out.println(treeMapDeptCodes);
		return treeMapDeptCodes;
	}
	
}

