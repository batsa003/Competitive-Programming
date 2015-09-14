package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class TaskA {
    static class Pair implements Comparable<Pair>{
        int val;
        int ind=0;
        int lastIndex;

        public Pair(int v, int i){
            val=v;
            ind= i;
        }

        public Pair(int v, int i,int last){
            val=v;
            lastIndex= last;
            ind=i;
        }

        /*public String toString(){
            return ("(" + val + "," + ind + ")");
        }*/

        @Override
        public int compareTo(Pair o) {
            if(val!= o.val){
                return val-o.val;
            }else{
                return ind-o.ind;
            }
        }
    }
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int k = in.nextInt();
        ArrayList<Pair>[] list = new ArrayList[59049]; // list of indices with given trait 3^10
        for(int i=0; i<list.length; i++){
            list[i]= new ArrayList<Pair>();
        }
        int[] trait= new int[n];
        int[] score= new int[n];
        for(int i=0; i<n; i++){
            score[i]=in.nextInt();
            int l=in.nextInt();
            for(int j=0; j<l; j++){
                trait[i] |= 1<<(in.nextInt()-1);
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<(1<<k); j++){
                int mask=0;
                for(int ind=0; ind<k; ind++){
                    if((j & (1<<ind))!=0){
                        //fix ind-th bit;
                        if((trait[i]&(1<<ind))==0 ){
                            mask+=(int)Math.pow(3,ind);
                        }else{
                            mask+= 2*(int)Math.pow(3,ind);
                        }
                    }
                }
                if(!list[mask].contains(score[i])){
                    list[mask].add(new Pair(score[i],i));
                }
            }
        }
        //There are at most 10^7 numbers
        for(int i=0; i<list.length; i++){
            Collections.sort(list[i]);
            ArrayList<Pair> p= new ArrayList();
            for(int j=0; j<list[i].size(); j++){
                if(( j== list[i].size()-1)|| (list[i].get(j).val != list[i].get(j+1).val)){
                    p.add(new Pair(list[i].get(j).val,0,j));
                }
            }
            list[i]=p;
        }

        int m =in.nextInt();
        StringBuilder sb= new StringBuilder();
        for(int count=0; count< m; count++){
            int a= in.nextInt()-1;
            int tr= 0;
            int t= in.nextInt();
            for(int i=0; i<t; i++){
                int h= in.nextInt()-1;
                if((trait[a]&(1<<h))==0){
                    tr+= Math.pow(3,h);
                }else{
                    tr+= (int)Math.pow(3,h)*2;
                }
            }
            ArrayList<Pair> ar= list[tr]; // find the score of creature a and print the index
            /*System.out.println("m=" + (count+1));
            for(Pair c : ar) {
                System.out.print("(" + c.val + "," + c.ind + "," + c.lastIndex +")\n");
            }*/

            /*int z=Collections.binarySearch(ar,new Pair(score[a],a));
            while(z<(ar.size()-1)&& ar.get(z+1).val==ar.get(z).val){
                z++;
            }
            sb.append((ar.size()- z)+"\n");*/
            int z=Collections.binarySearch(ar, new Pair(score[a],0));
            //System.out.println(z);
            sb.append(((ar.get(ar.size()-1).lastIndex+1 - ar.get(z).lastIndex))+"\n");
        }
        out.println(sb);
    }
}
