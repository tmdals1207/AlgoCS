import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		for (int i=2; i<N+1; i++) {
			if (arr[i] == 1) { // 소수가 아닌 경우
				continue;
			}
      if (i >= M) {
          System.out.println(i);
      }
			int r = 1;
			while (i * r < N + 1) {
				arr[i * r] = 1;
				r++;
			}
		}
	}
}
