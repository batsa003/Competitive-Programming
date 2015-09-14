package created;

import utils.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {
    ArrayList<Edge>[] G;
    boolean[] used;
    int[] tin, fup;
    int timer = 0;
    boolean[] bridge;
    boolean[] copy;

    void dfs(int v, int p) {
        used[v] = true;
        tin[v] = fup[v] = timer++;

        for (Edge e : G[v]) {
            int to = e.to;
            if (to == p)
                continue;
            if (used[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    bridge[e.ind] = true;
                }
            }
        }
    }

    class Edge {

        public Edge(int to, int cost, int ind) {
            this.to = to;
            this.cost = cost;
            this.ind = ind;
        }

        int to, cost, ind;
    }


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int m =in.nextInt();
        int s= in.nextInt()-1;
        int t= in.nextInt()-1;

        int[] from =new int[m];
        int[] to = new int[m];
        int[] cost= new int[m];
        for(int i=0; i<m; i++){
            from[i]=in.nextInt()-1;
            to[i]=in.nextInt()-1;
            cost[i]=in.nextInt();
        }

        Dijkstra d1= new Dijkstra(n);
        Dijkstra d2= new Dijkstra(n);

        TreeMap<Struct, Integer> map =new TreeMap();

        copy= new boolean[m];

        for(int i=0; i<m; i++){
            d1.addEdge(from[i],to[i],cost[i]);
            d2.addEdge(to[i],from[i],cost[i]);

            Struct str= new Struct(from[i],to[i],cost[i]);
            if(map.containsKey(str)){
                copy[i]=true;
                copy[map.get(str)]=true;
                //System.out.println("i, get= " + i + " " + map.get(str));
            }else{
                map.put(str,i);
            }
        }

        long[] dist1= d1.dijkstra(s);
        long[] dist2= d2.dijkstra(t);


        tin= new int[n];
        fup = new int[n];
        used= new boolean[n];
        timer=0;
        bridge= new boolean[m];
        G = new ArrayList[n];

        for(int i=0; i<n; i++) G[i]= new ArrayList<Edge>();

        long min= dist1[t];
        for(int i=0; i<m; i++){
            int x= from[i];
            int y= to[i];
            if( dist1[x] + cost[i] + dist2[y] == min){
                G[x].add( new Edge(y,cost[i],i));
                G[y].add( new Edge(x, cost[i],i));
            }
        }

        dfs(s,-1);

        for(int i=0; i<m; i++){

            if( bridge[i] && !copy[i]){
                out.println("YES");
            }else if( dist1[from[i]] + 1 + dist2[to[i]] < min){
                out.println("CAN " + (cost[i]-( min-1-dist1[from[i]]- dist2[to[i]])));
            }else{
                out.println("NO");
            }
        }

    }
}

class Struct implements Comparable<Struct> {

    public Struct(int a, int b, int cost) {
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    public int compareTo(Struct s) {
        if (a == s.a)
            return b == s.b ? cost - s.cost : b - s.b;
        else
            return a - s.a;
    }

    int a, b, cost;
}

class Dijkstra {

    class Edge {
        int to;
        long cost;
        Edge(int to, long cost) {
            this.to   = to;
            this.cost = cost;
        }
    }
    class Pair implements Comparable<Pair> {
        long first;
        int second;
        Pair(long first, int second) {
            this.first = first;
            this.second = second;
        }
        @Override
        public int compareTo(Pair o) {
            if (this.first == o.first) return 0;
            return (this.first < o.first) ? -1 : 1;
        }
    }
    int n;
    long INF = (long)1e15;
    ArrayList<Edge>[] G;
    int[] P;

    // n is the number of nodes
    // nodes must be 0 ... n-1
    Dijkstra(int n) {
        this.n = n;
        G = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<Edge>();
        }
        P = new int[n];
    }

    void addEdge(int from, int to, long cost) {
        G[from].add(new Edge(to,cost));
    }

    // returns an array dist
    // s.t. dist[t] = minimum distance from s to t
    long[] dijkstra(int s) {
        PriorityQueue<Pair> que = new PriorityQueue<Pair>();
        long[] dist  = new long[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        que.add(new Pair(0,s));
        while (!que.isEmpty()) {
            Pair p = que.poll();
            int v = p.second;
            if (dist[v] < p.first) continue;
            for (int i = 0; i < G[v].size(); i++) {
                int u = G[v].get(i).to;
                long alt = dist[v] + G[v].get(i).cost;
                if (alt < dist[u]) {
                    P[u] = v;
                    dist[u] = alt;
                    que.add(new Pair(alt,u));
                }
            }
        }
        return dist;
    }
}