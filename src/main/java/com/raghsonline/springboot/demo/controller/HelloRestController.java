package com.raghsonline.springboot.demo.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class HelloRestController 
{	
	@GetMapping
	public String sayHello() //URI --> /api/ [http://localhost:8080/api/]
	{
		System.out.println("URI - '/' invoked!");
		
		return String.format("Hello from SpringBoot RestController, at %s", 
				LocalDateTime.now().toString());
	}
}
