package s0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  문자열 중 최대 n개의 종류의 알파벳을 가진 연속된 문자열만 인식하는 번역기
 * 	이때 번역기가 인식가능한 최대 문자열의 길이
 */
public class Main_BJ16472 {									//고냥이

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int[] c = new int[26];				//인식할 문자 종류 0:a ~ 25:z
		int count = 0, max = 0;
		
		int start = 0, end = 0;					//인식할 문자의 처음과 끝 지정
		while(end < str.length()) {
			if(c[str.charAt(end) - 'a']++ == 0) {	//0이면 c 배열 값 증가하고
                count++;							//문자열 종류도 증가
            }
			
			while(count > n && end > start) {				//읽어들인 문자열의 문자 종류가 n값보다 크면
				if(--c[str.charAt(start++) - 'a'] == 0) {	//c 배열값 줄이면서 start 지점 이동
					count--;		//배열값 줄인게 0이면(해당 문자 종류가 start와 end 사이에서 없어지면) count값도 줄이기
				}
			}
			
			max = Math.max(max, end - start + 1);	//인식 가능한 문자열 길이와 최댓값 비교
			
			end++;
		}
		
		System.out.println(max);
	}

}
