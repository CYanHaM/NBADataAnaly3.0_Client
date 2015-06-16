import urllib
import json
import MySQLdb
import picture

def getInfo(url,team,season):
	print team
	print season
	doc = urllib.urlopen(url).read()
	s = json.loads(doc)
	row = s['resultSets'][0]['rowSet']
	lenth = len(row)
	values = []
	for i in range(0,lenth):
		result = []
		infor = row[i]
		result.append(infor[12])
		picture.savePNG(infor[12],infor[3])
		"""for j in range(3,12):
			result.append(infor[j])
		result.append(team)
		result.append(season)
		values.append(result)
	conn = MySQLdb.connect(host='localhost', user='root', passwd='cyanham')
	cursor = conn.cursor()
	conn.select_db('NBADataAnaly')
	cursor.executemany(replace into playerInfo values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s),values)
	conn.commit()
	cursor.close()"""