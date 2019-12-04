package com.magi.fly;

public class Player extends Object_Fly{
	
	Player(){
		photo = Game.playerImage;
		width = photo.getWidth();
		height = photo.getHeight();
		x = 175;
		y = 950;	
	}
	
	public void moving(int x, int y) {
		this.x = x - width/2;
		this.y = y - height/2;
	}

}
