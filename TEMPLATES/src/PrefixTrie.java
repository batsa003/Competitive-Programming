/**
 * Created by Bat-Orgil on 6/26/2015.
 */
public class PrefixTrie {
    // nextNode[i] stores the childrens of i
    // if nextNode[i][char - 'a'] != 0, exists.
    // Node 1 is the root
    //Nodes are numbered with a new number z everytime we need new node for the Character.
    static int[][] nextNode= new int[2000001][26];
    static int z=1;

    static void add(String s){
        int node=1;
        for(int i=0; i<s.length(); i++){
            int which= nextNode[node][s.charAt(i)-'a'];
            if(which==0){
                z++;
                nextNode[node][s.charAt(i)-'a']=z;
                which=z;
            }
            node=which;
        }
        return;
    }

    //GAME STRATEGY

    /*public boolean canWin(x){
        boolean answer= DP[x];
        if(was[x]) return answer;
        was[x]=true;
        answer=false;
        for all possible next STATE we can reach from the move{
            if( ! canWin(next STATE)){
                answer=true;
            }
        }
        DP[x]=answer;
        return answer;
    }*/

}
