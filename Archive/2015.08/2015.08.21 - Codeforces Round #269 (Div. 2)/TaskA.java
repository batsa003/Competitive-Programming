package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        HashMap<Integer,Integer> m = new HashMap();
        for(int i=0; i<6; i++){
            int s= in.nextInt();
            if(m.containsKey(s)){
                m.put(s,m.get(s)+1);
            }else{
                m.put(s,1);
            }
        }
        for(int key : m.keySet()){
            if(m.get(key)>=4){
                if(m.get(key)==4){
                    m.remove(key);
                }else{
                    m.put(key,m.get(key)-4);
                }

                for(int k : m.keySet()){
                    if(m.get(k)==2){
                        out.println("Elephant");
                        return;
                    }
                }
                out.println("Bear");
                return;
            }
        }
        out.println("Alien");


    }
}
