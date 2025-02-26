import java.util.*;

class Solution {
    
    static int arrowCnt;     // 총 화살 수
    static int[] answer;
    static int[] lionShoot;  // 라이언이 맞춘 과녁
    static int[] apichShoot; // 어피치가 맞춘 과녁
    static int maxDiff;      // 라이언이 이겼을 경우 중 최대 점수 차이
    
    static public int[] solution(int n, int[] info) {
        // 초기 설정
        lionShoot = new int[11];
        apichShoot = info;
        arrowCnt = n;

        // 백트래킹 시작
        // 현재 0개의 화살을 쏘았고 10번째 과녁(0점 과녁)부터 맞춘다.
        f(0, 10);
        
        if (maxDiff == 0) { // 라이언이 이긴 경우가 없을 때
            return new int[] {-1};
        }
        return answer;
    }
    
    static private void f(int cnt, int scoreNum) {
        if (cnt == arrowCnt) {    // 모든 화살을 쏜 경우
            int diff = calcDiff(); 
            if (maxDiff < diff) { // 최대 점수 차이보다 현재 점수차가 더 큰 경우
                maxDiff = diff;
                answer = lionShoot.clone(); // 정답 갱신
            }
            return;
        }
        
        for (int i=scoreNum; i>=0; i--) {
            lionShoot[i]++; // cnt+1번째 화살을 점수 i에 맞춘 상황 가정
            f(cnt + 1, i);  // 재귀호출
            lionShoot[i]--; // 가정 해제
        }
    }
    
    static private int calcDiff() { // 점수차 계산
        int diff = 0;
        for (int i=0; i<11; i++) {
            if (apichShoot[i] == 0 && lionShoot[i] == 0) {
                continue;
            }
            if (apichShoot[i] >= lionShoot[i]) {
                diff -= (10 - i);
            } else {
                diff += (10 - i);
            }
        }
        if (diff <= 0) { // 라이언이 지거나 비긴 경우 0 리턴
            return 0;
        }
        return diff;
    }
}
