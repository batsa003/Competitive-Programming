package created;

import java.util.ArrayList;
import java.util.HashSet;

public class OddEvenTree {
    public int[] getTree(String[] x) {
        ArrayList<Integer> odd = new ArrayList();
        ArrayList<Integer> even = new ArrayList();
        int n= x.length;
        for(int i=0; i<n; i++){
            if(x[0].charAt(i)=='E'){
                even.add(i);
            }else{
                odd.add(i);
            }
        }
        int[] a= new int[1];
        a[0]=-1;
        if(odd.isEmpty() || even.isEmpty()){
            return a;
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if( x[i].charAt(j)=='E'){
                    if((odd.contains(i) && odd.contains(j)) || (even.contains(i) && even.contains(j))){
                    }else{
                        return a;
                    }
                }else{
                    if(( odd.contains(i) && even.contains(j)) || (odd.contains(j) && even.contains(i))){

                    }else{
                        return a;
                    }
                }
            }
        }
        int[] ans = new int[2*n-2];
        int cur=0;
        for(int j=0; j<odd.size(); j++){
            ans[cur]= even.get(0);
            ans[cur+1]= odd.get(j);
            cur+=2;
        }
        for(int j=1; j<even.size(); j++){
            ans[cur]= odd.get(0);
            ans[cur+1]= even.get(j);
            cur+=2;
        }
        return ans;
    }
}
