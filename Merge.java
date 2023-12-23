package T1;

public class Merge extends SortAlgorithm{

    public void sort(Comparable[] objs){
        Comparable[] temp = new Comparable[objs.length];
        mergesort(objs,temp,0,objs.length-1);
    }

    public void mergesort(Comparable[] objs,Comparable[] temp,int left,int right){
        int mid = left + (right-left)/2;
        if(left == right) return;
        mergesort(objs,temp,left,mid);
        mergesort(objs,temp,mid+1,right);
        for(int i = left; i<=right;i++){
            temp[i] = objs[i];
        }

        int i1 = left;
        int i2 = mid + 1;
        for(int curr = left;curr<=right;curr++){
            if(i1 == mid+1){
                objs[curr] = temp[i2++];
            }
            else if(i2>right){
                objs[curr] = temp[i1++];
            }
            else if(less(temp[i1],temp[i2])){
                objs[curr] = temp[i1++];
            }
            else{
                objs[curr] = temp[i2++];
            }
        }
    }
}
