package T1;

public class Quicker extends SortAlgorithm{

    public void sort(Comparable[] objs){
        qsort(objs,0,objs.length-1);
    }


    public void qsort(Comparable[] objs,int i,int j) {
        if (i > j) return;
        exchange(objs,i+(j-i)/2,j);
        int[] ans = partition(objs, i, j,objs[j] );
        qsort(objs,i,ans[0]-1);
        qsort(objs,ans[1]+1,j);
    }
    public int[] partition(Comparable[] objs, int left, int right, Comparable pivot) {  //使用双指针分治

        //left是小于区的有边界
        //right是小区的左边界
        if(left>right) return new int[]{-1,-1};
        if(left==right)return new int[]{left,right};
        int less = left-1;
        int more = right;
        int index = left;

        while(index<more){
            if(less(objs[index],pivot)){
                exchange(objs,index++,++less);
            }
            else if(objs[index].equals(pivot)){
                index++;
            }
            else{
                exchange(objs,index,--more);
            }
        }
        exchange(objs,more,right);
        return new int[]{less+1,more};
    }


}

