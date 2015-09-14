package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        HashSet<String> set= new HashSet();

        String B= in.next();
        int n= B.length();
        for(int i=1; i<=(n-1); i++){
            for(int j=i+1; j<=n; j++){
                String s= B.charAt(i-1) +"" +B.charAt(j-1);
                int a=i;
                int b=j;
                while(true){
                    int c=a+b;
                    if(c>n){
                        break;
                    }
                    s+= B.charAt(c-1);
                    a=b;
                    b=c;
                }
                set.add(s);
            }
        }
        out.println(set.size());
    }
}
