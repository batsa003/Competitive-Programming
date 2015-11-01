import java.util.Arrays;

/**
 * Created by Bat-Orgil on 9/27/2015.
 */
public class CountSort {

    static int[] countSort(int n, int[] A, int k){
        // First, it is going to keep track of the frequency of each value in A
        int[] C= new int[k+1];

        System.out.println("Original array A= " + Arrays.toString(A));
        for(int j=0; j<A.length; j++){
            //Number of elements with value equal to A[j] is incremented by one
            C[A[j]]++;
        }
        for(int i=0; i<C.length; i++){
            System.out.println("Count of " + i + " in the array A is " + C[i]);
        }

        // By calculating prefix sum of C array, C[i] = Number of elements less than or equal to i
        for(int j=0; j<=k; j++){
            if(j>0) C[j]+=C[j-1];
            System.out.println("The number of elements less than or equals to " + j + " is " + C[j]);
        }

        int[] result = new int[A.length];

        for(int i= A.length-1; i>=0; i--){
            // Put the number A[i] in the proper position, which is (C of A[i]) - 1
            result[C[A[i]]-1]=A[i];
            // Since A[i] is placed, C should be updated
            C[A[i]]--;
            System.out.println("Current result= " + Arrays.toString(result));
        }

        System.out.println("Final result = " + Arrays.toString(result));
        return result;
    }

    public static void main(String[] args){
        int[] ar= {7,1,3,1,2,4,5,7,2,4,3};
        int[] result= countSort(ar.length,ar,10);

    }
}
