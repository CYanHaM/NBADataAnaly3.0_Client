package presentation.statui;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.PieDataset;

public class PieChart {

	public static JFreeChart createChart(PieDataset dataset,String charttitle,String x_value,String y_value) {
		  // create the chart..
		  JFreeChart chart = ChartFactory.createPieChart(charttitle,// 标题
		    dataset,// 数据
		    false,// 是否显示图例注释(对于简单的柱状图必须是false)
		    false,// 是否生成工具//没用过
		    false);// 是否生成URL链接//没用过
		  // 周围的背景色
		  chart.setBackgroundPaint(null);
		  // 设置字体，否则会显示乱码
		  Font font = new Font("微软雅黑", 0, 15);
		  TextTitle title = chart.getTitle();
		  // 设置标题字体
		  title.setFont(font);
		  // 得到一个参考
		  CategoryPlot plot = (CategoryPlot) chart.getPlot();
		  // 生成图片的背景色
		  plot.setBackgroundPaint(null);
		//  plot.setBackgroundPaint(Color.white);
		  // 行线的颜色
		  plot.setRangeGridlinePaint(Color.BLACK);
		  // 刻度字体
		  plot.getDomainAxis().setTickLabelFont(font);
		  // X轴名称字体
		  plot.getDomainAxis().setLabelFont(font);
		  


		  return chart;
		 }
}
