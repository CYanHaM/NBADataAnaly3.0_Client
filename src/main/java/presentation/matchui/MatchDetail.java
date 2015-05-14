package presentation.matchui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import presentation.playerui.PlayerInfoPanel;
import presentation.playerui.PlayerTechPanel;
import presentation.preset.MatchPre;
import presentation.preset.PlayerTechPre;
import presentation.teamui.TeamInfoPanel;
import presentation.teamui.TeamTechPanel;
import PO.PlayerTechMPO;
import VO.MatchVO;

public class MatchDetail extends JPanel implements ActionListener{
	/**
	 * the details of every match
	 * @author blisscry
	 * @date 2015年4月29日22:55:38
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	public static int FRAME_WIDTH=1020;
	public static int FRAME_HEIGHT=670;
	//表格大小
	private static int TABLEWIDTH=785;
	private static int TABLEHEIGHT=160;

	private int playerNum_guest=0;
	private int playerNum_home=0;

	private JScrollPane jsp_guest;
	private JTable table_guest;
	private Object[][] teaminfo_guest;
	private JScrollPane jsp_home;
	private JTable table_home;
	private Object[][] teaminfo_home;

	private String[] columnName={"姓名","分钟","命中","出手","三分命中","三分出手",
			"罚球命中","罚球出手","进攻","防守","篮板","助攻","抢断","盖帽","失误","犯规","得分"};
	private static int ROWHEIGHT=28;
	//表格列宽
	private static int[] COLUMNWIDTH={118,36,36,36,54,54,54,54,36,36,36,36,36,36,36,36,36};
	public int HeaderColumn=0;
	
	//-------------------------kits define--------------------------------
	private JLabel guestteamimg;
	private JLabel hometeamimg;
	private JLabel VS;
	private JLabel[] num;
	private JLabel[][] scores;
	private JButton showdata;
	private JButton showgraph;
	private JLabel guestname;
	private JLabel homename;
	
	private JButton SeasonInfo;
	private JButton MatchInfo;
	private JButton TeamInfo;
	private JButton PlayerInfo;
	private JButton Hot;
	
	private JButton back;
	
	MatchGraph matchgraph;
	
	MatchPre MP;
	JFrame Frame;
	MatchVO matchvo;
	JPanel panelToReturn;
	JPanel panelToRemove;
	PlayerTechPre PTPre;

	public MatchDetail(JFrame frame,MatchVO mvo,JPanel panel) {
//		System.out.println(mvo.guestTeam);
		Frame=frame;
		matchvo=mvo;
		panelToReturn=panel;
		panelToRemove=this;
		this.setLayout(null);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		MP = new MatchPre();
		PTPre = new PlayerTechPre();
		
		addbuttons();
		addlabels();

		//---------------------add jscrollpane and table-----------------
		getplayerNum(mvo);
		tableguest_config();
		tablehome_config();
		insertData(mvo);

		jsp_guest = new JScrollPane(table_guest);
		jsp_guest.setViewportView(table_guest);
		jsp_guest.setBounds(200,280,TABLEWIDTH,TABLEHEIGHT);
		scrollpane_config(jsp_guest);
		guestname=new JLabel(switchTeamName(matchvo.guestTeam)+" - "+matchvo.guestTeam);
		guestname.setBounds(200, 280-20, 150, 20);
		guestname.setFont( new Font("华文细黑",0,18));
		guestname.setForeground(MP.White);
		this.add(guestname);

		jsp_home = new JScrollPane(table_home);
		jsp_home.setViewportView(table_home);
		jsp_home.setBounds(200,270+TABLEHEIGHT+40,TABLEWIDTH,TABLEHEIGHT);
		scrollpane_config(jsp_home);
		homename=new JLabel(switchTeamName(matchvo.homeTeam)+" - "+matchvo.homeTeam);
		homename.setBounds(200,270+TABLEHEIGHT+40-20, 150, 20);
		homename.setFont(new Font("华文细黑",0,18));
		homename.setForeground(MP.White);
		this.add(homename);
		//-----------------------------------------------------------------
		
		this.repaint();

	}
	
	private void addbuttons(){
		SeasonInfo=new JButton(new ImageIcon("images/system_img/seasoninfo_initial.png"));
		sideButton_config(SeasonInfo, "seasoninfo", 0);
		
		MatchInfo=new JButton(new ImageIcon("images/system_img/matchinfo_initial.png"));
		sideButton_config(MatchInfo, "matchinfo", 1);
		MatchInfo.setSelected(true);
		
		TeamInfo=new JButton(new ImageIcon("images/system_img/teaminfo_initial.png"));
		sideButton_config(TeamInfo, "teaminfo", 2);
		
		PlayerInfo=new JButton(new ImageIcon("images/system_img/playerinfo_initial.png"));
		sideButton_config(PlayerInfo, "playerinfo", 3);
		
		Hot=new JButton(new ImageIcon("images/system_img/hot_initial.png"));
		sideButton_config(Hot, "hot", 4);
		
		back=new JButton(new ImageIcon("images/system_img/back_initial.png"));
		back.setBounds(200, 85, 100, 50);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setRolloverIcon(new ImageIcon("images/system_img/back_rollover.png"));
		back.setPressedIcon(new ImageIcon("images/system_img/back_pressed.png"));
		back.addActionListener(this);
		this.add(back);
		
		showdata=new JButton("队员信息");
		showdata.setBounds(785, 240, 100, 30);
		showdata.addActionListener(this);
		showdata.setSelected(true);
		this.add(showdata);
		
		showgraph=new JButton("对比图表");
		showgraph.setBounds(885, 240, 100, 30);
		showgraph.addActionListener(this);
		
		this.add(showgraph);
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
	
	private void addlabels(){
		guestteamimg=new JLabel(new ImageIcon("images/teams/standard/"+matchvo.guestTeam+".png"));
		guestteamimg.setBounds(400, 75, 130, 130);
		this.add(guestteamimg);

		hometeamimg=new JLabel(new ImageIcon("images/teams/standard/"+matchvo.homeTeam+".png"));
		hometeamimg.setBounds(640, 75, 130, 130);
		this.add(hometeamimg);
		
		//VS
		VS=new JLabel("VS");
		VS.setBounds(555, 100, 70, 70);
		VS.setFont(new Font("华文细黑",0,45));
		VS.setForeground(MP.White);
		this.add(VS);
		
		//score
		String scoretemp[];
		num=new JLabel[4];
		scores=new JLabel[5][2];
		scoretemp=matchvo.score.split("-");
		scores[0][0]=new JLabel(scoretemp[0]);
		scores[0][0].setBounds(330, 120, 70, 35);
		scores[0][0].setFont(MP.BigScore);
//		System.out.println(scoretemp[0]+"-"+scoretemp[1]);
		if(Integer.parseInt(scoretemp[0])>Integer.parseInt(scoretemp[1]))
			scores[0][0].setForeground(MP.Red);
		else
			scores[0][0].setForeground(MP.White);
		scores[0][1]=new JLabel(scoretemp[1]);
		scores[0][1].setBounds(770, 120, 70, 35);
		scores[0][1].setFont(MP.BigScore);
		if(Integer.parseInt(scoretemp[0])<Integer.parseInt(scoretemp[1]))
			scores[0][1].setForeground(MP.Red);
		else
			scores[0][1].setForeground(MP.White);
		//score1
		scoretemp=matchvo.score1.split("-");
//		System.out.println(scoretemp[0]+"-"+scoretemp[1]);
			scores[1][0]=new JLabel(scoretemp[0]);
			scores[1][0].setBounds(505, 200, 30, 20);
			scores[1][0].setFont(MP.SmallScore);
			if(Integer.parseInt(scoretemp[0])>Integer.parseInt(scoretemp[1]))
				scores[1][0].setForeground(MP.Red);
			else
				scores[1][0].setForeground(MP.White);
			scores[1][1]=new JLabel(scoretemp[1]);
			scores[1][1].setBounds(505, 220, 30, 20);
			scores[1][1].setFont(MP.SmallScore);
			if(Integer.parseInt(scoretemp[0])<Integer.parseInt(scoretemp[1]))
				scores[1][1].setForeground(MP.Red);
			else
				scores[1][1].setForeground(MP.White);
			num[0]=new JLabel("1");
			num[0].setBounds(510, 180, 30, 15);
			num[0].setFont(MP.Teamname);
			num[0].setForeground(MP.White);
			this.add(num[0]);
			
		//score2
			scoretemp=matchvo.score2.split("-");
//			System.out.println(scoretemp[0]+"-"+scoretemp[1]);
			scores[2][0]=new JLabel(scoretemp[0]);
			scores[2][0].setBounds(505+40, 200, 30, 20);
			scores[2][0].setFont(MP.SmallScore);
			if(Integer.parseInt(scoretemp[0])>Integer.parseInt(scoretemp[1]))
				scores[2][0].setForeground(MP.Red);
			else
				scores[2][0].setForeground(MP.White);
			scores[2][1]=new JLabel(scoretemp[1]);
			scores[2][1].setBounds(505+40, 220, 30, 20);
			scores[2][1].setFont(MP.SmallScore);
			if(Integer.parseInt(scoretemp[0])<Integer.parseInt(scoretemp[1]))
				scores[2][1].setForeground(MP.Red);
			else
				scores[2][1].setForeground(MP.White);
			
			num[1]=new JLabel("2");
			num[1].setBounds(510+40, 180, 30, 15);
			num[1].setFont(MP.Teamname);
			num[1].setForeground(MP.White);
			this.add(num[1]);
			
			//score3
			scoretemp=matchvo.score3.split("-");
//			System.out.println(scoretemp[0]+"-"+scoretemp[1]);
			scores[3][0]=new JLabel(scoretemp[0]);
			scores[3][0].setBounds(505+40*2, 200, 30, 20);
			scores[3][0].setFont(MP.SmallScore);
			if(Integer.parseInt(scoretemp[0])>Integer.parseInt(scoretemp[1]))
				scores[3][0].setForeground(MP.Red);
			else
				scores[3][0].setForeground(MP.White);
			scores[3][1]=new JLabel(scoretemp[1]);
			scores[3][1].setBounds(505+40*2, 220, 30, 20);
			scores[3][1].setFont(MP.SmallScore);
			if(Integer.parseInt(scoretemp[0])<Integer.parseInt(scoretemp[1]))
				scores[3][1].setForeground(MP.Red);
			else
				scores[3][1].setForeground(MP.White);
			
			num[2]=new JLabel("3");
			num[2].setBounds(510+40*2, 180, 30, 15);
			num[2].setFont(MP.Teamname);
			num[2].setForeground(MP.White);
			this.add(num[2]);
			
			//score4
			scoretemp=matchvo.score4.split("-");
//			System.out.println(scoretemp[0]+"-"+scoretemp[1]);
			scores[4][0]=new JLabel(scoretemp[0]);
			scores[4][0].setBounds(505+40*3, 200, 30, 20);
			scores[4][0].setFont(MP.SmallScore);
			if(Integer.parseInt(scoretemp[0])>Integer.parseInt(scoretemp[1]))
				scores[4][0].setForeground(MP.Red);
			else
				scores[4][0].setForeground(MP.White);
			scores[4][1]=new JLabel(scoretemp[1]);
			scores[4][1].setBounds(505+40*3, 220, 30, 20);
			scores[4][1].setFont(MP.SmallScore);
			if(Integer.parseInt(scoretemp[0])<Integer.parseInt(scoretemp[1]))
				scores[4][1].setForeground(MP.Red);
			else
				scores[4][1].setForeground(MP.White);
			
			num[3]=new JLabel("4");
			num[3].setBounds(510+40*3, 180, 30, 15);
			num[3].setFont(MP.Teamname);
			num[3].setForeground(MP.White);
			this.add(num[3]);
		
		for(int i=0;i<scores.length;i++){
			this.add(scores[i][0]);
			this.add(scores[i][1]);
		}
	}

	public void getplayerNum(MatchVO mvo){
		ArrayList<PlayerTechMPO> playerTech=mvo.playerStatistic;
		for(PlayerTechMPO ppo:playerTech){
			if(ppo.team.equals(mvo.guestTeam)){
				playerNum_guest++;
			}else if(ppo.team.equals(mvo.homeTeam)){
				playerNum_home++;
			}
		}
	}
	
	private String switchTeamName(String name){
		switch(name){
		case "ATL":
			return "老鹰";
		case "CHA":
			return "黄蜂";
		case "MIA":
			return "热火";
		case "ORL":
			return "魔术";
		case "WAS":
			return "奇才";

		case "CHI":
			return "公牛";
		case "CLE":
			return "骑士";
		case "DET":
			return "活塞";
		case "IND":
			return "步行者";
		case "MIL":
			return "雄鹿";

		case "BOS":
			return "凯尔特人";
		case "BKN":
			return "篮网";
		case "NYK":
			return "尼克斯";
		case "PHI":
			return "76人";
		case "TOR":
			return "猛龙";


		case "GSW":
			return "勇士";
		case "LAC":
			return "快船";
		case "LAL":
			return "湖人";
		case "PHX":
			return "太阳";
		case "SAC":
			return "国王";

		case "DEN":
			return "掘金";
		case "MIN":
			return "森林狼";
		case "OKC":
			return "雷霆";
		case "POR":
			return "开拓者";
		case "UTA":
			return "勇士";

		case "DAL":
			return "小牛";
		case "HOU":
			return "火箭";
		case "MEM":
			return "灰熊";
		case "NOP":
			return "鹈鹕";
		case "SAS":
			return "马刺";
		default :
			return null;
		}
	}

	private void insertData(MatchVO mvo){
		ArrayList<PlayerTechMPO> playerTech=mvo.playerStatistic;
		int i=0;
		for(PlayerTechMPO ppo:playerTech){
			if(ppo.team.equals(mvo.guestTeam)){
				teaminfo_guest[i][0]=ppo.name;
				teaminfo_guest[i][1]=ppo.time;
				teaminfo_guest[i][2]=ppo.shotIn;
				teaminfo_guest[i][3]=ppo.shot;
				teaminfo_guest[i][4]=ppo.threeShotIn;
				teaminfo_guest[i][5]=ppo.threeShot;
				teaminfo_guest[i][6]=ppo.penaltyShotIn;
				teaminfo_guest[i][7]=ppo.penaltyShot;
				teaminfo_guest[i][8]=ppo.offensiveRebound;
				teaminfo_guest[i][9]=ppo.defensiveRebound;
				teaminfo_guest[i][10]=ppo.rebound;
				teaminfo_guest[i][11]=ppo.secondaryAttack;
				teaminfo_guest[i][12]=ppo.steal;
				teaminfo_guest[i][13]=ppo.blockShot;
				teaminfo_guest[i][14]=ppo.fault;
				teaminfo_guest[i][15]=ppo.foul;
				teaminfo_guest[i][16]=ppo.score;
				i++;
			}

		}
		i=0;
		for(PlayerTechMPO ppo:playerTech){
			if(ppo.team.equals(mvo.homeTeam)){
				teaminfo_home[i][0]=ppo.name;
				teaminfo_home[i][1]=ppo.time;
				teaminfo_home[i][2]=ppo.shotIn;
				teaminfo_home[i][3]=ppo.shot;
				teaminfo_home[i][4]=ppo.threeShotIn;
				teaminfo_home[i][5]=ppo.threeShot;
				teaminfo_home[i][6]=ppo.penaltyShotIn;
				teaminfo_home[i][7]=ppo.penaltyShot;
				teaminfo_home[i][8]=ppo.offensiveRebound;
				teaminfo_home[i][9]=ppo.defensiveRebound;
				teaminfo_home[i][10]=ppo.rebound;
				teaminfo_home[i][11]=ppo.secondaryAttack;
				teaminfo_home[i][12]=ppo.steal;
				teaminfo_home[i][13]=ppo.blockShot;
				teaminfo_home[i][14]=ppo.fault;
				teaminfo_home[i][15]=ppo.foul;
				teaminfo_home[i][16]=ppo.score;
				i++;
			}

		}
	}

	//表格配置
	public void tableguest_config(){
		//------------------------------表格基本属性--------------------------

		teaminfo_guest=new Object[playerNum_guest][columnName.length];
		//define table
		table_guest = new JTable(teaminfo_guest, columnName){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};

		//根据条目名自动调整列宽
		table_guest.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		table_guest.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) table_guest.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		table_guest.setFont(MP.CellFont);
		table_guest.setForeground(MP.CellFg);
		table_guest.getTableHeader().setFont(MP.HeaderFont);
		table_guest.getTableHeader().setForeground(MP.TableFg);
		table_guest.getTableHeader().setOpaque(false);
		table_guest.getTableHeader().setBackground(MP.TableBg);
		//去除边框
		table_guest.setBorder(null);

		//按行修改表格背景
		TableColumnModel model = table_guest.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		table_guest.setShowHorizontalLines(false);
		table_guest.setShowVerticalLines(false);
		//设置选中颜色
		table_guest.setSelectionBackground(MP.LineSelected);

		//设置行高
		table_guest.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH.length;i++){
			table_guest.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH[i]);
		}
		
		table_guest.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			   int row= table_guest.getSelectedRow();
               int column= table_guest.getSelectedColumn();
               if(column==0){
               //得到选中的单元格的值，表格中都是字符串
               Object value= table_guest.getValueAt(row, column);
               PlayerInfoPanel pip=new PlayerInfoPanel(Frame,String.valueOf(value),panelToRemove);
               Frame.remove(panelToRemove);
               Frame.add(pip);
               Frame.repaint();
               }
			 }
		});

	}

	//表格配置
	public void tablehome_config(){
		//------------------------------表格基本属性--------------------------
		teaminfo_home=new Object[playerNum_home][columnName.length];
		//define table
		table_home = new JTable(teaminfo_home, columnName){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		table_home.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		table_home.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) table_home.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		table_home.setFont(MP.CellFont);
		table_home.setForeground(MP.CellFg);
		table_home.getTableHeader().setFont(MP.HeaderFont);
		table_home.getTableHeader().setForeground(MP.TableFg);
		table_home.getTableHeader().setOpaque(false);
		table_home.getTableHeader().setBackground(MP.TableBg);
		//去除边框
		table_home.setBorder(null);

		//按行修改表格背景
		TableColumnModel model = table_home.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		table_home.setShowHorizontalLines(false);
		table_home.setShowVerticalLines(false);
		//设置选中颜色
		table_home.setSelectionBackground(MP.LineSelected);

		//设置行高
		table_home.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH.length;i++){
			table_home.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH[i]);
		}
		
		table_home.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			   int row= table_home.getSelectedRow();
              int column= table_home.getSelectedColumn();
              if(column==0){
              //得到选中的单元格的值，表格中都是字符串
              Object value= table_home.getValueAt(row, column);
              PlayerInfoPanel pip=new PlayerInfoPanel(Frame,String.valueOf(value),panelToRemove);
              Frame.remove(panelToRemove);
              Frame.add(pip);
              Frame.repaint();
              }
			 }
		});


	}

	public void scrollpane_config(JScrollPane jsp){
		//滑动面板信息
		jsp.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jsp.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		jsp.setVisible(true);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		jsp.setBorder(null);
		
		if (jsp.getCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER) == null) {
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
			jsp.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, component);
		}

		if (jsp.getCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER) == null) {
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
			jsp.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER,component);
		}
		
		this.add(jsp);
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
				setBackground(MP.EvenTableLine);
			else
				setBackground(MP.OddTableLine);
			return super.getTableCellRendererComponent(t, value, isSelected,
					hasFocus, row, column);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ImageIcon im=new ImageIcon("images/system_img/teams_bg.png");
		g.drawImage(im.getImage(),0,0,this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==SeasonInfo){
			Frame.remove(panelToRemove);
			TeamTechPanel panel=new TeamTechPanel(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		if(arg0.getSource()==TeamInfo){
			Frame.remove(panelToRemove);
			TeamInfoPanel panel=new TeamInfoPanel(Frame);
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
		if(arg0.getSource()==back){
			Frame.remove(panelToRemove);
			Frame.add(panelToReturn);
			Frame.repaint();
		}
		
		if(arg0.getSource()==showdata){
			if(!showdata.isSelected()){
			showdata.setSelected(true);
			showgraph.setSelected(false);
			jsp_guest.setVisible(true);
			jsp_home.setVisible(true);
			guestname.setVisible(true);
			homename.setVisible(true);
			this.remove(matchgraph);
			Frame.repaint();
			}
		}
		if(arg0.getSource()==showgraph){
			if(!showgraph.isSelected()){
				showgraph.setSelected(true);
				showdata.setSelected(false);
				jsp_guest.setVisible(false);
				jsp_home.setVisible(false);
				guestname.setVisible(false);
				homename.setVisible(false);
				matchgraph=new MatchGraph(matchvo);
				matchgraph.setBounds(200, 280, 750, 450);
				this.add(matchgraph);
				Frame.repaint();
				}
		}
	}
}
