package VO;

public class PlayerVO {
	public String name;            //����
	public int uniformNum;      //���º���
	public String position;        //λ��
	public String height;          //��ߣ�Ӣ��-Ӣ�磩
	public double weight;          //���أ�����
	public String birth;           //���գ��� �գ��꣩
	public int age;                //����
	public int exp;             //����
	public String school;          //��ҵѧУ
	
	public boolean equals(PlayerVO pvo){
		if(!this.name.equals(pvo.name)){
			System.out.println("name");
			return false;
		}
		if(this.uniformNum!=pvo.uniformNum){
			System.out.println("uniformNum");
			return false;
		}
		if(!this.position.equals(pvo.position)){
			System.out.println("position");
			return false;
		}
		if(!this.height.equals(pvo.height)){
			System.out.println("height");
			return false;
		}
		if(this.weight!=pvo.weight){
			System.out.println("weight");
			return false;
		}
		if(!this.birth.equals(pvo.birth)){
			System.out.println("birth");
			return false;
		}
		if(this.age!=pvo.age){
			System.out.println("age");
			return false;
		}
		if(this.exp!=pvo.exp){
			System.out.println("exp");
			return false;
		}
		if(!this.school.equals(pvo.school)){
			System.out.println("school");
			return false;
		}
		return true;
	}
}
