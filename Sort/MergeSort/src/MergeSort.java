import java.util.Arrays;

public class MergeSort {
    private MergeSort() {};

    // arr[l,mid],arr[mid+1,r]归并
    private static void merge(Comparable[] arr, int l, int mid, int r) {
       Comparable[] aux = Arrays.copyOfRange(arr,l,r+1);

       int i = l, j = mid + 1;
       for(int k=l;k<=r;k++) {
           if(i>mid)        { arr[k] = aux[j-l]; j++; }
           else if (j>r)    { arr[k] = aux[i-l]; i++; }
           else if (aux[i-l].compareTo(aux[j-l])<0) {
               arr[k] = aux[i-l]; i++;
           }
           else { arr[k] = aux[j-l]; j++; }

       }
    }
    private static String repeatCharacters(char character, int length) {
        StringBuilder s = new StringBuilder(length);
        for(int i=0;i<length;i++)
            s.append(character);
        return s.toString();
    }
    private static void sort(Comparable[] arr, int l, int r, int depth) {
        System.out.print(repeatCharacters('-', depth*2));
        System.out.println("Deal with [ "+ l+", " + r + " ]");
        if(l>=r)
            return;
        int mid = (l+r)/2;
        sort(arr, l, mid, depth+1);
        sort(arr, mid+1, r, depth+1);
        merge(arr,l,mid,r);

    }
    public static void sort(Comparable[] arr){
        int n = arr.length;
        sort(arr, 0, n-1,0);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[8];
        for(int i=0;i<8;i++)
            arr[i] = new Integer(8-i);
        MergeSort.sort(arr);
    }
}
