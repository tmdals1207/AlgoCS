package s0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    완전 이진 트리 형태 탐색할거임. 깊이는 k, 노드 개수는 2^k-1개
    처음 위치는 루트에서 시작
    왼쪽-가운데-오른쪽 순으로 탐색(중위 순위를 하겠다)
    단, 가운데 노드에 들어갈때는 종이에 번호를 적을 거임(중위 순위 탐색 순으로 적어둘 예정)
    i번째 줄에 트리 레벨이 i인 빌딩의 번호를 출력, 왼쪽에서 오른쪽으로 출력하기
    완전 이진 트리 => 부모 노드 번호 x2 = 왼쪽 자식, 부모노드 x2 + 1 = 오른쪽 자식
 */
public class Main_BJ9934 {                  //완전 이진 트리
    static StringTokenizer st;

    static class Node{
        int no;
        Node left;
        Node right;

        public Node(){
            no = 0;
            left = null;
            right = null;
        }
    }

    public static void inorder(Node node){
        if(node.left != null) {
            inorder(node.left);
        }
        node.no = Integer.parseInt(st.nextToken());
        if(node.right != null) {
            inorder(node.right);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());        //트리의 레벨

        st = new StringTokenizer(br.readLine());        //중위 순회 한 노드 순서

        int n = (int)Math.pow(2, k)-1;                  //노드 개수
        Node node[] = new Node[n+1];
        for(int i = 1; i <= n; i++){
            node[i] = new Node();
        }

        int m = (int)Math.pow(2, k-1)-1;
        for(int i = 1; i <= m; i++){
            node[i].left = node[i*2];
            node[i].right = node[i*2 + 1];
        }

        inorder(node[1]);

        int t = 1;
        for(int i = 1; i <= n; i++){
            System.out.print(node[i].no + " ");
            if(i == (int)Math.pow(2, t)-1) {
                System.out.println();
                t++;
            }
        }
    }
}
