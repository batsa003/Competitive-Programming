package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<n; i++){
            ar[i]=in.nextInt();
        }

        int sol=0;
        int[] tdeath= new int[n];
        Stack<Integer> st= new Stack();
        for(int i=0; i<n; i++){
            tdeath[i]=0;
            while(!st.isEmpty() && ar[st.peek()]< ar[i]){
                tdeath[i]= Math.max(tdeath[st.pop()]+1, tdeath[i]);
            }
            if(st.isEmpty()){
                tdeath[i]=-1;
            }

            st.push(i);
            sol= Math.max(tdeath[i]+1,sol);
        }
        out.println(sol);
    }
}
