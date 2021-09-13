
package com.cozentusrt.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentusrt.entity.ChangeAuth;
import com.cozentusrt.entity.UserEntity;
import com.cozentusrt.repository.UserRepository;



@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepo;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean emailVerify(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
	}
	
	public UserEntity userAuth(UserEntity user) {
		UserEntity usr;	
		String msg;
		boolean res = emailVerify(user.getUemail());
		
		if(res) {
			usr = userRepo.findByUemail(user.getUemail());
			System.out.println(userRepo.findByUemail(user.getUemail()));
			if(usr == null) {
				msg = "Sorry.. ! Mail-Id Not Found";
			} else if(user.getUpassword().compareTo(usr.getUpassword()) != 0) {
				msg = "Incorrect Email or Password";
			} else {
				msg = "OK";
			}
			
		}
		
		else {
			user.setUname(user.getUemail());
			user.setUemail(null);
			usr = userRepo.findByUname(user.getUname());
			System.out.println(usr);
			
			System.out.println(userRepo.findByUname(user.getUname()));
			if(usr == null) {
				msg = "Sorry...! Username Not Found";
			} else if(user.getUpassword().compareTo(usr.getUpassword()) != 0) {
				msg = "Incorrect Username or Password";
			} else {
				msg = "OK";
			}
		}
		if(usr == null) {
			usr = new UserEntity();
		}
		usr.setMsg(msg);
		return usr;
		
		
		
	}
	
	
	
	public void Update(UserEntity user) {
		userRepo.save(user);
	}
	
	
	public boolean validatePasswd(String password) {
		if(password.length() >= 8) {
			
			boolean U = false;
			boolean L = false;
			boolean N = false;
			boolean S = false;
			
			for(int i = 0; i < password.length(); i++) {
				int asciiVal = (int)password.charAt(i);
				if( asciiVal >= 65 && asciiVal <= 90 ) {
					U = true;
				}
				else if( asciiVal >= 97 && asciiVal <= 122 ) {
					L = true;
				}
				else if( asciiVal >= 48 && asciiVal <= 57 ) {
					N = true;
				}
				else {
					S = true;
				}	
			}
			
			
			if(S && N && L && U) {
				return true;
			} else {
				return false;
			}
		
		} else {
			return false;
		}
	}
	
	
	
	public ChangeAuth verifyPassword(ChangeAuth pass) {
		ChangeAuth argPass = new ChangeAuth();
		String msg;
		argPass.setUid(pass.getUid());
		UserEntity user = userRepo.findById(pass.getUid()).get();
		if(user.getUpassword().compareTo(pass.getExistingpasswd()) == 0) {
			if(pass.getNewpasswd().compareTo(pass.getConfirmpasswd()) == 0) {
				if(pass.getNewpasswd().compareTo(pass.getExistingpasswd()) != 0) {
					if(this.validatePasswd(pass.getNewpasswd())) {
						user.setUpassword(pass.getNewpasswd());
						this.Update(user);
						msg = "Password Changed Successfully";
					} else {
						msg = "Invalid Password..! Min length=8, min-Upper=1, min-Lower=1,min-Special=1.min-Digit=1";
					}
				} else {
					msg = "New Password must not be same as Exxisting Password!!";
				}
			} else {
				msg = "New and Confirm Password Not Matching..!";
			}
		} else {
			msg = "Old Password did not match with current password!!";
		}
		argPass.setMsg(msg);
		return argPass;
	}
	

}
