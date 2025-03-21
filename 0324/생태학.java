import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

// 생태학
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new TreeMap<>();

        int sum = 0;
        while (true) {
            String input = br.readLine();
            if (input == null || input.length() == 0) {
                break;
            }

            map.merge(input, 1, Integer::sum);
            sum++;
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.print(entry.getKey() + " ");
            double v = (double) entry.getValue() / sum * 100;
            System.out.println(String.format("%.4f", (double) entry.getValue() / sum * 100));
        }
    }
}
