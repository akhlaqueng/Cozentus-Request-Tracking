package com.cozentusrt.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;





	@Entity
	@Table(name="dept")
	
	public class DeptEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="dept_id")
		private int did;
		@Column(name="dept_code")
		private String dcode;
		@Column(name="department_name")
		private String dname;
		@Column(name="parent_department_code")
		private String pdcode;
		@Column(name="created_by")
		private String createdby;
		@Column(name="is_dept_active")
		
		private boolean deptactive;
	
		@OneToMany(mappedBy = "requestDept",targetEntity = RequestsEntity.class)
		List<RequestsEntity> reqList;
		
		
		@Transient
		private int uid; 
		
		public int getUid()
		{
			return uid;
		}
		
		public void setUid(int uid)
		{
			this.uid=uid;
		}
		
		public int getDid() {
			return did;
		}
		public void setDid(int did) {
			this.did = did;
		}
		public String getDcode() {
			return dcode;
		}
		public void setDcode(String dcode) {
			this.dcode = dcode;
		}
		public String getDname() {
			return dname;
		}
		public void setDname(String dname) {
			this.dname = dname;
		}
		public String getPdcode() {
			return pdcode;
		}
		public void setPdcode(String pdcode) {
			this.pdcode = pdcode;
		}
		public String getCreatedby() {
			return createdby;
		}
		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}
		public Boolean getDeptactive() {
			return deptactive;
		}
		public void setDeptActive(Boolean deptactive) {
			this.deptactive = deptactive;
		}
	
		
		

}
