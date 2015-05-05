package com.cusoft.ThreadShareVariable;

/**
 * 我的线程共享变量测试
 * 关于一道面试题 
 * 设计两个线程，一个变量 j ，一个线程变量对j  加  1 ，
 * 一个线程对变量  j 减 1
 * @author
 *
 */
public class MyThreadSVTest {
	
	private static MyShareData data = new MyShareData();
	private int j=10;//线程共享数据
	public static void main(String[] args){
//		new Thread(new Runnable(){
//			public void run(){
//				data.dec();
//			}
//		}).start();
//		new Thread(new Runnable(){
//			public void run(){
//				data.inc();
//			}
//		}).start();
		MyThreadSVTest mt =  new MyThreadSVTest();
		new Thread(mt.new DecThread()).start();
		new Thread(mt.new IncThread()).start();
	}
	
	public synchronized void inc(){
		for(int i=0;i<10;i++){
			j++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" "+j);
		}
	}
	
	public synchronized void dec(){
		for(int i=0;i<10;i++){
			j--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" "+j);
		}
	}
	
	/**
	 * 实现加 1 操作的线程
	 * @author 
	 *
	 */
	class IncThread implements Runnable{

		@Override
		public void run() {
			inc();
		}
		
	}
	
	/**
	 * 实现减1 操作的线程
	 * @author 
	 */
	class DecThread implements Runnable{

		@Override
		public void run() {
			dec();
		}
		
	}

}

/**
 * 作为线程共享的外部类
 * @author zuohuai
 *
 */
class MyShareData{
	int j =9;;
	
	/**
	 * 对j 加 1 的操作
	 */
	public  void inc(){
		while(true){
			j--;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" "+ j);
		}
	}
	
	/**
	 *对j 减 1 的操作
	 */
	public  void dec(){
		while(true){
			j++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" "+ j);
		}
	}
}
