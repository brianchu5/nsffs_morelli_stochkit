import numpy as np
import matplotlib 
import pylab as plt
data = np.loadtxt('map.txt', usecols=(0,1,2,3,4),unpack=True)


#a =  np.where((data[1,:]==100) & (data[0,:]>=30) & (data[2,:]!=0))

#b =  np.where((data[1,:]>100) & (data[1,:]<1200))

#flux = 0
#for i in np.nditer(a):
#flux = flux +  data[:,i][3]

#print flux

occB=[]

for i in range(0,510,10):
        time = np.where(data[1,:]==i)
        occupancysum = 0
        for j in np.nditer(time):
           if data[:,j][0] >= 10:
                occupancysum = occupancysum + data[:,j][4]
        occB.append(occupancysum)




timelist = [x for x in range(0,502,2)]
print occB


f = open('occB10.txt','a')
f.write('\n')
f.write(str(occB[-1])) # python will convert \n to os.linesep
f.close() # you can omit in most cases as the destructor will call if
#plt.subplot(2, 1, 1)
#plt.ylabel('Occupancy A')
#plt.xlabel('Time')
#plt.plot(timelist,occA)
#plt.subplot(2, 1, 2)
#plt.ylabel('Occupancy B')
#plt.xlabel('Time')
#plt.plot(timelist,occB)
#plt.subplot(3,1,2)
#plt.ylabel('Occupancy C')
#plt.xlabel('Time')
#plt.plot(timelist,occC)
#plt.show()


