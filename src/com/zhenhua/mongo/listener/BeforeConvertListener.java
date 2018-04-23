package com.zhenhua.mongo.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.stereotype.Component;

import com.zhenhua.web.po.Person;

//@Component
public class BeforeConvertListener extends AbstractMongoEventListener<Person>{

	@Override
	public void onAfterConvert(AfterConvertEvent<Person> event) {
		System.out.println("onAfterConvert: " + event.toString());
		super.onAfterConvert(event);
	}

	@Override
	public void onAfterDelete(AfterDeleteEvent<Person> event) {
		System.out.println("onAfterDelete: " + event.toString());
		super.onAfterDelete(event);
	}

	@Override
	public void onAfterLoad(AfterLoadEvent<Person> event) {
		System.out.println("onAfterLoad: " + event.toString());
		super.onAfterLoad(event);
	}

	@Override
	public void onAfterSave(AfterSaveEvent<Person> event) {
		System.out.println("onAfterSave: " + event.toString());
		super.onAfterSave(event);
	}

	@Override
	public void onApplicationEvent(MongoMappingEvent<?> arg0) {
		System.out.println("onApplicationEvent: " + arg0);
		super.onApplicationEvent(arg0);
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Person> event) {
		System.out.println("onBeforeConvert: " + event.toString());
		super.onBeforeConvert(event);
	}

	@Override
	public void onBeforeDelete(BeforeDeleteEvent<Person> event) {
		System.out.println("onBeforeDelete: " + event.toString());
		super.onBeforeDelete(event);
	}

	@Override
	public void onBeforeSave(BeforeSaveEvent<Person> event) {
		System.out.println("onBeforeSave: " + event.toString());
		super.onBeforeSave(event);
	}
	
}
