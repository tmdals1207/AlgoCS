import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Baekjoon2665 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static class Node {
		int x, y, changeCount;
		public Node(int x, int y, int changeCount) {
			this.x = x;
			this.y = y;
			this.changeCount = changeCount;
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String oneLine = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Character.getNumericValue(oneLine.charAt(j)); 
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Deque<Node> deque = new LinkedList<>();
		deque.offerFirst(new Node(0, 0, 0));
		visited[0][0] = true;
		
		while(!deque.isEmpty()) {
			Node checkNode = deque.pollFirst();
			
			if(checkNode.x == N - 1 && checkNode.y == N - 1) {
				return checkNode.changeCount;
			}
			
			int[] mr = {-1, 1, 0, 0};
			int[] mc = {0, 0, -1, 1};
			
			for(int i = 0; i < 4; i++) {
				int nextX = checkNode.x + mr[i];
				int nextY = checkNode.y + mc[i];
				
				if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY]) {
					if(map[nextX][nextY] == 1) {
						deque.offerFirst(new Node(nextX, nextY, checkNode.changeCount));
						visited[nextX][nextY] = true;
					}else if(map[nextX][nextY] == 0) {
						deque.offerLast(new Node(nextX, nextY, checkNode.changeCount + 1));
						visited[nextX][nextY] = true;
					}
				}
			}
		}
		
		return -1;
	}
}
