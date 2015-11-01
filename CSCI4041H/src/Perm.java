/**
 * Created by Bat-Orgil on 10/9/2015.
 */
public class Perm {
    static char[] str;
    static int n;

    static void rec(int i){
        if(i==n){
            for(int j=0; j<n; j++){
                System.out.print(str[j]);
            }
            System.out.println();
        }
        for(int j=i; j<n; j++){
            char temp = str[j];
            str[j]=str[i];
            str[i]=temp;
            rec(i+1);
            temp = str[j];
            str[j]=str[i];
            str[i]=temp;
        }
    }

    static void print(String s){
        str= s.toCharArray();
        n= str.length;
        rec(0);
    }

    public static void main(String[] args){

    }
}
