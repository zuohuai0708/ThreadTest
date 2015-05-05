package com.cusoft.collection;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest<T> {
	private  final ArrayBlockingQueue<T> arrayBlockingQueue  = new ArrayBlockingQueue<T>(10);
	
	public static void main(String[] args) throws Exception{
		final ArrayBlockingQueueTest<Double> test = new ArrayBlockingQueueTest<Double>();
		for(int i=0;i<20;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						test.put(Math.random());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();;
		}
		Thread.currentThread().sleep(5000);
		
		for(int i=0;i<30;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(test.get());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	public void put(T e) throws Exception{
		//将指定的元素插入此队列的尾部，如果该队列已满，则等待可用的空间。
		arrayBlockingQueue.put(e);
	}
	
	public T get() throws Exception{
		//获取并移除此队列的头部，在元素变得可用之前一直等待（如果有必要）。
		return arrayBlockingQueue.take();
	}
}
