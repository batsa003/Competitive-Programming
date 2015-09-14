import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Bat-Orgil on 6/14/2015.
 */
public class POJ2418 {
    public static void main(String[] args){
        Scanner in= new Scanner(System.in);
        TreeMap<String,Integer> map= new TreeMap();
        double count=0;
        while(in.hasNext()){
            String s=in.nextLine();
            count+=1;
            if(!map.containsKey(s)){
                map.put(s,1);
            }else{
                map.put(s,map.get(s)+1);
            }
        }
        //System.out.println("End");
        PrintWriter out= new PrintWriter(System.out);
        DecimalFormat df= new DecimalFormat("#.####");
        df.setMaximumFractionDigits(4);
        df.setMinimumFractionDigits(4);
        for(String s : map.keySet()){
            out.println(s + " " + df.format((double) map.get(s)*100.0000/(double)count));
        }

        out.close();
        System.exit(0);
    }
}
