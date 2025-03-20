import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소세지를 일렬로 놓았을 때 총 길이가 (소세지 개수 * 평론가 수)라고 가정
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int ssgCnt = Integer.parseInt(split[0]);
        int userCnt = Integer.parseInt(split[1]);

        if (ssgCnt % userCnt == 0) { // 소세지가 평론가 배수만큼 있는 경우
            System.out.println(0);
            return;
        }
        if (userCnt % ssgCnt == 0) { // 평론가가 소세지 배수만큼 있는 경우
            System.out.println((userCnt / ssgCnt - 1) * ssgCnt);
            return;
        }

        int lcm = ssgCnt * userCnt;
        for (int i=Math.max(ssgCnt, userCnt); i<=ssgCnt * userCnt; i++) { // 소세지와 평론가의 최소공배수 구하기
            if (i % ssgCnt == 0 && i % userCnt == 0) {
                lcm = i;
                break;
            }
        }

        int x = 0; // ssgCnt * userCnt 보다 작은 수 중 lcm의 배수의 개수
        for (int i=lcm; i<ssgCnt*userCnt; i++) { 
            if (i % lcm == 0) {
                x++;
            }
        }
        int answer = (userCnt - 1) - x;
        System.out.println(answer);
    }
}
