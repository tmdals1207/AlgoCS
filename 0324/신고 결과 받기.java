import java.util.*;

class Solution {
    
    static int userCnt;
    // 닉네임 -> id 변환
    static Map<String, Integer> idMapper = new HashMap<>();
    // 자신을 신고한 user 목록
    static HashSet<Integer>[] reportList;
    
    public int[] solution(String[] idList, String[] report, int k) {
        
        userCnt = idList.length;
        
        for (int i=0; i<userCnt; i++) {
            idMapper.put(idList[i], i);
        }
        
        reportList = new HashSet[userCnt];
        for (int i=0; i<userCnt; i++) {
            reportList[i] = new HashSet<Integer>();
        }
        
        // 자신을 신고한 user 목록 업데이트
        for (String r : report) {
            String[] split = r.split(" ");
            int id1 = idMapper.get(split[0]);
            int id2 = idMapper.get(split[1]);
            
            reportList[id2].add(id1);
        }
        
        int[] answer = new int[userCnt];
        for (HashSet<Integer> list : reportList) {
            if (list.size() < k) { // 정지가 아닌 경우
                continue;
            }
            
            for (Integer id : list) {
                answer[id] += 1;
            }
        }
        
        return answer;
    }
}
