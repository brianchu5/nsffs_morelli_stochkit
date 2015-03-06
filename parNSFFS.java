import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;


public class parNSFFS{


  private String solver = "./runF.sh";
 

  private Stack<Trajectory> stack;

  private Bins bins;

  private TreeCounter treecounter;

  private double wmin;

  private double wmax;


  private int timeinterval;


  private int stopTime;


  private double startingR;

  private int RTime;



  private availableslots avail;


   


public parNSFFS(int timeinterval, int stopTime, double startingR, int RTime, availableslots avail){

   this.stack = new Stack<Trajectory>();
   this.treecounter = new TreeCounter();
   this.wmax = 1;
   this.wmin = 0;
   this.timeinterval = timeinterval;
   this.stopTime = stopTime;
   this.bins = new Bins(treecounter);
   this.startingR = startingR;
   this.RTime = RTime;
   this.avail = avail; 
}



public synchronized Stack getstack(){
return this.stack;
}

public synchronized void stackpush(Trajectory t){

this.stack.push(t);

}


public synchronized Trajectory stackpop(){

return this.stack.pop();

}


public double getStopTime(){
return this.stopTime;
}



public void updateProperties(Trajectory trajectory, double lambda, int time, double incomingweight,double A, double B, double AA, double BB, double OAA, double OBB, double O, double R )
{

	this.bins.updateH(lambinning((int)lambda),time,incomingweight);


    // do something other
   this.bins.updateJli(lambinning((int)lambda), time);
 //  if (trajectory.getHistory().equals("AB")){
 //  this.bins.updateQab((int)lambda,time,incomingweight);
  // }
   //if (trajectory.getHistory().equals("A")){
 //  this.bins.updateSA((int)lambda,time,incomingweight);
  // }

//   bins.updateABBins((int) A, (int) B, (int) AA, (int) BB, (int) OAA, (int) OBB, (int) O, (int) R,(int) lambda, time);



}


public void initialTreeCreation(Trajectory root) {
   treecounter.incrementTreeCounter();
   double lambda = root.getLambda();
   double incomingweight = root.getWeight();
   double A = root.getA();
   double B = root.getB();
   double AA = root.getAA();
   double BB = root.getBB();
   double OAA = root.getOAA();
   double OBB = root.getOBB();
   double O = root.getO();
   double R = root.getR();
   int time =root.getTime();
   this.updateProperties(root,lambda,time,incomingweight,A, B, AA, BB, OAA, OBB, O, R);
   //bins.updateH((int)lambda, time,incomingweight);
   //bins.updateJli((int)lambda, time);
   //bins.updateABBins((int) A, (int) B, (int) RAon, (int) RBon, (int) S1, (int) RAoff, (int) RBoff, (int) lambda, time);
   stackpush(root);
   // create new model file with unique id
}


public int lambinning(int l){
    if (l>70){

        return 70;

    }

	else if (l < -70){ 

        return -70;
	}


	else{

	return l;
	}


}


public boolean WeightIsWithinRange(Trajectory tr){

   if (tr.getWeight() >= wmin && tr.getWeight() <= wmax) {
      return true;
      } else{
               return false;

      }
   }





public void Branching(Trajectory tr) {
 /*   if (tr.getTime() == stopTime && stack.size() == 0 ) {
	System.out.println("Shutting down executor *************");
	executor.shutdown();
    } */
//   System.out.println("number of waiting processes "+ this.getstack().size());
     if(tr.getTime()< stopTime){
 //  if (tr.getTime()<stopTime){
        int ni = avail.closeslot();
//	System.out.println(ni);
        if (ni == 9999){
        this.stackpush(tr);
        return;
        }
       // System.out.println(ni+ " is now closed");
        tr.setslot(ni);
        tr.setparams(); 

//System.out.println("starting "+tr.getmodelname());
if(tr.getTime() == RTime){
//if ((tr.getTime()==RTime){  // induce signal at specified time
        tr.setR(startingR);
    }

    //wmin = bins.calculateWmin();  // store value of minimum weight
     
    simulateForward(tr);        // simulate forward for a timestep
	
    avail.openslot(ni);
//	System.out.println(ni+ " is now open.");
    double A = tr.getA();     // perform updates 
    double B = tr.getB();   
    double R = tr.getR();    
    double AA = tr.getAA();
    double BB = tr.getBB();
    double OAA = tr.getOAA();
    double OBB = tr.getOBB(); 
    double O = tr.getO();
    int time = tr.getTime();
    String history = tr.getHistory();
   // System.out.println(time);
    double lambda = tr.getLambda();
    double incomingweight = tr.getWeight();
    double jli;
    double n;
    synchronized(this){
    this.updateProperties(tr,lambda,time,incomingweight,A, B, AA,BB,OAA,OBB,O,R);
    //bins.updateH((int)lambda,time,incomingweight);
    //bins.updateJli((int)lambda,time);
    //bins.updateABBins((int) A, (int) B, (int) RAon, (int) RBon, (int) S1, (int) RAoff, (int) RBoff, (int) lambda, time);
    //wmin = bins.calculateWmin();
    jli = bins.getJli(lambinning((int)lambda),time);
    n = bins.calculateChildNumber(lambinning((int)lambda),time,incomingweight);
        }
    if (WeightIsWithinRange(tr)){   // create children
        BnDistribution bn = new BnDistribution(n);
        int nchildren = (int) bn.drawFromBn();
	//System.out.println("number of waiting processes "+ nchildren);
        for (int i=0; i<nchildren; i++){
            
           // System.out.println("number of waiting processes "+ this.getstack().size());
            Trajectory t = new Trajectory(history,A,B,AA,BB,OAA,OBB,O,R,time,jli);
	    this.stackpush(t);
	    //System.out.println(bins.getHli(0, 0));
	   /* while (!avail.full() && !getstack().empty() && getstack().size() != 1){ 
            Trajectory traje = this.stackpop();
            TrajectoryThread traj = new TrajectoryThread(this,traje,avail,semaphore);
	    Thread thread = new Thread(traj);
            thread.start();
	     }*/
    }
}
 else {
	//System.out.println("number of waiting processes "+ this.getstack().size());
        Trajectory t = new Trajectory(history,A, B,AA,BB,OAA,OBB,O,R,time,incomingweight);
	this.stackpush(t);
	//System.out.println("hi");
	//System.out.println(bins.getHli(0, 0));
/*	while (!avail.full() && !getstack().empty() && getstack().size() != 1){

	Trajectory traje = this.stackpop();
        TrajectoryThread traj = new TrajectoryThread(this,traje,avail,semaphore);
	Thread thread = new Thread(traj);
        thread.start();
	}*/
    } 

  }
}


public void simulateForward(Trajectory tr) {

    double A = tr.getA();
    double B = tr.getB();
    double AA = tr.getAA();
    double BB = tr.getBB();
    double R = tr.getR();
    double OAA = tr.getOAA();
    double OBB = tr.getOBB();
    double O = tr.getO();
    int time = tr.getTime();
    
    String datA = tr.getdataA();
    String datB = tr.getdataB();
    String datR = tr.getdataR();
    String datAA = tr.getdataAA();
    String datBB = tr.getdataBB();
    String datOAA = tr.getdataOAA();
    String datOBB = tr.getdataOBB(); 
    String datO = tr.getdataO();
    String modname = tr.getmodelname();
    String out = tr.getdatafile();

    double lambda;
    
    ModelChange(modname ,String.valueOf(A),String.valueOf(B), String.valueOf(AA), String.valueOf(BB), String.valueOf(OAA), String.valueOf(OBB), String.valueOf(O), String.valueOf(R));
    try{
        callStochKit(datA,datB, datAA, datBB, datOAA, datOBB, datO, datR,modname,out,time);
    } catch (Exception e){
        e.printStackTrace();
    }
    try{
        A = readLines(tr.getdataA());
    } catch (IOException e){
        e.printStackTrace();
        }
    try{
        B = readLines(tr.getdataB());
    } catch (IOException e) {
        e.printStackTrace();   
    }
    try{
        R = readLines(tr.getdataR());
    } catch (IOException e) {
        e.printStackTrace();
    }
    try{
        AA = readLines(tr.getdataAA());
    } catch (IOException e) {
        e.printStackTrace();
    } 
    try{
        BB = readLines(tr.getdataBB());
    } catch (IOException e){
        e.printStackTrace();
        }
    try{
        OAA = readLines(tr.getdataOAA());
    } catch (IOException e) {
        e.printStackTrace();
    }
    try{
        OBB = readLines(tr.getdataOBB());
    } catch (IOException e) {
        e.printStackTrace();
    }
    try{
        O = readLines(tr.getdataO());
    } catch (IOException e) {
        e.printStackTrace();
    }

    tr.incrementTime(timeinterval);
    tr.setA(A);
    tr.setB(B);
    tr.setAA(AA);
    tr.setBB(BB);
    tr.setR(R);
    tr.setOAA(OAA);
    tr.setOBB(OBB);
    tr.setO(O);
    tr.setLambda();
  //  tr.setHistory();

}


public static double readLines(String filename) throws IOException
{
    FileReader fileReader = new FileReader(filename);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    java.util.List <String> lines = new ArrayList<String>();
    String line = null;

    while ((line = bufferedReader.readLine()) != null){
                lines.add(line);
    }

    bufferedReader.close();

    String[] strlines = lines.toArray(new String[lines.size()]);

    double[] doublelines = new double[strlines.length];

    for (int i = 0; i<strlines.length; i++){

        doublelines[i] = Double.parseDouble(strlines[i]);
    }

    return doublelines[0];
}

public void callStochKit(String A,String B,String AA,String BB,String OAA,String OBB,String  O, String R,String modname,String out,double time) throws Exception
{
    String st = Integer.toString(timeinterval);
    String real = Integer.toString(1);
    String ival = Integer.toString(0);
    try
        {
            Process process = new ProcessBuilder(this.solver,modname,st,real,ival,out,out,A,AA,B,OAA,BB,OBB,O,R).start();
            final int exitStatus = process.waitFor();
            //System.out.println("Processed finished with status: " + exitStatus);
        }
            catch(IOException ioe)
                {
                            ioe.printStackTrace();
                                }
}


public void ModelChange(String filename,String A,String B, String AA , String BB, String OAA, String OBB, String O, String R){
    try{
                File model = new File(filename);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(model);
                doc.getDocumentElement().normalize();
		NodeList cytokines = doc.getElementsByTagName("InitialPopulation");
                Node a = (Node) cytokines.item(0);
                Node aa = (Node) cytokines.item(1);
                Node b= (Node) cytokines.item(2);
	        Node oaa = (Node) cytokines.item(3);
                Node bb = (Node) cytokines.item(4);
		
                 Node obb = (Node) cytokines.item(5);
  	        Node o = (Node) cytokines.item(6);
		Node r = (Node) cytokines.item(7);
                 a.setTextContent(A);
                 aa.setTextContent(AA);
                 b.setTextContent(B);
                 oaa.setTextContent(OAA);
 	           bb.setTextContent(BB);
  	          obb.setTextContent(OBB);
  		  o.setTextContent(O);
                  r.setTextContent(R);
		
                  TransformerFactory transformerFactory = TransformerFactory.newInstance();
                  Transformer transformer = transformerFactory.newTransformer();
                  DOMSource source = new DOMSource(doc);
                  StreamResult result = new StreamResult(new File(filename));
                  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                  transformer.transform(source, result);
                                                                                                                                  // System.out.println("XML file updated successfully");
    }catch (SAXException | ParserConfigurationException|IOException | TransformerException  e1)
    {
                e1.printStackTrace();
                        }
}

public Bins getBins(){
return this.bins;
}


}

