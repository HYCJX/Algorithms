package DataStructure;

public class ListUsage {

    //Remove the nth node counting from the end of a linked list:
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    private class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }
}