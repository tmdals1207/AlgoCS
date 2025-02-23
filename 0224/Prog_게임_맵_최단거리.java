import java.util.Queue;
import java.util.LinkedList;

class Solution {
    static class Node {
        int x, y, dist; 
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 1));
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            Node checkNode = queue.poll();
            
            if(checkNode.x == n - 1 && checkNode.y == m - 1) {
                return checkNode.dist;
            }
            
            int[] mr = {-1, 1, 0, 0};
            int[] mc = {0, 0, -1, 1};
            
            for(int i = 0; i < 4; i++) {
                int nextX = checkNode.x + mr[i];
                int nextY = checkNode.y + mc[i];
                int nextDist = checkNode.dist + 1;
                
                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && !visited[nextX][nextY] && maps[nextX][nextY] == 1) {
                    queue.offer(new Node(nextX, nextY, nextDist));
                    visited[nextX][nextY] = true;
                }
            }
        }
        
        return -1;
    }
}