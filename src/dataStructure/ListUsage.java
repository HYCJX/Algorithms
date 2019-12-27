package dataStructure;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ListUsage {

    /* Array List */

    //Find the first and the last occurance of an element in a sorted array --- Modified binary search:
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1,-1};
        int leftIndex = binarySearch(nums, target, true);
        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return targetRange;
        }
        targetRange[0] = leftIndex;
        targetRange[1] = binarySearch(nums, target, false) - 1;
        return targetRange;
    }

    private int binarySearch(int[]nums, int target, boolean isFindingLeft) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target || (isFindingLeft && target == nums[mid])) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /* Linked List */

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

    //Merge k sorted linked lists:
    public ListNode mergeKLists(ListNode[] lists) {
        Comparator<ListNode> cmp = Comparator.comparingInt(o -> o.val);
        Queue<ListNode> priorityQueue = new PriorityQueue<ListNode>(cmp);
        for(ListNode l : lists){
            if(l!=null){
                priorityQueue.add(l);
            }
        }
        ListNode head = new ListNode(0);
        ListNode point = head;
        while(!priorityQueue.isEmpty()) {
            ListNode nextNode = priorityQueue.poll();
            point.next = nextNode;
            point = point.next;
            if (nextNode.next != null) {
                priorityQueue.add(nextNode.next);
            }

        }
        return head.next;
    }

    private class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }
}
