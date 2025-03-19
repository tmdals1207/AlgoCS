package s0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * 나무들이 주어졌을때, 각 종이 전체의 몇 %를 차지하는지 구하는 프로그램 만들기
 * 하나의 나무 종 이름이 한 줄씩 입력, 30자 미만
 * 각 나무의 종 이름을 사전순으로 출력 후 차지하는 백분율 소수 4째자리까지 반올림해서 같이 출력
 */
public class Main_BJ4358 {

	public static void main(String[] args) throws IOException {	//생태학
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tree;
		Map<String, Integer> map = new HashMap<>();
		int n = 0;									//입력값 개수
		while((tree = br.readLine()) != null) {		//입력값에 제한이 따로 없음-> 다음줄이 없을때까지 쭉 받아보기
			n++;
			map.put(tree, map.getOrDefault(tree, 0)+1);
		}
		
		//해시맵 정렬(사전순)
		//key가 들어있는 set을 가져와 리스트로 만들기
		ArrayList<String> keySet = new ArrayList<>(map.keySet());	
		Collections.sort(keySet);		//키 값을 기준으로 오름차순 정렬
		
		for(String str : keySet) {
			System.out.println(str + " " + String.format("%.4f", ((double)map.get(str) / n) * 100));
		}
		
	}

}
