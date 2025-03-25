package s0331;

import java.util.Scanner;

/*
 * 이게 뭔 문제야
 * 걍 수식 알아서 잘 전개해보세요 ㅎ..
 */
public class Main_BJ21868 {									//미적분학 입문하기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int e1 = sc.nextInt();
		int e2 = sc.nextInt(); // e1-분자 e2-분모
		
		int a = sc.nextInt();
		int b = sc.nextInt();	// ax+b의 형태
		
		int x0 = sc.nextInt(); 		//극한값 L=f(x0)
		
		int l = a * x0 + b;
		
		
		System.out.println(l);
		
		if(a==0) {
			System.out.println(0 +" "+ 0);
		}else {
			System.out.println(e1 +" "+ e2 * Math.abs(a));
		}
	}

}
