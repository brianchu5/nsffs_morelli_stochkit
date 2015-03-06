import java.util.*;

public class availableslots{




Boolean[] slots;

public availableslots(){

this.slots = new Boolean[4];
for (int i =0; i<4; i++){
slots[i] = true;
}



}

public synchronized boolean full(){
boolean full = true;
for (int i =0; i<4; i++){
if (slots[i] == true){
full = false;
break;
}




}


return full;
}


public synchronized void openslot(int slotnum){

slots[slotnum-1] = true;
} 


public synchronized int closeslot(){

int j =9999;


for (int i = 0; i<slots.length; i++){

    if ( slots[i] ){
	
	slots[i] = false;
      j = i+1;
	break;
    }


}

return j;


}









}
