package s0414;

import java.util.*;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42883
 * 어떤 숫자에서 k만큼의 수를 제거했을때 얻을 수 있는 수 중 가장 큰 수 구하기
 */
public class Solution_Pro42883 {					//큰 수 만들기

	class Solution {
	    public String solution(String number, int k) {
	        Stack<Character> s = new Stack<>();
	        int cnt = 0;
	        for(int i = 0; i < number.length(); i++){
	            while(!s.isEmpty() && s.peek() < number.charAt(i) && cnt < k){
	                s.pop();
	                cnt++;
	            }
	            s.push(number.charAt(i));
	        }
	        
	        StringBuilder str = new StringBuilder();
	        while(!s.isEmpty()){
	            str.append(s.pop());
	        }
	        
	        if(cnt < k){
	            return str.reverse().toString().substring(0, str.toString().length() - (k-cnt));
	        }
	        
	        return str.reverse().toString();
	    }
	}

}
