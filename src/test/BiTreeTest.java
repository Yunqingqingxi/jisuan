package test;
class BiTreeNode {
    char data;
    BiTreeNode left;
    BiTreeNode right;

    public BiTreeNode(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BiTree {
    BiTreeNode root;

    public BiTree(BiTreeNode root) {
        this.root = root;
    }

    // 先根遍历
    public void preOrderTraversal(BiTreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    // 中根遍历
    public void inOrderTraversal(BiTreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    // 后根遍历
    public void postOrderTraversal(BiTreeNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }
}

public class BiTreeTest {
    public static void main(String[] args) {
        // 构建二叉树
        BiTreeNode root = new BiTreeNode('A');
        root.left = new BiTreeNode('B');
        root.right = new BiTreeNode('C');
        root.left.left = new BiTreeNode('D');
        root.left.right = new BiTreeNode('E');

        BiTree binaryTree = new BiTree(root);

        // 输出先根、中根、后根遍历结果
        System.out.println("先根遍历结果：");
        binaryTree.preOrderTraversal(binaryTree.root);

        System.out.println("\n中根遍历结果：");
        binaryTree.inOrderTraversal(binaryTree.root);

        System.out.println("\n后根遍历结果：");
        binaryTree.postOrderTraversal(binaryTree.root);
    }
}
