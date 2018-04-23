package com.zhenhua.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhenhua.web.po.CollectionEntity;
import com.zhenhua.web.po.Home;
import com.zhenhua.web.po.Person;
import com.zhenhua.web.service.MongoService;

@Controller
@RequestMapping("/mongo")
public class MongoDemoController {
	
	@Autowired
	private MongoService service;
	
	@RequestMapping(value="/insert")
	public ModelAndView insert(){
		ModelAndView modelAndView = new ModelAndView("/pages/index");
		
		Person person = new Person();
		person.setAge(21);
		person.setId(1);
		person.setName("杨振华");
		person.setBirthday(new Date());
		service.save(person);
		System.out.println("mongo save - " + person);
		return modelAndView;
	}
	
	@RequestMapping(value="/insert2")
	public ModelAndView insert2(){
		ModelAndView modelAndView = new ModelAndView("/pages/index");
		Home home = new Home();
		Person person = new Person();
		person.setAge(21);
		person.setId(4);
		person.setName("黄xx");
		person.setBirthday(new Date());
		Person person2 = new Person();
		person2.setAge(3);
		person2.setId(3);
		person2.setName("杨小椿");
		person2.setBirthday(new Date());
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		persons.add(person2);
		//service.saveAll(persons);

		home.setId(2);
		home.setHomeName("卡兰蒂斯");
		home.setAddress("村里面的");
		home.setCity("邕宁");
		
		home.setPersons(persons);
		service.save(home);
		System.out.println("mongo save - " + home);
		return modelAndView;
	}
	
	@RequestMapping(value="/getByCollectionExists")
	public ModelAndView getByCollectionExists(){
		ModelAndView modelAndView = new ModelAndView("/pages/index");
		service.getByCollectionExists();
		return modelAndView;
	}
	
	
	@RequestMapping(value="/saveColections")
	public ModelAndView saveColections(){
		ModelAndView modelAndView = new ModelAndView("/pages/index");
		CollectionEntity entity = new CollectionEntity();
		Long[] adds = {1L,2L,3L};
		entity.setId("1");
		entity.setName("太初");
		entity.setAddress(adds);
		service.saveCollection(entity);
		
		CollectionEntity entity2 = new CollectionEntity();
		Long[] adds2 = {0L,6L,9L};
		entity2.setId("2");
		entity2.setName("太始");
		entity2.setAddress(adds2);
		service.saveCollection(entity2);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/getByCollectionExistsArray")
	public ModelAndView getByCollectionExistsArray(){
		ModelAndView modelAndView = new ModelAndView("/pages/index");
		service.getByCollectionExistsArray();
		return modelAndView;
	}
	
	@RequestMapping(value="/saveFile")
	public ModelAndView saveFile(){
		ModelAndView modelAndView = new ModelAndView("/pages/index");
		service.saveFile();
		return modelAndView;
	}
	
	@RequestMapping(value="/queryFile")
	public ModelAndView queryFile(){
		ModelAndView modelAndView = new ModelAndView("/pages/index");
		service.queryFile();
		return modelAndView;
	}
	
	@RequestMapping(value="/getFile")
	public ModelAndView getFile(){
		ModelAndView modelAndView = new ModelAndView("/pages/index");
		try {
			service.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
}
