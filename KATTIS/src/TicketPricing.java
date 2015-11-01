import java.util.Scanner;

/**
 * Created by Bat-Orgil on 10/28/2015.
 */
public class TicketPricing {
    public static void main(String[] args){
        Scanner in= new Scanner(System.in);
        int N =in.nextInt();
        int W= in.nextInt();
        int[] K = new int[W+2];
        int[][] p = new int[W+2][101];
        int[][] c= new int[W+2][101];
        for(int i=W+1; i>=1; i--) {
            K[i] = in.nextInt();
            for (int j = 0; j < K[i]; j++) {
                p[i][j] = in.nextInt();
            }
            for (int j = 0; j < K[i]; j++) {
                c[i][j] = in.nextInt();
            }
        }

        long[][] dp = new long[W+2][301];
        int ind=-1;
        for(int i=1; i<=W+1; i++){
            for(int j=0; j<=300; j++){
                long ans=0;
                if(j==0) {
                    dp[i][j] = 0;
                    continue;
                }
                for(int k=0; k<K[i]; k++){
                    if(c[i][k]>=j){
                        ans= Math.max(ans, p[i][k]*j);
                        if(ans== p[i][k]*j && i==W+1 && j==N){
                            ind=k;
                        }
                     //   System.out.println(p[i][k]*j)
                    }else{
                        ans= Math.max(ans, p[i][k]*c[i][k]+dp[i-1][j-c[i][k]]);
                        if(ans== p[i][k]*c[i][k]+dp[i-1][j-c[i][k]] && i==W+1 && j==N){
                            ind=k;
                            if(ind==-1){
                                ind=k;
                            }else{
                                if(p[i][ind]>p[i][k]){
                                    ind=k;
                                }
                            }
                        }
                    }
                }
                dp[i][j]=ans;
            }
        }
        long max= dp[W+1][N];
        int i=W+1;
        for(int k=0; k<K[i]; k++) {
            if (c[i][k] >= N) {
                if( p[i][k] * N == max && p[W+1][ind] >p[W+1][k]){
                    ind=k;
                }
            }else{
                if(max== p[i][k]*c[i][k]+dp[W][N-c[i][k]] && p[W+1][ind]>p[W+1][k]){
                    ind=k;
                }
            }
        }

        System.out.println(dp[W+1][N]);
        System.out.println(p[W+1][ind]);


    }
}
