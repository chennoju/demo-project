package com.example.demo;

import org.springframework.stereotype.Component;

@Component("ex2")
public class Example2 {
	public static int i=0;
	public void compile() {
		System.out.println("Example2 running....."+(++i));
	}
}
