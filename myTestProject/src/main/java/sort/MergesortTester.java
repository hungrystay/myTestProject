package sort;

public class MergesortTester {



    void mergeSort(int[] array, int begin, int end) {
        int mid = begin + (end - begin)/2;

        int[] tmpArray = new int[end - begin + 1];
        mergeSort(array, begin, mid);
        mergeSort(array, mid + 1, end);
        merge(begin, mid, end, array, tmpArray);
    }

    void merge(int begin, int mid, int end, int[] array, int[] tmpArray){
        int indexA = begin;
        int indexB = mid + 1;
        int indexTmp = begin;
        while(indexA <= mid && indexB <= end) {
            if(array[indexA] < array[indexB]){
                tmpArray[indexTmp] = array[indexA];
                indexA ++;
            }
            else {
                tmpArray[indexTmp] = array[indexB];
                indexB++;
            }
            indexTmp ++;
        }


    }

}
