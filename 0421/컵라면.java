package s0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * n개의 문제가 있을때 각 문제에 대해 데드라인과 받을 수 있는 컵라면 개수가 주어진다면, 
 * 가장 많이 받을 수 있는 컵라면 수 구하기
 */
public class Main_BJ1781 {							//컵라면
	
	static class Problem implements Comparable<Problem>{
		int dead;
		int cup;
		
		public Problem(int dead, int cup){
			this.dead = dead;
			this.cup = cup;
		}

		@Override
		public int compareTo(Problem o) {
			if(this.dead == o.dead) {				
				return Integer.compare(o.cup, this.cup); //데드라인이 같은 경우엔 내림차순(더 많은 컵라면 우선)
			}
			return Integer.compare(this.dead, o.dead);	//데드라인 짧은것부터 큐에 우선순위로
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Problem> p = new PriorityQueue<>();				//문제정보 담을 우선순위 큐
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int dead = Integer.parseInt(st.nextToken());
			int cup = Integer.parseInt(st.nextToken());
			
			p.offer(new Problem(dead, cup));
		}
		
		PriorityQueue<Integer> solve = new PriorityQueue<>();			//푼 것들 담을 우선순위 큐
		while(!p.isEmpty()) {
			Problem temp = p.poll();
			
			if(solve.size() < temp.dead) {							//현재 데드라인보다 큐가 비어있으면
				solve.offer(temp.cup);								//담을 수 있으니까 담기
			}
			else {
				if(solve.peek() < temp.cup) {	//그게 아니면 이미 채워진것보다 많은 컵라면을 들고 있는 경우에만 가장 작은 애랑 바꿔주기
					solve.poll();
					solve.offer(temp.cup);
				}
			}
		}
		
		int total = 0;
		for(int i : solve) {
			total += i;
		}
		
		System.out.println(total);
		
	}
	
}
