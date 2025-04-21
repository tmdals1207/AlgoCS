import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Baekjoon11286 {
	static Scanner scanner = new Scanner(System.in);
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		int N = scanner.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>(
			(a, b) -> {
				int absA = Math.abs(a);
				int absB = Math.abs(b);
				
				if(absA == absB) {
					return Integer.compare(a, b);
				}
				
				return Integer.compare(absA, absB);
			}
		);
		
		for(int i = 0; i < N; i++) {
			int command = scanner.nextInt();
			
			if(command == 0) {
				if(pq.isEmpty()) {
					bw.write("0\n");
				}else {
					bw.write(pq.poll() + "\n");
				}
			}else {
				pq.add(command);
			}
		}
		
		bw.flush();
		bw.close();
	}
}
