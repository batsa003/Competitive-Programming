package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskA {

    static StringBuilder ans(int n){
        StringBuilder sb= new StringBuilder();
        if(n==4){
            sb.append("1 * 2 = 2\n2 * 3 = 6\n6 * 4 = 24\n");
        }else if(n==5){
            sb.append("5 * 4 = 20\n20 + 2 = 22\n22 + 3 = 25\n25 - 1 = 24\n");
        }else if(n==6){
            sb.append("6 * 4 = 24\n2 * 3 = 6\n24 + 6 = 30\n30 - 5 = 25\n25 - 1 = 24\n");
        }else if(n==7){
            sb.append("6 * 4 = 24\n24 * 1 = 24\n5 * 2 = 10\n24 + 10 = 34\n34 - 7 = 27\n27 - 3 = 24\n");
        }else{
            sb= ans(n-4);
            sb.append( (n-3) + " + " + n + " = " + (2*n-3)+ "\n");
            sb.append( (2*n-3) + " - " + (n-1) + " = " + (n-2) + "\n");
            sb.append( (n-2) + " - " + (n-2) + " = " + 0 + "\n");
            sb.append("24 + 0 = 24\n");
        }
        return sb;
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        if(n<=3){
            out.println("NO");
        }else{
            out.println("YES");
            out.println(ans(n));
        }
    }
}
