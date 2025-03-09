import java.io.*;
import java.util.*;

public class 고냥이 {
    static int N;
    static char[] words; // 입력받은 문자열을 문자배열로 변환하여 저장.
    
    
    private static int meow() {
    	// 문자 별로 등장하는 횟수를 저장할 HashMap 사전 생성.
        Map<Character, Integer> dict = new HashMap<>();
        
        // 투포인터와 최대 문자열의 길이, 사전의 길이 변수 생성.
        int left = 0, right = 0, maxLen = 0, dictCnt = 0;

        // 오른쪽 포인터가 받은 문자열의 길이보다 작을 때까지 수행.
        while (right < words.length) {
            char c = words[right]; // 현재 분석할 문자 추출.

            // 이미 사전 안에 존재하면 해당 문자의 Value 값을 1 증가.
            if (dict.containsKey(c)) {
                dict.put(c, dict.get(c) + 1);
            } 
            // 만약 사전 안에 존재하지 않았다면 Value 값에 1을 지정하여 추가.
            else {
                dict.put(c, 1);
                dictCnt++; // 사전 안에 들어가 있는 문자의 수 증가.
            }

            // 사전의 크기가 N보다 크면 계속 수행.
            while (dictCnt > N) {
                char leftChar = words[left]; // 왼쪽 포인터가 가리키는 문자 추출.
                
                // 사전에서 해당 문자의 Value 값을 1 감소.
                dict.put(leftChar, dict.get(leftChar) - 1); 
                
                // Value 값이 모두 감소되어 0이 되면 사전에서 삭제하고 사전의 크기 1 감소.
                if (dict.get(leftChar) == 0) {
                    dict.remove(leftChar);
                    dictCnt--;
                }
                // 해당 문자의 Value가 0이 될 때까지 왼쪽 포인터 오른쪽으로 이동.
                left++;
            }

            // 기존의 최대 문자열의 길이와 현재의 두 포인터 사이의 거리를 비교.
            maxLen = Math.max(maxLen, right - left + 1);
            right++; // 오른쪽 포인터 이동.
        }

        // 최대 문자열의 길이 리턴.
        return maxLen;
    }
    
    public static void main(String[] args) throws IOException {
        
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    	N = Integer.parseInt(br.readLine());
        words = br.readLine().toCharArray();

        System.out.println(meow());
    }
}