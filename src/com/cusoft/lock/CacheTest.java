package com.cusoft.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 读取 ReentrantReadWriteLock  中的api 中设计的 CachedData 
 * 读写锁：分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，写锁与写锁互斥，
 * 这是由jvm自己控制的，你只要上好相应的锁即可。如果你的代码只读数据，
 * 可以很多人同时读，但不能同时写，那就上读锁；如果你的代码修改数据，
 * 只能有一个人在写，且不能同时读取，那就上写锁。
 * 总之，读的时候上读锁，写的时候上写锁！
 * 利用读写锁，设计一套缓存系统
 * @author 
 *
 */
public class CacheTest {
	private Map<String,Object>  map = new HashMap<String,Object>();
	private ReadWriteLock  rwl = new ReentrantReadWriteLock(); //读写锁
	private Lock readLock = rwl.readLock();
	private Lock writeLock = rwl.writeLock();
	public static void main(String[] args){
		new CacheTest().read(); 
	}
	
	 void write() {
		// do sth...
		//read();
		 synchronized (this) {
				
			}
	}
	
	 void read(){
		synchronized (this) {
			write();
		}
	}
	
	public Object getData(String key ){
		rwl.readLock().lock();
		try{
			Object value = null;
			if(value == null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
						if(value == null){
							value ="data"; //写人数据
							rwl.writeLock().unlock();
						}

				}finally{
					rwl.writeLock().unlock();
					rwl.readLock().lock();
				}
			}
		}finally{
			rwl.readLock().unlock();
		}
		return null;
	}
}
