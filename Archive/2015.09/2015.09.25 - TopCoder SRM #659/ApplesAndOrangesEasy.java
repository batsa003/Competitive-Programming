package created;

public class ApplesAndOrangesEasy {
    public int maximumApples(int N, int K, int[] info) {

        boolean[] vis = new boolean[N+1];
        for(int i=0; i<info.length; i++){
            vis[ info[i]]=true;
        }
        int[] cnt= new int[N+1];
        for(int i=1; i<= (N-K+1); i++){
            int cur=0;
            for(int j=i; j<Math.min(N+1,i+K); j++){
                if(vis[j]) cur++;
            }
            cnt[i]=cur;
        }
        int ans=0;
        for(int i=1; i<=N; i++){
            if(vis[i]) {
                ans++;
                continue;
            }
            int maxApple=0;
            for(int j= Math.max(1, i-K+1); j<=i; j++){
                maxApple= Math.max(cnt[j], maxApple);
            }
            if(maxApple < K/2){
                vis[i]=true;
                ans++;
                for(int j= Math.max(1, i-K+1); j<=i; j++){
                    cnt[j]++;
                }
            }
        }
        return ans;
    }
}
