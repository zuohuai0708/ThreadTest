package com.cusoft.clone;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class CloneTest {

	@Test
	public void test_clone() throws Exception {
		Message message = Message.valueOf(1, "工人");
		Person p1 = Person.valueOf("张三", 10, Sex.MALE, message);

		Person p2 = Person.class.cast(p1.clone());

		// 克隆出来的对象不是同一个对象
		Assert.assertThat(p1 == p2, Is.is(false));
		// 基础数据类型，自动复制
		Assert.assertThat(p2.getAge(), Is.is(p1.getAge()));

		// 引用类型(包括枚举类型),指向的是同一个对象
		Assert.assertThat(p1.getSex() == p2.getSex(), Is.is(true));
		Assert.assertThat(p2.getName(), Is.is(p1.getName()));
		Assert.assertThat(p2.getName() == p1.getName(), Is.is(true));
		Assert.assertThat(p1.getMessage() == p2.getMessage(), Is.is(true));

	}

	public void test_deep_clone() throws Exception {

	}
}
