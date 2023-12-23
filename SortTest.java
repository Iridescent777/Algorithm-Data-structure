package T1;
import java.lang.Math;
public class SortTest {
    // 使用指定的排序算法完成一次排序所需要的时间，单位是纳秒
    public static double time(SortAlgorithm alg, Double[] numbers){
        double start = System.nanoTime();
        alg.sort(numbers);
        double end = System.nanoTime();
        return end - start;
    }
    // 为了避免一次测试数据所造成的不公平，对一个实验完成T次测试，获得T次测试之后的平均时间
    public static double test(SortAlgorithm alg, Double[] numbers, int T)
    {
        double totalTime = 0;
        for(int i = 0; i < T; i++) {
            totalTime += time(alg, numbers);
            System.out.println(numbers[99]);          //在这里输出目标值
          //  GenerateData.shuffle(numbers,0,numbers.length-1);
        }
        return totalTime/T;
    }
    // 执行样例，仅供参考。
    // 由于测试数据的规模大小，算法性能，机器性能等因素，请同学们耐心等待每次程序的运行。
    public static void main(String[] args) {
        int[] dataLength = {256,512,1024,2048,4096,8192,16384,32768,65536};
        double[] elapsedTime = new double[dataLength.length];
        SortAlgorithm alg = new kQuick(100);
        for(int i = 0; i < dataLength.length; i++)
            elapsedTime[i] = test(alg,GenerateData.getRandomData(dataLength[i]), 1);      //数据清洗10次
        for(double time: elapsedTime)
            System.out.printf("%6.4f ", Math.log10(time));
       System.out.println();
    }
}