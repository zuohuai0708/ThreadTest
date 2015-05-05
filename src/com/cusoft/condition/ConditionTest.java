package com.cusoft.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 在等待 Condition 时，允许发生“虚假唤醒”，这通常作为对基础平台语义的让步。
 * 对于大多数应用程序，这带来的实际影响很小，
 * 因为 Condition 应该总是在一个循环中被等待，并测试正被等待的状态声明。
 * 某个实现可以随意移除可能的虚假唤醒，但建议应用程序程序员总是假定这些虚假唤醒可能发生，
 * 因此总是在一个循环中等待。
	一个锁内部可以有多个Condition，即有多路等待和通知，
	可以参看jdk1.5提供的Lock与Condition实现的可阻塞队列的应用案例，
	从中除了要体味算法，还要体味面向对象的封装。
	在传统的线程机制中一个监视器对象上只能有一路等待和通知，要想实现多路等待和通知，
	必须嵌套使用多个同步监视器对象。（如果只用一个Condition，两个放的都在等，
	一旦一个放的进去了，那么它通知可能会导致另一个放接着往下走。）
 * 实现主线程运行50次 执行50次，子线程运行20次 执行20次，之后在交替执行
 * 一共交替执行50次
 * @author zuohuai
 *
 */
public class ConditionTest {

	public static void main(String[] args)
	{
		final Bussiness bussiness = new Bussiness();
		/*
		 * 子线程
		 */
		new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i=1;i<=20;i++){
					bussiness.sub1(i);
				}
			}
		}).start();
		
		/*
		 * 主线程
		 */
		for(int i=1;i<=20;i++){
			bussiness.main(i);
		}
	}
}

/**
 * 业务类
 * @author zuohuai
 *
 */
class Bussiness{
	private boolean beShouldSub = true;
	private Lock loc = new ReentrantLock();
	private Condition condition = loc.newCondition();
	 public void sub1(int i){
		 loc.lock();
		 try{
		//condition 也存在虚假唤醒，所以需要使用，使用while，替代if 可以避免被假唤醒的情况
		while(!beShouldSub){
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			for(int j=1;j<=20;j++){
				System.out.println("sub "+i+"的sequence" +j);
			}
			beShouldSub = false;
			condition.signal();
		 }finally{
			loc.unlock(); 
		 }
	}
	 
	 public void sub2(int i){
		 loc.lock();
		 try{
		//condition 也存在虚假唤醒，所以需要使用，使用while，替代if 可以避免被假唤醒的情况
		while(!beShouldSub){
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			for(int j=1;j<=20;j++){
				System.out.println("sub "+i+"的sequence" +j);
			}
			beShouldSub = false;
			condition.signal();
		 }finally{
			loc.unlock(); 
		 }
	}
	 
	 public void main(int i){
		 loc.lock();
		 try{
		//使用while，替代if 可以避免被假唤醒的情况
		while(beShouldSub){
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			for(int j=1;j<=100;j++){
				System.out.println("main "+i+"的sequence" +j);
			}
			beShouldSub = true;
			condition.signal();
		 }finally{
			 loc.unlock();
		 }
	}
}


