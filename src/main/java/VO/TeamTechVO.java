package VO;


public class TeamTechVO {
	
	public int ifReagular;                         //是否是常规赛
	
	public String name;                            //������
	public String season;                          //��
	public int gameNum;                         //������
	public double shotInRate;                      //Ͷ��������
	public double threeShotInRate;                 //���������
	public double penaltyShotInRate;               //����������
	public double winningRate;                     //ʤ��
	public double offensiveEfficiency;               //��Ч��
	public double defensiveEfficiency;               //����Ч��
	public double reboundEfficiency;                 //����Ч��
	public double stealEfficiency;                   //����Ч��
	public double secondaryAttackEfficiency;         //��Ч��
	public int winningNum;                      //ʤ����
	
	//�����������
	
	public int shotInNum;                       //Ͷ��������
	public int shotNum;                         //Ͷ��������
	public int threeShotInNum;                  //���������
	public int threeShotNum;                    //��ֳ�����
	public int penaltyShotInNum;                //����������
	public int penaltyShotNum;                  //���������
	public int offensiveRebound;                //ǰ��������
	public int defensiveRebound;                //��������
	public int rebound;                         //��������
	public int secondaryAttack;                 //����
	public int steal;                           //������
	public int blockShot;                       //��ñ��
	public int fault;                           //ʧ����
	public int foul;                            //������
	public int score;                           //����÷�
	public double offensiveRound;                  //��غ�
	
	//�����ǳ������
	
	public double shotInNumave;                       //Ͷ��������
	public double shotNumave;                         //Ͷ��������
	public double threeShotInNumave;                  //���������
	public double threeShotNumave;                    //��ֳ�����
	public double penaltyShotInNumave;                //����������
	public double penaltyShotNumave;                  //���������
	public double offensiveReboundave;                //ǰ��������
	public double defensiveReboundave;                //��������
	public double reboundave;                         //��������
	public double secondaryAttackave;                 //����
	public double stealave;                           //������
	public double blockShotave;                       //��ñ��
	public double faultave;                           //ʧ����
	public double foulave;                            //������
	public double scoreave;                           //����÷�
	public double offensiveRoundave;                  //��غ�
	
