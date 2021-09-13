package com.cozentusrt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.cozentusrt.services.UserServices;
import com.cozentusrt.entity.*;


@Controller
public class HomeController {

	private UserServices usvc;
		
	public HomeController(UserServices usvc) {
		this.usvc = usvc;
		
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

	
	
}
