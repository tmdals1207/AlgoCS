import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	static int max; // 최대 합을 담아둘 변수.
	
	static void dp() {
		int[] dp = new int[N]; // N의 크기만큼 dp배열 생성.
		
		dp[0] = arr[0]; // dp배열의 인덱스 0을 입력받은 배열의 0으로 초기화.
		
		max = dp[0]; // 최댓값에 dp배열의 인덱스 0을 대입.
		
        // 이전 인덱스까지의 최댓값과 다음에 올 값의 비교 후에 dp배열에 더 큰 값 저장.
		for(int i=1; i<N; i++) {
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
		}
		
        // dp배열에서 가장 큰 연속합을 찾아서 max 변수에 대입.
		for (int d : dp) {
			max = Math.max(max, d);
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp();
		
		System.out.println(max);
	}
}