package search;

public class BinarySearch {

    public static int binarySearch(int[] array, int m, int n, int k){
        if(m <= n){
            int mid = (m + n)/2;
            if(k < array[mid])
                return binarySearch(array, m, mid - 1, k);
            else if (array[mid] < k)
                return binarySearch(array, mid + 1, n, k);
            else
                return mid;
        }
        return -1;
    }

    public static int binarySearch1(int[] array, int m, int n, int k) {
        int start = m;
        int end = n;
        int mid = (start + end)/2;

        while(start <= end) {
            if(array[mid] > k){
                end = mid -1;
            } else if(array[mid] < k) {
                start = mid + 1;
            } else {
                return mid;
            }
            mid = (start + end) / 2;
        }
        return -1;
    }

    public static int binarySearch(int[] array, int k){
        return binarySearch1(array, 0, array.length -1, k);
    }

    public static void main(String[] args) {
        int[] array_1 = {2, 3};
        int[] array_2 = {1, 2, 11, 20, 45, 89, 100, 135, 245, 1000};
        int[] array_3 = {2, 3, 11,  16, 19, 21, 22};
        int[] array_4 = {11, 22, 22, 22, 23, 24, 25};
        int[] array_5 = {1};
        int[] array_6 = {3, 3, 3, 3};

        System.out.println(binarySearch(array_1, 2));
        System.out.println(binarySearch(array_1, 3));
        System.out.println(binarySearch(array_1, 4));
        System.out.println(binarySearch(array_2, 1000));
        System.out.println(binarySearch(array_4, 22));
    }
}
