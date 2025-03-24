import java.util.*;

// 유저가 신고한 사람을 저장해 두고 만약 이용정지를 당하면 메일을 받을 HashMap<String, HashSet<String>>
// 신고를 당할 때마다 Value를 증가시켜서 K가 넘는지 확인할 HashMap
// 신고를 K번 이상 당해서 정지당한 사람을 저장할 HashSet

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        // 유저가 신고한 사람들을 저장한 HashMap
        HashMap<String, HashSet<String>> reportedMap = new HashMap<String, HashSet<String>>();
        // 유저가 신고당한 횟수를 저장한 HashMap
        HashMap<String, Integer> reportedCount = new HashMap<String, Integer>();
        
        Set<String> bannedSet = new HashSet<String>();
        
        for (String id : id_list) {
            reportedMap.put(id, new HashSet<String>());
            reportedCount.put(id, 0);
        }
        
        for (String input : report) {
            StringTokenizer st = new StringTokenizer(input);
            String s1 = st.nextToken(); // 신고한 사람
            String s2 = st.nextToken(); // 신고 당한 사람
            
            // 유저가 신고한 사람들을 저장한 HashMap안의 Value값인 HashSet 추출.
            HashSet<String> reports = reportedMap.get(s1);
            // 해당 HashSet을 HashMap안의 Value로 다시 저장.
            reportedMap.put(s1, reports);
            
            // 새로운 신고라면 (중복 신고 방지)
            if (!reports.contains(s2)) {
                reports.add(s2); // 신고한 유저 목록에 추가
                reportedCount.put(s2, reportedCount.get(s2) + 1); // 신고당한 횟수 증가

                // 신고 횟수가 K 이상이면 정지 목록에 추가
                if (reportedCount.get(s2) >= k) {
                    bannedSet.add(s2);
                }
            }
        }

        // 각 유저가 받을 신고 처리 결과 메일 개수 계산
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            int cnt = 0;

            // 해당 유저가 신고한 사람들 중 정지된 사람이 있는지 확인
            for (String s : reportedMap.get(id)) {
                if(bannedSet.contains(s)) {
                    cnt++;
                }
            }
            answer[i] = cnt;
            
        }
        
        return answer;
    }
}