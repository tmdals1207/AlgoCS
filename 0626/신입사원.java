import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 신입사원 {

    static int N; 
    static List<Employee> employees = new ArrayList<>();

    static int test() {
        int minS2 = Integer.MAX_VALUE;
        int result = 0;
        for (Employee e : employees) {
            if (e.s2 < minS2) {
                result++;
                minS2 = e.s2;
            }
        }
        return result;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            employees.clear();
            N = Integer.parseInt(br.readLine());
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                Employee em = new Employee(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                employees.add(em);
            }
            employees.sort(Comparator.comparingInt(e -> e.s1));
            System.out.println(test());
        }
    }
}

class Employee{
    int s1, s2;

    public Employee(int s1, int s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
}
