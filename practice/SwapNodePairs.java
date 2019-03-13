/*
    Swap node pairs.

    Input: 1->2->3->4->5
    Output: 2->1->4->3->5
*/
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        // 2->1->3->4
        //    p  a  b
        ListNode newHead = head.next;
        ListNode prev = null;
        ListNode a = head;
        ListNode b = head.next;
        
        while(a.next != null){            
            if(prev != null){
                prev.next = b;
            }
            
            a.next = b.next;
            b.next = a;            
            
            if(a.next == null){
                break;
            }
            
            b = a.next.next;
            prev = a;
            a = a.next;            
        }
        
        return newHead;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */