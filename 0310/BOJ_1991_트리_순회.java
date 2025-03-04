import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon1991 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static LinkedList<Integer>[] tree;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		tree = new LinkedList[N];
		
		for(int i = 0; i < N; i++) {
			tree[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = st.nextToken().charAt(0) - 65;
			int leftChild = st.nextToken().charAt(0) - 65;
			int rightChild = st.nextToken().charAt(0) - 65;
			tree[parent].add(leftChild);
			tree[parent].add(rightChild);
		}
		
		preOrder(0);
		bw.write("\n");
		inOrder(0);
		bw.write("\n");
		postOrder(0);
		bw.write("\n");
		
		bw.flush();
		bw.close();
	}
	
	static void preOrder(int parent) throws IOException {
		bw.write((char)(parent + 65));
		
		for(int nextChild: tree[parent]) {
			if(nextChild != '.' - 65) {
				preOrder(nextChild);
			}
		}
		
	}
	
	static void inOrder(int parent) throws IOException {
		Iterator<Integer> iter = tree[parent].iterator();
		
		int leftChild = iter.next();
		if(leftChild != '.' - 65) {
			inOrder(leftChild);
		}
		
		bw.write((char)(parent + 65));
		
		int rightChild = iter.next();
		if(rightChild != '.' - 65) {
			inOrder(rightChild);
		}
	}
	
	static void postOrder(int parent) throws IOException {
		for(int nextChild: tree[parent]) {
			if(nextChild != '.' - 65) {
				postOrder(nextChild);
			}
 		}
		
		bw.write((char)(parent + 65));
	}
}
