package presentation.mainui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

//import com.sun.awt.AWTUtilities;

public class MessageFrame extends JFrame{
	/**
	 * 信息提示窗
	 * @author blisscry
	 * @date 2015年3月26日17:26:37
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	//定义登陆框架大小
		public final int POPFRAME_WIDTH=350;
		public final int POPFRAME_HEIGHT=200;
		//下面几个定义用于设置框架的可移动
		//定义鼠标坐标位置
		int X;
		int Y;
		//判断是否在拖动界面
		boolean isDraging;

		public MessageFrame(){
			this.setTitle("MESSAGE");
			this.setSize(POPFRAME_WIDTH, POPFRAME_HEIGHT);
			this.setResizable(false);
			//不显示windows自带边框
			this.setUndecorated(true);
			this.setVisible(true);
			//设置窗体居中
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);

			//窗体透明（此处引用了com.sun.awt.AWTUtilities，需引包）
//			AWTUtilities.setWindowOpaque(this, false);

			//鼠标事件，用于获取鼠标拖动的位置
			addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					isDraging = true;
					X= e.getX();
					Y= e.getY();
				}
				public void mouseReleased(MouseEvent e) {
					isDraging = false;
				}});
			//鼠标移动事件，用于获取移动的路径长度
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					if (isDraging) { 
						//获取当前框架的位置坐标
						int frame_x= getLocation().x;
						int frame_y= getLocation().y;
						setLocation(frame_x+e.getX()-X, frame_y+e.getY()-Y);
					}
				}});
			
		}
}
