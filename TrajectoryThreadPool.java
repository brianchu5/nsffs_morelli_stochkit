import java.util.*;
import java.util.concurrent.*;
import java.io.*;



public class TrajectoryThreadPool {

private ExecutorService executor;
private Trajectory root;
private String dataRAon;
private String dataRBon;
private String dataRAoff;
private String dataRBoff;



private parNSFFS nsffs;

private    double RStart;
private    int RBegin;
private    int  timeinterval;
private    int stoptime;

private availableslots availableslots;

public TrajectoryThreadPool(){
this.RStart = 2000.0;
this.RBegin = 100;
this.timeinterval = 10;
this.stoptime = 500 ;
this.availableslots = new availableslots();
this.nsffs = new parNSFFS(timeinterval,stoptime,RStart,RBegin,availableslots);




}

public parNSFFS getNSFFS(){
return this.nsffs;
}
public void execute() throws InterruptedException {
String history="";
double A = 10.0;
double AA= 10.0;
double OAA = 0.0;
double B = 0.0;
double BB = 0.0;
double OBB = 0.0;
double O = 1.0;
double R = 0.0;
int time = 0;
double weight = 1.0;
Trajectory root = new Trajectory(history, A,B,AA,BB,OAA,OBB,O,R,time,weight);
        nsffs.initialTreeCreation(root);
while (!availableslots.full() && !nsffs.getstack().empty()){
//System.out.println("number of waiting processes "+ nsffs.getstack().size());
Trajectory t1 = nsffs.stackpop();
Runnable tr1 = new TrajectoryThread(this.nsffs,t1);
Thread thread1 = new Thread(tr1);
thread1.start();
if (!availableslots.full() && !nsffs.getstack().empty()){
Trajectory t2 = nsffs.stackpop();
Runnable tr2 = new TrajectoryThread(this.nsffs,t2);
Thread thread2 = new Thread(tr2);
thread2.start();
if (!availableslots.full() && !nsffs.getstack().empty()){
Trajectory t3 = nsffs.stackpop();
Runnable tr3 = new TrajectoryThread(this.nsffs,t3);
Thread thread3 = new Thread(tr3);
thread3.start();

if (!availableslots.full() && !nsffs.getstack().empty()){
Trajectory t4 = nsffs.stackpop();
Runnable tr4 = new TrajectoryThread(this.nsffs,t4);
Thread thread4 = new Thread(tr4);
thread4.start();
thread4.join();



}
thread3.join();
}

thread2.join();
}

thread1.join();
}
}



public static void main (String args[]){
TrajectoryThreadPool ttp = new TrajectoryThreadPool();
try{
ttp.execute();
} catch(Exception e){
e.printStackTrace();
}

}


}




