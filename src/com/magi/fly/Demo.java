package com.magi.fly;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Demo {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		Game game = new Game();
//		Button bt = new Button("开始");
//		jFrame.add(bt);
		jFrame.add(game);
//		bt.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				game.play();
//			}
//		});
		//三板斧
		jFrame.setSize(400, 1000); // 设置大小
		jFrame.setAlwaysOnTop(true); // 设置其总在最上
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作
		jFrame.setLocationRelativeTo(null); // 设置窗体初始位置，null相当于默认在最中间,同时在底层调用JFrame的paint()方法
		jFrame.setVisible(true); // 显示窗口
		game.play(); // 启动执行
	}
}
