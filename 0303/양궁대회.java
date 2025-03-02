// [알고리즘의 전체적인 흐름]
// 백트래킹을 통해 0~10 중 중복을 허용하여 n개를 뽑는 모든 경우의 수를 탐색한다. => 중복조합
// 라이언이 이기는 경우 중 작은 점수를 더 많이 맞히는 경우를 정답으로 뽑아야 하기 때문에
// n개의 화살이 모두 0점에 맞는 경우부터 모두 10점에 맞는 경우 순으로 탐색한다. <- 이건 스타일 차이라고 생각합니다
// 탐색을 하며 기존 점수차보다 점수차가 더 큰 경우 정답을 업데이트한다.

// n == 10인 경우 백트래킹을 하며 만들어지는 lionShoot 배열의 값(중복조합의 모든 경우의 수)
// 48번째줄 if문 내부에서 lionShoot 출력 시
// 0 0 0 0 0 0 0 0 0 0 10 (각각 10 ~ 0점에 맞힌 화살 개수)
// 0 0 0 0 0 0 0 0 0 1 9
// 0 0 0 0 0 0 0 0 1 0 9
// 0 0 0 0 0 0 0 1 0 0 9
// ...
// 9 0 1 0 0 0 0 0 0 0 0
// 0 10 0 0 0 0 0 0 0 0 0
// 1 9 0 0 0 0 0 0 0 0 0
// 2 8 0 0 0 0 0 0 0 0 0
// ...
// 9 1 0 0 0 0 0 0 0 0 0
// 10 0 0 0 0 0 0 0 0 0 0

// 중복조합 유사 문제 - 백준 15652번 [N과 M (4)]
// 문제 https://www.acmicpc.net/problem/15652
// 답안 https://www.acmicpc.net/source/83670250

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
