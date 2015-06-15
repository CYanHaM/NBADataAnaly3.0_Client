package presentation.teamui;


import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import presentation.hotspotui.HotPlayerToday;
import presentation.matchui.MatchPanel;
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerTechPanel;
import presentation.preset.StatPre;
import presentation.preset.TeamTechPre;
import PO.TeamTechPO;
import TypeEnum.TeamTechEnum;
import VO.TeamTechVO;
import VO.TeamVO;


public class TeamTechPanel extends JPanel implements ActionListener{
	/**
	 * 球队统计数据界面
	 * @author blisscry
	 * @date 2015年3月21日16:35:28
	 * @version 1.6
	 */
	private static final long serialVersionUID = 1L;

	//-------------------------界面常量-------------------
	public static int WIDTH=1100;
	public static int HEIGHT=700;
	//定义边缘透明空白区域边界大小，单位px
	public static int e_space=10;
	//定义空出位置大小
	private static int space=20;
	//设置球队总数常量
	private static int TEAMNUM=30;
	
	//表格大小
	private static int TABLEWIDTH=900;
	private static int TABLEHEIGHT=520;
	//表格行高
	private static int ROWHEIGHT=28;

	//下拉框大小
	private static int BOXWIDTH=160;
	private static int BOXHEIGHT=30;
	
	private JComboBox<String> season;
	private int season_w=200;
	private int season_h=30;
	//----------------------------------------------------

	//-------------------------界面组件--------------------
	//设置表格属性
	private static JTable teamtable1;
	private static JTable teamtable2;
	private static JTable teamtable3;
	private static Object[][] teaminfo1;
	private static Object[][] teaminfo2;
	private static Object[][] teaminfo3;
	private JScrollPane teams;
	private String[] columnName1={"排名","球队名称","得分","篮板","助攻","抢断","盖帽","失误","命中%","三分%","罚球%"};
	private String[] columnName2={"排名","球队名称","场数","命中","出手","三分命中","三分出手","罚球命中","罚球出手","进攻篮板","防守篮板"};
	private String[] columnName3={"排名","球队名称","胜率","进攻回合","进攻效率","防守效率","篮板效率","抢断效率","助攻效率"};
	//表格列宽
	private static int[] COLUMNWIDTH1={50,210,67,67,70,67,70,70,70,70,70};
	private static int[] COLUMNWIDTH2={50,210,67,67,70,67,70,70,70,70,70};
	private static int[] COLUMNWIDTH3={50,210,85,87,85,85,87,95,96};
	
	private JButton first;
	private JButton second;
	private JButton third;
	
	//总数据与场均数据切换下拉框
	private JComboBox<String> switchbox;
//	private JComboBox<String> search;
	
	private JRadioButton order_Asc;
	private JRadioButton order_Des;
	private ButtonGroup group;
	
	private JLabel message;
	
	//----------------------------------------------------
	public TeamTechPre TTPre;
	public ImportTeam importdata;
	public ArrayList<TeamTechVO> initial_data;

