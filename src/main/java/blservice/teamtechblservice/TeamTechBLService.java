package blservice.teamtechblservice;

import java.util.ArrayList;

import TypeEnum.SortEnum;
import TypeEnum.TeamTechEnum;
import VO.TeamTechVO;

public interface TeamTechBLService {
    public ArrayList<TeamTechVO> Ascend(TeamTechEnum DataType,String season);
	/* �������������
	 * �������������
	 * ����
     * */
    
    public ArrayList<TeamTechVO> Descend(TeamTechEnum DataType,String season);
	/* �������������
	 * �������������
	 * ����
     * */
    
    public ArrayList<TeamTechVO> refresh(SortEnum sort, TeamTechEnum DataType,String season);
    /* ������������ͣ��Լ�������ǽ���
     * */
    
    public ArrayList<TeamTechVO> findSeasonHotTeam(TeamTechEnum DataType,String season);
    /* ����ɸѡ����
     * */
    
    public void init();
}
