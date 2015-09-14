package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int p =in.nextInt();
        String t= in.next();
        char[] s= new char[n+1];
        for(int i=0; i<n; i++){
            s[i+1]=t.charAt(i);
        }
        int i1= n/2;
        int i2;
        if(n%2==1){
            i2= i1+2;
        }else{
            i2=i1+1;
        }
        int L1=i1;
        int R1=-1;
        for(int i=i1; i>=1; i--){
            if(s[i]!= s[n+1-i]){
                L1=Math.min(i,L1);
                R1= Math.max(i,R1);
            }
        }
        if(R1==-1){
            out.println(0);
            return;
        }
        int L2=n+1;
        int R2=-1;
        for(int i=i2; i<= n; i++){
            if(s[i]!=s[n+1-i]){
                L2= Math.min(L2,i);
                R2= Math.max(i,R2);
            }
        }
        long change=0;
        for(int i=1; i<=i1; i++){
            int c= Math.abs((s[i]-'a')-(s[n+1-i]-'a'));
            change+= Math.min(c, 26-c);
        }
       /* out.println(L1 + " " + R1);
        out.println(L2 + " " + R2);
        out.println("change=" + change);*/
        //out.println("BOO");

        if(p<=i1){
            int leftright= Math.min( (Math.abs(p-L1) + Math.abs(L1-R1)), (Math.abs(p-R1)+Math.abs(L1-R1)));
            out.println((change+(long)leftright));
        }else{
            int leftright= Math.min( (Math.abs(p-L2) + Math.abs(L2-R2)), (Math.abs(p-R2)+Math.abs(L2-R2)));
            out.println((change+(long)leftright));
        }
    }
}
