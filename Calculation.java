import com.sun.jdi.IntegerValue;

public class Calculation {


    int length = 0;
    static int currentPriority = 0;  //栈中符号优先级
    static int priority = 0;        //待处理符号优先级

    static boolean is_fraction = false;  //表示该操作数是否为小数
    static int base = 0;              //表示小数的基数
    static double sum = 0.0;
    private LStack operator = new LStack();
    private LStack operand = new LStack();

    private LStack temp = new LStack();
    char[] expression = new char[100];

    public Calculation(String s){
        this.length = s.length();
        for(int i = 0;i<s.length();i++){
            expression[i] = s.charAt(i);
        }
    }
    int i = 0;
public double calculation() {
    while (i < length || !operator.isEmpty()) {
        if ((48 <= expression[i] && expression[i] <= 57) || (expression[i] == '.')) {
            temp.push(expression[i]);           //操作数推入临时栈
            if ((char) temp.topValue() == '.') {
                is_fraction = true;
                i++;
                continue;  //跳过本轮循环
            }
            if (is_fraction) {
                base--;
            }
        } else {

            if (operator.isEmpty() || expression[i] == '(') {
                operator.push(expression[i]);      //操作符栈为空，操作符入栈  //小括号也要入栈
                setCurrentPriority();              //每一个操作符入栈，更新当前操作符栈的优先级


            } else {
                if(i<length) {
                    if(expression[i] == ')'){
                        if((char)operator.topValue() == '('){
                            operator.pop();
                            setCurrentPriority();
                            i++;
                            continue;
                        }
                        else if((char)operator.topValue() == '+' || (char)operator.topValue() == '-' || (char)operator.topValue() == '*' || (char)operator.topValue() == '/' || (char)operator.topValue() == '^'){
                            i--;
                            priority = 0;
                        }
                        else{
                            return java.lang.Float.NaN;
                        }
                    }
                    setPriority();                   //如果操作符栈不空，获得当前字符的优先级
                }
                else{
                    priority = 0;                   //如果符号栈还有元素，当前字符优先级就是0
                }
                if (priority > currentPriority) {                       //如果当前运算符优先级高，则运算符入栈
                    operator.push(expression[i]);
                    setCurrentPriority();
                } else if (priority < currentPriority) {                   //如果当前运算符优先级低，则运算符出栈

                        double operand2 = (double) operand.pop();
                        double operand1 = (double) operand.pop();             //将两个操作数出栈
                        switch ((char) operator.pop()) {
                            case '+':
                                operand.push(operand1+operand2);
                                break;
                            case '-':
                                operand.push(operand1-operand2);
                                break;
                            case '*':
                                operand.push(operand1 * operand2);

                                if(operator.isEmpty()){
                                    continue;
                                }
                                setCurrentPriority();
                                if(currentPriority == priority && priority != 0){
                                    continue;
                                }
                                break;
                            case '/':
                                operand.push(operand1 / operand2);
                                if(operator.isEmpty()){
                                    continue;
                                }
                                setCurrentPriority();
                                if(currentPriority == priority && priority != 0){

                                    continue;
                                }
                                break;
                            case '^':
                                operand.push(Math.pow(operand1, operand2));
                                if(operator.isEmpty()){
                                    continue;
                                }
                                setCurrentPriority();
                                continue;      //这一行我太得意了，因为^会被重复压入栈，所以遇到^我们直接回到while
                            case '(':
                                return Float.NaN;
                        }
                        //弹掉栈顶符号


                }
                else{
                    switch((char)(operator.topValue())){
                        case '+':{
                            double operand2 = (double)operand.pop();
                            double operand1 = (double)operand.pop();             //将两个操作数出栈
                            operand.push(operand1+operand2);
                            operator.pop();
                            operator.push(expression[i]);
                            break;
                        }
                        case '-':{
                            double operand2 = (double)operand.pop();
                            double operand1 = (double)operand.pop();             //将两个操作数出栈
                            operand.push(operand1-operand2);
                            operator.pop();
                            operator.push(expression[i]);
                            break;
                        }
                        case '*':{
                            double operand2 = (double)operand.pop();
                            double operand1 = (double)operand.pop();             //将两个操作数出栈
                            operand.push(operand1*operand2);
                            operator.pop();
                            operator.push(expression[i]);
                            break;
                        }
                        case '/':{
                            double operand2 = (double)operand.pop();
                            double operand1 = (double)operand.pop();             //将两个操作数出栈
                            operand.push(operand1/operand2);
                            operator.pop();
                            operator.push(expression[i]);
                            break;
                        }
                        case '^':operator.push(expression[i]);break;          //注意这里的^是右复合
                    }
                }
            }
        }
        if(i<length){
            i++;
        }
        //先处理临时栈里面的操作数
        if((expression[i]>57 || expression[i]<48)&&expression[i]!='.') {
            is_fraction = false;
            boolean not_num = temp.isEmpty();         //如果temp栈没有数字推入，说明我们后面不需要进行operand.push的操作
            while (!temp.isEmpty()) {
                if ((char) temp.topValue() == '.') {
                    temp.pop();
                    continue;
                } else {
                    sum += ((char) temp.pop() - 48) * Math.pow(10, base);
                    base++;
                }
            }
            if(!not_num) {
                operand.push(sum);  //把得到的小数推进操作数栈
                sum = 0.0;        //刷新sum
                base = 0;         //刷新base
            }
        }

    }

    return (double)operand.topValue();
}

public void setCurrentPriority(){
    switch ((char) operator.topValue()) {
        case '+':
            currentPriority = 1;
            break;
        case '-':
            currentPriority = 1;
            break;
        case '*':
            currentPriority = 2;
            break;
        case '/':
            currentPriority = 2;
            break;
        case '^':
            currentPriority = 3;
            break;
        case '(':
            currentPriority = 0;
            break;
        //获得当前栈的优先级
        //遇到小括号栈顶的优先级直接变成0
    }
}

public void setPriority(){
    switch (expression[i]) {
        case '+':
            priority = 1;
            break;
        case '-':
            priority = 1;
            break;
        case '*':
            priority = 2;
            break;
        case '/':
            priority = 2;
            break;
        case '^':
            priority = 3;
            break;
        //获得待操作字符的优先级
    }
}


public static void main(String[] args){
    Calculation a1 = new Calculation("3^(5-2*1)");
    Calculation a2 = new Calculation("2^3^(1+1)");
    Calculation a3 = new Calculation("3.14*(5-2^2/2)");
    Calculation a4 = new Calculation("5^(2+3))-1");
    Calculation a5 = new Calculation("5*(3+(2+1)+3");
    System.out.println(a1.calculation());
    System.out.println(a2.calculation());
    System.out.println(a3.calculation());
    System.out.println(a4.calculation());
    System.out.println(a5.calculation());
}
}
