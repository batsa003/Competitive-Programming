import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


/*
* 1. The problem gives a graph, and we have to find the second shortest path from 1 to N.
* 2. For the problem we convert it into 0, ... , N-1.
*
*
*
* */


public class SecondShortest {
    static final int inf= Integer.MAX_VALUE;

    static class Edge{
        int f,to,w;

        Edge(int ff, int too, int ww){
            f=ff;
            to=too;
            w=ww;
        }
    }

    static class Node implements Comparable<Node>{
        int d,i;

        Node(int dist,  int index){
            d=dist;
            i=index;
        }

        @Override
        public int compareTo(Node o) {
            return d-o.d;
        }
    }



    public static void main(String[] args){
        Scanner in= new Scanner(System.in);

        int N =in.nextInt();
        int R= in.nextInt();

        ArrayList<Edge>[] G= new ArrayList[N];
        for(int i=0; i<N; i++){
            G[i]=new ArrayList<Edge>();
        }

        for(int i=0; i<R; i++){
            int a= in.nextInt()-1;
            int b= in.nextInt()-1;
            int w= in.nextInt();
            G[a].add(new Edge(a,b,w));
            G[b].add(new Edge(b,a,w));
        }

        int[] dist1= new int[N];
        int[] dist2= new int[N];
        Arrays.fill(dist1, inf);
        Arrays.fill(dist2, inf);

        dist1[0]=0;

        PriorityQueue<Node> pq= new PriorityQueue();
        pq.add(new Node(0,0));

        while(!pq.isEmpty()){
            Node cur= pq.poll();
            int u = cur.i;
            int d= cur.d;
            if(d > dist1[u]) continue;

           // dist1[u]=d;
            for(Edge neighbor: G[u]){
                int v= neighbor.to;
                int w= neighbor.w;
                if( dist1[u]+w < dist1[v]){
                    dist1[v]= dist1[u]+w;
                    pq.add(new Node(dist1[v], v));
                }
            }
        }

        for(int i=0; i<N; i++){
            if(dist1[i]!=inf) {
                for (Edge neighbor : G[i]) {
                    int j = neighbor.to;
                    int w = neighbor.w;
                    if (dist2[j] > dist1[i] + w && (dist1[i]+w)>dist1[j]) {
                        dist2[j] = dist1[i] + w;
                    }
                }
            }
        }

        pq.clear();
        for(int i=0; i<N; i++){
            pq.add(new Node(dist2[i],i));
        }

        while(!pq.isEmpty()) {
            Node cur= pq.poll();
            int u = cur.i;
            int d= cur.d;
            if(d> dist2[u]) continue;

            dist2[u]=d;
            for(Edge neighbor: G[u]){
                int v= neighbor.to;
                int w= neighbor.w;
                if((dist2[u]+w) < dist2[v] && (dist2[u]+w)>dist1[v]){
                    dist2[v]= dist2[u]+w;
                    pq.add(new Node(dist2[v],v));
                }
            }
        }

        System.out.println(dist2[N-1]);

    }

}

