// 투포인터
// 두 큐의 sum을 비교해서 큰 쪽의 원소를 pop하여 다른쪽에 insert하는 과정을 반복
// 일정 횟수 이상 수행이 되면 answer를 -1로 설정한다

class Solution {
    
    static int[] arr;
    
    public int solution(int[] queue1, int[] queue2) {
        arr = new int[queue1.length + queue2.length];
        long sum1 = 0, sum2 = 0;
        for (int i=0; i<queue1.length; i++) {
            arr[i] = queue1[i];
            sum1 += queue1[i];
        }
        for (int i=0; i<queue2.length; i++) {
            arr[i + queue1.length] = queue2[i];
            sum2 += queue2[i];
        }
        
        int start1 = 0, start2 = queue1.length;
        
        int answer = 0;
        while (sum1 != sum2) {
            if (sum1 < sum2) {
                sum2 -= arr[start2];
                sum1 += arr[start2];
                start2 = moveStart(start2);
            } else {
                sum1 -= arr[start1];
                sum2 += arr[start1];
                start1 = moveStart(start1);
            }
            if (answer == arr.length * 4) {
                answer = -1;
                break;
            }
            answer++;
        }
        
        return answer;
    }
    
    static int moveStart(int start) {
        return (start + 1) == arr.length ? 0 : start + 1;
    }
}
