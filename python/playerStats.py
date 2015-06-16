from bs4  import BeautifulSoup
from bs4 import  SoupStrainer
import urllib
import player

#season 1999-00 2014-15
prefix = "http://espn.go.com/nba/statistics/player/_/stat/"
year = 2000
while(year<=2015):
	tem1 = '%d' %year
	tem2 = '%d' %(year-1)
	season = tem2+"-"+tem1
	print season
	postfix = "/seasontype/2/qualified/false"

	"""sc = "scoring/sort/points/year/"
	reg1 = prefix+sc+tem1+"/seasontype/2"
	post1 =  prefix+sc+tem1
	print "Regular"
	player.getScoring(reg1,season,"Regular")
	print "PostSeason"
	player.getScoring(post1,season,"PostSeason")

	
	reg2 = prefix +"rebounds/sort/avgRebounds/year/"+tem1+postfix
	post2 = prefix+"rebounds/year/"+tem1
	player.getRebounds(reg2,season,"Regular")
	player.getRebounds(post2,season,"PostSeason")
	
	reg3 = prefix+"field-goals/year/"+tem1+postfix
	post3 = prefix+"field-goals/sort/fieldGoalPct/year/"+tem1+"/qualified/false"
	player.getFieldGoals(reg3,season,"Regular")
	player.getFieldGoals(post3,season,"PostSeason")
	
	reg4 = prefix+"free-throws/sort/freeThrowPct/year/"+tem1+postfix
	post4 = prefix+"free-throws/sort/freeThrowPct/year/"+tem1+"/qualified/false"
	player.getFreeThrows(reg4,season,"Regular")
	player.getFreeThrows(post4,season,"PostSeason")
	"""
	reg5 = prefix+"3-points/sort/threePointFieldGoalPct/year/"+tem1+postfix
	post5 = prefix+"3-points/sort/threePointFieldGoalPct/year/"+tem1+"/qualified/false"
	player.get3points(reg5,season,"Regular")
	player.get3points(post5,season,"PostSeason")
	"""
	reg6 = prefix+"assists/sort/avgAssists/year/"+tem1+postfix
	post6 =prefix+"assists/sort/avgAssists/year/"+tem1+"/qualified/false" 
	player.getAssists(reg6,season,"Regular")
	player.getAssists(post6,season,"PostSeason")
	
	reg7 = prefix+"steals/year/"+tem1+postfix
	post7 = prefix+"steals/sort/avgSteals/year"+tem1+"/qualified/false"
	player.getSteals(reg7,season,"Regular")
	player.getSteals(post7,season,"PostSeason")
	
	reg8 = prefix+"blocks/sort/avgBlocks/year"+tem1+postfix
	post8 = prefix+"blocks/year/"+tem1+"/qualified/false"
	player.getBlocks(reg8,season,"Regular")
	player.getBlocks(post8,season,"PostSeason")
	
	
	reg9 = prefix+"fouls/year/"+tem1+postfix
	post9 = prefix+"fouls/sort/avgFouls/year/"+tem1+"/qualified/false"
	player.getFouls(reg9,season,"Regular")
	player.getFouls(post9,season,"PostSeason")

	reg10 = prefix+"minutes/sort/avgMinutes/year/"+tem1+postfix
	post10 = prefix+"minutes/year/"+tem1+"/qualified/false"
	player.getMinutes(reg10,season,"Regular")
	player.getMinutes(post10,season,"PostSeason")

	
	reg11 = prefix+"turnovers/year/"+tem1+postfix
	post11 =  prefix+"turnovers/sort/avgTurnovers/year"+tem1+"/qualified/false"
	player.getTurnovers(reg11,season,'Regular')
	player.getTurnovers(post11,season,"PostSeason")
	
	reg12 = prefix+"double-doubles/sort/doubleDouble/year/"+tem1+postfix
	post12 = prefix+"double-doubles/year/"+tem1+"/qualified/false"
	player.getDoubleDoubles(reg12,season,"Regular")
	player.getDoubleDoubles(post12,season,"PostSeason")
	"""
	year+=1