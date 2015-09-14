package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Stack;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<n; i++){
            ar[i]=in.nextInt();
        }

        int[] L= new int[n];
        int[] R= new int[n];
        Stack<Integer> st= new Stack();

        for(int i=0; i<n; i++){
            while(!st.isEmpty() && ar[st.peek()]>= ar[i]){
                st.pop();
            }

            if(st.isEmpty()){
                L[i]=0;
            }else{
                L[i]=st.peek()+1;
            }
            st.push(i);
        }

        st.clear();

        for(int i=n-1; i>=0; i--){
            while(!st.isEmpty() && ar[st.peek()]>= ar[i]){
                st.pop();
            }

            if(st.isEmpty()){
                R[i]=n;
            }else{
                R[i]=st.peek();
            }
            st.push(i);
        }

        int[] ans = new int[n+1];
        for(int i=0; i<n; i++){
            ans[R[i]-L[i]]= Math.max(ans[R[i]-L[i]],ar[i]);
        }

        for(int i=n-1; i>=1; i--){
            ans[i]= Math.max(ans[i+1],ans[i]);
        }

        for(int i=1; i<=n; i++){
            out.print(ans[i] + " ");
        }
    }
}
