package com.cusoft.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义annotation ,annotaion的本质是接口 ,只能 基本数据类型 string class annotation 
 * @author jy
 *
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.CONSTRUCTOR,ElementType.TYPE} ) //annotation的作用域 类，方法，参数上
@Retention(RetentionPolicy.CLASS)
public @interface MyAnnotation {
	String name() ; //定义方法
	
	Student student(); //
}
