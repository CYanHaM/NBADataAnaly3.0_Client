from bs4  import BeautifulSoup
from bs4 import  SoupStrainer
import urllib
import re
import MySQLdb

def schedule(team,url,tp,season,year):
	prefix = "http://espn.go.com"
	doc =  urllib.urlopen(url).read()
	useful = SoupStrainer("table",class_="tablehead");
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	matchAll = soup.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	for match in matchAll:
		td= match.find("td")
		date = td.text
		print date
		result = match.find("li",class_="score")
		if(result is None):
			pass
		else:
			res = result.find("a")
			link = prefix+res['href']
			box =  urllib.urlopen(link).read()
			boxu = SoupStrainer("section",id="article-feed");
			so = BeautifulSoup(box, "html.parser",parse_only=boxu)
			aside = so.find("aside",class_="game_package")
			if(aside is None):
				newLink = link
			else:
				score = aside.find("a",text="Box Score")
				newLink = score['href']
			getInfo(team,newLink,date,season,tp,year)

def getInfo(team,url,date,season,tp,year):
	doc =  urllib.urlopen(url).read()
	useful = SoupStrainer("div",class_="gamehq-wrapper");
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	score = soup.find("table",class_="linescore")
	if(score is None):
		pass
	else:
		trs = score.find_all("tr")
		result =[]
		for tr in trs:
			if(tr.get('class') is None):
				index=0;
				for stri in tr.strings:
					par = stri.parent
					if(index==5 and len(par.attrs)==2):
						#print 0
						#print 0
						#print 0
						result.append("0")
						result.append("0")
						result.append("0")
					if(index==6 and len(par.attrs)==2):
						#print 0
						#print 0
						result.append("0")
						result.append("0")
					if(index==7 and len(par.attrs)==2):
						#print 0
						result.append("0")	
					if(index==8 and len(par.attrs)!=2):
						pass
					#print(stri.encode("utf-8"))
					else:
						result.append(stri.encode("utf-8").strip())
					index+=1
			else:
				pass
		conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
		cursor = conn.cursor()
		conn.select_db('NBADataAnaly')
		result.append(date)
		result.append(season)
		result.append(tp)
		cursor.execute("""replace into score values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",result)
		conn.commit()
		cursor.close()
		#-----------------------------------------------------------------------------
		players = soup.find("table",class_="mod-data")
		if(players is None):
			pass
		else:
			child = players.children
			values2=[]
			for ch in child:
				if(cmp(ch.name,"thead")==0):
					n = ch.find("tr",class_="team-color-strip")
					if(n is not None):
						na = n.find("th")
						if(na is not None):
							team = na.text
				if(cmp(ch.name,"tbody")==0):
					staAll = ch.find_all("tr",class_=re.compile("player-"))
					for sta in staAll:
						flag = 0
						result2=[]
						for s in sta.strings:
							if(re.search("DNP" , s)):
								#print "0*14"
								for i in range(0,14):
									result2.append("0")
							else:
								#print(s.encode("utf-8"))
								if(cmp(s,"\n")==0):
									pass
								else:
									if(flag==0 and s.find(",")!=-1):
										result2.append(s.encode("utf-8"))
										result2.append(" ")
										flag+=1
									if(flag==14 and year<2009):
										result2.append("0")
										result2.append(s.encode("utf-8"))
										flag+=1
									else:
										result2.append(s.encode("utf-8"))
										flag+=1
						result2.append(date)
						result2.append(season)
						result2.append(team)
						result2.append(tp)
						print len(result2)
						print result2
						values2.append(result2)
			conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
			cursor = conn.cursor()
			conn.select_db('NBADataAnaly')
			cursor.executemany("""replace into match_detail values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values2)
			conn.commit()
			cursor.close()