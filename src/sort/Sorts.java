package sort;

public class Sorts {



    public static void main(String[] args) {


        int[] ns = new int[] {
                8, 9, 8, 2, 3, 4, 7, 1
        };
        // selectSort(ns);
        mergeSort(ns);
        for (int i = 0; i< ns.length; i++) {
            System.out.print(ns[i] + " ");
        }
    }


    /**
     * 冒泡排序
     * 相邻交换
     */
    private static void bubbleSort(int[] ns) {

        // i = 0 ... n-2
        //     0 ... n-3
        for (int i = 0; i < ns.length; i++) {
            for (int j = 0; j < ns.length - i - 1; j++) {
                if (ns[j] > ns[j+1]) {
                    swap(ns, j, j+1);
                }
            }
        }
    }

    private static void swap(int[] ns, int k1, int k2) {
        int temp = ns[k1];
        ns[k1] = ns[k2];
        ns[k2] = temp;
        // System.out.println("swipe " + k1 + "/" + k2);
    }


    /**
     * 选择排序
     */
    private static void selectSort(int[] ns) {

        for (int i = 0; i < ns.length; i++) {
            int index = i;
            int min = ns[i];
            for (int j = i; j < ns.length; j++) {

                if (min > ns[j]) {
                    index = j;
                    min = ns[j];
                }
            }
            if (index != i) {
                swap(ns, i, index);
            }
        }

    }



    /**
     * 插入排序
     */
    private static void insertSort(int[] ns) {

    }


    /**
     * 并归排序
     *
     *
     */
    private static void mergeSort(int[] ns) {

        mergeSort_(ns,0, ns.length - 1);
    }

    private static void mergeSort_(int[] ns, int start, int end) {


        if (start >= end) {
            return;
        }

        int[] temp = new int[ns.length];
        int middle = (start + end) / 2;
        mergeSort_(ns, start, middle);
        mergeSort_(ns, middle + 1, end);
        mergeSortedArray(ns, start, middle, end, temp);
    }

    // 对两个已经排序的列表合并排序
    private static void mergeSortedArray(int[] ns, int start, int middle, int end, int[] temp) {

        int i = start;
        int j = middle + 1;
        int p = i;
        while (i <= middle && j <= end) {
            if (ns[i] < ns[j]) {
                temp[p++] = ns[i++];
            } else {
                temp[p++] = ns[j++];
            }
        }
        while (i <= middle) {
            temp[p++] = ns[i++];
        }

        while (j <= end) {
            temp[p++] = ns[j++];
        }

        for (int pointer = start; pointer <= end; pointer++) {
            ns[pointer] = temp[pointer];
        }
    }

    /**
     * 快速排序
     *
     * 
     */
    private static void quickSort(int[] ns) {


    }


}
