package com.magi.fly;

import java.util.Random;

public class Ant extends Object_Fly{
	
	int moveSpeed = 2;
	
      	Ant(){		
		photo = Game.antImage;
		width = photo.getWidth();
		height = photo.getHeight();
		Random r = new Random();
		x = r.nextInt(50);
		y = r.nextInt(450);
	}

	public void move() {
		x+= moveSpeed;
	}
	
}