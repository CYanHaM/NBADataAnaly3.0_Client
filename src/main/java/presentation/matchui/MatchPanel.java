package presentation.matchui;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import presentation.hotspotui.HotPlayerToday;
import presentation.mainui.DateLabel;
import presentation.playerui.PlayerTechPanel;
import presentation.preset.MatchPre;
import presentation.teamui.TeamInfoPanel;
import presentation.teamui.TeamTechPanel;
import blservice.matchblservice.MatchBLService;
import bussinesslogic.matchbl.Match;
//import blservice.matchblservice.MatchBLService;
//import bussinesslogic.matchbl.Match;

public class MatchPanel extends JPanel implements ActionListener{
	/**
	 * 比赛面板
	 * @author blisscry
	 * @date 2015年4月28日22:41:05
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	public static int FRAME_WIDTH=1100;
	public static int FRAME_HEIGHT=700;

	//define kits
	private JLabel showcal;
	private JLabel calendar;
	private JButton yesterday;
	private JButton tomorrow;
	private JButton refresh;
	
	private MatchInfo matchinfo;
	private JScrollPane jsp;
	private DateLabel datelabel;
	private MatchBLService mbs;
	public JFrame Frame;
	public JPanel panelToRemove;
	public MatchPanel(JFrame frame) {
		Frame=frame;
		panelToRemove=this;
		mbs=new Match();
		
		this.setLayout(null);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//定义比赛面板预设
		MatchPre MP=new MatchPre();

		showcal = new JLabel(new ImageIcon("images/matches/calendar.png"));
		showcal.setText("日历");
		showcal.setForeground(MP.CallendarinitColor);
		showcal.setFont(MP.CallendarinitFont);
		showcal.setBounds(192, 95, 95, 30);
		calendar = new JLabel();
		calendar.setBounds(208, 125, 90, 30);
		calendar.setFont(MP.CallendarinitFont);
		calendar.setForeground(MP.CallendarinitColor);
		
		//TODO change the lastdate when testing 
		String lastdate="2015-06-14";
//		String lastdate=mbs.returnPresentDate();
		datelabel=DateLabel.getInstance(lastdate);
		datelabel.register(calendar);
		calendar.setText(datelabel.getSelectedDate());

		yesterday = new JButton(new ImageIcon("images/matches/yesterday_1.png"));
		yesterday.setBounds(310, 140, 60, 25);
		yesterday.setBorderPainted(false);
		yesterday.setContentAreaFilled(false);
		yesterday.setFocusPainted(false);
		yesterday.setRolloverIcon(new ImageIcon("images/matches/yesterday_2.png"));
		yesterday.setPressedIcon(new ImageIcon("images/matches/yesterday_3.png"));
		yesterday.addActionListener(this);
		yesterday.setVisible(false);
		
		
		tomorrow = new JButton(new ImageIcon("images/matches/tomorrow_1.png"));
		tomorrow.setBounds(370, 140, 60, 25);
		tomorrow.setBorderPainted(false);
		tomorrow.setContentAreaFilled(false);
		tomorrow.setFocusPainted(false);
		tomorrow.setRolloverIcon(new ImageIcon("images/matches/tomorrow_2.png"));
		tomorrow.setPressedIcon(new ImageIcon("images/matches/tomorrow_3.png"));
		tomorrow.addActionListener(this);
		tomorrow.setVisible(false);
		
		refresh = new JButton(new ImageIcon("images/matches/refresh_1.png"));
		refresh.setBounds(300, 130, 60, 25);
		refresh.setBorderPainted(false);
		refresh.setContentAreaFilled(false);
		refresh.setFocusPainted(false);
		refresh.setRolloverIcon(new ImageIcon("images/matches/refresh_2.png"));
		refresh.setPressedIcon(new ImageIcon("images/matches/refresh_3.png"));
		refresh.addActionListener(this);
		
		this.add(showcal);
		this.add(calendar);
		this.add(yesterday);
		this.add(tomorrow);
		this.add(refresh);

		matchinfo=new MatchInfo(calendar.getText(),Frame,this);
		JScrollPane_config();
		
	}
	

	private void JScrollPane_config(){
		jsp = new JScrollPane();
		jsp.setBounds(185, 160, 860, 485);
		jsp.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		jsp.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		jsp.setBorder(null);
		
		jsp.setViewportView(matchinfo);
		this.add(jsp);
		this.repaint();
		
	}
	
	private void refresh(){
		matchinfo=new MatchInfo(calendar.getText(),Frame,this);
		jsp.setViewportView(matchinfo);
		jsp.repaint();
		Frame.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ImageIcon im=new ImageIcon("images/system_img/main_bg.png");
		g.drawImage(im.getImage(),0,0,this);
	}

	private String checkdate(Calendar cl){
		String[] temp=new String[3];
		temp[0]=String.valueOf(cl.get(Calendar.YEAR));
		temp[1]=String.valueOf(cl.get(Calendar.MONTH)+1);
		temp[2]=String.valueOf(cl.get(Calendar.DATE));
		String resultdate;
		if(cl.get(Calendar.MONTH)+1<10){
			if(cl.get(Calendar.DATE)<10){
				resultdate=temp[0]+"-0"+temp[1]+"-0"+temp[2];
			}else
				resultdate=temp[0]+"-0"+temp[1]+"-"+temp[2];
		}else
			resultdate=temp[0]+"-"+temp[1]+"-"+temp[2];
		
		return resultdate;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==yesterday){
			String[] tempdate=calendar.getText().split("-");
			Calendar cl=Calendar.getInstance();
			cl.set(Integer.parseInt(tempdate[0]),Integer.parseInt(tempdate[1])-1,Integer.parseInt(tempdate[2]));
			int day=cl.get(Calendar.DATE);
			cl.set(Calendar.DATE,day-1);
			
			String result=checkdate(cl);
			calendar.setText(result);
			datelabel=DateLabel.getInstance(result);
			datelabel.register(calendar);
			this.repaint();
			
		}
		if(arg0.getSource()==tomorrow){
			String[] tempdate=calendar.getText().split("-");
			Calendar cl=Calendar.getInstance();
			cl.set(Integer.parseInt(tempdate[0]),Integer.parseInt(tempdate[1])-1,Integer.parseInt(tempdate[2]));
			int day=cl.get(Calendar.DATE);
			cl.set(Calendar.DATE,day+1);
			
			String result=checkdate(cl);
			calendar.setText(result);
			datelabel=DateLabel.getInstance(result);
			datelabel.register(calendar);
			this.repaint();
			
		}
		
		if(arg0.getSource()==refresh){
			refresh();
		}
		
	}

}
