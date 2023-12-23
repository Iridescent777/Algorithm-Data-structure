import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;


public class ResizingAList implements List<Character>{

    private static int capacity = 1;  //动态变化数组长度


    private int numInList = 0;
    private int curr = 0;
    private Character[] listArray;

    public ResizingAList() {
        setup();
    }
    private void setup() {
        listArray = new Character[capacity];
    }

    private void setup(int size){
        listArray = new Character[size];
    }

    public boolean isFull() {
        return capacity == numInList;
    }

    public boolean isEmpty() {
        return numInList == 0;
    }

    public void insert(Character item) throws ListException {

        if (isFull()) {
            Character[] oldlist = listArray;
            setup(2*capacity);  //扩表
            for(int i = 0;i<numInList;i++){
                listArray[i] = oldlist[i];
            }  //老表值给新表
            capacity = 2*capacity;//容量翻倍
            insert(item);
        } else if (isEmpty()) {
            listArray[0] = item;
            curr = 0;
            numInList++;          //空表将curr变成0,指向当前元素
        } else {
            for (int i = numInList; i > curr+1; i--) {
                listArray[i] = listArray[i - 1];
            }
            listArray[curr+1] =  item; //curr的下一位作为插入位置
            numInList++;
            curr++;                    //curr指向插入元素
        }
    }

    public void remove()  {
        if (!isEmpty()) {                        //表不空的时候
            if(curr == numInList -1){
                curr = 0;
                numInList -- ;                 //如果在指针在表尾，则指到表头
            }
            else{
                for(int i = curr;i<numInList-1;i++){
                    listArray[i] = listArray[i+1];  //元素移动
                }
                numInList --;
            }
        }
        if(numInList<=capacity/4 && capacity>=4){
            Character[] oldlist = listArray;
            setup(capacity/2);
            for(int i = 0;i<numInList;i++){
                listArray[i] = oldlist[i];
            }
            capacity = capacity/2;
        }
    }


    public void replace(Character Element) {
        if(!isEmpty()){
            listArray[curr] = Element;
        }
    }

    public boolean gotoBeginning() {
        if (!isEmpty()) {
            curr = 0;
            return true;
        }
        return false;
    }

    public boolean gotoEnd() {
        if (!isEmpty()) {
            curr = numInList-1;
            return true;
        }
        return false;
    }

    public boolean gotoNext() {
        if (!isEmpty() && curr < numInList-1) {
            curr++;
            return true;
        }
        return false;
    }

    public boolean gotoPrev() {
        if (!isEmpty() && curr > 0) {
            curr--;
            return true;
        }
        return false;
    }

    @Override
    public Character getCursor() {
        return listArray[curr];
    }

    public boolean find(Character element) {
        if(!isEmpty()) {
            for (int i = curr; i < numInList; i++) {
                if (listArray[i].equals(element)) {
                    curr = i;
                    return true;
                }
            }
            curr = numInList-1;
            return false;
        }
        return false;
    }

    @Override
    public void moveToNth(int n) {
        Character removed = listArray[curr];
        remove();
        curr = n;
        try{
            insert(removed);

        }catch(ListException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public void showStructure(PrintWriter pw) {
        if (isEmpty()) {
            pw.print("表是空的");
        } else {
            pw.print("< ");
            for (int i = 0; i < numInList; i++) {
                pw.print(listArray[i] + " ");
            }
            pw.print("> {capacity = "+ capacity+", length = " + numInList+", cursor = "+curr+"}");
        }
    }

    public void clear() {
        numInList = 0;
        curr = 0;
        capacity =1;
    }

    public static void main(String[] args) {
        ResizingAList list = new ResizingAList();
        try (BufferedReader bf = new BufferedReader(new FileReader("D:/list_testcase.txt"))){

            String line;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            while((line = bf.readLine())!=null){
                handleCommand(list,line,pw);
            }
            System.out.println(sw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleCommand(ResizingAList list,String command,PrintWriter pw){
        String[] parts = command.split(" ");
        for(String item : parts){
            try {
                switch (item.charAt(0)) {
                    case '+':
                        list.insert(item.charAt(1));
                        break;
                    case '-':
                        list.remove();
                        break;
                    case '=':
                        list.replace(item.charAt(1));
                        break;
                    case '*':
                        list.gotoEnd();
                        break;
                    case '#':
                        list.gotoBeginning();
                        break;
                    case '~':
                        list.clear();
                        break;
                    case '>':
                        list.gotoNext();
                        break;
                    case '<':
                        list.gotoPrev();
                        break;
                    default:
                        System.out.println("没有这个命令");
                }
            }
            catch(ListException e){
                System.out.println("ListException"+e.getMessage());
            }


        }
        list.showStructure(pw);
        pw.println();
    }


}
