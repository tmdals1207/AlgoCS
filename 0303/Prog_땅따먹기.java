class Solution {
    static int answer = 0;
    
    int solution(int[][] land) {
        int N = land.length;
        
        for(int i = 1; i < N; i++) { // 행
            for(int j = 0; j < 4; j++) {  // 열
                int maxPrev = 0;
                
                for(int k = 0; k < 4; k++) { // 이전 행
                    if(j != k) {
                        // 현재 행의 요소와 이전 행의 선택 가능한 요소들의 합 중 가장 큰 것을 계산
                        maxPrev = Math.max(maxPrev, land[i - 1][k]);
                    }
                }
                // 가장 큰 것을 현재 행 요소에 합하여 업데이트
                land[i][j] += maxPrev;
            }
        }
        
        for(int i = 0; i < 4; i++) {
            answer = Math.max(answer, land[N - 1][i]);
        }

        return answer;
    }
}