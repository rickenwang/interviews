package sort;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.StreamSupport;

public class BinaryTree {


    public static void main(String[] args) {

        Node d = new Node("d", null, null);
        Node e = new Node("e", null, null);
        Node f = new Node("f", null, null);
        Node g = new Node("g", null, null);

        Node b = new Node("b", d, e);
        Node c = new Node("c", f, g);

        Node a = new Node("a", b, c);

        dfsTraversal_1(a, "a");
    }

    // 深度优先遍历 - 递归
    private static void dfsTraversal_1(Node node, String route) {

        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.println(route);
        }

        if (node.left != null) {
            dfsTraversal_1(node.left, route + " " + node.left.data);
        }

        if (node.right != null) {
            dfsTraversal_1(node.right, route + " " + node.right.data);
        }

    }

    // 深度优先遍历 - 迭代
    private static void dfsTraversal_2(Node root) {

        Stack stack = new Stack();
        stack.push(root);
        Stack route = new Stack();

        List<List<Node>> paths = new LinkedList<>();
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            route.push(node);

            // System.out.println(node.data + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.left == null && node.right == null) {
                paths.add(new LinkedList<>(route.mData));
            }
        }

        System.out.println(paths);
    }


    // 广度优先遍历 - 迭代
    private static void bfsTraversal(Node root) {

        Queue queue = new Queue();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.data);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }


    static class Stack {
        LinkedList<Node> mData = new LinkedList<>();

        void push(Node data) {
            mData.push(data);
            // System.out.println(data.data);
        }

        Node pop() {
            return mData.pop();
        }

        boolean isEmpty() {
            return mData.isEmpty();
        }
    }

    static class Queue {

        LinkedList<Node> mData = new LinkedList<>();

        void add(Node data) {
            mData.addLast(data);
            // System.out.println(data.data);
        }

        Node remove() {
            return mData.removeFirst();
        }

        boolean isEmpty() {
            return mData.isEmpty();
        }
    }

    static class Node {

        String data;
        Node left;
        Node right;

        Node(String data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

}
