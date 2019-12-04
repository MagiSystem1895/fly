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
	
	int flying_cnt = 0;//���Ʒ����������ٶ�
	
//-----------------------------------------------------------------------------------------------	
//������Ϸ״̬
	int state = 0;
	public final int START = 0;
	public final int RUNNING = 1;
	public final int PAUSE = 0;
	public final int GAME_OVER = 0;
	
//-----------------------------------------------------------------------------------------
//����ͼƬ
//BufferedImageΪʲôҪ�þ�̬��Ҳ��֪�����������Ϊ���Ķ���ant��static���,��̬��������ͨ������ֱ�ӵ��á�
//��ʵ���Ķ���antΪʲôҪ��static����Ҳ��֪��,����ΪҪ���������������أ�����
//����ImageIO.read()��Ϊʲô��new File��Ҳ��֪����ʱ����ȣ����պ�«��ư�ɡ�����

	static BufferedImage antImage;
	static BufferedImage background;
//-----------------------------------------------------------------------------------------------
//����
	List<Object_Fly> ant = new ArrayList<>();//����С���ӵ�ArrayList
//---------------------------------------------------------------------------------------------	
//���ؾ�̬��Դ
	static {
		try {
			antImage = ImageIO.read(new File("./imgs/ant.png"));
			background = ImageIO.read(new File("./imgs/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//------------------------------------------------------------------------------------------------
//��ͼ
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		for(int i =0; i<ant.size(); i++) {
			g.drawImage(ant.get(i).getPhoto(),ant.get(i).x,ant.get(i).y,null);
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
			//����ŻῪʼ
			public void mouseClicked(MouseEvent e) {
				switch(state) {
					case START:
						state = RUNNING;
						System.out.println("��Ϸ��ʼ");
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
					System.out.println("��Ϸ��ͣ");
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
				//1.�������볡
				//2.�ƶ�
				//3.���
				//4.�ӵ��������
				//5.ɾ��Խ���������ӵ�
				//6.������
				enterAction();
				stepAction();
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
			Object_Fly fly = new Ant();
			ant.add(fly);
		}
	}
	//��������ӷ������˽���
	private void stepAction() {
		for(int i=0; i<ant.size(); i++) {
			Object_Fly fly = ant.get(i);
			fly.move();
		}
	}
	
}
//Game�ൽ�˽���