from scipy import interpolate
import numpy as np

def my_interp(X, Y, Z, x, y, spn=3):
    xs,ys = map(np.array,(x,y))
    z = np.zeros(xs.shape)
    for i,(x,y) in enumerate(zip(xs,ys)):
        # get the indices of the nearest x,y
        xi = np.argmin(np.abs(X[0,:]-x))
        yi = np.argmin(np.abs(Y[:,0]-y))
        xlo = max(xi-spn, 0)
        ylo = max(yi-spn, 0)
        xhi = min(xi+spn, X[0,:].size)
        yhi = min(yi+spn, Y[:,0].size)
        # make slices of X,Y,Z that are only a few items wide
        nX = X[xlo:xhi, ylo:yhi]
        nY = Y[xlo:xhi, ylo:yhi]
        nZ = Z[xlo:xhi, ylo:yhi]
        intp = interpolate.interp2d(nX, nY, nZ)
        z[i] = intp(x,y)[0]
    return z

N = 1000
X,Y = np.meshgrid(np.arange(N), np.arange(N))
Z = np.random.random((N, N))

print my_interp(X, Y, Z, [13.2, 999.9], [0.01, 45.3])
