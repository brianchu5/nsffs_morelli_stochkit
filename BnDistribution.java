import java.util.*;

public class BnDistribution{

private List<double[]> bn;
private double floorF;
private double ceilingC;
private double probF;
private double probC;


public BnDistribution(double n) {

this.floorF = Math.floor(n);
this.ceilingC = Math.ceil(n);

if (Math.floor(n) == n)
{
probF = 1.0;
probC = 1.0;
}else
{
probC = n - Math.floor(n);
probF = Math.ceil(n) - n;
this.floorF = floorF;
this.ceilingC = ceilingC;
}
}

public double drawFromBn(){
//System.out.println(ceilingC+"has"+probC);
//System.out.println(floorF+"has"+probF);


double random = Math.random();

if (Math.random() < probC)
{ 

return this.ceilingC;


}else{


return this.floorF;

}



}

//public static void main(String args[]){
//try{
//BnDistribution bndist = new BnDistribution((Double)3.0);
//System.out.print(bndist.drawFromBn());
//}catch(PruneException P){
//System.out.println("branch pruned");
//}


//}





} 
