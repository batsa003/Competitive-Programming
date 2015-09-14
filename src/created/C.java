package created;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Bat-Orgil on 9/5/2015.
 */
public class C {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String s= in.next();
        BigInteger a= BigInteger.valueOf(in.nextInt());
        BigInteger b= BigInteger.valueOf(in.nextInt());

        if(s.length()==1){
            out.println("NO");
            return;
        }
        for(int i=0; i<s.length()-1; i++){
            BigInteger one= new BigInteger( s.substring(0,i+1));
            BigInteger two = new BigInteger( s.substring(i+1));
            if(one.mod(a).longValue()==0 && two.mod(b).longValue()==0){
                out.println("YES");
                out.println(one.toString());
                out.println(two.toString());
                return;
            }
        }
        out.println("NO");

    }
}
