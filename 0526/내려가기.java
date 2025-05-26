import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] arr;
    static int[][] maxDp;
    static int[][] minDp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][3];
        maxDp = new int[N][3];
        minDp = new int[N][3];

        for (int i=0; i<N; i++) {
            String input = bf.readLine();
            String[] split = input.split(" ");
            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
            arr[i][2] = Integer.parseInt(split[2]);
        }

        maxDp[0][0] = arr[0][0];
        maxDp[0][1] = arr[0][1];
        maxDp[0][2] = arr[0][2];

        minDp[0][0] = arr[0][0];
        minDp[0][1] = arr[0][1];
        minDp[0][2] = arr[0][2];

        for (int i=1; i<N; i++) {
            maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + arr[i][0];
            maxDp[i][1] = Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2])) + arr[i][1];
            maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + arr[i][2];

            minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + arr[i][0];
            minDp[i][1] = Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2])) + arr[i][1];
            minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + arr[i][2];
        }

        System.out.println(Arrays.stream(maxDp[N - 1]).max().getAsInt() + " " + Arrays.stream(minDp[N - 1]).min().getAsInt());
    }
}
