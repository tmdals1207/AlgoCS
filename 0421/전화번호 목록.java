package s0421;

import java.util.HashMap;
import java.util.Map;

/*
 * 
 */
public class Solution_Pro42577 {					//전화번호 목록

	class Solution {
	    public boolean solution(String[] phone_book) {
	        Map<String, Integer> map = new HashMap<>();
	        for(int i =0; i < phone_book.length; i++){
	            map.put(phone_book[i], 1);
	        }
	        
	        for(String str : map.keySet()){
	            String head = "";
	            for(int i = 0; i < str.length()-1; i++){    //자기자신 제외
	                head += str.charAt(i);
	                if(map.containsKey(head)){
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	}

}
