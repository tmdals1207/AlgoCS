import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 숨바꼭질 {

    static int N, K; // 수빈이의 위치와 동생의 위치
    static boolean[] visited; // 같은 점에 갈 경우를 대비한 visited 배열


    static int bfs() {
        Queue<Integer> queue = new ArrayDeque<>(); // 큐 생성
        queue.add(N); // 처음에 주어진 수빈이의 위치 지점을 삽입
        visited[N] = true;
        int cnt = 0; // 수빈이가 이동에 걸린 시간을 담을 변수

        while (!queue.isEmpty()) {
            // 1초가 지날 때마다 갈 수 있는 곳만큼 연산을 해야하기 때문.
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                int cur = queue.poll();

                if (cur == K) return cnt; // 현재 수빈이의 위치가 동생의 위치와 같으면 종료

                // 수빈이가 현재의 위치에서 이동할 수 있는 위치들의 리스트
                int[] list = {cur + 1, cur - 1, cur * 2};

                for (int li : list) { // 갈 수 있는 곳들 중에서
                    // 범위 안에 있고 방문하지 않았던 곳이라면
                    if (li >= 0 && li <= 100000 && !visited[li]) {
                        // 큐에 입력하고 visited 처리
                        queue.add(li);
                        visited[li] = true;
                    }
                }
            }
            // 1초가 지난 후에 갈 수 있던 모든 점들을 탐색하면 1초를 증가시킴.
            cnt++;
        }
        return cnt;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001]; // 0과 100000을 포함하는 범위

        System.out.println(bfs());
    }
}