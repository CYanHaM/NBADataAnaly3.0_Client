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
import presentation.mainui.TeamListener;
import presentation.matchui.MatchPanel;
import presentation.playerui.PlayerTechPanel;
import presentation.preset.HotPre;
import presentation.teamui.TeamInfoPanel;
import presentation.teamui.TeamTechPanel;
import VO.PlayerTechVO;
import blservice.playertechblservice.FindPlayerTechService;
import bussinesslogic.playertechbl.FindPlayerTech;

public class FastImpPlayer extends JPanel implements ActionListener{
	/**
	 * 进步最快球员
	 * @author blisscry
	 * @date 2015年4月29日21:46:27
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH=1020;
	public static int HEIGHT=670;

	private JButton SeasonInfo;
	private JButton MatchInfo;
	private JButton TeamInfo;
	private JButton PlayerInfo;
	private JButton Hot;
	
	//热点球员，热点球队切换按钮
	private JButton hotplayer;
	private JButton hotteam;
	
	private JButton today;
	private JButton season;
	private JButton fastimp;
	
	//切换框大小
	private static int BOXWIDTH=85;
	private static int BOXHEIGHT=40;

	private int buttonwidth=154;
	private int buttonheight=40;

	//得分，篮板，助攻，盖帽，抢断
	private JButton score;
	private JButton rebound;
	private JButton secondAttack;
	private JButton blockShot;
	private JButton steal;

	private JLabel[] name;
	private JLabel[] info;
	private JLabel[] data;
	private JLabel[] compare;
	private JLabel[] playerimage;
	private JLabel[] teamimage;
	private JLabel[] state;

	private ArrayList<PlayerTechVO> fastImpPlist;
	private String selectedkeyword;

	private ImportHotData fts;

	private HotPre HP;
	private JFrame Frame;
	private JPanel panelToRemove;

	public FastImpPlayer(JFrame frame) {
		Frame=frame;
		panelToRemove=this;
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);

		name=new JLabel[5];
		info=new JLabel[5];
		data=new JLabel[5];
		compare=new JLabel[5];
		playerimage=new JLabel[5];
		teamimage=new JLabel[5];
		state=new JLabel[5];

		fts=new ImportHotData();

		HP=new HotPre();

		addbox();
		addbutton();

		initlabels();
		initdata();
	}

	private void initlabels(){
		for(int i=0;i<5;i++){
			name[i]=new JLabel();
			info[i]=new JLabel();
			data[i]=new JLabel();
			compare[i]=new JLabel();
			playerimage[i]=new JLabel();
			teamimage[i]=new JLabel();
			state[i]=new JLabel();
		}
	}

	private void initdata(){
		insertData();
		addFirstPlayer();
		addOtherPlayers();
		Frame.repaint();
	}

	private void addbox(){
		hotplayer = new JButton(new ImageIcon("images/buttons/hotplayer1.png"));
		hotplayer.setBounds(200, 135, BOXWIDTH, BOXHEIGHT);
		hotplayer.setBorderPainted(false);
		hotplayer.setContentAreaFilled(false);
		hotplayer.setFocusPainted(false);
		hotplayer.setRolloverIcon(new ImageIcon("images/buttons/hotplayer2.png"));
		hotplayer.setPressedIcon(new ImageIcon("images/buttons/hotplayer2.png"));
		hotplayer.setSelectedIcon(new ImageIcon("images/buttons/hotplayer3.png"));
		hotplayer.setSelected(true);
		this.add(hotplayer);
		
		hotteam = new JButton(new ImageIcon("images/buttons/hotteam1.png"));
		hotteam.setBounds(200+BOXWIDTH, 135, BOXWIDTH, BOXHEIGHT);
		hotteam.setBorderPainted(false);
		hotteam.setContentAreaFilled(false);
		hotteam.setFocusPainted(false);
		hotteam.setRolloverIcon(new ImageIcon("images/buttons/hotteam2.png"));
		hotteam.setPressedIcon(new ImageIcon("images/buttons/hotteam2.png"));
		hotteam.setSelectedIcon(new ImageIcon("images/buttons/hotteam3.png"));
		hotteam.addActionListener(this);
		this.add(hotteam);
		
		today = new JButton(new ImageIcon("images/buttons/today1.png"));
		today.setBounds(390, 139, 60, 30);
		today.setBorderPainted(false);
		today.setContentAreaFilled(false);
		today.setFocusPainted(false);
		today.setRolloverIcon(new ImageIcon("images/buttons/today2.png"));
		today.setPressedIcon(new ImageIcon("images/buttons/today2.png"));
		today.setSelectedIcon(new ImageIcon("images/buttons/today3.png"));
		today.addActionListener(this);
		this.add(today);
		season = new JButton(new ImageIcon("images/buttons/season1.png"));
		season.setBounds(450, 139, 60, 30);
		season.setBorderPainted(false);
		season.setContentAreaFilled(false);
		season.setFocusPainted(false);
		season.setRolloverIcon(new ImageIcon("images/buttons/season2.png"));
		season.setPressedIcon(new ImageIcon("images/buttons/season2.png"));
		season.setSelectedIcon(new ImageIcon("images/buttons/season3.png"));
		season.addActionListener(this);
		this.add(season);
		fastimp = new JButton(new ImageIcon("images/buttons/fastimp1.png"));
		fastimp.setBounds(510, 139, 85, 30);
		fastimp.setBorderPainted(false);
		fastimp.setContentAreaFilled(false);
		fastimp.setFocusPainted(false);
		fastimp.setRolloverIcon(new ImageIcon("images/buttons/fastimp2.png"));
		fastimp.setPressedIcon(new ImageIcon("images/buttons/fastimp2.png"));
		fastimp.setSelectedIcon(new ImageIcon("images/buttons/fastimp3.png"));
		fastimp.setSelected(true);
		this.add(fastimp);
	}

	private void addbutton(){
		SeasonInfo=new JButton(new ImageIcon("images/system_img/seasoninfo_initial.png"));
		sideButton_config(SeasonInfo, "seasoninfo", 0);
		
		MatchInfo=new JButton(new ImageIcon("images/system_img/matchinfo_initial.png"));
		sideButton_config(MatchInfo, "matchinfo", 1);
		
		TeamInfo=new JButton(new ImageIcon("images/system_img/teaminfo_initial.png"));
		sideButton_config(TeamInfo, "teaminfo", 2);
		
		PlayerInfo=new JButton(new ImageIcon("images/system_img/playerinfo_initial.png"));
		sideButton_config(PlayerInfo, "playerinfo", 3);
		
		Hot=new JButton(new ImageIcon("images/system_img/hot_initial.png"));
		sideButton_config(Hot, "hot", 4);
		Hot.setSelected(true);
		
		score = new JButton(new ImageIcon("images/buttons/fastimp/score1.png"));
		button_config(score, "score", 0);
		score.setSelected(true);

		rebound = new JButton(new ImageIcon("images/buttons/fastimp/rebound1.png"));
		button_config(rebound, "rebound", 1);
		rebound.setSelected(false);

		secondAttack = new JButton(new ImageIcon("images/buttons/fastimp/secondaryattack1.png"));
		button_config(secondAttack, "secondaryattack", 2);
		secondAttack.setSelected(false);

		blockShot = new JButton(new ImageIcon("images/buttons/fastimp/blockshot1.png"));
		button_config(blockShot, "blockshot", 3);
		blockShot.setSelected(false);

		steal = new JButton(new ImageIcon("images/buttons/fastimp/steal1.png"));
		button_config(steal, "steal", 4);
		steal.setSelected(false);


		this.add(score);
		this.add(rebound);
		this.add(secondAttack);
		this.add(blockShot);
		this.add(steal);
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
	
	private void button_config(JButton button,String info,int num){
		button.setBounds(200+buttonwidth*num, 185, buttonwidth, buttonheight);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setRolloverIcon(new ImageIcon("images/buttons/fastimp/"+info+"2.png"));
		button.setPressedIcon(new ImageIcon("images/buttons/fastimp/"+info+"3.png"));
		button.setSelectedIcon(new ImageIcon("images/buttons/fastimp/"+info+"3.png"));
		button.addActionListener(this);
	}
	private void addFirstPlayer(){
		name[0].setBounds(375, 450, 250, 30);
		name[0].setFont(HP.name);
		name[0].setForeground(HP.LineSelected);
		name[0].setText(fastImpPlist.get(0).name);
		name[0].addMouseListener(new PlayerListener(Frame,panelToRemove,name[0], fastImpPlist.get(0).name,HP.LineSelected,HP.LabelSelected));

		info[0].setBounds(375, 475, 200, 20);
		info[0].setFont(HP.teamandinfo);
		info[0].setForeground(HP.LineSelected);
		info[0].setText(fastImpPlist.get(0).team+" - "+switchTeamName(fastImpPlist.get(0).team));
		info[0].addMouseListener(new TeamListener(Frame,panelToRemove,info[0],fastImpPlist.get(0).team,HP.LineSelected,HP.LabelSelected));
		
		data[0].setBounds(375, 500, 100, 30);
		data[0].setFont(HP.data);
		data[0].setForeground(HP.LineSelected);
		data[0].setText(switchkeyword(0, selectedkeyword));

		playerimage[0].setBounds(205, 275, 189, 300);
		playerimage[0].setIcon(new ImageIcon("images/players/action_small/"+fastImpPlist.get(0).name+".png"));

		teamimage[0].setBounds(450, 260, 130, 130);
		teamimage[0].setIcon(new ImageIcon("images/teams/standard/"+fastImpPlist.get(0).team+".png"));
		
		state[0].setBounds(435, 500, 30, 30);

		compare[0].setBounds(460, 500, 100, 30);
		compare[0].setFont(HP.scoreratio);
		compare[0].setForeground(HP.LineSelected);
		String temp=switchcomp(0,selectedkeyword);
		compare[0].setText(temp);


		this.add(name[0]);
		this.add(info[0]);
		this.add(data[0]);
		this.add(compare[0]);
		this.add(playerimage[0]);
		this.add(teamimage[0]);
		this.add(state[0]);
	}

	private void addOtherPlayers(){
		for(int i=1;i<5;i++){
			name[i].setBounds(740, 260+100*(i-1)-18*(i-1)+10, 200, 20);
			name[i].setFont(HP.name_small);
			name[i].setForeground(HP.LineSelected);
			name[i].setText(fastImpPlist.get(i).name);
			name[i].addMouseListener(new PlayerListener(Frame,panelToRemove,name[i], fastImpPlist.get(i).name,HP.LineSelected,HP.LabelSelected));

			info[i].setBounds(740, 280+100*(i-1)-18*(i-1)+10, 200, 13);
			info[i].setFont(HP.teamandinfo_small);
			info[i].setForeground(HP.LineSelected);
			info[i].setText(fastImpPlist.get(i).team+" - "+switchTeamName(fastImpPlist.get(i).team));
			info[i].addMouseListener(new TeamListener(Frame,panelToRemove,info[i],fastImpPlist.get(i).team,HP.LineSelected,HP.LabelSelected));

			data[i].setBounds(740, 295+100*(i-1)-18*(i-1)+10, 100, 25);
			data[i].setFont(HP.data_small);
			data[i].setForeground(HP.LineSelected);
			data[i].setText(switchkeyword(i, selectedkeyword));

			playerimage[i].setBounds(580, 265+100*(i-1)-18*(i-1), 160, 60);
			ImageIcon im=new ImageIcon("images/players/portrait_long/"+fastImpPlist.get(i).name+".png");

			if(im.getIconWidth()<0)
				im=new ImageIcon("images/players/portrait_long/default_player.png");
			playerimage[i].setIcon(im);

			teamimage[i].setBounds(890, 230+100*(i-1)-18*(i-1), 130, 130);
			teamimage[i].setIcon(new ImageIcon("images/teams/middle/"+fastImpPlist.get(i).team+".png"));
			
			state[i].setBounds(795, 295+100*(i-1)-18*(i-1)+8, 30, 30);

			compare[i].setBounds(820, 295+100*(i-1)-18*(i-1)+10, 100, 25);
			compare[i].setFont(HP.scoreratio);
			compare[i].setForeground(HP.LineSelected);
			String temp=switchcomp(i,selectedkeyword);
			compare[i].setText(temp);

			this.add(name[i]);
			this.add(info[i]);
			this.add(data[i]);
			this.add(compare[i]);
			this.add(playerimage[i]);
			this.add(teamimage[i]);
			this.add(state[i]);
		}
	}

	private void insertData(){
		if(score.isSelected()){
			selectedkeyword="score";
			fastImpPlist=fts.findFastImprovingPlayer("score");
		}
		if(rebound.isSelected()){
			selectedkeyword="rebound";
			fastImpPlist=fts.findFastImprovingPlayer("rebound");
		}
		if(secondAttack.isSelected()){
			selectedkeyword="secondaryAttack";
			fastImpPlist=fts.findFastImprovingPlayer("secondaryattack");
		}
		if(blockShot.isSelected()){
			selectedkeyword="blockshot";
			fastImpPlist=fts.findFastImprovingPlayer("blockshot");
		}
		if(steal.isSelected()){
			selectedkeyword="steal";
			fastImpPlist=fts.findFastImprovingPlayer("steal");
		}
	}

	private String switchkeyword(int count,String keyword){
		String resultword=null;
		switch(keyword){
		case "score":
			resultword=String.valueOf(fastImpPlist.get(count).score);
			break;
		case "rebound":
			resultword=String.valueOf(fastImpPlist.get(count).rebound);
			break;
		case "secondaryAttack":
			resultword=String.valueOf(fastImpPlist.get(count).secondaryAttack);
			break;
		case "blockshot":
			resultword=String.valueOf(fastImpPlist.get(count).blockShot);
			break;
		case "steal":
			resultword=String.valueOf(fastImpPlist.get(count).steal);
			break;
		case "threeshotinrate":
			resultword=String.valueOf(fastImpPlist.get(count).threeShotInRate);
			break;
		case "shotinrate":
			resultword=String.valueOf(fastImpPlist.get(count).shotInRate);
			break;
		case "penaltyshotinrate":
			resultword=String.valueOf(fastImpPlist.get(count).penaltyShotInRate);
			break;
		case "double":
			resultword=String.valueOf(fastImpPlist.get(count).ifDouble);
			break;
		}
		return resultword;
	}

	//switch the ratio conditions
	private String switchcomp(int count,String keyword){
		String result=null;
		double resultword=0.0;
		switch(keyword){
		case "score":
			resultword=fastImpPlist.get(count).scoreImproving;
			break;
		case "rebound":
			resultword=fastImpPlist.get(count).reboundImproving;
			break;
		case "secondaryAttack":
			resultword=fastImpPlist.get(count).secondaryAttackImproving;
			break;
		case "blockshot":
			resultword=fastImpPlist.get(count).blockShotImproving;
			break;
		case "steal":
			resultword=fastImpPlist.get(count).stealImproving;
			break;
		}
		//					resultword=0.323;
		//			System.out.println(resultword);

		DecimalFormat   df   =   new   DecimalFormat("#0.00"); 
		String temp=df.format(resultword);
		if(keyword.equals("double")){
			state[count].setIcon(new ImageIcon("images/system_img/none.png"));
		}else{
			if(resultword>0){
				result=String.valueOf(Double.parseDouble(temp));
				state[count].setIcon(new ImageIcon("images/system_img/up.png"));
			}else if(resultword==0){
				compare[count].setVisible(false);
				state[count].setIcon(new ImageIcon("images/system_img/equal.png"));
			}else if(resultword<0){
				result=String.valueOf(Double.parseDouble(temp));
				state[count].setIcon(new ImageIcon("images/system_img/down.png"));
			}
		}
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
		
		if(arg0.getSource()==hotteam){
			Frame.remove(panelToRemove);
			SeasonHotTeam panel=new SeasonHotTeam(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		if(arg0.getSource()==today){
			Frame.remove(panelToRemove);
			HotPlayerToday panel=new HotPlayerToday(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		if(arg0.getSource()==season){
			Frame.remove(panelToRemove);
			SeasonHotPlayer panel=new SeasonHotPlayer(Frame);
			Frame.add(panel);
			Frame.repaint();
		}
		
		if(arg0.getSource()==score){
			score.setSelected(true);
			rebound.setSelected(false);
			secondAttack.setSelected(false);
			blockShot.setSelected(false);
			steal.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==rebound){
			score.setSelected(false);
			rebound.setSelected(true);
			secondAttack.setSelected(false);
			blockShot.setSelected(false);
			steal.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==secondAttack){
			score.setSelected(false);
			rebound.setSelected(false);
			secondAttack.setSelected(true);
			blockShot.setSelected(false);
			steal.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==blockShot){
			score.setSelected(false);
			rebound.setSelected(false);
			secondAttack.setSelected(false);
			blockShot.setSelected(true);
			steal.setSelected(false);
			initdata();
		}
		if(arg0.getSource()==steal){
			score.setSelected(false);
			rebound.setSelected(false);
			secondAttack.setSelected(false);
			blockShot.setSelected(false);
			steal.setSelected(true);
			initdata();
		}
	}

}