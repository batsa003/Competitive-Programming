package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k =in.nextInt();
        long[] ar= new long[n];
        for(int i=0; i<n; i++){
            ar[i]=in.nextInt();
        }

        if(n==2){
            out.println(0);
            return;
        }

        HashMap<Long, Integer> map = new HashMap();

        for(int i=2; i<n; i++){
            if(map.containsKey(ar[i])){
                map.put(ar[i],map.get(ar[i])+1);
            }else{
                map.put(ar[i],1);
            }
        }
        long count=0;
        HashMap<Long, Integer> prev= new HashMap();
        prev.put(ar[0],1);
        for(int i=1; i<(n-1); i++){
           // System.out.println("p"+ prev);
            //System.out.println("m"+ map);
            if( ar[i]%k==0 && prev.containsKey(ar[i]/k) && map.containsKey(ar[i]*k)){
                count+= (long)prev.get(ar[i]/k) * (long) map.get(ar[i]*k);
            }
            if(map.get(ar[i+1])==1){
                map.remove(ar[i+1]);
            }else{
                map.put(ar[i+1],map.get(ar[i+1])-1);
            }
            if(prev.containsKey(ar[i])){
                prev.put(ar[i],prev.get(ar[i])+1);
            }else{
                prev.put(ar[i],1);
            }
        }
        out.println(count);
    }
}
