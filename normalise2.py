import numpy as np
from scipy.interpolate import griddata
import pylab as plt
import matplotlib
x,y,z =np.loadtxt('map1.txt',delimiter=' ',usecols=(0,1,2),unpack=True)
from scipy import interpolate

xi, yi = np.linspace(x.min(), x.max(), 20), np.linspace(y.min(), y.max(), 20)
xi, yi = np.meshgrid(xi, yi)


zi = interpolate.RectBivariateSpline(x, y, z)

plt.imshow(zi, vmin=z.min(), vmax=z.max(), origin='lower',
           extent=[x.min(), x.max(), y.min(), y.max()])
plt.scatter(x, y, c=z)
plt.colorbar()
plt.show()
