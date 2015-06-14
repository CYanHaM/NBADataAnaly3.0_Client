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

public class PlayerStatPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	//JComboBox of datatype & seasonselection
	private JComboBox<String> DataType;
	private int DataType_w=150;
	private int DataType_h=30;
	private JComboBox<String> SeasonSelection;
	private int SeasonSelection_w=200;
	private int SeasonSelection_h=30;
	private JComboBox<String> Teams;
	private String[] TeamNames={"","","","","",""};
	private int Teams_w=150;
	private int Teams_h=30;

	private JLabel TeamLogo;
	private JLabel TeamInfo;

	//table showing the different data of team comparing to whole teams
	private JScrollPane DataPane;
	private int DataPane_w=370;
	private int DataPane_h=385;
	private JTable DataTable;
	private Object[][] DataInfo;
	private String[] columnNames={"场均","场均排名","总体平均值","区间估计"};
	private String[] lineNames={"得分","篮板","助攻","盖帽","抢断","罚球","失误","犯规","上场时间"};
	private int[] COLUMNWIDTH={80,60,80,150};

	//buttons showing differentdatas
	private int button_w=130;
	private int button_h=40;
	private JButton[] linebutton;

	private ChartPanel radarchart;
	private ChartPanel barchart;
	private ChartPanel linechart;
	
	JFrame Frame;
	JPanel panelToRemove;
	
	public PlayerStatPanel(JFrame frame) {
		Frame=frame;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		
		datainitial();
		
		addbuttons();
		addbox();
		addtable();
		addbarchart();
		addradarchart();
	}

	private void addbuttons(){
		int space=80;
		Team=new JButton("球队");
		Team.setBounds(SIDEWIDTH, space, Team_w, Team_h);
		Team.setSelected(false);
		Team.addActionListener(this);
		this.add(Team);
		
		Player=new JButton("球员");
		Player.setBounds(SIDEWIDTH+Team_w+10, space, Player_w, Player_h);
		Player.setSelected(true);
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
		
		linebutton=new JButton[lineNames.length];
		for(int i=0;i<lineNames.length;i++){
			final int temp=i;
			linebutton[i]=new JButton(lineNames[i]);
			linebutton[i].setBounds(SIDEWIDTH, 270+i*button_h, button_w, button_h);
			linebutton[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!linebutton[temp].isSelected()){
						for(int i=0;i<linebutton.length;i++){
							linebutton[i].setSelected(false);
						}
						linebutton[temp].setSelected(true);
						setchart(lineNames[temp]);
					}
				}
			});
			this.add(linebutton[i]);
		}
	}
	
	private void addbox(){
		DataType=new JComboBox<String>();
		DataType.setFocusable(false);
		DataType.setBackground(StatPre.indefaultcolor);
		DataType.addItem("总属性");
		DataType.addItem("进攻属性");
		DataType.addItem("防守属性");
		DataType.setBounds(SIDEWIDTH,190,DataType_w,DataType_h);
		DataType.setFont(StatPre.BoxFont);
		DataType.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					if(DataType.getSelectedIndex()==0){
						
					}
					if(DataType.getSelectedIndex()==1){
						
					}
					if(DataType.getSelectedIndex()==2){
						
					}
				}
			}
		});
		this.add(DataType);
		
		SeasonSelection=new JComboBox<String>();
		SeasonSelection.setFocusable(false);
		SeasonSelection.setBackground(StatPre.indefaultcolor);
		SeasonSelection.addItem("2010-2011 常规赛");
		SeasonSelection.addItem("2010-2011 季后赛");
		SeasonSelection.addItem("2011-2012 常规赛");
		SeasonSelection.addItem("2011-2012 季后赛");
		SeasonSelection.addItem("2012-2013 常规赛");
		SeasonSelection.addItem("2012-2013 季后赛");
		SeasonSelection.setBounds(SIDEWIDTH+DataType_w+20,190,SeasonSelection_w,SeasonSelection_h);
		SeasonSelection.setFont(StatPre.BoxFont);
		SeasonSelection.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
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
		Teams.addItem("2010-2011 常规赛");
		Teams.addItem("2010-2011 季后赛");
		Teams.addItem("2011-2012 常规赛");
		Teams.addItem("2011-2012 季后赛");
		Teams.addItem("2012-2013 常规赛");
		Teams.addItem("2012-2013 季后赛");
		Teams.setBounds(SIDEWIDTH+DataType_w+20,190,SeasonSelection_w,SeasonSelection_h);
		Teams.setFont(StatPre.BoxFont);
		Teams.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
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
		this.add(Teams);
	}
	
	private void addtable(){
		DataPane=new JScrollPane();
		DataPane.setBounds(SIDEWIDTH+button_w, 245, DataPane_w, DataPane_h);
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

		if (DataPane.getCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER) == null) {
			Component component = new JLabel("") {

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					Paint oldPaint = g2.getPaint();
					Rectangle bounds = getBounds();
					Paint backgroupRectPaint = new GradientPaint(0, 0, StatPre.TableLineSelected,
							bounds.width, bounds.height, StatPre.TableLineSelected);
					g2.setPaint(backgroupRectPaint);
					g2.fillRect(0, 0, bounds.width, bounds.height);
					g2.setPaint(oldPaint);
				}
			};
			DataPane.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, component);
		}

		if (DataPane.getCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER) == null) {
			Component component = new JLabel("") {
				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					Paint oldPaint = g2.getPaint();
					Rectangle bounds = getBounds();
					Paint backgroupRectPaint = new GradientPaint(0, 0, StatPre.TableBg,
							bounds.width, bounds.height, StatPre.TableBg);
					g2.setPaint(backgroupRectPaint);
					g2.fillRect(0, 0, bounds.width, bounds.height);
					g2.setPaint(oldPaint);
				}
			};
			DataPane.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER,component);
		}
		this.add(DataPane);
		
		refreshtable();
	}

	private void table_config(){
		//表格属性设置
		DataTable=new JTable(DataInfo, columnNames){
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
		DataInfo=new Object[lineNames.length][columnNames.length];
	}
	
	public void refreshtable(){
		table_config();
		DataPane.setViewportView(DataTable);
		Frame.repaint();
	}
	
	private void addlabel(){
		TeamLogo=new JLabel();
//		TeamLogo.setBounds(500, 70, , height);
		TeamInfo=new JLabel();
		
	}
	
	private void addbarchart(){
		CategoryDataset BarSet=createBarDataset();
		barchart=new ChartPanel(new BarChart().createChart(BarSet,"整体对比",null,null));
		barchart.setBounds(SIDEWIDTH+DataPane_w+button_w+40, 370, 350, 300);
		barchart.setOpaque(false);
		this.add(barchart);
		this.repaint();
	}
	
	private void addradarchart(){
		CategoryDataset radarset=createRadarDataset();
		radarchart=new ChartPanel(new RadarChart().createChart(radarset,"数据分布"));
		radarchart.setBounds(SIDEWIDTH+DataPane_w+button_w-10, 230, 230, 150);
		radarchart.setOpaque(false);
		this.add(radarchart);
		this.repaint();
	}
	
	 public static CategoryDataset createBarDataset() {

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
	
	 public static PieDataset createPieDataset1(){
		 DefaultPieDataset dataset=new DefaultPieDataset();
		 dataset.setValue("首发", 97);
		 dataset.setValue("替补", 30);
		 
		 return dataset;
	 }
	 
	 public static PieDataset createPieDataset2(){
		 DefaultPieDataset dataset=new DefaultPieDataset();
		 dataset.setValue("两分球", 60);
		 dataset.setValue("三分球", 30);
		 dataset.setValue("罚球", 10);
		 
		 return dataset;
	 }
	 
	 public static CategoryDataset createLineDataset(){
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
	 
	 private void setchart(String linename){
		 
	 }
	 
	 private void setAnalysisSelected(){
		 Analysis.setSelected(true);
		 Trend.setSelected(false);
		 for(int i=0;i<lineNames.length;i++){
			 linebutton[i].setVisible(true);
		 }
		 SeasonSelection.setVisible(true);
		 DataPane.setVisible(true);
		 
		 radarchart.setVisible(true);
		 barchart.setVisible(true);
		 linechart.setVisible(false);
		 
	 }
	 
	 private void setTrendSelected(){
		 Analysis.setSelected(false);
		 Trend.setSelected(true);
		 for(int i=0;i<lineNames.length;i++){
			 linebutton[i].setVisible(false);
		 }
		 SeasonSelection.setVisible(false);
		 DataPane.setVisible(false);
		 
		 CategoryDataset ScoreTrendSet=createLineDataset();
		 linechart=new ChartPanel(new LineChart().createChart(ScoreTrendSet,"球员数据","得分","年份"));
		 linechart.setBounds(SIDEWIDTH, 250, 600, 400);
		 linechart.setOpaque(false);
		 this.add(linechart);
		 
		 radarchart.setVisible(false);
		 barchart.setVisible(false);
		 linechart.setVisible(true);
	 }
	 
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
		if(e.getSource()==Team){
			TeamStatPanel psp=new TeamStatPanel(Frame);
			Frame.remove(panelToRemove);
			Frame.add(psp);
			Frame.repaint();
		}
	}
	 
	 
	 public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 ImageIcon im1=new ImageIcon("images/system_img/main_bg.png");
		g.drawImage(im1.getImage(),0,0,this);
	 }

}
