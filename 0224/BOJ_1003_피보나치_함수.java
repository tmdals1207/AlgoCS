import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Baekjoon1003 {
	static Scanner scanner = new Scanner(System.in);
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int t, n;
	static int array0[], array1[];
	
	public static void main(String[] args) throws IOException {
		t = scanner.nextInt();
		
		
		for(int i = 0; i < t; i++) {
			n = scanner.nextInt();
			array0 = new int[n + 1];
			array1 = new int[n + 1];
			
			bw.write(fib0(n) + " " + fib1(n) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int fib0(int n) {
		if(n == 0 || n == 2) {
			return 1;
		}else if(n == 1) {
			return 0;
		}else if(array0[n] != 0) {
			return array0[n];
		}else {
			return array0[n] = fib0(n - 1) + fib0(n - 2);
		}
	}
	
	static int fib1(int n) {
		if(n == 0) {
			return 0;
		}else if(n == 1) {
			return 1;
		}else if(array1[n] != 0) {
			return array1[n];
		}else {
			return array1[n] = fib1(n - 1) + fib1(n - 2);
		}
	}
}
