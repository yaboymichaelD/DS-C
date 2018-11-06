package spelling;

import java.util.Set;

/*****************************************************
 *    Title: AVLTree
 *    Author:
 *    Site owner/sponsor: Rosetta code
 *    Date: Unspecified
 *    Code version: Unspecified
 *    Availability: https://rosettacode.org/wiki/AVL_tree
 *    (Accessed 25 October 2018)
 *    Modified:
 *     1. Uses generic type for data instead of int
 *     2. Added add method - to match Dictionary interface - this just calls the insert method
 *     3. Added contains method - to match Dictionary interface
 *****************************************************/

public class AVLTree<E extends Comparable<E>> {

    private class Node {
        private E key;
        private int balance;
        private int height;
        private Node left;
        private Node right;
        private Node parent;

        Node(E key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }

    private Node root;

    public int getHeight() {
        return root.height;
    }

    public void add(E word) {
        insert(word);
    }

    public boolean contains(E word) {
        Node current = root;
        return containsSub(root, word);
    }

    private boolean containsSub(Node node, E word) {
        if (node == null)
            return false;
        else {
            int result = node.key.compareTo(word);
            if (result == 0)
                return true;
            else if (result > 0) {
                //go left
                return containsSub(node.left, word);
            } else {
                //go right
                return containsSub(node.right, word);

            }
        }
    }

    public boolean insert(E key) {
        if (root == null) {
            root = new Node(key, null);
            return true;
        }

        Node n = root;
        while (true) {
            if (n.key == key)
                return false;

            Node parent = n;

            //boolean goLeft = n.key > key;
            boolean goLeft = false;
            if (n.key.compareTo(key) > 0)
                goLeft = true;

            n = goLeft ? n.left : n.right;

            if (n == null) {
                if (goLeft) {
                    parent.left = new Node(key, parent);
                } else {
                    parent.right = new Node(key, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void rebalance(Node n) {
        setBalance(n);

        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);

        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }

    private Node rotateLeft(Node a) {

        Node b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null)
            a.right.parent = a;

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateRight(Node a) {

        Node b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null)
            a.left.parent = a;

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateLeftThenRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private Node rotateRightThenLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(Node n) {
        if (n == null)
            return -1;
        return n.height;
    }

    private void setBalance(Node... nodes) {
        for (Node n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }

    private void reheight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }
}
