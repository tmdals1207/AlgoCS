import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    // 방향을 전환하는 배열은 변하지 않으니 final 선언.
    final private static int[] dx = {0, 0, 1, -1};
	final private static int[] dy = {1, -1, 0, 0};
    static int n, m;
    
    public int solution(int[][] maps) {
        
        // maps의 크기인 n,m을 static 선언 후 값 입력.
        n = maps.length;
        m = maps[0].length;
        
        return bfs(maps);
    }
    
    private static int bfs(int[][] maps) {
        Queue<Point> queue = new LinkedList<>();
        // 1. 큐에 시작 좌표인 (0,0)의 Point를 add.
        queue.add(new Point(0,0));
        
        // 2. 큐가 빌 때까지 계속 실행.
        while(!queue.isEmpty()) {
            // 3. 큐에서 제일 처음 들어갔던 것을 poll.
            Point current = queue.poll();
            // 4. 상하좌우 4방향에 대한 이동 실행.
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                // 5. 인덱스의 범위 안에서 벽이 아닌 1인 길을 찾기.
                if(nx>=0 && nx<n && ny>=0 && ny<m && maps[nx][ny]==1) {
                    // 6. 찾았다면 해당 좌표에 해당 좌표까지 가는 값을 저장.
                    maps[nx][ny] = maps[current.x][current.y] + 1;
                    // 7. 큐에 다음에 이동할 좌표를 add.
                    queue.add(new Point(nx, ny));
                }
            }   
        }
        // 삼항 연산자를 통해 만약 maps[n-1][m-1]의 값이 기존에 초기화 되었던 값인 1이면 -1 리턴.
        return maps[n-1][m-1] == 1 ? -1 : maps[n-1][m-1];
    }
    
    // Point 클래스를 만들어 한칸을 표현.
    public static class Point{
		int x, y;
		
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
    }
}