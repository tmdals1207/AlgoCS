import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2042 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, K;
	static long[] array, tree;
	
	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		
		array = new long[N];
		tree = new long[getTreeSize()];
		
		for(int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		
		init(0, N - 1, 1);
		int kCount = 0;
		
		while(kCount < K) {
			String[] info = br.readLine().split(" ");
			
			int a = Integer.parseInt(info[0]);
			int b = Integer.parseInt(info[1]);
			int c = Integer.parseInt(info[2]);
			
			if(a == 1) {
				long dif = c - array[b - 1];
				update(0, N - 1, 1, b - 1, dif);
				array[b - 1] = c; 
			}else if(a == 2) {
				long sum = partSum(0, N - 1, 1, b - 1, c - 1);
				bw.write(sum + "\n");
				kCount++;
			}
		}
		
		 bw.flush();
		 bw.close();
	}
	
	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
		return (int)Math.pow(2, h) - 1;
	}
	
	static long init(int start, int end, int nodeIdx) {
		if(start == end) {
			return tree[nodeIdx] = array[start];			
		}
		
		int mid = (start + end) / 2;
		
		return tree[nodeIdx] = init(start, mid, nodeIdx * 2) + init(mid + 1, end, nodeIdx * 2 + 1);
	}
	
	static void update(int start, int end, int nodeIdx, int idx, long dif) {
		if(start <= idx && idx <= end) {
			tree[nodeIdx] += dif;
		}else {
			return;
		}
		
		if(start == end) {
			return;
		}
		
		int mid = (start + end) / 2;
		update(start, mid, nodeIdx * 2, idx, dif);
		update(mid + 1, end, nodeIdx * 2 + 1, idx, dif);
	}
	
	static long partSum(int start, int end, int nodeIdx, int l, int r) {
		if(r < start || l > end) {
			return 0;
		}
		
		if(l <= start && r >= end) {
			return tree[nodeIdx];
		}
		
		int mid = (start + end) / 2;
		
		return partSum(start, mid, nodeIdx * 2, l, r) + partSum(mid + 1, end, nodeIdx * 2 + 1, l, r);
	}
}
