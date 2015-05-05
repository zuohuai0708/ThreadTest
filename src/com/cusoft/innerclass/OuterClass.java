package com.cusoft.innerclass;

/**
 * 内部类和外部类的测试
 * @author zuohuai
 *
 */
public class OuterClass {
	public static void main(String[] args){
		
	}
	
	public void init(){
		InnerClass innerClass =  new InnerClass();
	}
	
	class InnerClass{
		public void outer(){
			System.out.println("I'm a innerClass !! ");
		}
	}
}

