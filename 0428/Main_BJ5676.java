package s0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    n개로 이루어진 수열 X1...Xn이 주어짐
    k번 동안 라운드가 진행, 라운드마다 두 명령 중 하나 수행
    1. (C): 수열의 값 하나 변경 / 2. (P): 수열의 일부 Xi, Xi+1, ...Xj의 곱이 양수, 음수, 0 중 무엇인지 판별
    곱셈 명령의 결과를 한 줄로 출력하기
    ->양수 음수 0 구분만 하면 되는데 굳이 직접 곱할 필요가 있나?
 */
public class Main_BJ5676 {                  //음주 코딩
    static int[] tree, arr;

        static void init(int[] arr, int node, int start, int end){
            if(start == end){
                if(arr[start] > 0){         //리프노드에 넣을건데 양수면 1로
                    tree[node] = 1;
                } else if(arr[start] < 0){  //음수면 -1로
                    tree[node] = -1;
                } else {
                    tree[node] = 0;         //0이면 0으로
                }
            } else{
                init(arr, node*2, start, (start + end)/2);
                init(arr, node*2 + 1,(start + end)/2 + 1, end);
                tree[node] = tree[node*2] * tree[node*2 + 1];
            }
        }

        static void change(int node, int start, int end, int idx, int v){
            if(idx < start || idx > end){
                return;
            }

            if(start == end){
                tree[node] = v;
            } else{
                change(node*2, start, (start + end)/2, idx, v);
                change(node*2 + 1,(start + end)/2 + 1, end, idx, v);
                tree[node] = tree[node*2] * tree[node*2 + 1];
            }

        }

        static int product(int node, int start, int end, int i, int j){
            if(j < start || i > end){
                return 1;
            }

            if(i <= start && j >= end){
                return tree[node];
            } else{
                return product(node*2, start, (start + end)/2, i, j)
                        * product(node*2 + 1,(start + end)/2 + 1, end, i, j);
            }

        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s;
            while ((s = br.readLine()) != null && !s.isEmpty()) {
                StringTokenizer st = new StringTokenizer(s);
                StringBuilder sb = new StringBuilder();

                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());        //수열 가져오기
                arr = new int[n+1];
                for(int i = 1; i <= n; i++){
                    arr[i] = Integer.parseInt(st.nextToken());
                }

                int h = (int) Math.ceil(Math.log(n) / Math.log(2));
                int size = (int) Math.pow(2, h+1);
                tree = new int[size];

                init(arr, 1, 1, n);

                for(int i = 0; i < k; i++){
                    st = new StringTokenizer(br.readLine());
                    String str = st.nextToken();
                    int idx  = Integer.parseInt(st.nextToken());    //idx번째 요소를
                    int v = Integer.parseInt(st.nextToken());       //v로 바꿔주세요 (명령 C 한정)
                    if(str.equals("C")){
                        if(v > 0){
                            v = 1;
                        } else if(v < 0){
                            v = -1;
                        } else {
                            v = 0;
                        }
                        change(1, 1, n, idx, v);
                    } else if(str.equals("P")){
                        int answer = product(1, 1, n, idx, v);
                        if(answer > 0){
                            sb.append("+");
                        } else if(answer < 0){
                            sb.append("-");
                        } else {
                            sb.append("0");
                        }
                    }
                }
                System.out.println(sb.toString());
            }
        }
    }