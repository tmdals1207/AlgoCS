import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Baekjoon11659 {
	static Scanner scanner = new Scanner(System.in);
	static int n, m, i, j;
	static ArrayList<Integer> array = new ArrayList<>();
	static ArrayList<Integer> prefixSum = new ArrayList<>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		n = scanner.nextInt();
		m = scanner.nextInt();
		
		for(int i = 0; i < n; i++) {
			int input = scanner.nextInt();
			array.add(input);
			if(i == 0) {
				prefixSum.add(input);
			}else {
				prefixSum.add(prefixSum.get(i - 1) + input);
			}
		}
		
		for(int l = 0; l < m; l++) {
			int sum = 0;
			
			i = scanner.nextInt();
			j = scanner.nextInt();
			
			if(i == j) {
				sum = array.get(i - 1);
				bw.write(sum + "\n");
			}else if(i == 1 && i != j){
				sum = prefixSum.get(j - 1);
				bw.write(sum + "\n");
			}else {
				sum = prefixSum.get(j - 1) - prefixSum.get(i - 2);
				bw.write(sum + "\n");
			}
			
		}
		
		bw.flush();
		bw.close();
	}
}
