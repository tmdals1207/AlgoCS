import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, w, l;
	static Queue<Integer> truck;
	static Queue<Integer> bridge;
	static int time, bridgeWeight;
	
	private static void bridge() {
		while(!bridge.isEmpty()) {
			time++;
			bridgeWeight -= bridge.poll();
			
			if(!truck.isEmpty()) {
				if((bridgeWeight + truck.peek())<=l) {
					bridgeWeight += truck.peek();
					bridge.add(truck.poll());
				}
				else {
					bridge.add(0);
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
		for (int j = 0; j < w; j++) {
			bridge.add(0);
		}
		
		bridge();
		
		System.out.println(time);
	}
}