package com.cusoft.collection;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
	public static void main(String[] args){
		ConcurrentHashMap<Integer,Person> map  = new ConcurrentHashMap<Integer,Person>();
		map.put(1, Person.valueOf(1, "张三")) ;
		map.put(2, Person.valueOf(2, "李四")) ;
		Person p = map.get(1);
		p.setName("王五");
		for(Entry<Integer,Person> entry :map.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue().getName());
		}
	}
	
	static class  Person {
		private int id ;
		private String name ;
		
		public static Person valueOf(int id , String name ){
			Person vo = new Person();
			vo.id = id ;
			vo.name = name ;
			return vo ;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
}
