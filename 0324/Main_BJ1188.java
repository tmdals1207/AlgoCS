package s0324;

import java.util.Scanner;

/*
 * n개의 소세지, m명의 음식 평론가
 * 모든 평론가들이 같은 양의 소세지를 받게할 때, 소세지를 자르는 횟수를 최소로 하여 출력
 * 
 */
public class Main_BJ1188 {						//음식 평론가
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int cut = 0;
		while(m > 1) {		//m=1 이면 이미 다 나눠받은걸로 판정
			n = n % m; 		//안 잘라도 되는 부분들은 일단 배제해서 n값 다시 설정
			if(n == 0) {	//나눌 소세지가 더 없으면 탈출
				System.out.println(cut);
				return;
			}
			else {
				m = m - n;	//소세지 수 만큼만 1인분으로 잘라서 평론가에게 제공
				cut += n;	//자른 횟수 반영
			}
		}
		System.out.println(cut);
		sc.close();
	}

}
