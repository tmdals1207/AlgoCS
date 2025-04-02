import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon17129 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;
	static boolean[][] visited;
	static int n, m;
	static class Point {
		int x, y, distance;
		public Point(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws IOException {
		String[] mapSize = br.readLine().split(" ");
		n = Integer.parseInt(mapSize[0]);
		m = Integer.parseInt(mapSize[1]);
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			String oneLine = br.readLine();
			
			for(int j = 0; j < m; j++) {
				map[i][j] = Character.getNumericValue(oneLine.charAt(j));
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 2) {
					int result = bfs(i, j);
					
					if(result == -1) {
						System.out.println("NIE");
					}else {
						System.out.println("TAK");
						System.out.println(result);
					}
				}
			}
		}
	}
	
	static int bfs(int startX, int startY) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(startX, startY, 0));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Point checkNode = queue.poll();
			
			if(map[checkNode.x][checkNode.y] == 3 || map[checkNode.x][checkNode.y] == 4 || map[checkNode.x][checkNode.y] == 5) {
				return checkNode.distance;
			}
			
			int[] mr = {-1, 1, 0, 0};
			int[] mc = {0, 0, -1, 1};
			
			for(int i = 0; i < 4; i++) {
				int nextX = checkNode.x + mr[i];
				int nextY = checkNode.y + mc[i];
				int nextDistance = checkNode.distance + 1;
				
				if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && !visited[nextX][nextY] && map[nextX][nextY] != 1) {
					queue.offer(new Point(nextX, nextY, nextDistance));
					visited[nextX][nextY] = true;
				}
			}
		}
		
		return -1;
	}
}
