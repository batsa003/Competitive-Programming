package created;

import utils.InputReader;
import java.io.PrintWriter;

public class TaskC {


    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n= in.nextInt();
        int m =in.nextInt();
        int w= in.nextInt();
        int[] ar= new int[n];
        for(int i=0; i<n; i++){
            ar[i]=in.nextInt();
        }
        long low=1;
        long high= 2000000000;
        while((high-low)>1){
            long mid= (high+low)/2;
            //Does mid as a shortest flower works?
            long need=0;
            int cur=0;
            SegmentTreeRangeUpdate seg= new SegmentTreeRangeUpdate(ar);
            for(int i=0; i<= (n-w); i++){
                long a= seg.query(i,i);
               // out.println("i=" + i + " and a=" + a);
                if(a<mid){
                    seg.update(i, i+w-1,(int)(mid-a));
                    need+= (mid-a);
                }
            }

            int maxNeed=0;
            for(int i= (n-w+1); i<n; i++){
                maxNeed= Math.max(maxNeed, (int)(mid- seg.query(i,i)));
            }
            need+= maxNeed;

            if(need<0 || need>m){
                high=mid;
            }else{
                low=mid;
            }
        }
        out.println(low);
    }
}

class SegmentTreeRangeUpdate {
    public long[] leaf;
    public long[] update;
    public int origSize;
    public SegmentTreeRangeUpdate(int[] list)	{
        origSize = list.length;
        leaf = new long[4*list.length];
        update = new long[4*list.length];
        build(1,0,list.length-1,list);
    }
    public void build(int curr, int begin, int end, int[] list)	{
        if(begin == end)
            leaf[curr] = list[begin];
        else	{
            int mid = (begin+end)/2;
            build(2 * curr, begin, mid, list);
            build(2 * curr + 1, mid+1, end, list);
            leaf[curr] = leaf[2*curr] + leaf[2*curr+1];
        }
    }
    public void update(int begin, int end, int val)	{
        update(1,0,origSize-1,begin,end,val);
    }
    public void update(int curr,  int tBegin, int tEnd, int begin, int end, int val)	{
        if(tBegin >= begin && tEnd <= end)
            update[curr] += val;
        else	{
            leaf[curr] += (Math.min(end,tEnd)-Math.max(begin,tBegin)+1) * val;
            int mid = (tBegin+tEnd)/2;
            if(mid >= begin && tBegin <= end)
                update(2*curr, tBegin, mid, begin, end, val);
            if(tEnd >= begin && mid+1 <= end)
                update(2*curr+1, mid+1, tEnd, begin, end, val);
        }
    }
    public long query(int begin, int end)	{
        return query(1,0,origSize-1,begin,end);
    }
    public long query(int curr, int tBegin, int tEnd, int begin, int end)	{
        if(tBegin >= begin && tEnd <= end)	{
            if(update[curr] != 0)	{
                leaf[curr] += (tEnd-tBegin+1) * update[curr];
                if(2*curr < update.length){
                    update[2*curr] += update[curr];
                    update[2*curr+1] += update[curr];
                }
                update[curr] = 0;
            }
            return leaf[curr];
        }
        else	{
            leaf[curr] += (tEnd-tBegin+1) * update[curr];
            if(2*curr < update.length){
                update[2*curr] += update[curr];
                update[2*curr+1] += update[curr];
            }
            update[curr] = 0;
            int mid = (tBegin+tEnd)/2;
            long ret = 0;
            if(mid >= begin && tBegin <= end)
                ret += query(2*curr, tBegin, mid, begin, end);
            if(tEnd >= begin && mid+1 <= end)
                ret += query(2*curr+1, mid+1, tEnd, begin, end);
            return ret;
        }
    }
}