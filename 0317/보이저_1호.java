import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준G5 3987 보이저 1호
// 시뮬레이션
public class Main {

    static final int INFINITE = 987654321; // 무한 전파인 경우 time이 가질 값

    static int N;
    static int M;
    static char[][] map;
    static int nowN, nowM, nowD; // 현재 행, 열, 방향
    static int[] dns = {-1, 0, 1, 0};
    static int[] dms = {0, 1, 0, -1};
    static char[] directions = {'U', 'R', 'D', 'L'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        map = new char[N][M];

        for (int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        split = br.readLine().split(" ");
        int startN = Integer.parseInt(split[0]) - 1; // 시작 좌표 입력
        int startM = Integer.parseInt(split[1]) - 1;

        int maxTime = 0;
        int maxD = -1;
        for (int startD=0; startD<4; startD++) { // 각 방향마다 수행
            nowN = startN; // 현재 행
            nowM = startM; // 현재 열
            nowD = startD; // 현재 방향
            int time = 1;
            while (true) {
                int nn = nowN + dns[nowD]; 
                int nm = nowM + dms[nowD];

                if (nn == startN && nm == startM && nowD == startD) {
                    time = INFINITE;
                    break;
                }

                if (!inRange(nn, nm)) { // 항성계를 벗어난 경우 끝
                    break;
                }

                if (map[nn][nm] == 'C') { // 블랙홀인 경우 끝
                    break;
                }

                nowN = nn; // 시그널 이동
                nowM = nm;
                time++;

                if (map[nn][nm] == '/') { // 행성을 만난 경우 방향 전환
                    if (nowD == 0) nowD = 1;
                    else if (nowD == 1) nowD = 0;
                    else if (nowD == 2) nowD = 3;
                    else if (nowD == 3) nowD = 2;
                } else if (map[nn][nm] == '\\') {
                    if (nowD == 0) nowD = 3;
                    else if (nowD == 3) nowD = 0;
                    else if (nowD == 1) nowD = 2;
                    else if (nowD == 2) nowD = 1;
                }
            }

            if (maxTime < time) {
                maxTime = time;
                maxD = startD;
            }

            if (maxTime == INFINITE) {
                break;
            }
        }

        System.out.println(directions[maxD]);
        if (maxTime == INFINITE) {
            System.out.println("Voyager");
        } else {
            System.out.println(maxTime);
        }
    }

    private static boolean inRange(int n, int m) {
        return 0<=n && n<N && 0<=m && m<M;
    }
}
