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

	public boolean shootByAnt(AntBullet antBullet) {
		int x = antBullet.x;
		int y = antBullet.y;
		return x>this.x && x<this.x+this.width && y>this.y && y<this.y+this.height;
	}

}
