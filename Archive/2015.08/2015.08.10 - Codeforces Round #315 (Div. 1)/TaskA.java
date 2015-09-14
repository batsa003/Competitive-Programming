package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskA {
    static int[] pal, prime;
    static boolean[] isPrime;


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int p = in.nextInt();
        int q= in.nextInt();
        int n= 1300000;
        pal= new int[n+1];
        prime= new int[n+1];
        isPrime= new boolean[n+1];
        Arrays.fill(isPrime, true);
        for(int i=2; i<=n; i++){
            if(isPrime[i]){
                for(int j=2; i*j<=n; j++){
                    isPrime[i*j]=false;
                }
            }
        }

        int cur=0;
        for(int i=2; i<=n; i++){
            if(isPrime[i]){
                cur++;
            }
            prime[i]=cur;
        }

        for(int i=1; i<=n; i++){
            String s= ""+i;
            int k=0;
            int l=s.length()-1;
            boolean Pal=true;
            while(k<l){
                if(s.charAt(k)!=s.charAt(l)){
                    Pal=false;
                    break;
                }
                k++;
                l--;
            }
            if(Pal){
                pal[i]=pal[i-1]+1;
            }else{
                pal[i]=pal[i-1];
            }
            //System.out.println(i + " "+ pal[i]);
        }
       // double max=0;
        for(int i=n; i>=1; i--){
           /* out.println((double) pal[i]/(double)prime[i]);
            max= Math.max(max, (double) pal[i]/(double)prime[i]);*/
            if(i==1){
                out.println(1);
                return;
            }
            if((long)prime[i]*q <= (long)pal[i]*p ){
                out.println(i);
                return;
            }
        }

        out.println("Palindromic tree is better than splay tree");
    }
}
