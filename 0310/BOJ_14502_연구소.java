import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon14502 {
	static Scanner scanner = new Scanner(System.in);
	static int[][] map;
	static int[][] mapFix;
	static boolean[][] visited;
	static List<Node> emptySpace = new ArrayList<>();
	static List<List<Node>> addWalls = new ArrayList<>();
	static int N, M;
	static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = scanner.nextInt();
			}
		}

		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					emptySpace.add(new Node(i, j));
				}
			}
		}
		
		pick3Walls(0, new ArrayList<>());
		int maxSafeArea = 0;
		
		for(List<Node> c: addWalls) {
			mapFix = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					mapFix[i][j] = map[i][j];
				}
			}
			
			for(Node n: c) {
				mapFix[n.x][n.y] = 1;
			}
			
			visited = new boolean[N][M];
			Queue<Node> queue = new LinkedList<>();
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(mapFix[i][j] == 2) {
						queue.offer(new Node(i, j));
						visited[i][j] = true;
					}
				}
			}
			
			bfsVirus(queue);
			int safeArea = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(mapFix[i][j] == 0) {
						safeArea++;
					}
				}
			}
			
			maxSafeArea = Math.max(maxSafeArea, safeArea);
		}
		
		System.out.println(maxSafeArea);
	}
	
	static void pick3Walls(int s, List<Node> list) {
		if(list.size() == 3) {
			addWalls.add(new ArrayList<>(list));
			return;
		}
		
		for(int i = s; i < emptySpace.size(); i++) {
			list.add(emptySpace.get(i));
			pick3Walls(i + 1, list);
			list.remove(list.size() - 1);
		}
		
	}
	
	static void bfsVirus(Queue<Node> queue) {
		while(!queue.isEmpty()) {
			Node checkNode = queue.poll();
			int[] mr = {0, 0, -1, 1};
			int[] mc = {-1, 1, 0, 0};
			
			for(int i = 0; i < 4; i++) {
				int nextX = checkNode.x + mr[i];
				int nextY = checkNode.y + mc[i];
				
				if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY] && mapFix[nextX][nextY] == 0) {
					queue.offer(new Node(nextX, nextY));
					visited[nextX][nextY] = true;
					mapFix[nextX][nextY] = 2;
				}
			}
		}
	}
}
