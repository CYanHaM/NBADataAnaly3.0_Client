package presentation.playerui;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import PO.PlayerTechPO;
import VO.PlayerTechVO;
import VO.PlayerVO;
import VO.ScreeningConditionVO;
import blservice.playerinfoblservice.PlayerInfoService;
import blservice.playertechblservice.FindPlayerTechService;
import blservice.playertechblservice.ShowPlayerTechService;
import bussinesslogic.PlayerInfo.PlayerInfoBL;
import bussinesslogic.playertechbl.ShowPlayerTech;
import bussinesslogic.playertechbl.findPlayerTech;
import data.playertech.Show;
import dataservice.playertechdataservice.ShowDataService;

public class ImportPlayer {
	/**
	 * 界面层通过BL层的接口导入并处理数据
	 * @author blisscry
	 * @date 2015年3月31日01:35:32
	 * @version 1.0
	 */
	//定义层间传输接口
	PlayerInfoService pis;
	FindPlayerTechService fpt;
	ShowPlayerTechService spt;
	ShowDataService sds;

	public ImportPlayer(){
		pis = new PlayerInfoBL();
		fpt = new findPlayerTech();
		spt = new ShowPlayerTech();
		sds = new Show();
	}

	public ArrayList<String> getPlayerSeasonList(){
		ArrayList<String> seasonlist=new ArrayList<String>();
		
//		ArrayList<String> seasonlist=new ArrayList<String>();
//		seasonlist.add("2011-12 Regular");
//		seasonlist.add("2011-12 Postseason");
//		seasonlist.add("2012-13 Regular");
//		seasonlist.add("2012-13 Postseason");
//		seasonlist.add("2013-14 Regular");
//		seasonlist.add("2013-14 Postseason");
//		seasonlist.add("2015-16 Regular");
//		seasonlist.add("2015-16 Postseason");
//		seasonlist.add("2016-17 Regular");
//		seasonlist.add("2016-17 Postseason");
		
		ArrayList<PlayerTechVO> playertechlist=spt.ascend("season", "all");
		for(int i=0;i<playertechlist.size();i++){
//			System.out.println(playertechlist.get(i).season);
			seasonlist.add(playertechlist.get(i).season);
		}
		
		ArrayList<String> resultlist=new ArrayList<String>();
		java.util.Iterator<String> it=seasonlist.iterator();
//		for(int i=0;i<seasonlist.size();i++){
//			for(int j=0;j<resultlist.size();j++){
//				if(!seasonlist.get(i).equals(resultlist.get(j))){
//					resultlist.add(seasonlist.get(i));
////					System.out.println("--"+resultlist.get(i));
//				}
//			}
//		}
		while(it.hasNext()){
			String temp=it.next();
			if(!resultlist.contains(temp)){
				resultlist.add(temp);
			}
		}
		
		for(int i=0;i<resultlist.size();i++){
			System.out.println("---"+resultlist.get(i));
		}
//		
		
		return resultlist;
	}
	
	public ArrayList<PlayerTechVO> getPlayerTechAscend(String DataType,String season){
		ArrayList<PlayerTechVO> playerlist=spt.ascend(DataType,season);
//		for(int i=0;i<playerlist.size();i++){
//			System.out.println(playerlist.get(i).name);
//		}
//		System.out.println(DataType+"　"+season);
//		System.out.println(playerlist.size());
		return playerlist;
	}

	public ArrayList<PlayerTechVO> getPlayerTechDescend(String DataType,String season){
		return spt.descend(DataType,season);
	}
	
	public ArrayList<PlayerTechVO> sift(ArrayList<ScreeningConditionVO> scvo,String season){
		return fpt.sift(scvo,season);
	}
	
	public PlayerTechVO findPlayerTechByName(String name,String season){
		return fpt.findPlayerTechByName(name,season);
	}
	
	public ArrayList<PlayerTechVO> findPlayerByLetter(char letter,String season){
		return fpt.findPlayerByLetter(letter,season);
	}

	public ArrayList<PlayerVO> findByTeam(String tvo,int retire){
		return pis.findByTeam(tvo,retire);
	}
	
	public PlayerVO showPlayerInfo (String name,int retire){
		return pis.showPlayerInfo(name,retire);
	}

}
