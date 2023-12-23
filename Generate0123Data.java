package T1;

public class Generate0123Data {

    public static Double[] getRandomData(int N){
        Double[] numbers = Generate0123Data.getSortedData(N);
        GenerateData.shuffle(numbers,0,numbers.length);
        return numbers;
    }


    public static Double[] getSortedData(int N){
        Double[] numbers = new Double[N];
        for(int i = 0;i<N/2;i++){
            numbers[i] = 0.0;
        }
        for(int i = (N/2);i<3*N/4;i++){
            numbers[i] = 1.0;
        }
        for(int i = 3*N/4;i<7*N/8;i++){
            numbers[i] = 2.0;
        }
        for(int i = 7*N/8;i<N;i++){
            numbers[i] = 3.0;
        }
        return numbers;
    }

    public static Double[] getInversedData(int N){
        Double[] numbers = new Double[N];
        for(int i = 0;i<N/2;i++){
            numbers[i] = 3.0;
        }
        for(int i = (N/2);i<3*N/4;i++){
            numbers[i] = 2.0;
        }
        for(int i = 3*N/4;i<7*N/8;i++){
            numbers[i] = 1.0;
        }
        for(int i = 7*N/8;i<N;i++){
            numbers[i] = 0.0;
        }
        return numbers;
    }
}
