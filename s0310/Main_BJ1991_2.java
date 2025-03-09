package s0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이진 트리를 입력 받아 전위 중위 후위 순회 돌기
 */
public class Main_BJ1991_2 {						//트리 순회 (다시 풀기)
	static class Node{
		char root;
		Node left;
		Node right;
		
		public Node(char root){
			this.root = root;
			this.left = null;
			this.right = null;
		}
	}
	
	static void preorder(Node node) {
		System.out.print(node.root);
		if(node.left != null) {
			preorder(node.left);
		}
		if(node.right != null) {
			preorder(node.right);
		}
	}
	
	static void inorder(Node node) {
		if(node.left != null) {
			inorder(node.left);
		}
		System.out.print(node.root);
		if(node.right != null) {
			inorder(node.right);
		}
	}
	
	static void postorder(Node node) {
		if(node.left != null) {
			postorder(node.left);
		}
		if(node.right != null) {
			postorder(node.right);
		}
		System.out.print(node.root);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Node node[] = new Node[n];
		for(int i = 0; i < n ; i++) {
			node[i]= new Node((char)('A'+i));
		}
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			int c = root-'A';
			
			if(left == '.') {
				node[c].left = null;
			}else {
				node[c].left = node[left-'A'];
			}
			
			if(right == '.') {
				node[c].right = null;
			}else {
				node[c].right = node[right-'A'];
			}
		}
		
		preorder(node[0]);
		System.out.println();
		inorder(node[0]);
		System.out.println();
		postorder(node[0]);
	}

}
