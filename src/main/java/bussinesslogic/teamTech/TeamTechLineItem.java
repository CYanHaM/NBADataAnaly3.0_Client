package bussinesslogic.teamTech;

public class TeamTechLineItem {
	
	public int ifReagular;                         //是否是常规赛
	
	public String name;                            //�������
	public String season;                          //����
	public int gameNum;                         //��������
	public double shotInRate;                      //Ͷ��������
	public double threeShotInRate;                 //����������
	public double penaltyShotInRate;               //����������
	public double winningRate;                     //ʤ��
	public double offensiveEfficiency;               //����Ч��
	public double defensiveEfficiency;               //����Ч��
	public double reboundEfficiency;                 //����Ч��
	public double stealEfficiency;                   //����Ч��
	public double secondaryAttackEfficiency;         //����Ч��
	public int winningNum;                      //ʤ����
	
	//������������
	
	public int shotInNum;                       //Ͷ��������
	public int shotNum;                         //Ͷ��������
	public int threeShotInNum;                  //����������
	public int threeShotNum;                    //���ֳ�����
	public int penaltyShotInNum;                //����������
	public int penaltyShotNum;                  //���������
	public int offensiveRebound;                //ǰ��������
	public int defensiveRebound;                //��������
	public int rebound;                         //��������
	public int secondaryAttack;                 //������
	public int steal;                           //������
	public int blockShot;                       //��ñ��
	public int fault;                           //ʧ����
	public int foul;                            //������
	public int score;                           //�����÷�
	public double offensiveRound;                  //�����غ�
	
	//�����ǳ�������
	
	public double shotInNumave;                       //Ͷ��������
	public double shotNumave;                         //Ͷ��������
	public double threeShotInNumave;                  //����������
	public double threeShotNumave;                    //���ֳ�����
	public double penaltyShotInNumave;                //����������
	public double penaltyShotNumave;                  //���������
	public double offensiveReboundave;                //ǰ��������
	public double defensiveReboundave;                //��������
	public double reboundave;                         //��������
	public double secondaryAttackave;                 //������
	public double stealave;                           //������
	public double blockShotave;                       //��ñ��
	public double faultave;                           //ʧ����
	public double foulave;                            //������
	public double scoreave;                           //�����÷�
	public double offensiveRoundave;                  //�����غ�
	
