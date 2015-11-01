package created;

public class BuildingTowersEasy {
    public int maxHeight(int N, int[] x, int[] t) {
        for(int i=0; i<x.length; i++){
            x[i]--;
        }
        int currentIndex=1;
        int curLevel=0;
        int max=0;
        for(int i=1; i<N; i++){
            boolean canUp=true;
            boolean canDir=false;
            boolean mustDown=false;
            for(int k=0; k<x.length; k++){
                if(x[k]<i){
                    continue;
                }
                int j = x[k];
                if( (curLevel+1)- (j-i) == (t[k]-1)){
                    canUp=false;
                    canDir=true;
                }else if( (curLevel)-(j-i)>=t[k]){
                    mustDown=true;
                    canUp=false;
                }
            }
            if(canUp){
                curLevel++;
                max= Math.max(curLevel,max);
            }else if(mustDown){
                curLevel--;
            }
        }


        return max;
    }
}
