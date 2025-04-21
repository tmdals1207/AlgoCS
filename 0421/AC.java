import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class AC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringBuilder sb = new StringBuilder();

            boolean isError = false;    // 에러 여부 체크용 플래그
            boolean isReverse = false;  // 현재 배열이 뒤집혀있는지 여부

            String orderInput = br.readLine(); 
            char[] order = orderInput.toCharArray();  

            int num = Integer.parseInt(br.readLine()); 

            String numberInput = br.readLine();
            String[] nums = numberInput.substring(1, numberInput.length() - 1).split(",");  
            // 대괄호를 제거하고 ',' 기준으로 숫자 분리

            Deque<Integer> numbers = new ArrayDeque<>(); 
            if (!Objects.equals(nums[0], "")) {  // 빈 배열이 아닌 경우
                for (String s : nums) {
                    numbers.add(Integer.parseInt(s));  // 문자열 숫자를 정수로 변환 후 덱에 저장
                }
            }

            // 명령어 실행
            for (char c : order) {
                if (c == 'R') {  // 'R' 명령 -> 배열 뒤집기
                    isReverse = !isReverse;
                } else {  // 'D' 명령 -> 원소 삭제
                    if (numbers.isEmpty()) {  // 삭제 시도했는데 배열이 비어있으면 에러
                        isError = true;
                        break;
                    }
                    if (!isReverse) {  // 정방향일 경우 앞에서 삭제
                        numbers.pollFirst();
                    } else {           // 역방향일 경우 뒤에서 삭제
                        numbers.pollLast();
                    }
                }
            }

            if (isError) {  // 에러 발생 시
                System.out.println("error");
            } else {  // 정상 처리 시
                sb.append("[");
                while (!numbers.isEmpty()) {
                    sb.append(isReverse ? numbers.pollLast() : numbers.pollFirst());  // 현재 방향에 맞게 출력
                    if (!numbers.isEmpty()) {  // 마지막 원소가 아니면 콤마 추가
                        sb.append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb);
            }
        }
    }
}
