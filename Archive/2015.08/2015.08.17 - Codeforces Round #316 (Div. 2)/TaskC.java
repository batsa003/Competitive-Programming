package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.TreeSet;

public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int m= in.nextInt();
        TreeSet<Integer> set= new TreeSet();
        String s= in.next();
        set.add(0);
        set.add(n+1);
        int cur=0;
        int ans=0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)!='.'){
                set.add(i+1);
                if(cur>=2){
                    ans+= (cur-1);
                }
                cur=0;
            }else{
                cur++;
            }
        }
        if(cur>1){
            ans+=(cur-1);
        }

        //System.out.println("ans= " + ans);
       // char[] ch= ("a" + s).toCharArray();
        StringBuilder sb= new StringBuilder();
        for(int count=0; count<m; count++){
            int x= in.nextInt();
            char c= in.next().charAt(0);
            if(c!='.'){
                if(set.contains(x)){
                    sb.append(ans+ "\n");
                }else{
                    int low= set.lower(x);
                    int high= set.higher(x);
                    ans-= high-low-2;
                    if(x-low>1){
                        ans+= (x-low-2);
                    }
                    if(high-x>1){
                        ans+= (high-x-2);
                    }
                    set.add(x);
                    sb.append(ans + "\n");
                }
            }else{
                if(!set.contains(x)){
                    sb.append(ans+ "\n");
                }else{
                    //changing character to dot;
                    int low= set.lower(x);
                    int high= set.higher(x);
                    ans+= high-low-2;
                    if(x-low>1){
                        ans-= (x-low-2);
                    }
                    if(high-x>1){
                        ans-= (high-x-2);
                    }
                    set.remove(x);
                    sb.append(ans + "\n");
                }
            }
        }
        out.println(sb);
    }
}
