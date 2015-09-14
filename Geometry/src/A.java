import java.util.Arrays;
import java.util.Scanner;

import java.text.DecimalFormat;

/**
 * Created by Bat-Orgil on 9/3/2015.
 */
public class A {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        double[][] ar= new double[3][2];
        for(int i=0; i<3; i++){
            for(int j=0; j<2; j++){
                ar[i][j]=in.nextDouble();
            }
        }
        double x1= ar[0][0]+ar[1][0]-ar[2][0];
        double y1= ar[0][1]+ar[1][1]-ar[2][1];
        double x2= ar[1][0]+ar[2][0]-ar[0][0];
        double y2= ar[1][1]+ ar[2][1]-ar[0][1];
        double x3= ar[2][0]+ar[0][0]-ar[1][0];
        double y3= ar[2][1]+ar[0][1]-ar[1][1];
        Pair[] p= new Pair[3];
        p[0]= new Pair(x1,y1);
        p[1]= new Pair(x2,y2);
        p[2]= new Pair(x3,y3);
        Arrays.sort(p);
        for(int i=0; i<3; i++){
            int a= String.valueOf(p[i].x).indexOf(".");
            int b= String.valueOf(p[i].y).indexOf(".");
            String s1= String.valueOf(p[i].x) + "0000";
            String s2= String.valueOf(p[i].y) + "0000";
            System.out.println(s1.substring(0,a+5)+ " " +s2.substring(0,b+5));
        }

    }

    static class Pair implements Comparable<Pair>{
        double x,y;

        Pair(double xx, double yy){
            x=xx;
            y=yy;
        }


        @Override
        public int compareTo(Pair o) {
            if(x!=o.x){
                if(x>o.x){
                    return 1;
                }else if(x<o.x){
                    return -1;
                }else{
                    return 0;
                }
            }else{
                if(y>o.y){
                    return 1;
                }else if (y<o.y){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
    }
}
