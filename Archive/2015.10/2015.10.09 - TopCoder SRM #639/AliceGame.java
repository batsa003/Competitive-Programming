package created;

import java.util.HashMap;
import java.util.TreeMap;

public class AliceGame {


    public long findMinimumValue(long x, long y) {
        double z= Math.sqrt(x+y);
        if(x==0 && y==0 ) return 0;
        if(y==2) return -1;
        if( Math.abs((z- (long) z))< 0.00000001){
            long t=  (long)z;
            if(x<=t && x%2==1){
                return 1;
            }else if(x<=t && x%2==0){
                if(x>=4){
                    return 2;
                }else if(x==0){
                    return 0;
                }else{
                    return -1;
                }
            }else{
                long val= 2L*t-1;
                long count=0;
                while(x>val){
                    x-= val;
                    val-=2;
                    count++;
                }
                if(x==0){
                    return count;
                }else if( x%2==1){
                    return (count+1);
                }else if( x%2==0 ){
                    return (count+2);
                }
            }
        }

        return -1;
    }
}
