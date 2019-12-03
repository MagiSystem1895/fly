package com.magi.fly;

import java.util.Random;

public class Ant extends Object_Fly{
	
	Ant(){
		System.out.println("我是虫子的构造函数");
		
		photo = Game.ant;
		int width = photo.getWidth();
		int height = photo.getHeight();
		x = 0;
		Random r = new Random();
		int random = r.nextInt(450);
		System.out.println(random);
		y = random;
	}
}