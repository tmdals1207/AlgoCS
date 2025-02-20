package s0224;

import java.util.Scanner;

public class Main_BJ1003 {								//피보나치 수열
	static StringBuilder sb;
	static int[][] data;								
	
	private static int fibonacci(int n) {				//이렇게..하는게 DP가 맞나?
		if(data[n][1] != 0) {
			return data[n][1];
		}
	    if (n == 0) {
	    	data[n][0] = 1;
	        return 0;
	    } else if (n == 1) {
	    	data[n][1] = 1;
	        return 1;
	    } else {
	    	data[n][1] = fibonacci(n-1) + fibonacci(n-2);		//1이 나온 횟수 = fibonacci(n)과 같음
	    	data[n][0] = data[n-1][0] + data[n-2][0];			//0이 나온 횟수도 0이 나온 횟수끼리 피보나치 수열을 이룸
	        return data[n][1];
	    }
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i = 0; i < t; i++) {
			int n = sc.nextInt();
			
			//fibonacci(n)은 data[n][0]만큼 fibonacci(0)이, data[n][1]만큼 fibonacci(1)이 호출됨을 의미
			data = new int[n+1][2];								
				
			fibonacci(n);
			
			System.out.println(data[n][0] + " " + data[n][1]);
		}
		
		sc.close();
	}

}
