import java.util.*;
import java.io.*;


public class test


{



public static void main (String args[]) throws Exception

{

try
        {
            Process process = new ProcessBuilder("./runpython.sh").start();
                BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
           while ((line = bfr.readLine()) != null){
                System.out.println(line);
               }
            final int exitStatus = process.waitFor();
            //System.out.println("Processed finished with status: " + exitStatus);
        }
            catch(IOException ioe)
                {
                            ioe.printStackTrace();
                                }
}




}
