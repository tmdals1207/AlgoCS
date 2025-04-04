import java.util.*;
import java.io.*;

public class Main {

    static int N; // 수빈이 위치
    static int K; // 동생 위치
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);

        System.out.println(bfs());
  }

    static int bfs() {
        int[] visited = new int[2 * Math.max(N, K) + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(N);

        while (!q.isEmpty()) {
            int poll = q.poll();
            if (poll == K) {
                return visited[K];
            }

            if (poll+1 < 2*K+1 && visited[poll + 1] == 0) {
                visited[poll + 1] = visited[poll] + 1;
                q.add(poll + 1);
            }
            
            if (poll-1 >= 0 && visited[poll - 1] == 0) {
                visited[poll - 1] = visited[poll] + 1;
                q.add(poll - 1);
            }
            
            if (poll*2 < 2*K+1 && visited[poll * 2] == 0) {
                visited[poll * 2] = visited[poll] + 1;            
                q.add(poll * 2);
            }
        }

        return -1;
    }
}
