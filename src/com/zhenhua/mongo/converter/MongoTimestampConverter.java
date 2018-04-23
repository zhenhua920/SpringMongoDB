package com.zhenhua.mongo.converter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class MongoTimestampConverter implements Converter<Date,Timestamp>{

	@Override
	public Timestamp convert(Date date) {
		if(date != null ){
			return new Timestamp(date.getTime());
		}
		return null;
	}
}