	public int HeaderColumn=0;
	public JFrame Frame;
	public JPanel panelToRemove;
	public TeamTechPanel(JFrame frame){
		Frame=frame;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		this.setOpaque(false);
		
		//创建颜色预设对象
		TTPre=new TeamTechPre();
		importdata=new ImportTeam();
		
		//添加下拉框
		addbox();
		
		initdata();
		//加载初始表格，显示队伍总数据
//		handleinitial(initial_data);

		//加载表格配置
		table1_config();
		table2_config();
		table3_config();
		//加载滑动面板配置
		scrollpane_config();

		//添加单选按钮
		addradiobutton();
		//添加侧边栏按钮
		addbutton();
		
		message=new JLabel();
		message.setBounds(WIDTH-TABLEWIDTH-e_space-space+BOXWIDTH+130, HEIGHT-TABLEHEIGHT-e_space-space-42, 200, 15);
		message.setFont(TTPre.switchbox);
		message.setForeground(TTPre.TableFg);
		
		this.add(message);
		
		this.repaint();
	}

	
	private void addbutton(){
		first=new JButton(new ImageIcon("images/system_img/1_1.png"));
		first.setBounds(WIDTH-TABLEWIDTH-e_space-space+(BOXWIDTH+10)*4+35+110, HEIGHT-TABLEHEIGHT-e_space-space-50+20, 20, 20);
		first.setBorderPainted(false);
		first.setContentAreaFilled(false);
		first.setFocusPainted(false);
		first.setRolloverIcon(new ImageIcon("images/system_img/1_2.png"));
		first.setPressedIcon(new ImageIcon("images/system_img/1_3.png"));
		first.setSelectedIcon(new ImageIcon("images/system_img/1_3.png"));
		first.setSelected(true);
		first.addActionListener(this);
		second=new JButton(new ImageIcon("images/system_img/2_1.png"));
		second.setBounds(WIDTH-TABLEWIDTH-e_space-space+(BOXWIDTH+10)*4+35+20+110, HEIGHT-TABLEHEIGHT-e_space-space-50+20, 20, 20);
		second.setBorderPainted(false);
		second.setContentAreaFilled(false);
		second.setFocusPainted(false);
		second.setRolloverIcon(new ImageIcon("images/system_img/2_2.png"));
		second.setPressedIcon(new ImageIcon("images/system_img/2_3.png"));
		second.setSelectedIcon(new ImageIcon("images/system_img/2_3.png"));
		second.addActionListener(this);
		third=new JButton(new ImageIcon("images/system_img/3_1.png"));
		third.setBounds(WIDTH-TABLEWIDTH-e_space-space+(BOXWIDTH+10)*4+35+20*2+110, HEIGHT-TABLEHEIGHT-e_space-space-50+20, 20, 20);
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

	private void addbox(){
		//下拉框
		switchbox=new JComboBox<String>();
		switchbox.setFocusable(false);
		switchbox.setBackground(TTPre.LineSelected);
		switchbox.addItem("赛季总数据");
		switchbox.addItem("场均数据");
		switchbox.setBounds(WIDTH-TABLEWIDTH-e_space-space,HEIGHT-TABLEHEIGHT-e_space-space-50,BOXWIDTH,BOXHEIGHT);
		switchbox.setFont(TTPre.switchbox);
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
//		ArrayList<String> seasonlist=importdata.getTeamSeasonList();
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
		season.setBounds(WIDTH-TABLEWIDTH-e_space-space,HEIGHT-TABLEHEIGHT-e_space-space-85,season_w,season_h);
		season.setFocusable(false);
		season.setBackground(StatPre.indefaultcolor);
		season.setFont(TTPre.switchbox);
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
	
	private void addradiobutton(){
		order_Asc=new JRadioButton("升序");
		order_Asc.setFont(TTPre.switchbox);
		order_Asc.setForeground(TTPre.TableFg);
		order_Asc.setBorderPainted(false);
		order_Asc.setContentAreaFilled(false);
		order_Asc.setFocusPainted(false);
//		order_Asc.setSelected(true);
		order_Asc.setBounds(WIDTH-TABLEWIDTH-e_space-space+BOXWIDTH+10,HEIGHT-TABLEHEIGHT-e_space-space-42,50,15);
		
		order_Des=new JRadioButton("降序");
		order_Des.setFont(TTPre.switchbox);
		order_Des.setForeground(TTPre.TableFg);
		order_Des.setBorderPainted(false);
		order_Des.setContentAreaFilled(false);
		order_Des.setFocusPainted(false);
		order_Des.setSelected(true);
		order_Des.setBounds(WIDTH-TABLEWIDTH-e_space-space+BOXWIDTH+60,HEIGHT-TABLEHEIGHT-e_space-space-42,50,15);
		
		group=new ButtonGroup();
		group.add(order_Asc);
		group.add(order_Des);
		
		this.add(order_Asc);
		this.add(order_Des);
	}
	
	//----------------------initial & different methods------------
	private void initdata(){
		//TODO delete the test
//		initial_data=importdata.getTeamTechAscend(TeamTechEnum.name,(String)season.getSelectedItem());

//		teaminfo1=new Object[initial_data.size()][columnName1.length];
//		teaminfo2=new Object[initial_data.size()][columnName2.length];
//		teaminfo3=new Object[initial_data.size()][columnName3.length];
		teaminfo1=new Object[TEAMNUM][columnName1.length];
		teaminfo2=new Object[TEAMNUM][columnName2.length];
		teaminfo3=new Object[TEAMNUM][columnName3.length];
	}
	
	private void handleinitial(ArrayList<TeamTechVO> totaldata){
		int a=0;
		DecimalFormat f = new DecimalFormat("#,##0.0000");  

		for(TeamTechVO i:totaldata){
			teaminfo1[a][1]=switchTeamName(i.name);
			teaminfo1[a][2]=i.score;
			teaminfo1[a][3]=i.rebound;
			teaminfo1[a][4]=i.secondaryAttack;
			teaminfo1[a][5]=i.steal;
			teaminfo1[a][6]=i.blockShot;
			teaminfo1[a][7]=i.fault;
//			teaminfo1[a][8]=i.foul;
			teaminfo1[a][8]=String.valueOf((int) Math.floor(i.shotInRate*100))+"%";
			teaminfo1[a][9]=String.valueOf((int) Math.floor(i.threeShotInRate*100))+"%";
			teaminfo1[a][10]=String.valueOf((int) Math.floor(i.penaltyShotInRate*100))+"%";
			
			teaminfo2[a][1]=switchTeamName(i.name);
			teaminfo2[a][2]=i.gameNum;
			teaminfo2[a][3]=i.shotInNum;
			teaminfo2[a][4]=i.shotNum;
			teaminfo2[a][5]=i.threeShotInNum;
			teaminfo2[a][6]=i.threeShotNum;
			teaminfo2[a][7]=i.penaltyShotInNum;
			teaminfo2[a][8]=i.penaltyShotNum;
			teaminfo2[a][9]=i.offensiveRebound;
			teaminfo2[a][10]=i.defensiveRebound;
			
			teaminfo3[a][1]=switchTeamName(i.name);
			teaminfo3[a][2]=String.valueOf((int) Math.floor(i.winningRate*100))+"%";
			teaminfo3[a][3]=(int) Math.floor(i.offensiveRound);
			teaminfo3[a][4]=String.valueOf((int) Math.floor(i.offensiveEfficiency))+"%";
			teaminfo3[a][5]=String.valueOf((int) Math.floor(i.defensiveEfficiency))+"%";
			teaminfo3[a][6]=String.valueOf((int) Math.floor(i.reboundEfficiency*100))+"%";
			teaminfo3[a][7]=String.valueOf((int) Math.floor(i.stealEfficiency))+"%";
			teaminfo3[a][8]=String.valueOf((int) Math.floor(i.secondaryAttackEfficiency))+"%";
			a++;
		}
	}
	
	private void handleTotalData(ArrayList<TeamTechVO> totaldata){
		DecimalFormat f = new DecimalFormat("#,##0.0000"); 
		int a=0;
		for(TeamTechVO i:totaldata){
			teaminfo1[a][1]=switchTeamName(i.name);
			teaminfo1[a][2]=i.score;
			teaminfo1[a][3]=i.rebound;
			teaminfo1[a][4]=i.secondaryAttack;
			teaminfo1[a][5]=i.steal;
			teaminfo1[a][6]=i.blockShot;
			teaminfo1[a][7]=i.fault;
//			teaminfo1[a][8]=i.foul;
			teaminfo1[a][8]=String.valueOf((int) Math.floor(i.shotInRate*100))+"%";
			teaminfo1[a][9]=String.valueOf((int) Math.floor(i.threeShotInRate*100))+"%";
			teaminfo1[a][10]=String.valueOf((int) Math.floor(i.penaltyShotInRate*100))+"%";
			
			teaminfo2[a][1]=switchTeamName(i.name);
			teaminfo2[a][2]=i.gameNum;
			teaminfo2[a][3]=i.shotInNum;
			teaminfo2[a][4]=i.shotNum;
			teaminfo2[a][5]=i.threeShotInNum;
			teaminfo2[a][6]=i.threeShotNum;
			teaminfo2[a][7]=i.penaltyShotInNum;
			teaminfo2[a][8]=i.penaltyShotNum;
			teaminfo2[a][9]=i.offensiveRebound;
			teaminfo2[a][10]=i.defensiveRebound;
			
			teaminfo3[a][1]=switchTeamName(i.name);
			teaminfo3[a][2]=String.valueOf((int) Math.floor(i.winningRate*100))+"%";
			teaminfo3[a][3]=(int) Math.floor(i.offensiveRound);
			teaminfo3[a][4]=String.valueOf((int) Math.floor(i.offensiveEfficiency))+"%";
			teaminfo3[a][5]=String.valueOf((int) Math.floor(i.defensiveEfficiency))+"%";
			teaminfo3[a][6]=String.valueOf((int) Math.floor(i.reboundEfficiency*100))+"%";
			teaminfo3[a][7]=String.valueOf((int) Math.floor(i.stealEfficiency))+"%";
			teaminfo3[a][8]=String.valueOf((int) Math.floor(i.secondaryAttackEfficiency))+"%";
			a++;
		}
		refreshtable();
	}

	private void handleAverageData(ArrayList<TeamTechVO> averagedata){
		DecimalFormat f = new DecimalFormat("#,##0.0000"); 
		int a=0;
		for(TeamTechVO i:averagedata){
			teaminfo1[a][1]=switchTeamName(i.name);
			teaminfo1[a][2]=i.scoreave;
			teaminfo1[a][3]=i.reboundave;
			teaminfo1[a][4]=i.secondaryAttackave;
			teaminfo1[a][5]=i.stealave;
			teaminfo1[a][6]=i.blockShotave;
			teaminfo1[a][7]=i.faultave;
//			teaminfo1[a][8]=i.foulave;
			teaminfo1[a][8]=String.valueOf((int) Math.floor(i.shotInRate*100))+"%";
			teaminfo1[a][9]=String.valueOf((int) Math.floor(i.threeShotInRate*100))+"%";
			teaminfo1[a][10]=String.valueOf((int) Math.floor(i.penaltyShotInRate*100))+"%";
			
			teaminfo2[a][1]=switchTeamName(i.name);
			teaminfo2[a][2]=i.gameNum;
			teaminfo2[a][3]=i.shotInNumave;
			teaminfo2[a][4]=i.shotNumave;
			teaminfo2[a][5]=i.threeShotInNumave;
			teaminfo2[a][6]=i.threeShotNumave;
			teaminfo2[a][7]=i.penaltyShotInNumave;
			teaminfo2[a][8]=i.penaltyShotNumave;
			teaminfo2[a][9]=i.offensiveReboundave;
			teaminfo2[a][10]=i.defensiveReboundave;
			
			teaminfo3[a][1]=switchTeamName(i.name);
			teaminfo3[a][2]=String.valueOf((int) Math.floor(i.winningRate*100))+"%";
			teaminfo3[a][3]=(int) Math.floor(i.offensiveRoundave);
			teaminfo3[a][4]=String.valueOf((int) Math.floor(i.offensiveEfficiency))+"%";
			teaminfo3[a][5]=String.valueOf((int) Math.floor(i.defensiveEfficiency))+"%";
			teaminfo3[a][6]=String.valueOf((int) Math.floor(i.reboundEfficiency*100))+"%";
			teaminfo3[a][7]=String.valueOf((int) Math.floor(i.stealEfficiency))+"%";
			teaminfo3[a][8]=String.valueOf((int) Math.floor(i.secondaryAttackEfficiency))+"%";
			a++;
		}
		refreshtable();
	}

	private String switchTeamName(String name){
		switch(name){
		case "ATL":
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
		default :
				return null;
		}
	}
	
	private String switchTeam(String name){
		switch(name){
		case "老鹰 Atlanta-Hawks":
			return "ATL";
		case "黄蜂 Charlotte-Hornets":
			return "CHA";
		case "热火 Miami-Heat":
			return "MIA";
		case "魔术 Orlando-Magic":
			return "ORL";
		case "奇才 Washington-Wizards":
			return "WAS";
			
		case "公牛 Chicago-Bulls":
			return "CHI";
		case "骑士 Cleveland-Cavaliers":
			return "CLE";
		case "活塞 Detroit-Pistons":
			return "DET";
		case "步行者 Indiana-Pacers":
			return "IND";
		case "雄鹿 Milwaukee-Bucks":
			return "MIL";
			
		case "凯尔特人 Boston-Celtics":
			return "BOS";
		case "篮网 Brooklyn-Nets":
			return "BKN";
		case "尼克斯 New York-Knicks":
			return "NYK";
		case "76人 Philadelphia-76ers":
			return "PHI";
		case "猛龙 Toronto-Raptors":
			return "TOR";
			
			
		case "勇士 Golden State-Warriors":
			return "GSW";
		case "快船 Los Angeles-Clippers":
			return "LAC";
		case "湖人 Los Angeles-Lakers":
			return "LAL";
		case "太阳 Phoenix-Suns":
			return "PHX";
		case "国王 Sacramento-Kings":
			return "SAC";
			
		case "掘金 Denver-Nuggets":
			return "DEN";
		case "森林狼 Minnesota-Timberwolves":
			return "MIN";
		case "雷霆 Oklahoma City-Thunder":
			return "OKC";
		case "开拓者 Portland-Trail Blazers":
			return "POR";
		case "勇士 Utah-Jazz":
			return "UTA";
			
		case "小牛 Dallas-Mavericks":
			return "DAL";
		case "火箭 Houston-Rockets":
			return "HOU";
		case "灰熊 Memphis-Grizzlies":
			return "MEM";
		case "鹈鹕 New Orleans-Pelicans":
			return "NOP";
		case "马刺 San Antonio-Spurs":
			return "SAS";
		default :
				return null;
		}
	}
	
	//表格配置
	public void table1_config(){
		//------------------------------表格基本属性--------------------------
		for(int i=0;i<TEAMNUM;i++){
			teaminfo1[i][0]=i+1;
		}
		//表格属性设置
		teamtable1=new JTable(teaminfo1, columnName1){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		teamtable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		teamtable1.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) teamtable1.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		teamtable1.setFont(TTPre.CellFont);
		teamtable1.setForeground(TTPre.CellFg);
		teamtable1.getTableHeader().setFont(TTPre.HeaderFont);
		teamtable1.getTableHeader().setForeground(TTPre.TableFg);
		teamtable1.getTableHeader().setOpaque(false);
		teamtable1.getTableHeader().setBackground(TTPre.TableBg);
		//去除边框
		teamtable1.setBorder(null);

		//按行修改表格背景
		TableColumnModel model = teamtable1.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		teamtable1.setShowHorizontalLines(false);
		teamtable1.setShowVerticalLines(false);
		//设置选中颜色
		teamtable1.setSelectionBackground(TTPre.LineSelected);
		
		//设置行高
		teamtable1.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH1.length;i++){
		teamtable1.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH1[i]);
		}
		
		//-----------------------------------------------------------------

		//添加table表头点击事件
		teamtable1.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				HeaderColumn=teamtable1.columnAtPoint(e.getPoint());
				String orderSource=teamtable1.getColumnName(HeaderColumn);
//				System.out.println(orderSource);
				if(!orderSource.equals("排名")&&!orderSource.equals("比赛场数")){
					message.setText("当前排序依据:"+orderSource);
				judgeOrderSource(orderSource,(String) switchbox.getSelectedItem(),switchseason((String)season.getSelectedItem()));
				}
				
			}
		});
		
		teamtable1.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				   int row= teamtable1.getSelectedRow();
	               int column= teamtable1.getSelectedColumn();
	               if(column==1){
	               //得到选中的单元格的值，表格中都是字符串
	               Object value= teamtable1.getValueAt(row, column);
	               TeamVO tvo=new TeamVO();
	               tvo.abbreviation=switchTeam(String.valueOf(value));
	               TeamPanel pip=new TeamPanel(tvo,Frame,panelToRemove);
	               jumpToPanel(pip);
	               
	               }
				 }
		});

	}
	
	public void table2_config(){
		//------------------------------表格基本属性--------------------------
//		for(int i=0;i<initial_data.size();i++){
//			teaminfo[i][0]=i+1;
//		}
		for(int i=0;i<TEAMNUM;i++){
			teaminfo2[i][0]=i+1;
		}
		//表格属性设置
		teamtable2=new JTable(teaminfo2, columnName2){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		teamtable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		teamtable2.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) teamtable2.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		teamtable2.setFont(TTPre.CellFont);
		teamtable2.setForeground(TTPre.CellFg);
		teamtable2.getTableHeader().setFont(TTPre.HeaderFont);
		teamtable2.getTableHeader().setForeground(TTPre.TableFg);
		teamtable2.getTableHeader().setOpaque(false);
		teamtable2.getTableHeader().setBackground(TTPre.TableBg);
		//去除边框
		teamtable2.setBorder(null);

		//按行修改表格背景
		TableColumnModel model = teamtable2.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		teamtable2.setShowHorizontalLines(false);
		teamtable2.setShowVerticalLines(false);
		//设置选中颜色
		teamtable2.setSelectionBackground(TTPre.LineSelected);
		
		//设置行高
		teamtable2.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH2.length;i++){
		teamtable2.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH2[i]);
		}
		
		//-----------------------------------------------------------------

		//添加table表头点击事件
		teamtable2.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				HeaderColumn=teamtable2.columnAtPoint(e.getPoint());
				String orderSource=teamtable2.getColumnName(HeaderColumn);
