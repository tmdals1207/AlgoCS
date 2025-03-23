import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Set<String>> reportMap = new HashMap<>();
        HashMap<String, Integer> declareMap = new HashMap<>();
        int[] result = new int[id_list.length];
        
        for(int i = 0; i < id_list.length; i++) {
            reportMap.put(id_list[i], new HashSet<>());
            declareMap.put(id_list[i], 0);
        }
        
        for(int i = 0; i < report.length; i++) {
            String[] oneCase = report[i].split(" ");
            
            if(!reportMap.get(oneCase[0]).contains(oneCase[1])) {
                reportMap.get(oneCase[0]).add(oneCase[1]);
                declareMap.put(oneCase[1], declareMap.get(oneCase[1]) + 1);
            }
            
        }
        
        for(int i = 0; i < id_list.length; i++) {
            for(String target: reportMap.get(id_list[i])) {
                if(declareMap.get(target) >= k) {
                    result[i] += 1;
                }
            }
        }
        
        return result;
    }
}