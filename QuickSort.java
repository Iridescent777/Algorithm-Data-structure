public class QuickSort {


private static int k = 0;                //轴值点的下标
    private LStack stack = new LStack();

    private class Task{            //内部类，创建任务对象
        String OperationType = null;
        int i = 0;
        int j = 0;
        public Task(int i,int j,String operationType) {
            this.i = i;
            this.j = j;
            this.OperationType = operationType;
        }

    }
    public void qsort(int[] objs,int i,int j){
        Task GeneralTask = new Task(i,j,"qsort");
        stack.push(GeneralTask);     //推入总任务
        while(!stack.isEmpty()){
            Task currentTask =(Task) stack.pop(); //得到当前任务
            if(currentTask.OperationType == "qsort"){
                Task SubTask1 = new Task(i,j,"partition");
                Task SubTask2 = new Task(i,k-1,"qsort");
                Task SubTask3 = new Task(k+1,j,"qsort");
                if(j-k>1) {
                    stack.push(SubTask3);
                    i = k+1;
                }
                if(k-i>1) {
                    stack.push(SubTask2);
                    j = k-1;
                }
                stack.push(SubTask1);         //分治任务最后入栈
            }
            else{
              k = partition(objs,currentTask.i,currentTask.j-1,movePivot(objs,currentTask.i,currentTask.j));
              int temp = objs[k];
              objs[k] = objs[currentTask.j];
              objs[currentTask.j] = temp;
            }
        }
    }
    public int movePivot(int[] objs,int left,int right) {   //查询轴值
        int mid = left + (right - left) / 2;
        if(objs[right]<objs[mid]){
            int temp = objs[mid];
            objs[mid] = objs[right];
            objs[right] = temp;
        }
        if(objs[mid]<objs[left]){
            int temp = objs[mid];
            objs[mid] = objs[left];
            objs[left] = temp;
        }
        if(objs[right]>objs[mid]){
            int temp = objs[right];
            objs[right] = objs[mid];
            objs[mid] = temp;
        }
        return objs[right];            //返还轴值
    }

    public int partition(int[] objs, int left, int right, int pivot) {  //使用双指针分治
        while (left <= right) {      //让双指针相遇
            while (objs[left]<pivot) {
                left++;
            }
            while (right >-1 && (pivot< objs[right] || objs[right] == pivot)) {
                right--;
            }
            if(right<0){
                return left;              // 如果right<0，说明left未移动，数据交界点就是left
            }
            int temp = objs[left];
            objs[left] = objs[right];
            objs[right] = temp;
        }
        int temp = objs[left];
        objs[left] = objs[right];
        objs[right] = temp;
        return left;                                            // 返还交界点的记录
    }


    public static void main(String[] args){
        int[] num = {9,7,2,5,10,8,16,42,1};
        QuickSort alg = new QuickSort();
        alg.qsort(num,0,8);
        for(int i = 0; i<num.length;i++){
            System.out.print(num[i]+"  ");
        }
    }

}
