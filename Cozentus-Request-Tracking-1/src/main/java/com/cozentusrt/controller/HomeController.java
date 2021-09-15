package com.cozentusrt.controller;

import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cozentusrt.services.DeptService;
import com.cozentusrt.services.UserServices;

import com.cozentusrt.entity.*;
import com.cozentusrt.services.*;

@Controller
public class HomeController {

	private UserServices usvc;
	private DeptService  dsvc;
	private RequestService reqsvc;
		
	public HomeController(UserServices usvc, DeptService dsvc, RequestService reqsvc) {
		this.usvc = usvc;
		this.dsvc = dsvc;
		this.reqsvc=reqsvc;
		
	}
	 
	@GetMapping("/")
	public String ULogin(Model model) {
		UserEntity user = new UserEntity();
		model.addAttribute("user", user);
		
		return "index";
	}
	
	@PostMapping("/Login")
	public String login(@ModelAttribute("user") UserEntity user, Model model) {
		UserEntity res = usvc.userAuth(user);
		if(res.getMsg().compareTo("OK") == 0) {
			model.addAttribute("user",res);
			return "homepage";
		} else {
			res.setFlag(true);
			model.addAttribute("user",res);
			return "index";
		}
		
	}
	
	
	@GetMapping("/Changepwd/{id}")
	public ModelAndView changeAuth(@PathVariable(name = "id") int id, Model model) {

		ChangeAuth password = new ChangeAuth();
		ModelAndView mav = new ModelAndView("changepwd");
		password.setUid(id);
		mav.addObject("password", password);
		
		return mav;
			
	}
	
	@PostMapping("/UpdatePassword")
	public String updatePassword(@ModelAttribute("password") ChangeAuth password, Model model) {
		
		ChangeAuth argPassword = usvc.verifyPassword(password);
		System.out.println(argPassword.getMsg());
		model.addAttribute("message", argPassword.getMsg());
		argPassword.setVisited(true);
		model.addAttribute("password", argPassword);
		return "changepwd";
	}
	
	@GetMapping("/Dept/{id}")
	public String serveDepartment(@PathVariable(name = "id") int id, Model model) {
		DeptEntity dept=new DeptEntity();
		dept.setUid(id);
		model.addAttribute("dept",dept);
		return "deptui";
	}
	
	@PostMapping("/saveDept")
	public String saveDept(@ModelAttribute("dept") DeptEntity dept, Model model) {
		System.out.println("Save Dept");
		String msg = dsvc.save(dept);
		System.out.println(msg);
		model.addAttribute("message", msg);
		return "deptui";
	}
	
	@GetMapping("/CreateRequest")
	public String getCreateRequest(Model model) {
		RequestsEntity request=new RequestsEntity();
		TreeMap<Integer, String> deptIdsAndCodes = dsvc.getAllDeptId();
		
//		for(String code : deptCodes) {
//			System.out.println(code);
//		}
		
		
		model.addAttribute("deptIds", deptIdsAndCodes);
        model.addAttribute("request",request);
        return "CreateRequest";
	}
	
	 @PostMapping("/saveRequest")
	    public String saveRqst(@ModelAttribute("request") RequestsEntity request, Model model) {
	        System.out.println("Save Request");
	        int status = this.reqsvc.saveRequest(request);
	        
	        if(status == 1) {
	        	return "redirect:/Homepage";
	        } else {
	        	String msg = "Some Error Occured!! Please try again";
	        	model.addAttribute("message",msg);
	        	return "CreateRequest";
	        }
	        
//	        System.out.println(msg);
//	        model.addAttribute("message", msg);
	        
	  }
	
	@GetMapping("/Homepage")
	public String getRhomepage(Model model) {

		List<RequestsEntity> allRequests = reqsvc.getAllRequests();
		model.addAttribute("allRequests", allRequests);
		return "requesthomepage";
	}
	

	
	
}
