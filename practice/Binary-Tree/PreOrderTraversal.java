public static void preorderTraverse(Node root) {
    if(root == null) {
        return;
    }

    Stack<Node> children = new Stack<>();
    children.push(root);

    while(!children.isEmpty()) {
        Node curr = children.pop();
        System.out.println(curr.val);
        if(curr.right != null) {
            children.push(curr.right);
        }
        if(curr.left != null) {
            children.push(curr.left);
        }
    }
}