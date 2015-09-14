package created;

import java.util.HashMap;

public class BearPlays {

    public int GCD(int a, int b) {
        if (b==0) return a;
        return GCD(b,a%b);
    }


    public int pileSize(int A, int B, int K) {
        if(B>A){
            A^=B;
            B^=A;
            A^=B;
        }
        int gcd= GCD(A,B);
        A= A/gcd;
        B=B/gcd;
        HashMap<Integer,Integer> m1=new HashMap(); //small number to K
        HashMap<Integer,Integer> m2= new HashMap(); // K-th turn to small number

        int count=0;
        while(true){
            if( m1.containsKey(B)){
                return m2.get((K%(m1.get(B)-count)))*gcd;
            }

            m1.put(B,count);
            m2.put(count,B);
            if(count==K) return B*gcd;
            if(B==0) return 0;
            //System.out.println(m2.toString());
            count++;
            A-=B;
            B*=2;
            if(A<B){
                A^=B;
                B^=A;
                A^=B;
            }
        }


    }
}
