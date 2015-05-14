package presentation.matchui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import VO.MatchVO;

public class MatchGraph extends JPanel{
	/**
	 * extends the JPanel to show the changes of matchdata using graphs
	 * @author blisscry
	 * @date 2015年5月4日01:43:33
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	private String[] info={"命中%","三分%","罚球%","得分","篮板","助攻","盖帽","抢断","犯规","失误"};
	private double[][] data;
	private int[][] rectlength;
	
	private static int WIDTH=750;
	private static int HEIGHT=450;
	private int rect_height=30;
	private int extra=5;
	private int maxlength=330;
	private int middle_left=335;
	private int middle_right=415;
	private JLabel[] graphname;
	private JLabel[][] graphdata;
	//
	
	public MatchGraph(MatchVO mvo) {
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		
		data=new double[10][2];
		rectlength=new int[10][2];
		graphname=new JLabel[10];
		graphdata=new JLabel[10][2];
		
		handledata(mvo);
		addlabels();
		adddata();
		this.repaint();
		
	}
	
	private void handledata(MatchVO matchinfo){
		data[0][0]=((double) matchinfo.guestShotIn)/((double) matchinfo.guestShot);
		data[0][1]=((double) matchinfo.homeShotIn)/((double) matchinfo.homeShot);
		
		data[1][0]=((double) matchinfo.guestThreeShotIn)/((double) matchinfo.guestThreeShot);
		data[1][1]=((double) matchinfo.homeThreeShotIn)/((double) matchinfo.homeThreeShot);
		
		data[2][0]=((double) matchinfo.guestPenaltyShotIn)/((double) matchinfo.guestPenaltyShot);
		data[2][1]=((double) matchinfo.homePenaltyShotIn)/((double) matchinfo.homePenaltyShot);
		
		data[3][0]=matchinfo.guestScore;
		data[3][1]=matchinfo.homeScore;
		
		data[4][0]=matchinfo.guestTeamOffensiveRebound+matchinfo.guestTeamDeffensiveRebound;
		data[4][1]=matchinfo.homeTeamOffensiveRebound+matchinfo.homeTeamDeffensiveRebound;
		
		data[5][0]=matchinfo.guestTeamSecondaryAttack;
		data[5][1]=matchinfo.homeTeamSecondaryAttack;
		
		data[6][0]=matchinfo.guestTeamBlockShot;
		data[6][1]=matchinfo.homeTeamBlockShot;
		
		data[7][0]=matchinfo.guestTeamSteal;
		data[7][1]=matchinfo.homeTeamSteal;
		
		data[8][0]=matchinfo.guestTeamFoul;
		data[8][1]=matchinfo.homeTeamFoul;
		
		data[9][0]=matchinfo.guestFault;
		data[9][1]=matchinfo.homeFault;
		

		
		rectlength[0][0]=(int) Math.floor(data[0][0]*maxlength);
		rectlength[0][1]=(int) Math.floor(data[0][1]*maxlength);
		
		rectlength[1][0]=(int) Math.floor(data[1][0]*maxlength);
		rectlength[1][1]=(int) Math.floor(data[1][1]*maxlength);
		
		rectlength[2][0]=(int) Math.floor(data[2][0]*maxlength);
		rectlength[2][1]=(int) Math.floor(data[2][1]*maxlength);
		
		rectlength[3][0]=(int) Math.floor((data[3][0]/(data[3][0]+data[3][1]))*maxlength);
		rectlength[3][1]=(int) Math.floor((data[3][1]/(data[3][0]+data[3][1]))*maxlength);
//		System.out.println(rectlength[0][0]+" "+rectlength[0][1]);
		
		rectlength[4][0]=(int) Math.floor((data[4][0]/100)*maxlength);
		rectlength[4][1]=(int) Math.floor((data[4][1]/100)*maxlength);
//		System.out.println(rectlength[1][0]+" "+rectlength[1][1]);
		
		rectlength[5][0]=(int) Math.floor((data[5][0]/100)*maxlength);
		rectlength[5][1]=(int) Math.floor((data[5][1]/100)*maxlength);
		
		rectlength[6][0]=(int) Math.floor((data[6][0]/100)*maxlength);
		rectlength[6][1]=(int) Math.floor((data[6][1]/100)*maxlength);
		
		rectlength[7][0]=(int) Math.floor((data[7][0]/100)*maxlength);
		rectlength[7][1]=(int) Math.floor((data[7][1]/100)*maxlength);
		
		rectlength[8][0]=(int) Math.floor((data[8][0]/100)*maxlength);
		rectlength[8][1]=(int) Math.floor((data[8][1]/100)*maxlength);
		
		rectlength[9][0]=(int) Math.floor((data[9][0]/100)*maxlength);
		rectlength[9][1]=(int) Math.floor((data[9][1]/100)*maxlength);

		
	}
	
	private void addlabels(){
		for(int i=0;i<graphname.length;i++){
			graphname[i]=new JLabel(info[i]);
		if(i>=3){
			graphname[i].setBounds(355, (rect_height+extra)*i+5, 100, 20);
		}else
			graphname[i].setBounds(350, (rect_height+extra)*i+5, 100, 20);
		graphname[i].setFont(new Font("华文细黑",0,18));
		graphname[i].setForeground(Color.WHITE);
		this.add(graphname[i]);
		}
	}
	
	private void adddata(){
		for(int i=0;i<graphname.length;i++){
			if(i<=2){
			graphdata[i][0]=new JLabel(String.valueOf((int)Math.floor(data[i][0]*100))+"%");
			graphdata[i][1]=new JLabel(String.valueOf((int)Math.floor(data[i][1]*100))+"%");
			}else{
				graphdata[i][0]=new JLabel(String.valueOf((int)Math.floor(data[i][0])));
				graphdata[i][1]=new JLabel(String.valueOf((int)Math.floor(data[i][1])));
			}
			
			graphdata[i][0].setBounds(middle_left-rectlength[i][0]-50, (rect_height+extra)*i+5, 50, 25);
			graphdata[i][0].setFont(new Font("华文细黑",0,25));
			graphdata[i][0].setForeground(Color.WHITE);
			this.add(graphdata[i][0]);
			
			graphdata[i][1].setBounds(middle_right+rectlength[i][1], (rect_height+extra)*i+5, 50, 25);
			graphdata[i][1].setFont(new Font("华文细黑",0,25));
			graphdata[i][1].setForeground(Color.WHITE);
			this.add(graphdata[i][1]);
		}
	}
	
	//override the paint method to paint a rect
	public void paintComponent(Graphics g){
		for(int i=0;i<10;i++){
			if(rectlength[i][0]>rectlength[i][1]){
				g.setColor(new Color(160,160,160));
				g.fillRect(middle_left-rectlength[i][0],(rect_height+extra)*i, rectlength[i][0], rect_height);
				g.setColor(Color.WHITE);
				g.fillRect(middle_right, (rect_height+extra)*i, rectlength[i][1], rect_height);
			}else if(rectlength[i][0]<rectlength[i][1]){
				g.setColor(Color.WHITE);
				g.fillRect(middle_left-rectlength[i][0], (rect_height+extra)*i, rectlength[i][0], rect_height);
				g.setColor(new Color(160,160,160));
				g.fillRect(middle_right, (rect_height+extra)*i, rectlength[i][1], rect_height);
			}else{
				g.setColor(Color.WHITE);
				g.fillRect(middle_left-rectlength[i][0], (rect_height+extra)*i, rectlength[i][0], rect_height);
				g.fillRect(middle_right, (rect_height+extra)*i, rectlength[i][1], rect_height);
			}
		}
//		this.repaint();
	}
}
