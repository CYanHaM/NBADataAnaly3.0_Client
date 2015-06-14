package presentation.statui;

import javax.swing.JLabel;

public class StatLabel extends JLabel{
	/**
	 * this label is used to present the team or players stat datas
	 * @author blisscry
	 * @date 2015年6月14日16:03:02
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	public int labelwidth;
	public int labelheight;
	
	public JLabel[] headers;
	public JLabel[][] linedatas;
	
	public int[] headerwidth;
	public int headerheight;
	public int[] linewidth;
	public int lineheight;
	

	public StatLabel(int width,int height,Object[][] datainfo,String[] columnnames,String[] linenames) {
		labelwidth=width;
		labelheight=height;
		
		//add headers
		headers=new JLabel[columnnames.length];
		for(int i=0;i<columnnames.length;i++){
			headers[i]=new JLabel();
			
		}
		
		//add lines
		linedatas=new JLabel[linenames.length][columnnames.length];
		for(int i=0;i<linenames.length;i++){
			for(int j=0;j<columnnames.length;j++){
				linedatas[i][j]=new JLabel();
				linedatas[i][j].setText(String.valueOf(datainfo[i][j]));
//				linedatas[i][j].setBounds(0, , width, height);
			}
		}
		

	}
}
