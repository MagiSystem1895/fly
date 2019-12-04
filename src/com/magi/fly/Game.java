package com.magi.fly;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel{
	
	int flying_cnt = 0;//控制飞行物生成速度
	int mybullet_cnt = 0;//控制蜜蜂的子弹射出速度
	
//-----------------------------------------------------------------------------------------------	
//设置游戏状态
	int state = 1;
	public final int START = 0;
	public final int RUNNING = 1;
	public final int PAUSE = 0;
	public final int GAME_OVER = 0;
	
//-----------------------------------------------------------------------------------------
//导入图片
	static BufferedImage antImage;
	static BufferedImage backgroundImage;
	static BufferedImage playerImage;
	static BufferedImage myBulletImage;
	static BufferedImage antBulletImage;

	static {
		try {
			antImage = ImageIO.read(new File("./imgs/ant.png"));
			backgroundImage = ImageIO.read(new File("./imgs/background.png"));
			playerImage = ImageIO.read(new File("./imgs/player.png"));
			myBulletImage = ImageIO.read(new File("./imgs/mybullet.png"));
			antBulletImage = ImageIO.read(new File("./imgs/antbullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//------------------------------------------------------------------------------------------------
//集合和Player对象
	List<Object_Fly> ant = new ArrayList<>();//储存小虫子的ArrayList
	List<MyBullet> myBullets = new ArrayList<>();
	List<AntBullet> antBullets = new ArrayList<>();
	Player player = new Player();
//---------------------------------------------------------------------------------------------	
//画图
	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(playerImage, player.x, player.y, null);
		for(int i=0; i<ant.size(); i++) {//打印小虫子
			g.drawImage(antImage,ant.get(i).x,ant.get(i).y,null);
		}
		for(int i=0; i<myBullets.size(); i++) {//打印自己的子弹
			g.drawImage(myBulletImage,myBullets.get(i).x,myBullets.get(i).y,null);
		}
		for(int i=0; i<antBullets.size(); i++) {//打印虫子的子弹
			g.drawImage(antBulletImage,antBullets.get(i).x,antBullets.get(i).y,null);
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
			public void mouseClicked(MouseEvent e) {
				switch(state) {
					case START:
						state = RUNNING;
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
					player.moving(x,y);
				}
			}

/*			public void mouseExited(MouseEvent e) {
				if(state != START) {
					System.out.println("游戏暂停");
					state = PAUSE;
				}
			}*/

			public void mouseEntered(MouseEvent e) {
				state = RUNNING;
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
				shootAction();
				bangAction();
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
			ant.add(new Ant());
		}
	}
	//飞行物添加方法到此结束
	private void stepAction() {
		for(int i=0; i<ant.size(); i++) {//虫子移动
//			Object_Fly fly = ant.get(i);
//			fly.move();
			ant.get(i).move();
		}
		for(int i=0; i<myBullets.size(); i++) {//玩家子弹移动
			myBullets.get(i).move();
		}
		for(int i=0; i<antBullets.size(); i++) {//虫子子弹移动
			antBullets.get(i).move();
		}
	}
//飞行物移动方法到此结束
	
	private void shootAction() {
		mybullet_cnt++;
		if(mybullet_cnt % 50 == 0) {
			myBullets.add(new MyBullet(player.x+10,player.y-20));
			
		}
		//玩家射击到此结束
		
		for(int i=0; i<ant.size(); i++) {
		Random random = new Random();
		int r = random.nextInt(100);
		if(mybullet_cnt % 80 == 0 && r<70) {
			Object_Fly a = ant.get(i);
			antBullets.add(new AntBullet(a.x+10,a.y+70));			
			}
		}
		//虫子射击到此结束	
	}
//射击方法到此结束
	
    public void bangAction() {
    	for(int i=0; i<myBullets.size(); i++) {
    		MyBullet myBullet = myBullets.get(i);
    		bang(myBullet);
    		
    	}
    }
 //遍历子弹方法结束

    public void bang(MyBullet myBullet) {
    	int flag = -1;
    	for(int i=0; i<ant.size(); i++) {
    		if(ant.get(i).shootBy(myBullet)) {
    			flag = i;
    			myBullets.remove(myBullets);
    			break;
    		}
    	}
    	
    	if(flag != -1) {
    		ant.remove(flag);
    	}
    }
//bang方法结束    
    

}
//Game类到此结束



















