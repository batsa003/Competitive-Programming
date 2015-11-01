import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class C {
    static Scanner in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new Scanner(System.in);
        out= new PrintWriter(System.out,true);
        while(in.hasNext()){
            String s= in.nextLine();
            int[] cnt= new int[26];
            for(int i=0; i<s.length(); i++){
                cnt[ s.charAt(i)-'a']++;
            }
            int sum=0;
            int numOdd=0;
            for(int i=0; i<26; i++){
                sum+=cnt[i];
                if(cnt[i]%2==1){
                    numOdd++;
                }
            }
            int X=0;
            if(numOdd>1){
                X= numOdd-1;
            }
            long ans=0;
            long[] fac= new long[12];
            fac[0]=1;
            for(int i=1; i<12; i++){
                fac[i]=fac[i-1]*i;
            }
            if(numOdd<=1) {
                long res= fac[sum/2];
                for (int i = 0; i < 26; i++) {
                    if (cnt[i] % 2 != 0) {
                        cnt[i]--;
                    }
                    res/= fac[cnt[i]/2];
                }
                ans=res;
            }else{
                for(int j=0; j<26; j++){
                    if(cnt[j]%2==1) {
                        long res=  fac[(sum-numOdd)/2];
                        for(int i=0; i<26; i++){
                            res/=fac[(cnt[i]/2)];
                        }
                        ans=res;
                        break;
                    }
                }
            }
            out.println(X + "," + ans);
        }
        out.println();


        out.close();
        System.exit(0);

    }
}