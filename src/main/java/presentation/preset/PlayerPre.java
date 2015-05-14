package presentation.preset;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class PlayerPre {
	//颜色预设
	public Color OddTableLine;//奇数行颜色
	public Color EvenTableLine;//偶数行颜色
	public Color LineSelected;//单元行选中颜色
	public Color TableFg;//表格标题栏字体颜色
	public Color CellFg;//表格单元格字体颜色
	public Color TableBg;//表格标题栏背景颜色
	public Color TableSelBg;//表格标题栏选中背景
	
	public Color label;
	//字体预设
	public Font switchbox;//下拉框字体
	public Font CellFont;//单元格字体
	public Font HeaderFont;//表头字体
	
	public Font message;
	public Font messageSmall;
	public Font fullName;//球队全名
	public Font abbreviation;//缩写
	public Font location;//所在地
	public Font division;//赛区
	public Font partition;//分区
	public Font homeCourt;//主场
	public Font time;//建立时间
	
	public Font bigname;
	public Font name;
	public Font middlename;
	public Font smallname;
	public Font supersmallname;

	public PlayerPre(){

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
		
		//显示球队信息的字体颜色
		label=new Color(235,235,235);

		switchbox=new Font("幼圆",0,12);
		CellFont=new Font("Arial",0,12);
		HeaderFont=new Font("黑体",0,11);
		
		
		message=new Font("微软雅黑",0,15);
		messageSmall=new Font("微软雅黑",0,13);
		fullName=new Font("Arial",0,25);
		abbreviation=new Font("Arial",0,11);
		location=new Font("Arial",0,15);
		division=new Font("Arial",0,15);
		partition=new Font("Arial",0,15);
		homeCourt=new Font("Arial",0,15);
		time=new Font("Arial",0,12);
		
		bigname=new Font("华文细黑",0,35);
		name=new Font("华文细黑",0,25);
		middlename=new Font("华文细黑",0,20);
		smallname=new Font("华文细黑",0,15);
		supersmallname=new Font("微软雅黑",0,13);
	}
}
