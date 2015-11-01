package created;

public class ProblemSets {
    public long maxSets(long E, long EM, long M, long MH, long H) {

        long good=0;
        long bad= Long.MAX_VALUE;

        while( (bad-good)>1){
            long k= (good+bad)/2;
            long e=E;
            long em=EM;
            long m= M;
            long mh = MH;
            long h=H;

            if(e +em < k || mh+h<k){
                bad=k;
                continue;
            }

            if(k>e){
                em-= (k-e);
            }
            if(k>h){
                mh-= (k-h);
            }
            if( m + em + mh >=k){
                good=k;
            }else{
                bad=k;
            }
        }


        return good;

    }
}
