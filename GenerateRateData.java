package T1;

import java.util.Random;

public class GenerateRateData extends GenerateData{

    public static Double[] getSortedData(int N,double rate){
        Double[] numbers = new Double[N];
        double t = 0.0;
        for (int i = 0; i < N*rate; i++){
            numbers[i] = t;
            t =  1.0;
        }
        for(int i =(int)( N*rate);i<N;i++){
            t = t+1.0/N;
            numbers[i] = t;
        }
        return numbers;
    }

    public static Double[] getInversedData(int N,double rate){
        Double[] numbers = new Double[N];
        double t = 1.0;
        for (int i = 0; i < N*rate; i++){
            numbers[i] = t;
        }
        for(int i = (int)(N*rate);i<N;i++){
            t  = 1.0/N+t;
            numbers[i] = t;
        }
        return numbers;
    }

    public static Double[] getRandomData(int N,double rate){
        Double[] numbers = getSortedData(N,rate);
        shuffle(numbers, 0, numbers.length);
        return numbers;
    }

}
