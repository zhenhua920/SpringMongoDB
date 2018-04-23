package com.zhenhua.web.po;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mongo_collection_collectionEntity")
public class CollectionEntity implements Serializable {

	private String id;
	private String name;
	private Long[] address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long[] getAddress() {
		return address;
	}

	public void setAddress(Long[] address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "CollectionEntity [id=" + id + ", name=" + name + ", address=" + Arrays.toString(address) + "]";
	}

}
