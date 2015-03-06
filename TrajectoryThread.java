import java.util.concurrent.*;

public class TrajectoryThread implements Runnable{


private parNSFFS nsffs;

private availableslots avail;

private Trajectory trajectory;


public TrajectoryThread(parNSFFS nsffs, Trajectory t) {

      this.trajectory = t;
      this.nsffs = nsffs;
}




public void run() {

nsffs.Branching(trajectory);

/*System.out.println(this.nsffs.getstack().size());
if (this.trajectory.getTime() == this.nsffs.getStopTime() && this.nsffs.getstack().size() == 0 ) {
        System.out.println("Shutting down executor *************");
      semaphore.release();
}*/

}




}



