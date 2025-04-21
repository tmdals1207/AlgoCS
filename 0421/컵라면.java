import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Baekjoon1781 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ramenCount;
	static class Problem{
		int deadLine, ramen;
		public Problem(int deadLine, int ramen) {
			this.deadLine = deadLine;
			this.ramen = ramen;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		ArrayList<Problem> problems = new ArrayList<>();
		ramenCount = 0;
		
		for(int i = 0; i < N; i++) {
			String[] info = br.readLine().split(" ");
			int deadLine = Integer.parseInt(info[0]);
			int ramen = Integer.parseInt(info[1]);
			problems.add(new Problem(deadLine, ramen));
		}
		
		Collections.sort(problems, (a, b) -> {
			if(a.deadLine == b.deadLine) { // 데드라인이 같으면
				return b.ramen - a.ramen; // 라면 갯수가 많은 순으로
			}
			
			return a.deadLine - b.deadLine; // 데드라인이 짧은 순으로
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(Problem p: problems) {
			pq.add(p.ramen);
			
			if(pq.size() > p.deadLine) {
				pq.poll();
			}
		}
		
		for(int ramen: pq) {
			ramenCount += ramen;
		}
		
		System.out.println(ramenCount);
	}
}
