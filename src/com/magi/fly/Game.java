package com.magi.fly;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Game extends JPanel{
	
	int flying_cnt = 0;//���������
	
	//������Ϸ״̬
	int state = 0;
	public final int START = 0;
	public final int RUNNING = 1;
	public final int PAUSE = 0;
	public final int GAME_OVER = 0;
	
	List<Object_Fly> object_Flies = new ArrayList<>();.//����������ArrayList
	
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
		if(flying_cnt % 40 == 0) {
			flying_cnt++;
			Object_Fly ant = new Ant();
			object_Flies.add(ant);
		}
	}
	
}
//Game�ൽ�˽���