import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class POJ3497 {
    static InputReader in;
    static PrintWriter out;
    static class Pair {
        int p;
        int q;
        Pair(int ff, int ss){
            p=ff;
            q=ss;
        }

    }

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        int T=in.nextInt();
        for(int aa=0; aa<T; aa++){
            int n= in.nextInt();
            int b= in.nextInt();
            HashMap<String, HashSet<Pair>> map= new HashMap();
            for(int i=0; i<n; i++){
                String s= in.next();
                String l= in.next();
                Pair pair = new Pair(in.nextInt(),in.nextInt());
                if(map.containsKey(s)){
                    HashSet temp = map.get(s);
                    temp.add(pair);
                    map.put(s,temp);
                }else{
                    HashSet<Pair> set= new HashSet();
                    set.add(pair);
                    map.put(s,set);
                }
            }

            int low=0;
            int high=1000000001;

            While:
            while((high-low)>1){
                int mid= (low+high)/2;
                int budget=0;
                for(String key: map.keySet()){
                    HashSet<Pair> set = map.get(key);
                    int min = Integer.MAX_VALUE;
                    for(Pair pair : set){
                        if(pair.q >= mid && pair.p<min){
                            min=pair.p;
                        }
                    }
                    if(min==Integer.MAX_VALUE){
                        high=mid;
                        continue While;
                    }
                    budget+=min;
                }
                if(budget<=b){
                    low=mid;
                }else{
                    high=mid;
                }
            }

            out.println(low);
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
