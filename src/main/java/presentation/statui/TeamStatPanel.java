package presentation.statui;

import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import presentation.preset.StatPre;
import VO.PlayerVO;
import blservice.playerinfoblservice.PlayerInfoService;
import bussinesslogic.PlayerInfo.PlayerInfoBL;

public class TeamStatPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	/**
	 * 球队统计显示面板
	 * @author blisscry
	 * @date 2015年6月12日19:58:30
	 * @version 2.0
	 */

	//-------------------------ui static values-------------------
	public static int WIDTH=1100;
	public static int HEIGHT=700;
	public static int SIDEWIDTH=170;
	private int TableLineHeight=40;
	//swing kits range from up to down
	//team & player 
	private JButton Team;
	private int Team_w=130;
	private int Team_h=35;
	private JButton Player;
	private int Player_w=130;
	private int Player_h=35;
	
	//analysis & trend
	private JButton Analysis;
	private int Analysis_w=100;
	private int Analysis_h=30;
	private JButton Trend;
	private int Trend_w=100;
	private int Trend_h=30;
	
	//2012-13 Postseason
	//2013-14 Regular
	
	//JComboBox of datatype & seasonselection
	private JComboBox<String> DataType;
	private int DataType_w=100;
	private int DataType_h=30;
	private JComboBox<String> SeasonSelection;
	private int SeasonSelection_w=150;
	private int SeasonSelection_h=30;
	private JComboBox<String> Teams;
	private int Teams_w=200;
	private int Teams_h=30;
	private String[] TeamNames={
"老鹰 Atlanta-Hawks",
"黄蜂 Charlotte-Hornets",
"热火 Miami-Heat",
"魔术 Orlando-Magic",
"奇才 Washington-Wizards",
	
"公牛 Chicago-Bulls",
"骑士 Cleveland-Cavaliers",
"活塞 Detroit-Pistons",
"步行者 Indiana-Pacers",
"雄鹿 Milwaukee-Bucks",
	
"凯尔特人 Boston-Celtics",
"篮网 Brooklyn-Nets",
"尼克斯 New York-Knicks",
"76人 Philadelphia-76ers",
"猛龙 Toronto-Raptors",
	
"勇士 Golden State-Warriors",
"快船 Los Angeles-Clippers",
"湖人 Los Angeles-Lakers",
"太阳 Phoenix-Suns",
"国王 Sacramento-Kings",
	
"掘金 Denver-Nuggets",
"森林狼 Minnesota-Timberwolves",
"雷霆 Oklahoma City-Thunder",
"开拓者 Portland-Trail Blazers",
"勇士 Utah-Jazz",
	
"小牛 Dallas-Mavericks",
"火箭 Houston-Rockets",
"灰熊 Memphis-Grizzlies",
"鹈鹕 New Orleans-Pelicans",
"马刺 San Antonio-Spurs"};
	
	private JComboBox<String> season;
	private int season_w=100;
	private int season_h=30;
	
	private JComboBox<String> formeryear;
	private int formeryear_w=60;
	private int formeryear_h=30;
	private JComboBox<String> latteryear;
	private int latteryear_w=60;
	private int latteryear_h=30;
	private JLabel yearmessage;
	private JButton summit;
	
	private JLabel TeamLogo;
	private JLabel TeamInfo;
	
	//table showing the different data of team comparing to whole teams
	private JScrollPane DataPane;
	private int DataPane_w=370;
	private int DataPane_h=305;
	private JTable DataTable;
	private Object[][] DataInfo1;
	private Object[][] DataInfo2;
	private Object[][] DataInfo3;
	private String[] columnNames={"场均","场均排名","总体平均值","区间估计"};
	private String[] lineNames1={"得分","篮板","助攻","抢断","盖帽","失误","犯规"};
	private String[] lineNames2={"得分","助攻","命中率","罚球","三分"};
	private String[] lineNames3={"篮板","抢断","盖帽","失误"};
	private int[] COLUMNWIDTH={80,60,80,150};
	private JComboBox<String> alpha;
	private String[] alphaValue={"0.4","0.3","0.2","0.1","0.05","0.025","0.01","0.005","0.0005"};
	private int alpha_w=60;
	private int alpha_h=30;
	private JLabel alphamessage;
	
	//buttons showing differentdatas
	private int button_w=130;
	private int button_h=40;
	private JButton[] linebutton1;
	private JButton[] linebutton2;
	private JButton[] linebutton3;
	
	private ChartPanel piechart1;
	private ChartPanel piechart2;
	private ChartPanel barchart;
	private ChartPanel linechart;
	
	JFrame Frame;
	JPanel panelToRemove;
	
	public TeamStatPanel(JFrame frame) {
		Frame=frame;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		
		datainitial();

		addbuttons();
		addbox();
		addtable();

		barchart=new ChartPanel(null);
		piechart1=new ChartPanel(null);
		piechart2=new ChartPanel(null);
		linechart=new ChartPanel(null);
		//setchart(String.valueOf(Teams.getSelectedItem()));
		addbarchart(String.valueOf(Teams.getSelectedItem()),"得分");
		addpiechart(String.valueOf(Teams.getSelectedItem()));
		
		addlabel();
	}
	
	private void addbuttons(){
		int space=80;
		Team=new JButton("球队");
		Team.setBounds(SIDEWIDTH, space, Team_w, Team_h);
		Team.setSelected(true);
		this.add(Team);
		
		Player=new JButton("球员");
		Player.setBounds(SIDEWIDTH+Team_w+10, space, Player_w, Player_h);
		Player.setSelected(false);
		Player.addActionListener(this);
		this.add(Player);
		
		Analysis=new JButton("赛季分析");
		Analysis.setBounds(SIDEWIDTH, space+Team_h+10, Analysis_w, Analysis_h);
		Analysis.setSelected(true);
		Analysis.addActionListener(this);
		this.add(Analysis);
		
		Trend=new JButton("发展趋势");
		Trend.setBounds(SIDEWIDTH+Analysis_w+10,space+Team_h+10, Trend_w, Trend_h);
		Trend.setSelected(false);
		Trend.addActionListener(this);
		this.add(Trend);
		
		linebutton1=new JButton[lineNames1.length];
		for(int i=0;i<lineNames1.length;i++){
			final int temp=i;
			linebutton1[i]=new JButton(lineNames1[i]);
			linebutton1[i].setBounds(SIDEWIDTH, 340+i*button_h, button_w, button_h);
			linebutton1[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!linebutton1[temp].isSelected()){
						for(int i=0;i<linebutton1.length;i++){
							linebutton1[i].setSelected(false);
						}
						linebutton1[temp].setSelected(true);
						setchart(lineNames1[temp]);
					}
					if(Trend.isSelected()){
						addlinechart(String.valueOf(Teams.getSelectedItem()),lineNames1[temp]);
					}
				}
			});
			this.add(linebutton1[i]);
		}
		linebutton1[0].setSelected(true);
		
		linebutton2=new JButton[lineNames2.length];
		for(int i=0;i<lineNames2.length;i++){
			final int temp=i;
			linebutton2[i]=new JButton(lineNames2[i]);
			linebutton2[i].setBounds(SIDEWIDTH, 340+i*button_h, button_w, button_h);
			linebutton2[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!linebutton2[temp].isSelected()){
						for(int i=0;i<linebutton2.length;i++){
							linebutton2[i].setSelected(false);
						}
						linebutton2[temp].setSelected(true);
						setchart(lineNames2[temp]);
					}
					if(Trend.isSelected()){
						addlinechart(String.valueOf(Teams.getSelectedItem()),lineNames2[temp]);
					}
				}
			});
			this.add(linebutton2[i]);
		}
		linebutton2[0].setSelected(true);
		
		linebutton3=new JButton[lineNames3.length];
		for(int i=0;i<lineNames3.length;i++){
			final int temp=i;
			linebutton3[i]=new JButton(lineNames3[i]);
			linebutton3[i].setBounds(SIDEWIDTH, 340+i*button_h, button_w, button_h);
			linebutton3[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!linebutton3[temp].isSelected()){
						for(int i=0;i<linebutton3.length;i++){
							linebutton3[i].setSelected(false);
						}
						linebutton3[temp].setSelected(true);
						setchart(lineNames3[temp]);
					}
					if(Trend.isSelected()){
						addlinechart(String.valueOf(Teams.getSelectedItem()),lineNames3[temp]);
					}
				}
			});
			this.add(linebutton3[i]);
		}
		linebutton3[0].setSelected(true);
	}
	
	private void showbutton(String info){
		switch(info){
		case "all":
			for(int i=0;i<linebutton1.length;i++){
				linebutton1[i].setVisible(true);
			}
			for(int i=0;i<linebutton2.length;i++){
				linebutton2[i].setVisible(false);
			}
			for(int i=0;i<linebutton3.length;i++){
				linebutton3[i].setVisible(false);
			}
			break;
		case "offense":
			for(int i=0;i<linebutton1.length;i++){
				linebutton1[i].setVisible(false);
			}
			for(int i=0;i<linebutton2.length;i++){
				linebutton2[i].setVisible(true);
			}
			for(int i=0;i<linebutton3.length;i++){
				linebutton3[i].setVisible(false);
			}
			break;
		case "defense":
			for(int i=0;i<linebutton1.length;i++){
				linebutton1[i].setVisible(false);
			}
			for(int i=0;i<linebutton2.length;i++){
				linebutton2[i].setVisible(false);
			}
			for(int i=0;i<linebutton3.length;i++){
				linebutton3[i].setVisible(true);
			}
			break;
		}
	}
	
	private void addbox(){
		DataType=new JComboBox<String>();
		DataType.setFocusable(false);
		DataType.setBackground(StatPre.indefaultcolor);
		DataType.addItem("总属性");
		DataType.addItem("进攻属性");
		DataType.addItem("防守属性");
		DataType.setBounds(SIDEWIDTH,240,DataType_w,DataType_h);
		DataType.setFont(StatPre.BoxFont);
		DataType.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					if(DataType.getSelectedIndex()==0){
						showbutton("all");
						refreshtable("all");
					}
					if(DataType.getSelectedIndex()==1){
						showbutton("offense");
						refreshtable("offense");
					}
					if(DataType.getSelectedIndex()==2){
						showbutton("defense");
						refreshtable("defense");
					}
				}
			}
		});
		this.add(DataType);
		
		SeasonSelection=new JComboBox<String>();
		SeasonSelection.setFocusable(false);
		SeasonSelection.setBackground(StatPre.indefaultcolor);
