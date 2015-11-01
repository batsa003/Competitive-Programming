package created;

import java.util.Arrays;

public class AB {

    static int calc(char[] str){
        int count=0;
        for(int i=0; i<str.length; i++){
            for(int j=i+1; j<str.length; j++){
                if(str[i]=='A' && str[j]=='B'){
                    count++;
                }
            }
        }
        return count;
    }
    public String createString(int N, int K) {
        for(int i=0; i<= N/2; i++){

            char[] str=  new char[N];
            Arrays.fill(str, 'B');
            for(int j=0; j<i; j++){
                str[j]='A';
            }
            for(int j= N-1; j>= i; j--){
                str[j]='A';
                if(calc(str)==K){
                    String ans="";
                    for(int k=0; k<N; k++){
                        ans+= str[k];
                    }
                    return ans;
                }
                str[j]='B';
            }
        }
        return "";

    }
}
