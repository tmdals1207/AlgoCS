import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        int left = 0, right = arr.length - 1;                  // left 포인터 -> arr의 시작, right 포인터 -> arr의 끝
        int min = Integer.MAX_VALUE;                           // min : 0에 가장 가까운 특성값
        int leftAnswer = arr[left], rightAnswer = arr[right];  // 답 저장

        while (left < right) {

            int sum = arr[left] + arr[right]; // sum : 두 용액의 특성값의 합
            if (Math.abs(sum) < min) {        // 0에 가장 가까운 특성값 업데이트
                min = Math.abs(sum);
                leftAnswer = arr[left];
                rightAnswer = arr[right];
            }

            // 더 작은 특성값이 있는지 탐색
            // sum이 0보다 작으면 sum을 높여야 하므로 left 증가시킴
            // sum이 0보다 크면 sumd을 낮춰야 하므로 right 감소시킴
            // left와 right가 arr 배열의 가운데에서 양쪽으로 출발해도 되나? => left, right의 방향이 고정되어 있으므로 답을 찾지 못할 수 있음
            if (sum < 0) {        
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                break;
            }
        }

        System.out.println(leftAnswer + " " + rightAnswer);
    }
}
