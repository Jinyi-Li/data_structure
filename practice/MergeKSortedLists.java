/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null; 
        }
        if(lists.length == 1){
            return lists[0];
        }
       
        PriorityQueue<ListNode> nodes = new PriorityQueue<>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode a, ListNode b){
                if(a.val == b.val){
                    return 0;
                }
                return a.val - b.val > 0 ? 1 : -1;
            }
        });
        for(ListNode head : lists){
            if(head != null){
                nodes.offer(head);
            }
        }
        
        // find the new head
        ListNode finalHead = null;
        if(!nodes.isEmpty()){
            finalHead = nodes.poll();  
            if(finalHead.next != null){
                nodes.offer(finalHead.next);
            }        
        }
        
        // merge the rest
        ListNode current = finalHead;
        while(!nodes.isEmpty()){
            ListNode node = nodes.poll();
            if(node.next != null){
                nodes.offer(node.next);
            }            
            current.next = node;
            current = node;
        }
        return finalHead;
    }
}