//				System.out.println(orderSource);
				if(!orderSource.equals("排名")&&!orderSource.equals("比赛场数")){
					message.setText("当前排序依据:"+orderSource);
				judgeOrderSource(orderSource,(String) switchbox.getSelectedItem(),switchseason((String)season.getSelectedItem()));
				}
				
			}
		});
		
		teamtable2.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				   int row= teamtable2.getSelectedRow();
	               int column= teamtable2.getSelectedColumn();
	               if(column==1){
	               //得到选中的单元格的值，表格中都是字符串
	               Object value= teamtable2.getValueAt(row, column);
	               TeamVO tvo=new TeamVO();
	               tvo.abbreviation=switchTeam(String.valueOf(value));
	               TeamPanel pip=new TeamPanel(tvo,Frame,panelToRemove);
	               jumpToPanel(pip);
	               }
				 }
		});

	}
	
	public void table3_config(){
		//------------------------------表格基本属性--------------------------
//		for(int i=0;i<initial_data.size();i++){
//			teaminfo[i][0]=i+1;
//		}
		for(int i=0;i<TEAMNUM;i++){
			teaminfo3[i][0]=i+1;
		}
		//表格属性设置
		teamtable3=new JTable(teaminfo3, columnName3){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		teamtable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		teamtable3.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) teamtable3.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		teamtable3.setFont(TTPre.CellFont);
		teamtable3.setForeground(TTPre.CellFg);
		teamtable3.getTableHeader().setFont(TTPre.HeaderFont);
		teamtable3.getTableHeader().setForeground(TTPre.TableFg);
		teamtable3.getTableHeader().setOpaque(false);
		teamtable3.getTableHeader().setBackground(TTPre.TableBg);
		//去除边框
		teamtable3.setBorder(null);

		//按行修改表格背景
		TableColumnModel model = teamtable3.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		teamtable3.setShowHorizontalLines(false);
		teamtable3.setShowVerticalLines(false);
		//设置选中颜色
		teamtable3.setSelectionBackground(TTPre.LineSelected);
		
		//设置行高
		teamtable3.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH3.length;i++){
		teamtable3.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH3[i]);
		}
		
		//-----------------------------------------------------------------

		//添加table表头点击事件
		teamtable3.getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				HeaderColumn=teamtable3.columnAtPoint(e.getPoint());
				String orderSource=teamtable3.getColumnName(HeaderColumn);
