package com.example.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping("/home")
	
	/*
	 public String home(@RequestParam("nam") String name,HttpServletRequest req) {
		HttpSession ses=req.getSession();
		ses.setAttribute("name", name);
		return "home";}
		*/
	public ModelAndView home() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	
	
}
