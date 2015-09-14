package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m =in.nextInt();
        int s= in.nextInt();
        int[] digits= new int[m+1];
        if(1<= s && s<=9*m){
            digits[1]=1;
            int sum=s-1;
            int cur= m;
            while(sum>0){
                if(sum>9){
                    digits[cur]=9;
                    cur--;
                    sum-=9;
                }else{
                    digits[cur]+=sum;
                    sum=0;
                }
            }
            for(int i=1; i<=m; i++){
                out.print(digits[i]);
            }

            Arrays.fill(digits, 9);
            int cursum=9*m;
            sum=s;
            cur=m;
            while(sum<cursum){
                if(cursum-sum<=9){
                    digits[cur]-= (cursum-sum);
                    cursum=sum;
                }else{
                    digits[cur]=0;
                    cursum-=9;
                    cur--;
                }
            }
            out.print(" ");
            for(int i=1; i<=m; i++){
                out.print(digits[i]);
            }


        }else if(s==0 && m==1) {
            out.println("0 0");
        }else {
            out.println("-1 -1");
        }
    }
}
