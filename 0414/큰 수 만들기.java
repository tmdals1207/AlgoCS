class Solution {
    
    static char[] answer;
    
    public String solution(String number, int k) {
        char[] chars = number.toCharArray();
        
        int cnt = number.length() - k;
        answer = new char[cnt];
        
        int start = 0; // 선택 시작 index
        for (int i=1; i<=cnt; i++) { // i번째 숫자 선택 과정
            char max = '0'; // 선택 범위 중 가장 큰 수
            for (int j=start; j<number.length() - (cnt - i); j++) {
                if (chars[j] > max) {
                    max = chars[j];
                    start = j + 1;
                }
            }
            answer[i - 1] = max;
        }
        
        return new String(answer);
    }
}
