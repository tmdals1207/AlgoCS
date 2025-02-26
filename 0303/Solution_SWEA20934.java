package s0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 3개의 컵 중 하나의 컵에 방울을 넣고 섞음. (섞는다 = 인접한 두 컵을 바꾼다)
 * 동전의 앞면이 나오면 왼쪽과 가운데를, 뒷면이 나오면 가운데와 오른쪽을 섞음. 동전의 각 면이 나올 확률은 1/2.
 * 방울이 있는 컵을 교환하면 소리가 울림, k번 소리가 울렸음
 * 방울이 있을 확률이 가장 높은 컵은?
 * 왼쪽 0 가운데 1 오른쪽 2로 출력, 확률이 동일하면 가장 왼쪽 것 출력
 */
public class Solution_SWEA20934 {							//방울마술

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < k; i++) {
				if(s.equals("o..")) {
					s = ".o.";
				}else if(s.equals(".o.")) {
					s = "o..";
				}else if(s.equals("..o")) {
					s = ".o.";
				}
			}
			int idx = 0;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == 'o') {
					idx = i;
				}
			}
			System.out.println("#"+tc +" "+ idx);
		}
		br.close();
	}
}
