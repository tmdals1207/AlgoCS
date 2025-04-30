import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N; // 세로선의 개수
    static int M; // 가로선의 개수
    static int H; // 세로선마다 가로선을 놓을 수 있는 위치의 개수
    static boolean[][] ladder;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        H = Integer.parseInt(split[2]);
        ladder = new boolean[N][H];

        for (int m=0; m<M; m++) {
            split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]) - 1;
            int b = Integer.parseInt(split[1]) - 1;
            ladder[b][a] = true;
        }
        // 입력 끝

        for (int cnt=0; cnt<=3; cnt++) { // cnt: 추가로 설치할 사다리 개수
            boolean flag = setLadders(0, 0, cnt); // 개수만큼 사다리를 설치했을 때 도달 가능한지 여부
            if (flag) { // 도달 가능하다면 정답이므로 break;
                answer = cnt;
                break;
            }
        }

        System.out.println(answer);
    }

    // 사다리를 cnt개만큼 설치했을 때 도달 가능하다면 true, 아니면 false 리턴
    private static boolean setLadders(int startNum, int nowCnt, int cnt) {
        if (nowCnt == cnt) { // 사다리를 cnt개수 만큼 설치한경우 도달 가능한지 탐색
            return canGo();
        }

        for (int i=startNum; i<(N-1)*H; i++) { // startNum번째에 사다리를 설치할 수 있는지 확인 후 설치할 수 있다면 설치
            int startLadder = i / H;
            int h = i % H;
            if (ladder[startLadder][h]) { // startNum번째에 이미 사다리가 있다면 넘김
                continue;
            }

            ladder[startLadder][h] = true; // startNum번째에 사다리가 없다면 설치

            boolean flag = setLadders(i + 1, nowCnt + 1, cnt); // 다음 사다리 설치하러 ㄱㄱ(cnt개만큼 설치해야 하므로)
            if (flag) { // cnt개만큼 설치한 결과 도달 가능하다면 true 리턴. 아니라면 다음 경우의 수 탐색
                return true;
            }
            ladder[startLadder][h] = false; // 설치한 사다리 삭제
        }

        return false; // 모든 경우를 탐색해도 방법이 없는 경우 false 리턴
    }

    // 각 시작점으로부터 도착점까지 도달이 가능하면 true, 아니면 false 리턴
    private static boolean canGo() {
        for (int start=0; start<N; start++) { // start : 시작점
            int arriveNum = rideLadder(start, 0); // arriveNum : 사다리 타보고 도착한 지점
            if (start != arriveNum) { // 시작점과 도착점이 같지 않으면 false 리턴
                return false;
            }
        }
        return true; // 모든 시작점과 도착점이 같으면 true 리턴
    }

    // start번째 막대의 startH번째 높이에서부터 사다리 타보기
    private static int rideLadder(int start, int startH) {
        for (int h = startH; h < H; h++) { // h : 현재 탐색 중인 사다리의 높이
            if (ladder[start][h]) { // start번째 사다리의 높이 h에서 오른쪽으로 갈 수 있는 경우
                return rideLadder(start + 1, h + 1); // 왼쪽으로 이동하고 그 결과 리턴
            }
            if (start > 0 && ladder[start - 1][h]) { // start번째 사다리의 높이 h에서 왼쪽으로 갈 수 있는 경우
                return rideLadder(start - 1, h + 1); // 오른쪽으로 이동하고 그 결과 리턴
            }
        }
        return start; // startH가 H인 경우, 즉 맨 밑에까지 내려온 경우 start(현재 탐색 중인 막대이자 도착점) 리턴
    }
}
