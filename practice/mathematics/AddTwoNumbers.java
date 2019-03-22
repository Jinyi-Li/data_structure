/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    private boolean carryOn;
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = getDigitSum(l1, l2);
        ListNode curr = head;                                
                
        l1 = l1.next;
        l2 = l2.next;
        while(l1 != null && l2 != null){
            ListNode digitSum = getDigitSum(l1, l2);
            curr.next = digitSum;
            curr = curr.next;     
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while(l1 != null){
            ListNode digitSum = getDigitSum(l1, new ListNode(0));
            curr.next = digitSum;
            curr = curr.next;
            l1 = l1.next;
        }
        while(l2 != null){
            ListNode digitSum = getDigitSum(l2, new ListNode(0));
            curr.next = digitSum;
            curr = curr.next;
            l2 = l2.next;
        }
    
        if(carryOn){
            curr.next = new ListNode(1);            
        }
        return head;
    }
    
    private ListNode getDigitSum(ListNode l1, ListNode l2){
        if(carryOn){                        // carry 1 digit
            int sum = l1.val + l2.val + 1;
            if(sum >= 10){                  // still carry 1                 
                return new ListNode(sum % 10);
            }else{                          // no carryon anymore
                carryOn = false;
                return new ListNode(sum);
            }            
        }else{                              // no carryon
            int sum = l1.val + l2.val;
            if(sum >= 10){                  // now carries 1
                carryOn = true;
                return new ListNode(sum % 10);
            }else{                          // still no carryon
                return new ListNode(sum);
            }            
        }                
    }
}