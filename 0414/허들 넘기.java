import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
/*
 * 출발지에서 목적지까지의 각 경로 상에서 높이가 가장 높은 허들을 구하고, 
 * 여러 개의 경로에서 가장 높은 허들 중에 가장 작은 허들을 찾아햐 하는 문제.
 */
public class Baekjoon23286 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, T;
	static LinkedList<Node>[] graph;
	static int[][] height;
	
	static class Node implements Comparable<Node>{
		int nodeNum, height;
		public Node(int nodeNum, int height) {
			this.nodeNum = nodeNum;
			this.height = height;
		}
		
		@Override
		public int compareTo(Node node) {
			return height - node.height;
		}
	}
	
	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		T = Integer.parseInt(input[2]);
		graph = new LinkedList[N + 1];
		height = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<Node>();
			Arrays.fill(height[i], Integer.MAX_VALUE);
		}
		
		for(int i = 0; i < M; i++) {
			String[] info = br.readLine().split(" ");
			int u = Integer.parseInt(info[0]);
			int v = Integer.parseInt(info[1]);
			int h = Integer.parseInt(info[2]);
			graph[u].add(new Node(v, h));
		}
		
		for(int i = 1; i <= N; i++) {			
			dijkstra(i);
		}
		
		for(int i = 0; i < T; i++) {
			String[] startEnd = br.readLine().split(" ");
			int s = Integer.parseInt(startEnd[0]);
			int e = Integer.parseInt(startEnd[1]);
			
			if(height[s][e] == Integer.MAX_VALUE) {
				bw.write("-1\n");
			}else {
				bw.write(height[s][e] + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		height[start][start] = 0;
		
		while(!pq.isEmpty()) {
			Node checkNode = pq.poll();
			int checkNodeNum = checkNode.nodeNum;
			int checkNodeHeight = checkNode.height;
			
			// 이미 다른 경로를 통해 가장 높은 허들 중에 가장 낮은 허들이 계산되어 있는 경우
			if(height[start][checkNodeNum] < checkNodeHeight) {
				continue;
			}
			
			for(Node nextNode: graph[checkNodeNum]) {
				int nextNodeNum = nextNode.nodeNum;
				int nextNodeHeight = nextNode.height;
				// 한 경로 상에서 가장 큰 허들 크기를 뽑는 과정
				int maxHeight = Math.max(checkNodeHeight, nextNodeHeight);
				// 여러 경로 상에서 가장 큰 허들 중에 가장 작은 허들을 뽑는 과정
				if(height[start][nextNodeNum] > maxHeight) {
					pq.add(new Node(nextNodeNum, maxHeight));
					height[start][nextNodeNum] = maxHeight;
				}
			}
		}
	}
}