//		SeasonSelection.addItem("2010-2011 常规赛");
//		SeasonSelection.addItem("2010-2011 季后赛");
//		SeasonSelection.addItem("2011-2012 常规赛");
//		SeasonSelection.addItem("2011-2012 季后赛");
//		SeasonSelection.addItem("2012-2013 常规赛");
//		SeasonSelection.addItem("2012-2013 季后赛");
		
		SeasonSelection.setBounds(SIDEWIDTH+DataType_w+5,240,SeasonSelection_w,SeasonSelection_h);
		SeasonSelection.setFont(StatPre.BoxFont);
		SeasonSelection.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					if(SeasonSelection.getSelectedIndex()==0){
						
					}
					if(SeasonSelection.getSelectedIndex()==1){
						
					}
					if(SeasonSelection.getSelectedIndex()==2){
						
					}
				}
			}
		});
		this.add(SeasonSelection);
		
		Teams=new JComboBox<String>();
		Teams.setFocusable(false);
		Teams.setBackground(StatPre.indefaultcolor);
		for(int i=0;i<TeamNames.length;i++){
			Teams.addItem(TeamNames[i]);
		}
		Teams.setBounds(SIDEWIDTH,205,Teams_w,Teams_h);
		Teams.setFont(StatPre.BoxFont);
		Teams.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					TeamLogo.setIcon(new ImageIcon("images/teams/big/"+switchTeam(String.valueOf(Teams.getSelectedItem()))+".png"));
				}
			}
		});
		this.add(Teams);
		
		alpha=new JComboBox<String>();
		alpha.setFocusable(false);
		alpha.setBackground(StatPre.indefaultcolor);
		for(int i=0;i<alphaValue.length;i++){
			alpha.addItem(alphaValue[i]);
		}
		alpha.setBounds(SIDEWIDTH+DataType_w+SeasonSelection_w+5*2+20,240,alpha_w,alpha_h);
		alpha.setFont(StatPre.BoxFont);
		this.add(alpha);
		alphamessage=new JLabel("α :");
		alphamessage.setBounds(SIDEWIDTH+DataType_w+SeasonSelection_w+5*2,245,30,15);
		alphamessage.setFont(StatPre.MessageFont);
		alphamessage.setForeground(StatPre.defaultcolor);
		this.add(alphamessage);
		
		season=new JComboBox<String>();
		season.addItem("常规赛");
		season.addItem("季后赛");
		season.setBounds(SIDEWIDTH,240,season_w,season_h);
		season.setFocusable(false);
		season.setBackground(StatPre.indefaultcolor);
		season.setFont(StatPre.BoxFont);
		season.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					//a默认0.01
					TeamLogo.setIcon(new ImageIcon("images/teams/big/"+switchTeam(String.valueOf(Teams.getSelectedItem()))+".png"));
				}
			}
		});
		season.setVisible(false);
		this.add(season);
		
		formeryear=new JComboBox<String>();
		formeryear.addItem("2009");
		formeryear.addItem("2010");
		formeryear.addItem("2011");
		formeryear.setBounds(SIDEWIDTH+DataType_w+40, 240, formeryear_w, formeryear_h);
		formeryear.setFocusable(false);
		formeryear.setBackground(StatPre.indefaultcolor);
		formeryear.setFont(StatPre.BoxFont);
		formeryear.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					
					TeamLogo.setIcon(new ImageIcon("images/teams/big/"+switchTeam(String.valueOf(Teams.getSelectedItem()))+".png"));
				}
			}
		});
		formeryear.setVisible(false);
		this.add(formeryear);
		
		latteryear=new JComboBox<String>();
		latteryear.addItem("2010");
		latteryear.addItem("2011");
		latteryear.addItem("2012");
		latteryear.setBounds(SIDEWIDTH+DataType_w+formeryear_w+40+15, 240, latteryear_w, latteryear_h);
		latteryear.setFocusable(false);
		latteryear.setBackground(StatPre.indefaultcolor);
		latteryear.setFont(StatPre.BoxFont);
		latteryear.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					
					TeamLogo.setIcon(new ImageIcon("images/teams/big/"+switchTeam(String.valueOf(Teams.getSelectedItem()))+".png"));
				}
			}
		});
		latteryear.setVisible(false);
		this.add(latteryear);
		
		yearmessage=new JLabel("~");
		yearmessage.setBounds(SIDEWIDTH+DataType_w+40+latteryear_w, 245, 20, 10);
		yearmessage.setFont(StatPre.MessageFont);
		yearmessage.setForeground(StatPre.defaultcolor);
		yearmessage.setVisible(false);
		this.add(yearmessage);
		
	}
	
	private void addtable(){
		DataPane=new JScrollPane();
		DataPane.setBounds(SIDEWIDTH+button_w, 315, DataPane_w, DataPane_h);
//		DataPane.setHorizontalScrollBarPolicy( 
//				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
//		DataPane.setVerticalScrollBarPolicy( 
//				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		DataPane.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		DataPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		DataPane.setVisible(true);
		DataPane.setOpaque(false);
		DataPane.getViewport().setOpaque(false);
		DataPane.setBorder(null);

		this.add(DataPane);
		
		refreshtable("all");
	}

	private void table_config(Object[][] datainfo){
		//表格属性设置
		DataTable=new JTable(datainfo, columnNames){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		DataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//设置表格列不可移动
		DataTable.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) DataTable.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		DataTable.setFont(StatPre.TableCellFont);
		DataTable.setForeground(StatPre.TableCellFg);
		DataTable.getTableHeader().setFont(StatPre.TableHeaderFont);
		DataTable.getTableHeader().setForeground(StatPre.TableFg);
		DataTable.getTableHeader().setOpaque(false);
		DataTable.getTableHeader().setBackground(StatPre.TableBg);
		//去除边框
		DataTable.setBorder(null);

		//按行修改表格背景
		TableColumnModel model = DataTable.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new DefaultTableCellRenderer(){
				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent(JTable t, Object value,
						boolean isSelected, boolean hasFocus, int row, int column) 
				{
					//单元格居中
					setHorizontalAlignment(JLabel.CENTER);
					//设置奇偶行的背景色
					if (row % 2 == 0)
						setBackground(StatPre.EvenTableLine);
					else
						setBackground(StatPre.OddTableLine);
					return super.getTableCellRendererComponent(t, value, isSelected,
							hasFocus, row, column);
				}
			});
		}

		//不显示单元格边框线
		DataTable.setShowHorizontalLines(false);
		DataTable.setShowVerticalLines(false);
		//设置选中颜色
		DataTable.setSelectionBackground(StatPre.TableLineSelected);
		
		//设置行高
		DataTable.setRowHeight(TableLineHeight);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH.length;i++){
			DataTable.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH[i]);
		}

	}
	
	private void datainitial(){
		DataInfo1=new Object[lineNames1.length][columnNames.length];
		DataInfo2=new Object[lineNames2.length][columnNames.length];
		DataInfo3=new Object[lineNames3.length][columnNames.length];
	}
	
	public void refreshtable(String info){
		switch(info){
		case "all":
			table_config(DataInfo1);
			DataPane.setSize(DataPane_w, DataPane_h);
			break;
		case "offense":
			table_config(DataInfo2);
			DataPane.setSize(DataPane_w, DataPane_h-button_h*2);
			break;
		case "defense":
			table_config(DataInfo3);
			DataPane.setSize(DataPane_w, DataPane_h-button_h*3);
			break;
		}
		DataPane.setViewportView(DataTable);
		Frame.repaint();
	}
	
	//Team info and details
	private void addlabel(){
		TeamLogo=new JLabel();
		String teamname=String.valueOf(Teams.getSelectedItem());
		TeamLogo.setBounds(WIDTH-235, -10, 235,235 );
		TeamLogo.setIcon(new ImageIcon("images/teams/big/"+switchTeam(teamname)+".png"));
		this.add(TeamLogo);
		TeamInfo=new JLabel();
		
	}
	//------------------------chart configs-------------------------------
	 public static CategoryDataset getBarDataset(String teamname,String linename) {

		  String series1 = "1";
//		  String series2 = "2";
//		  String series3 = "3";
		  // 列
		  String category1 = "场均";
		  String category2 = "总体";
		  String category3 = "东部联盟";
		  String category4 = "西部联盟";

		  // 创建数据源
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		  // 放入数据
		  dataset.addValue(40.0, series1, category1);
		  dataset.addValue(45.0, series1, category2);
		  dataset.addValue(36.0, series1, category3);
		  dataset.addValue(51.0, series1, category4);

//		  dataset.addValue(5.0, series2, category1);
//		  dataset.addValue(7.0, series2, category2);
//		  dataset.addValue(6.0, series2, category3);
//		  dataset.addValue(8.0, series2, category4);
//
//		  dataset.addValue(4.0, series3, category1);
//		  dataset.addValue(3.0, series3, category2);
//		  dataset.addValue(2.0, series3, category3);
//		  dataset.addValue(3.0, series3, category4);

		  return dataset;
		 }
	
	 public static PieDataset getPieDataset1(String teamname){
		 DefaultPieDataset dataset=new DefaultPieDataset();
		 dataset.setValue("首发", 97);
		 dataset.setValue("替补", 30);
		 
		 return dataset;
	 }
	 
	 public static PieDataset getPieDataset2(String teamname){
		 DefaultPieDataset dataset=new DefaultPieDataset();
		 dataset.setValue("两分球", 60);
		 dataset.setValue("三分球", 30);
		 dataset.setValue("罚球", 10);
		 
		 return dataset;
	 }
	 
	 public static CategoryDataset getLineDataset(String teamname,String linename){
		 DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		 dataset.addValue(1, "First", "2013");  
		 dataset.addValue(3, "First", "2014");  
		 dataset.addValue(2, "First", "2015");  
		 dataset.addValue(6, "First", "2016");  
		 dataset.addValue(5, "First", "2017");  
		 dataset.addValue(12, "First", "2018");  
		 dataset.addValue(14, "Second", "2013");  
		 dataset.addValue(13, "Second", "2014");  
		 dataset.addValue(12, "Second", "2015");  
		 dataset.addValue(9, "Second", "2016");  
		 dataset.addValue(5, "Second", "2017");  
		 dataset.addValue(7, "Second", "2018");  
		 
		 return dataset;
	 }
	 
	 public static CategoryDataset createRadarDataset(){
		 String s = "First";
		  String s1 = "Second";
		  String s2 = "Third";
		  String s3 = "Category 1";
		  String s4 = "Category 2";
		  String s5 = "Category 3";
		  String s6 = "Category 4";
		  String s7 = "Category 5";
		  DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		  defaultcategorydataset.addValue(1.0D, s, s3);
		  defaultcategorydataset.addValue(4D, s, s4);
		  defaultcategorydataset.addValue(3D, s, s5);
		  defaultcategorydataset.addValue(5D, s, s6);
		  defaultcategorydataset.addValue(5D, s, s7);
//		  defaultcategorydataset.addValue(5D, s1, s3);
//		  defaultcategorydataset.addValue(7D, s1, s4);
//		  defaultcategorydataset.addValue(6D, s1, s5);
//		  defaultcategorydataset.addValue(8D, s1, s6);
//		  defaultcategorydataset.addValue(4D, s1, s7);
//		  defaultcategorydataset.addValue(4D, s2, s3);
//		  defaultcategorydataset.addValue(3D, s2, s4);
//		  defaultcategorydataset.addValue(2D, s2, s5);
//		  defaultcategorydataset.addValue(3D, s2, s6);
//		  defaultcategorydataset.addValue(6D, s2, s7);
		  return defaultcategorydataset;
	 }
	 
	 private static XYDataset createXYDataset()
	 {
		 XYSeriesCollection localXYSeriesCollection = new XYSeriesCollection();
		 XYSeries localXYSeries1 = new XYSeries("Series 1");
		 localXYSeries1.add(0.0D, 2.0D);
		 localXYSeries1.add(90.0D, 13.0D);
		 localXYSeries1.add(180.0D, 9.0D);
		 localXYSeries1.add(270.0D, 8.0D);
		 localXYSeriesCollection.addSeries(localXYSeries1);
		 XYSeries localXYSeries2 = new XYSeries("Series 2");
		 localXYSeries2.add(90.0D, -11.2D);
		 localXYSeries2.add(180.0D, 21.4D);
		 localXYSeries2.add(250.0D, 17.3D);
		 localXYSeries2.add(355.0D, 10.9D);
		 localXYSeriesCollection.addSeries(localXYSeries2);
		 return localXYSeriesCollection;
	 }

	 private void addbarchart(String teamname,String linename){
		 CategoryDataset BarSet=getBarDataset(teamname,linename);

		 barchart.setChart(new BarChart().createChart(BarSet,"单项对比",null,linename));
		 barchart.setBounds(SIDEWIDTH+DataPane_w+button_w+40, 370, 350, 300);
		 barchart.setOpaque(false);
		 this.add(barchart);
		 Frame.repaint();
	 }

	 private void addpiechart(String teamname){
		 PieDataset ScorecompSet=getPieDataset1(teamname);
//		 piechart1=new ChartPanel(new PieChart().createChart(ScorecompSet,"首发/替补"));
		 piechart1.setChart(new PieChart().createChart(ScorecompSet,"首发/替补"));
		 piechart1.setBounds(SIDEWIDTH+DataPane_w+button_w-10, 230, 230, 150);
		 piechart1.setOpaque(false);
		 this.add(piechart1);
		 Frame.repaint();

		 PieDataset ScorepercentSet=getPieDataset2(teamname);
//		 piechart2=new ChartPanel(new PieChart().createChart(ScorepercentSet,"得分比重"));
		 piechart2.setChart(new PieChart().createChart(ScorepercentSet,"得分比重"));
		 piechart2.setBounds(SIDEWIDTH+DataPane_w+button_w+190, 230, 230, 150);
		 piechart2.setOpaque(false);
		 this.add(piechart2);
		 Frame.repaint();

	 }
	 
	 private void addlinechart(String teamname,String linename){
		 CategoryDataset ScoreTrendSet=getLineDataset(teamname,linename);
//		 linechart=new ChartPanel(new LineChart().createChart(ScoreTrendSet,"球员数据","得分","年份"));
		 linechart.setChart(new LineChart().createChart(ScoreTrendSet,"变化趋势","年份",linename));
		 linechart.setBounds(SIDEWIDTH+button_w, 270, 600, 400);
		 linechart.setOpaque(false);
		 this.add(linechart);
		 Frame.repaint();
	 }

	 //refresh charts
	 private void setchart(String linename){
		 addbarchart(String.valueOf(Teams.getSelectedItem()),linename);
	     addpiechart(String.valueOf(Teams.getSelectedItem()));
	 }
	 
	 private void setAnalysisSelected(){
		 Analysis.setSelected(true);
		 Trend.setSelected(false);
		 DataType.setVisible(true);
		 SeasonSelection.setVisible(true);
		 DataPane.setVisible(true);
		 alpha.setVisible(true);
		 alphamessage.setVisible(true);
		 season.setVisible(false);
		 formeryear.setVisible(false);
		 latteryear.setVisible(false);;
		 yearmessage.setVisible(false);
		 
		 linebutton1[5].setVisible(true);
		 linebutton1[6].setVisible(true);
		 
		 switch(DataType.getSelectedIndex()){
		 case 0:
		 for(int i=0;i<linebutton1.length;i++){
			 if(linebutton1[i].isSelected())
			 setchart(lineNames1[i]);
		 }
		 break;
		 case 1:
			 for(int i=0;i<linebutton2.length;i++){
				 if(linebutton2[i].isSelected())
				 setchart(lineNames2[i]);
			 }
			 break;
		 case 2:
			 for(int i=0;i<linebutton3.length;i++){
				 if(linebutton3[i].isSelected())
				 setchart(lineNames3[i]);
			 }
			 break;
		 }
		 
		 piechart1.setVisible(true);
		 piechart2.setVisible(true);
		 barchart.setVisible(true);
		 linechart.setVisible(false);
		   
	 }
	 
	 private void setTrendSelected(){
		 Analysis.setSelected(false);
		 Trend.setSelected(true);
		 DataType.setVisible(false);
		 SeasonSelection.setVisible(false);
		 DataPane.setVisible(false);
		 alpha.setVisible(false);
		 alphamessage.setVisible(false);
		 season.setVisible(true);
		 formeryear.setVisible(true);
		 latteryear.setVisible(true);;
		 yearmessage.setVisible(true);

		 linebutton1[5].setVisible(false);
		 linebutton1[6].setVisible(false);
		 for(int i=0;i<linebutton2.length;i++){
			 linebutton2[i].setVisible(false);
		 }
		 for(int i=0;i<linebutton3.length;i++){
			 linebutton3[i].setVisible(false);
		 }
		 
		 piechart1.setVisible(false);
		 piechart2.setVisible(false);
		 barchart.setVisible(false);
		 addlinechart(String.valueOf(Teams.getSelectedItem()), lineNames1[0]);
		 linechart.setVisible(true);
	 }
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Analysis){
			if(!Analysis.isSelected()){
				setAnalysisSelected();
			}
		}
		if(e.getSource()==Trend){
			if(!Trend.isSelected()){
				setTrendSelected();
			}
		}
		if(e.getSource()==Player){
			PlayerStatPanel psp=new PlayerStatPanel(Frame);
			Frame.remove(panelToRemove);
			Frame.add(psp);
			Frame.repaint();
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
	
	 public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 ImageIcon im1=new ImageIcon("images/system_img/main_bg.png");
		g.drawImage(im1.getImage(),0,0,this);
	 }
	 

}
