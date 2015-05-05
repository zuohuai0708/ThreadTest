package com.cusoft.callableAndFuture;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池、Callable 、Future、CompleteService 的使用
 * @author 
 *
 */
public class CallableAndFutureTest {
	
	public static void main(String[] args){
		ExecutorService theadPool = Executors.newFixedThreadPool(3) ;
		Future<Object> future = theadPool.submit(new Callable<Object>(){
			@Override
			public Object call(){
				try {
					Thread.sleep(new Random().nextInt(5000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "hello";
			}
		});
		System.out.println("准备拿结果……");
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		CompletionService<Object> completionServcie = new ExecutorCompletionService<Object>(theadPool);
		for(int i=1;i<=10;i++){
			final int j = i;
			completionServcie.submit(new Callable<Object>(){
				@Override
				public Object call(){
					try {
						Thread.sleep(new Random().nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return "任务返回的结果是："+j;
				}
			});
		}
		for(int i=1;i<=10;i++){
			;
			try {
				System.out.println(completionServcie.take().get());
			} catch (InterruptedException e) {
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		theadPool.shutdown();
	}
}
