package presentation.statui;

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
		  Font font = new Font("华文细黑", 0, 15);
		  TextTitle title = chart.getTitle();
		  // 设置标题字体
		  title.setFont(font);
		  title.setPaint(Color.WHITE);
		  // 得到一个参考
		  CategoryPlot plot = (CategoryPlot) chart.getPlot();
		  plot.getRangeAxis().setLabelFont(new Font("华文细黑",0,15));
		  plot.getRangeAxis().setLabelPaint(Color.WHITE);
		  plot.getDomainAxis().setLabelFont(new Font("华文细黑",0,15));
		  plot.getDomainAxis().setLabelPaint(Color.WHITE);
		  
		  LineAndShapeRenderer renderer=(LineAndShapeRenderer) plot.getRenderer();
		  renderer.setSeriesPaint(0, StatPre.LineColor1);
		  renderer.setSeriesPaint(1, StatPre.LineColor2);
		  renderer.setBaseItemLabelsVisible(false);
		  
		  plot.setRenderer(renderer);
		  
		  chart.getLegend().setVisible(false);
		  plot.setOutlinePaint(null);
		  plot.setBackgroundPaint(null);
		  
		  
		  return chart;
		 }
}
