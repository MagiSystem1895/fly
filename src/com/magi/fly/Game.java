package com.magi.fly;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Game extends JPanel{
	
	int flying_cnt = 0;//飞行物个数
	
	//设置游戏状态
	int state = 0;
	public final int START = 0;
	public final int RUNNING = 1;
	public final int PAUSE = 0;
	public final int GAME_OVER = 0;
	
	List<Object_Fly> object_Flies = new ArrayList<>();.//储存飞行物的ArrayList
	
	public void play() {
		//1.覆写鼠标适配器的方法
		//2.添加鼠标监听对象
		//3.覆写TimerTask
		//4.创建Timer对象
		//5.调用Timer的schedule方法
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
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				//1.飞行物入场
				//2.移动
				//3.射击
				//4.子弹打飞行物
				//5.删除越界飞行物和子弹
				//6.检查结束
				enterAction();
			}
		};
		timer.schedule(timerTask, 1000,1000);
	}
	//play方法到此结束
	private void enterAction() {
		//1.判断飞行物数量
		//2.创建飞行物对象
		//3.将对象加入到ArrayList末尾
		//flying_cnt:飞行物个数
		//objed_Filese:飞行物集合
		if(flying_cnt % 40 == 0) {
			flying_cnt++;
			Object_Fly ant = new Ant();
			object_Flies.add(ant);
		}
	}
	
}
//Game类到此结束