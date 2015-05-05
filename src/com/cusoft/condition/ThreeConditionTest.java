package com.cusoft.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3 个线程交替运行
 * @author zuohuai
 *
 */
public class ThreeConditionTest {

	public static void main(String[] args)
	{
		final Bussiness bussiness = new Bussiness();
		/*
		 * 子线程1
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
		 * 子线程2
		 */
		new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i=1;i<=20;i++){
					bussiness.sub2(i);
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
	
	/**
	 * 业务类
	 * @author zuohuai
	 *
	 */
	static class Bussiness{
		private Lock loc = new ReentrantLock();
		private Condition condition1 = loc.newCondition();
		private Condition condition2 = loc.newCondition();
		private Condition condition3 = loc.newCondition();
		int flag = 1 ; //主线程运行的标志分别是 1,2,3
		
		 public void sub1(int i){
			 loc.lock();
			 try{
			//condition 也存在虚假唤醒，所以需要使用，使用while，替代if 可以避免被假唤醒的情况
			while(flag!=2){
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				for(int j=1;j<=20;j++){
					System.out.println("sub1 "+i+"的sequence" +j);
				}
				flag = 3;
				condition2.signal();
			 }finally{
				loc.unlock(); 
			 }
		}
		 
		 public void sub2(int i){
			 loc.lock();
			 try{
			//condition 也存在虚假唤醒，所以需要使用，使用while，替代if 可以避免被假唤醒的情况
			while(flag!=3){
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				for(int j=1;j<=30;j++){
					System.out.println("sub2 "+i+"的sequence" +j);
				}
				flag = 1;
				condition1.signal();
			 }finally{
				loc.unlock(); 
			 }
		}
		 
		 public void main(int i){
			 loc.lock();
			 try{
			//使用while，替代if 可以避免被假唤醒的情况
			while(flag!= 1){
				try {
					condition1.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				for(int j=1;j<=50;j++){
					System.out.println("main "+i+"的sequence" +j);
				}
				flag = 2;
				condition2.signal();
			 }finally{
				 loc.unlock();
			 }
		}
	}
}




