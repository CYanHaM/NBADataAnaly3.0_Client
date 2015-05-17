package presentation.mainui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

import presentation.teamui.TeamInfoPanel;
//import blservice.playerinfoblservice.PlayerInfoService;
//import blservice.playertechblservice.ShowPlayerTechService;
//import blservice.teamblservice.TeamBLservice;
//import blservice.teamtechblservice.TeamTechBLService;
//import bussinesslogic.TeamBL.Team;
//import bussinesslogic.TeamTech.TeamTech;
//import bussinesslogic.playerinfobl.PlayerInfo;
//import bussinesslogic.playertechbl.ShowPlayerTech;

//import com.sun.awt.AWTUtilities;

public class MainFrame extends JFrame{
	/**
	 * 绯荤粺涓荤晫闈紝鍖呮嫭鐣岄潰澶у皬锛屽竷灞�鏂瑰紡锛岄紶鏍囩偣鍑绘嫋鍔ㄤ簨浠�
	 * @author blisscry
	 * @date 2015骞�4鏈�27鏃�19:38:56
	 * @version 2.0
	 */

	private static final long serialVersionUID = 1L;
	//瀹氫箟涓绘鏋跺ぇ灏�
	public static int FRAME_WIDTH=1100;
	public static int FRAME_HEIGHT=700;
	//瀹氫箟榧犳爣鍧愭爣浣嶇疆
	int X;
	int Y;
	//鍒ゆ柇鏄惁鍦ㄦ嫋鍔ㄧ晫闈�
	boolean isDraging;

	JButton MINIMIZE;
	JButton CLOSE;
	JFrame Frame;

	public MainFrame(){
		//瀹氫箟鏁翠釜鐣岄潰澶у皬
		this.setLayout(null);
		this.setTitle("NBADataAnaly");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		//涓嶆樉绀簑indows鑷甫杈规
		this.setUndecorated(true);
		//璁剧疆绐椾綋灞呬腑
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		Frame=this;

		//绐椾綋閫忔槑锛堟澶勫紩鐢ㄤ簡com.sun.awt.AWTUtilities锛岄渶寮曞寘锛�
		//AWTUtilities.setWindowOpaque(this, false);
		//绐椾綋閫忔槑鏂规硶鐩墠寰楀埌鏀硅繘
		this.setBackground(new Color(0,0,0,0));
		

		//榧犳爣浜嬩欢锛岀敤浜庤幏鍙栭紶鏍囨嫋鍔ㄧ殑浣嶇疆
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				isDraging = true;
				X= e.getX();
				Y= e.getY();
			}
			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}});
		//榧犳爣绉诲姩浜嬩欢锛岀敤浜庤幏鍙栫Щ鍔ㄧ殑璺緞闀垮害
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDraging) { 
					//鑾峰彇褰撳墠妗嗘灦鐨勪綅缃潗鏍�
					int frame_x= getLocation().x;
					int frame_y= getLocation().y;
					setLocation(frame_x+e.getX()-X, frame_y+e.getY()-Y);
				}
			}});


		MainPanel mp=new MainPanel(this,FRAME_WIDTH,FRAME_HEIGHT);
		this.add(mp);
		this.repaint();
	}



	public static void main(String[] args){

		//鍔犺浇绯荤粺鐣岄潰
		try {
			// 灏哃ookAndFeel璁剧疆鎴怶indows鏍峰紡
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/*
		 * 杩唬1鎵�浣跨敤鐨勭櫥褰曠晫闈紝杩唬2鍙栨秷浜哛MI锛屽皢涔嬪垹鍘�
		 * MessageFrame messageframe=new MessageFrame();
		 * LoginMsgPanel loginmsgpanel=new LoginMsgPanel(messageframe);
		 * messageframe.add(loginmsgpanel);
		 * messageframe.repaint();
		 */
//		TeamBLservice tbs=new Team();
//		tbs.init();
//		PlayerInfoService pis=new PlayerInfo();
//		pis.PlayerInfoIni();
//		ShowPlayerTechService ppts=new ShowPlayerTech();
//		ppts.PlayerTechIni();
//		TeamTechBLService ttbs=new TeamTech();
//		ttbs.init();
		
		
		MainFrame mf=new MainFrame();
//		MatchPanel mp=new MatchPanel(mf);
		TeamInfoPanel mp=new TeamInfoPanel(mf);
//		HotPlayerToday mp=new HotPlayerToday(mf);
//		SeasonHotPlayer mp=new SeasonHotPlayer(mf);
		mf.add(mp);
		mf.repaint();
	}

}
