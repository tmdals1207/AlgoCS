package s0303;

public class Solution_Pro12913 {									//땅따먹기

	class Solution {
	    int solution(int[][] land) {
	        int n = land.length;

	        for(int i = 1; i < n; i++){
	            for(int j = 0; j < 4; j++){
	                int max = 0;
	                for(int k = 0; k <4; k++){
	                    if(j != k){
	                        max = Math.max(max, land[i-1][k]);      //이전 행의 값중 가장 큰 값 찾기
	                    }
	                }
	                land[i][j] = max + land[i][j];
	            }
	        }
	        
	        int answer = 0;
	        for(int i = 0; i < 4; i++){
	            answer = Math.max(answer, land[n-1][i]);
	        }
	        return answer;
	    }
	}

}
