from bs4  import BeautifulSoup
from bs4 import  SoupStrainer
import urllib
import re
import info
doc = urllib.urlopen("http://stats.nba.com/teams/")
soup = BeautifulSoup(doc)
prefix = "http://stats.nba.com/stats/commonteamroster?LeagueID=00&Season="
mid = "&TeamID="
teamid=[]
teamid.append("1610612738")
teamid.append("1610612751")
teamid.append("1610612752")
teamid.append("1610612755")
teamid.append("1610612761")
teamid.append("1610612741")
teamid.append("1610612739")
teamid.append( "1610612765")
teamid.append( "1610612754")
teamid.append( "1610612749")
teamid.append("1610612737")
teamid.append("1610612766")
teamid.append("1610612748")
teamid.append("1610612753")
teamid.append("1610612764")
teamid.append("1610612743")
teamid.append( "1610612750")
teamid.append( "1610612760")
teamid.append( "1610612757")
teamid.append("1610612762")
teamid.append("1610612744")
teamid.append( "1610612746")
teamid.append( "1610612747")
teamid.append("1610612756")
teamid.append("1610612758")
teamid.append("1610612742")
teamid.append("1610612745")
teamid.append("1610612763")
teamid.append("1610612740")
teamid.append( "1610612759")

team=[]
team.append("Boston Celtics")
team.append("Brooklyn Nets")
team.append("New York Knicks")
team.append("Philadelphia Sixers")
team.append("Toronto Raptors")
team.append("Chicago Bulls")
team.append("Cleveland Cavaliers")
team.append("Detroit Pistons")
team.append("Indiana Pacers")
team.append("Milwaukee Bucks")
team.append("Atlanta Hawks")
team.append("Charlotte Hornets")
team.append("Miami Heat")
team.append("Orlando Magic")
team.append("Washington Wizards")
team.append("Denver Nuggets")
team.append("Minnesota Timberwolves")
team.append("Oklahoma City Thunder")
team.append("Portland Trail Blazers")
team.append("Utah Jazz")
team.append("Golden State Warriors")
team.append("Los Angeles Clippers")
team.append("Los Angeles Lakers")
team.append("Phoenix Suns")
team.append("Sacramento Kings")
team.append("Dallas Mavericks")
team.append("Houston Rockets")
team.append("Memphis Grizzlies")
team.append("New Orleans Pelicans")
team.append("San Antonio Spurs")

year = 2010
while(year<=2015):
	temp = '%d' %year
	tem1 = temp[2:]
	tem2 = '%d' %(year-1)
	season = tem2+"-"+tem1
	for i in range(0,30):
		url = prefix+season+mid+teamid[i]
		print url
		info.getInfo(url,team[i],season)
		i+=1
	year+=1