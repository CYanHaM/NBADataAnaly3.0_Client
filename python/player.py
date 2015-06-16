from bs4  import BeautifulSoup
from bs4 import  SoupStrainer
import urllib
import re
import MySQLdb

def getScoring(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		result = []
		tds = tr.find_all("td")
		index = 0
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_scoring values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getScoring(link,season,tp)
def getRebounds(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		result = []
		tds = tr.find_all("td")
		index = 0
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_rebounds values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		getRebounds(link,season,tp)

def getFieldGoals(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_fieldgoals values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getFieldGoals(link,season,tp)

def getFreeThrows(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_freethrows values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getFreeThrows(link,season,tp)

def get3points(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_3points values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		get3points(link,season,tp)

def getAssists(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_assists values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getAssists(link,season,tp)

def getSteals(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_steals values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getSteals(link,season,tp)

def getBlocks(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values= []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_blocks values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getBlocks(link,season,tp)

def getFouls(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values=[]
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result =[]
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_fouls values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getFouls(link,season,tp)

def getMinutes(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_minutes values(%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getMinutes(link,season,tp)

def getTurnovers(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_turnovers values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getTurnovers(link,season,tp)

def getDoubleDoubles(url,season,tp):
	doc = urllib.urlopen(url).read()
	useful = SoupStrainer("div",id="my-players-table")
	soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
	div = soup.find("table",class_="tablehead")
	trs = div.find_all("tr",class_=re.compile("^oddrow|^evenrow"))
	values = []
	for tr in trs:
		tds = tr.find_all("td")
		index = 0
		result = []
		for td in tds:
			for s in td.strings:
				if(index==0):
					pass
				else:
					#print s
					result.append(s)
			index+=1
		result.append(season)
		result.append(tp)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany("""replace into player_doubles values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",values)
	conn.commit()
	cursor.close()
	#find page down
	next = soup.find("div",class_="controls")
	child = next.find("div",class_="jcarousel-next")
	if(len(child['class'])==2):
		pass
	else:
		link = child.parent['href']
		print "next page"
		getDoubleDoubles(link,season,tp)