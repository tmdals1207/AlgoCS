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
			
			for (int c = 0; c < cup.length; c++) {
	            if (cup[c] == 'o') {
	            	current = c;
	                break;
	            }
	        }
			for (int j = 0; j < k; j++) {
				if (current == 1 || current == 2) current--;
				else current++;
				
			} // for
			System.out.println("#" + i + " " + current);
		} // for
	} // main
}