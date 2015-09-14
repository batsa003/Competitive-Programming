package created;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Bat-Orgil on 9/5/2015.
 */
public class D {

    static long gcd(long a, long b){
        if(a<b){
            a^=b; b^=a;a^=b;
        }
        if(b==0){
            return a;
        }else{
            return gcd(b,a%b);
        }
    }

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        long a= in.nextLong()*in.nextLong();
        long b= in.nextLong()*in.nextLong();
        a/=gcd(a,b);
        b/=gcd(a,b);
        int a1=0,a2=0,b1=0,b2=0;
        while(a%2==0){
                a/=2;
                a1++;
        }
        while(a%3==0){
            a/=3;
            a2++;
        }
        while(b%2==0){
            b/=2;
            b1++;
        }
        while(b%3==0){
            b/=3;
            b2++;
        }

        if(a==b && a==1){
            if(a2<b2){
                a1^=b1;
                b1^=a1;
                a1^=b1;
                a2^=b2;
                b2^=a2;
                a2^=b2;
            }

            int z= a2-b2;
            a1+=z;
            z+= Math.abs(a1-b1);


        }else{
            out.println(-1);
        }


    }
}
