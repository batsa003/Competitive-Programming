/**
 * Created by Bat-Orgil on 8/21/2015.
 */
/*Let T be the text, and P be the pattern. We're going to find the number of occurence of P in the text T.
* Let T be given as an array of integers ( with length n), and P be an array of integers(with length m).
* KMP is going to return number of occurences.
* We can also keep track of the ending indices of the occurences, if required.
*
* */



public class KMP {

    static int getKMP(int[] P, int[] T) {
        int p=0;
        int hit=0;
        int[] kmp= kmpTable(P);
        for(int i=0; i<T.length; i++){
            while(p>=0 && P[p] != T[i]){
                p=kmp[p];
            }
            if(++p == P.length){
                // P matches T[i-m+1, ... i]
                p=kmp[p];
                hit++;
            }
        }
        return hit;

    }

    static int[] kmpTable(int[] str){
        int n= str.length;
        int[] kmp =new int[n+1];
        kmp[0]= -1; kmp[1]=0;
        for(int i=2,j=0; i<=n; i++){
            while(j>0 && str[i-1] != str[j]){
                j=kmp[j];
            }
            kmp[i]= str[i-1]==str[j] ? ++j : 0;
        }
        return kmp;
    }
}
