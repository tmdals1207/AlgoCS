import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16724 {
    
    static int N, M, safeZone;
    static int[][] board, isCycle;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};


    static void piriDfs(int x, int y) {
    	
    	visited[x][y] = true;
		isCycle[x][y] += 1;

    	int nr = x + dr[board[x][y]];
    	int nc = y + dc[board[x][y]];

		if (nr < 0 || nr >= N || nc < 0 || nc >= M) return;
    	
    	if(!visited[nr][nc]) {					
    		piriDfs(nr, nc);
    	}
    	else if (isCycle[nr][nc] == 2) {
			safeZone++;
    	}

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        isCycle = new int[N][M];

        for (int i = 0; i < N; i++) {
        	String input = br.readLine();
        	for (int j = 0; j < M; j++) {
        		char c = input.charAt(j);
        		
                    switch (c) {
                        case 'U':
                            board[i][j] = 0;
                            break;
                        case 'R':
                            board[i][j] = 1;
                            break;
                        case 'D':
                            board[i][j] = 2;
                            break;
                        case 'L':
                            board[i][j] = 3;
                            break;
                        default:
                            break;
                    }
        	}
        }
		        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < M; j++) {
        		if (!visited[i][j]) {

        			piriDfs(i, j);
        		}
        	}
        }

        System.out.println(safeZone);
    }
}