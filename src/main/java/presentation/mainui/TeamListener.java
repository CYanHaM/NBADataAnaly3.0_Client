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
	@Override
	public void mouseEntered(MouseEvent arg0) {
		choosenlabel.setForeground(latterColor);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		TeamVO tvo=new TeamVO();
		tvo.abbreviation=teamname;
		TeamPanel tp=new TeamPanel(tvo,Frame,panelToRemove);
		Frame.remove(panelToRemove);
		Frame.add(tp);
		Frame.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		choosenlabel.setForeground(formerColor);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}