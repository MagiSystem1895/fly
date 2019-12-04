package com.magi.fly;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel{
	
	int flying_cnt = 0;//控制飞行物生成速度
	
//-----------------------------------------------------------------------------------------------	
//设置游戏状态
	int state = 0;
	public final int START = 0;
	public final int RUNNING = 1;
	public final int PAUSE = 0;
	public final int GAME_OVER = 0;
	
//-----------------------------------------------------------------------------------------
//导入图片
//BufferedImage为什么要用静态我也不知道，大概是因为他的对象ant在static里吧,静态变量可以通过类名直接调用。
//其实他的对象ant为什么要在static里我也不知道,是因为要优先于主函数加载？？？
//关于ImageIO.read()里为什么是new File我也不知道，时间紧迫，先照葫芦画瓢吧。。。

	static BufferedImage antImage;
	static BufferedImage background;
//-----------------------------------------------------------------------------------------------
//集合
	List<Object_Fly> ant = new ArrayList<>();//储存小虫子的ArrayList
//---------------------------------------------------------------------------------------------	
//加载静态资源
	static {
		try {
			antImage = ImageIO.read(new File("./imgs/ant.png"));
			background = ImageIO.read(new File("./imgs/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//------------------------------------------------------------------------------------------------
//画图
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		for(int i =0; i<ant.size(); i++) {
			g.drawImage(ant.get(i).getPhoto(),ant.get(i).x,ant.get(i).y,null);
		}


	}
//----------------------------------------------------------------------------------------------------
//灵魂
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
				stepAction();
				repaint();
			}
		};
		timer.schedule(timerTask, 10,10);

	}
	//play方法到此结束
	private void enterAction() {
		//1.判断飞行物数量
		//2.创建飞行物对象
		//3.将对象加入到ArrayList末尾
		//flying_cnt:飞行物个数
		//objed_Filese:飞行物集合
		flying_cnt++;
		if(flying_cnt % 40 == 0) {
			Object_Fly fly = new Ant();
			ant.add(fly);
		}
	}
	//飞行物添加方法到此结束
	private void stepAction() {
		for(int i=0; i<ant.size(); i++) {
			Object_Fly fly = ant.get(i);
			fly.move();
		}
	}
	
}
//Game类到此结束