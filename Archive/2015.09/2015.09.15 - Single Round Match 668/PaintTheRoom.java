package created;

public class PaintTheRoom {
    public String canPaintEvenly(int R, int C, int K) {
        if(R==1 && C==1 && K>1){
            return "Cannot paint";
        }else if(K==1){
            return "Paint";
        }else if(R%2==1 && C%2==1){
            return "Cannot paint";
        }else{
            return "Paint";
        }


    }
}
