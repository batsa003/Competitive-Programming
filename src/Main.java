import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {


            String s = in.next();
            BigInteger a = BigInteger.valueOf(in.nextInt());
            BigInteger b = BigInteger.valueOf(in.nextInt());

            if (s.length() == 1) {
                out.println("NO");
                return;
            }
            for (int i = 0; i < s.length() - 1; i++) {
                BigInteger one = new BigInteger(s.substring(0, i + 1));
                BigInteger two = new BigInteger(s.substring(i + 1));
                if (one.mod(a).longValue() == 0 && two.mod(b).longValue() == 0) {
                    out.println("YES");
                    out.println(one.toString());
                    out.println(two.toString());
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

