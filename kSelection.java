package T1;


public class kSelection extends SortAlgorithm{

  private int k = 0;

    public void sort(Comparable[] objs){
        int N = objs.length;
        for(int i = 0;i<k;i++){
            int temp = i;                             // 一个总是指向当前最小值的指针
            for(int j = i+1; j<N;j++){
                if(less(objs[j],objs[temp])) {      // 如果objs[j]小于objs[temp]
                    temp = j;                       // 让temp指向j的位置
                }
            }
            exchange(objs,i,temp);                  //交换i,temp的位置
        }
    }

    public kSelection(int k){
        this.k = k;

    }
}
