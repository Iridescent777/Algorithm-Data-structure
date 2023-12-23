import java.io.*;

public class RadixSort1 {



    public static void main(String[] args) {

        LQueue[] radix = new LQueue[100000000];
        try (BufferedReader bf = new BufferedReader(new FileReader("D:/temp/radix1.txt"))) {
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

    public static void handleCommand(LQueue[] queue, String command, PrintWriter pw) {
        String[] parts = command.split(" ");
        for(int i = 0; i<20000000;i++){
            queue[i] = new LQueue();
        }
        for (String item : parts) {
            try {

                double num = Double.parseDouble(item);
                queue[(int) num].enqueue(num);
            } finally {

            }
        }
        for (int i = 0; i < 20000000; i++) {
            while (!queue[i].isEmpty()) {
                Double value = queue[i].dequeue();
                pw.print(value.longValue()+" ");
            }
        }
    }
}
