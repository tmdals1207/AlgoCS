// 방법 1 : 배열을 2중 for문으로 순회하며 포함하는지 확인 - > 시간초과
// 방법 2 : 배열을 정렬하고 다음에 올 문자열을 확인 - > 가능할듯?
// contains를 사용하니 접두사가 아니여도 false로 만들어버림
// 해결하기 위해서 substring 사용해서 이전 문자열의 길이만큼 자르고 같은지 비교
// 다음 문자열이 더 작으면 Exception 발생
// String의 startsWith 메소드 사용해서 해당 문제 해결

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        List<String> list = new ArrayList<>();
        
        boolean answer = true;
        
        for (String s : phone_book) {
            list.add(s);
        }
        
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        
        for (int i = 0; i < sortedList.size()-1; i++) {
            String str = sortedList.get(i);
            if (sortedList.get(i+1).startsWith(str)) {
                answer = false;
            }
        }
        
        return answer;
    }
}