import java.io.*;

public class LList implements List<Character>{
    private LinkedNode head = null;
    private LinkedNode DummyNode = new LinkedNode(null,null);

    private LinkedNode curr = null;
    private static class LinkedNode<Character>{
        Character element;                        //以泛型类型定义栈的element
        LinkedNode<Character> next;              //以LinkedNode类型定义指针next
        public LinkedNode(Character element ,LinkedNode<Character> next){
            this.element = element;
            this.next = next;
        }

        public LinkedNode getNext(){
            return this.next;              //获取当前结点指针域的值
        }

        public Character getElement(){
            return this.element;         //获取当前结点的元素值
        }

        public void setElement(Character element) {
            this.element = element;
        }

        public void setNext(LinkedNode<Character> next) {
            this.next = next;
        }
    }

    public LList(){
        head = DummyNode;      // 创建表的时候把头指针指向哑结点
        curr = DummyNode;      //curr指向当前结点的前一个结点
    }

    public boolean isFull(){
        return false;
    }

    public boolean isEmpty(){
        return DummyNode.getNext() == null;
    }

    public boolean gotoPrev(){
        if(curr != head && !isEmpty()){  //如果不在表头
            while(head.getNext() != curr){
                head = head.getNext();
            }
            curr = head;
            head = DummyNode;  //head归位
            return true;
        }
        return false;
    }

    public boolean gotoBeginning(){
        if(!isEmpty()){
            curr = head;
            return true;
        }
        return false;
    }

    public boolean gotoEnd(){
           if(!isEmpty()){
               while(curr.getNext().getNext()!=null){  //目标元素的指针域为null则终止循环
                   curr = curr.getNext();
               }
               return true;
           }
           return false;
    }

    public boolean gotoNext(){
        if(!isEmpty() && curr.getNext().getNext() != null){
            curr = curr.getNext();
            return true;
        }
        return false;
    }

    public boolean find(Character Element){
        if(!isEmpty()){
            for(LinkedNode i = curr;i.getNext()!=null; i =i.getNext()){
                if(i.getNext().getElement().equals(Element)){
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
    public void clear(){
        DummyNode.setNext(null);
        head = DummyNode;
        curr = head;

    }

    public void moveToNth(int n){

        Character removed = (Character)curr.getNext().getElement();
        remove();             //删除目标结点
        curr = head;
        int index = 0;
        while(index<n-1){
            gotoNext();
            index++;
        }
        try{
            insert(removed);

        }catch(ListException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Character getCursor() {
        return (Character) curr.getNext().getElement();
    }

    public void insert(Character element) throws ListException {

        if (isEmpty()) {
            DummyNode.setNext(new LinkedNode(element, null));
            //  curr不做变动，curr = DummyNode.getNext();
        } else {
                curr.getNext().setNext(new LinkedNode(element, curr.getNext().getNext()));
                curr = curr.getNext(); //curr后移
                //curr.getNext()是实际指向的元素，在其后插入元素
            }
    }

    public void remove(){

        if(!isEmpty()){

            if(curr.getNext().getNext() != null) {
                curr.setNext(curr.getNext().getNext());  //被删除结点的前结点指向后结点
                //curr不用移动
            }
            else if(head != curr){   //删除尾结点
                curr.setNext(null);
                gotoBeginning();            //如果是删除尾结点，删完让curr前移
            }
            else{
                DummyNode.setNext(null);   //如果curr已经指头了，那么要把哑结点的指针域变成null
            }
        }
    }

    @Override
    public void replace(Character newElement) {
          if(!isEmpty()) {
              curr.getNext().setElement(newElement);
          }
    }

    public int NumInList(){
        int index = 0;
        while(head.getNext()!=null){
            index++;
            head = head.getNext();
        }
        head = DummyNode;
        return index;
    }

    public int GetPos() {
        if (isEmpty()) {
            return 0;
        } else {
            int index = 0;
            while (head != curr) {
                index++;
                head = head.getNext(); //头指针前进
            }
            head = DummyNode;           //归位
            return index;
        }
    }

    @Override
    public void showStructure(PrintWriter pw) {
        if (isEmpty()) {
            pw.print("表是空的");
        } else {
            pw.print("< ");
            for (LinkedNode i = head.getNext(); i!=null;i=i.getNext()) {
                pw.print(i.getElement() + " ");
            }
            pw.print("> {capacity = "+ NumInList()+", length = " + NumInList()+", cursor = "+GetPos()+"}");
        }
    }

    public static void main(String[] args) {
        LList list = new LList();
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

    public static void handleCommand(LList list,String command,PrintWriter pw){
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
