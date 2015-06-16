package presentation.playerui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import presentation.hotspotui.HotPlayerToday;
import presentation.matchui.MatchPanel;
import presentation.preset.PlayerTechPre;
import presentation.preset.StatPre;
import presentation.teamui.TeamInfoPanel;
import presentation.teamui.TeamTechPanel;
import TypeEnum.PlayerTechEnum;
import VO.PlayerTechVO;
import VO.ScreeningConditionVO;

public class PlayerTechPanel extends JPanel implements ActionListener{
	/**
	 * 球员统计数据界面
	 * @author blisscry
	 * @date 2015年3月30日15:40:16
	 * @version 1.3
	 */
	private static final long serialVersionUID = 1L;

	//-------------------------界面常量-------------------
	public static int WIDTH=1100;
	public static int HEIGHT=700;
	//定义边缘透明空白区域边界大小，单位px
	public static int e_space=10;
	//定义空出位置大小
	private static int space=20;
	//设置球员总数常量
	private static int PLAYERNUM=443;

	//表格大小
	private static int TABLEWIDTH=900;
	private static int TABLEHEIGHT=415;
	//表格行高
	private static int ROWHEIGHT=40;

	//下拉框大小
	private static int BOXWIDTH=160;
	private static int BOXHEIGHT=30;
	//----------------------------------------------------

	//-------------------------界面组件--------------------
	//设置表格属性
	private JTable playertable1;
	private JTable playertable2;
	private JTable playertable3;
	private String[] playernames;
	private Object[][] playerinfo1;
	private Object[][] playerinfo2;
	private Object[][] playerinfo3;
	private JScrollPane players;
	private String[] columnName1={"排名","球员名称","所属球队","得分","篮板","助攻","进攻","防守","抢断","盖帽","失误","犯规",};
	private String[] columnName2={"排名","球员名称","命中%","三分%","罚球%","篮板%","进攻篮板%","防守篮板%","助攻%","抢断%","盖帽%"};
	private String[] columnName3={"排名","球员名称","时间","参赛","先发","真实命中%","GmSc 效率","投篮效率","失误%","使用%"};
	//表格列宽
	private static int[] COLUMNWIDTH1={40,160,170,70,70,70,50,50,50,50,50,51};
	private static int[] COLUMNWIDTH2={40,160,75,75,75,80,80,80,72,72,72};
	private static int[] COLUMNWIDTH3={40,160,80,80,80,80,80,90,98,93};

	
	//总数据与场均数据切换下拉框
	private JComboBox<String> switchbox;

	//排序方式单选按钮组
	private JRadioButton order_Asc;
	private JRadioButton order_Des;
	private ButtonGroup group;
	//排序依据显示行
	private JLabel message;
	
	//高级筛选按钮
	private JButton seniorsift;
	private JButton add;
	private JButton delete;
	private JScrollPane siftpanel;
	private SeniorSiftPanel seniorsiftpanel;
	public ArrayList<ScreeningConditionVO> screeningconditions;
	//筛选提交按钮
	private JButton commit;
	//表格重置按钮
	private JButton reset;
	
	private JComboBox<String> season;
	private int season_w=200;
	private int season_h=30;
	
	private JButton first;
	private JButton second;
	private JButton third;
	
	private JButton trigger;
	private boolean exit=false;
	private JButton[] letterbutton;
	private char[] letter;
	private String letterstring="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	//----------------------------------------------------
	public PlayerTechPre PTPre;
	public ImportPlayer importdata;
	public ArrayList<PlayerTechVO> initial_data;

	public int HeaderColumn=0;
	public JFrame Frame; 
	public JPanel panelToRemove;
	
	
	public PlayerTechPanel(JFrame frame){
		Frame=frame;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		screeningconditions=new ArrayList<ScreeningConditionVO>();
		
		letter=new char[26];
		letter=letterstring.toCharArray();
		letterbutton=new JButton[26];
		
		//创建颜色预设对象
		PTPre=new PlayerTechPre();
		importdata=new ImportPlayer();
		
		//添加下拉框
		addbox();

		initdata();
		//加载初始表格，显示队伍总数据
		handleinitial(initial_data);

		//加载表格配置
		table1_config();
		table2_config();
		table3_config();
		//加载滑动面板配置
		scrollpane_config();
		
		//添加高级筛选
		addseniorsift();
		//添加单选按钮组
		addradiobutton();
		//添加提示信息
		addmessage();
		//添加提交，重置按钮
		addbutton();

		addletterbutton();

		this.repaint();
	}
	
	//===================================================================
	private void addbox(){
		//下拉框
		switchbox=new JComboBox<String>();
		switchbox.setFocusable(false);
		switchbox.setBackground(PTPre.LineSelected);
		switchbox.addItem("赛季总数据");
		switchbox.addItem("场均数据");
		switchbox.setBounds(WIDTH-TABLEWIDTH-e_space-space,HEIGHT-TABLEHEIGHT-e_space-space-100-35+15,BOXWIDTH,BOXHEIGHT);
		switchbox.setFont(PTPre.switchbox);
		switchbox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					if(switchbox.getSelectedItem().equals("赛季总数据")){
						initdata();
						handleTotalData(initial_data);
					}
					if(switchbox.getSelectedItem().equals("场均数据")){
						initdata();
						handleAverageData(initial_data);
					}
				}
			}
		});
		this.add(switchbox);
		
		season=new JComboBox<String>();
		//TODO delete the test