	public boolean equals(TeamTechVO ttvo){
		if(!this.name.equals(ttvo.name)){
			System.out.println("name");
			return false;
		}
	    if(!this.season.equals(ttvo.season)){
	    	System.out.println("season");
	    	return false;
	    }
	    if(this.gameNum!=ttvo.gameNum){
	    	System.out.println("gameNum");
	    	return false;
	    }
	    if(this.shotInRate!=ttvo.shotInRate){
	    	System.out.println("shotInRate");
	    	return false;
	    }
	    if(this.threeShotInRate!=ttvo.threeShotInRate){
	    	System.out.println("threeShotInRate");
	    	return false;
	    }
	    if(this.penaltyShotInRate!=ttvo.penaltyShotInRate){
	    	System.out.println("penaltyShotInRate");
	    	return false;
	    }
	    if(this.winningRate!=ttvo.winningRate){
	    	System.out.println("winningRate");
	    	return false;
	    }
	    if(this.offensiveEfficiency!=ttvo.offensiveEfficiency){
	    	System.out.println("offensiveEfficiency");
	    	return false;
	    }
	    if(this.defensiveEfficiency!=ttvo.defensiveEfficiency){
	    	System.out.println("defensiveEfficiency");
	    	return false;
	    }
	    if(this.reboundEfficiency!=ttvo.reboundEfficiency){
	    	System.out.println("reboundEfficiency");
	    	return false;
	    }
	    if(this.stealEfficiency!=ttvo.stealEfficiency){
	    	System.out.println("stealEfficiency");
	    	return false;
	    }
	    if(this.secondaryAttackEfficiency!=ttvo.secondaryAttackEfficiency){
	    	System.out.println("secondaryAttackEfficiency");
	    	return false;
	    }
	    if(this.winningNum!=ttvo.winningNum){
	    	System.out.println("winningNum");
	    	return false;
	    }
	    if(this.shotInNum!=ttvo.shotInNum){
	    	System.out.println("shotInNum");
	    	return false;
	    }
	    if(this.shotNum!=ttvo.shotNum){
	    	System.out.println("shotNum");
	    	return false;
	    }
	    if(this.threeShotInNum!=ttvo.threeShotInNum){
	    	System.out.println("threeShotInNum");
	    	return false;
	    }
	    if(this.threeShotNum!=ttvo.threeShotNum){
	    	System.out.println("threeShotNum");
	    	return false;
	    }
	    if(this.penaltyShotInNum!=ttvo.penaltyShotInNum){
	    	System.out.println("penaltyShotInNum");
	    	return false;
	    }
	    if(this.penaltyShotNum!=ttvo.penaltyShotNum){
	    	System.out.println("penaltyShotNum");
	    	return false;
	    }
	    if(this.offensiveRebound!=ttvo.offensiveRebound){
	    	System.out.println("offensiveRebound");
	    	return false;
	    }
	    if(this.defensiveRebound!=ttvo.defensiveRebound){
	    	System.out.println("defensiveRebound");
	    	return false;
	    }
	    if(this.rebound!=ttvo.rebound){
	    	System.out.println("rebound");
	    	return false;
	    }
	    if(this.secondaryAttack!=ttvo.secondaryAttack){
	    	System.out.println("TeamTech");
	    	System.out.println("secondaryAttack");
	    	return false;
	    }
	    if(this.steal!=ttvo.steal){
	    	System.out.println("steal");
	    	return false;
	    }
	    if(this.blockShot!=ttvo.blockShot){
	    	System.out.println("blockShot");
	    	return false;
	    }
	    if(this.fault!=ttvo.fault){
	    	System.out.println("fault");
	    	return false;
	    }
	    if(this.foul!=ttvo.foul){
	    	System.out.println("foul");
	    	return false;
	    }
	    if(this.score!=ttvo.score){
	    	System.out.println("score");
	    	return false;
	    }
	    if(this.offensiveRound!=ttvo.offensiveRound){
	    	System.out.println("offensiveRound");
	    	return false;
	    }
	    if(this.shotInNumave!=ttvo.shotInNumave){
	    	System.out.println("shotInNumave");
	    	return false;
	    }
	    if(this.shotNumave!=ttvo.shotNumave){
	    	System.out.println("shotNumave");
	    	return false;
	    }
	    if(this.threeShotInNumave!=ttvo.threeShotInNumave){
	    	System.out.println("threeShotInNumave");
	    	return false;
	    }
	    if(this.threeShotNumave!=ttvo.threeShotNumave){
	    	System.out.println("threeShotNumave");
	    	return false;
	    }
	    if(this.penaltyShotInNumave!=ttvo.penaltyShotInNumave){
	    	System.out.println("penaltyShotInNumave");
	    	return false;
	    }
	    if(this.penaltyShotNumave!=ttvo.penaltyShotNumave){
	    	System.out.println("penaltyShotNumave");
	    	return false;
	    }
	    if(this.offensiveReboundave!=ttvo.offensiveReboundave){
	    	System.out.println("offensiveReboundave");
	    	return false;
	    }
	    if(this.defensiveReboundave!=ttvo.defensiveReboundave){
	    	System.out.println("defensiveReboundave");
	    	return false;
	    }
	    if(this.reboundave!=ttvo.reboundave){
	    	System.out.println("reboundave");
	    	return false;
	    }
	    if(this.secondaryAttackave!=ttvo.secondaryAttackave){
	    	System.out.println("secondaryAttackave");
	    	return false;
	    }
	    if(this.stealave!=ttvo.stealave){
	    	System.out.println("stealave");
	    	return false;
	    }
	    if(this.blockShotave!=ttvo.blockShotave){
	    	System.out.println("blockShotave");
	    	return false;
	    }
	    if(this.faultave!=ttvo.faultave){
	    	System.out.println("faultave");
	    	return false;
	    }
	    if(this.foulave!=ttvo.foulave){
	    	System.out.println("foulave");
	    	return false;
	    }
	    if(this.scoreave!=ttvo.scoreave){
	    	System.out.println("scoreave");
	    	return false;
	    }
	    if(this.offensiveRoundave!=ttvo.offensiveRoundave){
	    	System.out.println("offensiveRoundave");
	    	return false;
	    }
	    return true;
	}
}
