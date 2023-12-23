package T1;


public class Insertion extends SortAlgorithm {
    public void sort(Comparable[] objs) {
        int N = objs.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(objs[j], objs[j - 1]); j--)
                exchange(objs, j, j - 1);
        }
    }

        public void sort(Comparable[] objs,int i,int j){
            int N = objs.length;
            for(int p = i;p<j;p++){
                for(int q = p;q>0 && less(objs[q],objs[q-1]);q--){
                    exchange(objs,q,q-1);
                }
            }
        }

}