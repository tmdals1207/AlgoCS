import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int M,N;

	// 소수 판별 함수
	static boolean sosu(int num) {
		if (num == 1) return false; // 1은 소수가 아님
		
		// 2부터 √num까지 나누어 떨어지는지 확인
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) return false; // 나누어 떨어지면 소수가 아님
		}
		
		return true; // 위 조건을 통과하면 소수
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		for (int i = M; i <= N; i++) {
			if(sosu(i)) {
				bw.write(i + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
}