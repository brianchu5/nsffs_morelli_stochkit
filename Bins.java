import java.util.*; 
import java.io.*;

public class Bins{

	private TreeCounter treecounter;
	private HashMap<keys,values> bins;
//	private HashMap<List<Integer>,List<Double>> ABbins;
	private int tmax;
	private int increment;
public Bins(TreeCounter treecounter){

	this.bins = new HashMap<keys,values>();// double[0] - H
							// double[1] - j
//        this.ABbins = new HashMap<List<Integer>,List<Double>>();

	this.increment = 10;
	this.tmax = 510;

/*	for (int A = 0; A < 90; A+=1){
            for (int B = 0; B < 90; B+=1){ 
		for (int RAoff = 0; RAoff<2; RAoff++){
                for (int RBoff = 0; RBoff<2; RBoff++){
		for (int RAon = 0; RAon<2; RAon++){
		for (int RBon = 0; RBon<2; RBon++){
		for (int S1 = 0; S1<1001; S1++){
                for (int t = 0; t<1100; t+=30){

                List<Double> props = new ArrayList<Double>();
                props.add(0.0);
                props.add(0.0);
                props.add(0.0);
                List<Integer> a = new ArrayList<Integer>();
                a.add(A);
                a.add(B);
		a.add(RAon);
		a.add(RBon);
		a.add(S1);
		a.add(RAoff);
		a.add(RBoff);
	
                a.add(t);
		
        //      Double b = t/1000.0;

                ABbins.put(a,props);
	}
         }
        }
	}
	}
	}	
	}
}*/

	for (int l = -70; l < 71; l+=1){
 		for (int t = 0; t<tmax; t+=increment){
	

		bins.put(new keys(l,t),new values(0.0,0.0));


	 }
	}

		this.treecounter = treecounter; 
	}


/*public static Integer lambinning(Integer l){

	if (l>=-50 && l < -40){

		return -45;

	} else if (l >=-40 && l < -30){

		return -35; 

	} else if (l >=-30 && l <-20){

		return -25;

	} else if (l >=-20 && l <-10){

		return -15; 

	}else if (l >=-10 && l <0){

		return -5;

	} else if (l >=0 && l < 10){
	
		return 5;

	} else if (l >=10 && l <20){

		return 15;

	} else if (l >=20 && l <30){

		return 25;
	
	}else if (l >=30 && l <40){

		return 35;

	} else if (l>=40 && l <=50){

		return 45;

	}

	return 100;
}*/

public synchronized double getJli(int l, int t){

	return bins.get(new keys(l,t)).getJ();

}

public synchronized void updateProbabilities(){

	double sum;
	double hli;

	for (int t = 0; t<tmax; t+=increment){
		sum = 0.0;
		
	for (int l = -70; l < 70; l+=1){
		hli = getHli(l,t);
		sum += hli;


	 }
	for (int la = -70; la<70; la+=1){
		hli = getHli(la,t);
		updateP(la,t,hli/sum);
		}
	}

}

/*public synchronized void updateABProbabilities(){

        Double sum;
        Double hli;

        for (int t = 0; t<1100; t+=30){
                sum = 0.0;

        for (int a = 0; a < 100; a+=1){
	for (int b = 0; b< 100; b+=1){ 
	for (int RAoff = 0; RAoff<2; RAoff++){
                for (int RBoff = 0; RBoff<2; RBoff++){
                for (int RAon = 0; RAon<2; RAon++){
                for (int RBon = 0; RBon<2; RBon++){
                for (int S1 = 0; S1<1001; S1++){	
                hli = getABHli(a,b,RAon,RBon,S1,RAoff,RBoff,t);
                sum += hli;


         }
	}
	}
	}
	}
	}
	}
        for (int la = 0; la<100; la+=1){
	for (int lb = 0; lb<100; lb+=1){
	for (int RAoff = 0; RAoff<2; RAoff++){
        for (int RBoff = 0; RBoff<2; RBoff++){
        for (int RAon = 0; RAon<2; RAon++){
        for (int RBon = 0; RBon<2; RBon++){
        for (int S1 = 0; S1<1001; S1++){

                hli = getABHli(la,lb,RAon,RBon,S1,RAoff,RBoff,t);
                updateABP(la,lb,RAon,RBon,S1,RAoff,RBoff,t,hli/sum);
                }
	}
	}
	}
	}	
	}
        }
	}
	HashMap<Integer,Double> tbins = new HashMap<Integer,Double>();
	for (Map.Entry<List<Integer>,List<Double>> entry: ABbins.entrySet()){
	List<Integer> key = entry.getKey();
	List<Double> value = entry.getValue();
	double h = value.get(0);
        int t =key.get(8);
	List<Integer> a = new ArrayList<Integer>();
	if (tbins.get(key)!=null){
             tbins.put(t,tbins.get(t)+h);
	}else

	tbins.put(t,h);

	}	
	for (Map.Entry<List<Integer>,List<Double>> entry: ABbins.entrySet()){
	entry.getKey();
	List<Integer> key = entry.getKey();
        List<Double> value = entry.getValue();
	int t = key.get(8);
	Double total =tbins.get(t);	
	Double Jli = value.get(1);
	Double Hli = value.get(0);
	Double P = Hli/total;
	List<Double> value2 = new ArrayList<Double>();
	value2.add(Jli);
	value2.add(Hli);
	value2.add(P);
	ABbins.put(key,value2)	;
	}
}*/ 
public synchronized void updateJli(int l, int t){

        bins.get(new keys(l,t)).updateJ(treecounter.getTreeCounter());
/*	Double H = getHli(l,t);
	Integer S = treecounter.getTreeCounter();
   //      Double qw = bins.get(key).get(2);
    //    Double qab = bins.get(key).get(3);
     //   Double sa = bins.get(key).get(4);
	//Integer S = 2;
	Double Jli = H/S;
	List<Double> props = new ArrayList<Double>();
	props.add(H);
	props.add(Jli);
//	props.add(qw);
//	props.add(qab);
//	props.add(sa);
	bins.put(new keys(l,t), props);
*/
}

/*public synchronized void updateQab(Integer l, Integer t, Double w){

	List<Integer> key = new ArrayList<Integer>();
        key.add(l);
        key.add(t);
        Double H = bins.get(key).get(0);
	Double Jli = bins.get(key).get(1);
        Integer S = treecounter.getTreeCounter();
	Double qw = bins.get(key).get(2);
	qw = qw + w;
	Double qab = qw/S;
	Double sa = bins.get(key).get(4);  
        //Integer S = 2;
        List<Double> props = new ArrayList<Double>();
        props.add(H);
        props.add(Jli);
	props.add(qw);
        props.add(qab);	
	props.add(sa);
        bins.put(key, props);

}*/

/*Ipublic synchronized void updateSA(Integer l, Integer t, Double w){

        List<Integer> key = new ArrayList<Integer>();
        key.add(l);
        key.add(t);
        Double H = bins.get(key).get(0);
        Double Jli = bins.get(key).get(1);
        Double qw = bins.get(key).get(2);
        Double qab = bins.get(key).get(3);
        Double sa = bins.get(key).get(4);
	sa = sa + w;
        //Integer S = 2;
        List<Double> props = new ArrayList<Double>();
        props.add(H);
        props.add(Jli);
        props.add(qw);
        props.add(qab);
        props.add(sa);
        bins.put(key, props);

}*/





public synchronized Double getHli(int l, int t){

	return bins.get(new keys(l,t)).getH();

}


/*public synchronized Double getABHli(Integer A, Integer B, Integer AA, Integer BB, Integer OAA, Integer OBB, Integer O, Integer R, Integer t){

        List<Integer> key = new ArrayList<Integer>();
        key.add(A);
	key.add(B);
	key.add(AA);
	key.add(BB);
	key.add(OAA);
	key.add(OBB);
	key.add(O);
        key.add(R);
	key.add(t);
        Double Hli = bins.get(key).get(0);
        return Hli;

}*/


public synchronized void updateH(int l, int t,double w){

	bins.get(new keys(l,t)).updateH(w);

/*	List<Double> arr  = bins.get(new keys(l,t));
        if (arr == null) // doesn't exist
         {// do stuff
          List<Double> props = new ArrayList<Double>();
                props.add(0.0); //H
                props.add(0.0); //Jli
   //             props.add(0.0); //qw
    //            props.add(0.0); //qab
     //           props.add(0.0); //sa
      //          props.add(0.0); //prob 
	 System.out.println("lambda:"+l);
//	 System.out.println("time:"+t);
	 bins.put(new keys(l,t),props);
         }

	Double H = getHli(l,t);
	Integer S = treecounter.getTreeCounter();
	//Integer S = 2;
	Double Jli = getJli(l,t);
//	 Double qw = bins.get(key).get(2);
 //       Double qab = bins.get(key).get(3);
  //      Double sa = bins.get(key).get(4);
	List<Double> props = new ArrayList<Double>();
	props.add(H+ (Double) w);
	props.add(Jli);
//	props.add(qw);
 //       props.add(qab);
  //      props.add(sa);	
	bins.put(new keys(l,t),props);*/
}

public synchronized void updateP(int l, int t, double p){ 

	bins.get(new keys(l,t)).updateP(p);

/*	double H = getHli(l,t);
	double Jli = getJli(l,t);
//	Double Qw = bins.get(key).get(2);
//	Double Qab = bins.get(key).get(3);
//	Double Sa = bins.get(key).get(4);
	List<Double> props = new ArrayList<Double>();
	props.add(H);
	props.add(Jli);
	props.add(p);
//	props.add(Qw);
//	props.add(Qab);
//	props.add(Sa);
	bins.put(new keys(l,t),props);*/
}
/*public synchronized void updateABP(Integer A, Integer B, Integer AA, Integer BB, Integer OAA, Integer OBB, Integer O,Integer R,  Integer t, Double p){

        List<Integer> key = new ArrayList<Integer>();
        key.add(A);
        key.add(B);
	key.add(AA);
	key.add(BB);
	key.add(OAA);
	key.add(OBB);
	key.add(O);
        key.add(R);	
        key.add(t);
        Double H = bins.get(key).get(0);
        Double Jli = bins.get(key).get(1);
        List<Double> props = new ArrayList<Double>();
        props.add(H);
        props.add(Jli);
        props.add(p);
        ABbins.put(key,props);
}*/



public synchronized Double calculateChildNumber(int l, int t, double weight){

	double Jli = getJli(l,t);
	return weight / Jli;

}

/*public synchronized Double calculateWmin(){
	List<Double> keys = new ArrayList<Double>();
	for (Map.Entry<keys,List<Double>> entry: bins.entrySet()){
		Double key = entry.getValue().get(1);
		keys.add(key);
	}
	Double Wmin = Collections.min(keys);

	return Wmin;

}*/


/*public Double printBin(Integer l, Integer t){
	
	

	List<Integer> key = new ArrayList<Integer>();
	key.add(l);
	key.add(t);
	Double Jli = bins.get(key).get(1);

	return Jli;

}*/


/*public synchronized void updateABBins(Integer A, Integer B, Integer AA, Integer BB, Integer OAA, Integer OBB, Integer O, Integer R, Integer l, Integer t ){
List<Integer> key = new ArrayList<Integer>();
        key.add(A);
	key.add(B);
	key.add(AA);
	key.add(BB);
	key.add(OAA);
	key.add(OBB);
	key.add(O);
	key.add(R);
        key.add(t);
        Double H = getHli(l,t);
	Double Jli = getJli(l,t);
        List<Double> props = new ArrayList<Double>();
        props.add(H);
        props.add(Jli);
        ABbins.put(key,props);


}*/


public synchronized void printBins() throws IOException{

FileWriter fstream;
BufferedWriter out;
fstream = new FileWriter("map.txt");
out = new BufferedWriter(fstream);

int count = 0;

Iterator<Map.Entry<keys,values>> it = bins.entrySet().iterator();

while (it.hasNext()){
Map.Entry<keys,values> pairs = it.next();

out.write(pairs.getKey().x + "\t");
out.write(pairs.getKey().y + "\t");


out.write(pairs.getValue().getH() + "\t");
out.write(pairs.getValue().getJ() + "\t");
out.write(pairs.getValue().getP() + "\t");
//for (Double b : pairs.getValue()){
//out.write(""+String.format("%.5f" + "\t",pairs.getValue().getH() ));
//out.write(""+String.format("%.5f" + "\t",pairs.getValue().getJ() ));
//out.write(""+String.format("%.5f" + "\t",pairs.getValue().getP() ));
//}
out.write("\n");
count++;
}
out.close();
}


/*public synchronized void ABprintBins() throws IOException{

FileWriter fstream;
BufferedWriter out;
fstream = new FileWriter("ABmap.txt");
out = new BufferedWriter(fstream);

int count = 0;

Iterator<Map.Entry<List<Integer>,List<Double>>> it = ABbins.entrySet().iterator();

while (it.hasNext()){
Map.Entry<List<Integer>,List<Double>> pairs = it.next();

for (Integer a : pairs.getKey()){
out.write(a + "\t");
}
for (Double b : pairs.getValue()){
out.write(""+String.format("%.5f" + "\t",b ));

}
out.write("\n");
count++;
}
out.close();
}*/




/*for (Map.Entry<List<Integer>,List<Double>> entry: bins.entrySet()){
      List<Integer> key = entry.getKey();
      Integer[] keys = new Integer[2];
      keys = key.toArray(keys);
      String skey = Arrays.toString(keys);

      List<Double> value = entry.getValue();
      Double[] values = new Double[2];
      values = value.toArray(values);
      String dvalue = Arrays.toString(values);
      System.out.println(skey+"=>"+dvalue);


}
List<Integer> search = new ArrayList<Integer>();
search.add(1);
search.add(2);

System.out.println(bins.get(search));


}*/



public static void main(String args[]){
TreeCounter tree = new TreeCounter();
tree.incrementTreeCounter();
Bins b = new Bins(tree);
//List<Integer> search = new ArrayList<Integer>();
//search.add(1);
//search.add(1);
//System.out.println(b.bins.get(search));
//System.out.println(b.getJli(1,1));
//b.updateH(1,150,10.0);
b.updateH(150,150,10.0);
b.updateJli(150,150);
System.out.println(b.getJli(150,150));
}
// public static void main(String args[]){

//Map<Integer[],Integer> tm = new HashMap<Integer[],Integer>();

//for (int i=0;i<10;i++){
//for (int j=-5;j<6;j++){
//Integer[] a = {i,j};
//tm.put(a,new Integer(j));
//}
//}
//for (Map.Entry<Integer[],Integer> entry: tm.entrySet()){
//	Integer[] key = entry.getKey();
//	String skey = Arrays.toString(key);
//	Integer value = entry.getValue();
//	System.out.println(skey+"=>"+value);
//}
//}
	}
