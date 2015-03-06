import java.io.*;

public class simrunner{

private parNSFFS nsffs;


public simrunner(parNSFFS nsffs){

this.nsffs = nsffs; 

}



public static void main(String args[]){


long tStart = System.currentTimeMillis();
TrajectoryThreadPool ttp = new TrajectoryThreadPool();

simrunner sr = new simrunner(ttp.getNSFFS());



for (int i = 0; i<20; i++){
System.out.println(" new run starting **************  " + i );
try{
ttp.execute();
sr.nsffs.getBins().updateProbabilities();
        sr.nsffs.getBins().printBins();


            Process process = new ProcessBuilder("./runpython.sh").start();
		BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
	String line = "";
            while ((line = bfr.readLine()) != null){
                System.out.println(line); 
		}
            final int exitStatus = process.waitFor();
            //System.out.println("Processed finished with status: " + exitStatus);


}catch(Exception e){
/**/
}
System.out.println("  run ending --------------  " + i );

}
    sr.nsffs.getBins().updateProbabilities();
    try{
	sr.nsffs.getBins().printBins();}


    catch(IOException e){
    e.printStackTrace();}
long tEnd = System.currentTimeMillis();
long tDelta = tEnd - tStart;
double elapsedMinutes = tDelta / 60000.0;
System.out.println(elapsedMinutes);
}
}
