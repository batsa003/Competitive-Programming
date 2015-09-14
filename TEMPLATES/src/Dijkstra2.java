import java.util.*;

/**
 * Cited from, https://github.com/joker23/ACM/blob/master/poj/3255.java
 */
public class Dijkstra2 {
    static final int INF= Integer.MAX_VALUE;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n= in.nextInt();
        int m=in.nextInt();
        PriorityQueue<Node> pq = new PriorityQueue();
        List<Node>[] adj = new List[n+1];
        for(int i=1; i<=n; i++){
            adj[i] = new ArrayList<Node>();
        }

        int[][] dist= new int[2][n+1];
        Arrays.fill(dist[0],INF);
        Arrays.fill(dist[1],INF);
        boolean[] visit1 = new boolean[n+1];
        boolean[] visit2= new boolean[n+1];

        dist[0][1]=0; // 0 is source;

        for(int i=0; i<m; i++){
            int a= in.nextInt();
            int b= in.nextInt();
            int c= in.nextInt();
            adj[a].add(new Node(b,c));
            adj[b].add(new Node(a,c));
        }

        pq.add(new Node(1,0));
        Node curr; int weight;

        while( !pq.isEmpty() ){
            curr= pq.poll();
            //System.out.println("PUlls out a curr with : " + curr.a + " " + curr.w);
            if(visit2[curr.a]) continue;
            for(Node next : adj[curr.a]){
                weight= next.w+curr.w;
                if(dist[0][next.a] > weight){               // if smaller than both shorter paths
                    dist[1][next.a]= dist[0][next.a];
                    dist[0][next.a]= weight;
                }else{                                  // if the weight is bigger than shortest,
                                                        // but still smaller than second, we replace.
                    dist[1][next.a]=Math.min(dist[1][next.a],weight);
                }

                if(!visit1[curr.a]) pq.add(new Node(next.a,weight)); // tentative shortest path
                                                                    // for the Node next is weight
            }
            if( ! visit1[curr.a]) visit1[curr.a]=true; // if it is visited for first time
            else visit2[curr.a]=true;                  // if we visited the next second time
                                                        // we don't have to visit it again
                                                        // the claim is that if the node is
        }
        System.out.println(dist[1][n]);
    }

    public static class Node implements Comparable<Node>{

        @Override
        public int compareTo(Node o) {
            return this.w-o.w;
        }

        int a,w;

        public Node(int aa, int ww){
            a=aa;
            w=ww;
        }

    }

}
