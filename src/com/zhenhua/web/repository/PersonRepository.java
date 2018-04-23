package com.zhenhua.web.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zhenhua.web.po.Person;

public interface PersonRepository extends MongoRepository<Person, Integer>{
	
}