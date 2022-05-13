package bytedance;


// 合并链表
public class MergeList {

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

        Node root1 = new Node(0, new Node(2, new Node(5, new Node(7, new Node(10, null)))));
        Node root2 = new Node(1, new Node(8, new Node(9, new Node(11, new Node(20, null)))));
        Node root = converseList(root1);
        while (root != null) {
            System.out.println(root.data);
            root = root.next;
        }
    }



    private static Node mergeTwoList(Node root1, Node root2) {

        Node root = new Node(0, null);
        Node p = root;

        while (root1 != null && root2 != null) {
            if (root1.data < root2.data) {

                p.next = root1;
                root1 = root1.next;
            } else {
                p.next = root2;
                root2 = root2.next;
            }
            p = p.next;
        }

        while (root1 != null) {
            p.next = root1;
            root1 = root1.next;
            p = p.next;
        }

        while (root2 != null) {
            p.next = root2;
            root2 = root2.next;
            p = p.next;
        }
        return root.next;
    }

    private static Node converseList(Node root) {

        if (root == null || root.next == null) {
            return root;
        }
        Node p1 = root;
        Node p2 = root.next;
        Node p3 = null;
        while (p2 != null) {

            p3 = p2.next;
            p2.next = p1;

            if (p1 == root) {
                p1.next = null;
            }

            p1 = p2;
            p2 = p3;
        }
        return p1;
    }



    static class Node {

        int data;
        Node next;

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
