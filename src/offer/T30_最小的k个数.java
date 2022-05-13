package offer;

public class T30_最小的k个数 {


    public static void main(String[] args) {

        int[] arr = new int[] {
                8, 9, 1, 2, 5, 7, 3,
        };
        int k = 4;
        findK(arr, 4);
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    private static void findK(int[] arr, int k) {
        findK(arr, 0, arr.length - 1, k);
    }

    private static void findK(int[] arr, int start, int end, int k) {

        if (start >= end) {
            return;
        }

        int base = partition(arr, start, end);

        if (base == k) {
            return;
        }

        if (base > k) {
            findK(arr, start, base - 1, k);
        } else {
            findK(arr, base + 1, end, k - base);
        }
    }


    private static int partition(int[] arr, int start, int end) {

        int base = arr[start];

        while (start < end) {

            while (start < end && base < arr[end]) {
                end--;
            }
            arr[start] = arr[end];

            while (start < end && base > arr[start]) {
                start++;
            }
            arr[end] = arr[start];
        }

        arr[start] = base;
        return start;
    }

}
