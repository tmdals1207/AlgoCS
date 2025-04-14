import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 질문 게시판 뒤지다가 찾은 코드
// https://www.acmicpc.net/board/view/139985
public class Main {
    static int N;
    static int M;
    static int K;
    static long a[];
    static int leaf_start, tree_last_index;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        int tree_size = 2;
        while((tree_size = tree_size * 2) <=  N){} // 트리 사이즈를 while문으로 찾는 방법
        tree_size *= 2;

        leaf_start = tree_size/2; // 리프 노드 시작점
        tree_last_index = tree_size - 1;
        a = new long[tree_size];

        //세그먼트 트리 생성=============================================================================================
        for(int i = leaf_start; i < leaf_start + N; i++){
            a[i] = Long.parseLong(bufferedReader.readLine());
        }
        sagment_tree();

        //로직 실행 부분================================================================================================
        for(int i = 0; i < M + K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int check = Integer.parseInt(stringTokenizer.nextToken());

            //로직 실행 부분(수 바꾸기)===================================================================================
            if(check == 1){
                int index = Integer.parseInt(stringTokenizer.nextToken()) + leaf_start - 1;
                long value = Long.parseLong(stringTokenizer.nextToken());
                change(index, value);
            }

            //로직 실행 부분(수 더하기)===================================================================================
            if(check == 2){
                int s = Integer.parseInt(stringTokenizer.nextToken()) + leaf_start - 1;
                int e = Integer.parseInt(stringTokenizer.nextToken()) + leaf_start - 1;
                long print = sum(s,e);
                if(i != M+K-1) {
                    stringBuilder.append(print + "\n");
                }else{
                    stringBuilder.append(print);
                }
            }

        }
        System.out.println(stringBuilder);

    }

    // 입력 배열 자체를 트리로 사용
    // 트리 아래쪽부터 세그먼트 트리로 만들기
    static void sagment_tree(){
        for(int i = tree_last_index-1; i > 1; i -= 2){
            a[i/2] = a[i] + a[i+1];
        }
    }

    static void change(int index, long value){
        long diff = value - a[index];
        for(int i = index; i >= 1; i /= 2){
            a[i] = a[i] + diff;
        }
    }

    static long sum(int s, int e){
        int sum = 0;
        while(s <= e){
            if(s % 2 == 1){ // s를 짝수로 만들기..?
                sum+=a[s];
                s++;
            }
            if(e % 2 == 0){ // e를 홀수로 만들기..?
                sum+=a[e];
                e--;
            }
            s/=2;
            e/=2;
        }
        return sum;
    }

}
