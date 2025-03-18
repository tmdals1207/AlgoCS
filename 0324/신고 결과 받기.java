package s0324;

import java.util.*;
//https://school.programmers.co.kr/learn/courses/30/lessons/92334
/*
 * 
 */
public class Solution_Pro92334 {				//신고 결과 받기

	class Solution {
	    public int[] solution(String[] id_list, String[] report, int k) {
	        int[] answer = new int[id_list.length];
	        
	        HashMap<String, Set<String>> reportMap = new HashMap<>();   //신고자-신고 받은 사람 Map
	        HashMap<String, Integer> resultMap = new HashMap<>();       //신고 받은 사람- 신고 횟수 Map
	        
	        ArrayList<String> list = new ArrayList<>();             //정지 대상자 List
	        
	        HashMap<String, Integer> mail = new HashMap<>();    //유저가 받을 결과 메일, 0으로 초기화
	        for(String name : id_list){
	            mail.put(name, 0);
	        }
	        
	        for(int i =0; i < report.length; i++){
	            String[] temp = report[i].split(" ");
	            reportMap.putIfAbsent(temp[0], new HashSet<>());    //temp[0]에 대한 set 가져오기, 없으면 새로 생성
	            reportMap.get(temp[0]).add(temp[1]);                
	        }
	        
	        //신고받은 사람들의 신고 받은 횟수 계산
	        Set<String> reportKeySet = reportMap.keySet();
	        for(String reporter : reportKeySet){
	            for(String reported : reportMap.get(reporter)){
	                resultMap.put(reported, resultMap.getOrDefault(reported, 0)+1);
	            }
	        }
	        
	        //신고받은 사람들의 누적 신고수가 k 이상이면 정지 대상에 추가
	        Set<String> resultKeySet = resultMap.keySet();
	        for(String reported : resultKeySet){
	            if(resultMap.get(reported) >= k){
	                list.add(reported);
	            }
	        }
	        
	        if(list.isEmpty()){         //정지 대상자가 없으면 전부 0으로 반환
	            return answer;
	        }
	        
	        //신고자가 신고한 정지대상자 계산
	        for(String reporter : reportKeySet){
	            for(String reported : reportMap.get(reporter)){
	                for(String str : list){
	                    if(reported.equals(str)){
	                        mail.put(reporter, mail.get(reporter)+1);
	                    }
	                }
	            }
	        }
	        
	        //결과 배열로 가져오기
	        int d = 0;
	        for(String name : id_list){
	            answer[d] = mail.get(name);
	            d++;
	        }
	        
	        return answer;
	    }
	}
}
