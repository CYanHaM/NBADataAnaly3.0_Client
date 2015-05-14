package presentation.mainui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainPanel extends JPanel{
	/**
	 * 主界面最小化及关闭按钮以及背景设置
	 * @author blisscry
	 * @date 2015年3月20日21:00:56
	 * @version 1.3
	 */
	private static final long serialVersionUID = 1L;
	
	//定义边缘透明空白区域边界大小，单位px
	public static int e_space=10;
	//关闭与最小化按钮必须是正方形，定义边长大小
	public static int sidelength=25;
	JButton MINIMIZE;
	JButton CLOSE;
	JFrame Frame;
	
	public MainPanel(JFrame frame,int WIDTH,int HEIGHT){
		Frame=frame;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		this.setOpaque(false);
		
		//-----------------------添加最小化及关闭按钮-------------------------------
		MINIMIZE=new JButton(new ImageIcon("images/system_img/MINIMIZE_1.png"));
		MINIMIZE.setBounds(WIDTH-e_space-sidelength*2 , e_space , sidelength , sidelength);
		MINIMIZE.setBorderPainted(false);
		MINIMIZE.setContentAreaFilled(false);
		MINIMIZE.setFocusPainted(false);
		MINIMIZE.setRolloverIcon(new ImageIcon("images/system_img/MINIMIZE_2.png"));
		MINIMIZE.setPressedIcon(new ImageIcon("images/system_img/MINIMIZE_3.png"));
		MINIMIZE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Frame.setExtendedState(JFrame.ICONIFIED);
			}
		});

		CLOSE=new JButton(new ImageIcon("images/system_img/CLOSE_1.png"));
		CLOSE.setBounds(WIDTH-e_space-sidelength , e_space , sidelength , sidelength);
		CLOSE.setBorderPainted(false);
		CLOSE.setContentAreaFilled(false);
		CLOSE.setFocusPainted(false);
		CLOSE.setRolloverIcon(new ImageIcon("images/system_img/CLOSE_2.png"));
		CLOSE.setPressedIcon(new ImageIcon("images/system_img/CLOSE_3.png"));
		CLOSE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Frame.dispose();
			}
		});


		this.add(MINIMIZE);
		this.add(CLOSE);
	}
}