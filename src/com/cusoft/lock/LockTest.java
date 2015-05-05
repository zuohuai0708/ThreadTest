package com.cusoft.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock比传统线程模型中的synchronized方式更加面向对象，与生活中的锁类似，
 * 锁本身也应该是一个对象。
 * 两个线程执行的代码片段要实现同步互斥的效果，它们必须用同一个Lock对象。
 * 关于传统线程互斥  使用synchronized 关键字
 * 要进行互斥效果的时候，必须使用同一个锁对象
 * @author zuohuai
 *
 */
public class LockTest {
//	private static int a = 0; 
	public static void main(String[] args){
		new LockTest().init();
	}
	
	public void init(){
		 final Outputer outputer = new Outputer(); //为什么要加上final
		//outputer.output1("");
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					try {
						Thread.sleep(10);
						outputer.output1("zhangzuohuai");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					try {
						Thread.sleep(10);
						outputer.output1("aaa");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	/**
	 * 非内部类 可以调用外部类 的所有 非静态成员
	 * @author zuohuai
	 *
	 */
	 static class Outputer {
		/**
		 * 方式一
		 * 代码段上加入synchronized 
		 * 如果不加synchronized 就会出现“zhangzuohua
		 * i” 的结果
		 * @param name
		 */
		 private Lock lock = new ReentrantLock(); 
		 // 不单单是synchronized的替代品， 与synchronized 有着 本质的区别 ，可以查看  http://tenyears.iteye.com/blog/48750
		private void output1(String name){
			//System.out.println(a);
			int length = name.length();
			lock.lock();
			try{
				for(int i=0;i<length;i++){
					System.out.print(name.charAt(i));
				}
			}finally{
				lock.unlock();
			}
			System.out.println();
			
		}
		
		/**
		 * 方法二
		 * 方法前加入synchronized ，和方法一可以互斥，因为是使用的都是 同一把锁（this 对象）
		 * @param name
		 */
		synchronized private void output2(String name){
			int length = name.length();
				for(int i=0;i<length;i++){
					System.out.print(name.charAt(i));
					System.out.println();
			}
		}
		
		/**
		 * 方法三
		 * 静态方法前加入synchronized ，和方法一可以互斥，因为是使用的都是 同一把锁（Outputer.class  字节码对象）
		 * @param name
		 */
		 static synchronized private void output3(String name){
			int length = name.length();
				for(int i=0;i<length;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
		}
	}
}
