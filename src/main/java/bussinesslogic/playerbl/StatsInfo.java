package bussinesslogic.playerbl;

import java.util.ArrayList;

import PO.MatchPO;
import PO.PlayerStatsPO;
import PO.PlayerTechMPO;
import PO.PlayerTechPO;
import PO.TeamStatsPO;
import PO.TeamTechPO;

public interface StatsInfo {
	//������
	public TeamTechPO getTeamTech(String teamname,String season,int ifRegular);
	//������
	public PlayerTechPO getPlayerTech(String player,String season,int ifRegular);
	//���20������
	public ArrayList<MatchPO> getRecentMatch(String team,String season);
	//��Ա���20��
	public ArrayList<PlayerTechMPO> getRecentPlayerM(String player,String season);
	//��ӵ����г�����
	public ArrayList<TeamTechPO>getMatchForYear(String team);
	//������
	public ArrayList<PlayerTechPO>getPlayerForYear(String name);
	//������ �÷������������ϸ�ñ����ʧ��Ͷ�������������������������������
	public TeamTechPO getTeamRank(String teamname,String season,int ifRegular);
	//������ �������Ե�30��ֵ
	public TeamTechPO getAllTeamAverage(String season,int ifRegular);
	//������ ��Ա�������� �÷������������ϸ�ñ����ʧ��Ͷ����������������������������ϳ�ʱ��
	public PlayerTechPO getPlayerRank(String player,String season,int ifRegular);
	//��ֵ
	public PlayerTechPO getAllPlayerAverage(String season,int ifRegular);
	//WEСд
	public TeamTechPO getDivTeamAverage(String season,int ifRegular,String division);
	public PlayerTechPO getDivPlayerAverage(String season,int ifRegular,String division);
	//�μӹ��ĳ������ͼ�����
	public String[] getTeamSeasonList(String team);
	public String[] getPlayerSeasonList(String player);



}
