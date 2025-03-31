class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int idx = 1; // 현재 확인 중인 아파트의 시작 인덱스 (1부터 시작)
        int range = 2 * w + 1; // 하나의 기지국이 커버할 수 있는 범위
        
        for (int station : stations) { // 기존 기지국들의 위치를 순회하며 확인
            int left = station - w; // 기지국이 커버할 수 있는 왼쪽 범위의 끝
            
            if (left > idx) { // 기지국이 커버하지 않는 왼쪽의 빈 구역이 존재할 경우
                int gap = left - idx; // 빈 구역의 길이
                answer += (gap + range - 1) / range; // 빈 구역을 커버할 수 있도록 필요한 기지국 개수 추가
            }
            idx = station + w + 1; // 다음 확인할 아파트 인덱스를 기지국 범위의 오른쪽 끝 다음으로 이동
        }
        
        // 마지막 기지국 이후 남은 구역이 있을 경우 처리
        if (idx <= n) {
            int gap = n - idx + 1; // 마지막 기지국 이후의 오른쪽 빈 구역 길이 
            answer += (gap + range - 1) / range; // 필요한 기지국 개수 추가
        }

        return answer;
    }
}
