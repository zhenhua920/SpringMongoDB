package com.zhenhua.mongo.comm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoClientOptionsFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

/**
 * 使用元注解配置 MongoDB
 * @author zhenhua920
 */
 @Configuration
 @EnableMongoRepositories(basePackages="com.zhenhua")
public class MongoConfiguration extends AbstractMongoConfiguration{
	
	private String mongoURI = "mongodb://username:password@ip:port/fsugar";
	private String mongoHost = "127.0.0.1";
	private Integer mongoPort = 27017;
	private String mongodbName = "dbName";
	
	@Override
	protected String getDatabaseName() {
		return mongodbName;
	}
	
	public @Bean MongoClient mongoClient() {
//		MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoURI));
		return new MongoClient(mongoHost, mongoPort);
	}

	public @Bean MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
		/*MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
		MongoCredential mc =MongoCredential.createCredential("fsugar", "fsugar", "fsugar!123".toCharArray());
		MongoCredential[] credentials = {mc};
		mongoClientFactoryBean.setCredentials(credentials);*/
		return new SimpleMongoDbFactory(mongoClient, mongodbName);
	}

	/**
	 * MongoOperations 接口实例
	 * @param mongoDbFactory
	 * @return MongoOperations
	 */
	public @Bean MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
		@SuppressWarnings("deprecation")
		MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory, new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return new MongoTemplate(mongoDbFactory, converter);
	}

	/**
	 * GridFsOperations 接口实例 <br/>
	 * 用来查询文件,保存文件
	 * @param mongoDbFactory
	 * @return GridFsOperations
	 * @throws Exception
	 */
	@Bean
	public GridFsTemplate gridFsTemplate(MongoDbFactory mongoDbFactory) throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}
	
	/**
	 * @param mongoDbFactory
	 * @return com.mongodb.client.gridfs.GridFSBucket
	 */
	@Bean
	public GridFSBucket gridFSBuckets(MongoClient mongoClient){
		return GridFSBuckets.create(mongoClient.getDatabase(mongodbName));
	}
	
}
