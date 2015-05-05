package com.cusoft.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * �Զ���annotation ,annotaion�ı����ǽӿ� ,ֻ�� ������������ string class annotation 
 * @author jy
 *
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.CONSTRUCTOR,ElementType.TYPE} ) //annotation�������� �࣬������������
@Retention(RetentionPolicy.CLASS)
public @interface MyAnnotation {
	String name() ; //���巽��
	
	Student student(); //
}
