import java.util.Arrays;

public class HeapSortData {
    public enum Type{
        Default,
        NearlyOrdered,
        Identical
    }
    public int[] numbers;
    public int heapIndex;//numbers[heapIndex,N)已经排好序

    public HeapSortData(int N, int randomBound, HeapSortData.Type dataType) {
        numbers = new int[N];
        heapIndex = N;
        int lBound = 1;
        int rBound = randomBound;
        if(dataType == HeapSortData.Type.Identical) {
            int avgNumber = (lBound + rBound) / 2;
            lBound = avgNumber;
            rBound = avgNumber;
        }
        for(int i=0;i<N;i++){
            numbers[i] = (int)(Math.random()*(rBound-lBound+1))+lBound;
        }
        if( dataType == HeapSortData.Type.NearlyOrdered ){
            Arrays.sort(numbers);
            int swapTime = (int)(0.01 * N);
            for(int i=0;i<swapTime;i++){
                int a = (int)(Math.random() * N);
                int b = (int)(Math.random() * N);
                swap(a,b);
            }
        }
        for(int i = 0;i < N; i++)
            numbers[i] = (int)(Math.random() * randomBound ) + 1;
    }
    public HeapSortData(int N, int randomBound){
        this(N, randomBound, Type.Default);
    }
    public int N() { return numbers.length; }

    public int get(int index) {
        if(index<0 || index>numbers.length)
            throw new IllegalArgumentException("Invalid index");
        return numbers[index];
    }
    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
