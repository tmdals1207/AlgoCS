class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int row = land.length;  // 행 개수
        int dp[][] = new int[row+1][4]; // DP 테이블 (각 행에서 선택할 수 있는 네 개의 칸을 저장)
        
        // DP 테이블 채우기
        for (int i = 1; i <= row; i++) {  // 1번 행부터 마지막 행까지 순회
            for (int j = 0; j < 4; j++) { // 현재 선택할 열 (0~3)
                for (int k = 0; k < 4; k++) { // 이전 행에서 선택할 열 (0~3)
                    if (k == j) continue; // 같은 열은 연속해서 선택 불가

                    // 이전 행에서 k번째 열을 선택한 경우 중 최대값을 찾아 현재 열(j)에 더함
                    dp[i][j] = Math.max(dp[i-1][k] + land[i-1][j], dp[i][j]);
                    
                    // 최댓값 갱신
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        return answer; // 가장 큰 누적 점수 반환
    }
}
