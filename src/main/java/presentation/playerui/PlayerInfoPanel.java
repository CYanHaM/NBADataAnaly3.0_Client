package presentation.playerui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import presentation.hotspotui.HotPlayerToday;
import presentation.matchui.MatchPanel;
import presentation.preset.PlayerPre;
import presentation.preset.PlayerTechPre;
import presentation.preset.StatPre;
import presentation.teamui.TeamInfoPanel;
import presentation.teamui.TeamTechPanel;
import VO.PlayerTechVO;
import VO.PlayerVO;

public class PlayerInfoPanel extends JPanel implements ActionListener{
	/**
	 * 球员信息显示界面
	 * @author blisscry
	 * @date 2015年5月7日03:15:03
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH=1100;
	public static int HEIGHT=700;

	//定义边缘透明空白区域边界大小，单位px
	public static int e_space=10;
	//定义空出位置大小
	private static int space=20;
	//表格大小
	private static int TABLEWIDTH=900;
	private static int TABLEHEIGHT=415;
	
	private static int locx=201;
	private static int locy=232;
	private static int Button_width=113;
	private static int Button_height=61;

	//侧边栏按钮
	private JButton SeasonInfo;
	private JButton MatchInfo;
	private JButton TeamInfo;
	private JButton PlayerInfo;
	private JButton Hot;

	private JButton back;

	private JLabel playerimg;
	private JLabel[] playerinfo;
	private JLabel[] info;
	private String[] playerinfoname={"姓名","球衣号码","位置","身高","体重","生日","年龄","球龄","毕业学校"};
	private JLabel[] teaminfo;
	private JLabel line;
	private String[] teaminfoname={"全名","缩写","地区","分区"};

	private JComboBox<String> season;
	private int season_w=200;
	private int season_h=30;
	
	private JLabel message;
	private JScrollPane playertech;
	private JTable techtable;
	private String[] columnName={" ","分钟","进攻","防守","命中%","三分%","罚球%","篮板","助攻","盖帽","抢断","失误","犯规","得分"};
	private Object[][] playertechinfo;
	//表格行高
	private static int ROWHEIGHT=50;
	//表格列宽
	private static int[] COLUMNWIDTH={60,50,50,50,65,65,65,50,50,50,50,50,50,50};

	private PlayerVO playervo;
	private PlayerTechVO playertechvo;
	ImportPlayer importplayer;
	PlayerPre PPre;
	PlayerTechPre PTPre;

	JFrame Frame;
	String playername;
	JPanel panelToReturn;
	JPanel panelToRemove;

	public PlayerInfoPanel(JFrame frame,String name,JPanel panel){
		Frame=frame;
		playername=name;
		panelToReturn=panel;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);

		PPre=new PlayerPre();
		PTPre=new PlayerTechPre();
		
		importplayer=new ImportPlayer();
		
		//添加下拉框
		addbox();
		
		playervo=importplayer.showPlayerInfo(playername);
		initdata();
		
		addbuttons();
		ImageIcon img;
		if(playervo==null){
			img=new ImageIcon("images/players/action_small/default_player.png");
			playerimg=new JLabel(img);
			playerimg.setBounds(300, 50, 189, 300);
			this.add(playerimg);
		}
		else{
			img=new ImageIcon("images/players/action_small/"+playervo.name+".png");
			playerimg=new JLabel(img);
			playerimg.setBounds(300, 50, 189, 300);
			this.add(playerimg);

			addlabels();
		}
		
		scrollpane_config();
		table_config();
		
		playertech.setViewportView(techtable);
		Frame.repaint();
		this.repaint();
	}

	private void addbox(){
		season=new JComboBox<String>();
		//TODO delete the test
//		ArrayList<String> seasonlist=importplayer.getPlayerSeasonList();
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
		season.setBounds(WIDTH-TABLEWIDTH-e_space-space,HEIGHT-TABLEHEIGHT-e_space-space-100-35+15,season_w,season_h);
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
	
	//----------------------initial & different methods------------
	private void initdata(){
		System.out.println(playername);
		playertechvo=importplayer.findPlayerTechByName(playername,switchseason((String)season.getSelectedItem()));
		//TODO delete the test
		adddata();
	}

	private void refreshtable(){
		table_config();
		playertech.setViewportView(techtable);
		Frame.repaint();
	}
	
	private void addbuttons(){
		back=new JButton(new ImageIcon("images/system_img/back_initial.png"));
		back.setBounds(200, 85, 100, 50);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setRolloverIcon(new ImageIcon("images/system_img/back_rollover.png"));
		back.setPressedIcon(new ImageIcon("images/system_img/back_pressed.png"));
		back.addActionListener(this);
		this.add(back);
	}

	private void addlabels(){
		playerinfo=new JLabel[9];
		playerinfo[0]=new JLabel(playervo.name);
		playerinfo[0].setBounds(565, 120, 300, 35);
		playerinfo[0].setFont(PPre.bigname);
		playerinfo[0].setForeground(Color.WHITE);

		line=new JLabel(new ImageIcon("images/players/line.png"));
		line.setBounds(565, 152, 200, 4);
		this.add(line);

		playerinfo[1]=new JLabel(String.valueOf(playervo.uniformNum));
		playerinfo[1].setBounds(565, 160, 150, 25);
		playerinfo[1].setFont(PPre.name);
		playerinfo[1].setForeground(Color.WHITE);

		playerinfo[2]=new JLabel(playervo.position);
		playerinfo[2].setBounds(615, 160, 150, 25);
		playerinfo[2].setFont(PPre.name);
		playerinfo[2].setForeground(Color.WHITE);

		playerinfo[3]=new JLabel(playervo.birth);
		playerinfo[3].setBounds(565, 195, 150, 20);
		playerinfo[3].setFont(PPre.smallname);
		playerinfo[3].setForeground(Color.WHITE);

		playerinfo[4]=new JLabel(String.valueOf(playervo.age));
		playerinfo[4].setBounds(565, 215, 150, 20);
		playerinfo[4].setFont(PPre.smallname);
		playerinfo[4].setForeground(Color.WHITE);

		playerinfo[5]=new JLabel(String.valueOf(playervo.exp));
		playerinfo[5].setBounds(590, 215, 150, 20);
		playerinfo[5].setFont(PPre.smallname);
		playerinfo[5].setForeground(Color.WHITE);

		playerinfo[6]=new JLabel(playervo.height+"/英尺-英寸");
		playerinfo[6].setBounds(565, 240, 150, 20);
		playerinfo[6].setFont(PPre.smallname);
		playerinfo[6].setForeground(Color.WHITE);

		playerinfo[7]=new JLabel(String.valueOf(playervo.weight)+"/磅");
		playerinfo[7].setBounds(565, 260, 150, 20);
		playerinfo[7].setFont(PPre.smallname);
		playerinfo[7].setForeground(Color.WHITE);

		playerinfo[8]=new JLabel(playervo.school);
		playerinfo[8].setBounds(565, 280, 200, 20);
		playerinfo[8].setFont(PPre.smallname);
		playerinfo[8].setForeground(Color.WHITE);

		for(int i=0;i<playerinfo.length;i++){
			this.add(playerinfo[i]);
		}

		info=new JLabel[5];
		info[0]=new JLabel("生日");
		info[0].setBounds(485, 195, 100, 20);
		info[0].setForeground(Color.WHITE);
		info[0].setFont(PPre.supersmallname);
		info[1]=new JLabel("年龄/球龄");
		info[1].setBounds(485, 215, 100, 20);
		info[1].setForeground(Color.WHITE);
		info[1].setFont(PPre.supersmallname);
		info[2]=new JLabel("身高");
		info[2].setBounds(485, 240, 100, 20);
		info[2].setForeground(Color.WHITE);
		info[2].setFont(PPre.supersmallname);
		info[3]=new JLabel("体重");
		info[3].setBounds(485, 260, 100, 20);
		info[3].setForeground(Color.WHITE);
		info[3].setFont(PPre.supersmallname);
		info[4]=new JLabel("毕业学校");
		info[4].setBounds(485, 280, 100, 20);
		info[4].setForeground(Color.WHITE);
		info[4].setFont(PPre.supersmallname);

		for(int i=0;i<info.length;i++){
			this.add(info[i]);
		}

		teaminfo=new JLabel[4];
		teaminfo[0]=new JLabel(new ImageIcon("images/teams/middle/"+playertechvo.team+".png"));
		teaminfo[0].setBounds(820, 90, 85, 85);

		teaminfo[1]=new JLabel(switchTeamName(playertechvo.team));
		teaminfo[1].setBounds(810, 175, 200, 25);
		teaminfo[1].setFont(PPre.middlename);
		teaminfo[1].setForeground(Color.WHITE);

		teaminfo[2]=new JLabel(switchDivision(playertechvo.team));
		teaminfo[2].setBounds(810, 195, 200, 20);
		teaminfo[2].setFont(PPre.smallname);
		teaminfo[2].setForeground(Color.WHITE);

		for(int i=0;i<3;i++){
			this.add(teaminfo[i]);
		}
		
		message=new JLabel("赛季/场均数据对比");
		message.setBounds(215, 395, 300, 20);
		message.setFont(PPre.middlename);
		message.setForeground(Color.WHITE);
		this.add(message);
	}

	private void scrollpane_config(){
		playertech=new JScrollPane();
		playertech.setBounds(215, 420, 757, ROWHEIGHT*2+32);
		playertech.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		playertech.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		playertech.setOpaque(false);
		playertech.getViewport().setOpaque(false);
		playertech.setBorder(null);

		this.add(playertech);
	}

	private void table_config(){
		//表格属性设置
		techtable=new JTable(playertechinfo, columnName){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		//根据条目名自动调整列宽
		techtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//设置表格列不可移动
		techtable.getTableHeader().setReorderingAllowed(false);
		//设置列名居中
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) techtable.getTableHeader() .getDefaultRenderer();  
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		//设置表格数据及表头字体字号
		techtable.setFont(PPre.CellFont);
		techtable.setForeground(PPre.CellFg);
		techtable.getTableHeader().setFont(PPre.HeaderFont);
		techtable.getTableHeader().setForeground(PPre.TableFg);
		techtable.getTableHeader().setOpaque(false);
		techtable.getTableHeader().setBackground(PPre.TableBg);
		//去除边框
		techtable.setBorder(null);

		//按行修改表格背景
		TableColumnModel model = techtable.getColumnModel();
		for (int i = 0, n = model.getColumnCount(); i < n; i++) 
		{
			TableColumn column = model.getColumn(i);
			column.setCellRenderer(new RowRenderer());
		}

		//不显示单元格边框线
		techtable.setShowHorizontalLines(false);
		techtable.setShowVerticalLines(false);
		//设置选中颜色
		techtable.setSelectionBackground(PPre.LineSelected);

		//设置行高
		techtable.setRowHeight(ROWHEIGHT);
		//设置列宽
		for(int i=0;i<COLUMNWIDTH.length;i++){
			techtable.getColumnModel().getColumn(i).setPreferredWidth(COLUMNWIDTH[i]);
		}
	}

	private void adddata(){
		playertechinfo=new Object[2][14];
//		"分钟","进攻","防守","命中%","三分%","罚球%","篮板","助攻","盖帽","抢断","失误","犯规","得分"
		playertechinfo[0][0]="Season";
		playertechinfo[0][1]=playertechvo.time;
		playertechinfo[0][2]=playertechvo.offensiveNum;
		playertechinfo[0][3]=playertechvo.defensiveNum;
		playertechinfo[0][4]=String.valueOf((int) Math.floor((playertechvo.shotInRate)*100))+"%";
		playertechinfo[0][5]=String.valueOf((int) Math.floor((playertechvo.threeShotInRate)*100))+"%";
		playertechinfo[0][6]=String.valueOf((int) Math.floor((playertechvo.penaltyShotInRate)*100))+"%";
		playertechinfo[0][7]=playertechvo.rebound;
		playertechinfo[0][8]=playertechvo.secondaryAttack;
		playertechinfo[0][9]=playertechvo.blockShot;
		playertechinfo[0][10]=playertechvo.steal;
		playertechinfo[0][11]=playertechvo.fault;
		playertechinfo[0][12]=playertechvo.foul;
		playertechinfo[0][13]=playertechvo.score;
		
		playertechinfo[1][0]="Average";
		playertechinfo[1][1]=playertechvo.timeave;
		playertechinfo[1][2]=playertechvo.offensiveNumave;
		playertechinfo[1][3]=playertechvo.defensiveNumave;
		playertechinfo[1][4]=String.valueOf((int) Math.floor((playertechvo.shotInRate)*100))+"%";
		playertechinfo[1][5]=String.valueOf((int) Math.floor((playertechvo.threeShotInRate)*100))+"%";
		playertechinfo[1][6]=String.valueOf((int) Math.floor((playertechvo.penaltyShotInRate)*100))+"%";
		playertechinfo[1][7]=playertechvo.reboundave;
		playertechinfo[1][8]=playertechvo.secondaryAttackave;
		playertechinfo[1][9]=playertechvo.blockShotave;
		playertechinfo[1][10]=playertechvo.stealave;
		playertechinfo[1][11]=playertechvo.faultave;
		playertechinfo[1][12]=playertechvo.foulave;
		playertechinfo[1][13]=playertechvo.scoreave;
		
	}

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

	private String switchDivision(String name){
		switch(name){
		case "ATL":
			return "东部联盟/东南分区";
		case "CHA":
			return "东部联盟/东南分区";
		case "MIA":
			return "东部联盟/东南分区";
		case "ORL":
			return "东部联盟/东南分区";
		case "WAS":
			return "东部联盟/东南分区";

		case "CHI":
			return "东部联盟/中央分区";
		case "CLE":
			return "东部联盟/中央分区";
		case "DET":
			return "东部联盟/中央分区";
		case "IND":
			return "东部联盟/中央分区";
		case "MIL":
			return "东部联盟/中央分区";

		case "BOS":
			return "东部联盟/大西洋分区";
		case "BKN":
			return "东部联盟/大西洋分区";
		case "NYK":
			return "东部联盟/大西洋分区";
		case "PHI":
			return "东部联盟/大西洋分区";
		case "TOR":
			return "东部联盟/大西洋分区";


		case "GSW":
			return "西部联盟/太平洋分区";
		case "LAC":
			return "西部联盟/太平洋分区";
		case "LAL":
			return "西部联盟/太平洋分区";
		case "PHX":
			return "西部联盟/太平洋分区";
		case "SAC":
			return "西部联盟/太平洋分区";

		case "DEN":
			return "西部联盟/西北分区";
		case "MIN":
			return "西部联盟/西北分区";
		case "OKC":
			return "西部联盟/西北分区";
		case "POR":
			return "西部联盟/西北分区";
		case "UTA":
			return "西部联盟/西北分区";

		case "DAL":
			return "西部联盟/西南分区";
		case "HOU":
			return "西部联盟/西南分区";
		case "MEM":
			return "西部联盟/西南分区";
		case "NOP":
			return "西部联盟/西南分区";
		case "NOH":
			return "西部联盟/西南分区";
		case "SAS":
			return "西部联盟/西南分区";
		default :
			return null;
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
		if(arg0.getSource()==TeamInfo){
			Frame.remove(panelToRemove);
			TeamInfoPanel panel=new TeamInfoPanel(Frame);
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

	}
}
