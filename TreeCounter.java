public class TreeCounter{


private int counter;

TreeCounter(){



this.counter = 0;



}


public synchronized void incrementTreeCounter(){
this.counter+=1; 
}


public synchronized int getTreeCounter(){
return this.counter;
}










}
