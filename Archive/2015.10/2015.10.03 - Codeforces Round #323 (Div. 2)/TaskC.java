package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.TreeMap;

public class TaskC {

    public static int gcd(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return gcd(b, a % b);
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        TreeMap<Integer,Integer > map  = new TreeMap();
        for(int i=0; i<n*n; i++){
            int c= in.nextInt();
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }
        int[] nums = new int[n];
        for(int i=0; i<n; i++){
            int highest= map.lastKey();
            if(map.get(highest)==1){
                map.remove(highest);
            }else{
                map.put(highest, map.get(highest)-1);
            }
            out.print(highest + " ");
            nums[i]=highest;
            for(int j=0; j<i; j++){
                int gcd = gcd(nums[j],nums[i]);
               // System.out.println(gcd);
                if(map.get(gcd)==2){
                    map.remove(gcd);
                }else{
                    map.put(gcd, map.get(gcd)-2);
                }
            }
        }

    }
}
