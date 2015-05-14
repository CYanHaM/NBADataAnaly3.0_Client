package presentation.preset;

import java.awt.Color;
import java.awt.Font;

public class PlayerTechPre {
	//颜色预设
	public Color OddTableLine;//奇数行颜色
	public Color EvenTableLine;//偶数行颜色
	public Color LineSelected;//单元行选中颜色
	public Color TableFg;//表格标题栏字体颜色
	public Color CellFg;//表格单元格字体颜色
	public Color TableBg;//表格标题栏背景颜色
	public Color TableSelBg;//表格标题栏选中背景
	//字体预设
	public Font switchbox;//下拉框字体
	public Font CellFont;//单元格字体
	public Font HeaderFont;//表头字体

	public PlayerTechPre(){

		//偶数行灰蓝色RGB 211,221,240
		OddTableLine=new Color(255,255,255);
		//奇数行白色RGB 255,255,255
		EvenTableLine=new Color(211,221,240);
		//选中行为灰色RGB 210,210,210
		LineSelected=new Color(210,210,210);
		//标题栏字体颜色白色RGB 255,255,255
		TableFg=new Color(255,255,255);
		//标题栏字体颜色黑色RGB 40,40,40
		CellFg=new Color(80,80,80);
		//标题栏背景颜色灰黑色RGB 135,138,143
		TableBg=new Color(135,138,143);
		//标题栏选中背景颜色灰白色RGB 
		TableSelBg=new Color(153,153,153);

		switchbox=new Font("幼圆",0,12);
		CellFont=new Font("微软雅黑",0,12);
		HeaderFont=new Font("黑体",0,11);
	}
}
