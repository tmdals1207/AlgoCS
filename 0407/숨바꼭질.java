import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon1697 {
	static Scanner scanner = new Scanner(System.in);
	static boolean[] visited = new boolean[100001];
	static int N, K;
	static class Location {
		int x, time;
		public Location(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
	
	public static void main(String[] args) {
		N = scanner.nextInt();
		K = scanner.nextInt();
		
		System.out.println(bfs(N, 0));
	}
	
	static int bfs(int x, int time) {
		Queue<Location> queue = new LinkedList<>();
		queue.offer(new Location(x, time));
		visited[x] = true;
		
		while(!queue.isEmpty()) {
			Location checkLocation = queue.poll();
			
			if(checkLocation.x == K) {
				return checkLocation.time;
			}
			
			int[] nextX = new int[3];
			nextX[0] = checkLocation.x + 1;
			nextX[1] = checkLocation.x - 1;
			nextX[2] = checkLocation.x * 2;
			
			for(int i = 0; i < 3; i++) {
				if(nextX[i] >= 0 && nextX[i] < 100001 && !visited[nextX[i]]) {
					queue.offer(new Location(nextX[i], checkLocation.time + 1));
					visited[nextX[i]] = true;
				}
			}
		}
		
		return -1;
	}
}
