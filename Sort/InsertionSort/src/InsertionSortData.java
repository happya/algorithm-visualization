import java.util.Arrays;

public class InsertionSortData {
    private int [] numbers;
    public enum Type {
        Default,
        NearlyOrdered
    }

    public int orderedIndex = -1; // [0,orderedIndex)有序
    public int currentIndex = -1;

    public InsertionSortData(int N, int randomBound, Type dataType) {
        numbers = new int[N];
        for(int i=0;i<N;i++)
            numbers[i] = (int)(Math.random() * randomBound) + 1;

        if(dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(0.02 * N);

            for(int i=0;i<swapTime;i++){
               int a = (int)(Math.random() * N);
               int b = (int)(Math.random() * N);
               swap(a,b);
            }
        }
    }

    public InsertionSortData(int N, int randomBound) {
        this(N, randomBound, Type.Default);
    }
    public int N() { return numbers.length; }

    public int get(int index) {
        if(index<0 || index>=numbers.length)
            throw new IllegalArgumentException("Invalid index");
        return numbers[index];
    }

    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
