package s0303;
/*
 * n행 4열로 이루어진 땅에서 행마다 땅 하나를 밟으며 내려올때, 점수의 최댓값 구하기
 * 단, 직전 행에서 밟은 열은 밟을 수 없음!!!
 * dp 배열을 따로 쓰지 않고 입력값 land 배열을 진행할수록 최댓값이 갱신되도록 적용 (n행에 도착하면 n행에 도착할때까지의 점수의 합이 들어감)  
 */
public class Solution_Pro12913 {									//땅따먹기

	class Solution {
	    int solution(int[][] land) {
	        int n = land.length;

	        for(int i = 1; i < n; i++){								//n개의 행만큼 반복
	            for(int j = 0; j < 4; j++){							
	                int max = 0;
	                for(int k = 0; k <4; k++){
	                    if(j != k){									//직전 열과 다른 인덱스인 애일 경우애만
	                        max = Math.max(max, land[i-1][k]);      //이전 행의 값중 가장 큰 값 찾기
	                    }
	                }
	                land[i][j] = max + land[i][j];					//i행j열에 도착했을때의 점수 값 갱신
	            }
	        }
	        
	        int answer = 0;
	        for(int i = 0; i < 4; i++){
	            answer = Math.max(answer, land[n-1][i]);			//마지막 n번째 행의 열 값들 중 가장 큰 애 찾아서 정답으로
	        }
	        return answer;
	    }
	}

}
