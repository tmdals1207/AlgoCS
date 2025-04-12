import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static char[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        answer = new char[N][N];
        for (char[] c : answer) {
            Arrays.fill(c, ' ');
        }

        print(0, 0, N);

        for (char[] a : answer) {
            System.out.println(new String(a));
        }
    }

    private static void print(int x, int y, int n) {
        if (n == 1) {
            answer[x][y] = '*';
            return;
        }

        for (int i=x; i<x+n; i+=n/3) {
            for (int j=y; j<y+n; j+=n/3) {
                if (x+n/3 <= i && i<x+n/3*2 && y+n/3<=j && j<y+n/3*2) {
                    continue;
                }
                print(i, j, n/3);
            }
        }
    }
}
