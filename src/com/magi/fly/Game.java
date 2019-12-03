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
	
	int flying_cnt = 0;//���������
	
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

	static BufferedImage ant;
//-----------------------------------------------------------------------------------------------
//����
	List<Object_Fly> flyings = new ArrayList<>();//����������ArrayList
//---------------------------------------------------------------------------------------------	
//���ؾ�̬��Դ
	static {
		try {
			ant = ImageIO.read(new File("./imgs/ant.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//------------------------------------------------------------------------------------------------
//��ͼ
	public void paint(Graphics g) {
		for(int i =0; i<flyings.size(); i++) {
			g.drawImage(flyings.get(i).getPhoto(),flyings.get(i).x,flyings.get(i).y,null);
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
				System.out.println("�һ�����");
				enterAction();
				repaint();
			}
		};
		timer.schedule(timerTask, 1000,1000);
	}
	//play�������˽���
	private void enterAction() {
		//1.�жϷ���������
		//2.�������������
		//3.��������뵽ArrayListĩβ
		//flying_cnt:���������
		//objed_Filese:�����Ｏ��
		System.out.println("���ǲ�������");
/*		if(flying_cnt % 40 == 0) {
		}*/
		flying_cnt++;
		Object_Fly ant = new Ant();
		flyings.add(ant);
	}
	
}
//Game�ൽ�˽���