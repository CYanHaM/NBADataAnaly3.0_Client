package presentation.statui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;

import presentation.preset.StatPre;


public class LineChart {
	
	public static JFreeChart createChart(CategoryDataset dataset,String charttitle,String categoryAxisLabel,String valueAxisLabel) {
		  // create the chart..
		  JFreeChart chart = ChartFactory.createLineChart(charttitle,// 标题
			categoryAxisLabel,
			valueAxisLabel,
		    dataset);// 数据
		  // 周围的背景色
		  chart.setBorderPaint(null);
		  chart.setBackgroundPaint(null);
		  // 设置字体，否则会显示乱码
		  TextTitle title = chart.getTitle();
		  // 设置标题字体
		  title.setFont(StatPre.ChartTitleFont);
		  title.setPaint(StatPre.defaultcolor);
		  // 得到一个参考
		  CategoryPlot plot = (CategoryPlot) chart.getPlot();
		  plot.getRangeAxis().setLabelFont(StatPre.ChartRangeAxisFont);
		  plot.getRangeAxis().setLabelPaint(StatPre.defaultcolor);
		  plot.getRangeAxis().setTickLabelFont(StatPre.ChartRangeAxisFont);
		  plot.getRangeAxis().setTickLabelPaint(StatPre.defaultcolor);
		  plot.getRangeAxis().setAxisLinePaint(StatPre.defaultcolor);
		  
		  plot.getDomainAxis().setLabelFont(StatPre.ChartDomainAxisFont);
		  plot.getDomainAxis().setLabelPaint(StatPre.defaultcolor);
		  plot.getDomainAxis().setTickLabelFont(StatPre.ChartRangeAxisFont);
		  plot.getDomainAxis().setTickLabelPaint(StatPre.defaultcolor);
		  plot.getDomainAxis().setAxisLinePaint(StatPre.defaultcolor);
		  
		  LineAndShapeRenderer renderer=(LineAndShapeRenderer) plot.getRenderer();
		  renderer.setSeriesPaint(0, StatPre.LineColor1);
		  renderer.setSeriesPaint(1, StatPre.LineColor2);
		  renderer.setSeriesStroke(0, new BasicStroke(3F));
		  renderer.setSeriesStroke(1, new BasicStroke(3F));
		  renderer.setBaseItemLabelsVisible(true);
		  
		  plot.setRenderer(renderer);
		  
		  chart.getLegend().setVisible(false);
		  plot.setOutlinePaint(null);
		  plot.setBackgroundPaint(null);
		  
		  
		  return chart;
		 }
}
