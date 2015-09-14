package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskC {

    static boolean compare(String s1, String s2){
        return s1.compareTo(s2)>0;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        String[] f= new String[n];
        String[] s= new String[n];
        for(int i=0; i<n; i++){
            f[i]=in.next();
            s[i]=in.next();
        }
        int[] p = new int[n];
        for(int i=0; i<n; i++) {
            p[i]=in.nextInt()-1;
        }
        String[] h= new String[n];
        h[p[0]]= (f[p[0]].compareTo(s[p[0]]) >0) ? s[p[0]]:f[p[0]];
        for(int i=1; i<n; i++){
            if( compare(h[p[i-1]],f[p[i]]) && compare(h[p[i-1]], s[p[i]])){
                out.println("NO");
                return;
            }
            String str= h[p[i-1]];
            String c1= f[p[i]];
            String c2= s[p[i]];
            String ans="";
            if( compare(str,c1)){
                ans=c2;
            }else if(compare(str,c2)){
                ans=c1;
            }else{
                ans= (compare(c1,c2)) ? c2:c1;
            }
            h[p[i]]=ans;
        }
        out.println("YES");

    }
}
