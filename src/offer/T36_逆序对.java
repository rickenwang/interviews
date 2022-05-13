package offer;

public class T36_逆序对 {


    public static void main(String[] args) {

        int[] arr = new int[] {
                1, -2, 3, 10, -4, 7, 2, -5
        };
    }


    //
    private static int find(int[] arr) {

        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

}
