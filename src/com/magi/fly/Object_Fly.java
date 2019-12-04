package com.magi.fly;

import java.awt.image.BufferedImage;

public abstract class Object_Fly {
	int x;//ºáÖá×ø±ê
	int y;//×ÝÖá×ø±ê
	int width;
	int height;
	
	BufferedImage photo;
	
    public BufferedImage getPhoto() {
        return photo;
    }

	public void move() {
		
	}
	
	public void shootBy() {
		
	}

	public boolean shootBy(MyBullet myBullet) {
		int x = myBullet.x;
		int y = myBullet.y;
		return x>this.x && x<this.x+this.width && y>this.y && y<this.y+this.height;
	}
}
