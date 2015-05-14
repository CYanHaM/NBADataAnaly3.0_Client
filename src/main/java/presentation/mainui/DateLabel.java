package presentation.mainui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DateLabel extends JLabel{
	/**
	 * 整体可用的日历板label类
	 * 响应Iten可扩展为JLabel,JButton,JTextField等
	 * @author blisscry\
	 * @date 2015年4月27日11:31:15
	 * @version 1.3
	 */
	private static final long serialVersionUID = 1L;

	private Calendar calendar;
	private Calendar now = Calendar.getInstance();
	private JPanel calendarPanel;
	private java.awt.Font font = new java.awt.Font("Times",java.awt.Font.PLAIN,12);
	private java.awt.Font dayfont = new java.awt.Font("Times",java.awt.Font.PLAIN,14);
	private java.text.SimpleDateFormat sdf;
	private final LabelManager lm = new LabelManager();
	private javax.swing.Popup pop;
	//定义日历的三个面板
	private TitlePanel titlePanel;
	private BodyPanel bodyPanel;
	private FooterPanel footerPanel;
	//定义绑定的日期显示项
	private JComponent showDate;
	private boolean isShow = false;
	//定义日期显示模式
	private static final String DEFAULTFORMAT = "yyyy-MM-dd";
	private static final String[] WEEKTEXT = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
	private static WeekLabel[] weekLabels = new WeekLabel[7];
	private static int defaultStartDAY = 0;//0表示从SUN开始，1表示从MON开始
	//定义鼠标点击颜色
	private static Color hoverColor = new Color(150,150,150);
	//定义初始label颜色
	private static Color initColor = new  Color(235,235,235);

	public DateLabel(java.util.Date date, String format, int startDAY,String lastdate) {
		if(startDAY > -1 && startDAY < 7) defaultStartDAY = startDAY;
		int dayIndex = defaultStartDAY;
		for(int i=0; i<7; i++){
			if(dayIndex > 6) dayIndex = 0;
			weekLabels[i] = new WeekLabel(dayIndex, WEEKTEXT[dayIndex]);
			dayIndex ++ ;
		}
		sdf = new java.text.SimpleDateFormat(format);
		calendar = Calendar.getInstance();
//		String[] tempdate=lastdate.split("-");
		
		setTime(lastdate);
		initCalendarPanel();
	}
	
	public void setTime(String lastdate){
		Date lastDate=null;
		try {
			lastDate=sdf.parse(lastdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.setTime(lastDate);
	}
	//使用getinstance单例模式以节省空间
	public static DateLabel getInstance(java.util.Date date, String format,String lastdate){
		return new DateLabel(date, format, defaultStartDAY,lastdate);
	}
	public static DateLabel getInstance(java.util.Date date,String lastdate){
		return getInstance(date, DEFAULTFORMAT,lastdate);
	}
	public static DateLabel getInstance(String format,String lastdate){
		return getInstance(new java.util.Date(), format,lastdate);
	}
	public static DateLabel getInstance(String lastdate){
		return getInstance(new java.util.Date(), DEFAULTFORMAT,lastdate);
	}
	public static DateLabel getInstance(){
		Calendar cal=Calendar.getInstance();
		
		String lastdate=String.valueOf(cal.get(Calendar.YEAR))+"-"+String.valueOf(cal.get(Calendar.MONTH)+1)+"-"+String.valueOf(cal.get(Calendar.DATE));
		return getInstance(new java.util.Date(), DEFAULTFORMAT,lastdate);
	}

	private void initCalendarPanel(){
		calendarPanel = new JPanel(new java.awt.BorderLayout());
		//        calendarPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0xAA, 0xAA, 0xAA)));
		calendarPanel.setBorder(null);
		calendarPanel.add(titlePanel = new TitlePanel(), java.awt.BorderLayout.NORTH);
		calendarPanel.add(bodyPanel = new BodyPanel(), java.awt.BorderLayout.CENTER);
		calendarPanel.add(footerPanel = new FooterPanel(),java.awt.BorderLayout.SOUTH);
		this.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) { }
			public void ancestorRemoved(AncestorEvent event) {hidePanel();}
			//hide pop when move component.
			public void ancestorMoved(AncestorEvent event) {
				hidePanel();
			}
		});
	}

	//注册日历板实现对象
	public void register(final JComponent showComponent) {
		this.showDate = showComponent;
		showComponent.setRequestFocusEnabled(true);
		showComponent.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				showComponent.requestFocusInWindow();
			}
		});
		this.add(showComponent, BorderLayout.CENTER);
		//this.setPreferredSize(new Dimension(90, 25));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		showComponent.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
				if (showComponent.isEnabled()) {
					showComponent.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
			public void mouseExited(MouseEvent me) {
				if (showComponent.isEnabled()) {
					showComponent.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					showComponent.setForeground(initColor);
				}
			}
			public void mousePressed(MouseEvent me) {
				if (showComponent.isEnabled()) {
					showComponent.setForeground(hoverColor);
					if (isShow) {
						hidePanel();
					} else {
						showPanel(showComponent);
					}
				}
			}
			public void mouseReleased(MouseEvent me) {
				if (showComponent.isEnabled()) {
					showComponent.setForeground(initColor);
				}
			}
		});
		showComponent.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				hidePanel();
			}
			public void focusGained(FocusEvent e) { }
		});
	}

	//用于返回当前所选值
	public String getSelectedDate(){
		return sdf.format(calendar.getTime());
	}

	//hide the main panel.
	private void hidePanel() {
		if (pop != null) {
			isShow = false;
			pop.hide();
			pop = null;
		}
	}

	//show the main panel.
	private void showPanel(Component owner) {
		if (pop != null) pop.hide();
		Point show = new Point(0, showDate.getHeight());
		SwingUtilities.convertPointToScreen(show, showDate);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int x = show.x;
		int y = show.y;
		if (x < 0) x = 0;
		if (x > size.width - 212) x = size.width - 212;
		if (y > size.height - 167) y -= 165;
		pop = PopupFactory.getSharedInstance().getPopup(owner, calendarPanel, x, y);
		pop.show();
		isShow = true;
	}

	//修改注册了日历板的组件内容，支持扩展为JButton
	private void commit() {
		if (showDate instanceof JTextField) {
			((JTextField) showDate).setText(sdf.format(calendar.getTime()));
		}else if (showDate instanceof JLabel) {
			((JLabel) showDate).setText(sdf.format(calendar.getTime()));
		}
		hidePanel();
	}

	// control panel
	private class TitlePanel extends JPanel {

		private static final long serialVersionUID = -2865282186037420798L;
		private JLabel preYear,preMonth,center,nextMonth,nextYear,centercontainer;

		public TitlePanel(){
			super(new java.awt.BorderLayout());
			this.setBackground(new java.awt.Color(190, 200, 200));
			initTitlePanel();
		}
		private void initTitlePanel(){
			preYear = new JLabel("<<", JLabel.CENTER);
			preYear.setFont(font);
			preMonth = new JLabel("<", JLabel.CENTER);
			preMonth.setFont(font);
			center = new JLabel("", JLabel.CENTER);
			center.setFont(font);
			centercontainer = new JLabel("", JLabel.CENTER);
			centercontainer.setFont(font);
			nextMonth = new JLabel(">", JLabel.CENTER);
			nextMonth.setFont(font);
			nextYear = new JLabel(">>", JLabel.CENTER);
			nextYear.setFont(font);

			preYear.setToolTipText("Last Year");
			preMonth.setToolTipText("Last Month");
			nextMonth.setToolTipText("Next Month");
			nextYear.setToolTipText("Next Year");

			preYear.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 0, 0));
			preMonth.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 15, 0, 0));
			nextMonth.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 15));
			nextYear.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 10));

			centercontainer.setLayout(new java.awt.BorderLayout());
			centercontainer.add(preMonth,java.awt.BorderLayout.WEST);
			centercontainer.add(center,java.awt.BorderLayout.CENTER);
			centercontainer.add(nextMonth,java.awt.BorderLayout.EAST);

			this.add(preYear,java.awt.BorderLayout.WEST);
			this.add(centercontainer,java.awt.BorderLayout.CENTER);
			this.add(nextYear,java.awt.BorderLayout.EAST);
			this.setPreferredSize(new java.awt.Dimension(210, 25));

			updateDate();

			preYear.addMouseListener(new MyMouseAdapter(preYear, Calendar.YEAR, -1));
			preMonth.addMouseListener(new MyMouseAdapter(preMonth, Calendar.MONTH, -1));
			nextMonth.addMouseListener(new MyMouseAdapter(nextMonth, Calendar.MONTH, 1));
			nextYear.addMouseListener(new MyMouseAdapter(nextYear, Calendar.YEAR, 1));
		}

		private void updateDate() {
			center.setText(calendar.get(Calendar.YEAR)+" - "+(calendar.get(Calendar.MONTH) + 1));
		}
		// listener for control label.
		class MyMouseAdapter extends java.awt.event.MouseAdapter{

			JLabel label;
			private int type, value;

			public MyMouseAdapter(final JLabel label, final int type, final int value){
				this.label = label;
				this.type = type;
				this.value = value;
			}
			public void mouseEntered(java.awt.event.MouseEvent me) {
				label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				label.setForeground(hoverColor);
			}
			public void mouseExited(java.awt.event.MouseEvent me) {
				label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
				label.setForeground(java.awt.Color.BLACK);
			}
			public void mousePressed(java.awt.event.MouseEvent me) {
				calendar.add(type, value);
				label.setForeground(initColor);
				refresh();
			}
			public void mouseReleased(java.awt.event.MouseEvent me) {
				label.setForeground(java.awt.Color.BLACK);
			}
		}
	}
	// body panel, include week labels and day labels.
	private class BodyPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		public BodyPanel(){
			super(new GridLayout(7, 7));
			this.setPreferredSize(new java.awt.Dimension(210, 140));
			initMonthPanel();
		}
		private void initMonthPanel(){
			updateDate();
		}
		public void updateDate() {
			this.removeAll();
			lm.clear();
			java.util.Date temp = calendar.getTime();
			Calendar cal = Calendar.getInstance();
			cal.setTime(temp);
			cal.set(Calendar.DAY_OF_MONTH, 1);

			int index = cal.get(Calendar.DAY_OF_WEEK);
			// 从当月1号前移，一直移动到面板显示的第一天的前一天；当-index + defaultStartDAY为正数时，为避免面板从当月1号之后开始显示，需要前移一周，确保当月显示完全
			if(index > defaultStartDAY) cal.add(Calendar.DAY_OF_MONTH, -index + defaultStartDAY);
			else cal.add(Calendar.DAY_OF_MONTH, -index + defaultStartDAY - 7);

			for (WeekLabel weekLabel : weekLabels) {
				this.add(weekLabel);
			}
			for (int i = 0; i < 42; i++) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				lm.addLabel(new DayLabel(cal));
			}
			for (DayLabel dl : lm.getLabels()) {
				this.add(dl);
			}
		}
	}

	private class FooterPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private JLabel dateLabel;

		public FooterPanel(){
			super(new BorderLayout());
			initFooterPanel();
		}
		private void initFooterPanel(){
			dateLabel = new JLabel("TODAY : "+sdf.format(new java.util.Date()));
			dateLabel.setFont(font);
			dateLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {
					calendar.setTime(new Date());
					refresh();
					commit();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					dateLabel.setForeground(Color.BLACK);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					dateLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
					dateLabel.setForeground(hoverColor);
				}
				@Override
				public void mouseClicked(MouseEvent e) {}
			});
			this.add(dateLabel);
		}
		public void updateDate(){};
	}
	//refresh all panel
	private void refresh() {
		titlePanel.updateDate();
		bodyPanel.updateDate();
		footerPanel.updateDate();
		SwingUtilities.updateComponentTreeUI(this);
	}

	private class WeekLabel extends JLabel {

		private static final long serialVersionUID = 1L;
		private String name;

		public WeekLabel(int index, String name){
			super(name, JLabel.CENTER);
			this.name = name;
			this.setFont(font);
		}
		public String toString(){
			return name;
		}
	}

	private class DayLabel extends JLabel implements java.util.Comparator<DayLabel>, java.awt.event.MouseListener, java.awt.event.MouseMotionListener {

		private static final long serialVersionUID = 1L;
		private boolean isSelected;
		private int year, month, day;

		public DayLabel(Calendar cal){
			super(""+cal.get(Calendar.DAY_OF_MONTH), JLabel.CENTER);
			this.year = cal.get(Calendar.YEAR);
			this.month = cal.get(Calendar.MONTH);
			this.day = cal.get(Calendar.DAY_OF_MONTH);

			this.setFont(dayfont);
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			if(month == calendar.get(Calendar.MONTH)) this.setForeground(java.awt.Color.BLACK);
			else this.setForeground(java.awt.Color.LIGHT_GRAY);

		}
		public boolean getIsSelected() {
			return isSelected;
		}
		public void setSelected(boolean b, boolean isDrag) {
			isSelected = b;
			if (b && !isDrag) {
				int temp = calendar.get(Calendar.MONTH);
				calendar.set(year, month, day);
				if (temp == month) {
					SwingUtilities.updateComponentTreeUI(bodyPanel);
				} else {
					refresh();
				}
				this.repaint();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			//set curr select day's background
			if(day == calendar.get(Calendar.DAY_OF_MONTH) && month == calendar.get(Calendar.MONTH)){
				g.setColor(new java.awt.Color(0xBB, 0xBF, 0xDA));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
			//set current day's border
			if(year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH) && day == now.get(Calendar.DAY_OF_MONTH)){
				Graphics2D gd = (Graphics2D) g;
				gd.setColor(new java.awt.Color(0x55, 0x55, 0x88));
				Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			if (isSelected){
				Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1.0f, new float[] { 2.0f, 2.0f }, 1.0f);
				Graphics2D gd = (Graphics2D) g;
				gd.setStroke(s);
				gd.setColor(Color.BLACK);
				Polygon p = new Polygon();
				p.addPoint(0, 0);
				p.addPoint(getWidth() - 1, 0);
				p.addPoint(getWidth() - 1, getHeight() - 1);
				p.addPoint(0, getHeight() - 1);
				gd.drawPolygon(p);
			}
			super.paintComponent(g);
		}
		public boolean contains(Point p) {
			return this.getBounds().contains(p);
		}
		private void update() {
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) { }
		@Override
		public void mouseMoved(MouseEvent e) { }
		@Override
		public void mouseClicked(MouseEvent e) { }
		@Override
		public void mousePressed(MouseEvent e) {
			isSelected = true;
			update();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			Point p = SwingUtilities.convertPoint(this, e.getPoint(), bodyPanel);
			this.setForeground(Color.BLACK);
			lm.setSelect(p, false);
			commit();
		}
		@Override // change color when mouse over.
		public void mouseEntered(MouseEvent e) {
			this.setForeground(hoverColor);
			this.repaint();
		}
		@Override // change color when mouse exit.
		public void mouseExited(MouseEvent e) {
			if(month == calendar.get(Calendar.MONTH)) this.setForeground(java.awt.Color.BLACK);
			else this.setForeground(java.awt.Color.LIGHT_GRAY);
			this.repaint();
		}
		@Override
		public int compare(DayLabel o1, DayLabel o2) {
			Calendar c1 = Calendar.getInstance();
			c1.set(o1.year, o1.month, o1.day);
			Calendar c2 = Calendar.getInstance();
			c2.set(o2.year, o2.month, o2.day);
			return c1.compareTo(c2);
		}

	}

	private class LabelManager {
		private List<DayLabel> list;

		public LabelManager(){
			list = new ArrayList<DateLabel.DayLabel>();
		}

		public List<DayLabel> getLabels(){
			return list;
		}
		public void addLabel(DayLabel label){
			list.add(label);
		}
		public void clear() {
			list.clear();
		}
		public void setSelect(Point p, boolean b) {
			//如果是拖动,则要优化一下,以提高效率  
			if (b) {
				//表示是否能返回,不用比较完所有的标签,能返回的标志就是把上一个标签和  
				//将要显示的标签找到了就可以了  
				boolean findPrevious = false, findNext = false;
				for (DayLabel lab : list) {
					if (lab.contains(p)) {
						findNext = true;
						if (lab.getIsSelected()) findPrevious = true;
						else lab.setSelected(true, b);
					} else if (lab.getIsSelected()) {
						findPrevious = true;
						lab.setSelected(false, b);
					}
					if (findPrevious && findNext) return;
				}
			}else {
				DayLabel temp = null;
				for (DayLabel m : list) {
					if (m.contains(p)) {
						temp = m;
					} else if (m.getIsSelected()) {
						m.setSelected(false, b);
					}
				}
				if (temp != null) temp.setSelected(true, b);
			}
		}
	}


}
