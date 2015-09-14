// to initialize, we need starting index and ending index of the array.
// This segment tree is modified to perform the & operation in the range.
// Each UPDATE is modified to do | operation in the l,r range.
// Each QUERY is modified to do & operation in the given range.


public class SegmentTree {
    public SegmentTree left, right;
    public int val, lazy, start, end;

    public SegmentTree(int start, int end) {
        this.start = start;
        this.end = end;
        if (start != end) {
            int mid = (start + end) >> 1;
            left = new SegmentTree(start, mid);
            right = new SegmentTree(mid + 1, end);
            lazy = 0;
            val = 0;
        }
    }

    public void update(int l, int r, int v) {
        if (l == start && r == end) {
            // **** THE MAIN OPERATION IS | ****
            val |= v;
            lazy |= v;
            return;
        }
        push();
        int mid = (start + end) >> 1;
        if (r <= mid) left.update(l, r, v);
        else if (l > mid) right.update(l, r, v);
        else {
            left.update(l, mid, v);
            right.update(mid+1, r, v);
        }
        join();
    }

    public int query (int l, int r) {
        if (l == start && r == end)
            return val;
        push();
        int mid = (start + end) >> 1;
        int ret = 0;
        if (r <= mid) ret = left.query(l, r);
        else if (l > mid) ret = right.query(l, r);
        else ret = left.query(l, mid) & right.query(mid + 1, r); // **** The main operation in this query is & ****
        join();
        return ret;
    }

    public void push() {
        if (left != null) {
            left.val |= lazy;
            right.val |= lazy;
            left.lazy |= val;
            right.lazy |= val;
        }
        lazy = 0;
    }

    public void join() {
        if (left != null) {
            this.val = left.val & right.val;
        }
    }
}