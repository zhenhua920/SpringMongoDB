package com.zhenhua.web.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.zhenhua.web.po.Person;

public interface PersonPagingAndSortingRepository extends PagingAndSortingRepository<Person, Integer>{
	
}