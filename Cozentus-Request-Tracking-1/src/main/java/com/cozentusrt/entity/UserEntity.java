package com.cozentusrt.entity;


import javax.persistence.Transient;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user1")
public class UserEntity {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   @Column(name="user_id")
	   private int uid;
	   @Column(name="username")
	   private String uname;
	   @Column(name="user_First_Name")
	   private String ufname;
	   @Column(name="user_last_Name")
	   private String ulastname;
	   @Column(name="user_email")
	   private String uemail;
	   @Column(name="user_password")
	   private String upassword;
	   @Column(name="created_date")
	   private LocalDate ucreateddate;
	   @Column(name="created_By")
	   private String ucreatedby;
	   @Column(name="is_User_Active")
	   private Boolean isuactive;
	   
	   @Transient
	   private Boolean flag=false;
	   @Transient
	   private String msg="";
	   
	  
	   
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUfname() {
		return ufname;
	}
	public void setUfname(String ufname) {
		this.ufname = ufname;
	}
	public String getUlastname() {
		return ulastname;
	}
	public void setUlastname(String ulastname) {
		this.ulastname = ulastname;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public LocalDate getUcreateddate() {
		return ucreateddate;
	}
	public void setUcreateddate(LocalDate ucreateddate) {
		this.ucreateddate = ucreateddate;
	}
	public String getUcreatedby() {
		return ucreatedby;
	}
	public void setUcreatedby(String ucreatedby) {
		this.ucreatedby = ucreatedby;
	}
	public Boolean getIsuactive() {
		return isuactive;
	}
	public void setIsuactive(Boolean isuactive) {
		this.isuactive = isuactive;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	   
	
	   
	
	

}
