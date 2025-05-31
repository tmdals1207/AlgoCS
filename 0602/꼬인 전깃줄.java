import java.util.*;
import java.io.*;

// [LIS]
// 증가하는 부분 수열 중 가장 긴 부분 수열을 찾는 문제
// 단, 시간 복잡도를 고려해야 함 -> 이분탐색 사용

// [알고리즘 이해]
// tails라는 정수 배열을 둔다. 길이는 N + 1이다.
// tails[i]에 저장된 값은 "길이가 i인 여러 증가하는 부분수열들의 마직막 원소 중 가장 작은 값"이다.
    // 예를 들어 만들 수 있는 증가하는 부분수열이 [1, 2, 3]과 [1, 2, 4]이면 tails[3] = 3이라는 뜻
// 주어진 배열 arr의 원소를 하나씩 보면서
    // 만약 해당 원소(A)가 tails에 저장된 모든 값보다 크다면 tails의 마지막에 A를 추가한다.
    // 그렇지 않다면 tails에 저장된 값 중 A보다 크거나 같은 값들 중 가장 작은 값의 위치에 A를 덮어씌운다.
// 이러한 tails 배열을 두고 예제를 풀어보자.

// [예제 적용]
// 예제1에서 주어진 수열(arr)이 [2, 3, 4, 1]이기 때문에 tails 배열은 길이가 5인 정수배열이 된다.
// tails[0]은 0으로 채운다.
// arr의 원소를 하나씩 돌면서 tails 배열을 채운다.
// arr의 첫 번째 원소 2
    // tails 배열의 모든 값보다 2가 크기 때문에 tails 배열의 마지막에 2를 추가한다.
// arr의 두 번째 원소 3
    // 마찬가지로 tails 배열의 마지막에 3을 추가한다.
// arr의 세 번째 원소 4
    // 마찬가지로 tails 배열의 마지막에 3을 추가한다.
// arr의 네 번째 원소 1
    // 1보다 크거나 같은 원소들 중 가장 작은 값인 2를 1로 덮어씌운다.(2를 찾는 과정에 이분 탐색이 사용된다)
// 결과적으로 tails 배열은 [1, 3, 4]가 되고 가장 긴 증가하는 부분 수열의 길이는 3이 된다.

// [2를 1로 덮어 씌워도 되는 이유와 그렇게 하는 이유]
// 덮어 씌워도 되는 이유는 1은 어짜피 2보다 작기 때문에 "LIS의 길이"를 구함에 있어서 문제가 되지 않는다.
// 다만, 실제 LIS를 구성하는 원소를 구하기 위해선 추가적인 장치가 필요하다.
// 덮어 씌우는 이유는 1로 시작하는 LIS를 구하기 위해서이다.
// 예를 들어 주어진 배열이 [2, 3, 4, 1, 2]라면 tails 배열은 최종적으로 [1, 2, 4]로 완성이 되고,
// 주어진 배열이 [2, 3, 4, 1, 2, 3, 4]라면 tails 배열은 최종적으로 [1, 2, 3, 4]로 완성이 되어서 1로 시작하는 LIS의 길이를 구할 수 있게 된다.
// 나름대로 이해해서 정리해보면, 2를 1로 덮어 씌우는 이유는 
// "지금까지 구한 LIS의 길이에는 영향을 주지 않으면서 앞으로 구할 수 있는 LIS의 길이를 염두에 두기 위함"이다.

// ps. tails를 List로 구현하면 편하다.
public class Main {

    static int N;
    static int[] arr;
    static List<Integer> tails;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tails = new ArrayList<>();

        for (int i=0; i<N; i++) {
            int a = arr[i];
            if (tails.isEmpty() || tails.get(tails.size() - 1) < a) {
                tails.add(a);
                continue;
            }

            int idx = findIdx(a);
            tails.set(idx, a);
        }
        
        System.out.println(N - tails.size());
    }

    static int findIdx(int value) {
        int left = 0;
        int right = tails.size() - 1;
        int idx = tails.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (tails.get(mid) == value) {
                return mid;
            }
            if (tails.get(mid) < value) {
                left = mid + 1;
            } else {
                idx = mid;
                right = mid - 1;
            }
        }

        return idx;
    }
}
