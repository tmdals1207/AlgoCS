package s0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 절댓값 힙 = 절댓값이 가장 작은 수를 poll 하는 힙
 * 단, 절댓값이 가장 작은 수가 여러개라면 가장 작은 수를 출력할 것.
 * 입력 받은 수가 0이 아니면 절댓값 힙에 넣고, 0이면 poll한 값을 출력
 */
public class Main_BJ11286 {                                //절댓값 힙

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //comparator 잘 안 써봐서 어색하넹..
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(Math.abs(o1) == Math.abs(o2)){
                    return Integer.compare(o1, o2);
                }
                return Integer.compare(Math.abs(o1), Math.abs(o2));
            }
        });

        for(int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            if(m == 0){
                if(pq.isEmpty()){
                    System.out.println(0);
                }else {
                    System.out.println(pq.poll());
                }
            }else {
                pq.offer(m);
            }
        }
    }

}
