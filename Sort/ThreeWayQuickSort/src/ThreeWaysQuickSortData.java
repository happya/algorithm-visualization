import java.util.Arrays;

public class ThreeWaysQuickSortData {
    public enum Type{
        Default,
        NearlyOrdered,
        Identical
    }
    private int[] numbers;
    public int l,r;
    public int curPivot;
    public boolean[] fixedPivots;
    public int curL, curR;

    public ThreeWaysQuickSortData(int N, int randomBound, ThreeWaysQuickSortData.Type dataType) {
        numbers = new int[N];
        fixedPivots = new boolean[N];
        int lBound = 1;
        int rBound = randomBound;
        if(dataType == ThreeWaysQuickSortData.Type.Identical) {
            int avgNumber = (lBound + rBound) / 2;
            lBound = avgNumber;
            rBound = avgNumber;
        }
        for(int i=0;i<N;i++){
            numbers[i] = (int)(Math.random()*(rBound-lBound+1))+lBound;
            fixedPivots[i] = false;
        }
        if( dataType == ThreeWaysQuickSortData.Type.NearlyOrdered ){
            Arrays.sort(numbers);
            int swapTime = (int)(0.01 * N);
            for(int i=0;i<swapTime;i++){
                int a = (int)(Math.random() * N);
                int b = (int)(Math.random() * N);
                swap(a,b);
            }
        }

    }
    public ThreeWaysQuickSortData(int N, int randomBound){

        this(N, randomBound, ThreeWaysQuickSortData.Type.Default);
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
