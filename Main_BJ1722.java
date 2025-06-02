package s0609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    1부터 N까지의 수를 임의로 배열한 순열은 총 N! = N×(N-1)×…×2×1 가지
    순열 정렬 = 첫 번째 수가 작은 것이 순서상에서 앞서며, 첫 번째 수가 같으면 두 번째 수가 작은 것이, 두 번째 수도 같으면 세 번째 수가 작은 것이….
    N이 주어지면, 아래의 두 소문제 중에 하나를 풀어야 한다. k가 주어지면 k번째 순열을 구하고,
    임의의 순열이 주어지면 이 순열이 "몇 번째 순열"인지를 출력하는 프로그램을 작성
    *N(1 ≤ N ≤ 20)이 주어진다. 둘째 줄의 첫 번째 수는 소문제 번호이다. 1인 경우 k(1 ≤ k ≤ N!)를 입력받고, 2인 경우 임의의 순열을 나타내는 N개의 수를 입력
    *N개의 수에는 1부터 N까지의 정수가 한 번씩만
 */
public class Main_BJ1722 {                                  //순열의 순서
    static int n;
    static long k;
    static int[] arr;
    static long[] fact;
    static boolean[] visited;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        visited = new boolean[n + 1];
        fact = new long[n + 1];
        fact[0] = 1;
        for(int i = 1; i <= n; i++){                    //팩토리얼 배열 생성
            fact[i] = fact[i - 1] * i;
        }

        k = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        if(num == 1) {
            k = Long.parseLong(st.nextToken()) - 1;

            findPermutation(n);
        } else {
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            findOrder(0);
            System.out.println(k + 1);
        }
    }

    private static void findPermutation(int cnt) {
        if (cnt == 0) {  //남은 자릿수가 없을 때
            StringBuilder sb = new StringBuilder();
            for (int num : list) {
                sb.append(num).append(" ");
            }
            System.out.println(sb);
            return;
        }

        long blockSize = fact[cnt - 1];  //나머지 자리에 가능한 순열의 경우의 수
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (k < blockSize) {  //현재 자리에 i를 추가
                    list.add(i);
                    visited[i] = true;
                    findPermutation(cnt - 1);
                } else {  //현재 블록을 건너뜀
                    k -= blockSize;
                }
            }
        }
    }

    private static void findOrder(int cnt) {
        if(cnt == n){
            return;
        }

        int cur = arr[cnt];
        long count = 0;

        for(int i = 1; i < cur; i++){
            if(!visited[i]){
                count += fact[n - cnt - 1];
            }
        }

        visited[cur] = true;        //방문처리
        k += count;
        findOrder(cnt + 1);
    }
}
