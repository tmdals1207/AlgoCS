package s0526;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    n줄에 0~9사이의 숫자가 3개씩 적혀있음(nX3 배열)
    맨 위에서 시작해서 한 줄씩 내려가는데 인접한 수에만 접근이 가능(0번 인덱스에서 2번 인덱스로 내려가기 불가능)
    얻을 수 있는 최대, 최소 점수 구하기
    * (1 <= n <= 100000)
 */
public class Main_BJ2096 {              //내려가기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] dp_max = new int[2][3];             //최댓값 비교용
        int[][] dp_min = new int[2][3];             //최솟값 비교용, 현재값과 이전값만 담아서 비교
        int max, min;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());

                dp_max[1][j] = num;                 //인덱스가 1 = 현재 값, 0 = 직전 값
                dp_min[1][j] = num;                 //입력 받을때마다 처리
            }

            if(i != 0) {                            // 조건에 맞게 탐색시작
                dp_max[1][0] += Math.max(dp_max[0][0], dp_max[0][1]);
                dp_max[1][1] += Math.max(dp_max[0][0], Math.max(dp_max[0][1], dp_max[0][2]));
                dp_max[1][2] += Math.max(dp_max[0][1], dp_max[0][2]);

                dp_min[1][0] += Math.min(dp_min[0][0], dp_min[0][1]);
                dp_min[1][1] += Math.min(dp_min[0][0], Math.min(dp_min[0][1], dp_min[0][2]));
                dp_min[1][2] += Math.min(dp_min[0][1], dp_min[0][2]);
            }

            for(int j = 0; j < 3; j++) {                //현재 탐색 값을 이전 값으로 저장
                dp_max[0][j] = dp_max[1][j];
                dp_min[0][j] = dp_min[1][j];
            }
        }

        max = Math.max(Math.max(dp_max[1][0], dp_max[1][1]), dp_max[1][2]);
        min = Math.min(Math.min(dp_min[1][0], dp_min[1][1]), dp_min[1][2]);

        System.out.println(max + " " + min);

    }
}
