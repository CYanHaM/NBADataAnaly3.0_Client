package presentation.mainui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.teamui.TeamInfoPanel;

public class LoadingPanel extends JPanel{
	/**
	 * 打开软件的加载画面
	 * @author blisscry
	 * @date 2015年4月27日19:31:47
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	//定义主框架大小
	public static int WIDTH=1100;
	public static int HEIGHT=700;
	private static int loadingnum=90;
	JFrame Frame;
	JLabel beginloading;
	JPanel paneltoremove;
	
	
	public LoadingPanel(JFrame frame) {
		Frame=frame;
		paneltoremove=this;
		this.setSize(WIDTH,HEIGHT);
		this.setLayout(null);
		this.setOpaque(false);
		
		beginloading=new JLabel();
		beginloading.setBounds(0, 0, WIDTH, HEIGHT);
		this.add(beginloading);
		Thread beginthread=new Thread(){
			public void run(){
				for(int i=0;i<loadingnum;i++){
					beginloading.setIcon(new ImageIcon("images/system_img/begin/begin_"+i+".png"));
					try {
						Thread.sleep(20);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(i==81){
						MainPanel mp=new MainPanel(Frame);
						TeamInfoPanel tip=new TeamInfoPanel(Frame);
						Frame.remove(paneltoremove);
						Frame.add(mp);
						Frame.add(tip);
						Frame.repaint();
					}
				}
			}
		};
		beginthread.start();
	}
}
