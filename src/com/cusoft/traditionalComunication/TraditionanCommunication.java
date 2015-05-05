package com.cusoft.traditionalComunication;

/**
 * 实现主线程运行50次 执行50次，
 * 子线程运行20次 执行20次，之后在交替执行
 * 一共交替执行
 * 从这个程序中学习到高内聚，把相关联的方式封装成一个类
 *  synchronized（锁）一般是放在需要访问的资源上的 
 * @author 
 *
 */
public class TraditionanCommunication {

	public static void main(String[] args)
	{
		final Bussiness bussiness = new Bussiness();
		/*
		 * 子线程
		 */
		new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i=1;i<=50;i++){
					bussiness.sub(i);
				}
			}
		}).start();
		
		/*
		 * 主线程
		 */
		for(int i=1;i<=50;i++){
			bussiness.main(i);
		}
	}
}

/**
 * 业务类
 * @author 
 *
 */
class Bussiness{
	private boolean beShouldSub = true;
	synchronized public void sub(int i){
		//使用while，替代if 可以避免被假唤醒的情况 
		while(!beShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int j=1;j<=20;j++){
			System.out.println("sub "+i+"的sequence" +j+"+++++++++++++++++++++++");
		}
		beShouldSub = false;
		this.notify(); //通知一个在wait 的线程
	}
	synchronized public void main(int i){
		//使用while，替代if 可以避免被假唤醒的情况
		while(beShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int j=1;j<=100;j++){
			System.out.println("main "+i+"的sequence" +j);
		}
		beShouldSub = true;
		this.notify();
	}
}


