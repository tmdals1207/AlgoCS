package s0421;

import java.util.*;

public class Solution_Pro118667 {                                        //두 큐 합 같게 만들기

    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            Queue<Integer> q1 = new ArrayDeque<>();
            Queue<Integer> q2 = new ArrayDeque<>();

            int n = queue1.length;                      //n = 큐 하나의 길이

            long q1_sum = 0;
            long q2_sum = 0;
            for(int i = 0; i < n; i++){
                q1.offer(queue1[i]);
                q2.offer(queue2[i]);
                q1_sum += queue1[i];
                q2_sum += queue2[i];
            }

            long total_sum = q1_sum + q2_sum;

            if(total_sum % 2 != 0){         //모든 원소의 합이 홀수면 같게 만들어 줄 수 없으므로
                return -1;
            }

            int answer = 0;
            while(answer <= n*4){           //answer의 최댓값을 2n로 예상하였으나 반례가 존재하여 더 큰 범위로 설정
                if(q1_sum > q2_sum){
                    int temp = q1.poll();
                    q2.offer(temp);
                    q1_sum -= temp;
                    q2_sum += temp;
                } else if(q1_sum < q2_sum){
                    int temp = q2.poll();
                    q1.offer(temp);
                    q1_sum += temp;
                    q2_sum -= temp;
                } else{
                    break;
                }

                answer++;
            }

            if(answer > n*4){
                return -1;
            } else{
                return answer;
            }
        }
    }
}
