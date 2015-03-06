import matplotlib.pyplot as plt
import numpy as np
import scipy.interpolate
from matplotlib.colors import LogNorm


def plotpy(filename):
	myfile = filename
	time,fox,ror = np.loadtxt(filename,skiprows=2,usecols=(0,1,2),unpack=True)
	#plt.plot(time,fox-ror);	
	#plt.plot(fox[::100],ror[::100])
#	plt.hist2d(fox, ror, bins=40,norm=LogNorm())
#	plt.colorbar()

def plotkit(filename):
        myfile = filename
       #time,ror,fox = np.loadtxt(filename,skiprows=2,usecols=(0,1,2),unpack=True)
       	time,lam= np.loadtxt(filename,usecols=(0,1),unpack=True)	
#	n, bins, patches = plt.hist(fox-ror, 20, normed=1, facecolor='green', alpha=0.75)	
	plt.plot(time,lam);
	#plt.xlabel('ROR')
        #plt.ylabel('Probablity (%)')
#        plt.hist2d(ror,fox, bins=40,norm=LogNorm())
#        plt.colorbar()

#plotdata('foxror0.txt')
#plotdata('foxror1.txt')
#plotdata('foxror2.txt')
plotkit('lambda.txt')
#plotdata('foxror15.txt')
#plotdata('foxror20.txt')
#plotdata('foxror100.txt')
#plotdata('foxror75.txt')


plt.show()
