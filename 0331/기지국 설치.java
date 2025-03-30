import java.util.*;

class Solution {
    
    public int solution(int n, int[] stations, int w) {
        // 기존에 설치된 기지국의 전파가 닿지 않는 영역의 길이들을 저장
        List<Integer> blanks = new ArrayList<>();
        // blanks 리스트를 채우기 위해 탐색을 시작할 아파트
        int pre = 1;
        for (int s : stations) {
            // pre 아파트부터 현재 탐색 중인 기지국의 전파 범위 사이에 전파가 닿지 않는 영역이 있는지 확인
            int diff = s - w - pre;
            // 영역이 있다면 blanks에 그 크기를 추가
            if (diff > 0) {
                blanks.add(diff);
            }
            // pre 아파트를 현재 탐색 중인 기지국의 전파범위 바로 다음 칸으로 이동
            pre = s + w + 1;
        }
        
        // 마지막 기지국의 전파 범위부터 마지막 아파트까지의 영역 탐색
        int last = stations[stations.length - 1];
        // 전파가 닿지 않는 공간이 있으면 blanks에 추가
        if (last + w < n) {
            blanks.add(n - last - w);
        }
        
        int answer = 0;
        // 각 blank를 탐색하며 필요한 기지국의 개수 계산
        for (int blank : blanks) {
            if (blank > 2 * w + 1) { // 전파가 닿지 않는 영역이 2 * w + 1보다 큰 경우 기지국을 두 개 이상 설치해야 함
                if (blank % (2 * w + 1) == 0) {
                    answer += blank / (2 * w + 1);
                } else {
                    answer += blank / (2 * w + 1) + 1;
                }
            } else { // 그 외의 경우에는 기지국을 하나만 설치해도 됨
                answer++;
            }
        }
        
        return answer;
    }
}
