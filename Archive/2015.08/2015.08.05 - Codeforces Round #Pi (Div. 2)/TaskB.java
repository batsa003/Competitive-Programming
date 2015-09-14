package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        int n= in.nextInt();
        boolean[] ar= new boolean[1000001];
        int cur=0;
        boolean[] q= new boolean[n];
        int[] qID= new int[n];
        for(int i=0; i<n; i++){
            String s= in.next();
            if(s.equals("+")){
                q[i]=true;
                int c= in.nextInt();
                qID[i]=c;
                ar[c]=true;
            }else{
                int c= in.nextInt();
                qID[i]=c;
                if(!ar[c]) cur++;
            }
        }

        ar= new boolean[1000001];
        int max= cur;
        for(int i=0; i<n; i++){
            if(q[i]) cur++;
            else cur--;

            max= Math.max(cur,max);
        }
        out.println(max);
    }
}
