import java.util.Arrays;
import java.util.Random;

/**
 * Created by Bat-Orgil on 6/29/2015.
 */
public class Array {
    static int[] unique(int[] a) {
        Random rng = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int j = rng.nextInt(i + 1);
            int tmp = a[j];
            a[j] = a[i];
            a[i] = tmp;
        }
        Arrays.sort(a);
        int sz = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] != a[sz - 1]) {
                a[sz++] = a[i];
            }
        }
        return Arrays.copyOf(a, sz);
    }
}
