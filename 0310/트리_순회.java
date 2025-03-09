import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 트리 순회
public class Main {

    static class Node {
        int left;
        int right;
    }

    static int N;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        // 'A' = 65

        for (int i=0; i<N; i++) { // 입력값을 바탕으로 트리 구성
            Node node = new Node(); // 노드 생성
            String[] split = br.readLine().split(" ");
            int idx = split[0].charAt(0) - 65; // 알파벳의 아스키코드 값으로 인덱스 구성
            char left = split[1].charAt(0); // 왼쪽 자식 설정
            if (left == '.') {
                node.left = -1;
            } else {
                node.left = (int) left - 65;
            }

            char right = split[2].charAt(0); // 오른쪽 자식 설정
            if (right == '.') {
                node.right = -1;
            } else {
                node.right = (int) right - 65;
            }
            nodes[idx] = node; // 노드 추가
        }

        // 전위 순회 - root, left, right 순
        pre(0);
        System.out.println();
        // 중위 순회 - left, root, right 순
        mid(0);
        System.out.println();
        // 후위 순회 - left, right, root 순
        post(0);
    }

    private static void post(int idx) {
        if (idx == -1) {
            return;
        }
        post(nodes[idx].left);
        post(nodes[idx].right);
        System.out.print((char) (idx + 65));
    }

    private static void mid(int idx) {
        if (idx == -1) {
            return;
        }
        mid(nodes[idx].left);
        System.out.print((char) (idx + 65));
        mid(nodes[idx].right);
    }

    private static void pre(int idx) {
        if (idx == -1) {
            return;
        }
        System.out.print((char) (idx + 65));
        pre(nodes[idx].left);
        pre(nodes[idx].right);
    }
}
