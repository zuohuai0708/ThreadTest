package com.cusoft.synchronizedTraditionnal;

/**
 * 线程的原子性
 * 关于传统线程互斥  使用synchronized 关键字
 * 要进行互斥效果的时候，必须使用同一个锁对象
 * @author 
 *
 */
public class SynchronizedTraditional {
//	private static int a = 0; 
	public static void main(String[] args){
		new SynchronizedTraditional().init();
	}
	
	public void init(){
		 final Outputer outputer = new Outputer(); 
		//outputer.output1("");
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					try {
						Thread.sleep(10);
						outputer.output1("Jacking");
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
						outputer.output3("zhangzuohuai");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	/**
	 * 非内部类 可以调用外部类 的所有 非静态成员
	 * @author 
	 *
	 */
	 static class Outputer {
		/**
		 * 方式一
		 * 代码段上加入synchronized 
		 * 如果不加synchronized 就会出现“
		 * 混乱 的结果
		 * @param name
		 */
		private void output1(String name){
			//System.out.println(a);
			int length = name.length();
			synchronized (/*this*/Outputer.class) {
				for(int i=0;i<length;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
		
		/**
		 * 方法二
		 * 方法前加入synchronized ，和方法一可以互斥，
		 * 因为是使用的都是 同一把锁（this 对象）
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
		 * 静态方法前加入synchronized ，和方法一可以互斥，
		 * 因为是使用的都是 同一把锁（Outputer.class  字节码对象）
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
