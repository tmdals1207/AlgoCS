package s0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n개의 수가 주어질 때, 중간 부분의 합을 구하고자 함.
 * 다만 중간에 수의 변경이 빈번히 일어남. ex)12345 ->12645로 변경후 2번째부터 5번째 합을 구하기 ->17
 * 1<=n<=1000000 / 1<=m<=10000 / 1<=k<=10000
 * 각각 수의 개수 n, 변경이 일어난 횟수 m, 구간의 합을 구하는 횟수 k
 * a, b, c => a==1이면 b번째 수를 c로 변경, a==2이면 b부터 c까지의 합 구하기
 */
public class Main_BJ2042 {								//구간 합 구하기
	static long[] arr, tree;
	
	static void init(long[] arr, int node, int start, int end) {		//세그먼트 트리 초기화
		if(start == end) {					//배열 arr의 탐색 범위가 동일하다면, 즉 리프 노드라면
			tree[node] = arr[start];		//해당 번호에 배열값 저장
		} else {
			init(arr, node * 2, start, (start+end)/2);
			init(arr, node * 2+1, (start+end)/2 + 1, end);
			tree[node] = tree[node*2] + tree[node*2+1];
		}
		
	}
	
	static void update(int node, int start, int end, int b, long diff) {		//배열 값 변경
		if(start > b || b > end) {
			return;		//탐색 범위 밖이면 종료
		}
		
		tree[node] += diff;					//현재 노드에 변경값 적용하기
		
		if(start != end) {					//리프 노드만날때까지 자식 재귀로 호출
			update(node*2, start, (start+end)/2, b, diff);
			update(node*2+1, (start+end)/2 + 1, end, b, diff);
		}
		
	}
	
	static long partSum(int node, int start, int end, int b, int c) {		//구간 합 구하기
		if(b > end || c < start) {
			return 0;
		}
		
		if(b <= start && end <= c) {
			return tree[node];
		}
		
		return partSum(node*2, start, (start+end)/2, b, c)
		+ partSum(node*2+1, (start+end)/2 + 1, end, b, c);
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new long[n+1];					
		for(int i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		//세그먼트 트리의 필요 노드 수 : 2n-1, 배열의 크기 : 트리 높이 h일 때 2^(h+1) (h=log2(n))
		int h = (int)Math.ceil(Math.log(n) / Math.log(2));			//트리 높이
		int size = (int)Math.pow(2, h+1);							//트리 배열의 총 크기
		tree = new long[size];
		
		init(arr, 1, 1, n);					//세그먼트 트리 초기화(1번 노드에서부터 시작, 배열 1번 인덱스에서 n번째까지 탐색)
		
		for(int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());		//런타임에러 때문에 long타입으로 변경
			
			if(a == 1) {			//수 변경
				//1번 노드부터 탐색, 배열 1번 인덱스~n번 인덱스까지, b번째 인덱스의 값을 c로 바꾸면서 부모들도 한 번에 차이값 적용
				update(1, 1, n, b, c - arr[b]);	
				arr[b] = c;					//혹시 모르니 배열도 변경
			} else {				//구간 합 구하기
				System.out.println(partSum(1, 1, n, b, (int)c));
			}
		}
	}

}