//		ArrayList<String> seasonlist=importdata.getPlayerSeasonList();
		ArrayList<String> seasonlist=new ArrayList<String>();
		seasonlist.add("2011-12 Regular");
		seasonlist.add("2011-12 Postseason");
		seasonlist.add("2012-13 Regular");
		seasonlist.add("2012-13 Postseason");
		seasonlist.add("2013-14 Regular");
		seasonlist.add("2013-14 Postseason");
		seasonlist.add("2015-16 Regular");
		seasonlist.add("2015-16 Postseason");
		seasonlist.add("2016-17 Regular");
		seasonlist.add("2016-17 Postseason");
		for(int i=0;i<seasonlist.size();i++){
			String[] temp=seasonlist.get(i).split(" ");
			if(temp[1].equals("Regular")){
				season.addItem(temp[0]+" 常规赛");
			}else if(temp[1].equals("Postseason")){
				season.addItem(temp[0]+" 季后赛");
			}
		}
		season.setBounds(WIDTH-TABLEWIDTH-e_space-space,HEIGHT-TABLEHEIGHT-e_space-space-100-35+15-35,season_w,season_h);
		season.setFocusable(false);
		season.setBackground(StatPre.indefaultcolor);
		season.setFont(PTPre.switchbox);
		season.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					initdata();
					refreshtable();
				}
			}
		});
		this.add(season);
	}
	
	private void addletterbutton(){
		trigger=new JButton(new ImageIcon("images/players/trigger_1.png"));
		trigger.setBounds(WIDTH-TABLEWIDTH-e_space-space, HEIGHT-TABLEHEIGHT-e_space-space-100-35+65, 110, 30);
		trigger.setBorderPainted(false);
		trigger.setContentAreaFilled(false);
		trigger.setFocusPainted(false);
		trigger.setRolloverIcon(new ImageIcon("images/players/trigger_2.png"));
		trigger.setPressedIcon(new ImageIcon("images/players/trigger_2.png"));
		trigger.setSelectedIcon(new ImageIcon("images/players/trigger_3.png"));
		trigger.addActionListener(this);
		this.add(trigger);
		
		for(int i=0;i<letterbutton.length;i++){
			letterbutton[i]=new JButton(new ImageIcon("images/buttons/letters/"+i+"_1.png"));
			letterbutton[i].setBounds(WIDTH-TABLEWIDTH-e_space-space+i*30, HEIGHT-TABLEHEIGHT-e_space-space-35, 30, 30);
			letterbutton[i].setBorderPainted(false);
			letterbutton[i].setContentAreaFilled(false);
			letterbutton[i].setFocusPainted(false);
			letterbutton[i].setRolloverIcon(new ImageIcon("images/buttons/letters/"+i+"_2.png"));
			letterbutton[i].setPressedIcon(new ImageIcon("images/buttons/letters/"+i+"_3.png"));
			letterbutton[i].setSelectedIcon(new ImageIcon("images/buttons/letters/"+i+"_3.png"));
			//add new actionlistener
			letterbutton[i].addActionListener(new letterbuttonAction(i));
			this.add(letterbutton[i]);
			letterbutton[i].setVisible(false);
		}
	}
	
	private class letterbuttonAction implements ActionListener{
		private int Count;
		public letterbuttonAction(int count) {
			Count=count;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<PlayerTechVO> playervo=importdata.findPlayerByLetter(letter[Count],String.valueOf(season.getSelectedItem()));
			playerinfo1=new Object[playervo.size()][columnName1.length];
			playerinfo2=new Object[playervo.size()][columnName2.length];
			playerinfo3=new Object[playervo.size()][columnName3.length];
			String switchboxsel=(String) switchbox.getSelectedItem();
			if(switchboxsel.equals("赛季总数据")){
				handleTotalData(playervo);
			}else if(switchboxsel.equals("场均数据")){
				handleAverageData(playervo);
			}
			for(int j=0;j<letterbutton.length;j++){
				if(j==Count)
					letterbutton[j].setSelected(true);
				else{
					letterbutton[j].setSelected(false);
				}
			}
		}
	}
	
	private void addseniorsift(){
		siftpanel=new JScrollPane();
		siftpanel.setBounds(WIDTH-TABLEWIDTH-e_space-space+520, HEIGHT-TABLEHEIGHT-e_space-space-100-65, 380, 90);
		siftpanel.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		siftpanel.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		siftpanel.setOpaque(false);
		siftpanel.getViewport().setOpaque(false);
		siftpanel.setBorder(null);
		seniorsiftpanel=new SeniorSiftPanel(this);
		siftpanel.setViewportView(seniorsiftpanel);
		this.add(siftpanel);
		
		add=new JButton(new ImageIcon("images/system_img/+_1.png"));
		add.setBounds(WIDTH-TABLEWIDTH-e_space-space+460+15, HEIGHT-TABLEHEIGHT-e_space-space-100-40-20, 30, 30);
		add.setBorderPainted(false);
		add.setContentAreaFilled(false);
		add.setFocusPainted(false);
		add.setRolloverIcon(new ImageIcon("images/system_img/+_2.png"));
		add.setPressedIcon(new ImageIcon("images/system_img/+_3.png"));
		add.setSelectedIcon(new ImageIcon("images/system_img/+_3.png"));
		add.addActionListener(this);
		this.add(add);
		
		delete=new JButton(new ImageIcon("images/system_img/-_1.png"));
		delete.setBounds(WIDTH-TABLEWIDTH-e_space-space+460+15, HEIGHT-TABLEHEIGHT-e_space-space-100-10-20, 30, 30);
		delete.setBorderPainted(false);
		delete.setContentAreaFilled(false);
		delete.setFocusPainted(false);
		delete.setRolloverIcon(new ImageIcon("images/system_img/-_2.png"));
		delete.setPressedIcon(new ImageIcon("images/system_img/-_3.png"));
		delete.setSelectedIcon(new ImageIcon("images/system_img/-_3.png"));
		delete.addActionListener(this);
		this.add(delete);
		
	}
	
	private void addradiobutton(){
		order_Asc=new JRadioButton("升序");
		order_Asc.setFont(PTPre.switchbox);
		order_Asc.setForeground(PTPre.TableFg);
		order_Asc.setBorderPainted(false);
		order_Asc.setContentAreaFilled(false);
		order_Asc.setFocusPainted(false);
//		order_Asc.setSelected(true);
		order_Asc.setBounds(WIDTH-TABLEWIDTH-e_space-space+BOXWIDTH+10,HEIGHT-TABLEHEIGHT-e_space-space-100+15-30,50,15);
		
		order_Des=new JRadioButton("降序");
		order_Des.setFont(PTPre.switchbox);
		order_Des.setForeground(PTPre.TableFg);
		order_Des.setBorderPainted(false);
		order_Des.setContentAreaFilled(false);
		order_Des.setFocusPainted(false);
		order_Des.setSelected(true);
		order_Des.setBounds(WIDTH-TABLEWIDTH-e_space-space+BOXWIDTH+60,HEIGHT-TABLEHEIGHT-e_space-space-100+15-30,50,15);
		
		group=new ButtonGroup();
		group.add(order_Asc);
		group.add(order_Des);
		
		this.add(order_Asc);
		this.add(order_Des);
	}
	
	private void addmessage(){
		message=new JLabel();
		message.setBounds(WIDTH-TABLEWIDTH-e_space-space+BOXWIDTH+120, HEIGHT-TABLEHEIGHT-e_space-space-92-65, 200, 15);
		message.setFont(PTPre.switchbox);
		message.setForeground(PTPre.TableFg);
		
		this.add(message);
	}
	
	private void addbutton(){
		commit=new JButton(new ImageIcon("images/buttons/playertech/Commit_initial.png"));
		commit.setBounds(WIDTH-TABLEWIDTH-e_space-space+(BOXWIDTH+10)*3+120,HEIGHT-TABLEHEIGHT-e_space-space-50-20,BOXWIDTH,BOXHEIGHT);
		commit.setBorderPainted(false);
		commit.setContentAreaFilled(false);
		commit.setFocusPainted(false);
		commit.setRolloverIcon(new ImageIcon("images/buttons/playertech/Commit_rollover.png"));
		commit.setPressedIcon(new ImageIcon("images/buttons/playertech/Commit_pressed.png"));
		commit.addActionListener(this);
		
		reset=new JButton(new ImageIcon("images/buttons/playertech/Reset_initial.png"));
		reset.setBounds(WIDTH-TABLEWIDTH-e_space-space+(BOXWIDTH+10)*4+120, HEIGHT-TABLEHEIGHT-e_space-space-50-20, 100, 30);
		reset.setBorderPainted(false);
		reset.setContentAreaFilled(false);
		reset.setFocusPainted(false);
		reset.setRolloverIcon(new ImageIcon("images/buttons/playertech/Reset_rollover.png"));
		reset.setPressedIcon(new ImageIcon("images/buttons/playertech/Reset_pressed.png"));
		reset.addActionListener(this);
		
		this.add(commit);
		this.add(reset);
		
		first=new JButton(new ImageIcon("images/system_img/1_1.png"));
		first.setBounds(WIDTH-TABLEWIDTH-e_space-space+(BOXWIDTH+10)*4+35+120, HEIGHT-TABLEHEIGHT-e_space-space-50+20, 20, 20);
		first.setBorderPainted(false);
		first.setContentAreaFilled(false);
		first.setFocusPainted(false);
		first.setRolloverIcon(new ImageIcon("images/system_img/1_2.png"));
		first.setPressedIcon(new ImageIcon("images/system_img/1_3.png"));
		first.setSelectedIcon(new ImageIcon("images/system_img/1_3.png"));
		first.setSelected(true);
		first.addActionListener(this);
		second=new JButton(new ImageIcon("images/system_img/2_1.png"));
		second.setBounds(WIDTH-TABLEWIDTH-e_space-space+(BOXWIDTH+10)*4+35+20+120, HEIGHT-TABLEHEIGHT-e_space-space-50+20, 20, 20);
		second.setBorderPainted(false);
		second.setContentAreaFilled(false);
		second.setFocusPainted(false);
		second.setRolloverIcon(new ImageIcon("images/system_img/2_2.png"));
		second.setPressedIcon(new ImageIcon("images/system_img/2_3.png"));
		second.setSelectedIcon(new ImageIcon("images/system_img/2_3.png"));
		second.addActionListener(this);
		third=new JButton(new ImageIcon("images/system_img/3_1.png"));
		third.setBounds(WIDTH-TABLEWIDTH-e_space-space+(BOXWIDTH+10)*4+35+20*2+120, HEIGHT-TABLEHEIGHT-e_space-space-50+20, 20, 20);
		third.setBorderPainted(false);
		third.setContentAreaFilled(false);
		third.setFocusPainted(false);
		third.setRolloverIcon(new ImageIcon("images/system_img/3_2.png"));
		third.setPressedIcon(new ImageIcon("images/system_img/3_3.png"));
		third.setSelectedIcon(new ImageIcon("images/system_img/3_3.png"));
		third.addActionListener(this);
		this.add(first);
		this.add(second);
		this.add(third);
		
	}
	
	//===================================================================
	//----------------------initial & different methods------------
	private void initdata(){
		initial_data=importdata.getPlayerTechAscend("name",(String)season.getSelectedItem());
		//TODO delete the test
		playerinfo1=new Object[initial_data.size()][columnName1.length];
		playerinfo2=new Object[initial_data.size()][columnName2.length];
		playerinfo3=new Object[initial_data.size()][columnName3.length];
//		playerinfo1=new Object[PLAYERNUM][columnName1.length];
//		playerinfo2=new Object[PLAYERNUM][columnName2.length];
//		playerinfo3=new Object[PLAYERNUM][columnName3.length];
	}
	private void handleinitial(ArrayList<PlayerTechVO> totaldata){
		int a=0;
		playernames=new String[totaldata.size()];
		for(PlayerTechVO i:totaldata){
			playerinfo1[a][1]=i.name;
			playerinfo1[a][2]=switchTeamName(i.team);
			playerinfo1[a][3]=i.score;
			playerinfo1[a][4]=i.rebound;
			playerinfo1[a][5]=i.secondaryAttack;
			playerinfo1[a][6]=i.offensiveNum;
			playerinfo1[a][7]=i.defensiveNum;
			playerinfo1[a][8]=i.steal;
			playerinfo1[a][9]=i.blockShot;
			playerinfo1[a][10]=i.fault;
			playerinfo1[a][11]=i.foul;
			
			playerinfo2[a][1]=i.name;
			playerinfo2[a][2]=String.valueOf((int) Math.floor(i.shotInRate*100))+"%";
			playerinfo2[a][3]=String.valueOf((int) Math.floor(i.threeShotInRate*100))+"%";
			playerinfo2[a][4]=String.valueOf((int) Math.floor(i.penaltyShotInRate*100))+"%";
			playerinfo2[a][5]=String.valueOf((int) Math.floor(i.reboundRate*100))+"%";
			playerinfo2[a][6]=String.valueOf((int) Math.floor(i.offensiveReboundRate*100))+"%";
			playerinfo2[a][7]=String.valueOf((int) Math.floor(i.defensiveReboundRate*100))+"%";
			playerinfo2[a][8]=String.valueOf((int) Math.floor(i.secondaryAttackRate*100))+"%";
			playerinfo2[a][9]=String.valueOf((int) Math.floor(i.stealRate*100))+"%";
			playerinfo2[a][10]=String.valueOf((int) Math.floor(i.blockShotRate*100))+"%";
			
			playerinfo3[a][1]=i.name;
			playerinfo3[a][2]=i.time;
			playerinfo3[a][3]=i.gameNum;
			playerinfo3[a][4]=i.startingNum;
			playerinfo3[a][5]=String.valueOf((int) Math.floor(i.trueShotInRate*100))+"%";
			playerinfo3[a][6]=String.valueOf((int) Math.floor(i.efficiency))+"%";
			playerinfo3[a][7]=String.valueOf((int) Math.floor(i.GmScEfficiency))+"%";
			playerinfo3[a][8]=String.valueOf((int) Math.floor(i.shootingEfficiency*100))+"%";
			playerinfo3[a][9]=String.valueOf((int) Math.floor(i.faultRate*100))+"%";
			playerinfo3[a][10]=String.valueOf((int) Math.floor(i.usageRate*100))+"%";
			
			playernames[a]=i.name;
			a++;
		}
	}
	
	private void handleTotalData(ArrayList<PlayerTechVO> totaldata){
		int a=0;
		playernames=new String[totaldata.size()];
		for(PlayerTechVO i:totaldata){
			playerinfo1[a][1]=i.name;
			playerinfo1[a][2]=switchTeamName(i.team);
			playerinfo1[a][3]=i.score;
			playerinfo1[a][4]=i.rebound;
			playerinfo1[a][5]=i.secondaryAttack;
			playerinfo1[a][6]=i.offensiveNum;
			playerinfo1[a][7]=i.defensiveNum;
			playerinfo1[a][8]=i.steal;
			playerinfo1[a][9]=i.blockShot;
			playerinfo1[a][10]=i.fault;
			playerinfo1[a][11]=i.foul;
			
			playerinfo2[a][1]=i.name;
			playerinfo2[a][2]=String.valueOf((int) Math.floor(i.shotInRate*100))+"%";
			playerinfo2[a][3]=String.valueOf((int) Math.floor(i.threeShotInRate*100))+"%";
			playerinfo2[a][4]=String.valueOf((int) Math.floor(i.penaltyShotInRate*100))+"%";
			playerinfo2[a][5]=String.valueOf((int) Math.floor(i.reboundRate*100))+"%";
			playerinfo2[a][6]=String.valueOf((int) Math.floor(i.offensiveReboundRate*100))+"%";
			playerinfo2[a][7]=String.valueOf((int) Math.floor(i.defensiveReboundRate*100))+"%";
			playerinfo2[a][8]=String.valueOf((int) Math.floor(i.secondaryAttackRate*100))+"%";
			playerinfo2[a][9]=String.valueOf((int) Math.floor(i.stealRate*100))+"%";
			playerinfo2[a][10]=String.valueOf((int) Math.floor(i.blockShotRate*100))+"%";
			
			playerinfo3[a][1]=i.name;
			playerinfo3[a][2]=i.time;
			playerinfo3[a][3]=i.gameNum;
			playerinfo3[a][4]=i.startingNum;
			playerinfo3[a][5]=String.valueOf((int) Math.floor(i.trueShotInRate*100))+"%";
			playerinfo3[a][6]=String.valueOf((int) Math.floor(i.efficiency))+"%";
			playerinfo3[a][7]=String.valueOf((int) Math.floor(i.GmScEfficiency))+"%";
			playerinfo3[a][8]=String.valueOf((int) Math.floor(i.shootingEfficiency*100))+"%";
			playerinfo3[a][9]=String.valueOf((int) Math.floor(i.faultRate*100))+"%";
			playerinfo3[a][10]=String.valueOf((int) Math.floor(i.usageRate*100))+"%";
			a++;
			
		}
		refreshtable();
	}

	private void handleAverageData(ArrayList<PlayerTechVO> averagedata){
		int a=0;
		for(PlayerTechVO i:averagedata){
			playerinfo1[a][1]=i.name;
			playerinfo1[a][2]=switchTeamName(i.team);
			playerinfo1[a][3]=String.valueOf((int) Math.floor(i.scoreave));
			playerinfo1[a][4]=String.valueOf((int) Math.floor(i.reboundave));
			playerinfo1[a][5]=String.valueOf((int) Math.floor(i.secondaryAttackave));
			playerinfo1[a][6]=String.valueOf((int) Math.floor(i.offensiveNumave));
			playerinfo1[a][7]=String.valueOf((int) Math.floor(i.defensiveNumave));
			playerinfo1[a][8]=String.valueOf((int) Math.floor(i.stealave));
			playerinfo1[a][9]=String.valueOf((int) Math.floor(i.blockShotave));
			playerinfo1[a][10]=String.valueOf((int) Math.floor(i.faultave));
			playerinfo1[a][11]=String.valueOf((int) Math.floor(i.foulave));
			
			playerinfo2[a][1]=i.name;
			playerinfo2[a][2]=String.valueOf((int) Math.floor(i.shotInRate*100))+"%";
			playerinfo2[a][3]=String.valueOf((int) Math.floor(i.threeShotInRate*100))+"%";
			playerinfo2[a][4]=String.valueOf((int) Math.floor(i.penaltyShotInRate*100))+"%";
			playerinfo2[a][5]=String.valueOf((int) Math.floor(i.reboundRate*100))+"%";
			playerinfo2[a][6]=String.valueOf((int) Math.floor(i.offensiveReboundRate*100))+"%";
			playerinfo2[a][7]=String.valueOf((int) Math.floor(i.defensiveReboundRate*100))+"%";
			playerinfo2[a][8]=String.valueOf((int) Math.floor(i.secondaryAttackRate*100))+"%";
			playerinfo2[a][9]=String.valueOf((int) Math.floor(i.stealRate*100))+"%";
			playerinfo2[a][10]=String.valueOf((int) Math.floor(i.blockShotRate*100))+"%";
			
			playerinfo3[a][1]=i.name;
			playerinfo3[a][2]=String.valueOf((int) Math.floor(i.timeave));
			playerinfo3[a][3]=i.gameNum;
			playerinfo3[a][4]=i.startingNum;
			playerinfo3[a][5]=String.valueOf((int) Math.floor(i.trueShotInRate*100))+"%";
			playerinfo3[a][6]=String.valueOf((int) Math.floor(i.efficiency))+"%";
			playerinfo3[a][7]=String.valueOf((int) Math.floor(i.GmScEfficiency))+"%";
			playerinfo3[a][8]=String.valueOf((int) Math.floor(i.shootingEfficiency*100))+"%";
			playerinfo3[a][9]=String.valueOf((int) Math.floor(i.faultRate*100))+"%";
			playerinfo3[a][10]=String.valueOf((int) Math.floor(i.usageRate*100))+"%";
			a++;
		}
		refreshtable();
	}

	//The class extends TableCellRender,however,due to the poor vision,I choose to change the way to show table in project3.0
	 private class Showplayerimg extends DefaultTableCellRenderer{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private int Count;
		private String Name;
		private JLabel img;
		
		public Showplayerimg(int count,String name) {
			Count=count;
			Name=name;
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if(row==Count){
			ImageIcon Icon=new ImageIcon("images/players/portrait_long/"+Name+".png");
			img=new JLabel(Icon);
			}
			return img;
		}
	}
	 //reference
	/*
	 * case "ATL":
			return "老鹰 Atlanta-Hawks";
		case "CHA":
			return "黄蜂 Charlotte-Hornets";
		case "MIA":
			return "热火 Miami-Heat";
		case "ORL":
			return "魔术 Orlando-Magic";
		case "WAS":
			return "奇才 Washington-Wizards";
			
		case "CHI":
			return "公牛 Chicago-Bulls";
		case "CLE":
			return "骑士 Cleveland-Cavaliers";
		case "DET":
			return "活塞 Detroit-Pistons";
		case "IND":
			return "步行者 Indiana-Pacers";
		case "MIL":
			return "雄鹿 Milwaukee-Bucks";
			
		case "BOS":
			return "凯尔特人 Boston-Celtics";
		case "BKN":
			return "篮网 Brooklyn-Nets";
		case "NYK":
			return "尼克斯 New York-Knicks";
		case "PHI":
			return "76人 Philadelphia-76ers";
		case "TOR":
			return "猛龙 Toronto-Raptors";
			
			
		case "GSW":
			return "勇士 Golden State-Warriors";
		case "LAC":
			return "快船 Los Angeles-Clippers";
		case "LAL":
			return "湖人 Los Angeles-Lakers";
		case "PHX":
			return "太阳 Phoenix-Suns";
		case "SAC":
			return "国王 Sacramento-Kings";
			
		case "DEN":
			return "掘金 Denver-Nuggets";
		case "MIN":
			return "森林狼 Minnesota-Timberwolves";
		case "OKC":
			return "雷霆 Oklahoma City-Thunder";
		case "POR":
			return "开拓者 Portland-Trail Blazers";
		case "UTA":
			return "勇士 Utah-Jazz";
			
		case "DAL":
			return "小牛 Dallas-Mavericks";
		case "HOU":
			return "火箭 Houston-Rockets";
		case "MEM":
			return "灰熊 Memphis-Grizzlies";
		case "NOP":
			return "鹈鹕 New Orleans-Pelicans";
		case "NOH":
			return "鹈鹕 New Orleans-Pelicans";
		case "SAS":
			return "马刺 San Antonio-Spurs";
	 * 
	 * 
	 */
	
	private String switchTeamName(String name){
		switch(name){
		case "ATL":
			return "老鹰 - Hawks";
		case "CHA":
			return "黄蜂 - Hornets";
		case "MIA":
			return "热火 - Heat";
		case "ORL":
			return "魔术 - Magic";
		case "WAS":
			return "奇才 - Wizards";
			
		case "CHI":
			return "公牛 - Bulls";
		case "CLE":
			return "骑士 - Cavaliers";
		case "DET":
			return "活塞 - Pistons";
		case "IND":
			return "步行者 - Pacers";
		case "MIL":
			return "雄鹿 - Bucks";
			
		case "BOS":
			return "凯尔特人 - Celtics";
		case "BKN":
			return "篮网 - Nets";
		case "NYK":
			return "尼克斯 - Knicks";
		case "PHI":
			return "76人 - 76ers";
		case "TOR":
			return "猛龙 - Raptors";
			
			
		case "GSW":
			return "勇士 - Warriors";
		case "LAC":
			return "快船 - Clippers";
		case "LAL":
			return "湖人 - Lakers";
		case "PHX":
			return "太阳 - Suns";
		case "SAC":
			return "国王 - Kings";
			
		case "DEN":
			return "掘金 - Nuggets";
		case "MIN":
			return "森林狼 - Timberwolves";
		case "OKC":
			return "雷霆 - Thunder";
		case "POR":
			return "开拓者 - Trail Blazers";
		case "UTA":
			return "勇士 - Jazz";
			
		case "DAL":
			return "小牛 - Mavericks";
		case "HOU":
			return "火箭 - Rockets";
		case "MEM":
			return "灰熊 - Grizzlies";
		case "NOP":
			return "鹈鹕 - Pelicans";
		case "NOH":
			return "鹈鹕 - Pelicans";
		case "SAS":
			return "马刺 - Spurs";
		default :
				return null;
		}
	}
	
	//表格配置
    public void table1_config(){
		//------------------------------表格基本属性--------------------------
		for(int i=0;i<playerinfo1.length;i++){
			playerinfo1[i][0]=i+1;
		}
		//表格属性设置
		playertable1=new JTable(playerinfo1, columnName1){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		playertable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		playertable1.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) playertable1.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		playertable1.setFont(PTPre.CellFont);
		playertable1.setForeground(PTPre.CellFg);
		playertable1.getTableHeader().setFont(PTPre.HeaderFont);
		playertable1.getTableHeader().setForeground(PTPre.TableFg);
		playertable1.getTableHeader().setOpaque(false);
		playertable1.getTableHeader().setBackground(PTPre.TableBg);
		//去除边框
		playertable1.setBorder(null);
		//设置透明
//		playertable1.setOpaque(false);

		//按行修改表格背景
		TableColumnModel model = playertable1.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		playertable1.setShowHorizontalLines(false);
		playertable1.setShowVerticalLines(false);
		//设置选中颜色
		playertable1.setSelectionBackground(PTPre.LineSelected);

		//设置行高
		playertable1.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH1.length;i++){
			playertable1.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH1[i]);
		}
		//-----------------------------------------------------------------
		//添加table表头点击事件
		playertable1.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				HeaderColumn=playertable1.columnAtPoint(e.getPoint());
				String orderSource=playertable1.getColumnName(HeaderColumn);
				//System.out.println(orderSource);
				if(!orderSource.equals("排名")){
					message.setText("当前排序依据:"+orderSource);
					judgeOrderSource(orderSource,(String) switchbox.getSelectedItem(),switchseason((String) season.getSelectedItem()));
				}

			}
		});

		playertable1.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				   int row= playertable1.getSelectedRow();
	               int column= playertable1.getSelectedColumn();
	               if(column==1){
	               //得到选中的单元格的值，表格中都是字符串
	               Object value= playertable1.getValueAt(row, column);
	               PlayerInfoPanel pip=new PlayerInfoPanel(Frame,String.valueOf(value),panelToRemove);
	               Frame.remove(panelToRemove);
	               Frame.add(pip);
	               Frame.repaint();
	               }
				 }
		});
		
	}
    
    public void table2_config(){
		//------------------------------表格基本属性--------------------------
		for(int i=0;i<playerinfo2.length;i++){
			playerinfo2[i][0]=i+1;
		}
		//表格属性设置
		playertable2=new JTable(playerinfo2, columnName2){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		playertable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		playertable2.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) playertable2.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		playertable2.setFont(PTPre.CellFont);
		playertable2.setForeground(PTPre.CellFg);
		playertable2.getTableHeader().setFont(PTPre.HeaderFont);
		playertable2.getTableHeader().setForeground(PTPre.TableFg);
		playertable2.getTableHeader().setOpaque(false);
		playertable2.getTableHeader().setBackground(PTPre.TableBg);
		//去除边框
		playertable2.setBorder(null);
		//设置透明
//		playertable2.setOpaque(false);

		//按行修改表格背景
		TableColumnModel model = playertable2.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		playertable2.setShowHorizontalLines(false);
		playertable2.setShowVerticalLines(false);
		//设置选中颜色
		playertable2.setSelectionBackground(PTPre.LineSelected);

		//设置行高
		playertable2.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH2.length;i++){
			playertable2.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH2[i]);
		}
		
		//添加table表头点击事件
		playertable2.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				HeaderColumn=playertable2.columnAtPoint(e.getPoint());
				String orderSource=playertable2.getColumnName(HeaderColumn);
				//System.out.println(orderSource);
				if(!orderSource.equals("排名")){
					message.setText("当前排序依据:"+orderSource);
					judgeOrderSource(orderSource,(String) switchbox.getSelectedItem(),switchseason((String) season.getSelectedItem()));
				}

			}
		});

		playertable2.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				   int row= playertable2.getSelectedRow();
	               int column= playertable2.getSelectedColumn();
	               if(column==1){
	               //得到选中的单元格的值，表格中都是字符串
	               Object value= playertable1.getValueAt(row, column);
	               PlayerInfoPanel pip=new PlayerInfoPanel(Frame,String.valueOf(value),panelToRemove);
	               Frame.remove(panelToRemove);
	               Frame.add(pip);
	               Frame.repaint();
	               }
				 }
		});
		
	}
    
    
    public void table3_config(){
		//------------------------------表格基本属性--------------------------
		for(int i=0;i<playerinfo3.length;i++){
			playerinfo3[i][0]=i+1;
		}
		//表格属性设置
		playertable3=new JTable(playerinfo3, columnName3){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		playertable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		playertable3.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) playertable3.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		playertable3.setFont(PTPre.CellFont);
		playertable3.setForeground(PTPre.CellFg);
		playertable3.getTableHeader().setFont(PTPre.HeaderFont);
		playertable3.getTableHeader().setForeground(PTPre.TableFg);
		playertable3.getTableHeader().setOpaque(false);
		playertable3.getTableHeader().setBackground(PTPre.TableBg);
		//去除边框
		playertable3.setBorder(null);
		//设置透明
//		playertable3.setOpaque(false);

		//按行修改表格背景
		TableColumnModel model = playertable3.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		playertable3.setShowHorizontalLines(false);
		playertable3.setShowVerticalLines(false);
		//设置选中颜色
		playertable3.setSelectionBackground(PTPre.LineSelected);

		//设置行高
		playertable3.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH3.length;i++){
			playertable3.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH3[i]);
		}
		
		//-----------------------------------------------------------------
		//添加table表头点击事件
		playertable3.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				HeaderColumn=playertable3.columnAtPoint(e.getPoint());
				String orderSource=playertable3.getColumnName(HeaderColumn);
				//System.out.println(orderSource);
				if(!orderSource.equals("排名")){
					message.setText("当前排序依据:"+orderSource);
					judgeOrderSource(orderSource,(String) switchbox.getSelectedItem(),switchseason((String) season.getSelectedItem()));
				}

			}
		});

		playertable3.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				   int row= playertable3.getSelectedRow();
	               int column= playertable3.getSelectedColumn();
	               if(column==1){
	               //得到选中的单元格的值，表格中都是字符串
	               Object value= playertable1.getValueAt(row, column);
	               PlayerInfoPanel pip=new PlayerInfoPanel(Frame,String.valueOf(value),panelToRemove);
	               Frame.remove(panelToRemove);
	               Frame.add(pip);
	               Frame.repaint();
	               }
				 }
		});
		
	}
    

	public void refreshtable(){
		table1_config();
		table2_config();
		table2_config();
		if(first.isSelected())
			players.setViewportView(playertable1);
		else if(second.isSelected())
			players.setViewportView(playertable2);
		else if(third.isSelected())
			players.setViewportView(playertable3);
		Frame.repaint();
	}

	private void judgeOrderSource(String ordersource,String AvgOrTotal,String season){
		ArrayList<PlayerTechVO> orderPlayerTechVO = null;
		if(order_Asc.isSelected()){
		switch(ordersource){

		case "球员名称":
			orderPlayerTechVO=importdata.getPlayerTechAscend("name",season);
			break;
		case "所属球队":
			orderPlayerTechVO=importdata.getPlayerTechAscend("team",season);
			break;
		case "参赛":
			orderPlayerTechVO=importdata.getPlayerTechAscend("gamenum",season);
			break;
		case "先发":
			orderPlayerTechVO=importdata.getPlayerTechAscend("startingnum",season);
			break;
		case "篮板":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("reboundave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("rebound",season);
			}
			break;
		case "助攻":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("secondaryattackave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("secondaryattack",season);
			}
			break;
		case "时间":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("timeave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("time",season);
			}
			break;
			
		case "命中%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("shotinrate",season);
			break;
		case "三分%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("threeshotinrate",season);
			break;
		case "罚球%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("penaltyshotinrate",season);
			break;
			
		case "进攻":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("offensivenumave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("offensivenum",season);
				}
			break;
		case "防守":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("defensivenumave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("defensivenum",season);
				}
			break;
		case "抢断":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("stealave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("steal",season);
				}
			break;
		case "盖帽":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("blockshotave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("blockshot",season);
				}
			break;
		case "失误":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("faultave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("fault",season);
				}
			break;
		case "犯规":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("foulave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("foul",season);
				}
			break;
		case "得分":
			if(AvgOrTotal.equals("场均数据")){
				orderPlayerTechVO=importdata.getPlayerTechAscend("scoreave",season);
			}else{
				orderPlayerTechVO=importdata.getPlayerTechAscend("score",season);
			}
			break;
//		case "效率":
//			orderPlayerTechVO=importdata.getPlayerTechAscend("efficiency",season);
//			break;
		case "GmSc 效率":
			orderPlayerTechVO=importdata.getPlayerTechAscend("gmscefficiency",season);
			break;
		case "真实命中%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("trueshotinrate",season);
			break;
		case "投篮效率":
			orderPlayerTechVO=importdata.getPlayerTechAscend("shootingefficiency",season);
			break;
		case "篮板%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("reboundrate",season);
			break;
		case "进攻篮板%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("offensivereboundrate",season);
			break;
		case "防守篮板%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("defensivereboundrate",season);
			break;
		case "助攻%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("secondaryattackrate",season);
			break;
		case "抢断%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("stealrate",season);
			break;
		case "盖帽%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("blockshotrate",season);
			break;
		case "失误%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("faultrate",season);
			break;
		case "使用%":
			orderPlayerTechVO=importdata.getPlayerTechAscend("usagerate",season);
			break;
		}
		
		}
		if(order_Des.isSelected()){
			switch(ordersource){
			case "球员名称":
				orderPlayerTechVO=importdata.getPlayerTechDescend("name",season);
				break;
			case "所属球队":
				orderPlayerTechVO=importdata.getPlayerTechDescend("team",season);
				break;
			case "参赛":
				orderPlayerTechVO=importdata.getPlayerTechDescend("gamenum",season);
				break;
			case "先发":
				orderPlayerTechVO=importdata.getPlayerTechDescend("startingnum",season);
				break;
			case "篮板":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("reboundave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("rebound",season);
				}
				break;
			case "助攻":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("secondaryattackave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("secondaryattack",season);
				}
				break;
			case "时间":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("timeave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("time",season);
				}
				break;
				
			case "命中%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("shotinrate",season);
				break;
			case "三分%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("threeshotinrate",season);
				break;
			case "罚球%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("penaltyshotinrate",season);
				break;
				
			case "进攻":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("offensivenumave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("offensivenum",season);
				}
				break;
			case "防守":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("defensivenumave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("defensivenum",season);
				}
				break;
			case "抢断":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("stealave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("steal",season);
				}
				break;
			case "盖帽":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("blockshotave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("blockshot",season);
				}
				break;
			case "失误":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("faultave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("fault",season);
				}
				break;
			case "犯规":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("foulave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("foul",season);
				}
				break;
			case "得分":
				if(AvgOrTotal.equals("场均数据")){
					orderPlayerTechVO=importdata.getPlayerTechDescend("scoreave",season);
				}else{
					orderPlayerTechVO=importdata.getPlayerTechDescend("score",season);
				}
				break;
//			case "效率":
//				orderPlayerTechVO=importdata.getPlayerTechDescend("efficiency",season);
//				break;
			case "GmSc 效率":
				orderPlayerTechVO=importdata.getPlayerTechDescend("gmscefficiency",season);
				break;
			case "真实命中%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("trueshotinrate",season);
				break;
			case "投篮效率":
				orderPlayerTechVO=importdata.getPlayerTechDescend("shootingefficiency",season);
				break;
			case "篮板%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("reboundrate",season);
				break;
			case "进攻篮板%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("offensivereboundrate",season);
				break;
			case "防守篮板%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("defensivereboundrate",season);
				break;
			case "助攻%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("secondaryattackrate",season);
				break;
			case "抢断%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("stealrate",season);
				break;
			case "盖帽%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("blockshotrate",season);
				break;
			case "失误%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("faultrate",season);
				break;
			case "使用%":
				orderPlayerTechVO=importdata.getPlayerTechDescend("usagerate",season);
				break;
			}
		}
		
		if(AvgOrTotal.equals("赛季总数据")){
			handleTotalData(orderPlayerTechVO);
			}else if(AvgOrTotal.equals("场均数据")){
				handleAverageData(orderPlayerTechVO);
			}
	}
	
	//滑动面板配置
	public void scrollpane_config(){
		//滑动面板信息
		players=new JScrollPane(playertable1);
		players.setBounds(WIDTH-TABLEWIDTH-e_space-space,HEIGHT-TABLEHEIGHT-e_space-space,TABLEWIDTH,TABLEHEIGHT);
		players.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		players.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		players.setVisible(true);
		players.setOpaque(false);
		players.getViewport().setOpaque(false);
//		players.getViewport().setBorder(null);
		players.setBorder(null);

		if (players.getCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER) == null) {
			Component component = new JLabel("") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					Paint oldPaint = g2.getPaint();
					Rectangle bounds = getBounds();
					Paint backgroupRectPaint = new GradientPaint(0, 0, PTPre.LineSelected,
							bounds.width, bounds.height, PTPre.LineSelected);
					g2.setPaint(backgroupRectPaint);
					g2.fillRect(0, 0, bounds.width, bounds.height);
					g2.setPaint(oldPaint);
				}
			};
			players.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, component);
		}

		if (players.getCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER) == null) {
			Component component = new JLabel("") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					Paint oldPaint = g2.getPaint();
					Rectangle bounds = getBounds();
					Paint backgroupRectPaint = new GradientPaint(0, 0, PTPre.TableBg,
							bounds.width, bounds.height, PTPre.TableBg);
					g2.setPaint(backgroupRectPaint);
					g2.fillRect(0, 0, bounds.width, bounds.height);
					g2.setPaint(oldPaint);
				}
			};
			players.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER,component);
		}
		this.add(players);
	}

	//重载单元格标准类,用于改变单元格背景颜色
	private class RowRenderer extends DefaultTableCellRenderer 
	{
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable t, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) 
		{
			//单元格居中
			setHorizontalAlignment(JLabel.CENTER);
//			setOpaque(false);
			//设置奇偶行的背景色
			if (row % 2 == 0)
				setBackground(PTPre.EvenTableLine);
			else
				setBackground(PTPre.OddTableLine);
			

			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);
		}
	}

	
	private String switchseason(String season){
		String result=null;
		String[] temp=season.split(" ");
		if(temp[1].equals("Regular")){
		result=temp[0]+" 常规赛";
		}else if(temp[1].equals("Postseason")){
			result=temp[0]+" 季后赛";
		}
		return result;
	}
	
	
	//绘制球员数据界面背景
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ImageIcon im1=new ImageIcon("images/system_img/main_bg.png");
		g.drawImage(im1.getImage(),0,0,this);
	}
	
	

	//按钮鼠标监听事件
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==trigger){
			if(!trigger.isSelected()){
				Thread show=new Thread(){
					public void run(){
						for(int i=0;i<letterbutton.length;i++){
							letterbutton[i].setVisible(true);
							try {
								Thread.sleep(15);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
			show.start();
			trigger.setSelected(true);
			}else{
				Thread hide=new Thread(){
					public void run(){
						for(int i=letterbutton.length-1;i>=0;i--){
							letterbutton[i].setVisible(false);
							try {
								Thread.sleep(15);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				hide.start();
				trigger.setSelected(false);
			}
		}
		
		
		if(arg0.getSource()==seniorsift){
			if(!seniorsift.isSelected()){
			seniorsift.setSelected(true);
			Frame.add(siftpanel);
			Frame.add(this);
			playertable1.setEnabled(false);
			playertable1.getTableHeader().setEnabled(false);
			playertable2.setEnabled(false);
			playertable2.getTableHeader().setEnabled(false);
			playertable3.setEnabled(false);
			playertable3.getTableHeader().setEnabled(false);
			Frame.repaint();
			}else{
				Frame.remove(siftpanel);
				seniorsift.setSelected(false);
				Frame.repaint();
			}
		}
		
		if(arg0.getSource()==add){
			seniorsiftpanel.addcondition();
			seniorsiftpanel.repaint();
		}
		if(arg0.getSource()==delete){
			seniorsiftpanel.deletecondition();
			seniorsiftpanel.repaint();
		}
		
		if(arg0.getSource()==commit){
			seniorsiftpanel.getScreeningCondition();
			//TODO test the screeningcondition and delete it when necessary
			for(int i=0;i<screeningconditions.size();i++){
				System.out.println(screeningconditions.get(i).position+"--"+screeningconditions.get(i).division+"--"+screeningconditions.get(i).condition+"--"+screeningconditions.get(i).number);
			}
			ArrayList<PlayerTechVO> siftVO=importdata.sift(screeningconditions,switchseason((String) season.getSelectedItem()));
			playerinfo1=new Object[siftVO.size()][columnName1.length];
			playerinfo2=new Object[siftVO.size()][columnName2.length];
			playerinfo2=new Object[siftVO.size()][columnName3.length];
			String switchboxsel=(String) switchbox.getSelectedItem();
			if(switchboxsel.equals("赛季总数据")){
				handleTotalData(siftVO);
			}else if(switchboxsel.equals("场均数据")){
				handleAverageData(siftVO);
			}
			
			
		}
		
		if(arg0.getSource()==reset){
			Frame.remove(this);
			PlayerTechPanel ptp=new PlayerTechPanel(Frame);
			Frame.add(ptp);
			Frame.repaint();
		}
		
		if(arg0.getSource()==first){
			if(!first.isSelected()){
			first.setSelected(true);
			second.setSelected(false);
			third.setSelected(false);
			refreshtable();
			}
		}
		if(arg0.getSource()==second){
			if(!second.isSelected()){
			first.setSelected(false);
			second.setSelected(true);
			third.setSelected(false);
			refreshtable();
			}
		}
		if(arg0.getSource()==third){
			if(!third.isSelected()){
			first.setSelected(false);
			second.setSelected(false);
			third.setSelected(true);
			refreshtable();
			}
		}
	}

}
