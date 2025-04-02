import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Baekjoon1504 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, E, case1 = 0, case2 = 0;
	static boolean case1Exist = true, case2Exist = true;
	static LinkedList<Node>[] graph;
	static int[] dist;
	
	static class Node implements Comparable<Node> {
		int nodeNum, weight;
		public Node(int nodeNum, int weight) {
			this.nodeNum = nodeNum;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node node) {
			return weight - node.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		E = Integer.parseInt(input[1]);
		graph = new LinkedList[N + 1];
		dist = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<Node>();
			dist[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 0; i < E; i++) {
			String[] info = br.readLine().split(" ");
			int a = Integer.parseInt(info[0]);
			int b = Integer.parseInt(info[1]);
			int c = Integer.parseInt(info[2]);
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
		
		String[] startEnd = br.readLine().split(" ");
		int v1 = Integer.parseInt(startEnd[0]);
		int v2 = Integer.parseInt(startEnd[1]);
		
		dijkstra(1);
		
		if(dist[v1] == Integer.MAX_VALUE) {
			case1Exist = false;
		}else {
			case1 += dist[v1];
		}
		
		if(dist[v2] == Integer.MAX_VALUE) {
			case2Exist = false;
		}else {
			case2 += dist[v2];
		}
		
		if(case1Exist) {
			for(int i = 1; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			
			dijkstra(v1);
			
			if(dist[v2] == Integer.MAX_VALUE) {
				case1Exist = false;
			}else {
				case1 += dist[v2];
			}
		}
		
		if(case2Exist) {
			for(int i = 1; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			
			dijkstra(v2);
			
			if(dist[v1] == Integer.MAX_VALUE) {
				case2Exist = false;
			}else {
				case2 += dist[v1];
			}
		}
		
		if(case1Exist) {
			for(int i = 1; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			
			dijkstra(v2);
			
			if(dist[N] == Integer.MAX_VALUE) {
				case1Exist = false;
			}else {
				case1 += dist[N];
			}
		}
		
		if(case2Exist) {
			for(int i = 1; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			
			dijkstra(v1);
			
			if(dist[N] == Integer.MAX_VALUE) {
				case2Exist = false;
			}else {
				case2 += dist[N];
			}
		}
		
		if(!case1Exist && !case2Exist) {
			System.out.println(-1);
		}else {
			System.out.println(Math.min(case1, case2));
		}
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node checkNode = pq.poll();
			int currentNodeNum = checkNode.nodeNum;
			int currentNodeWeight = checkNode.weight;
			
			if(dist[currentNodeNum] < currentNodeWeight) {
				continue;
			}
			
			for(Node nextNode: graph[currentNodeNum]) {
				int nextNodeNum = nextNode.nodeNum;
				int nextNodeWeight = nextNode.weight;
				
				if(dist[nextNodeNum] > currentNodeWeight + nextNodeWeight) {
					pq.add(new Node(nextNodeNum, currentNodeWeight + nextNodeWeight));
					dist[nextNodeNum] = currentNodeWeight + nextNodeWeight;
				}
			}
		}
	}
}
