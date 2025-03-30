import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Baekjoon1753 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int V, E, K;
	static LinkedList<Node>[] graph;
	static int[] dist;
	
	static class Node implements Comparable<Node>{
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
		V = Integer.parseInt(input[0]);
		E = Integer.parseInt(input[1]);
		K = Integer.parseInt(br.readLine());
		
		graph = new LinkedList[V + 1];
		dist = new int[V + 1];
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new LinkedList<Node>();
			dist[i] = Integer.MAX_VALUE;
		}
		
		dist[K] = 0;
		
		for(int i = 0; i < E; i++) {
			String[] linkInfo = br.readLine().split(" ");
			int u = Integer.parseInt(linkInfo[0]);
			int v = Integer.parseInt(linkInfo[1]);
			int w = Integer.parseInt(linkInfo[2]);
			graph[u].add(new Node(v, w));
		}
		
		dijkstra(K);
		
		for(int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				bw.write("INF\n");
			}else {
				bw.write(dist[i] + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
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
				
				if(dist[nextNodeNum] > dist[currentNodeNum] + nextNodeWeight) {
					dist[nextNodeNum] = dist[currentNodeNum] + nextNodeWeight;
					pq.add(new Node(nextNodeNum, dist[nextNodeNum]));
				}
			}
		}
	}
}
