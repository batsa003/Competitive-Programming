package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] s= in.next().toCharArray();
        for(int i=0; i<s.length; i++){
            if(i+6<s.length){
                int count1=0;
                int count2=0;
                for(int j=i; j<i+7; j++){
                    if(s[j]=='1'){
                        count1++;
                    }else{
                        count2++;
                    }
                }
                if(count1==7 || count2==7){
                    out.println("YES");
                    return;
                }
            }
        }
        out.println("NO");
    }
}
