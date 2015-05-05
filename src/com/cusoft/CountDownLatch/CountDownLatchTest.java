package com.cusoft.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 犹如倒计时计数器，调用CountDownLatch对象的countDown方法就将计数器减1，
 * 当计数到达0时，则所有等待者或单个等待者开始执行。
 * 这直接通过代码来说明CountDownLatch的作用，这样学员的理解效果更直接。
	可以实现一个人（也可以是多个人）等待其他所有人都来通知他，
	可以实现一个人通知多个人的效果，类似裁判一声口令，运动员同时开始奔跑，
	或者所有运动员都跑到终点后裁判才可以公布结果，用这个功能做百米赛跑的游戏程序不错哦！
	还可以实现一个计划需要多个领导都签字后才能继续向下实施的情况。
 * @author zuohuai
 *
 */
public class CountDownLatchTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);		
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						System.out.println("线程" + Thread.currentThread().getName() + 
								"正准备接受命令");						
						cdOrder.await();
						System.out.println("线程" + Thread.currentThread().getName() + 
						"已接受命令");								
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"回应命令处理结果");						
						cdAnswer.countDown();						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}		
		try {
			Thread.sleep((long)Math.random()*100000);
		
			System.out.println("线程" + Thread.currentThread().getName() + 
					"即将发布命令");						
			cdOrder.countDown();
			System.out.println("线程" + Thread.currentThread().getName() + 
			"已发送命令，正在等待结果");	
			cdAnswer.await();
			System.out.println("线程" + Thread.currentThread().getName() + 
			"已收到所有响应结果");	
		} catch (Exception e) {
			e.printStackTrace();
		}				
		service.shutdown();
	}
}
