package presentation.preset;

import java.awt.Color;
import java.awt.Font;

public class HotPre {
	
	public Color LineSelected;
	public Color LabelSelected;
	
	public Font switchbox;//下拉框字体
	public Font name;
	public Font teamandinfo;
	public Font data;
	
	public Font name_small;
	public Font teamandinfo_small;
	public Font data_small;
	public Font scoreratio;
	
	public HotPre() {
		
		LineSelected=new Color(245,245,245);
		LabelSelected=new Color(60,60,60);
		
		switchbox=new Font("幼圆",0,12);
		name=new Font("华文细黑",0,25);
		teamandinfo=new Font("华文细黑",0,16);
		data=new Font("华文细黑",0,30);
		
		name_small=new Font("华文细黑",0,17);
		teamandinfo_small=new Font("华文细黑",0,13);
		data_small=new Font("华文细黑",0,20);
		scoreratio=new Font("华文细黑",0,17);
	}
}
