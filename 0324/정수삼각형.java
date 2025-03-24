import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형 {
	
	static int[][] triangle, dp;
	static int N;

	
	// DP를 이용한 최댓값 경로 계산
	static void dp() {
		dp = new int[N][N]; // DP 테이블 초기화
		
		dp[0][0] = triangle[0][0]; // 삼각형의 첫 번째 값 초기화

		// 삼각형을 아래로 진행하면서 DP 테이블을 채움
		for (int i = 1; i < N; i++) {
			// 왼쪽 끝 요소는 바로 위의 요소에서만 내려올 수 있음
			dp[i][0] = dp[i-1][0] + triangle[i][0];
			// 오른쪽 끝 요소는 바로 위의 대각선 왼쪽 요소에서만 내려올 수 있음
			dp[i][i] = dp[i-1][i-1] + triangle[i][i];
			
			// 가운데 요소들은 왼쪽 위 또는 바로 위에서 내려올 수 있음
			for (int j = 1; j < i; j++) {
				dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		N = Integer.parseInt(br.readLine());
		triangle = new int[N][N];
		
		for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
		
		dp();
		
		int max = 0;
		
        // 가장 마지막 줄에서 각각의 경로의 값 중 최댓값을 구함
		for (int m : dp[N-1]) {
			max = Math.max(max, m);
		}
		
		System.out.println(max);
		
	}

}
