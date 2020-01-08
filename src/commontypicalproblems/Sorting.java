package commontypicalproblems;

public class Sorting {

    //Insertion sort (in-place):
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
    
    //Merge sort:
    public static void mergeSort(int[] array) {
        mergeSortRange(array, 0, array.length - 1);
    }

    private static void mergeSortRange(int[] array, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSortRange(array, l, m);
            mergeSortRange(array, m + 1, r);
            merge(array, l, m, r);
        }
    }

    private static void merge(int[] array, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int left[] = new int[n1];
        int right[] = new int[n2];
        System.arraycopy(array, l, left, 0, n1);
        System.arraycopy(array, m + 1, right, 0, n2);
        int i = 0, j = 0;
        for (int k = l; k <= r; k++) {
            if (i < n1 && j < n2) {
                if (left[i] <= right[j]) {
                    array[k] = left[i];
                    i++;
                } else {
                    array[k] = right[j];
                    j++;
                }
            } else if (i < n1) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
        }
    }

}
