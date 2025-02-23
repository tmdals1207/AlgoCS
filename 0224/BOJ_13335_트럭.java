import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon13335 {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		int n = scanner.nextInt();
		int w = scanner.nextInt();
		int l = scanner.nextInt();
		
		Queue<Integer> waitQueue = new LinkedList<>();
		ArrayList<Integer> bridge = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			waitQueue.add(scanner.nextInt());
		}
		
		for(int i = 0; i < w; i++) {
			bridge.add(0);
		}
		
		int time = 0;
		int passingTruck = 0;
		
		while(passingTruck != n) {
			if(bridge.get(0) != 0) {
				passingTruck++;
			}
			
			bridge.remove(0);
			int weightSum = 0;
			
			for(int weight: bridge) {
				weightSum += weight;
			}
			
			if(waitQueue.size() != 0) {
				if(weightSum + waitQueue.peek() <= l) {
					bridge.add(waitQueue.poll());
				}else {
					bridge.add(0);
				}
			}
			
			time++;
		}
		
		System.out.println(time);
	}
}
