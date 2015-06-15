package presentation.hotspotui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.mainui.PlayerListener;
import presentation.matchui.MatchPanel;
import presentation.playerui.ImportPlayer;
import presentation.playerui.PlayerTechPanel;
import presentation.preset.HotPre;
import presentation.preset.PlayerTechPre;
import presentation.preset.StatPre;
import presentation.teamui.TeamInfoPanel;
import presentation.teamui.TeamTechPanel;
import TypeEnum.TeamTechEnum;
import VO.TeamTechVO;
//import blservice.teamtechblservice.TeamTechBLService;
//import bussinesslogic.TeamTech.TeamTech;

public class SeasonHotTeam extends JPanel implements ActionListener{
	/**
	 * 赛季热点球队
	 * @author blisscry
	 * @date 2015年4月29日21:49:46
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH=1100;
	public static int HEIGHT=700;

	private JButton SeasonInfo;
	private JButton MatchInfo;
	private JButton TeamInfo;
	private JButton PlayerInfo;
	private JButton Hot;
	
	//热点球员，热点球队切换按钮
//	private JComboBox<String> switchbox;
	private JButton hotplayer;
	private JButton hotteam;
	
	//切换框大小
	private static int BOXWIDTH=85;
	private static int BOXHEIGHT=40;

	private int buttonwidth=95;
	private int buttonheight=40;
	
	private JComboBox<String> season;
	private int season_w=200;
	private int season_h=30;

	//场均得分，场均篮板，场均助攻，场均盖帽，场均抢断，三分命中率，投篮命中率，罚球命中率
	private JButton scoreave;
	private JButton reboundave;
	private JButton secondAttackave;
	private JButton blockShotave;
	private JButton stealave;
	private JButton threeshotinrate;
	private JButton shotinrate;
	private JButton penaltyshotinrate;

	private JLabel[] name;
	private JLabel[] info;
	private JLabel[] dataall;
	private JLabel[] dataave;
	private JLabel[] images;

	private ArrayList<TeamTechVO> seasonHTlist;
	private String selectedkeyword;

	private ImportHotData fts;
	private ImportPlayer importseason;

	private HotPre HP;
	private PlayerTechPre PTPre; 
	private JFrame Frame;
	private JPanel panelToRemove;

	public SeasonHotTeam(JFrame frame) {
		Frame=frame;
		panelToRemove=this;
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);

		name=new JLabel[5];
		info=new JLabel[5];
		dataall=new JLabel[5];
		dataave=new JLabel[5];
		images=new JLabel[5];

		fts=new ImportHotData();
		importseason=new ImportPlayer();
		
		HP=new HotPre();
		PTPre=new PlayerTechPre();

		addbox();
		addbutton();

		initlabels();
//		initdata();
	}

	private void initlabels(){
		for(int i=0;i<5;i++){
			name[i]=new JLabel();
			info[i]=new JLabel();
			dataall[i]=new JLabel();
			dataave[i]=new JLabel();
			images[i]=new JLabel();
		}
	}

	private void initdata(){
		insertData();
		addFirstTeam();
		addOtherTeams();
		Frame.repaint();
	}

	private void addbox(){
		season=new JComboBox<String>();
		//TODO delete the test
//		ArrayList<String> seasonlist=importseason.getPlayerSeasonList();
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
		season.setBounds(200,100,season_w,season_h);
		season.setFocusable(false);
		season.setBackground(StatPre.indefaultcolor);
		season.setFont(PTPre.switchbox);
		season.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()==ItemEvent.SELECTED){
					initdata();
				}
			}
		});
		this.add(season);
		
		
		hotplayer = new JButton(new ImageIcon("images/buttons/hotplayer1.png"));
		hotplayer.setBounds(200, 135, BOXWIDTH, BOXHEIGHT);
		hotplayer.setBorderPainted(false);
		hotplayer.setContentAreaFilled(false);
		hotplayer.setFocusPainted(false);
		hotplayer.setRolloverIcon(new ImageIcon("images/buttons/hotplayer2.png"));
		hotplayer.setPressedIcon(new ImageIcon("images/buttons/hotplayer2.png"));
		hotplayer.setSelectedIcon(new ImageIcon("images/buttons/hotplayer3.png"));
		hotplayer.addActionListener(this);
		this.add(hotplayer);
		
		hotteam = new JButton(new ImageIcon("images/buttons/hotteam1.png"));
		hotteam.setBounds(200+BOXWIDTH, 135, BOXWIDTH, BOXHEIGHT);
		hotteam.setBorderPainted(false);
		hotteam.setContentAreaFilled(false);
		hotteam.setFocusPainted(false);
		hotteam.setRolloverIcon(new ImageIcon("images/buttons/hotteam2.png"));
		hotteam.setPressedIcon(new ImageIcon("images/buttons/hotteam2.png"));
		hotteam.setSelectedIcon(new ImageIcon("images/buttons/hotteam3.png"));
		hotteam.setSelected(true);
		this.add(hotteam);
	}

	private void addbutton(){
		scoreave = new JButton(new ImageIcon("images/buttons/seasonhotplayer/scoreave4.png"));
		button_config(scoreave, "scoreave", 0);
		scoreave.setSelected(true);

		reboundave = new JButton(new ImageIcon("images/buttons/seasonhotplayer/reboundave4.png"));
		button_config(reboundave, "reboundave", 1);
		reboundave.setSelected(false);

		secondAttackave = new JButton(new ImageIcon("images/buttons/seasonhotplayer/secondaryattackave4.png"));
		button_config(secondAttackave, "secondaryattackave", 2);
		secondAttackave.setSelected(false);

		blockShotave = new JButton(new ImageIcon("images/buttons/seasonhotplayer/blockshotave4.png"));
		button_config(blockShotave, "blockshotave", 3);
		blockShotave.setSelected(false);

		stealave = new JButton(new ImageIcon("images/buttons/seasonhotplayer/stealave4.png"));
		button_config(stealave, "stealave", 4);
		stealave.setSelected(false);

		threeshotinrate = new JButton(new ImageIcon("images/buttons/seasonhotplayer/threeshotinrate4.png"));
		button_config(threeshotinrate, "threeshotinrate", 5);
		threeshotinrate.setSelected(false);

		shotinrate = new JButton(new ImageIcon("images/buttons/seasonhotplayer/shotinrate4.png"));
		button_config(shotinrate, "shotinrate", 6);
		shotinrate.setSelected(false);

		penaltyshotinrate = new JButton(new ImageIcon("images/buttons/seasonhotplayer/penaltyshotinrate4.png"));
		button_config(penaltyshotinrate, "penaltyshotinrate", 7);
		penaltyshotinrate.setSelected(false);


		this.add(scoreave);
		this.add(reboundave);
		this.add(secondAttackave);
		this.add(blockShotave);
		this.add(stealave);
		this.add(threeshotinrate);
		this.add(shotinrate);
		this.add(penaltyshotinrate);
	}
	
	private void button_config(JButton button,String info,int num){
		button.setBounds(200+buttonwidth*num, 185, buttonwidth, buttonheight);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setRolloverIcon(new ImageIcon("images/buttons/seasonhotplayer/"+info+"5.png"));
		button.setPressedIcon(new ImageIcon("images/buttons/seasonhotplayer/"+info+"5.png"));
		button.setSelectedIcon(new ImageIcon("images/buttons/seasonhotplayer/"+info+"6.png"));
		button.addActionListener(this);
	}

	private void addFirstTeam(){
		name[0].setBounds(430, 450, 250, 30);
		name[0].setFont(HP.name);
		name[0].setForeground(HP.LineSelected);
		name[0].setText(seasonHTlist.get(0).name+" - "+switchTeamName(seasonHTlist.get(0).name));
		name[0].addMouseListener(new PlayerListener(Frame,panelToRemove,name[0], seasonHTlist.get(0).name,HP.LineSelected,HP.LabelSelected));

		info[0].setBounds(430, 475, 200, 20);
		info[0].setFont(HP.teamandinfo);
		info[0].setForeground(HP.LineSelected);
		info[0].setText(switchTeamLeague(seasonHTlist.get(0).name));

		dataall[0].setBounds(430, 500, 100, 30);
		dataall[0].setFont(HP.data);
		dataall[0].setForeground(HP.LineSelected);
		dataall[0].setText(switchkeywordall(0, selectedkeyword));

		dataave[0].setBounds(433, 530, 100, 25);
		dataave[0].setFont(HP.data_small);
		dataave[0].setForeground(HP.LineSelected);
		dataave[0].setText(switchkeywordave(0, selectedkeyword));

		images[0].setBounds(205, 230, 300, 300);
		images[0].setIcon(new ImageIcon("images/teams/big/"+seasonHTlist.get(0).name+".png"));

		this.add(name[0]);
		this.add(info[0]);
		this.add(dataall[0]);
		this.add(dataave[0]);
		this.add(images[0]);
	}
	
	private void addOtherTeams(){
		for(int i=1;i<5;i++){
			name[i].setBounds(740, 250+100*(i-1)-18*(i-1)+10, 200, 20);
			name[i].setFont(HP.name_small);
			name[i].setForeground(HP.LineSelected);
			name[i].setText(seasonHTlist.get(i).name+" - "+switchTeamName(seasonHTlist.get(i).name));
			name[i].addMouseListener(new PlayerListener(Frame,panelToRemove,name[i], seasonHTlist.get(i).name,HP.LineSelected,HP.LabelSelected));

			info[i].setBounds(740, 270+100*(i-1)-18*(i-1)+10, 200, 13);
			info[i].setFont(HP.teamandinfo_small);
			info[i].setForeground(HP.LineSelected);
			info[i].setText(switchTeamLeague(seasonHTlist.get(i).name));

			dataall[i].setBounds(740, 295+100*(i-1)-18*(i-1)+10, 100, 25);
			dataall[i].setFont(HP.data_small);
			dataall[i].setForeground(HP.LineSelected);
			dataall[i].setText(switchkeywordall(i, selectedkeyword));
			
			dataave[i].setBounds(800, 295+100*(i-1)-18*(i-1)+10, 100, 25);
			dataave[i].setFont(HP.data_small);
			dataave[i].setForeground(HP.LineSelected);
			dataave[i].setText(switchkeywordave(i, selectedkeyword));

			images[i].setBounds(625, 265+100*(i-1)-18*(i-1), 160, 60);
			ImageIcon im=new ImageIcon("images/teams/middle/"+seasonHTlist.get(i).name+".png");
			images[i].setIcon(im);

			this.add(name[i]);
			this.add(info[i]);
			this.add(dataall[i]);
			this.add(dataave[i]);
			this.add(images[i]);
		}
	}

	private void insertData(){
		String seasoninfo=switchseason((String) season.getSelectedItem());
		if(scoreave.isSelected()){
			selectedkeyword="scoreave";
			seasonHTlist=fts.findSeasonHotTeam(TeamTechEnum.score,seasoninfo);
		}
		if(reboundave.isSelected()){
			selectedkeyword="reboundave";
			seasonHTlist=fts.findSeasonHotTeam(TeamTechEnum.rebound,seasoninfo);
		}
		if(secondAttackave.isSelected()){
			selectedkeyword="secondaryattackave";
			seasonHTlist=fts.findSeasonHotTeam(TeamTechEnum.secondaryAttack,seasoninfo);
		}
		if(blockShotave.isSelected()){
			selectedkeyword="blockshotave";
			seasonHTlist=fts.findSeasonHotTeam(TeamTechEnum.blockShot,seasoninfo);
		}
		if(stealave.isSelected()){
			selectedkeyword="stealave";
			seasonHTlist=fts.findSeasonHotTeam(TeamTechEnum.steal,seasoninfo);
		}
		if(threeshotinrate.isSelected()){
			selectedkeyword="threeshotinrate";
			seasonHTlist=fts.findSeasonHotTeam(TeamTechEnum.threeShotInRate,seasoninfo);
		}
		if(shotinrate.isSelected()){
			selectedkeyword="shotinrate";
			seasonHTlist=fts.findSeasonHotTeam(TeamTechEnum.shotInRate,seasoninfo);
		}
		if(penaltyshotinrate.isSelected()){
			selectedkeyword="penaltyshotinrate";
			seasonHTlist=fts.findSeasonHotTeam(TeamTechEnum.penaltyShotInRate,seasoninfo);
		}
	}

	private String switchkeywordall(int count,String keyword){
		String resultword=null;
		switch(keyword){
		case "scoreave":
			resultword=String.valueOf(seasonHTlist.get(count).score);
			break;
		case "reboundave":
			resultword=String.valueOf(seasonHTlist.get(count).rebound);
			break;
		case "secondaryattackave":
			resultword=String.valueOf(seasonHTlist.get(count).secondaryAttack);
			break;
		case "blockshotave":
			resultword=String.valueOf(seasonHTlist.get(count).blockShot);
			break;
		case "stealave":
			resultword=String.valueOf(seasonHTlist.get(count).steal);
			break;
		case "threeshotinrate":
			resultword=dataformat(seasonHTlist.get(count).threeShotInRate);
			break;
		case "shotinrate":
			resultword=dataformat(seasonHTlist.get(count).shotInRate);
			break;
		case "penaltyshotinrate":
			resultword=dataformat(seasonHTlist.get(count).penaltyShotInRate);
			break;
		}
		return resultword;
	}

	private String switchkeywordave(int count,String keyword){
		String resultword=null;
		switch(keyword){
		case "scoreave":
			resultword=String.valueOf(seasonHTlist.get(count).scoreave);
			break;
		case "reboundave":
			resultword=String.valueOf(seasonHTlist.get(count).reboundave);
			break;
		case "secondaryattackave":
			resultword=String.valueOf(seasonHTlist.get(count).secondaryAttackave);
			break;
		case "blockshotave":
			resultword=String.valueOf(seasonHTlist.get(count).blockShotave);
			break;
		case "stealave":
			resultword=String.valueOf(seasonHTlist.get(count).stealave);
			break;
		case "threeshotinrate":
			resultword="";
			break;
		case "shotinrate":
			resultword="";
			break;
		case "penaltyshotinrate":
			resultword="";
			break;
		}
		return resultword;
	}

	private String dataformat(double data){
		DecimalFormat   df   =   new   DecimalFormat("#0.00"); 
		String temp=df.format(data);
		String result=String.valueOf(Double.parseDouble(temp)*100);
		result=result+"%";
		return result;
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
		case "NOH":
			return "鹈鹕";
		case "SAS":
			return "马刺";
		default :
				return null;
		}
	}
	
	private String switchTeamLeague(String name){
		switch(name){
		case "ATL":
			return "东部联盟 - 东南分区";
		case "CHA":
			return "东部联盟 - 东南分区";
		case "MIA":
			return "东部联盟 - 东南分区";
		case "ORL":
			return "东部联盟 - 东南分区";
		case "WAS":
			return "东部联盟 - 东南分区";
			
		case "CHI":
			return "东部联盟 - 中央分区";
		case "CLE":
			return "东部联盟 - 中央分区";
		case "DET":
			return "东部联盟 - 中央分区";
		case "IND":
			return "东部联盟 - 中央分区";
		case "MIL":
			return "东部联盟 - 中央分区";
			
		case "BOS":
			return "东部联盟 - 大西洋分区";
		case "BKN":
			return "东部联盟 - 大西洋分区";
		case "NYK":
			return "东部联盟 - 大西洋分区";
		case "PHI":
			return "东部联盟 - 大西洋分区";
		case "TOR":
			return "东部联盟 - 大西洋分区";
			
			
		case "GSW":
			return "西部联盟 - 太平洋分区";
		case "LAC":
			return "西部联盟 - 太平洋分区";
		case "LAL":
			return "西部联盟 - 太平洋分区";
		case "PHX":
			return "西部联盟 - 太平洋分区";
		case "SAC":
			return "西部联盟 - 太平洋分区";
			
		case "DEN":
			return "西部联盟 - 西北分区";
		case "MIN":
			return "西部联盟 - 西北分区";
		case "OKC":
			return "西部联盟 - 西北分区";
		case "POR":
			return "西部联盟 - 西北分区";
		case "UTA":
			return "西部联盟 - 西北分区";
			
		case "DAL":
			return "西部联盟 - 西南分区";
		case "HOU":
			return "西部联盟 - 西南分区";
		case "MEM":
			return "西部联盟 - 西南分区";
		case "NOP":
			return "西部联盟 - 西南分区";
		case "NOH":
			return "西部联盟 - 西南分区";
		case "SAS":
			return "西部联盟 - 西南分区";
		default :
				return null;
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
	
	//paint the background
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ImageIcon im1=new ImageIcon("images/system_img/hot_bg.png");
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
		if(arg0.getSource()==PlayerInfo){
			Frame.remove(panelToRemove);
			PlayerTechPanel panel=new PlayerTechPanel(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		
		if(arg0.getSource()==hotplayer){
			Frame.remove(panelToRemove);
			HotPlayerToday panel=new HotPlayerToday(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		
		if(arg0.getSource()==scoreave){
			scoreave.setSelected(true);
			reboundave.setSelected(false);
			secondAttackave.setSelected(false);
			blockShotave.setSelected(false);
			stealave.setSelected(false);
			threeshotinrate.setSelected(false);
			shotinrate.setSelected(false);
			penaltyshotinrate.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==reboundave){
			scoreave.setSelected(false);
			reboundave.setSelected(true);
			secondAttackave.setSelected(false);
			blockShotave.setSelected(false);
			stealave.setSelected(false);
			threeshotinrate.setSelected(false);
			shotinrate.setSelected(false);
			penaltyshotinrate.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==secondAttackave){
			scoreave.setSelected(false);
			reboundave.setSelected(false);
			secondAttackave.setSelected(true);
			blockShotave.setSelected(false);
			stealave.setSelected(false);
			threeshotinrate.setSelected(false);
			shotinrate.setSelected(false);
			penaltyshotinrate.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==blockShotave){
			scoreave.setSelected(false);
			reboundave.setSelected(false);
			secondAttackave.setSelected(false);
			blockShotave.setSelected(true);
			stealave.setSelected(false);
			threeshotinrate.setSelected(false);
			shotinrate.setSelected(false);
			penaltyshotinrate.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==stealave){
			scoreave.setSelected(false);
			reboundave.setSelected(false);
			secondAttackave.setSelected(false);
			blockShotave.setSelected(false);
			stealave.setSelected(true);
			threeshotinrate.setSelected(false);
			shotinrate.setSelected(false);
			penaltyshotinrate.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==threeshotinrate){
			scoreave.setSelected(false);
			reboundave.setSelected(false);
			secondAttackave.setSelected(false);
			blockShotave.setSelected(false);
			stealave.setSelected(false);
			threeshotinrate.setSelected(true);
			shotinrate.setSelected(false);
			penaltyshotinrate.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==shotinrate){
			scoreave.setSelected(false);
			reboundave.setSelected(false);
			secondAttackave.setSelected(false);
			blockShotave.setSelected(false);
			stealave.setSelected(false);
			threeshotinrate.setSelected(false);
			shotinrate.setSelected(true);
			penaltyshotinrate.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==penaltyshotinrate){
			scoreave.setSelected(false);
			reboundave.setSelected(false);
			secondAttackave.setSelected(false);
			blockShotave.setSelected(false);
			stealave.setSelected(false);
			threeshotinrate.setSelected(false);
			shotinrate.setSelected(false);
			penaltyshotinrate.setSelected(true);
			initdata();
		}
	}
}
