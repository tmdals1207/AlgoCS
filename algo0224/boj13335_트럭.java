package algo0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class boj13335_트럭 {

    static int n; // 트럭의 수
    static int w; // 다리의 길이
    static int L; // 다리의 최대 하중
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Bridge {
        Queue<Integer> bridge = new LinkedList<>();
        int length;
        int nowWeight;
        int limit;

        Bridge (int length, int limit) {
            this.length = length;
            this.limit = limit;
            for (int i=0; i<length; i++) {
                bridge.add(0);
            }
        }

        boolean enter(int weight) {
            int poll = bridge.poll();
            nowWeight -= poll;
            if (nowWeight + weight > limit) {
                bridge.add(0);
                return false;
            }
            bridge.add(weight);
            nowWeight += weight;
            return true;
        }

        int done() {
            int time = 0;
            while (nowWeight > 0) {
                nowWeight -= bridge.poll();
                time++;
            }
            return time;
        }
    }

    public static void main(String[] args) throws IOException {
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = arr[0]; // 트럭의 수
        w = arr[1]; // 다리의 길이
        L = arr[2]; // 다리의 최대 하중

        int[] trucks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Bridge bridge = new Bridge(w, L);

        int time = 0;
        for (int i=0; i<n; i++) {
            int weight = trucks[i];
            if (!bridge.enter(weight)) {
                i--;
            }
            time++;
        }
        time += bridge.done();
        System.out.println(time);
    }
}
