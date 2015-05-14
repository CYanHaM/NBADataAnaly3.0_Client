package presentation.playerui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import VO.ScreeningConditionVO;

public class SeniorSiftPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//定义滑动面板动态大小
	private int panel_width=400;
	private int panel_height=90;
	
	private int label_width=400;
	private int label_height=35;
	
	private JComboBox<String> position;
	private String[] positionItem={"前锋","中锋","后卫"};
	private String[] positionstring={"F","C","G"};
	private JComboBox<String> division;
	private String[] divisionItem={"东部","西部"};
	private String[] divisionstring={"E","W"};
	
	public ArrayList<ScreeningConditionVO> resultcondition;
	public ArrayList<SeniorSift> siftlist; 
	
	public int conditionCount=0;
	public PlayerTechPanel transferpanel;

	public SeniorSiftPanel(PlayerTechPanel targetpanel) {
		transferpanel=targetpanel;
		this.setLayout(null);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(panel_width,panel_height));
		
		resultcondition=new ArrayList<ScreeningConditionVO>();
		siftlist=new ArrayList<SeniorSift>();
		
		position=new JComboBox<String>(positionItem);
		position.setBounds(0, 5, 80, 25);
		position.setFocusable(false);
		division=new JComboBox<String>(divisionItem);
		division.setBounds(90, 5, 80, 25);
		division.setFocusable(false);
		this.add(position);
		this.add(division);
		
		addcondition();
	}
	
	public void addcondition(){
		SeniorSift ss=new SeniorSift();
		ss.setBounds(0, 35+label_height*conditionCount, label_width,label_height);
		this.add(ss);
		siftlist.add(ss);
		if(conditionCount>0){
			panel_height=90+35*conditionCount;
			this.setPreferredSize(new Dimension(panel_width,panel_height));
			this.repaint();
		}
		conditionCount++;
	}
	
	public void deletecondition(){
		if(conditionCount>1){
			conditionCount--;
		this.remove(siftlist.get(siftlist.size()-1));
		siftlist.remove(siftlist.size()-1);
		panel_height=panel_height-35;
		this.setPreferredSize(new Dimension(panel_width,panel_height));
		this.repaint();
		}
	}

	public void getScreeningCondition(){
		resultcondition.clear();
		for(int i=0;i<siftlist.size();i++){
			ScreeningConditionVO temp=siftlist.get(i).getCondition();
			switch(String.valueOf(position.getSelectedItem())){
			case "前锋":
				temp.position="F";
				break;
			case "中锋":
				temp.position="C";
				break;
			case "后卫":
				temp.position="G";
				break;
			}
			switch(String.valueOf(division.getSelectedItem())){
			case "东部":
				temp.division="E";
				break;
			case "西部":
				temp.division="W";
				break;
			}
			resultcondition.add(temp);
		}
//		transferpanel.screeningconditions.clear();
		transferpanel.screeningconditions=resultcondition;
	}
}
