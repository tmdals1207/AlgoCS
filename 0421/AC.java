import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Baekjoon5430 {
	static Scanner scanner = new Scanner(System.in);
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		int T = scanner.nextInt();
		
		for(int i = 0; i < T; i++) {
			String commands = scanner.next();
			int size = scanner.nextInt();
			String arr = scanner.next();
			
			Deque<Integer> deque = new ArrayDeque<>();
			
			if(size != 0) {
				String subArr = arr.substring(1, arr.length() - 1);
				String[] splitArr = subArr.split(",");
				
				for(String num: splitArr) {
					deque.addLast(Integer.parseInt(num));
				}
			}
			
			boolean isReverse = false;
			boolean isError = false;
			
			for(int j = 0; j < commands.length(); j++) {
				char command = commands.charAt(j);
				
				if(command == 'R') {
					isReverse = !isReverse;
				}else if(command == 'D') {
					if(deque.isEmpty()) {
						isError = true;
						break;
					}else {
						if(isReverse) {
							deque.pollLast();
						}else {
							deque.pollFirst();
						}
					}
				}
			}
			
			if(isError) {
				bw.write("error\n");
			}else {
				if(isReverse) {
					bw.write("[");
					
					while(!deque.isEmpty()) {
						bw.write(deque.pollLast() + "");
						
						if(!deque.isEmpty()) {
							bw.write(",");
						}
					}
					
					bw.write("]\n");
				}else {
					bw.write("[");
					
					while(!deque.isEmpty()) {
						bw.write(deque.pollFirst() + "");
						
						if(!deque.isEmpty()) {
							bw.write(",");
						}
					}
					
					bw.write("]\n");
				}
			}
		}
		
		bw.flush();
		bw.close();
	}
}
