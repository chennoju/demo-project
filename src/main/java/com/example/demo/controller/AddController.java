package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Nums;

@Controller
public class AddController {
@RequestMapping("/sum")
public String show() {
	return "add";
}
	
@RequestMapping("/add")
public ModelAndView add(Nums n) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("nums", n);
	mv.setViewName("result");
	System.out.print(n.getNum1()+" "+n.getNum2());
	return mv;
}
	
}
