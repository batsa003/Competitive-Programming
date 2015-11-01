import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Bat-Orgil on 10/25/2015.
 */
public class kmp {

    // Compute_pi function implemented using the pseudocode from the book.
    static int[] compute_pi(char[] P){
        int m= P.length-1; // char at 0 is excluded because it is 1 indexed.
        int[] pi = new int[m+1];
        pi[1]=0;
        int k=0;
        for(int q=2; q<=m; q++){
            while (k>0 && P[k+1] != P[q]){
                k=pi[k];
            }
            if(P[k+1]==P[q]) {
                k++;
            }
            pi[q]=k;
        }
        return pi;
    }

    //kmp implemented using pseudocode from the book.
    static int KMP(String t, String p){
        int n= t.length();
        int m= p.length();
        // Since string is 0 indexed, let's make it 1 indexed.
        char[] T= (" " + t).toCharArray();
        char[] P = (" " + p).toCharArray();
        int[] pi = compute_pi(P);
        int q=0; //number of characters matched
        for(int i=1; i<=n; i++){ // scan from left to right
            while(q>0 && P[q+1]!=T[i]){
                q=pi[q];
            }
            if(P[q+1]==T[i]){
                q++;        // next character matches
            }               // if all of P matched
            if(q==m){
                //Pattern occurs with starting index (i-m+1);
                q=pi[q];
                //Return the first occurence index.
                return (i-m+1);
            }
        }

        //No match found
        return -1;
    }

    public static void main(String[] args){
        File f= new File("string_info.txt");

        String t="";
        String p="";
        try {
            Scanner in = new Scanner(f);
            t= in.nextLine();
            p=in.nextLine();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("START= " + KMP(t,p));

    }
}
