package com.magi.fly;

import javax.swing.JFrame;

public class Demo {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		Game game = new Game();
		jFrame.add(game);
		//���師
		jFrame.setSize(400, 1000); // ���ô�С
		jFrame.setAlwaysOnTop(true); // ��������������
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ĭ�Ϲرղ���
		jFrame.setLocationRelativeTo(null); // ���ô����ʼλ�ã�null�൱��Ĭ�������м�,ͬʱ�ڵײ����JFrame��paint()����
		jFrame.setVisible(true); // ��ʾ����
		game.play(); // ����ִ��
	}
}
