package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.TreeSet;

public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k =in.nextInt();
        int a= in.nextInt();
        int m= in.nextInt();
        int[] ar= new int[m];
        for(int i=0; i<m; i++){
            ar[i]=in.nextInt();
        }

        TreeSet<Integer> set =new TreeSet();
        set.add(0);
        set.add(n+1);
        int cur= (n+1)/(a+1);  // >=k

        for(int i=0; i<m; i++){
            int low= set.lower(ar[i]);
            int high= set.higher(ar[i]);
            cur-= (high-low)/(a+1);
            cur+= (ar[i]-low)/(a+1);
            cur+= (high-ar[i])/(a+1);
            set.add(ar[i]);
            /*System.out.println("Cur at i=" + (i+1) + " " + cur);
            System.out.println(set);*/
            if(cur<k){
                out.println(i+1);
                return;
            }
        }
        out.println(-1);


    }
}
