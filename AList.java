import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

public class AList implements List<Character> {

    private static final int defaultSize = 50;

    private int msize = 0;
    private int numInList = 0;
    private int curr = 0;
    private Character[] listArray;

    public AList() {
        setup(defaultSize);
    }

    public AList(int size) {
        setup(size);
    }

    private void setup(int size) {
        msize = size;
        numInList = 0;
        curr = 0;
        listArray = new Character[size];
    }

    public boolean isFull() {
        return msize == numInList;
    }

    public boolean isEmpty() {
        return numInList == 0;
    }

    public void insert(Character item) throws ListException {

        if (isFull()) {
            throw new ListException();
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
        curr = n-1;
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
            pw.print("> {capacity = "+ listArray.length+", length = " + numInList+", cursor = "+curr+"}");
        }
    }

    public void clear() {
        numInList = 0;
        curr = 0;
    }

    public static void main(String[] args) {
        AList list = new AList(512);
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

    public static void handleCommand(AList list,String command,PrintWriter pw){
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


