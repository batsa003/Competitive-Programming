package created;

public class WaitingForBus {
    static double MAX= Double.MAX_VALUE/100000;
    static double max=  MAX/100.0;

    public double whenWillBusArrive(int[] time, int[] prob, int s) {
        if(s==0){
            return 0;
        }
        double[] p = new double[s];
        p[0]=1;
        double ans=0;
        for(int i=0; i<s; i++) {
                for(int k=0; k<time.length; k++) {
                    if (i + time[k] >= s) {
                        ans += (i + time[k] - s) * p[i] * prob[k] / 100;
                    } else {
                        p[i + time[k]] += p[i] * prob[k]  / 100;
                    }
                }
        }

        return ans;
    }
}
