package presentation.playerui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VO.ScreeningConditionVO;

public class SeniorSift extends JPanel implements ActionListener{
	/**
	 * the seniorsift panel kit in playertechpanel
	 * @author blisscry
	 * @date 2015年5月4日17:18:28
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	private int width=370;
	private int height=45;
	
	
	private JComboBox<String> condition;
	private String[] conditionItem={"得分","篮板","助攻","盖帽","抢断"};
	private String[] ordergiststring={"score","rebound","secondaryattack","blockshot","steal"};
	private JComboBox<String> comparison;
	private String[] comparisonItem={">=","<="};
	private String[] comparisonstring={">=","<="};
	
	private JTextField info;
	
	private JButton commit;
	
	public SeniorSift() {
		this.setSize(width,height);
		this.setLayout(null);
		this.setOpaque(false);
		
		addkits();
	}
	
	private void addkits(){
		
		condition=new JComboBox<String>(conditionItem);
		condition.setBounds(0, 10, 100, 25);
		condition.setFocusable(false);
		comparison=new JComboBox<String>(comparisonItem);
		comparison.setBounds(110, 10, 50, 25);
		comparison.setFocusable(false);
		
		info=new JTextField();
		info.setBounds(170, 10, 180, 25);
		
//		commit=new JButton("提交");
//		commit.setBounds(510, 150, 160, 30);
//		commit.addActionListener(this);
		
		
		this.add(condition);
		this.add(comparison);
		this.add(info);
//		this.add(commit);
	}
	
//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//		ImageIcon im=new ImageIcon("images/players/seniorsift.png");
//		g.drawImage(im.getImage(),0,0,this);
//	}

	public ScreeningConditionVO getCondition(){
		ScreeningConditionVO scvo=new ScreeningConditionVO();
		switch(String.valueOf(condition.getSelectedItem())){
		case "得分":
			scvo.condition="score";
			break;
		case "篮板":
			scvo.condition="rebound";
			break;
		case "助攻":
			scvo.condition="secondaryattack";
			break;
		case "盖帽":
			scvo.condition="blockshot";
			break;
		case "抢断":
			scvo.condition="steal";
			break;
		}
		
		switch(String.valueOf(comparison.getSelectedItem())){
		case ">=":
			scvo.comparison=">=";
			break;
		case "<=":
			scvo.comparison="<=";
			break;
		}
		if(!(info.getText().equals(""))){
		scvo.number=Integer.parseInt(info.getText());
		}else{
			scvo.number=-1;
		}
		return scvo;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==commit){
//			this.setVisible(false);
//			ImportPlayer ip=new ImportPlayer();
//			ScreeningConditionVO scvo=new ScreeningConditionVO();
//			scvo.posotion=String.valueOf(position.getSelectedItem());
//			scvo.division=String.valueOf(division.getSelectedItem());
//			scvo.condition=String.valueOf(condition.getSelectedItem());
//			scvo.comparison=String.valueOf(comparison.getSelectedItem());
//			scvo.number=Integer.parseInt(info.getText());
//			ArrayList<PlayerTechVO> resultvo=ip.sift(scvo);
		}
	}
	
	
}

