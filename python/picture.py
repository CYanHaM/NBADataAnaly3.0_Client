import urllib

def savePNG(playerid,name):
	pid = '%d' %playerid
	if(playerid is None or name is None):
		pass
	else:
		url = "http://stats.nba.com/media/players/230x185/"+pid+".png"
		filename = name+".png"
		urllib.urlretrieve(url, filename, callbackfunc)

def callbackfunc(blocknum, blocksize, totalsize):
    percent = 100.0 * blocknum * blocksize / totalsize
    if percent > 100:
        percent = 100
    print "%.2f%%"% percent
	
