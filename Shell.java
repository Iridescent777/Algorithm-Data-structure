package T1;

public class Shell extends SortAlgorithm{
    public void sort(Comparable[] objs){
        int N = objs.length;
        for(int i = N/2;i>=3;i/=3){          //i表示增量大小,也就是希尔插入中的arr,起步增量采取数组一半,并以1/3开始下降
            for(int j = 0;j<i;j++){          //j表示每一组的首元素
                shellInsertion(objs,j,i);    //对第j-1组进行排序
            }
        }
        shellInsertion(objs,0,1);  //从头到尾采取直接插入排序
    }

    public void shellInsertion(Comparable[] objs,int start,int arr){
        int N = objs.length;

        for(int i = start+arr;i<N;i+=arr){          // i从start+arr开始遍历，按照arr间隔访问  ,i是无序区指针
            // j是待排元素的指针,每次都在与j-1比较
            for(int j = i;(j>=arr) && less(objs[j],objs[j-arr]);j-=arr){
                exchange(objs,j,j-arr);          // 注意Shell的第一次交换不一定是正确的，但却把有序区元素放进去了无序区，以后完全由j指向该元素
            }
        }
    }
}
