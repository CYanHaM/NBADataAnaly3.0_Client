package presentation.teamui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.hotspotui.HotPlayerToday;
import presentation.matchui.MatchPanel;
import presentation.playerui.PlayerTechPanel;
import presentation.preset.PlayerPre;
//import VO.TeamVO;

public class TeamInfoPanel extends JPanel implements ActionListener{
	/**
	 * 球员信息显示界面
	 * @author blisscry
	 * @date 2015年4月1日00:55:39
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH=1100;
	public static int HEIGHT=700;
	
	private static int locx=210;
	private static int locy=150;
	private static int Button_width=193;
	private static int Button_height=85;
	
	//东部联盟
	private JButton ATL;
	private JButton CHA;
	private JButton MIA;
	private JButton ORL;
	private JButton WAS;
	
	private JButton CHI;
	private JButton CLE;
	private JButton DET;
	private JButton IND;
	private JButton MIL;
	
	private JButton BOS;
	private JButton BKN;
	private JButton NYK;
	private JButton PHI;
	private JButton TOR;
	//西部联盟
	private JButton GSW;
	private JButton LAC;
	private JButton LAL;
	private JButton PHX;
	private JButton SAC;
	
	private JButton DEN;
	private JButton MIN;
	private JButton OKC;
	private JButton POR;
	private JButton UTA;
	
	private JButton DAL;
	private JButton HOU;
	private JButton MEM;
	private JButton NOP;
	private JButton SAS;
	//联盟选项按钮
	private JButton East;
	private JLabel eastlabel_1;
	private JLabel eastlabel_2;
	private JLabel eastlabel_3;
	private JButton West;
	private JLabel westlabel_1;
	private JLabel westlabel_2;
	private JLabel westlabel_3;
	//侧边栏按钮
	private JButton SeasonInfo;
	private JButton MatchInfo;
	private JButton TeamInfo;
	private JButton PlayerInfo;
	private JButton Hot;

	
	public PlayerPre PPre;
	public JFrame Frame;
	public JPanel panelToRemove;
	public TeamInfoPanel(JFrame frame){
		Frame=frame;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		this.setOpaque(false);
		PPre=new PlayerPre();
		
		addlabel();
		addbutton();
		addteamsbutton();
		setEastSelected();
		
		this.repaint();
}

	private void addlabel(){
		eastlabel_1=new JLabel("东南分区");
		eastlabel_1.setForeground(PPre.TableFg);
		eastlabel_1.setFont(PPre.switchbox);
		eastlabel_1.setBounds(270, 130, 100, 15);
		
		eastlabel_2=new JLabel("中央分区");
		eastlabel_2.setForeground(PPre.TableFg);
		eastlabel_2.setFont(PPre.switchbox);
		eastlabel_2.setBounds(535, 130, 100, 15);
		
		eastlabel_3=new JLabel("大西洋分区");
		eastlabel_3.setForeground(PPre.TableFg);
		eastlabel_3.setFont(PPre.switchbox);
		eastlabel_3.setBounds(805, 130, 100, 15);
		
		westlabel_1=new JLabel("太平洋分区");
		westlabel_1.setForeground(PPre.TableFg);
		westlabel_1.setFont(PPre.switchbox);
		westlabel_1.setBounds(270, 130, 100, 15);
		
		westlabel_2=new JLabel("西北分区");
		westlabel_2.setForeground(PPre.TableFg);
		westlabel_2.setFont(PPre.switchbox);
		westlabel_2.setBounds(535, 130, 100, 15);
		
		westlabel_3=new JLabel("西南分区");
		westlabel_3.setForeground(PPre.TableFg);
		westlabel_3.setFont(PPre.switchbox);
		westlabel_3.setBounds(805, 130, 100, 15);
		
		this.add(eastlabel_1);
		this.add(eastlabel_2);
		this.add(eastlabel_3);
		this.add(westlabel_1);
		this.add(westlabel_2);
		this.add(westlabel_3);
	}
	
	private void addbutton(){
		SeasonInfo=new JButton(new ImageIcon("images/system_img/seasoninfo_initial.png"));
		sideButton_config(SeasonInfo, "seasoninfo", 0);
		
		MatchInfo=new JButton(new ImageIcon("images/system_img/matchinfo_initial.png"));
		sideButton_config(MatchInfo, "matchinfo", 1);
		
		TeamInfo=new JButton(new ImageIcon("images/system_img/teaminfo_initial.png"));
		sideButton_config(TeamInfo, "teaminfo", 2);
		TeamInfo.setSelected(true);
		
		PlayerInfo=new JButton(new ImageIcon("images/system_img/playerinfo_initial.png"));
		sideButton_config(PlayerInfo, "playerinfo", 3);
		
		Hot=new JButton(new ImageIcon("images/system_img/hot_initial.png"));
		sideButton_config(Hot, "hot", 4);
		
		East=new JButton(new ImageIcon("images/teams/location/east_1.png"));
		East.setBounds(480, 90, 100, 30);
		East.setBorderPainted(false);
		East.setContentAreaFilled(false);
		East.setFocusPainted(false);
		East.setRolloverIcon(new ImageIcon("images/teams/location/east_2.png"));
		East.setPressedIcon(new ImageIcon("images/teams/location/east_3.png"));
		East.addActionListener(this);
		
		West=new JButton(new ImageIcon("images/teams/location/west_1.png"));
		West.setBounds(580, 90, 100, 30);
		West.setBorderPainted(false);
		West.setContentAreaFilled(false);
		West.setFocusPainted(false);
		West.setRolloverIcon(new ImageIcon("images/teams/location/west_2.png"));
		West.setPressedIcon(new ImageIcon("images/teams/location/west_3.png"));
		West.addActionListener(this);
		
		this.add(East);
		this.add(West);
	}
	
	private void sideButton_config(JButton button,String info,int count){
		button.setBounds(26, 145+50*count, 148, 50);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setRolloverIcon(new ImageIcon("images/system_img/"+info+"_rollover.png"));
		button.setPressedIcon(new ImageIcon("images/system_img/"+info+"_pressed.png"));
		button.setSelectedIcon(new ImageIcon("images/system_img/"+info+"_selected.png"));
		button.addActionListener(this);
		this.add(button);
	}
	
	public void addteamsbutton(){

		ATL=new JButton();
//		ATL.setBounds(locx, locy, Button_width, Button_height);
		button_config(ATL,"ATL");
		CHA=new JButton();
//		CHA.setBounds(locx, locy+13+Button_height, Button_width, Button_height);
		button_config(CHA,"CHA");
		MIA=new JButton();
//		MIA.setBounds(locx, locy+(13+Button_height)*2, Button_width, Button_height);
		button_config(MIA,"MIA");
		ORL=new JButton();
//		ORL.setBounds(locx, locy+(13+Button_height)*3, Button_width, Button_height);
		button_config(ORL,"ORL");
		WAS=new JButton();
//		WAS.setBounds(locx, locy+(13+Button_height)*4, Button_width, Button_height);
		button_config(WAS,"WAS");
		
		CHI=new JButton();
//		CHI.setBounds(locx+70+Button_width, locy, Button_width, Button_height);
		button_config(CHI,"CHI");
		CLE=new JButton();
//		CLE.setBounds(locx+70+Button_width, locy+13+Button_height, Button_width, Button_height);
		button_config(CLE,"CLE");
		DET=new JButton();
//		DET.setBounds(locx+70+Button_width, locy+(13+Button_height)*2, Button_width, Button_height);
		button_config(DET,"DET");
		IND=new JButton();
//		IND.setBounds(locx+70+Button_width, locy+(13+Button_height)*3, Button_width, Button_height);
		button_config(IND,"IND");
		MIL=new JButton();
//		MIL.setBounds(locx+70+Button_width, locy+(13+Button_height)*4, Button_width, Button_height);
		button_config(MIL,"MIL");
		
		BOS=new JButton();
//		BOS.setBounds(locx+(70+Button_width)*2, locy, Button_width, Button_height);
		button_config(BOS,"BOS");
		BKN=new JButton();
//		BKN.setBounds(locx+(70+Button_width)*2, locy+13+Button_height, Button_width, Button_height);
		button_config(BKN,"BKN");
		NYK=new JButton();
//		NYK.setBounds(locx+(70+Button_width)*2, locy+(13+Button_height)*2, Button_width, Button_height);
		button_config(NYK,"NYK");
		PHI=new JButton();
//		PHI.setBounds(locx+(70+Button_width)*2, locy+(13+Button_height)*3, Button_width, Button_height);
		button_config(PHI,"PHI");
		TOR=new JButton();
//		TOR.setBounds(locx+(70+Button_width)*2, locy+(13+Button_height)*4, Button_width, Button_height);
		button_config(TOR,"TOR");
		
		GSW=new JButton();
//		GSW.setBounds(locx, locy, Button_width, Button_height);
		button_config(GSW,"GSW");
		LAC=new JButton();
//		LAC.setBounds(locx, locy+13+Button_height, Button_width, Button_height);
		button_config(LAC,"LAC");
		LAL=new JButton();
//		LAL.setBounds(locx, locy+(13+Button_height)*2, Button_width, Button_height);
		button_config(LAL,"LAL");
		PHX=new JButton();
//		PHX.setBounds(locx, locy+(13+Button_height)*3, Button_width, Button_height);
		button_config(PHX,"PHX");
		SAC=new JButton();
//		SAC.setBounds(locx, locy+(13+Button_height)*4, Button_width, Button_height);
		button_config(SAC,"SAC");
		
		DEN=new JButton();
//		DEN.setBounds(locx+70+Button_width, locy, Button_width, Button_height);
		button_config(DEN,"DEN");
		MIN=new JButton();
//		MIN.setBounds(locx+70+Button_width, locy+13+Button_height, Button_width, Button_height);
		button_config(MIN,"MIN");
		OKC=new JButton();
//		OKC.setBounds(locx+70+Button_width, locy+(13+Button_height)*2, Button_width, Button_height);
		button_config(OKC,"OKC");
		POR=new JButton();
//		POR.setBounds(locx+70+Button_width, locy+(13+Button_height)*3, Button_width, Button_height);
		button_config(POR,"POR");
		UTA=new JButton();
//		UTA.setBounds(locx+70+Button_width, locy+(13+Button_height)*4, Button_width, Button_height);
		button_config(UTA,"UTA");
		
		DAL=new JButton();
//		DAL.setBounds(locx+(70+Button_width)*2, locy, Button_width, Button_height);
		button_config(DAL,"DAL");
		HOU=new JButton();
//		HOU.setBounds(locx+(70+Button_width)*2, locy+13+Button_height, Button_width, Button_height);
		button_config(HOU,"HOU");
		MEM=new JButton();
//		MEM.setBounds(locx+(70+Button_width)*2, locy+(13+Button_height)*2, Button_width, Button_height);
		button_config(MEM,"MEM");
		NOP=new JButton();
//		NOP.setBounds(locx+(70+Button_width)*2, locy+(13+Button_height)*3, Button_width, Button_height);
		button_config(NOP,"NOP");
		SAS=new JButton();
//		SAS.setBounds(locx+(70+Button_width)*2, locy+(13+Button_height)*4, Button_width, Button_height);
		button_config(SAS,"SAS");
	}
	
	public void button_config(JButton button,String name){
		button.setIcon(new ImageIcon("images/teams/small/"+name+"_1.png"));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setRolloverIcon(new ImageIcon("images/teams/small/"+name+"_2.png"));
//		button.setPressedIcon(new ImageIcon("images/teams/small/"+name+".png"));
		this.add(button);
		button.addActionListener(this);
	}
	
	public void setEastSelected(){
		East.setIcon(new ImageIcon("images/teams/location/east_3.png"));
		West.setIcon(new ImageIcon("images/teams/location/west_1.png"));
		eastlabel_1.setVisible(true);
		eastlabel_2.setVisible(true);
		eastlabel_3.setVisible(true);
		westlabel_1.setVisible(false);
		westlabel_2.setVisible(false);
		westlabel_3.setVisible(false);
		

		
		ATL.setVisible(true);
		CHA.setVisible(true);
		MIA.setVisible(true);
		ORL.setVisible(true);
		WAS.setVisible(true);
		
		CHI.setVisible(true);
		CLE.setVisible(true);
		DET.setVisible(true);
		IND.setVisible(true);
		MIL.setVisible(true);
		
		BOS.setVisible(true);
		BKN.setVisible(true);
		NYK.setVisible(true);
		PHI.setVisible(true);
		TOR.setVisible(true);
		
		GSW.setVisible(false);
		LAC.setVisible(false);
		LAL.setVisible(false);
		PHX.setVisible(false);
		SAC.setVisible(false);
		
		DEN.setVisible(false);
		MIN.setVisible(false);
		OKC.setVisible(false);
		POR.setVisible(false);
		UTA.setVisible(false);
		
		DAL.setVisible(false);
		HOU.setVisible(false);
		MEM.setVisible(false);
		NOP.setVisible(false);
		SAS.setVisible(false);
//		ATL.setVisible(true);
//		CHI.setVisible(true);
//		BOS.setVisible(true);
		
		Thread showline=new Thread(){
			public void run(){
				//show the first line
				int i=0;
				while(i-(70+Button_width)*2<locx){
				ATL.setBounds(i-(70+Button_width)*2, locy, Button_width, Button_height);
				CHI.setBounds(i-(70+Button_width), locy, Button_width, Button_height);
				BOS.setBounds(i, locy, Button_width, Button_height);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CHA.setBounds(i-(70+Button_width)*2, locy+13+Button_height, Button_width, Button_height);
				CLE.setBounds(i-(70+Button_width), locy+13+Button_height, Button_width, Button_height);
				BKN.setBounds(i, locy+13+Button_height, Button_width, Button_height);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MIA.setBounds(i-(70+Button_width)*2, locy+(13+Button_height)*2, Button_width, Button_height);
				DET.setBounds(i-(70+Button_width), locy+(13+Button_height)*2, Button_width, Button_height);
				NYK.setBounds(i, locy+(13+Button_height)*2, Button_width, Button_height);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ORL.setBounds(i-(70+Button_width)*2, locy+(13+Button_height)*3, Button_width, Button_height);
				IND.setBounds(i-(70+Button_width), locy+(13+Button_height)*3, Button_width, Button_height);
				PHI.setBounds(i, locy+(13+Button_height)*3, Button_width, Button_height);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				WAS.setBounds(i-(70+Button_width)*2, locy+(13+Button_height)*4, Button_width, Button_height);
				MIL.setBounds(i-(70+Button_width), locy+(13+Button_height)*4, Button_width, Button_height);
				TOR.setBounds(i, locy+(13+Button_height)*4, Button_width, Button_height);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				panelToRemove.repaint();
//				Frame.repaint();
				i+=50;
				}
//				ATL.setVisible(true);
//				CHI.setVisible(true);
//				BOS.setVisible(true);
				//delay 10ms
//				try {
//					Thread.sleep(5);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
				//show the second line
//				int j=0;
//				while(j-(70+Button_width)*2<locx){
//				CHA.setBounds(j-(70+Button_width)*2, locy+13+Button_height, Button_width, Button_height);
//				CLE.setBounds(j-(70+Button_width), locy+13+Button_height, Button_width, Button_height);
//				BKN.setBounds(j, locy+13+Button_height, Button_width, Button_height);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				panelToRemove.repaint();
////				Frame.repaint();
//				j+=30;
//				}
//				CHA.setVisible(true);
//				CLE.setVisible(true);
//				BKN.setVisible(true);
				//delay 10ms
//				try {
//					Thread.sleep(5);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			
				
				//show the third line
//				int k=0;
//				while(k-(70+Button_width)*2<locx){
//				MIA.setBounds(k-(70+Button_width)*2, locy+(13+Button_height)*2, Button_width, Button_height);
//				DET.setBounds(k-(70+Button_width), locy+(13+Button_height)*2, Button_width, Button_height);
//				NYK.setBounds(k, locy+(13+Button_height)*2, Button_width, Button_height);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				panelToRemove.repaint();
////				Frame.repaint();
//				k+=30;
//				}
//				MIA.setVisible(true);
//				DET.setVisible(true);
//				NYK.setVisible(true);
				//delay 10ms
//				try {
//					Thread.sleep(5);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
				//show the forth line
//				int l=0;
//				while(l-(70+Button_width)*2<locx){
//				ORL.setBounds(l-(70+Button_width)*2, locy+(13+Button_height)*3, Button_width, Button_height);
//				IND.setBounds(l-(70+Button_width), locy+(13+Button_height)*3, Button_width, Button_height);
//				PHI.setBounds(l, locy+(13+Button_height)*3, Button_width, Button_height);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				panelToRemove.repaint();
////				Frame.repaint();
//				l+=30;
//				}
//				ORL.setVisible(true);
//				IND.setVisible(true);
//				PHI.setVisible(true);
				//delay 10ms
//				try {
//					Thread.sleep(5);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
				//show the fifth line
//				int m=0;
//				while(m-(70+Button_width)*2<locx){
//				WAS.setBounds(m-(70+Button_width)*2, locy+(13+Button_height)*4, Button_width, Button_height);
//				MIL.setBounds(m-(70+Button_width), locy+(13+Button_height)*4, Button_width, Button_height);
//				TOR.setBounds(m, locy+(13+Button_height)*4, Button_width, Button_height);
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				panelToRemove.repaint();
////				Frame.repaint();
//				m+=30;
//				}
//				WAS.setVisible(true);
//				MIL.setVisible(true);
//				TOR.setVisible(true);
				//delay 10ms
//				try {
//					Thread.sleep(5);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
			}
		};
		showline.start();
		
	}
	
//	private void 
	
	public void setWestSelected(){
		East.setIcon(new ImageIcon("images/teams/location/east_1.png"));
		West.setIcon(new ImageIcon("images/teams/location/west_3.png"));
		eastlabel_1.setVisible(false);
		eastlabel_2.setVisible(false);
		eastlabel_3.setVisible(false);
		westlabel_1.setVisible(true);
		westlabel_2.setVisible(true);
		westlabel_3.setVisible(true);
		
		ATL.setVisible(false);
		CHA.setVisible(false);
		MIA.setVisible(false);
		ORL.setVisible(false);
		WAS.setVisible(false);
		
		CHI.setVisible(false);
		CLE.setVisible(false);
		DET.setVisible(false);
		IND.setVisible(false);
		MIL.setVisible(false);
		
		BOS.setVisible(false);
		BKN.setVisible(false);
		NYK.setVisible(false);
		PHI.setVisible(false);
		TOR.setVisible(false);
		
		GSW.setVisible(true);
		LAC.setVisible(true);
		LAL.setVisible(true);
		PHX.setVisible(true);
		SAC.setVisible(true);
		
		DEN.setVisible(true);
		MIN.setVisible(true);
		OKC.setVisible(true);
		POR.setVisible(true);
		UTA.setVisible(true);
		
		DAL.setVisible(true);
		HOU.setVisible(true);
		MEM.setVisible(true);
		NOP.setVisible(true);
		SAS.setVisible(true);
	}
	//绘制赛季数据界面背景
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ImageIcon im1=new ImageIcon("images/system_img/teams_bg.png");
		g.drawImage(im1.getImage(),0,0,this);
	}

	public void jumpToPanel(String abb){
//		System.out.println(abb);
		Frame.remove(this);
//		TeamVO tvo=new TeamVO();
//		tvo.abbreviation=abb;
//		TeamPanel tp=new TeamPanel(tvo,Frame,this);
//		Frame.add(tp);
		Frame.repaint();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==SeasonInfo){
			Frame.remove(panelToRemove);
			TeamTechPanel panel=new TeamTechPanel(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		if(arg0.getSource()==MatchInfo){
			Frame.remove(panelToRemove);
			MatchPanel panel=new MatchPanel(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		if(arg0.getSource()==PlayerInfo){
			Frame.remove(panelToRemove);
			PlayerTechPanel panel=new PlayerTechPanel(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		if(arg0.getSource()==Hot){
			Frame.remove(panelToRemove);
			HotPlayerToday panel=new HotPlayerToday(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		if(arg0.getSource()==East){
			setEastSelected();
		}
		if(arg0.getSource()==West){
			setWestSelected();
		}
		
		
		if(arg0.getSource()==ATL){
			jumpToPanel("ATL");
		}
		if(arg0.getSource()==CHA){
			jumpToPanel("CHA");
		}
		if(arg0.getSource()==MIA){
			jumpToPanel("MIA");
		}
		if(arg0.getSource()==ORL){
			jumpToPanel("ORL");
		}
		if(arg0.getSource()==WAS){
			jumpToPanel("WAS");
		}
		
		if(arg0.getSource()==CHI){
			jumpToPanel("CHI");
		}
		if(arg0.getSource()==CLE){
			jumpToPanel("CLE");
		}
		if(arg0.getSource()==DET){
			jumpToPanel("DET");
		}
		if(arg0.getSource()==IND){
			jumpToPanel("IND");
		}
		if(arg0.getSource()==MIL){
			jumpToPanel("MIL");
		}
		
		
		
		if(arg0.getSource()==BOS){
			jumpToPanel("BOS");
		}
		if(arg0.getSource()==BKN){
			jumpToPanel("BKN");
		}
		if(arg0.getSource()==NYK){
			jumpToPanel("NYK");
		}
		if(arg0.getSource()==PHI){
			jumpToPanel("PHI");
		}
		if(arg0.getSource()==TOR){
			jumpToPanel("TOR");
		}
		
		if(arg0.getSource()==GSW){
			jumpToPanel("GSW");
		}
		if(arg0.getSource()==LAC){
			jumpToPanel("LAC");
		}
		if(arg0.getSource()==LAL){
			jumpToPanel("LAL");
		}
		if(arg0.getSource()==PHX){
			jumpToPanel("PHX");
		}
		if(arg0.getSource()==SAC){
			jumpToPanel("SAC");
		}
		
		
		if(arg0.getSource()==DEN){
			jumpToPanel("DEN");
		}
		if(arg0.getSource()==MIN){
			jumpToPanel("MIN");
		}
		if(arg0.getSource()==OKC){
			jumpToPanel("OKC");
		}
		if(arg0.getSource()==POR){
			jumpToPanel("POR");
		}
		if(arg0.getSource()==UTA){
			jumpToPanel("UTA");
		}
		
		if(arg0.getSource()==DAL){
			jumpToPanel("DAL");
		}
		if(arg0.getSource()==HOU){
			jumpToPanel("HOU");
		}
		if(arg0.getSource()==MEM){
			jumpToPanel("MEM");
		}
		if(arg0.getSource()==NOP){
			jumpToPanel("NOP");
		}
		if(arg0.getSource()==SAS){
			jumpToPanel("SAS");
		}
	}
}