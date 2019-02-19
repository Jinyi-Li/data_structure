/**
 * Given a binary tree, find the subtree with maximum average.
 * Return the root of the subtree.
 *
 * 1 ​
 * / \
 * -5 11
 * / \ / \
 * 1 2 4 -2
 *
 * Return 11.
 */
public class MaximumAvarageSubtree {
    private static class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    private static class ResultType {
        Node currentNode;
        int sum;
        int numOfNodes;
        ResultType(){
            currentNode = null;
            sum = 0;
            numOfNodes = 0;
        }
        ResultType(Node curr, int s, int numNodes) {
            currentNode = curr;
            sum = s;
            numOfNodes = numNodes;
        }
    }

    private ResultType result;

    public Node findMaxAvgSubtree(Node root) {
        if (root == null) {
            return null;
        }

        traverseSubtree(root);
        return result.currentNode;
    }

    private ResultType traverseSubtree(Node root) {
        if(root == null) {
            ResultType rt = new ResultType();
            return rt;
        }

        ResultType leftResult = traverseSubtree(root.left);
        ResultType rightResult = traverseSubtree(root.right);

        ResultType myResult = new ResultType(root,
                leftResult.sum + rightResult.sum + root.val,
                leftResult.numOfNodes + rightResult.numOfNodes + 1);

        if(result == null ||
                myResult.sum * result.numOfNodes
                        > result.sum * myResult.numOfNodes) {
            result = myResult;
        }

        return myResult;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(-5);
        root.right = new Node(11);
        root.left.left = new Node(1);
        root.left.right = new Node(2);
        root.right.left = new Node(4);
        root.right.right = new Node(-1);
       root.left.left.left = new Node(-3);
       root.left.left.right = new Node(4);

        Solution solution = new Solution();
        System.out.println(solution.findMaxAvgSubtree(root).val);

    }
}
