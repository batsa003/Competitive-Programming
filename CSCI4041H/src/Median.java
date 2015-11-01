import java.util.ArrayList;
import java.util.Collections;

public class Median {

    //Helper function that returns a string which represent the array from index start to end (inclusive).
    static String str(int[] ar, int start, int end){
        String s = "[";
        s+= ar[start] + "";
        for(int i=start+1; i<=end; i++){
            s+= "," + ar[i];
        }
        s+= "]";
        return s;
    }

    public static double median(int[] ar1, int[] ar2, int i1, int j1, int i2 , int j2){
        System.out.println("The answer is median of ar1= " + str(ar1,i1,j1) + " and ar2= " + str(ar2, i2, j2));
        // In case if both arrays each have only one element
        if(i1==j1){
            return (double)(ar1[i1]+ar2[i2])/2.0;
        }
        //In case if each array has 2 elements.
        if(j1-i1==1){
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(ar1[i1]); list.add(ar1[j1]); list.add(ar2[i2]); list.add(ar2[j2]);
            Collections.sort(list);
            return (double)(list.get(1)+list.get(2))/2.0;
        }
        //General case
        int mid1= (i1+j1)/2;
        int mid2 = (i2+j2)/2;
        if((j1-i1)%2==1) mid2++; // if the length is even increment second pointer. Just to balance the middle point.
        int X= ar1[mid1];
        int Y = ar2[mid2];
        // Compare the middle point of two arrays
        System.out.println("Comparing " + X + " from ar1 and " + Y +" from ar2");
        if(X==Y){
            return X;
        }else if(X<Y){ // Median must be in the range [X ... Y]
            return median(ar1,ar2,mid1, j1,i2,mid2 );
        }else{      // Median must be in the range [Y ... X]
            return median(ar1,ar2, i1,mid1,mid2, j2);
        }

    }

    public static double median(int[] ar1, int[] ar2){
        return median(ar1,ar2,0,ar1.length-1, 0, ar2.length-1);
    }

    public static void main(String[] args){
        int[] ar1=  {1,1,9,10,20,100,150,150};
        int[] ar2= {4,4,5,6,7,30,40,50};
        System.out.println("The median of the two list is " + median(ar1,ar2));
    }
}
