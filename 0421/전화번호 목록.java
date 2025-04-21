import java.util.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        Set<String> set = new HashSet<>();
        for (String p : phoneBook) {
            set.add(p);
        }
        
        for (String p : phoneBook) {
            StringBuilder sb = new StringBuilder();
            char[] chars = p.toCharArray();
            for (int i=0; i<chars.length - 1; i++) {
                sb.append(chars[i]);
                if (set.contains(sb.toString())) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
