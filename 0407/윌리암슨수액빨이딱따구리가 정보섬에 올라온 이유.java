import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유 {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    static void bfs(Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (map[now.x][now.y] >= '3' && map[now.x][now.y] <= '5') {
                System.out.println("TAK");
                System.out.println(now.dist);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visited[nx][ny] && map[nx][ny] != '1') {
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny, now.dist + 1));
                    }
                }
            }
        }
        System.out.println("NIE");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '2') {
                    queue.add(new Node(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }
        bfs(queue);
    }

    static class Node {
        int x, y, dist;
        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}