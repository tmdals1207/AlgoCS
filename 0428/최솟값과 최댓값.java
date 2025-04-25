package s0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
   * n개의 정수가 있을때, a번째부터 b번째까지 정수 중 제일 작은 정수 and 제일 큰 정수를 찾는데
   * 문제는 a,b 쌍이 m개나 있다는거임 ㅇ0ㅇ
 */
public class Main_BJ2357 {                                  //최솟값과 최댓값
    static int[] minTree, maxTree;

    //최솟값 세그먼트 트리 생성
    private static void initMin(int[] arr, int node, int start, int end) {
        if(start == end){
            minTree[node] = arr[start];
            return;
        }

        initMin(arr, node*2, start, (start+end)/2);
        initMin(arr, node*2 + 1, (start+end)/2 + 1, end);
        minTree[node] = Math.min(minTree[node*2], minTree[node*2 + 1]);
    }

    //최댓값 세그먼트 트리 생성
    private static void initMax(int[] arr, int node, int start, int end) {
        if(start == end){
            maxTree[node] = arr[start];
            return;
        }

        initMax(arr, node*2, start, (start+end)/2);
        initMax(arr, node*2 + 1, (start+end)/2 + 1, end);
        maxTree[node] = Math.max(maxTree[node*2], maxTree[node*2 + 1]);
    }

    //최솟값 찾기
    private static int findMin(int node, int start, int end, int a, int b) {
        if(start > b || end < a){
            return Integer.MAX_VALUE;              //범위를 벗어나면 탈출
        }

        if(a <= start && end <= b){
            return minTree[node];
        } else {
            return Math.min(findMin(node*2, start, (start+end)/2, a, b)
                    , findMin(node*2 + 1, (start+end)/2 + 1, end, a, b));
        }
    }

    //최댓값 찾기
    private static int findMax(int node, int start, int end, int a, int b) {
        if(start > b || end < a){
            return Integer.MIN_VALUE;               //범위를 벗어나면 탈출
        }
        
        if(a <= start && end <= b){
            return maxTree[node];
        } else {
            return Math.max(findMax(node*2, start, (start+end)/2, a, b)
                    , findMax(node*2 + 1, (start+end)/2 + 1, end, a, b));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];

        int h = (int)Math.ceil(Math.log(n) / Math.log(2));
        int size = (int)Math.pow(2, h+1);
        minTree = new int[size];                             //최솟값 세그먼트 트리
        maxTree = new int[size];                             //최댓값 세그먼트 트리

        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMin(arr, 1, 1, n);
        initMax(arr, 1, 1, n);                     //트리 생성

        for(int i =0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int min = findMin(1, 1, n, a, b);
            int max = findMax(1, 1, n, a, b);

            System.out.println(min + " " + max);
        }
    }

}
