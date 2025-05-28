package s0602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    양의 정수 값을 가지는 산성 용액과 음의 정수 값을 가지는 알칼리성 용액이 존재, 1~1000000000까지
    같은 양의 세가지 용액을 합한 특성값 = 각 용액의 특성값의 합
    같은 양의 세 개의 용액을 혼합해 특성값이 0에 가장 가까운 용액을 찾는 세 용액을 구하기
    * 3 <= n <= 5000 / -1000000000 <= Ni <= 1000000000 / 각 용액은 모두 다른 특성값
    * 세 용액은 오름차순으로 출력, 답이 여러개면 그 중 아무거나
 */
public class Main_BJ2473 {                                    //세 용액
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int[] sol = new int[3];

        long min = Long.MAX_VALUE;

        outer: for(int i = 0; i < n; i++){
            int left = 0;
            int right = n-1;
            while(left < right) {
                if (left == i) {
                    left++;
                    continue;
                }

                if (right == i) {
                    right--;
                    continue;
                }

                long sum = arr[i] + arr[left] + arr[right];
                if(min > Math.abs(sum)){
                    min = Math.abs(sum);
                    sol[0] = i;
                    sol[1] = left;
                    sol[2] = right;
                }

                if(sum > 0){
                    right--;
                } else if(sum < 0){
                    left++;
                } else{
                    break outer;
                }
            }
        }

        Arrays.sort(sol);
        System.out.println(arr[sol[0]] + " " + arr[sol[1]] + " " + arr[sol[2]]);
    }
}
