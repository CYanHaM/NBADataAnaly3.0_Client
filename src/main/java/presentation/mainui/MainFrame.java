package presentation.mainui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

//import blservice.playerinfoblservice.PlayerInfoService;
//import blservice.playertechblservice.ShowPlayerTechService;
//import blservice.teamblservice.TeamBLservice;
//import blservice.teamtechblservice.TeamTechBLService;
//import bussinesslogic.TeamBL.Team;
//import bussinesslogic.TeamTech.TeamTech;
//import bussinesslogic.playerinfobl.PlayerInfo;
//import bussinesslogic.playertechbl.ShowPlayerTech;

//import com.sun.awt.AWTUtilities;

public class MainFrame extends JFrame{
	/**
	 * 系统主界面，包括界面大小，布局方式，鼠标点击拖动事件
	 * @author blisscry
	 * @date 2015年4月27日19:38:56
	 * @version 2.0
	 */

	private static final long serialVersionUID = 1L;
	//定义主框架大小
	public static int FRAME_WIDTH=1020;
	public static int FRAME_HEIGHT=670;
	//定义鼠标坐标位置
	int X;
	int Y;
	//判断是否在拖动界面
	boolean isDraging;

	JButton MINIMIZE;
	JButton CLOSE;
	JFrame Frame;

	public MainFrame(){
		//定义整个界面大小
		this.setLayout(null);
		this.setTitle("CYan HaM");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		//不显示windows自带边框
		this.setUndecorated(true);
		//设置窗体居中
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		Frame=this;

		//窗体透明（此处引用了com.sun.awt.AWTUtilities，需引包）
		//AWTUtilities.setWindowOpaque(this, false);
		//窗体透明方法目前得到改进
		this.setBackground(new Color(0,0,0,0));
		

		//鼠标事件，用于获取鼠标拖动的位置
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				isDraging = true;
				X= e.getX();
				Y= e.getY();
			}
			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}});
		//鼠标移动事件，用于获取移动的路径长度
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDraging) { 
					//获取当前框架的位置坐标
					int frame_x= getLocation().x;
					int frame_y= getLocation().y;
					setLocation(frame_x+e.getX()-X, frame_y+e.getY()-Y);
				}
			}});


		LoadingPanel loadingpanel=new LoadingPanel(this);
		this.add(loadingpanel);
		this.repaint();
	}



	public static void main(String[] args){

		//加载系统界面
		try {
			// 将LookAndFeel设置成Windows样式
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/*
		 * 迭代1所使用的登录界面，迭代2取消了RMI，将之删去
		 * MessageFrame messageframe=new MessageFrame();
		 * LoginMsgPanel loginmsgpanel=new LoginMsgPanel(messageframe);
		 * messageframe.add(loginmsgpanel);
		 * messageframe.repaint();
		 */
//		TeamBLservice tbs=new Team();
//		tbs.init();
//		PlayerInfoService pis=new PlayerInfo();
//		pis.PlayerInfoIni();
//		ShowPlayerTechService ppts=new ShowPlayerTech();
//		ppts.PlayerTechIni();
//		TeamTechBLService ttbs=new TeamTech();
//		ttbs.init();
		
		
		MainFrame mf=new MainFrame();
//		MatchPanel mp=new MatchPanel(mf);
//		TeamInfoPanel mp=new TeamInfoPanel(mf);
//		HotPlayerToday mp=new HotPlayerToday(mf);
//		SeasonHotPlayer mp=new SeasonHotPlayer(mf);
//		mf.add(mp);
		mf.repaint();
	}

}
