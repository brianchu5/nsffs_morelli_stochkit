import numpy as np
import pylab as plt
import matplotlib
x,y,z =np.loadtxt('map1.txt',delimiter=' ',usecols=(0,1,2),unpack=True)

#minz = np.amin(z)

#maxz = np.amax(z)
cmap=matplotlib.cm.jet
plt.scatter(y, x, c=z,cmap=cmap, lw=0,vmin=0.000000000000000000000000000000000000001,vmax=1,norm=matplotlib.colors.LogNorm())


plt.xlim(-2,502)
plt.ylim(-120,120)
plt.ylabel(r'$\lambda$',size=30)
plt.xlabel('Time',size=20)
plt.title('mu_r = 100')
plt.colorbar()
plt.savefig('plot.png')
#plt.show()
