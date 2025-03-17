import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Baekjoon4803 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static LinkedList<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		int caseNum = 0;
		
		while(true) {
			caseNum++;
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			
			if(n == 0 && m == 0) {
				break;
			}
			
			graph = new LinkedList[n + 1];
			visited = new boolean[n + 1];
			
			for(int i = 1; i <= n; i++) {
				graph[i] = new LinkedList<Integer>();
			}
			
			for(int i = 0; i < m; i++) {
				String[] inputNode = br.readLine().split(" ");
				int n1 = Integer.parseInt(inputNode[0]);
				int n2 = Integer.parseInt(inputNode[1]);
				graph[n1].add(n2);
				graph[n2].add(n1);
			}
			
			int treeCount = 0;
			
			for(int i = 1; i <= n; i++) {
				if(!visited[i]) {
					if(dfs(i, 0)) {
						treeCount++;
					}
				}
			}
			
			if(treeCount == 0) {
				bw.write("Case " + caseNum + ": No trees.\n");
			}else if(treeCount == 1) {
				bw.write("Case " + caseNum + ": There is one tree.\n");
			}else if(treeCount > 1) {
				bw.write("Case " + caseNum + ": A forest of " + treeCount + " trees.\n");
			}	
		}
		
		bw.flush();
		bw.close();
	}
	
	static boolean dfs(int start, int beforeNode) {
		visited[start] = true;
		boolean isTree = true;
		
		for(int nextNode: graph[start]) {
			// 다음 노드가 이전 노드가 아닌데 이미 방문했던 노드이면, 사이클이 존재하는 것임
			if(visited[nextNode] && nextNode != beforeNode) {
				return false;
			}
			
			// 다음 노드가 방문한 적 없다면 방문함
			if(!visited[nextNode]) {
				if(!dfs(nextNode, start)) {
					isTree = false;
				}
			}
		}
		
		return isTree;
	}
}
