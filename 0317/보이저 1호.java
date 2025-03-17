package s0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 항성계를 N * M개의 직사각형으로 나누어져 있는 N행 M열의 직사각형 그리드라고 가정, 
 * 각 칸은 행성, 블랙홀을 포함할 수 있으며, 비어있을 수도
 * 탐사선이 4방 중 하나를 골라 시그널을 보냄 -> 시그널은 블랙홀을 만나거나 항성계를 벗어날때까지 전파, 한 칸 이동하는데 1초
 * 탐사선이 시그널을 보낼 때, 항성계 내부에 있는 시간이 최대가 되는지 구하기? 
 * 시그널을 보내는 방향과 가장 긴 시간 출력, 무한히 전파된다면 Voyager 출력 
 */
public class Main_BJ3987 {						//보이저 1호
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};		//URDL순으로 벡터 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char arr[][] = new char[n][m];
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int pr = Integer.parseInt(st.nextToken())-1;
		int pc = Integer.parseInt(st.nextToken())-1;		//pr, pc가 1, 1부터 시작해서 0,0부터 시작하도록 조정
		
		int[] answer = new int[4]; // U R D L 순으로 정답을 담을거
		int dir = 0;				//현재 진행 방향
		
		for(int d = 0; d < 4; d++) { //0-U, 1-R, 2-D, 3-L
			int count = 0;
			int x = pr;
			int y = pc;	
			
			//현재 출발 방향 저장
			if(d == 0){
				dir = 0;
			}else if(d == 1){
				dir = 1;
			}else if(d == 2){
				dir = 2;
			}else if(d == 3){
				dir = 3;
			}
			
			while(true) {
				count++;
				
				//시그널이 존재하는 시간이 n*m(항성계를 전부 탐색하고도 넘을 시간)을 넘는다면 무한히 전파된다고 판단
				if(count > 25000000) {		//500*500으로도 택없길래 더 크게 설정
					answer[d] = 1000000000;
					break;
				}
				
				x = x + dx[dir];
				y = y + dy[dir];
				
				if(x < 0 || x >= n || y < 0 || y >= m || arr[x][y] == 'C') {
					answer[d] = count;
					break;								//블랙홀이나 맵 밖은 즉시 종료
				}
				
				//진행방향을 바꿀 요소가 등장하면 진행방향 변경
				if(arr[x][y] == '/') {
					if(dir == 0) {
						dir = 1;
					}else if(dir == 1) {
						dir = 0;
					}else if(dir == 2) {
						dir = 3;
					}else if(dir == 3) {
						dir = 2;
					}
				}else if(arr[x][y] == '\\') {
					if(dir == 0) {
						dir = 3;
					}else if(dir == 1) {
						dir = 2;
					}else if(dir == 2) {
						dir = 1;
					}else if(dir == 3) {
						dir = 0;
					}
				}
				
			}
		}
		
		int max = 0;
		int idx = 0;
		for(int d = 0; d < 4; d++) {
			if(answer[d] > max) {
				max = answer[d];
				idx = d;
			}
		}
		
		if(idx == 0) {
			System.out.println('U');
		}else if(idx == 1) {
			System.out.println('R');
		}else if(idx == 2) {
			System.out.println('D');
		}else if(idx == 3) {
			System.out.println('L');
		}
		
		if(max == 1000000000) {
			System.out.println("Voyager");
		}else{
			System.out.println(max);
		}
	}

}
