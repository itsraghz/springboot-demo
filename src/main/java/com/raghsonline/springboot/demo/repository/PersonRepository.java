package com.raghsonline.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raghsonline.springboot.demo.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
