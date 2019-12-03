package com.magi.fly;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Game extends JPanel{

	//设置游戏状态
	int state = 0;
	public final int START = 0;
	public final int RUNNING = 1;
	public final int PAUSE = 0;
	public final int GAME_OVER = 0;
		
	public void play() {
		MouseAdapter mouseAdapter = new MouseAdapter() {
			//点击才会开始
			public void mouseClicked(MouseEvent e) {
				switch(state) {
					case START:
						state = RUNNING;
						System.out.println("游戏开始");
						break;
					case RUNNING:
						break;
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state == RUNNING) {
					int x = e.getX();
					int y = e.getY();
					Player.moving(x,y);
					System.out.println("x"+x+"  "+"y"+y);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(state != START) {
					System.out.println("游戏暂停");
					state = PAUSE;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				state = START;
			}
		};
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
	//play方法的	
	}

}
