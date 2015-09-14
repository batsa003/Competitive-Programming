package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Stack;

public class TaskG {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k =in.nextInt();
        int[] ar= new int[n+1];
        int min=0;
        for(int i=1; i<=n; i++){
            ar[i]=in.nextInt();
            min=Math.min(ar[i],min);
        }

        Stack<Integer> st= new Stack();
        int cur=0;
        int ans=0;
        for(int i=1; i<=n; i++){
            cur+= ar[i];
            if(i>k){
                cur-=ar[i-k];
            }

            //out.println("cur at i, cur= " + i + " " + cur);
            st.push(i);
            if(i>=k) {
                if (cur >= 0) {
                    while (true) {
                        int c = st.pop();
                        int maxMinus = ar[c] - min;
                        if (cur - maxMinus <= -1) {
                            ans += cur + 1;
                            ar[c] -= (cur + 1);
                            cur = -1;
                            break;
                        } else {
                            cur -= maxMinus;
                            ans+= maxMinus;
                            ar[c] = min;
                        }
                    }
                }
            }
        }
        out.println(ans);
        for(int i=1; i<=n; i++){
            out.print(ar[i] + " ");
        }

    }
}
