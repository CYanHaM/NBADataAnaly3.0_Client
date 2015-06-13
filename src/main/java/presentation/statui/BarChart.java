package presentation.statui;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;


public class BarChart{
	
@SuppressWarnings("deprecation")
public static JFreeChart createChart(CategoryDataset dataset,String charttitle,String x_value,String y_value) {
	  // create the chart..
	  JFreeChart chart = ChartFactory.createBarChart(charttitle,// 标题
	    x_value,// x轴
	    y_value,// y轴
	    dataset,// 数据
	    PlotOrientation.VERTICAL,// 定位，VERTICAL：垂直
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
	  title.setPaint(Color.WHITE);
	  // 得到一个参考
	  CategoryPlot plot = (CategoryPlot) chart.getPlot();
	  // 生成图片的背景色
	  plot.setBackgroundPaint(null);
	//  plot.setBackgroundPaint(Color.white);
	  // 行线的颜色
	  plot.setRangeGridlinePaint(Color.WHITE);
	  // 刻度字体
	  plot.getDomainAxis().setTickLabelFont(font);
	  // X轴名称字体
	  plot.getDomainAxis().setLabelFont(font);
	  

	  // LayeredBarRenderer lbr = new LayeredBarRenderer();//(BarRenderer)类：
	  // //void setSeriesBarWidth(int series,double width)
	  // 设定每个分类的宽度（注意设置不要使某分类被覆盖）
	  // lbr.setSeriesBarWidth(1,0.1);

	  // 设置显示整数
	  NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

	  rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	  // 设置上部空白
	//  rangeAxis.setUpperMargin(0.15);
	  // 设置y轴名称字体
	  rangeAxis.setLabelFont(font);

	  CategoryItemRenderer renderer = plot.getRenderer();
	  renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	  // renderer.setDrawOutlines(true);//是否折线数据点根据不同数据使用不同的形状
	  // renderer.setSeriesShapesVisible(0, true);
	  renderer.setSeriesItemLabelsVisible(0, Boolean.FALSE);

	  CategoryAxis domainAxis = plot.getDomainAxis();
	  domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);// 倾斜45度
	  domainAxis.setLabelFont(new Font("幼圆",0,20));

	  BarRenderer renderer1 = new BarRenderer();// 设置柱子的相关属性
	  // 设置柱子宽度
	  // renderer1.setMaximumBarWidth(0.9);
	  // renderer1.setMaximumBarWidth(0.10000000000000001D); //宽度
	  //设置不显示3D效果
	  renderer1.setBarPainter(new StandardBarPainter());
	  // 设置柱子高度
	  renderer1.setMinimumBarLength(0.5);
	  // 设置柱子边框颜色
	  // renderer1.setBaseOutlinePaint(Color.BLACK);
	  // 设置柱子边框可见
	  // renderer1.setDrawBarOutline(true);
	  // 设置每个地区所包含的平行柱的之间距离，数值越大则间隔越大，图片大小一定的情况下会影响柱子的宽度，可以为负数
	  renderer1.setItemMargin(0.1);
	  renderer1.setSeriesPaint(0, new Color(119,173,78));
	  renderer1.setSeriesPaint(1, new Color(119,173,78));
	  renderer1.setSeriesPaint(2, new Color(119,173,78));
	  // 是否显示阴影
	  renderer1.setShadowVisible(false);
	  // 阴影颜色
	  // renderer1.setShadowPaint(Color.white);
	  plot.setRenderer(renderer1);
	//  plot.setBackgroundAlpha((float) 0.5); // 数据区的背景透明度（0.0～1.0）
	  // 设置柱的透明度
	  // plot.setForegroundAlpha(1.0f);
	  // 设置图形的宽度
	  CategoryAxis caxis = plot.getDomainAxis();
	  // 设置图形右边的空白
	  // caxis.setUpperMargin(0.2);
	  // 设置左边的空白
	  // caxis.setLowerMargin(0.2);

	  return chart;
	 }
}