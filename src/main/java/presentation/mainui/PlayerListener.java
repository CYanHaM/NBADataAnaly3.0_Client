package presentation.mainui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.playerui.PlayerInfoPanel;

public class PlayerListener implements MouseListener{
	JFrame Frame;
	JPanel panelToRemove;
	JLabel choosenlabel;
	String playername;
	Color formerColor;
	Color latterColor;
	
	public PlayerListener(JFrame frame,JPanel panel,JLabel Label,String name,Color color1,Color color2) {
		Frame=frame;
		panelToRemove=panel;
		choosenlabel=Label;
		playername=name;
		formerColor=color1;
		latterColor=color2;
	}
	public void mouseEntered(MouseEvent arg0) {
		choosenlabel.setForeground(latterColor);
	}
	public void mouseClicked(MouseEvent arg0) {
//		System.out.println(playername);
		PlayerInfoPanel tp=new PlayerInfoPanel(Frame,playername,panelToRemove);
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