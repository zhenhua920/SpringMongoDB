package com.zhenhua.web.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.zhenhua.web.po.CollectionEntity;
import com.zhenhua.web.po.Home;
import com.zhenhua.web.po.Person;
import com.zhenhua.web.repository.PersonRepository;

@Service
public class MongoService {
	
	@Resource(name="mongoTemplate")
	private MongoOperations operation;
	
	@Resource(name="gridFsTemplate")
	private GridFsOperations gridFsOperations;
	
	@Autowired
	private GridFSBucket gridFSBucket;
	
	@Autowired
	private PersonRepository personRepository;
	
	public void save(Person person){
		// insert为插入,即新增,如果存在id 则报异常
		operation.insert(person);
		// 相当于 saveOrUpdate 如果存在ID 则更新 否则新增记录
		//operation.save(person);
		
	}
	
	public void modify(Person person){
//		operation.updateFirst(new Query(), new Update(), Person.class);
	}
	
	/**
	 * 保存
	 * @param home
	 */
	public void save(Home home){
		operation.insert(home);
	}
	
	/**
	 * 根据ID 查询
	 * @param id
	 * @return
	 */
	public Person findById(String id){
		
		ObjectId objectId = new ObjectId(id);
		Person person = operation.findById(objectId , Person.class);
		return person;
	}
	
	/**
	 * 查询功能
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Person> getPersonList(){
		Criteria criteria = new Criteria();
		criteria.and("name").is("杨振华");
		criteria.and("age").gte(21); // 大于等于  ,gt- 大于
		Query query = new Query(criteria);
		
		// 过时了 ???
		Sort sort = new Sort(new Order(Direction.ASC, "age"));
		Pageable pageable = new PageRequest(0,10);
		query.with(pageable);
		query.with(sort);
		
		List<Person> data = operation.find(query,Person.class);
		return data;
	}
	

	public void saveAll(List<Person> persons) {
//		operation.insertAll(persons);
		personRepository.saveAll(persons);
	}
	
	public void getByCollectionExists() {
		Criteria criteria = new Criteria();
		//criteria.and("persons.name").is("杨振华");// 复杂结构的查询
		criteria.and("persons.age").gte(21);
		Query query = new Query(criteria);
		List<Home> data = operation.find(query, Home.class);
		System.out.println(data);
	}
	
	public void saveCollection(CollectionEntity entity){
		operation.save(entity);
	}
	
	public void getByCollectionExistsArray() {
		Criteria criteria = new Criteria();
		criteria.and("address").all(0);//数组中包含这个元素
		Query query = new Query(criteria);
		List<CollectionEntity> data = operation.find(query, CollectionEntity.class);
		System.out.println(data);
	}
	
	
	/**
	 * 保存文件
	 */
	public void saveFile(){
		DBObject metaData = new BasicDBObject();
        metaData.put("fileName1","我被@.jpg");
        metaData.put("updateDate", new Date());
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("E:/高清壁纸/wallpapercache/动态图/我被@.jpg");
            gridFsOperations.store(inputStream, "我被@.jpg", "image/png", metaData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Done");
	}

	/**
	 * 查询文件
	 */
	public void queryFile() {
		GridFSFindIterable gfsit = gridFsOperations.find(new Query().addCriteria(Criteria.where("filename").is("我被@.jpg")));
		MongoCursor<GridFSFile> iterator = gfsit.iterator();
		while(iterator.hasNext()){
			GridFSFile file = iterator.next();
			System.out.println(file.getFilename());
			System.out.println(file.getUploadDate());
			System.out.println(file.getMetadata().get("fileName1"));
			System.out.println(file.getObjectId());
		}
	}
	
	/**
	 * 获取文件流
	 * @throws IOException
	 */
	public void getFile() throws IOException {
		ObjectId objectId = new ObjectId("5aba00d0bdd5df0a64ff3916");// file.getObjectId()
		OutputStream out = new FileOutputStream("E:/高清壁纸/wallpapercache/动态图/我被@2.jpg");
		//ByteArrayOutputStream os = new ByteArrayOutputStream();
//		gridFSBucket.downloadToStream(objectId,os);// 查不到文件就抛异常....
		gridFSBucket.downloadToStream(objectId,out);
	}
	
	/**
	 * 删除文件
	 * @throws IOException
	 */
	public void delFile() throws IOException {
		ObjectId objectId = new ObjectId("5aba00d0bdd5df0a64ff3916");
		gridFSBucket.delete(objectId);
	}

	
}
