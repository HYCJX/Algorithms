package dataStructure;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ListUsage {

    /* Array List */

    //Find the first and the last occurance of an element in a sorted array --- Modified binary search:
    public static int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int leftIndex = binarySearchM1(nums, target, true);
        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return targetRange;
        }
        targetRange[0] = leftIndex;
        targetRange[1] = binarySearchM1(nums, target, false) - 1;
        return targetRange;
    }

    private static int binarySearchM1(int[] nums, int target, boolean isFindingLeft) {
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

    //Search an element in a rotated sorted list --- Modified binary search:
    public static int searchRotatedSorted(int[] nums, int target) {
        return binarySearchM2(nums, target, 0, nums.length - 1);
    }

    private static int binarySearchM2(int[] arr, int key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (arr[mid] == key) {
            return mid;
        }
        if (arr[low] <= arr[mid]) {
            if (key >= arr[low] && key <= arr[mid]) {
                return binarySearchM2(arr, key, low, mid - 1);
            }
            return binarySearchM2(arr, key, mid + 1, high);
        }
        if (key >= arr[mid] && key <= arr[high]) {
            return binarySearchM2(arr, key, mid + 1, high);
        }
        return binarySearchM2(arr, key, low, mid - 1);
    }

    //Sort a list with only 0s, 1s and 2s in one pass:
    public static void sortThreeNumbers(int[] nums) {
        int i = 0, j = nums.length-1, k = 0;
        while(i <= j) {
            if(nums[i] == 0) {
                i++;
            } else if(nums[i] == 1) {
                if(nums[j] == 2) {
                    j--;
                    continue;
                } else if (nums[j] == 0) {
                    nums[i] = 0;
                    nums[j] = 1;
                    i++;
                } else {
                    k = Math.max(i + 1, k);
                    while (k < nums.length && nums[k] != 0) {
                        k++;
                    }
                    if (k < nums.length){
                        nums[k] = 1;
                        nums[i] = 0;
                    }
                    i++;
                }
            } else if (nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }
    }

    /* Linked List */

    //Remove the nth node counting from the end of a linked list:
    public static ListNode removeNthFromEnd(ListNode head, int n) {
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
    public static ListNode mergeKLists(ListNode[] lists) {
        Comparator<ListNode> cmp = Comparator.comparingInt(o -> o.val);
        Queue<ListNode> priorityQueue = new PriorityQueue<ListNode>(cmp);
        for (ListNode l : lists) {
            if (l != null) {
                priorityQueue.add(l);
            }
        }
        ListNode head = new ListNode(0);
        ListNode point = head;
        while (!priorityQueue.isEmpty()) {
            ListNode nextNode = priorityQueue.poll();
            point.next = nextNode;
            point = point.next;
            if (nextNode.next != null) {
                priorityQueue.add(nextNode.next);
            }

        }
        return head.next;
    }

    private static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }
}