	public boolean equals(TeamTechLineItem ttli){
		if(!this.name.equals(ttli.name)){
			System.out.println("name");
			return false;
		}
	    if(!this.season.equals(ttli.season)){
	    	System.out.println("season");
	    	return false;
	    }
	    if(this.gameNum!=ttli.gameNum){
	    	System.out.println("gameNum");
	    	return false;
	    }
	    if(this.shotInRate!=ttli.shotInRate){
	    	System.out.println("shotInRate");
	    	return false;
	    }
	    if(this.threeShotInRate!=ttli.threeShotInRate){
	    	System.out.println("threeShotInRate");
	    	return false;
	    }
	    if(this.penaltyShotInRate!=ttli.penaltyShotInRate){
	    	System.out.println("penaltyShotInRate");
	    	return false;
	    }
	    if(this.winningRate!=ttli.winningRate){
	    	System.out.println("winningRate");
	    	return false;
	    }
	    if(this.offensiveEfficiency!=ttli.offensiveEfficiency){
	    	System.out.println("offensiveEfficiency");
	    	return false;
	    }
	    if(this.defensiveEfficiency!=ttli.defensiveEfficiency){
	    	System.out.println("defensiveEfficiency");
	    	return false;
	    }
	    if(this.reboundEfficiency!=ttli.reboundEfficiency){
	    	System.out.println("reboundEfficiency");
	    	return false;
	    }
	    if(this.stealEfficiency!=ttli.stealEfficiency){
	    	System.out.println("stealEfficiency");
	    	return false;
	    }
	    if(this.secondaryAttackEfficiency!=ttli.secondaryAttackEfficiency){
	    	System.out.println("secondaryAttackEfficiency");
	    	return false;
	    }
	    if(this.winningNum!=ttli.winningNum){
	    	System.out.println("winningNum");
	    	return false;
	    }
	    if(this.shotInNum!=ttli.shotInNum){
	    	System.out.println("shotInNum");
	    	return false;
	    }
	    if(this.shotNum!=ttli.shotNum){
	    	System.out.println("shotNum");
	    	return false;
	    }
	    if(this.threeShotInNum!=ttli.threeShotInNum){
	    	System.out.println("threeShotInNum");
	    	return false;
	    }
	    if(this.threeShotNum!=ttli.threeShotNum){
	    	System.out.println("threeShotNum");
	    	return false;
	    }
	    if(this.penaltyShotInNum!=ttli.penaltyShotInNum){
	    	System.out.println("penaltyShotInNum");
	    	return false;
	    }
	    if(this.penaltyShotNum!=ttli.penaltyShotNum){
	    	System.out.println("penaltyShotNum");
	    	return false;
	    }
	    if(this.offensiveRebound!=ttli.offensiveRebound){
	    	System.out.println("offensiveRebound");
	    	return false;
	    }
	    if(this.defensiveRebound!=ttli.defensiveRebound){
	    	System.out.println("defensiveRebound");
	    	return false;
	    }
	    if(this.rebound!=ttli.rebound){
	    	System.out.println("rebound");
	    	return false;
	    }
	    if(this.secondaryAttack!=ttli.secondaryAttack){
	    	System.out.println("TeamTech");
	    	System.out.println("secondaryAttack");
	    	return false;
	    }
	    if(this.steal!=ttli.steal){
	    	System.out.println("steal");
	    	return false;
	    }
	    if(this.blockShot!=ttli.blockShot){
	    	System.out.println("blockShot");
	    	return false;
	    }
	    if(this.fault!=ttli.fault){
	    	System.out.println("fault");
	    	return false;
	    }
	    if(this.foul!=ttli.foul){
	    	System.out.println("foul");
	    	return false;
	    }
	    if(this.score!=ttli.score){
	    	System.out.println("score");
	    	return false;
	    }
	    if(this.offensiveRound!=ttli.offensiveRound){
	    	System.out.println("offensiveRound");
	    	return false;
	    }
	    if(this.shotInNumave!=ttli.shotInNumave){
	    	System.out.println("shotInNumave");
	    	return false;
	    }
	    if(this.shotNumave!=ttli.shotNumave){
	    	System.out.println("shotNumave");
	    	return false;
	    }
	    if(this.threeShotInNumave!=ttli.threeShotInNumave){
	    	System.out.println("threeShotInNumave");
	    	return false;
	    }
	    if(this.threeShotNumave!=ttli.threeShotNumave){
	    	System.out.println("threeShotNumave");
	    	return false;
	    }
	    if(this.penaltyShotInNumave!=ttli.penaltyShotInNumave){
	    	System.out.println("penaltyShotInNumave");
	    	return false;
	    }
	    if(this.penaltyShotNumave!=ttli.penaltyShotNumave){
	    	System.out.println("penaltyShotNumave");
	    	return false;
	    }
	    if(this.offensiveReboundave!=ttli.offensiveReboundave){
	    	System.out.println("offensiveReboundave");
	    	return false;
	    }
	    if(this.defensiveReboundave!=ttli.defensiveReboundave){
	    	System.out.println("defensiveReboundave");
	    	return false;
	    }
	    if(this.reboundave!=ttli.reboundave){
	    	System.out.println("reboundave");
	    	return false;
	    }
	    if(this.secondaryAttackave!=ttli.secondaryAttackave){
	    	System.out.println("secondaryAttackave");
	    	return false;
	    }
	    if(this.stealave!=ttli.stealave){
	    	System.out.println("stealave");
	    	return false;
	    }
	    if(this.blockShotave!=ttli.blockShotave){
	    	System.out.println("blockShotave");
	    	return false;
	    }
	    if(this.faultave!=ttli.faultave){
	    	System.out.println("faultave");
	    	return false;
	    }
	    if(this.foulave!=ttli.foulave){
	    	System.out.println("foulave");
	    	return false;
	    }
	    if(this.scoreave!=ttli.scoreave){
	    	System.out.println("scoreave");
	    	return false;
	    }
	    if(this.offensiveRoundave!=ttli.offensiveRoundave){
	    	System.out.println("offensiveRoundave");
	    	return false;
	    }
	    return true;
	}
}
