import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 트리순회 {
	
	static int N;
	static Node[] nodes;
	static BufferedWriter bw;
	
	
    // 전위 순회 : Root → Left → Right
	static void preorder(Node node) throws IOException {
		bw.write(node.data); // 현재 노드 출력
        if (node.left != null) preorder(node.left); // 왼쪽 자식 방문
        if (node.right != null) preorder(node.right); // 오른쪽 자식 방문
	}
	
    // 중위 순회 : Left → Root → Right
	static void inorder(Node node) throws IOException {
		if (node.left != null) inorder(node.left); // 왼쪽 자식 방문
        bw.write(node.data); // 현재 노드 출력
        if (node.right != null) inorder(node.right); // 오른쪽 자식 방문	
	}
	
    // 후위 순회 : Left → Right → Root
	static void postorder(Node node) throws IOException {
		if (node.left != null) postorder(node.left); // 왼쪽 자식 방문
        if (node.right != null) postorder(node.right); // 오른쪽 자식 방문
        bw.write(node.data); // 현재 노드 출력
	}	
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		nodes = new Node[N];
		
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node((char) ('A'+i));
		}
		
		for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            char node = st.nextToken().charAt(0); // 부모 노드
            char left = st.nextToken().charAt(0); // 왼쪽 자식 노드
            char right = st.nextToken().charAt(0); // 오른쪽 자식 노드

            // 현재 노드 찾기
            Node cur = nodes[node - 'A'];

            // '.'이 아니면 해당 노드를 왼쪽 자식으로 연결
            cur.left = (left != '.') ? nodes[left - 'A'] : null;
            // '.'이 아니면 해당 노드를 오른쪽 자식으로 연결
            cur.right = (right != '.') ? nodes[right - 'A'] : null;
        }
		
		preorder(nodes[0]);
		bw.write("\n");
		bw.flush();
		inorder(nodes[0]);
		bw.write("\n");
		bw.flush();
		postorder(nodes[0]);
		bw.close();
	}

}


class Node {
	char data;
	Node left, right;
	
	public Node (char data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}