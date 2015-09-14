

    // LAZY CALCULATES THE MAX WITHIN THE INTERVALS
    // WHEN WE WANT TO PASS IN VALUE V TO INTERVAL [l,r], [l,r] values are updated to v if it was less than v

public class LAZY {
    //Lazy value lazy in node n means that this interval as well as its childrens must have value lazy
    // passed in sooner or later

    //By the time the value lazy passed in to node n, its A value must be updated accordingly.

    int N;
    int[] A;
    int[] lazy;

    LAZY(int size){
        N=size+10;
        A=new int[4*N+10];
        lazy= new int[4*N+10];
    }

    void upd(int val, int n){
        A[n] = Math.max(A[n],val);
        lazy[n]= Math.max(lazy[n], val);
    }
    void clearLazy(int a, int b, int n){
        if(lazy[n] ==0) return;
        if(a<b){
            //has two child to pass in value and lazy,
            upd(lazy[n], 2*n);
            upd(lazy[n], 2*n+1);
        }
        lazy[n]=0;
    }

    // When the update on a,b is done, its value A must be updated up to date.
    void update(int l, int r, int val, int a, int b, int n){
        clearLazy(a,b,n); // so that lazy[n]=0, and two childrens have the lazy to execute.
        if(r < a || b<l) return;
        if(l<=a && r>=b){
            upd(val,n);
        }
        int m =(a+b)/2;
        update(l,r,val,a,m,2*n);
        update(l,r,val,m+1,b,2*n+1);
        A[n] = Math.max(A[n*2], A[n*2+1]);
    }

    int query(int l, int r, int a, int b, int n){
        clearLazy(a,b,n); // get ready to make recursive call on two subsequent queries 2*n, 2*n+1.
        if( r<a || l>b) return 0;
        if( l<=a && r>=b) return A[n];

        int m= (a+b)/2;
        int m1= query(l,r,a,m,2*n);
        int m2= query(l,r,m+1,b,2*n+1);
        return Math.max(m1,m2);
    }
}
