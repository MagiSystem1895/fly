package com.magi.fly;

public class MyBullet extends Object_Fly{

	public MyBullet(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move() {
		this.y = y - 1;
	}

}
