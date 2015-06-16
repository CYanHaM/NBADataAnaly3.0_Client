from bs4  import BeautifulSoup
from bs4 import  SoupStrainer
import urllib
import re
import Get10
doc =  urllib.urlopen("http://espn.go.com/nba/teams").read()
useful = SoupStrainer("div",class_="mod-container mod-table mod-no-header");
soup = BeautifulSoup(doc, "html.parser",parse_only=useful)
partition = "Atlantic"
prefix = "http://espn.go.com"

total = soup.find_all("div",class_=re.compile("^span-2"))
for  part in total:
	partition = part.find("h4").string
	#print partition
	temp = part.find("ul" ,class_="medium-logos")
	teamAll = temp.find_all("li")
	for team in teamAll:
		#get Roster
		name =  team.h5.string
		print name
		u4 = team.find("a",href=re.compile("^http://espn.go.com/nba/team/_/name/"))
		url4 = u4['href']
		postfix = url4.split("/name")[1]

		u1= team.find("a", text = "Roster")
		url1 = prefix+u1['href']
		#Get.getRoster(name, url1)

		#get Match
		u2 = team.find("a", text = "Schedule")
		url2 = prefix+u2['href']
		#if(cmp(name,"Boston Celtics")==0 or cmp(name,"Brooklyn Nets")==0 or cmp(name,"New York Knicks")==0):
		#	print "pass"
		#else:
		Get10.getMatch(name, postfix,url2)
