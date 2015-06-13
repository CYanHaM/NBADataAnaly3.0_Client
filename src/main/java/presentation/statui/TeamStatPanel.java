package presentation.statui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TeamStatPanel extends JPanel{
	/**
	 * 球队统计显示面板
	 * @author blisscry
	 * @date 2015年6月12日19:58:30
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	//-------------------------界面常量-------------------
	public static int WIDTH=1100;
	public static int HEIGHT=700;
	JFrame Frame;
	JPanel panelToRemove;
	
	public TeamStatPanel(JFrame frame) {
		Frame=frame;
		panelToRemove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		
		CategoryDataset dataset=createDataset();
		ChartPanel chartpanel1=new ChartPanel(new BarChart().createChart(dataset));
		chartpanel1.setBounds(200, 200, 700, 400);
		this.add(chartpanel1);
		this.repaint();
	}
	
	 public static CategoryDataset createDataset() {

		  String series1 = "1";
		  String series2 = "2";
		  String series3 = "3";
		  // 列
		  String category1 = "一月份";
		  String category2 = "二月份";
		  String category3 = "三月份";
		  String category4 = "四月份";
		  String category5 = "五月份";

		  // 创建数据源
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		  // 放入数据
		  dataset.addValue(1.0, series1, category1);
		  dataset.addValue(4.0, series1, category2);
		  dataset.addValue(3.0, series1, category3);
		  dataset.addValue(5.0, series1, category4);
		  dataset.addValue(5.0, series1, category5);

		  dataset.addValue(5.0, series2, category1);
		  dataset.addValue(7.0, series2, category2);
		  dataset.addValue(6.0, series2, category3);
		  dataset.addValue(8.0, series2, category4);
		  dataset.addValue(4.0, series2, category5);

		  dataset.addValue(4.0, series3, category1);
		  dataset.addValue(3.0, series3, category2);
		  dataset.addValue(2.0, series3, category3);
		  dataset.addValue(3.0, series3, category4);
		  dataset.addValue(6.0, series3, category5);

		  return dataset;
		 }
	 
	 public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 ImageIcon im1=new ImageIcon("images/system_img/bg.png");
		g.drawImage(im1.getImage(),0,0,this);
	 }
}
