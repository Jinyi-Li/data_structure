import java.util.ArrayList;
import java.util.List;

/*
    Given a linked list with head and a list of nodes, cluster the nodes if they're adjacent in the linked list.

    Input:
    LL: 0 -> 1 -> 2 -> 3 -> 4 -> 5 
    nodes: [0, 2, 3, 5]

    Output:
    [
        [0],
        [2, 3],
        [5]
    ]
*/
class Solution {

    public List<List<Node>> clusterList(Node head, List<Node> nodes){
        if(head == null || nodes == null || nodes.size() == 0){
            throw new IllegalArgumentException();
        }

        List<List<Node>> res = new ArrayList<>();

        Node curr = head;
        int idx = 0;

        while(curr != null){
            if(curr.val == nodes.get(idx).val){
                List<Node> nodeList = new ArrayList<>();
                nodeList.add(curr);
                idx++;
                curr = curr.next;
                while(curr != null && curr.val == nodes.get(idx).val){
                    nodeList.add(curr);
                    curr = curr.next;
                    idx++;
                }
                res.add(nodeList);
            }
            if(curr != null){
                curr = curr.next;
            }
        }
        return res;
    }


    private static class Node {
        int val;
        Node next;
        private Node(int value){
            val = value;
        }
        public String toString(){
            return String.valueOf(val);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Node> allNodes = new ArrayList<>();

        Node head = new Node(0);
        Node curr = head;
        allNodes.add(curr);

        for(int i = 1; i < 10; i++){
            curr.next = new Node(i);
            allNodes.add(curr.next);
            curr = curr.next;
        }

        List<Node> data = new ArrayList<>();
        data.add(allNodes.get(0));
        data.add(allNodes.get(4));
        data.add(allNodes.get(5));
        data.add(allNodes.get(9));

        System.out.println(sol.clusterList(head, data));

    }
}