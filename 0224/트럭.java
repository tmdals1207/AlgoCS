import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, w, l;
	static Queue<Integer> truck; // 트럭들을 저장해둔 큐.
	static Queue<Integer> bridge; // 다리의 상황을 저장해둔 큐.
	static int time, bridgeWeight;
	
	private static void bridge() {
        // 다리에 아무것도 없을 때까지 반복.
		while(!bridge.isEmpty()) {
			time++; // 1. 시간을 증가시킴.
			bridgeWeight -= bridge.poll(); // 2. 다리 큐의 제일 앞쪽에서 하나를 poll()하여 다리의 총 무게에서 제외시킴.
			
            // 트럭 큐에 트럭이 없다면 로직 패스.
			if(!truck.isEmpty()) {
				if((bridgeWeight + truck.peek())<=l) { // 3. 현재 다리위의 총 무게와 다음에 올 트럭의 무게의 합을 비교.
					bridgeWeight += truck.peek(); // 4. 다리의 총 무게에 다음에 올 트럭의 무게를 더함.
					bridge.add(truck.poll()); // 5. 다리의 큐에 트럭의 큐에서 poll()하여 add()해줌.
				}
				else {
					bridge.add(0); // 총 합이 다리의 한계를 넘어서면 0을을 add().
				}
			} 
			
		}
	}
		
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		truck = new ArrayDeque<>();
		bridge = new ArrayDeque<>(); 
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			truck.add(Integer.parseInt(st.nextToken()));
		}
        // 다리 큐에 0을 가득 채움.
		for (int j = 0; j < w; j++) {
			bridge.add(0);
		}
		
		bridge();
		
		System.out.println(time);
	}
}