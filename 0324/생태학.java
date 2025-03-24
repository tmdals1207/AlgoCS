import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class 생태학 {

    static TreeMap<String, Integer> trees; // 이름을 Key로 가지고 나온 횟수를 Value로 가지는 TreeMap 생성.
                                           // TreeMap을 사용한 이유 : TreeMap은 기본으로 자동정렬.

	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        double cnt = 0.0; // 나무를 입력받은 횟수.

        while(true) {
            String input = br.readLine();
            
            if (input==null) break; // 입력이 더 이상 존재하지 않으면 break;
            cnt++; // 입력이 존재한다면 cnt값 1 증가.

            // TreeMap에 입력받은 나무를 넣는데 해당 TreeMap에서 Key 값으로 해당 나무를 찾아서 값을 가져오고 
            // 만약 없다면 0을 가져와서 +1을 하고 없으면 그냥 그 값에다가 +1 해서 저장.
            trees.put(input, trees.getOrDefault(input, 0) + 1); 
        }
        
        // TreeMap이 빌 때까지 이미 정렬이 되어 있으니 가장 앞에서 계속 꺼내서 BufferedWriter 더하기.
       while (!trees.isEmpty()) {
            bw.write(trees.firstKey() + " " + trees.get(trees.firstKey())/cnt);
            trees.pollFirstEntry();
       }
            
    }
}