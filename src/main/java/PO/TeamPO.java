package PO;
import java.io.Serializable;

import VO.TeamVO;


public class TeamPO implements Serializable{
	/**
	 * ��ӻ�����Ϣ
	 */
	public static final long serialVersionUID = 1L;
	public String fullName;            //���ȫ��
	public String abbreviation;        //��д
	public String location;            //���ڵ�
	public String division;            //����
	public String partition;           //����
	public String homeCourt;           //����
	public String time;                //����ʱ��
	
	
	public boolean equals(TeamPO tpo){
		if(!this.fullName.equals(tpo.fullName)){
			System.out.println("fullName");
			return false;
		}
		if(!this.abbreviation.equals(tpo.abbreviation)){
			System.out.println("abbreviation");
			return false;
		}
		if(!this.location.equals(tpo.location)){
			System.out.println("location");
			return false;
		}
		if(!this.division.equals(tpo.division)){
			System.out.println("division");
			return false;
		}
		if(!this.partition.equals(tpo.partition)){
			System.out.println("partition");
			return false;
		}
		if(!this.homeCourt.equals(tpo.homeCourt)){
			System.out.println("homeCourt");
			return false;
		}
		if(!this.time.equals(tpo.time)){
			System.out.println("time");
			return false;
		}
		return true;
	}
}
