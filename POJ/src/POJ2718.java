import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class POJ2718 {
    static Scanner in;
    static PrintWriter out;

    public static void main(String[] args) {
        in= new Scanner(System.in);
        out= new PrintWriter(System.out,true);
        int t= Integer.parseInt(in.nextLine());
        for(int c=0; c<t; c++){
            String[] nums= in.nextLine().split(" ");
            int[] digs= new int[nums.length];
            for(int i=0; i<nums.length; i++){
                digs[i]= Integer.parseInt(nums[i]);
            }
            if(digs.length%2==1){
                String a="",b= "";
                if(digs[0]==0) {
                    b += nums[1];
                    b += nums[0];
                }else{
                    b+= nums[0];
                    b+=nums[1];
                }
                int i=2;
                while(b.length()<=digs.length/2){
                    b+=nums[i];
                    i++;
                }
                i=digs.length-1;
                while(a.length()<digs.length/2){
                    a+= nums[i];
                    i--;
                }
                out.println(Integer.parseInt(b)-Integer.parseInt(a));
            }else{
                if(digs.length==2){
                    out.println(digs[1]-digs[0]);
                    continue;
                }
                int min= Integer.MAX_VALUE;
                for(int i=1; i<digs.length; i++){
                    // if i is the highest one
                    if(i==1 && digs[i-1]==0){
                        continue;
                    }
                    String a="",b= "";
                    b+= nums[i];
                    a+= nums[i-1];
                    int j=0;
                    while(b.length()<digs.length/2){
                        if(j==i-1 || j==i){
                            j=i+1;
                        }
                        b+= nums[j];
                        j++;
                    }
                    int k=digs.length-1;
                    while(a.length()<digs.length/2){
                        if(k==i || k==i-1){
                            k=i-2;
                        }
                        a+= nums[k];
                        k--;
                    }
                    //System.out.println(Integer.parseInt(b) + " "+Integer.parseInt(a));
                    min= Math.min(min,(Integer.parseInt(b)- Integer.parseInt(a)));
                }
                out.println(min);
            }

        }

        out.close();
        System.exit(0);

    }
}