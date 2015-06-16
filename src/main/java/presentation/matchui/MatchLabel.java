package presentation.matchui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.playerui.PlayerInfoPanel;
import presentation.preset.MatchPre;
import presentation.teamui.TeamPanel;
import VO.MatchVO;
import VO.TeamVO;

public class MatchLabel extends JPanel implements ActionListener{
	/**
	 * 继承JPanel的比赛面板类
	 * @author blisscry
	 * @date 2015年4月28日20:48:21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	private JLabel guestTeam_img;
	private JLabel homeTeam_img;
	private static int teamicon_width=130;
	private static int teamicon_height=130;
	//define JLabels to show the score between two teams
	private JLabel guestTeam_score;
	private JLabel homeTeam_score;
	private JLabel colon;
	private JLabel guestTeam_name1;
	private JLabel guestTeam_name2;
	private JLabel homeTeam_name1;
	private JLabel homeTeam_name2;
	private JButton details;
	//define the labels of the best players
	private JLabel[] scoringChampion;
	private JLabel[] reboundChampion;
	private JLabel[] assistChampion;

	private MatchPre MP;

	JFrame Frame;
	JPanel panelToReturn;
	MatchVO Matchinfo;
	public MatchLabel(MatchVO matchinfo,JFrame frame,JPanel panel) {
		Matchinfo=matchinfo;
		Frame=frame;
		panelToReturn=panel;
		MP = new MatchPre();
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(850, 150);
		//this.setIcon(new ImageIcon("images/matches/matchlabel.png"));

		team_config(matchinfo);
		champion_config(matchinfo);
		addkits();

	}

	//the kits to show the teams'info of the match
	private void team_config(MatchVO matchinfo){
		//--------------------add team icons----------------------------
		guestTeam_img = new JLabel(new ImageIcon("images/teams/standard/"+matchinfo.guestTeam+".png"));
		guestTeam_img.setBounds(15, 15,teamicon_width,teamicon_height);
		guestTeam_img.addMouseListener(new TeamListener(guestTeam_img,matchinfo.guestTeam));
		homeTeam_img = new JLabel(new ImageIcon("images/teams/standard/"+matchinfo.homeTeam+".png"));
		homeTeam_img.setBounds(330, 15,teamicon_width,teamicon_height);
		homeTeam_img.addMouseListener(new TeamListener(homeTeam_img,matchinfo.homeTeam));
		//--------------------add team scores---------------------------
		String scoretemp[]=matchinfo.score.split("-");
		guestTeam_score = new JLabel(scoretemp[0]);
		guestTeam_score.setBounds(168, 40, 100, 30);
		guestTeam_score.setFont(MP.Score);
		if(Integer.parseInt(scoretemp[0])>Integer.parseInt(scoretemp[1]))
			guestTeam_score.setForeground(MP.Red);
		else
			guestTeam_score.setForeground(MP.White);

		homeTeam_score = new JLabel(scoretemp[1]);
		homeTeam_score.setBounds(245, 40, 100, 30);
		homeTeam_score.setFont(MP.Score);
		if(Integer.parseInt(scoretemp[0])>Integer.parseInt(scoretemp[1]))
			homeTeam_score.setForeground(MP.White);
		else
			homeTeam_score.setForeground(MP.Red);

		colon = new JLabel(":");
		colon.setFont(MP.Score);
		colon.setForeground(MP.White);
		colon.setBounds(223, 40, 100, 25);
		//-----------------------add team names------------------------
		guestTeam_name1 = new JLabel(switchTeamName(matchinfo.guestTeam));
		guestTeam_name1.setBounds(135, 95, 100, 20);
		guestTeam_name1.setFont(MP.Teamname);
		guestTeam_name1.setForeground(null);
		guestTeam_name2 = new JLabel(matchinfo.guestTeam);
		guestTeam_name2.setBounds(136, 112, 50, 20);
		guestTeam_name2.setFont(MP.Teamabb);
		guestTeam_name2.setForeground(null);

		homeTeam_name1 = new JLabel(switchTeamName(matchinfo.homeTeam));
		homeTeam_name1.setBounds(300, 95, 100, 20);
		homeTeam_name1.setFont(MP.Teamname);
		homeTeam_name1.setForeground(null);
		homeTeam_name2 = new JLabel(matchinfo.homeTeam);
		homeTeam_name2.setBounds(301, 112, 50, 20);
		homeTeam_name2.setFont(MP.Teamabb);
		homeTeam_name2.setForeground(null);
		
		details = new JButton(new ImageIcon("images/matches/details_1.png"));
		details.setBounds(685, 5, 60, 25);
		details.setBorderPainted(false);
		details.setContentAreaFilled(false);
		details.setFocusPainted(false);
		details.setRolloverIcon(new ImageIcon("images/matches/details_2.png"));
		details.setPressedIcon(new ImageIcon("images/matches/details_3.png"));
		details.addActionListener(this);
	}

	//the kits to show the champion players'info
	private void champion_config(MatchVO matchinfo){
		//------------------add Champions and scores---------------------
		String sC[]=matchinfo.scoringChampion.split("_");
		scoringChampion = new JLabel[3];
		scoringChampion[0] = new JLabel(sC[0]);
		scoringChampion[0].setBounds(530, 33, 130, 30);
		scoringChampion[0].setFont(MP.Champion);
		scoringChampion[0].addMouseListener(new ChampionListener(scoringChampion[0],sC[0]));
		scoringChampion[1] = new JLabel(sC[1]);
		scoringChampion[1].setBounds(670, 33, 100, 30);
		scoringChampion[1].setFont(MP.Champion);
		scoringChampion[2] = new JLabel(sC[2]);
		scoringChampion[2].setBounds(700, 33, 100, 30);
		scoringChampion[2].setFont(MP.Champion);
		scoringChampion[2].addMouseListener(new TeamListener(scoringChampion[2],sC[2]));
		//scoringChampion.setForeground(MP.White);


		String rC[]=matchinfo.reboundChampion.split("_");
		reboundChampion = new JLabel[3];
		reboundChampion[0] = new JLabel(rC[0]);
		reboundChampion[0].setBounds(530, 65, 130, 30);
		reboundChampion[0].setFont(MP.Champion);
		reboundChampion[0].addMouseListener(new ChampionListener(reboundChampion[0],rC[0]));
		reboundChampion[1] = new JLabel(rC[1]);
		reboundChampion[1].setBounds(670, 65, 100, 30);
		reboundChampion[1].setFont(MP.Champion);
		reboundChampion[2] = new JLabel(rC[2]);
		reboundChampion[2].setBounds(700, 65, 100, 30);
		reboundChampion[2].setFont(MP.Champion);
		reboundChampion[2].addMouseListener(new TeamListener(reboundChampion[2],rC[2]));
		//reboundChampion.setForeground(MP.White);


		String aC[]=matchinfo.assistChampion.split("_");
		assistChampion = new JLabel[3];
		assistChampion[0] = new JLabel(aC[0]);
		assistChampion[0].setBounds(530, 97, 130, 30);
		assistChampion[0].setFont(MP.Champion);
		assistChampion[0].addMouseListener(new ChampionListener(assistChampion[0],aC[0]));
		assistChampion[1] = new JLabel(aC[1]);
		assistChampion[1].setBounds(670, 97, 100, 30);
		assistChampion[1].setFont(MP.Champion);
		assistChampion[2] = new JLabel(aC[2]);
		assistChampion[2].setBounds(700, 97, 100, 30);
		assistChampion[2].setFont(MP.Champion);
		assistChampion[2].addMouseListener(new TeamListener(assistChampion[2],aC[2]));
		//assistChampion.setForeground(MP.White);

	}

	private void addkits(){
		this.add(guestTeam_img);
		this.add(homeTeam_img);

		this.add(guestTeam_score);
		this.add(homeTeam_score);
		this.add(colon);

		this.add(guestTeam_name1);
		this.add(guestTeam_name2);
		this.add(homeTeam_name1);
		this.add(homeTeam_name2);
		
		this.add(details);

		this.add(scoringChampion[0]);
		this.add(scoringChampion[1]);
		this.add(scoringChampion[2]);
		this.add(reboundChampion[0]);
		this.add(reboundChampion[1]);
		this.add(reboundChampion[2]);
		this.add(assistChampion[0]);
		this.add(assistChampion[1]);
		this.add(assistChampion[2]);
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


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon im = new ImageIcon("images/matches/matchlabel.png");
		g.drawImage(im.getImage(),0,0,this);
	}


	private class ChampionListener implements MouseListener{

		JLabel choosenlabel;
		String playername;
		public ChampionListener(JLabel Label,String name) {
			choosenlabel=Label;
			playername=name;
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			choosenlabel.setForeground(MP.White);
			//			choosenlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			PlayerInfoPanel tp=new PlayerInfoPanel(Frame,playername,panelToReturn);
			Frame.remove(panelToReturn);
			Frame.add(tp);
			Frame.repaint();
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			choosenlabel.setForeground(null);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}

	private class TeamListener implements MouseListener{
		JLabel choosenlabel;
		String teamname;
		public TeamListener(JLabel Label,String name) {
			choosenlabel=Label;
			teamname=name;
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			choosenlabel.setForeground(MP.White);
			choosenlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			TeamVO tvo=new TeamVO();
			tvo.abbreviation=teamname;
			TeamPanel tp=new TeamPanel(tvo,Frame,panelToReturn);
			Frame.remove(panelToReturn);
			Frame.add(tp);
			Frame.repaint();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			choosenlabel.setForeground(null);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==details){
			Frame.remove(panelToReturn);
//			TeamInfoPanel md=new TeamInfoPanel(mf);
			MatchDetail md=new MatchDetail(Frame, Matchinfo,panelToReturn);
			Frame.add(md);
			Frame.repaint();
		}
	}
}
