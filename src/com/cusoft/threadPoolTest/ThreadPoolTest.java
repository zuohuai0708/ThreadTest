package com.cusoft.threadPoolTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	
	public static void main(String[] args){
		/**
		 * 如何实现线程死了，在启动一个，可以创建一个单一线程的线程池，或者使用守护线程
		 */
//		ExecutorService  threadPool1 = Executors.newSingleThreadExecutor();当大小为1 的时候，始终有一个线程在线程池中 
		ExecutorService  threadPool = Executors.newFixedThreadPool(3); //固定 线程的数量 
//		ExecutorService  threadPool = Executors.newCachedThreadPool(); //随着任务的多少，线程池中的线程数量递增
		for(int i=1;i<=10;i++){
			threadPool.execute(new Runnable(){
				@Override
				public void run(){
					for(int j=1;j<=10;j++){
						try {
							Thread.sleep(10);
							System.out.println(Thread.currentThread().getName()+" is loop of "+j);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		System.out.println("all of 10 task has commited ! ");
		threadPool.shutdown(); //如果还有任务在运行，等到任务运行结束之后，关闭线程池
//		threadPool.shutdownNow() ; //立刻停止线程池
		
		/**
		 * 定时器
		 */
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable(){
			@Override
			public void run(){
				System.out.println("bomb ! ");
			}
		}, 10,2,TimeUnit.SECONDS);
	}
}
