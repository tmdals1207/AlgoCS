import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 미로 탐색을 할 때 시작 노드부터 최종목적지 노드까지의 최소 이동 거리를 구하기 위해 Point 클래스를 이용하여 bfs 탐색을 수행한다.
 * Point 클래스는 row값과 col값과 그 노드까지의 이동거리인 distance 값을 갖는다.
 */
public class Baekjoon2178 {
	static Scanner scanner = new Scanner(System.in);
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static class Point {
		int row, col, distance;
		public Point(int row, int col ,int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) {
		n = scanner.nextInt();
		m = scanner.nextInt();
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			String line = scanner.next();
			
			for(int j = 0; j < m; j++) {
				map[i][j] = Character.getNumericValue(line.charAt(j));
			}
		}
		
		bfs(map, 0, 0, n - 1, m - 1);
	}
	
	static void bfs(int[][] map, int startRow, int startCol, int endRow, int endCol) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(startRow, startCol , 1));
		visited[startRow][startCol] = true;
		
		//상,하,좌,우
		int[] mr = {0, 0, -1, 1};
		int[] mc = {1, -1, 0, 0};
		
		while(!queue.isEmpty()) {
			Point checkNode = queue.poll();
			int beforeDistance = checkNode.distance;
			//checkNode를 기준으로 상하좌우에 있는 Node에 갈 수 있는지 확인.
			for(int i = 0; i < 4; i++) {
				int nextRow = checkNode.row + mr[i];
				int nextCol = checkNode.col + mc[i];
				// checkNode의 다음 노드가 범위안에 있고, 아직 방문하지 않은 노드이면서 그 노드의 값이 1이면 방문.
				if(nextRow >= 0 && nextRow <= endRow && nextCol >= 0 && nextCol <= endCol && map[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
					queue.offer(new Point(nextRow, nextCol, beforeDistance + 1));
					visited[nextRow][nextCol] = true;
					// 방문한 다음 노드가 최종 목적지 노드이면 checkNode의 distance + 1 값을 출력 후 탐색 종료.
					if(nextRow == endRow && nextCol == endCol) {
						System.out.println(checkNode.distance + 1);
						break;
					}
				}
			}
		}
	}
}
