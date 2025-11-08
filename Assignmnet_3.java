class Node {
    String data;
    Node left, right;

    public Node(String item) {
        data = item;
        left = right = null;
    }
}

public class Assignmnet_3 {
    int preIndex = 0;

    public Node buildTree(String[] preorder, String[] inorder, int start, int end) {
        if (start > end) {
            return null;
        }

        Node root = new Node(preorder[preIndex++]);
        if (start == end) {
            return root;
        }

        int inIndex = indexfinder(inorder, start, end, root.data);
        root.left = buildTree(preorder, inorder, start, inIndex - 1);
        root.right = buildTree(preorder, inorder, inIndex + 1, end);
        return root;
    }

    public int indexfinder(String[] arr, int start, int end, String value) {
        for (int i = start; i <= end; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        Assignmnet_3 tree = new Assignmnet_3();
        String[] preorder = {"F", "SF1", "Q1", "T1", "T4", "Q2", "T5", "SF2", "Q3", "T2"};
        String[] inorder = {"T1", "Q1", "T4", "SF1", "T5", "Q2", "F", "T2", "Q3", "SF2"};

        Node root = tree.buildTree(preorder, inorder, 0, inorder.length - 1);

        System.out.print("Inorder: ");
        tree.inorder(root);
        System.out.println();

        System.out.print("Preorder: ");
        tree.preorder(root);
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder(root);
        System.out.println();
    }
}