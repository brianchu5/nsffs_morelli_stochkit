import java.util.UUID;

public class Trajectory {


private int slot;

private double lambda;

private double A;

private double B;

private double AA;

private double BB;

private double OAA;

private double OBB;

private double O;

private double R;

private int time;

private double weight;

private double tfg;

private String dataA = "A" ;

private String dataB = "B";

private String dataAA = "AA";

private String dataBB = "BB";

private String dataOAA = "OAA";

private String dataOBB = "OBB";
 
private String dataO = "O";

private String dataR = "R";

private String modelname = "bex_stochkit";

private String history;

private String datafile;


public Trajectory(String history,double A, double B, double AA, double BB, double OAA, double OBB, double O, double R, int time,  double weight){
this.A = A;
this.B = B;
this.AA = AA;
this.BB = BB;
this.OAA = OAA;
this.OBB = OBB;
this.O = O;
this.R = R;
this.time=time;
this.lambda = ((2*OBB)+(2*BB)+B)-((2*OAA)+(2*AA)+A);
this.weight = weight; 
this.slot = 0;
this.datafile = "";
this.history = history;

}

public void setslot(int slot){
this.slot = slot;
}

public int getslot(){

return this.slot;

}

public void setparams(){
this.modelname = this.modelname + this.slot + ".xml";
this.dataA = this.dataA + this.slot + ".txt";
this.dataB = this.dataB + this.slot + ".txt";
this.dataR = this.dataR + this.slot + ".txt";
this.dataAA = this.dataAA + this.slot + ".txt";
this.dataBB = this.dataBB + this.slot + ".txt";
this.dataOAA = this.dataOAA + this.slot + ".txt";
this.dataOBB = this.dataOBB + this.slot + ".txt";
this.dataO = this.dataO + this.slot + ".txt";
this.datafile =  this.slot + ".txt";
} 


public void setHistory(){
if (this.lambda >= 0   && (this.history.equals("A") || this.history.equals("AB")))
{
this.history = this.history + "B";
}
else if ( this.lambda <= -30 && (this.history.equals("")|| this.history.equals("AB")))
{
this.history = this.history + "A";

}
}

public String getdatafile(){
return this.datafile;
}


public String getmodelname(){
 return this.modelname;
}
public String getdataA(){
return this.dataA;
}

public String getdataB(){
return this.dataB;
}


public double getA(){
return this.A;
}

public double getB(){

return this.B;
}



public String getdataAA(){
return this.dataAA;
}

public String getdataBB(){
return this.dataBB;
}


public double getAA(){
return this.AA;
}

public double getBB(){

return this.BB;
}


public String getdataOAA(){
return this.dataOAA;
}

public String getdataOBB(){
return this.dataOBB;
}


public double getOAA(){
return this.OAA;
}

public double getOBB(){

return this.OBB;
}


public String getdataO(){
return this.dataO;
}

public double getO(){
return this.O;
}

public String getdataR(){
return this.dataR;
}

public double getR(){
return this.R;
}




public int getTime(){
return this.time;
}



public double getWeight(){

return this.weight;

}  


public void setA(double A){
this.A = A;
}

public void setB(double B){
this.B = B;
}


public void setAA(double AA){
this.AA = AA;
}

public void setBB(double BB){
this.BB = BB;
}

public void setOAA(double OAA){
this.OAA = OAA;
}

public void setOBB(double OBB){
this.OBB = OBB;
}

public void setO( double O){
this.O = O;

}


public void setR(double R){
this.R = R;
}

public void setLambda(){

this.lambda = ((2*OBB)+(2*BB)+B)-((2*OAA)+(2*AA)+A); 

}

public double getLambda(){
return this.lambda;
}

public void setWeight(double weight){

this.weight = weight; 

}

public void incrementTime(int time){
this.time+=time;

}

public String getHistory(){

return this.history;

}



}
