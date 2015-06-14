package presentation.statui;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

import presentation.preset.StatPre;

public class PieChart {

	
	@SuppressWarnings("deprecation")
	public static JFreeChart createChart(PieDataset dataset,String charttitle) {
		  // create the chart..
		  JFreeChart chart = ChartFactory.createPieChart(charttitle,// 标题
		    dataset,// 数据
		    false,// 是否显示图例注释
		    false,// 是否生成工具
		    false);// 是否生成URL链接
		  // 周围的背景色
		  chart.setBorderPaint(null);
		  chart.setBackgroundPaint(null);
		  // 设置标题字体
		  chart.getTitle().setFont(StatPre.ChartTitleFont);
		  chart.getTitle().setPaint(Color.WHITE);
		  // 得到一个参考
		  PiePlot plot = (PiePlot) chart.getPlot();
		  
		  DecimalFormat df = new DecimalFormat("0%");//获得一个DecimalFormat对象，主要是设置小数问题,表示小数点后保留两位。
		  NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
		  StandardPieSectionLabelGenerator sp = new StandardPieSectionLabelGenerator(
			 "{0}:{2}", nf, df);//获得StandardPieSectionLabelGenerator对象,生成的格式，{0}表示section名，{1}表示section的值，{2}表示百分比。可以自定义
		  plot.setLabelGenerator(sp);//设置饼图显示百分比
		  
		  plot.setSectionPaint(0,StatPre.PieColor1);
		  plot.setSectionPaint(1,StatPre.PieColor2);
		  plot.setSectionPaint(2,StatPre.PieColor3);
		  plot.setSectionOutlinesVisible(false);
		  plot.setExplodePercent(0, 0.05);
		  
		  plot.setOutlinePaint(null);
		  plot.setShadowPaint(null);
		  plot.setBackgroundPaint(null);
		  
		  plot.setLabelFont(StatPre.LabelFont);
		  plot.setLabelPaint(StatPre.defaultcolor);
		  plot.setLabelBackgroundPaint(null);
		  plot.setLabelOutlinePaint(null);
		  plot.setLabelShadowPaint(null);
		  
		  return chart;
		 }
}
