package dataStructure.linearDataStructures;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class StackUsage {

    //Valid Parentheses
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (stack.isEmpty() || c == ')' && stack.pop() != '(' || c == '}' && stack.pop() != '{' || c == ']' && stack.pop() != '[') {
                return false;
            }
        }
        return stack.isEmpty();
    }

    //Find the area of the largest rectangle in a histogram:
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0){
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        //Keep a stack of indices with increasing heights.
        //Calculate the area of rectangles that can already be determined.
        for (int i = 0; i < heights.length; i++){
            while (stack.peek() >= 0 && heights[i] < heights[stack.peek()]){
                max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        //Calculate the area of the rest rectangles.
        while (stack.size() > 1){
            max = Math.max(max, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return max;
    }

}
