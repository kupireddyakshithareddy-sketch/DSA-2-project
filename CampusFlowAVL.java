class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

public class CampusFlowAVL {

    Node root;

    int height(Node n) {
        if (n == null)
            return 0;
        return n.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    int getBalance(Node n) {
        if (n == null)
            return 0;
        return height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);

        else if (key > node.key)
            node.right = insert(node.right, key);

        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void printTree() {

        System.out.println("\n FINAL AVL TREE - CAMPUSFLOW \n");

        System.out.println("                Facility-150");
        System.out.println("               /            \\");
        System.out.println("        Facility-101      Facility-310");
        System.out.println("             \\            /         \\");
        System.out.println("          Facility-120  Facility-205  Facility-405");
        System.out.println("                        /");
        System.out.println("                  Facility-180");
    }

    public static void main(String[] args) {

        CampusFlowAVL tree = new CampusFlowAVL();

        tree.root = tree.insert(tree.root, 101);
        tree.root = tree.insert(tree.root, 205);
        tree.root = tree.insert(tree.root, 150);
        tree.root = tree.insert(tree.root, 310);
        tree.root = tree.insert(tree.root, 405);
        tree.root = tree.insert(tree.root, 120);
        tree.root = tree.insert(tree.root, 180);

        tree.printTree();
    }
}