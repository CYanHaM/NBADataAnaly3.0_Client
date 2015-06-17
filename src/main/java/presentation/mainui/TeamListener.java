package presentation.mainui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.teamui.TeamPanel;
import VO.TeamVO;

public class TeamListener implements MouseListener{
	JFrame Frame;
	JPanel panelToRemove;
	JLabel choosenlabel;
	String teamname;
	Color formerColor;
	Color latterColor;
	public TeamListener(JFrame frame,JPanel panel,JLabel Label,String name,Color color1,Color color2) {
		Frame=frame;
		panelToRemove=panel;
		choosenlabel=Label;
		teamname=name;
		formerColor=color1;
		latterColor=color2;
	}
	
	private String switchTeam(String name){
		switch(name){
		case "老鹰 Atlanta-Hawks":
			return "ATL";
		case "黄蜂 Charlotte-Hornets":
			return "CHA";
		case "热火 Miami-Heat":
			return "MIA";
		case "魔术 Orlando-Magic":
			return "ORL";
		case "奇才 Washington-Wizards":
			return "WSH";
			
		case "公牛 Chicago-Bulls":
			return "CHI";
		case "骑士 Cleveland-Cavaliers":
			return "CLE";
		case "活塞 Detroit-Pistons":
			return "DET";
		case "步行者 Indiana-Pacers":
			return "IND";
		case "雄鹿 Milwaukee-Bucks":
			return "MIL";
			
		case "凯尔特人 Boston-Celtics":
			return "BOS";
		case "篮网 Brooklyn-Nets":
			return "BKN";
		case "尼克斯 New York-Knicks":
			return "NY";
		case "76人 Philadelphia-76ers":
			return "PHI";
		case "猛龙 Toronto-Raptors":
			return "TOR";
			
			
		case "勇士 Golden State-Warriors":
			return "GS";
		case "快船 Los Angeles-Clippers":
			return "LAC";
		case "湖人 Los Angeles-Lakers":
			return "LAL";
		case "太阳 Phoenix-Suns":
			return "PHX";
		case "国王 Sacramento-Kings":
			return "SAC";
			
		case "掘金 Denver-Nuggets":
			return "DEN";
		case "森林狼 Minnesota-Timberwolves":
			return "MIN";
		case "雷霆 Oklahoma City-Thunder":
			return "OKC";
		case "开拓者 Portland-Trail Blazers":
			return "POR";
		case "爵士 Utah-Jazz":
			return "UTAH";
			
		case "小牛 Dallas-Mavericks":
			return "DAL";
		case "火箭 Houston-Rockets":
			return "HOU";
		case "灰熊 Memphis-Grizzlies":
			return "MEM";
		case "鹈鹕 New Orleans-Pelicans":
			return "NO";
		case "马刺 San Antonio-Spurs":
			return "SEA";
		default :
				return null;
		}
	}
	
	public void mouseEntered(MouseEvent arg0) {
		choosenlabel.setForeground(latterColor);
	}
	public void mouseClicked(MouseEvent arg0) {
		TeamVO tvo=new TeamVO();
		tvo.abbreviation=teamname;
		TeamPanel tp=new TeamPanel(tvo,Frame,panelToRemove);
		Frame.remove(panelToRemove);
		Frame.add(tp);
		Frame.repaint();
	}

	public void mouseExited(MouseEvent arg0) {
		choosenlabel.setForeground(formerColor);
	}

	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}

}