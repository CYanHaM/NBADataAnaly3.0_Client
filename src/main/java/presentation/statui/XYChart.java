package presentation.statui;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.data.xy.XYDataset;

public class XYChart {
	public static JFreeChart createChart(XYDataset dataset) {
		JFreeChart localJFreeChart = ChartFactory.createPolarChart("233", dataset, true, false, false);
	    PolarPlot localPolarPlot = (PolarPlot)localJFreeChart.getPlot();
	    localPolarPlot.addCornerTextItem("Corner Item 1");
	    localPolarPlot.addCornerTextItem("Corner Item 2");
	    localPolarPlot.setAngleGridlinePaint(Color.white);
	    localPolarPlot.setRadiusGridlinePaint(Color.white);
	    NumberAxis localNumberAxis = (NumberAxis)localPolarPlot.getAxis();
	    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    ChartUtilities.applyCurrentTheme(localJFreeChart);
		  return localJFreeChart;
		  
		 }
}
