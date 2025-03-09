import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 점프 {
	
	static int N, cnt;
	static int[][] board;
	static long[][] dp;
	

	static void jump(int x, int y) {
		
		dp[0][0] = 1; // 시작점에서 출발하는 경로는 1개
		
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int value = board[i][j]; // 현재 칸에서 이동할 수 있는 값 (점프 거리)
                
                // 점프할 수 없는 칸(0)이라면 다음 칸으로 이동
				if(board[i][j]==0) break;
                
				 // 아래쪽(i+value)으로 이동 가능하면 경로 추가
                if (i + value < N) {
                    dp[i + value][j] += dp[i][j];
                }

                // 오른쪽(j+value)으로 이동 가능하면 경로 추가
                if (j + value < N) {
                    dp[i][j + value] += dp[i][j];
                }
			}
		}
		System.out.println(dp[N-1][N-1]);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		dp = new long[N][N]; // 주어진 입력값의 범위에 따라 Long으로 변경.
		
		for(int i = 0; i < N; i++) {
			st =  new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		jump(0, 0);
	}
}