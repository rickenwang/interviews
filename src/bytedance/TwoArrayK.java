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

    // log(m+n) 的解法
    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length1 = nums1.length, length2 = nums2.length;
            int totalLength = length1 + length2;
            if (totalLength % 2 == 1) {
                int midIndex = totalLength / 2;
                double median = getKthElement(nums1, nums2, midIndex + 1);
                return median;
            } else {
                int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
                double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
                return median;
            }
        }

        // 寻找两个数组中第 k 大的数
        public int getKthElement2(int[] nums1, int[] nums2, int k) {

            int len1 = nums1.length, len2 = nums2.length;
            int offset1 = 0, offset2 = 0;

            while (true) {

                if (offset1 == len1) {
                    return nums2[offset2 + k - 1];
                }

                if (offset2 == len2) {
                    return nums1[offset1 + k - 1];
                }

                if (k == 1) {
                    return Math.min(nums1[offset1], nums2[offset2]);
                }

                int half = k / 2;
                int newOffset1 = Math.min(offset1 + half, len1) - 1;
                int newOffset2 = Math.min(offset2 + half, len2) - 1;

                if (nums1[newOffset1] <= nums2[newOffset2]) {
                    k -= (newOffset1 - offset1 + 1);
                    offset1 = newOffset1 + 1;
                } else {
                    k -= (newOffset2 - offset2 + 1);
                    offset2 = newOffset2 + 1;
                }

            }

        }

        public int getKthElement(int[] nums1, int[] nums2, int k) {
            /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
             * 这里的 "/" 表示整除
             * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
             * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
             * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
             * 这样 pivot 本身最大也只能是第 k-1 小的元素
             * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
             * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
             * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
             */

            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;
            int kthElement = 0;

            while (true) {
                // 边界情况
                if (index1 == length1) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }
    }
}
