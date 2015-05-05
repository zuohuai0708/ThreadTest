package com.cusoft.clone;

//枚举是引用类型，
public enum Sex {
	// 该定义实际等价于 Sex MALE = new Sex(1)
	MALE(1), 
	FEMALE(2);

	private int code = 0;

	private Sex(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
