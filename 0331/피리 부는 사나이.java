import java.io.*;
import java.util.*;

public class 피리 부는 사나이 {
    static int N, M;
    static int[][] board;
    static int[][] visited; // 방문 여부 및 그룹 번호 저장 배열
    static int groupCount = 0; // 사이클 개수

    // 방향 이동 배열
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, 1, -1, 0};
    
    // DFS를 이용하여 사이클을 탐색하는 함수
    static boolean dfs(int x, int y, int group) {
        visited[x][y] = group; // 현재 칸을 현재 그룹 번호로 방문 표시

        int dir = board[x][y]; // 현재 칸에서 이동할 방향
        int nx = x + dx[dir]; // 이동할 다음 칸의 행 좌표
        int ny = y + dy[dir]; // 이동할 다음 칸의 열 좌표

        if (visited[nx][ny] == 0) { // 방문하지 않은 경우 계속 탐색
            return dfs(nx, ny, group);
        } else if (visited[nx][ny] == group) { // 같은 그룹 번호를 만난 경우 사이클 형성 
            return true;
        }

        return false; // 다른 그룹을 만나면 사이클이 아님
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);
                
                // 'U', 'R', 'L', 'D'를 숫자로 변환하여 저장
                if (c == 'U') board[i][j] = 0;
                else if (c == 'R') board[i][j] = 1;
                else if (c == 'L') board[i][j] = 2;
                else if (c == 'D') board[i][j] = 3;
            }
        }

        int groupNumber = 1; // 각 새로운 탐색에 사용할 그룹 번호

        // 모든 칸을 확인하며 DFS 탐색 수행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) { // 아직 방문하지 않은 경우 탐색 시작
                    if (dfs(i, j, groupNumber)) { // 사이클이 형성된 경우
                        groupCount++; // 사이클 개수 증가
                    }
                    groupNumber++; // 새로운 그룹 번호 할당
                }
            }
        }

        System.out.println(groupCount);
    }
}
