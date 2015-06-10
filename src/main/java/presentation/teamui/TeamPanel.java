package presentation.teamui;

import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import presentation.hotspotui.HotPlayerToday;
import presentation.matchui.MatchPanel;
import presentation.playerui.PlayerTechPanel;
import presentation.preset.PlayerPre;
import VO.PlayerVO;
import VO.TeamVO;

public class TeamPanel extends JPanel implements ActionListener{
	/**
	 * 显示球队基本信息的界面
	 * @author blisscry
	 * @date 2015年4月1日16:10:12
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH=1100;
	public static int HEIGHT=700;
	//定义边缘透明空白区域边界大小，单位px
	public static int e_space=10;
	//定义空出位置大小
	private static int space=35;
	//表格大小
	private static int TABLEWIDTH=765;
	private static int TABLEHEIGHT=275;
	//表格行高
	private static int ROWHEIGHT=35;
	//表格列宽
	private static int[] COLUMNWIDTH={200,80,80,100,100,200,60,60,250};
	//设置表格属性
	private JTable playertable;
	private Object[][] playerinfo;
	private JScrollPane players;
	private String[] columnName={
			"姓名","球衣号码","位置","身高(英尺-英寸)","体重(磅)","生日(月 日,年)","年龄","球龄","毕业学校"};
	
	private JLabel Icon;                //球队图片
	private JLabel fullName;            //球队全名
	private JLabel abbreviation;        //缩写
	private JLabel location;            //所在地
	private JLabel location_con;
	private JLabel division;            //赛区
	private JLabel division_con;
	private JLabel partition;           //分区
	private JLabel partition_con;
	private JLabel homeCourt;           //主场
	private JLabel homeCourt_con;
	private JLabel time;                //建立时间
	private JLabel time_con;
	private JLabel line;//水平线

	//侧边栏按钮
	private JButton SeasonInfo;
	private JButton MatchInfo;
	private JButton TeamInfo;
	private JButton PlayerInfo;
	private JButton Hot;
	
	private JButton back;
	
	ImportTeam importdata;
	public PlayerPre PPre;
	public JFrame Frame;
	public JPanel formerpanel;
	public JPanel panelToRemove;
	public TeamVO teamvo;
	public ArrayList<PlayerVO> playerlist;
	public TeamPanel(TeamVO tvo,JFrame frame,JPanel panel){
		Frame=frame;
		formerpanel=panel;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		//创建颜色预设对象
		PPre=new PlayerPre();
		//导入数据
//		importdata=new ImportTeam();
//		teamvo=importdata.getTeamVO(tvo);
		
//		if(teamvo.abbreviation.equals("NOP"))
//			teamvo.abbreviation="NOH";
//		playerlist=importdata.findByTeam(teamvo.abbreviation);
		
//		playerinfo=new Object[playerlist.size()][columnName.length];
		playerinfo=new Object[20][columnName.length];
		//初始化数据，以便放入table中
//		handleinitial(playerlist);
		//加载表格配置
		table_config();
		//加载滑动面板配置
		scrollpane_config();
		players.setViewportView(playertable);
		//添加按钮、消息框
		addbutton();
//		addlabel();
		
		this.repaint();
	}
	
	
    //=============================================
	private void addbutton(){
		
		back=new JButton(new ImageIcon("images/system_img/back_initial.png"));
		back.setBounds(200, 85, 100, 50);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setRolloverIcon(new ImageIcon("images/system_img/back_rollover.png"));
		back.setPressedIcon(new ImageIcon("images/system_img/back_pressed.png"));
		back.addActionListener(this);
		
		this.add(back);
		this.repaint();
	}
	
	 
	//定义所有显示及渲染球队信息的lablel
	private void addlabel(){
		System.out.println(teamvo.abbreviation);
		Icon=new JLabel(new ImageIcon("images/teams/"+teamvo.abbreviation+".png"));
		Icon.setBounds(210, 135, 300, 200);
		
		fullName=new JLabel(teamvo.fullName);
		fullName.setBounds(543, 145, 200, 20);
		fullName.setForeground(PPre.label);
		fullName.setFont(PPre.fullName);           
		
		line=new JLabel(new ImageIcon("images/teams/line.png"));
		line.setBounds(543, 165, 155, 5);
		
		abbreviation=new JLabel(teamvo.abbreviation);
		abbreviation.setBounds(545, 170, 200, 15);
		abbreviation.setForeground(PPre.label);
		abbreviation.setFont(PPre.abbreviation);   
		
		location=new JLabel("所在地:");
		location.setBounds(545, 190, 200, 15);
		location.setForeground(PPre.label);
		location.setFont(PPre.message);
		location_con=new JLabel(teamvo.location);
		location_con.setBounds(598, 190, 200, 15);
		location_con.setForeground(PPre.label);
		location_con.setFont(PPre.location);
		
		division=new JLabel("赛区:");
		division.setBounds(545, 215, 200, 15);
		division.setForeground(PPre.label);
		division.setFont(PPre.message);
		division_con=new JLabel(teamvo.division);
		division_con.setBounds(585, 215, 200, 15);
		division_con.setForeground(PPre.label);
		division_con.setFont(PPre.division);
		
		partition=new JLabel("分区:");
		partition.setBounds(545, 240, 200, 15);
		partition.setForeground(PPre.label);
		partition.setFont(PPre.message);
		partition_con=new JLabel(teamvo.partition);
		partition_con.setBounds(585, 240, 200, 15);
		partition_con.setForeground(PPre.label);
		partition_con.setFont(PPre.partition);
		
		homeCourt=new JLabel("主场:");
		homeCourt.setBounds(545, 265, 200, 15);
		homeCourt.setForeground(PPre.label);
		homeCourt.setFont(PPre.message);
		homeCourt_con=new JLabel(teamvo.homeCourt);
		homeCourt_con.setBounds(585, 265, 200, 15);
		homeCourt_con.setForeground(PPre.label);
		homeCourt_con.setFont(PPre.homeCourt);
		
		time=new JLabel("建立时间:");
		time.setBounds(545, 290, 200, 15);
		time.setForeground(PPre.label);
		time.setFont(PPre.message);
		time_con=new JLabel(teamvo.time);
		time_con.setBounds(615, 290, 200, 15);
		time_con.setForeground(PPre.label);
		time_con.setFont(PPre.time);
		
		this.add(fullName);
		this.add(abbreviation);
		this.add(location);
		this.add(location_con);
		this.add(division);
		this.add(division_con);
		this.add(partition);
		this.add(partition_con);
		this.add(homeCourt);
		this.add(homeCourt_con);
		this.add(time);
		this.add(time_con);
		
		this.add(line);
		this.add(Icon);
		
	}
	//=============================================
	//表格配置
	public void table_config(){
		//------------------------------表格基本属性--------------------------
		//表格属性设置
		playertable=new JTable(playerinfo, columnName){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		playertable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		playertable.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) playertable.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		playertable.setFont(PPre.CellFont);
		playertable.setForeground(PPre.CellFg);
		playertable.getTableHeader().setFont(PPre.HeaderFont);
		playertable.getTableHeader().setForeground(PPre.TableFg);
		playertable.getTableHeader().setOpaque(false);
		playertable.getTableHeader().setBackground(PPre.TableBg);
		//去除边框
		playertable.setBorder(null);

		//按行修改表格背景
		TableColumnModel model = playertable.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		playertable.setShowHorizontalLines(false);
		playertable.setShowVerticalLines(false);
		//设置选中颜色
		playertable.setSelectionBackground(PPre.LineSelected);

		//设置行高
		playertable.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH.length;i++){
			playertable.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH[i]);
		}


	}

	//滑动面板配置
	public void scrollpane_config(){
		//滑动面板信息
		players=new JScrollPane(playertable);
		players.setBounds(WIDTH-TABLEWIDTH-e_space-space,HEIGHT-TABLEHEIGHT-e_space-space,TABLEWIDTH,TABLEHEIGHT);
		players.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		players.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		players.setVisible(true);
		players.setOpaque(false);
		players.getViewport().setOpaque(false);
		players.setBorder(null);

		if (players.getCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER) == null) {
			Component component = new JLabel("") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					Paint oldPaint = g2.getPaint();
					Rectangle bounds = getBounds();
					Paint backgroupRectPaint = new GradientPaint(0, 0, PPre.LineSelected,
							bounds.width, bounds.height, PPre.LineSelected);
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
					Paint backgroupRectPaint = new GradientPaint(0, 0, PPre.TableBg,
							bounds.width, bounds.height, PPre.TableBg);
					g2.setPaint(backgroupRectPaint);
					g2.fillRect(0, 0, bounds.width, bounds.height);
					g2.setPaint(oldPaint);
				}
			};
			players.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER,component);
		}

		this.add(players);
	}

	public void handleinitial(ArrayList<PlayerVO> player){
		int counting = 0;
		for(int a = 0; a<player.size();a++){
			if(player.get(a)==null){
				
			}else{
				playerinfo[counting][0]=player.get(a).name;
				playerinfo[counting][1]=player.get(a).uniformNum;
				playerinfo[counting][2]=player.get(a).position;
				playerinfo[counting][3]=player.get(a).height;
				playerinfo[counting][4]=player.get(a).weight;
				playerinfo[counting][5]=player.get(a).birth;
				playerinfo[counting][6]=player.get(a).age;
				playerinfo[counting][7]=player.get(a).exp;
				playerinfo[counting][8]=player.get(a).school;
				counting++;
			}
		}
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
				setBackground(PPre.EvenTableLine);
			else
				setBackground(PPre.OddTableLine);
			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);
		}
	}

	//绘制界面背景
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ImageIcon im1=new ImageIcon("images/system_img/bg-1.png");
		g.drawImage(im1.getImage(),0,0,this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==back){
			Frame.add(formerpanel);
			Thread switchpanel=new Thread(){
				public void run(){
					int i=0;
					while(i<=11){
					panelToRemove.setLocation(100*i, 0);
					formerpanel.setLocation(-WIDTH+100*i, 0);
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
	}



}