//				System.out.println(orderSource);
				if(!orderSource.equals("排名")&&!orderSource.equals("比赛场数")){
					message.setText("当前排序依据:"+orderSource);
				judgeOrderSource(orderSource,(String) switchbox.getSelectedItem(),switchseason((String)season.getSelectedItem()));
				}
				
			}
		});
		
		teamtable3.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				   int row= teamtable3.getSelectedRow();
	               int column= teamtable3.getSelectedColumn();
	               if(column==1){
	               //得到选中的单元格的值，表格中都是字符串
	               Object value= teamtable3.getValueAt(row, column);
	               TeamVO tvo=new TeamVO();
	               tvo.abbreviation=switchTeam(String.valueOf(value));
	               TeamPanel pip=new TeamPanel(tvo,Frame,panelToRemove);
	               jumpToPanel(pip);
	               }
				 }
		});

	}
	
	public void refreshtable(){
		table1_config();
		table2_config();
		table3_config();
		if(first.isSelected())
			teams.setViewportView(teamtable1);
		else if(second.isSelected())
			teams.setViewportView(teamtable2);
		else if(third.isSelected())
			teams.setViewportView(teamtable3);
		Frame.repaint();
	}

	//"排名","球队名称","得分","篮板","助攻","抢断","盖帽","失误","犯规","命中%","三分%","罚球%"};
	//"排名","球队名称","场数","命中","出手","三分命中","三分出手","罚球命中","罚球出手","进攻篮板","防守篮板"};
	//"排名","球队名称","胜率","进攻回合","进攻效率","防守效率","篮板效率","抢断效率","助攻效率"};
	private void judgeOrderSource(String ordersource,String AvgOrTotal,String season){
		ArrayList<TeamTechVO> orderTeamTechVO = null;
		if(order_Asc.isSelected()){
		switch(ordersource){
		case "球队名称":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.name,season);
			break;
		case "命中":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.shotInNumave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.shotInNum,season);
			}
			break;
		case "出手":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.shotNumave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.shotNum,season);
			}
			break;
		case "三分命中":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.threeShotInNumave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.threeShotInNum,season);
			}
			break;
		case "三分出手":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.threeShotNumave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.threeShotNum,season);
			}
			break;
		case "罚球命中":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.penaltyShotInNumave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.penaltyShotInNum,season);
			}
			break;
		case "罚球出手":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.penaltyShotNumave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.penaltyShotNum,season);
			}
			break;
		case "进攻篮板":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.offensiveReboundave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.offensiveRebound,season);
			}
			break;
		case "防守篮板":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.defensiveReboundave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.defensiveRebound,season);
			}
			break;
		case "篮板":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.reboundave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.rebound,season);
			}
			break;
		case "助攻":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.secondaryAttackave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.secondaryAttack,season);
			}
			break;
		case "抢断":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.stealave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.steal,season);
			}
			break;
		case "盖帽":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.blockShotave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.blockShot,season);
			}
			break;
		case "失误":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.faultave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.fault,season);
			}
			break;
		case "犯规":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.foulave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.foul,season);
			}
			break;
		case "得分":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.scoreave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.score,season);
			}
			break;
		case "命中%":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.shotInRate,season);
			break;
		case "三分%":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.threeShotInRate,season);
			break;
		case "罚球%":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.penaltyShotInRate,season);
			break;
		case "胜率":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.winningRate,season);
			break;
		case "进攻回合":
			if(AvgOrTotal.equals("场均数据")){
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.offensiveRoundave,season);
			}else{
				orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.offensiveRound,season);
			}
			break;
		case "进攻效率":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.offensiveEfficiency,season);
			break;
		case "防守效率":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.defensiveEfficiency,season);
			break;
		case "篮板效率":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.reboundEfficiency,season);
			break;
		case "抢断效率":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.stealEfficiency,season);
			break;
		case "助攻效率":
			orderTeamTechVO=importdata.getTeamTechAscend(TeamTechEnum.secondaryAttackEfficiency,season);
			break;
		}
		
		//"排名","球队名称","得分","篮板","助攻","抢断","盖帽","失误","犯规","命中%","三分%","罚球%"};
		//"排名","球队名称","场数","命中","出手","三分命中","三分出手","罚球命中","罚球出手","进攻篮板","防守篮板"};
		//"排名","球队名称","胜率","进攻回合","进攻效率","防守效率","篮板效率","抢断效率","助攻效率"};
		
		}
		if(order_Des.isSelected()){
			switch(ordersource){
			case "球队名称":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.name,season);
				break;
			case "场数":
//				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.gameNum);
				break;
			case "命中":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.shotInNumave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.shotInNum,season);
				}
				break;
			case "出手":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.shotNumave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.shotNum,season);
				}
				break;
			case "三分命中":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.threeShotInNumave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.threeShotInNum,season);
				}
				break;
			case "三分出手":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.threeShotNumave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.threeShotNum,season);
				}
				break;
			case "罚球命中":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.penaltyShotInNumave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.penaltyShotInNum,season);
				}
				break;
			case "罚球出手":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.penaltyShotNumave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.penaltyShotNum,season);
				}
				break;
			case "进攻篮板":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.offensiveReboundave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.offensiveRebound,season);
				}
				break;
			case "防守篮板":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.defensiveReboundave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.defensiveRebound,season);
				}
				break;
			case "篮板":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.reboundave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.rebound,season);
				}
				break;
			case "助攻":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.secondaryAttackave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.secondaryAttack,season);
				}
				break;
			case "抢断":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.stealave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.steal,season);
				}
				break;
			case "盖帽":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.blockShotave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.blockShot,season);
				}
				break;
			case "失误":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.faultave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.fault,season);
				}
				break;
			case "犯规":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.foulave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.foul,season);
				}
				break;
			case "得分":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.scoreave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.score,season);
				}
				break;
			case "命中%":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.shotInRate,season);
				break;
			case "三分%":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.threeShotInRate,season);
				break;
			case "罚球%":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.penaltyShotInRate,season);
				break;
			case "胜率":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.winningRate,season);
				break;
			case "进攻回合":
				if(AvgOrTotal.equals("场均数据")){
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.offensiveRoundave,season);
				}else{
					orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.offensiveRound,season);
				}
				break;
			case "进攻效率":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.offensiveEfficiency,season);
				break;
			case "防守效率":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.defensiveEfficiency,season);
				break;
			case "篮板效率":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.reboundEfficiency,season);
				break;
			case "抢断效率":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.stealEfficiency,season);
				break;
			case "助攻效率":
				orderTeamTechVO=importdata.getTeamTechDescend(TeamTechEnum.secondaryAttackEfficiency,season);
				break;
			}
		}
		
		if(AvgOrTotal.equals("赛季总数据")){
			handleTotalData(orderTeamTechVO);
			}else if(AvgOrTotal.equals("场均数据")){
				handleAverageData(orderTeamTechVO);
			}
	}

	//滑动面板配置
	public void scrollpane_config(){
		//滑动面板信息
		teams=new JScrollPane(teamtable1);
		teams.setBounds(WIDTH-TABLEWIDTH-e_space-space,HEIGHT-TABLEHEIGHT-e_space-space,TABLEWIDTH,TABLEHEIGHT);
		teams.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		teams.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		teams.setVisible(true);
		teams.setOpaque(false);
		teams.getViewport().setOpaque(false);
		teams.setBorder(null);

		if (teams.getCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER) == null) {
			Component component = new JLabel("") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					Paint oldPaint = g2.getPaint();
					Rectangle bounds = getBounds();
					Paint backgroupRectPaint = new GradientPaint(0, 0, TTPre.LineSelected,
							bounds.width, bounds.height, TTPre.LineSelected);
					g2.setPaint(backgroupRectPaint);
					g2.fillRect(0, 0, bounds.width, bounds.height);
					g2.setPaint(oldPaint);
				}
			};
			teams.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, component);
		}

		if (teams.getCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER) == null) {
			Component component = new JLabel("") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					Paint oldPaint = g2.getPaint();
					Rectangle bounds = getBounds();
					Paint backgroupRectPaint = new GradientPaint(0, 0, TTPre.TableBg,
							bounds.width, bounds.height, TTPre.TableBg);
					g2.setPaint(backgroupRectPaint);
					g2.fillRect(0, 0, bounds.width, bounds.height);
					g2.setPaint(oldPaint);
				}
			};
			teams.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER,component);
		}
		
		this.add(teams);
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
			//设置奇偶行的背景色
			if (row % 2 == 0)
				setBackground(TTPre.EvenTableLine);
			else
				setBackground(TTPre.OddTableLine);
			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);
		}
	}
	
	public void jumpToPanel(final JPanel panelToJump){
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

	//绘制赛季数据界面背景
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ImageIcon im1=new ImageIcon("images/system_img/main_bg.png");
		g.drawImage(im1.getImage(),0,0,this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
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