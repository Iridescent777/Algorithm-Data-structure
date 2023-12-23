package T1;

public class Bubble extends SortAlgorithm{
    public void sort(Comparable[] objs){
        int N = objs.length;
        for(int i = 0; i<N-1;i++){
            for(int j = N-1;j>0;j--){
                if(less(objs[j],objs[j-1])){  //如果objs[j]小于objs[j-1]
                    exchange(objs,j,j-1);   //相邻元素交换
                }
            }
        }
    }
}
