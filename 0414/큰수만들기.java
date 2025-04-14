import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            
            // 스택의 가장 위 숫자가 현재 숫자(c) 보다 작으면 pop()하고 변경가능 횟수를 1번 줄임
            while (!stack.isEmpty() && k > 0 && stack.peek() < c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        // 루프를 다 돌았는데 k가 0이 아니라면 뒤쪽의 숫자를 k개만큼 지움
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}
