package com.raghsonline.springboot.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghsonline.springboot.demo.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> 
{
	
	public List<Person> findByEmail(String email);
	
	List<Person> findByEmailContaining(String email);
}
