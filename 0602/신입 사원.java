package s0602;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

/*
    서류심사 성적과 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않은 자만 선발하고자 함.
    ex) A지원자의 서류, 면접 점수가 모두 B보다 등수가 낮으면 선발되지 않음
    이 때 신규 채용에서 선발 가능한 신입 사원의 최대 수 구하기
    *두 성적 순위는 1위부터 n위까지 동석차 없이 결정된다고 가정.
    *입력값은 성적 값이 아닌 성적 순위임!!
 */
public class Main_BJ1946 {                                         //신입 사원
    static class Score{
        int doc;
        int interview;

        public Score(int doc, int interview){
            this.doc = doc;
            this.interview = interview;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            Score[] arr = new Score[n];
            for(int j = 0; j < n; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                int doc = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());
                arr[j] = new Score(doc, interview);
            }

            //서류 점수 기준으로 정렬
            Arrays.sort(arr, (o1, o2) -> Integer.compare(o1.doc, o2.doc));

            int sol = 1;            //서류 1등은 무조건 통과함
            int min = arr[0].interview;
            for(int j = 1; j < n; j++){             //서류 1등 빼고 나머지들 면접 등수 비교
                if (arr[j].interview < min) {       //등수가 더 큰(숫자 상으로는 작음) 경우에만 합격
                    sol++;
                    min = arr[j].interview;
                }
            }

            System.out.println(sol);
        }
    }
}
