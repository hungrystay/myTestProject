package sort;

public class QuicksortTester {

    static void swap (int a, int b, int[] array) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    static int findPivot(int begin, int end, int[] array) {
            int pivot = end;
            int indexA = begin - 1;
//            int indexB = begin;
            for(int indexB = begin; indexB < pivot; indexB++) {
                if(array[indexB] < array[pivot]){
                    indexA++;
                    swap(indexA, indexB, array);
                }
            }
            swap(pivot, indexA + 1, array);
            return indexA + 1;
    }

    static void quickSort(int begin, int end, int[] array) {
        if(begin <= end) {
            int pivot = findPivot(begin, end, array);
            quickSort(begin, pivot -1, array);
            quickSort(pivot + 1, end, array);
        }
    }

   static void quickSort(int[] array) {
        quickSort(0, array.length -1, array);
    }

    public static void main(String[] args) {
        int[] array = {5, 4, 3, 6, 7, 10};
        quickSort(array);
        for (int value: array){
            System.out.print(value + " ");
        }
    }

}
