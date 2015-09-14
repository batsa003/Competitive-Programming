import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Bat-Orgil
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] p = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = in.nextInt();
            }

            boolean[] vis = new boolean[n + 1];
            int[] size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                if (p[i] == i) {
                    out.println("YES");
                    for (int j = 1; j <= n; j++) {
                        if (j != i) {
                            out.println(i + " " + j);
                        }
                    }
                    return;
                }
            }
            for (int node = 1; node <= n; node++) {
                if (!vis[node]) {
                    int count = 1;
                    int cur = p[node];
                    vis[node] = true;
                    while (cur != node) {
                        vis[cur] = true;
                        count++;
                        cur = p[cur];
                    }
                    size[cur] = count;
                    if (count > 1 && count % 2 == 1) {
                        out.println("NO");
                        return;
                    }
                }
            }
            for (int i = 1; i <= n; i++) {
                if (size[i] == 2) {
                    int u = i;
                    int pu = p[u];
                    out.println("YES");
                    out.println(u + " " + pu);
                    for (int j = 1; j <= n; j++) {
                        if (j != u && j != pu && size[j] > 1) {
                            out.println(u + " " + j);
                            int cur = p[j];
                            int ind = 1;
                            while (cur != j) {
                                if (ind % 2 == 1) {
                                    out.println(p[u] + " " + cur);
                                } else {
                                    out.println(u + " " + cur);
                                }
                                cur = p[cur];
                                ind++;
                            }
                        }
                    }
                    return;
                }
            }
            out.println("NO");


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

    }
}

