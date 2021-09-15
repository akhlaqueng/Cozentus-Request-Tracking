package com.cozentusrt.entity;

public class ChangeAuth {
	
	private int uid;
	private String existingpasswd;
	private String newpasswd;
	private String confirmpasswd;
	
	private String msg = "";
	private boolean visited = false;
	
	private String emailid;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getExistingpasswd() {
		return existingpasswd;
	}

	public void setExistingpasswd(String existingpasswd) {
		this.existingpasswd = existingpasswd;
	}

	public String getNewpasswd() {
		return newpasswd;
	}

	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}

	public String getConfirmpasswd() {
		return confirmpasswd;
	}

	public void setConfirmpasswd(String confirmpasswd) {
		this.confirmpasswd = confirmpasswd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean v) {
		this.visited = v;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	

}
