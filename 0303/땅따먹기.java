class Solution {
    
    static int[][] dp; // dp[i][j] : i행 j열 칸을 밟았을 때 최대 점수
    static int answer;
    
    int solution(int[][] land) {
        
        dp = new int[land.length][4];
        for (int i=0; i<4; i++) { // 초기값 설정
            dp[0][i] = land[0][i];
        }

        for (int row=1; row<dp.length; row++) { // 행
            for (int i=0; i<4; i++) {           // 열
                for (int j=0; j<4; j++) {       // 이전 행의 열
                    if (i == j) {               // 열이 같은 경우 continue
                        continue;
                    }
                    // 'row행 i열까지의 최대 점수'와 'row-1행 j열의 점수에 현재 땅의 점수를 더한 값' 중 최대 값으로 업데이트
                    dp[row][i] = Math.max(dp[row][i], dp[row - 1][j] + land[row][i]);
                }
            }
        }
        
        
        for (int i=0; i<4; i++) {
            answer = Math.max(dp[dp.length - 1][i], answer);
        }
        
        return answer;
    }
}
