package sort;

/**
 * 排序算法
 */
public class Sort {


    public static void main(String[] args) {

        int[] arr = new int[] {
                9,1,4,5,2,3,7,
        };
        mergeSort(arr);

        System.out.println("complete");
    }



    private static void quickSort(int[] ns) {
        sort(ns, 0, ns.length-1);
    }


    private static void sort(int[] ns, int start, int end) {

        if (start >= end) {
            return;
        }
        int mid = partition(ns, start, end);
        sort(ns, start, mid);
        sort(ns, mid + 1, end);
    }

    private static int partition(int[] ns, int start, int end) {
        int mid = ns[start];
        while (start < end) {
            while (start < end && ns[end] > mid) end--;
            ns[start] = ns[end];
            while (start < end && ns[start] < mid) start++;
            ns[end] = ns[start];
        }
        ns[start] = mid;
        return start;
    }


    // 并归排序
    private static void mergeSort(int[] ns) {
        mergeSortedArr(ns, new int[ns.length], 0, ns.length - 1);
    }


    private static void mergeSortedArr(int[] ns, int[] temp, int start, int end) {


        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSortedArr(ns, temp, start, mid);
        mergeSortedArr(ns, temp, mid + 1, end);
        mergeSorted(ns, temp, start, mid, end);
    }

    private static void mergeSorted(int[] ns, int[] temp, int start, int mid, int end) {


        int i=start;
        int j=mid+1;
        int k=0;
        while (i<=mid && j<=end){
            temp[k++] = ns[i] <= ns[j] ? ns[i++] : ns[j++];
        }
        while (i <=mid){
            temp[k++] = ns[i++];
        }
        while ( j<=end){
            temp[k++] = ns[j++];
        }
        //把数据复制回原数组
        for (i=0; i<k; ++i){
            ns[start+i] = temp[i];
        }
    }

}
