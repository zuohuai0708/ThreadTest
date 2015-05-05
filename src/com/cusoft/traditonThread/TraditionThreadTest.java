package com.cusoft.traditonThread;

/**
 * 传统创建线程的2种方式
 * Thread 执行分析 ，在没有覆盖Thread方法时，如果没有覆盖
 * @author 
 *
 */
public class TraditionThreadTest {

	public static void main(String[] args){
		/**
		 * Thread 的子类
		 */
		Thread t1 = new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(500);
						System.out.println("1:"+Thread.currentThread().getName());
						System.out.println("1:"+this.getName()+":***");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
		
		/**
		 *实现Runnable接口
		 */
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					try {
						Thread.sleep(500);
						System.out.println("2:"+Thread.currentThread().getName());
						//System.out.println("1:"+this.getName()+":***");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t2.start();
		
		/**
		 * 运行的是Thread子类中的run()方法
		 * 而不是Runnable中的run()方法
		 */
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
						System.out.println("Runnable:"+Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}}){
			public void run(){
				while(true){
					try {
						Thread.sleep(500);
						System.out.println("Thread:"+Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
}
