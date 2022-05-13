package offer;

public class T28_字符串的排列 {


    public static void main(String[] args) {

        print("abc".toCharArray());

    }


    private static void print(char[] arr) {

        print(arr, 0);
    }

    private static void print(char[] arr, int start) {

        if (start == arr.length - 1) {
            printArr(arr);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            swapOnly(arr, start, i);
            print(arr, start + 1);
            swapOnly(arr, start, i);
        }

    }

    private static void printArr(char[] arr) {

        for (char c: arr) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private static char[] swap(char[] arr, int i, int j) {
        char[] newArr = new char[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        char temp = newArr[i];
        newArr[i] = newArr[j];
        newArr[j] = temp;
        return newArr;
    }

    private static void swapOnly(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
