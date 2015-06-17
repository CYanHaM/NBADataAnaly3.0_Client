package presentation.matchui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PO.MatchPO;
//import blservice.matchblservice.MatchBLService;
//import bussinesslogic.matchbl.Match;
import VO.MatchVO;
import blservice.matchblservice.MatchBLService;
import bussinesslogic.matchbl.Match;
import data.playertech.Find;

public class MatchInfo extends JPanel{
	/**
	 * 继承JPanel的内部滑动面板
	 * @author blisscry
	 * @date 2015年4月28日22:41:55
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	//定义滑动面板动态大小
	private int panel_width=840;
	private int panel_height=500;
	
	private int label_width=850;
	private int label_height=150;
	//
	private MatchBLService mbs;
	private String Date;
	private ArrayList<MatchPO> matches;
	private ArrayList<MatchPO> matchestemp;
	
	JFrame Frame;
	JPanel panelToReturn;
	public MatchInfo(String date,JFrame frame,JPanel panel) {
		Frame=frame;
		panelToReturn=panel;
		mbs=new Match();
		Date=date;
		
//		MatchPre MP=new MatchPre();
		this.setOpaque(false);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(panel_width,panel_height));
		this.setSize(panel_width,panel_height);
		this.setOpaque(false);
//		this.setBackground(MP.MatchInfobg);
		
		//get matches from interface
		Find find=new Find();
//		matches=mbs.showMatchList(Date);
		matches=find.showMatchList(Date);
		matchestemp=matches;
		System.out.println("bl"+matches.get(0).playerStatistic);
		System.out.println("matches"+matches.size());
//		testmatches();
		showMatchinfo(matches);
		
		
	}
	
	public ArrayList<MatchPO> getMatchlist(){
		return matchestemp;
	}
	
	private void testmatches(){
		MatchVO mvo1=new MatchVO();
		mvo1.guestTeam="CHA";
		mvo1.homeTeam="BOS";
		mvo1.score="98-100";
		mvo1.scoringChampion="Anderu Howards_30_CHA";
		mvo1.reboundChampion="Johns Harden_20_BOS";
		mvo1.assistChampion="Jam Wels_14_CHA";
		
		MatchVO mvo2=new MatchVO();
		mvo2.guestTeam="BKN";
		mvo2.homeTeam="ATL";
		mvo2.score="98-100";
		mvo2.scoringChampion="Anderu Howards_30_CHA";
		mvo2.reboundChampion="Johns Harden_20_BOS";
		mvo2.assistChampion="Jam Wels_14_CHA";
		
		MatchVO mvo3=new MatchVO();
		mvo3.guestTeam="BKN";
		mvo3.homeTeam="ATL";
		mvo3.score="103-74";
		mvo3.scoringChampion="Anderu Howards_30_CHA";
		mvo3.reboundChampion="Johns Harden_20_BOS";
		mvo3.assistChampion="Jam Wels_14_CHA";
		
//		matches = new ArrayList<MatchVO>();
//		matches.add(mvo1);
//		matches.add(mvo2);
//		matches.add(mvo3);
//		matches.add(mvo3);
	}
	
	private void showMatchinfo(ArrayList<MatchPO> matches){
		//change the dynamic panel_height when size>3
		if(matches.size()>3){
		panel_height=150*matches.size();
		this.setPreferredSize(new Dimension(panel_width,panel_height));
		this.repaint();
		}
		
		int i=0;
		for(MatchPO mvo:matches){
			MatchLabel ml=new MatchLabel(mvo,Frame,panelToReturn);
			ml.setBounds(0, i*label_height, label_width, label_height);
			this.add(ml);
			i++;
		}
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	
}
