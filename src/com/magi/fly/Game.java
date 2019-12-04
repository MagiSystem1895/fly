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
	
	int flying_cnt = 0;//���Ʒ����������ٶ�
	int mybullet_cnt = 0;//�����۷���ӵ�����ٶ�
	
//-----------------------------------------------------------------------------------------------	
//������Ϸ״̬
	int state = 1;
	public final int START = 0;
	public final int RUNNING = 1;
	public final int PAUSE = 0;
	public final int GAME_OVER = 0;
	
//-----------------------------------------------------------------------------------------
//����ͼƬ
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
//���Ϻ�Player����
	List<Object_Fly> ant = new ArrayList<>();//����С���ӵ�ArrayList
	List<MyBullet> myBullets = new ArrayList<>();
	List<AntBullet> antBullets = new ArrayList<>();
	Player player = new Player();
//---------------------------------------------------------------------------------------------	
//��ͼ
	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(playerImage, player.x, player.y, null);
		for(int i=0; i<ant.size(); i++) {//��ӡС����
			g.drawImage(antImage,ant.get(i).x,ant.get(i).y,null);
		}
		for(int i=0; i<myBullets.size(); i++) {//��ӡ�Լ����ӵ�
			g.drawImage(myBulletImage,myBullets.get(i).x,myBullets.get(i).y,null);
		}
		for(int i=0; i<antBullets.size(); i++) {//��ӡ���ӵ��ӵ�
			g.drawImage(antBulletImage,antBullets.get(i).x,antBullets.get(i).y,null);
		}


	}
//----------------------------------------------------------------------------------------------------
//���
	public void play() {									
		//1.��д����������ķ���
		//2.�������������
		//3.��дTimerTask
		//4.����Timer����
		//5.����Timer��schedule����
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
					System.out.println("��Ϸ��ͣ");
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
				//1.�������볡
				//2.�ƶ�
				//3.���
				//4.�ӵ��������
			//5.ɾ��Խ���������ӵ�
				//6.������
				enterAction();
				stepAction();
				shootAction();
				bangAction();
				repaint();
							
			}
		};
		timer.schedule(timerTask, 10,10);
	}
	//play�������˽���
	private void enterAction() {
		//1.�жϷ���������
		//2.�������������
		//3.��������뵽ArrayListĩβ
		//flying_cnt:���������
		//objed_Filese:�����Ｏ��      
		flying_cnt++;
		if(flying_cnt % 40 == 0) {
			ant.add(new Ant());
		}
	}
	//��������ӷ������˽���
	private void stepAction() {
		for(int i=0; i<ant.size(); i++) {//�����ƶ�
//			Object_Fly fly = ant.get(i);
//			fly.move();
			ant.get(i).move();
		}
		for(int i=0; i<myBullets.size(); i++) {//����ӵ��ƶ�
			myBullets.get(i).move();
		}
		for(int i=0; i<antBullets.size(); i++) {//�����ӵ��ƶ�
			antBullets.get(i).move();
		}
	}
//�������ƶ��������˽���
	
	private void shootAction() {
		mybullet_cnt++;
		if(mybullet_cnt % 50 == 0) {
			myBullets.add(new MyBullet(player.x+10,player.y-20));
			
		}
		//���������˽���
		
		for(int i=0; i<ant.size(); i++) {
		Random random = new Random();
		int r = random.nextInt(100);
		if(mybullet_cnt % 80 == 0 && r<70) {
			Object_Fly a = ant.get(i);
			antBullets.add(new AntBullet(a.x+10,a.y+70));			
			}
		}
		//����������˽���	
	}
//����������˽���
	
    public void bangAction() {
    	for(int i=0; i<myBullets.size(); i++) {
    		MyBullet myBullet = myBullets.get(i);
    		bang(myBullet);
    		
    	}
    }
 //�����ӵ���������

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
//bang��������    
    

}
//Game�ൽ�˽���



















