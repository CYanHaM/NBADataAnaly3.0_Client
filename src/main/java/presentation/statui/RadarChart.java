package presentation.statui;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleEdge;

import presentation.preset.StatPre;

public class RadarChart {
	
	private static Font titleFont=new Font("华文细黑",0,15);
	private static Font subtitleFont=new Font("华文细黑",0,10);
	
	public static JFreeChart createChart(CategoryDataset dataset,String title) {
		  SpiderWebPlot spiderwebplot = new SpiderWebPlot(dataset);
		  spiderwebplot.setStartAngle(54D);
		  spiderwebplot.setInteriorGap(0.40000000000000002D);
		  spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
		  JFreeChart chart = new JFreeChart(title, titleFont, spiderwebplot, false);
		  //chart configs
		  chart.getTitle().setPaint(Color.WHITE);
		  chart.getTitle().setFont(titleFont);
		  chart.setBorderPaint(null);
		  chart.setBackgroundPaint(null);
		  //plot configs
		  SpiderWebPlot plot=(SpiderWebPlot) chart.getPlot();
		  plot.setOutlinePaint(null);
		  plot.setBackgroundPaint(null);
		  plot.setSeriesPaint(0, StatPre.RaderColor1);
		  plot.setSeriesPaint(1, StatPre.RaderColor2);
		  plot.setSeriesPaint(2, StatPre.RaderColor3);
		  
		  plot.setLabelFont(subtitleFont);
		  plot.setLabelPaint(Color.WHITE);
		  plot.setAxisLinePaint(Color.WHITE);
		  plot.setWebFilled(true);
		  
		  return chart;
		  
		 }
}
