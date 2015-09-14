import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class POJ3233{
    static InputReader in;
    static PrintWriter out;
    static int mod;
    static int n;


    static int[][] mul(int[][] A, int[][] B){
        int[][] C= new int[A.length][B[0].length];
        for(int i=0; i<A.length; i++){
            for(int j=0; j<B[0].length; j++){
                int sum=0;
                for(int k=0; k<A[0].length; k++){
                    sum+= A[i][k]*B[k][j];
                    sum%=mod;
                }
                C[i][j]=sum;
            }
        }
        return C;
    }
    static int[][] pow(int[][] A, int k){
        int[][] C= new int[n][n];
        if(k==1){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    C[i][j]=A[i][j];
                }
            }
        }else {
            C = pow(A, k / 2);
            C = mul(C, C);
            if (k % 2 == 1) {
                C = mul(C, A);
            }
        }
        return C;
    }

    static void print(int[][] a){
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[0].length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] sum(int[][] A, int k){
        int[][] ans= new int[n][n];
        if(k==1){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    ans[i][j]=A[i][j];
                }
            }
            //return A;
        }else {
            int m = k / 2;
            int[][] B = sum(A, m);
            if (k % 2 == 0) {
                int[][] P = pow(A, m);
                ans = mul(B, P);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        ans[i][j] = (ans[i][j] + B[i][j]) % mod;
                    }
                }
            /*out.println("B:");
            print(B);
            out.println("P:");
            print(P);*/
            } else {
                int[][] P = pow(A, m + 1);
                ans = mul(B, P);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        ans[i][j] = (ans[i][j] + B[i][j] + P[i][j]) % mod;
                    }
                }
               /* out.println("B:");
                print(B);
                out.println("P:");
                print(P);*/
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        n= in.nextInt();
        int k =in.nextInt();
        mod=in.nextInt();
        int[][] A= new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                A[i][j]=in.nextInt();
            }
        }

        int[][] B= sum(A,k);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                out.print(B[i][j] + " ");
            }
            out.println();
        }

        out.close();
        System.exit(0);

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
