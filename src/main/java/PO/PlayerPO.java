package PO;
import java.io.Serializable;

public class PlayerPO implements Serializable{
	
	/**
	 * ��Ա������Ϣ
	 */
	public static final long serialVersionUID = 1L;
	public String name;            //����
	public int uniformNum;      //���º���
	public String position;        //λ��
	public String height;          //��ߣ�Ӣ��-Ӣ�磩
	public double weight;          //���أ�����
	public String birth;           //���գ��� �գ��꣩
	public int age;                //����
	public int exp;             //����
	public String school;          //��ҵѧУ
	public String team;
	public String season;
	
	public boolean equals(PlayerPO ppo){
		if(!this.name.equals(ppo.name)){
			System.out.println("name");
			return false;
		}
		if(this.uniformNum!=ppo.uniformNum){
			System.out.println("uniformNum");
			return false;
		}
		if(!this.position.equals(ppo.position)){
			System.out.println("position");
			return false;
		}
		if(!this.height.equals(ppo.height)){
			System.out.println("height");
			return false;
		}
		if(this.weight!=ppo.weight){
			System.out.println("weight");
			return false;
		}
		if(!this.birth.equals(ppo.birth)){
			System.out.println("birth");
			return false;
		}
		if(this.age!=ppo.age){
			System.out.println("age");
			return false;
		}
		if(this.exp!=ppo.exp){
			System.out.println("exp");
			return false;
		}
		if(!this.school.equals(ppo.school)){
			System.out.println("school");
			return false;
		}
		return true;
	}
}
