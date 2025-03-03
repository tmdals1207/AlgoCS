import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static char[] cup;
	static int current;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());

		for (int i = 1; i < tc+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			cup = st.nextToken().toCharArray();
			int k = Integer.parseInt(st.nextToken());
			
            // 현재 방울이 어디있는지 확인.
			for (int c = 0; c < cup.length; c++) {
	            if (cup[c] == 'o') {
	            	current = c;
	                break;
	            }
	        }
            // 방울의 위치가 왼쪽일 때는 가운데로 오고
            // 가운데나 오른쪽이면 왼쪽으로 이동.
			for (int j = 0; j < k; j++) {
				if (current == 1 || current == 2) current--;
				else current++;
				
			} // for
			System.out.println("#" + i + " " + current);
		} // for
	} // main
}