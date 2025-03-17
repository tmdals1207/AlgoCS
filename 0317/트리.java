import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리 {
	
	static int[] parent;
	static boolean[] isCycle; // 해당 루트 노드가 사이클을 포함하는지

	
	public static int find(int x) {
	    if (parent[x] == x) return x; // 자기 자신이 부모면 루트 노드
        return parent[x] = find(parent[x]); // 경로 압축 적용
	}
	
	public static void union(int a, int b) {
	    int rootA = find(a);
	    int rootB = find(b);

	   // 이미 같은 루트 -> 사이클 발생
       if (rootA == rootB) {
        isCycle[rootA] = true;
        } 
        else {
        // 작은 루트 번호를 부모로 설정
	    	if(rootA < rootB) {
	    		parent[rootB] = rootA;
	    		if (isCycle[rootB]) isCycle[rootA] = true; // 사이클 정보 전달
	    	}
	    	else {
	    		parent[rootA] = rootB;
	    		if (isCycle[rootA]) isCycle[rootB] = true;
	    	}
	    }
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder  sb = new StringBuilder ();
		int caseNum = 1;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			if (n==0 && m==0) break;
			
			parent = new int[n+1];
			isCycle = new boolean[n+1];
			
			for (int i = 1; i <= n; i++) {
				parent[i] = i; // 각 노드는 자기 자신을 부모로 설정
			}

			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				union(u, v);
			}
			
			int treeCnt = 0;
			for (int k = 1; k <=n; k++) {
				if(parent[k] == k && !isCycle[k]) { // 자기 자신이 루트이며 사이클이 없는 경우
					treeCnt++;
				}
			}
			
			if (treeCnt == 0) {
                sb.append("Case ").append(caseNum).append(": No trees.\n");
            } else if (treeCnt == 1) {
                sb.append("Case ").append(caseNum).append(": There is one tree.\n");
            } else {
                sb.append("Case ").append(caseNum).append(": A forest of ").append(treeCnt).append(" trees.\n");
            }
			
			caseNum++;
		}
		System.out.println(sb);
	}
}