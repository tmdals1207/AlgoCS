import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N; // 최대 인식 개수
    static String str; // 문자열
    static int[] countChar = new int[26];
    static int answer = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = br.readLine();
        // 입력==================================

        int left = 0, right = 0;
        while (true) {
            if (countChar[getCharIdx(right)]++ == 0) {
                count++;
            }

            if (count > N) { // 최대 인식 개수를 넘은 경우
                while (true) { // left 포인터 조정
                    if (--countChar[getCharIdx(left)] == 0) {
                        left++;
                        break;
                    }
                    left++;
                }
                count--;
            }

            answer = Math.max(answer, right - left + 1);

            if (right == str.length() - 1) {
                break;
            }

            right++;
        }
        System.out.println(answer);
    }

    private static int getCharIdx(int right) {
        return str.charAt(right) - 'a';
    }
}
