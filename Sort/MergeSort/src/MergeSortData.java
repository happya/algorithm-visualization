public class MergeSortData {
    public int[] numbers;
    public int l ,r;
    public int mergeIndex;

    public MergeSortData(int N, int randomBound) {
        numbers = new int[N];
        for(int i=0;i<N;i++)
            numbers[i] = (int)(Math.random()*randomBound)+1;
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
