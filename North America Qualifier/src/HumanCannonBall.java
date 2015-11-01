import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

@SuppressWarnings("unchecked")
public class HumanCannonBall {
    static InputReader in;
    static PrintWriter out;

    static double dist(double x, double y, double xx, double yy){
        return Math.sqrt((x-xx)*(x-xx) + (y-yy)*(y-yy));
    }

    public static void main(String[] args) {
        in= new InputReader(System.in);
        out= new PrintWriter(System.out,true);
        double X= in.nextDouble();
        double Y= in.nextDouble();
        double XX= in.nextDouble();
        double YY= in.nextDouble();
        int p =in.nextInt();
        double[] x= new double[p+2];
        double[] y= new double[p+2];
        for(int i=1; i<=p; i++){
            x[i]= in.nextDouble();
            y[i]= in.nextDouble();
        }
        x[0]=X;
        y[0]=Y;
        x[p+1]=XX;
        y[p+1]=YY;
        double[][] w= new double[p+2][p+2];
        for(int i=0; i<w.length; i++){
            for(int j=0; j<w.length;j++) {
                if (i != j) {
                    w[i][j] = dist(x[i], y[i], x[j], y[j]) / 5.0;
                }
            }
        }
        for(int i=1; i<=p; i++){
            for(int j=0; j<p+2; j++){
                if(i==j) continue;
                double d= Math.abs( (dist(x[i],y[i],x[j],y[j])-50));
                w[i][j]= Math.min(w[i][j], 2+d/5.0);
            }
        }
        ArrayList<Edge> edges=  new ArrayList();

        for(int i=0; i<w.length; i++){
            for(int j=0; j<w.length; j++){
                if(i!=j){
                    edges.add(new Edge(i+1,j+1,w[i][j]));
                }
            }
        }
        Edge[] e= edges.toArray(new Edge[edges.size()]);
        double ans= bellman(e,1,p+2,p+2);
        out.println(String.format("%.6f",ans));
        out.close();
        System.exit(0);

    }


    static double inf = 1000010;

    static double bellman(Edge[] graph, int start, int end, int n) {//-1 if negative cycle, //-2 if inf
        double[] dist = new double[n+1];
        fill(dist, inf);
        dist[start] = 0;

        for(int i=1; i<=n; i++) {
            for(Edge e: graph) {
                dist[e.to] = min(dist[e.from] + e.weight, dist[e.to]);
            }
        }

        if(hasNegCycle(graph, dist, end)) {
            return -1;
        } else if(dist[end] == inf){
            return  -2;
        } else {
            return dist[end];
        }
    }

    static boolean hasNegCycle(Edge[] graph, double[] dist, int end) {
        double curr = dist[end];

        for(Edge e: graph) {
            dist[e.to] = min(dist[e.from] + e.weight, dist[e.to]);
        }

        return dist[end] < curr;
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge e) {
            return (int) Math.signum(this.weight - e.weight);
        }
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}