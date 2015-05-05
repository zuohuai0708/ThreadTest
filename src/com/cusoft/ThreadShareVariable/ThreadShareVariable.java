package com.cusoft.ThreadShareVariable;

import java.util.Random;

/**
 * 线程 范围内的共享变量
 * 它的意思是：在线程范围内，不同的模块是共享变量的
 * 而在线程范围外，独立的
 * 学习如何在线程范围内共享数据的设计思路 ,
 * 学习应该有监听线程死亡的状态的ThreadDeathEvent 的那么一个事件类
 * @author zuohuai
 *
 */
public class ThreadShareVariable {
	public static void main(String[] args){
		new ThreadShareVariable().init();
	}
	public void init(){
		for(int i=1;i<=2;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					int  data  = new Random().nextInt();
					MyThreadData.getThreadData().setAge(data);
					MyThreadData.getThreadData().setName(data);
					System.out.println(Thread.currentThread().getName()+" has put data ："+MyThreadData.getThreadData().getName()+" ，"+MyThreadData.getThreadData().getAge());
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	/**
	 * 模块A
	 * @author zuohuai
	 *
	 */
	static class A {
		public void get(){
			MyThreadData myData = MyThreadData.getThreadData();
			System.out.println("A "+Thread.currentThread().getName()+"  get myData： "+myData.getName()+" ,"+myData.getAge());
		}
	}
	
	/**
	 * 模块B
	 * @author zuohuai
	 *
	 */
	static class B{
		public void get(){
			MyThreadData myData = MyThreadData.getThreadData();
			System.out.println("B "+Thread.currentThread().getName()+"  get myData： "+myData.getName()+" "+myData.getAge());
		}
	}
}

/**
 * 我的和线程相关的数据对象 ,更加满足面向对象的思想
 * @author zuohuai
 *
 */
class MyThreadData{
	int name;
	int age;
	private static MyThreadData data;
	private static ThreadLocal<MyThreadData> map = new ThreadLocal<MyThreadData>();
	private MyThreadData(){
		
	}
	
	public static  MyThreadData getThreadData(){
		data = map.get();
		if(data == null){
			data = new MyThreadData();
			map.set(data);
		}
		return data;
	}
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
