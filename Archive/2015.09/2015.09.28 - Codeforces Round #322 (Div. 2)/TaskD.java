package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskD {

    static String Print(int[][] board){
        StringBuilder sb= new StringBuilder();
        for(int i=1; i<board.length ;i++){
            for(int j=1; j<board[i].length; j++){
                if(board[i][j]==0){
                    sb.append("A");
                }else if(board[i][j]==1){
                    sb.append("B");
                }else{
                    sb.append("C");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int[] ar= new int[6];
        int area=0;
        Pair[] points= new Pair[3];
        for(int i=0; i<6; i++){
            ar[i] = in.nextInt();
            if(i%2==1){
                points[i/2]= new Pair(ar[i-1],ar[i]);
                area+= ar[i]*ar[i-1];
            }
        }
        int side=1;
        for(; side<=1000; side++){
            if(side*side==area){
                int[][] board= new int[side+1][side+1];
                for(int i=0; i<3; i++){ // i-th company
                    Pair p = points[i];
                    if(p.x==side){
                        int X= side;
                        int Y= side-p.y;

                        int[][] rest=  new int[2][2];
                        int k=0;
                        for(int j=i; j<(i+3); j++){
                            if(j!=i){
                                rest[k][0] = points[j%3].x;
                                rest[k][1]= points[j%3].y;
                                k++;
                            }
                        }
                        // 1 ,2 ,0
                        // rest[0] -> (i+1)th
                        // rest[1] -> (i+2)th

                        for(int a=0; a<2; a++){
                            for(int b=0; b<2; b++){
                                if( rest[0][a]== rest[1][b] && rest[0][a]==Y && (rest[0][(a+1)%2]+rest[1][(b+1)%2])==X){
                                    for(int z=1; z<=p.y; z++){
                                        for(int x=1; x<=side; x++){
                                            board[z][x]= i%3;
                                        }
                                    }
                                    for(int z= p.y+1; z<=side; z++){
                                        for(int x=1; x<=side; x++){
                                            if(x<= rest[0][(a+1)%2]){
                                                board[z][x]=(i+1)%3;
                                            }else{
                                                board[z][x]= (i+2)%3;
                                            }
                                        }
                                    }
                                    out.println(side);
                                    out.print(Print(board));
                                    return;
                                }
                            }
                        }

                        for(int a=0; a<2; a++){
                            for(int b=0; b<2; b++){
                                if( rest[0][a]== rest[1][b] && rest[0][a]==X && (rest[0][(a+1)%2]+rest[1][(b+1)%2])==Y){
                                    for(int z=1; z<=p.y; z++){
                                        for(int x=1; x<=side; x++){
                                            board[z][x]= i%3;
                                        }
                                    }
                                    for(int z= p.y+1; z<=side; z++){
                                        for(int x=1; x<=side; x++){
                                            if((z-p.y)<= rest[0][(a+1)%2]){
                                                board[z][x]=(i+1)%3;
                                            }else{
                                                board[z][x]= (i+2)%3;
                                            }
                                        }
                                    }
                                    out.println(side);
                                    out.print(Print(board));
                                    return;
                                }
                            }
                        }

                    }else if(p.y==side){
                        int X= side;
                        int Y= side-p.x;

                        int[][] rest=  new int[2][2];
                        int k=0;
                        for(int j=i; j<(i+3); j++){
                            if(j!=i){
                                rest[k][0] = points[j%3].x;
                                rest[k][1]= points[j%3].y;
                                k++;
                            }
                        }
                        // 1 ,2 ,0
                        // rest[0] -> (i+1)th
                        // rest[1] -> (i+2)th

                        for(int a=0; a<2; a++){
                            for(int b=0; b<2; b++){
                                if( rest[0][a]== rest[1][b] && rest[0][a]==Y && (rest[0][(a+1)%2]+rest[1][(b+1)%2])==X){
                                    for(int z=1; z<=p.x; z++){
                                        for(int x=1; x<=side; x++){
                                            board[z][x]= i%3;
                                        }
                                    }
                                    for(int z= p.x+1; z<=side; z++){
                                        for(int x=1; x<=side; x++){
                                            if(x<= rest[0][(a+1)%2]){
                                                board[z][x]=(i+1)%3;
                                            }else{
                                                board[z][x]= (i+2)%3;
                                            }
                                        }
                                    }
                                    out.println(side);

                                    out.print(Print(board));
                                    return;
                                }
                            }
                        }

                        for(int a=0; a<2; a++){
                            for(int b=0; b<2; b++){
                                if( rest[0][a]== rest[1][b] && rest[0][a]==X && (rest[0][(a+1)%2]+rest[1][(b+1)%2])==Y){
                                    for(int z=1; z<=p.x; z++){
                                        for(int x=1; x<=side; x++){
                                            board[z][x]= i%3;
                                        }
                                    }
                                    for(int z= p.x+1; z<=side; z++){
                                        for(int x=1; x<=side; x++){
                                            if((z-p.x)<= rest[0][(a+1)%2]){
                                                board[z][x]=(i+1)%3;
                                            }else{
                                                board[z][x]= (i+2)%3;
                                            }
                                        }
                                    }
                                    out.println(side);
                                    out.print(Print(board));
                                    return;
                                }
                            }
                        }
                    }
                }
                out.println(-1);
                return;
            }
        }
        out.println(-1);
    }

    static class Pair{
        int x,y;
        Pair(int xx, int yy){
            x=xx;
            y=yy;
        }
    }
}
