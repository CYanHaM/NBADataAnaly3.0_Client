package presentation.teamui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.hotspotui.HotPlayerToday;
import presentation.matchui.MatchPanel;
import presentation.playerui.PlayerTechPanel;
import presentation.preset.PlayerPre;
import VO.TeamVO;

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
	//定义边缘透明空白区域边界大小，单位px
//	public static int e_space=10;
	
	private static int locx=220;
	private static int locy=170;
	private static int Button_width=195;
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
	//loading label
	private JLabel loading;
	private ImageIcon loadingicon;

	
	public PlayerPre PPre;
	public JFrame Frame;
	public JPanel panelToRemove;
	public JPanel panelToJump;
	public TeamInfoPanel(JFrame frame){
		Frame=frame;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		this.setOpaque(false);
		PPre=new PlayerPre();
		loading = new JLabel();
		loading.setBounds(0, 50, WIDTH, HEIGHT-50);
		loadingicon=new ImageIcon("images/test.png");
//		loading.setIcon(loadingicon);
		loading.setVisible(false);
		this.add(loading);
		
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
		eastlabel_1.setBounds(270, locy-30, 100, 15);
		
		eastlabel_2=new JLabel("中央分区");
		eastlabel_2.setForeground(PPre.TableFg);
		eastlabel_2.setFont(PPre.switchbox);
		eastlabel_2.setBounds(535, locy-30, 100, 15);
		
		eastlabel_3=new JLabel("大西洋分区");
		eastlabel_3.setForeground(PPre.TableFg);
		eastlabel_3.setFont(PPre.switchbox);
		eastlabel_3.setBounds(805, locy-30, 100, 15);
		
		westlabel_1=new JLabel("太平洋分区");
		westlabel_1.setForeground(PPre.TableFg);
		westlabel_1.setFont(PPre.switchbox);
		westlabel_1.setBounds(270, locy-30, 100, 15);
		
		westlabel_2=new JLabel("西北分区");
		westlabel_2.setForeground(PPre.TableFg);
		westlabel_2.setFont(PPre.switchbox);
		westlabel_2.setBounds(535, locy-30, 100, 15);
		
		westlabel_3=new JLabel("西南分区");
		westlabel_3.setForeground(PPre.TableFg);
		westlabel_3.setFont(PPre.switchbox);
		westlabel_3.setBounds(805, locy-30, 100, 15);
		
		this.add(eastlabel_1);
		this.add(eastlabel_2);
		this.add(eastlabel_3);
		this.add(westlabel_1);
		this.add(westlabel_2);
		this.add(westlabel_3);
		
	}
	
	private void addbutton(){
		East=new JButton(new ImageIcon("images/teams/location/east_1.png"));
		East.setBounds(485, 90, 100, 30);
		East.setBorderPainted(false);
		East.setContentAreaFilled(false);
		East.setFocusPainted(false);
		East.setRolloverIcon(new ImageIcon("images/teams/location/east_2.png"));
		East.setPressedIcon(new ImageIcon("images/teams/location/east_3.png"));
		East.addActionListener(this);
		
		West=new JButton(new ImageIcon("images/teams/location/west_1.png"));
		West.setBounds(585, 90, 100, 30);
		West.setBorderPainted(false);
		West.setContentAreaFilled(false);
		West.setFocusPainted(false);
		West.setRolloverIcon(new ImageIcon("images/teams/location/west_2.png"));
		West.setPressedIcon(new ImageIcon("images/teams/location/west_3.png"));
		West.addActionListener(this);
		
		this.add(East);
		this.add(West);
	}
	
	
	public void addteamsbutton(){
		ATL=new JButton();
		button_config(ATL,"ATL");
		CHA=new JButton();
		button_config(CHA,"CHA");
		MIA=new JButton();
		button_config(MIA,"MIA");
		ORL=new JButton();
		button_config(ORL,"ORL");
		WAS=new JButton();
		button_config(WAS,"WAS");
		
		CHI=new JButton();
		button_config(CHI,"CHI");
		CLE=new JButton();
		button_config(CLE,"CLE");
		DET=new JButton();
		button_config(DET,"DET");
		IND=new JButton();
		button_config(IND,"IND");
		MIL=new JButton();
		button_config(MIL,"MIL");
		
		BOS=new JButton();
		button_config(BOS,"BOS");
		BKN=new JButton();
		button_config(BKN,"BKN");
		NYK=new JButton();
		button_config(NYK,"NYK");
		PHI=new JButton();
		button_config(PHI,"PHI");
		TOR=new JButton();
		button_config(TOR,"TOR");
		
		GSW=new JButton();
		button_config(GSW,"GSW");
		LAC=new JButton();
		button_config(LAC,"LAC");
		LAL=new JButton();
		button_config(LAL,"LAL");
		PHX=new JButton();
		button_config(PHX,"PHX");
		SAC=new JButton();
		button_config(SAC,"SAC");
		
		DEN=new JButton();
		button_config(DEN,"DEN");
		MIN=new JButton();
		button_config(MIN,"MIN");
		OKC=new JButton();
		button_config(OKC,"OKC");
		POR=new JButton();
		button_config(POR,"POR");
		UTA=new JButton();
		button_config(UTA,"UTA");
		
		DAL=new JButton();
		button_config(DAL,"DAL");
		HOU=new JButton();
		button_config(HOU,"HOU");
		MEM=new JButton();
		button_config(MEM,"MEM");
		NOP=new JButton();
		button_config(NOP,"NOP");
		SAS=new JButton();
		button_config(SAS,"SAS");
	}
	
	public void button_config(JButton button,String name){
		button.setIcon(new ImageIcon("images/teams/small/"+name+"_1.png"));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setRolloverIcon(new ImageIcon("images/teams/small/"+name+"_2.png"));
		//button.setPressedIcon(new ImageIcon("images/teams/small/"+name+".png"));
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
		
		Thread showline=new Thread(){
			int distance=50;
			int delay=50;
			public void run(){
				int i=0;
				boolean stop=false;
				while(!stop){
					if(i*distance-(70+Button_width)*2<=locx){
						ATL.setBounds(i*distance-(70+Button_width)*2, locy, Button_width, Button_height);
						CHI.setBounds(i*distance-(70+Button_width), locy, Button_width, Button_height);
						BOS.setBounds(i*distance, locy, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay<=locx){
						CHA.setBounds(i*distance-(70+Button_width)*2-delay, locy+13+Button_height, Button_width, Button_height);
						CLE.setBounds(i*distance-(70+Button_width)-delay, locy+13+Button_height, Button_width, Button_height);
						BKN.setBounds(i*distance-delay, locy+13+Button_height, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay*2<=locx){
						MIA.setBounds(i*distance-(70+Button_width)*2-delay*2, locy+(13+Button_height)*2, Button_width, Button_height);
						DET.setBounds(i*distance-(70+Button_width)-delay*2, locy+(13+Button_height)*2, Button_width, Button_height);
						NYK.setBounds(i*distance-delay*2, locy+(13+Button_height)*2, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay*3<=locx){
						ORL.setBounds(i*distance-(70+Button_width)*2-delay*3, locy+(13+Button_height)*3, Button_width, Button_height);
						IND.setBounds(i*distance-(70+Button_width)-delay*3, locy+(13+Button_height)*3, Button_width, Button_height);
						PHI.setBounds(i*distance-delay*3, locy+(13+Button_height)*3, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay*4<=locx){
						WAS.setBounds(i*distance-(70+Button_width)*2-delay*4, locy+(13+Button_height)*4, Button_width, Button_height);
						MIL.setBounds(i*distance-(70+Button_width)-delay*4, locy+(13+Button_height)*4, Button_width, Button_height);
						TOR.setBounds(i*distance-delay*4, locy+(13+Button_height)*4, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay*4==locx)
						stop=true;
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					panelToRemove.repaint();
					i++;
				}
			}
		};
		showline.start();
		
	}
	
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
		
		Thread showline=new Thread(){
			int distance=50;
			int delay=50;
			public void run(){
				int i=0;
				boolean stop=false;
				while(!stop){
					if(i*distance-(70+Button_width)*2<=locx){
						GSW.setBounds(i*distance-(70+Button_width)*2, locy, Button_width, Button_height);
						DEN.setBounds(i*distance-(70+Button_width), locy, Button_width, Button_height);
						DAL.setBounds(i*distance, locy, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay<=locx){
						LAC.setBounds(i*distance-(70+Button_width)*2-delay, locy+13+Button_height, Button_width, Button_height);
						MIN.setBounds(i*distance-(70+Button_width)-delay, locy+13+Button_height, Button_width, Button_height);
						HOU.setBounds(i*distance-delay, locy+13+Button_height, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay*2<=locx){
						LAL.setBounds(i*distance-(70+Button_width)*2-delay*2, locy+(13+Button_height)*2, Button_width, Button_height);
						OKC.setBounds(i*distance-(70+Button_width)-delay*2, locy+(13+Button_height)*2, Button_width, Button_height);
						MEM.setBounds(i*distance-delay*2, locy+(13+Button_height)*2, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay*3<=locx){
						PHX.setBounds(i*distance-(70+Button_width)*2-delay*3, locy+(13+Button_height)*3, Button_width, Button_height);
						POR.setBounds(i*distance-(70+Button_width)-delay*3, locy+(13+Button_height)*3, Button_width, Button_height);
						NOP.setBounds(i*distance-delay*3, locy+(13+Button_height)*3, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay*4<=locx){
						SAC.setBounds(i*distance-(70+Button_width)*2-delay*4, locy+(13+Button_height)*4, Button_width, Button_height);
						UTA.setBounds(i*distance-(70+Button_width)-delay*4, locy+(13+Button_height)*4, Button_width, Button_height);
						SAS.setBounds(i*distance-delay*4, locy+(13+Button_height)*4, Button_width, Button_height);
					}
					if(i*distance-(70+Button_width)*2-delay*4==locx)
						stop=true;
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					panelToRemove.repaint();
					i++;
				}
				
			}
		};
		showline.start();
	}
	
	public void loading(){

		Thread showloading=new Thread(){
			public void run(){
				ImageIcon imgicon=new ImageIcon("images/test.png");
				for(int scale=50;scale<300;scale+=5){
				int width=(int)(imgicon.getIconWidth()*scale/100);
				int height=(int)(imgicon.getIconHeight()*scale/100);
//				System.out.println(width+"--"+height);
				Image img=imgicon.getImage();
				img=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				loadingicon=new ImageIcon(img);
				loading.setIcon(loadingicon);
//				loading.repaint();
//				panelToRemove.repaint();
//				Frame.repaint();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		};
		showloading.start();
	}
	
	//绘制赛季数据界面背景
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ImageIcon im1=new ImageIcon("images/system_img/bg-1.png");
		g.drawImage(im1.getImage(),0,0,this);
	}

	public void jumpToPanel(String abb){
		TeamVO tvo=new TeamVO();
		tvo.abbreviation=abb;
		panelToJump=new TeamPanel(tvo,Frame,this);
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