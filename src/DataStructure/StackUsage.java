package DataStructure;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackUsage {

    //Valid Parentheses
    public boolean isValid(String s) {
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
}
