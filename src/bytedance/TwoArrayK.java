package bytedance;


// 寻找两个数组中的第 k 大
public class TwoArrayK {

    public static void main(String[] args) {

        int[] arr = new int[] {
                1, 8, 7, 5, 6, 4, 2, 3
        };

        int[] arr1 = new int[] {
                1, 3, 5, 7, 10
        };
        int[] arr2 = new int[] {
                1, 2, 4, 6
        };

        //System.out.println(findK(arr, 4));
        System.out.println(findK(arr1, arr2, 8));
    }

    // 查找两个有序数组第 k 大的数
    private static int findK(int[] arr1, int[] arr2, int k) {

        if (arr1.length + arr2.length < k) {
            throw new IllegalArgumentException("can not find k");
        }

        int value = Integer.MIN_VALUE;

        int p1 = 0;
        int p2 = 0;
        int p = 0;
        while (p1 < arr1.length && p2 < arr2.length && p < k) {
            if (arr1[p1] < arr2[p2]) {
                value = arr1[p1];
                p1++;
            } else {
                value = arr2[p2];
                p2++;
            }
            p++;
        }

        while (p1 < arr1.length && p < k) {
            value = arr1[p1++];
            p++;
        }

        while (p2 < arr2.length && p < k) {
            value = arr2[p2++];
            p++;
        }

        return value;
    }

    // 查找一个无序数组第 k 大的数
    private static int findK(int[] arr, int k) {

        return findK_(arr, 0, arr.length - 1, k - 1);
    }

    private static int findK_(int[] arr, int start, int end, int k) {

        int base = partition(arr, start, end);
        if (base == k) {
            return arr[base];
        }
        if (base > k) {
            return findK_(arr, start, base-1, k);
        } else {
            return findK_(arr, base+1, end, k);
        }
    }

    private static int partition(int[] arr, int start, int end) {

        int base = arr[start];
        while (start < end) {
            while (start < end && arr[end] >= base) {
                end--;
            }
            arr[start] = arr[end];
            while (start < end && arr[start] <= base) {
                start++;
            }
            arr[end] = arr[start];
        }
        arr[start] = base;
        System.out.println("find k " + start);
        return start;
    }

}
