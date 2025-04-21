import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] arr1, int[] arr2) {
        Queue<Long> queue1 = new LinkedList<>();
        Queue<Long> queue2 = new LinkedList<>();
        
        for (long n1 : arr1) {
            queue1.add(n1);
        }
        
        for (long n2 : arr2) {
            queue2.add(n2);
        }
        
        long sum1 = 0;
        long sum2 = 0;
        long diff = 0;
        int cnt = 0;
        
        // 큐1의 합 구하기
        for (long q1 : queue1) {
            sum1 += q1;
        }
        
        // 큐2의 합 구하기
        for (long q2 : queue2) {
            sum2 += q2;
        }
        
        // 두 큐의 합이 같아질 때까지 반복
        while(sum1 != sum2) {
            
            // 종료 조건: 반복이 비정상적으로 길어지는 경우 무한루프 방지
            if(cnt > (queue1.size() + queue2.size()) * 4) { 
                cnt = -1;
                break;
            }
            
            // sum1이 작을 경우 -> queue2에서 queue1으로 값을 이동
            if(sum1 < sum2) {
                long num = queue2.peek();
                sum1 += num;
                sum2 -= num;
                queue1.offer(queue2.poll());
            }
            // sum1이 더 클 경우 -> queue1에서 queue2로 값을 이동
            else {
                long num = queue1.peek();
                sum1 -= num;
                sum2 += num;
                queue2.offer(queue1.poll());
            }
            cnt++;  // 이동 횟수 증가
        }
        
        return cnt;
    }
}