package created;

import java.util.Arrays;
import java.util.LinkedList;

public class TrianglesContainOrigin {

    public long count(int[] x, int[] y) {
        long ans1=0;
        long ans2=0;
        LinkedList<Pair> A = new LinkedList();
        LinkedList<Pair> B= new LinkedList();
        int a=0;
        int b=0;
        for(int i=0; i<x.length; i++){
            if(x[i]<=0){
                A.add(new Pair(x[i],y[i],a));
                a++;
            }else{
                B.add(new Pair(x[i],y[i],b));
                b++;
            }
        }
        int[] numPos1 = new int[a];
        int[] numPos2 = new int[b];
        boolean[][] pos = new boolean[a][b];
        for(Pair point1 : A){
            int p=0;
            for(Pair point2 : B){
                double x1= point1.x;
                double x2= point2.x;
                double y1= point1.y;
                double y2= point2.y;
                if((y2- (y2-y1)/(x2-x1)*x2)>0){
                    p++;
                    pos[point1.i][point2.i]=true;
                    numPos2[point2.i]++;
                }
            }
            numPos1[point1.i]=p;
        }
       //System.out.println(Arrays.toString(numPos1));
      //  System.out.println(Arrays.toString(numPos2));
        for(Pair point1 : A){
            for(Pair point2 : B){
                int i1= point1.i;
                int i2 = point2.i;
                double x1= point1.x;
                double x2= point2.x;
                double y1= point1.y;
                double y2= point2.y;
                if(pos[i1][i2]){
                    ans1+= (a-numPos2[i2]);
                    ans2+= (b-numPos1[i1]);
                }else{
                    ans1+= numPos2[i2];
                    ans2+= numPos1[i1];
                }
            }
        }
        return (ans1/2)+(ans2/2);
    }

    static class Pair {
        int x,y,i;
        Pair(int X,int Y, int ind){
            x=X;
            y=Y;
            i=ind;
        }
    }
}
