package com.raghsonline.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController 
{
	@GetMapping("/")
	public String index()
	{
		System.out.println("HelloController - URI - '/' invoked!");
		return "index";
	}
}
