package bussinesslogic.teambl;

public class TeamLineItem {
	public String fullName;            //���ȫ��
	public String abbreviation;        //��д
	public String location;            //���ڵ�
	public String division;            //����
	public String partition;           //����
	public String homeCourt;           //����
	public String time;                //����ʱ��
	
	public boolean equals(TeamLineItem tli){
		if(!this.fullName.equals(tli.fullName)){
			System.out.println("fullName");
			return false;
		}
		if(!this.abbreviation.equals(tli.abbreviation)){
			System.out.println("abbreviation");
			return false;
		}
		if(!this.location.equals(tli.location)){
			System.out.println("location");
			return false;
		}
		if(!this.division.equals(tli.division)){
			System.out.println("division");
			return false;
		}
		if(!this.partition.equals(tli.partition)){
			System.out.println("partition");
			return false;
		}
		if(!this.homeCourt.equals(tli.homeCourt)){
			System.out.println("homeCourt");
			return false;
		}
		if(!this.time.equals(tli.time)){
			System.out.println("time");
			return false;
		}
		return true;
	}
}
