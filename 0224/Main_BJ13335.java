package s0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 다리에 n개의 트럭이 지나가고자 함. 트럭의 무게는 같을 수도 다를수도
 * 다리에는 w대의 트럭만 올라갈 수 있음, 다리를 전부 다 지나가려면 w만큼의 시간 소요
 * 다리는 L만큼의 무게만 수용가능
 * 다리의 길이 w와 최대하중 L, 트럭의 무게가 순서로 주어질때 모든 트럭이 다리를 건너는 최단 시간 구하기
 */
public class Main_BJ13335 {								//트럭

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());			//다리 길이
		int L = Integer.parseInt(st.nextToken());			//최대 하중
		
		Queue<Integer> truck = new ArrayDeque<>(); 			//트럭 대기열 큐
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			truck.offer(Integer.parseInt(st.nextToken()));	//트럭 대기열 입력
		}
		
		Queue<Integer> q = new ArrayDeque<>(); 				//다리 큐
		for(int i = 0; i < w; i++) {		
			q.add(0);										//초기 다리는 다리 길이만큼 빈 값으로 가득 채우기 (안 채우고 해보려니 안 되는거 같음)
		}
		int time = 0;										//최소 이동 시간
		int heavy = 0;										//현재 다리의 무게
		while(!q.isEmpty()) {
			time++;
			heavy = heavy - q.poll();						//다리에서 나온 애 무게 빼주기
			if(!truck.isEmpty()) {
				if(heavy + truck.peek() <= L) {				//다리가 새로 넣을 트럭의 무게를 견딜 수 있다면	
					heavy = heavy + truck.peek();			//현재 다리 무게 갱신
					q.offer(truck.poll());					//다리 큐에 트럭 넣기
				}
				else {										//그렇지 않으면 빈자리 삽입
					q.offer(0);
				}
			}
		}
		
		System.out.println(time);
	}

}
