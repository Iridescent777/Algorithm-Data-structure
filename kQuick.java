package T1;

public class kQuick extends SortAlgorithm{


    private int k0 = 0;

    public void sort(Comparable[] objs){
        qsort(objs,0,objs.length-1);
    }
    public void qsort(Comparable[] objs,int i,int j) {
        while(true) {
            int k = partition(objs, i, j - 1, movePivot(objs, i, j));//移动轴值
            exchange(objs, k, j);
            if ((k - i) > 1) qsort(objs, i, k - 1);
            if ((j - k) > 1) {
                i = k+1;
                if(i>=k0){
                    break;
                }
                continue;

            }
            return;
        }
    }



    public Comparable movePivot(Comparable[] objs,int left,int right) {   //查询轴值
        int mid = left + (right - left) / 2;
        if(less(objs[right],objs[mid])){
            exchange(objs,right,mid);       //把right和mid的key值小者放到mid处
        }
        if(less(objs[mid],objs[left])){
            exchange(objs,left,mid);
        }
        if(less(objs[right],objs[mid])){
            exchange(objs,mid,right);
        }
        return objs[right];            //返还轴值
    }

    public int partition(Comparable[] objs, int left, int right, Comparable pivot) {  //使用双指针分治
        while (left <= right) {      //让双指针相遇
            while (less(objs[left], pivot)) {
                left++;
            }
            while (right >-1 && (less(pivot, objs[right]) || objs[right].equals(pivot))) {
                right--;
            }
            if(right<0){
                return left;              // 如果right<0，说明left未移动，数据交界点就是left
            }
            exchange(objs, left, right);
        }
        exchange(objs, left, right);
        return left;                                            // 返还交界点的记录
    }

    public kQuick(int k){
        this.k0 = k;

    }
}
