from bs4  import BeautifulSoup
from bs4 import  SoupStrainer
import urllib
import re
import MySQLdb
import  Stats

def  getRoster(team,url):
	rosDoc = urllib.urlopen(url).read()
	useful = SoupStrainer("table",class_="tablehead");
	soup = BeautifulSoup(rosDoc, "html.parser",parse_only=useful)
	playerAll = soup.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values=[]
	for player in playerAll:
		result = []
		for str in player.strings:
			result.append(str.encode('utf-8'))
			#print(repr(str))
		#print result
		result.append(team)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player values(%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()

def getMatch(team,postfix,url):
	#season : 2002-2003  2014-2015
	#if has PostSeason : postseason(default),regular,preseason
	#if not preseason,regular(default)
	year = 2012
	name = postfix.split("/")
	
	while(year<=2012):
		tem1 = '%d' %year
		tem2 = '%d' %(year-1)
		season = tem2+"-"+tem1
		print season
		url = "http://espn.go.com/nba/team/schedule/_/name/"+name[1]+"/year/"+tem1+"/"+name[2]
		doc =  urllib.urlopen(url).read()
		#judge
		useful = SoupStrainer("div",id="my-teams-table");
		soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
		#if has Postseason
		temp1 = soup.find("div",class_="mod-page-tabs mod-thirdnav-tabs")
		link1 =  temp1.find("a",text="Postseason")
		if  link1 is None:
			tp = "Regular"
			print tp
			Stats.schedule(team,url,tp,season,year)
		else:
			print "Postseason"
		#	Stats.schedule(team,url,"Postseason",season,year)
			link2 = temp1.find("a",text="Regular")
			regular = link2['href']
			tp = "Regular"
			print tp
			Stats.schedule(team,regular,tp,season,year)
			#get Postseason stats
		year+=1