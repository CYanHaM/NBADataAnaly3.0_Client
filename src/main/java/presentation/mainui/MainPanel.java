package presentation.mainui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import presentation.teamui.TeamInfoPanel;
import presentation.teamui.TeamTechPanel;

public class MainPanel extends JPanel implements ActionListener{
	/**
	 * 主界面最小化及关闭按钮以及背景设置
	 * @author blisscry
	 * @date 2015年3月20日21:00:56
	 * @version 1.3
	 */
	
	private static final long serialVersionUID = 1L;
	
	//定义主框架大小
	public static int WIDTH=1100;
	public static int HEIGHT=700;
	//定义边缘透明空白区域边界大小，单位px
	public static int e_space=10;
	//关闭与最小化按钮必须是正方形，定义边长大小
	public static int sidelength=25;
	//侧边栏按钮
	private JButton SeasonInfo;
	private JButton MatchInfo;
	private JButton TeamInfo;
	private JButton PlayerInfo;
	private JButton Hot;
	
	private boolean stop=false;
	private JLabel[] effect;
		
	JButton MINIMIZE;
	JButton CLOSE;
	JFrame Frame;
	
	public MainPanel(JFrame frame){
		Frame=frame;
//		selectedbutton=buttonname;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		this.setOpaque(false);
		
		//-----------------------添加最小化及关闭按钮-------------------------------
		MINIMIZE=new JButton(new ImageIcon("images/system_img/MINIMIZE_1.png"));
		MINIMIZE.setBounds(WIDTH-e_space-sidelength*2+e_space-1 , 1 , sidelength , sidelength);
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
		CLOSE.setBounds(WIDTH-e_space-sidelength+e_space-1 , 1 , sidelength , sidelength);
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
		addbutton();
	}
	
	
	private void addbutton(){
		SeasonInfo=new JButton(new ImageIcon("images/system_img/graph_1.png"));
		sideButton_config(SeasonInfo, "graph", 0);
		
		MatchInfo=new JButton(new ImageIcon("images/system_img/match_1.png"));
		sideButton_config(MatchInfo, "match", 1);
		
		TeamInfo=new JButton(new ImageIcon("images/system_img/team_1.png"));
		sideButton_config(TeamInfo, "team", 2);
		TeamInfo.setSelected(true);
		
		PlayerInfo=new JButton(new ImageIcon("images/system_img/player_1.png"));
		sideButton_config(PlayerInfo, "player", 3);
		
		Hot=new JButton(new ImageIcon("images/system_img/hot_1.png"));
		sideButton_config(Hot, "hot", 4);
	}
	
	private void sideButton_config(final JButton button,final String info,int count){
		button.setBounds(0, 145+45*count, 140, 45);
		
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setRolloverIcon(new ImageIcon("images/system_img/"+info+"_2.png"));
		button.setPressedIcon(new ImageIcon("images/system_img/"+info+"_3.png"));
		button.setSelectedIcon(new ImageIcon("images/system_img/"+info+"_3.png"));
		button.addActionListener(this);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				stop=false;
				
				Thread show=new Thread(){
					public void run(){
						for(int i=0;i<19;i++){
							if(!stop){
							button.setRolloverIcon(new ImageIcon("images/system_img/"+info+"/"+info+"_"+i+".png"));
							try {
								Thread.sleep(25);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							}
						}
					}
				};
				show.start();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				stop=true;
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				stop=true;
			}
		});
		this.add(button);
	}
	
	public void paintComponent(final Graphics g){
		super.paintComponents(g);
		ImageIcon im1=new ImageIcon("images/system_img/frame_bg-2.png");
		g.drawImage(im1.getImage(),0,0,this);
	}
	
	public void jumpToPanel(JPanel panel){
		
		final JPanel panelToJump=panel;
		final JPanel panelToRemove=(JPanel) Frame.getContentPane().getComponent(1);
		Frame.add(panelToJump);
		Thread switchpanel=new Thread(){
			public void run(){
				int i=0;
				while(i<=11){
				panelToRemove.setLocation(-100*i, 0);
				panelToJump.setLocation(WIDTH-100*i, 0);
				Frame.repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				}
				Frame.remove(panelToRemove);
				
			}
		};
		switchpanel.start();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==SeasonInfo){
			SeasonInfo.setSelected(true);
			MatchInfo.setSelected(false);
			TeamInfo.setSelected(false);
			PlayerInfo.setSelected(false);
			Hot.setSelected(false);
			TeamTechPanel newpanel=new TeamTechPanel(Frame);
			jumpToPanel(newpanel);
		}
		if(arg0.getSource()==MatchInfo){
			SeasonInfo.setSelected(false);
			MatchInfo.setSelected(true);
			TeamInfo.setSelected(false);
			PlayerInfo.setSelected(false);
			Hot.setSelected(false);
		}
		if(arg0.getSource()==TeamInfo){
			SeasonInfo.setSelected(false);
			MatchInfo.setSelected(false);
			TeamInfo.setSelected(true);
			PlayerInfo.setSelected(false);
			Hot.setSelected(false);
			TeamInfoPanel newpanel=new TeamInfoPanel(Frame);
			jumpToPanel(newpanel);
		}
		if(arg0.getSource()==PlayerInfo){
			SeasonInfo.setSelected(false);
			MatchInfo.setSelected(false);
			TeamInfo.setSelected(false);
			PlayerInfo.setSelected(true);
			Hot.setSelected(false);
		}
		if(arg0.getSource()==Hot){
			SeasonInfo.setSelected(false);
			MatchInfo.setSelected(false);
			TeamInfo.setSelected(false);
			PlayerInfo.setSelected(false);
			Hot.setSelected(true);
		}
	}
}