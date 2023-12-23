import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
public class DLList implements List<Character> {

    private LinkedNode head = null;
    private LinkedNode tail = null;

    private LinkedNode curr = null;


    private static class LinkedNode<Character> {
        Character element;                        //以泛型类型定义栈的element
        LinkedNode<Character> next;              //以LinkedNode类型定义指针next

        LinkedNode<Character> prev;              //定义前向指针

        public LinkedNode(Character element, LinkedNode<Character> prev, LinkedNode<Character> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public LinkedNode getNext() {
            return this.next;              //获取当前结点指针域的值
        }

        public LinkedNode getPrev() {
            return this.prev;
        }

        public Character getElement() {
            return this.element;         //获取当前结点的元素值
        }

        public void setElement(Character element) {
            this.element = element;
        }

        public void setNext(LinkedNode<Character> next) {
            this.next = next;
        }

        public void setPrev(LinkedNode<Character> prev) {
            this.prev = prev;
        }
    }

    public DLList() {
        head = null;
        tail = null;
        curr = null;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void insert(Character newElement) throws ListException {
        if (isEmpty()) {                                              //如果表空，插入元素的同时，创建链表
            head = new LinkedNode(newElement, null, null);
            curr = head;
            tail = head;
        }
        else if (curr == tail) {          //如果curr在表尾
            curr.setNext(new LinkedNode(newElement, curr, null));
            tail = tail.getNext();
            gotoNext();
        } else {
            curr.setNext(new LinkedNode(newElement, curr, curr.getNext()));
            gotoNext(); //curr移向新元素
            curr.getNext().setPrev(curr);  //后继的前驱设置为curr
        }

    }

    @Override
    public void remove() {
        if (!isEmpty()) {
            if (head == tail) {  //删除表尾
                curr = null;
                head = null;
                tail = null;
                //清空表
            } else if (curr == tail) {
                //删除表尾元素
                tail = tail.getPrev();
                tail.setNext(null); //断开与表尾元素的链接
                gotoBeginning();  //curr到表头
            } else if (head == curr) {  //如果要删表头元素
                gotoNext();  //curr指向新的表头
                curr.setPrev(null);
                head = curr;
            } else {
                curr.getPrev().setNext(curr.getNext());
                curr.getNext().setPrev(curr.getPrev());
                gotoNext();
            }
        }
    }

    @Override
    public boolean gotoBeginning() {
        if (!isEmpty()) {
            curr = head;
            return true;
        }
        return false;
    }

    @Override
    public boolean gotoEnd() {
        if (!isEmpty()) {
            curr = tail;
            return true;
        }
        return false;
    }

    @Override
    public boolean gotoNext() {
        if (curr != tail && !isEmpty()) {
            curr = curr.getNext();
            return true;
        }
        return false;
    }

    @Override
    public boolean gotoPrev() {
        if (curr != head && !isEmpty()) {
            curr = curr.getPrev();
            return true;
        }
        return false;
    }

    public void moveToNth(int n) {

        Character removed = (Character) curr.getElement();
        remove();             //删除目标结点
        int index = 0;
        gotoBeginning();
        while (index < n) {
            gotoNext();
            index++;
        }
        try {
            insert(removed);

        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Character getCursor() {
        return (Character) curr.getElement();
    }


    @Override
    public boolean find(Character searchElement) {
        if (!isEmpty()) {
            for (LinkedNode i = curr; i.getNext() != null; i = i.getNext()) {
                if (i.getElement().equals(searchElement)) {
                    curr = i;
                    return true;
                }
            }
            gotoEnd();     //curr到列表尾巴
            return false;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        curr = null;
    }

    @Override
    public void replace(Character newElement) {
        if(!isEmpty()) {
            curr.setElement(newElement);
        }
    }



    public int NumInList(){
        int index = 0;
        LinkedNode i = head;
        while(i!=null){
            index++;
            i = i.getNext();
        }
        return index;
    }

    public int GetPos() {
        if (isEmpty()) {
            return 0;
        } else {
            int index = 0;
            LinkedNode i = head;
            while (i != curr) {
                index++;
                i = i.getNext(); //头指针前
            }
            return index;
        }
    }

    @Override
        public void showStructure(PrintWriter pw) {
            if (isEmpty()) {
                pw.print("表是空的");
            } else {
                pw.print("< ");
                for (LinkedNode i = head; i!=null; i=i.getNext()) {
                    pw.print(i.getElement() + " ");
                }
                pw.print("> {capacity = "+ NumInList()+", length = " + NumInList()+", cursor = "+GetPos()+"}");
            }
        }



    public static void main(String[] args) {
        DLList list = new DLList();
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

    public static void handleCommand(DLList list,String command,PrintWriter pw){
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
