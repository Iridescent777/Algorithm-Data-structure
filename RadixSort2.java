
    import java.io.*;

    public class RadixSort2 {

        public static void main(String[] args) {

            QueueString[] radix = new QueueString[52];        //这里要开52个队列
            try (BufferedReader bf = new BufferedReader(new FileReader("D:/temp/radix2.txt"))) {
                String line;
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                while ((line = bf.readLine()) != null) {
                    handleCommand(radix, line, pw);
                    break;
                }
                System.out.println(sw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void handleCommand(QueueString[] radix, String command, PrintWriter pw) {
            String[] parts = command.split(" ");
            for(int i = 0; i<52;i++){
                radix[i] = new QueueString();
            }
            for(int j = 7;j>=0;j--) {
                for (int i = 0; i < parts.length; i++) {


                        int index = 0;
                        if (65 <= parts[i].charAt(j) && parts[i].charAt(j) <= 90) {
                            index = parts[i].charAt(j) - 65;
                        } else if (97 <= parts[i].charAt(j) && parts[i].charAt(j) <= 122) {
                            index = parts[i].charAt(j) - 71;
                        } else {
                            System.out.println("invalid");
                        }
                        radix[index].enqueue(parts[i]);
                    }

                        int p = 0;
                        for(int k  = 0;k<52;k++){
                            while(!radix[k].isEmpty()){
                                parts[p] = radix[k].dequeue();
                                p++;
                            }
                        }

                }
            for (int i = 0; i < parts.length; i++) {
                    pw.print(parts[i]+" ");
                }
            }
        }

