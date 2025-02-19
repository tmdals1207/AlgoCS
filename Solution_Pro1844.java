package s0224;

import java.util.*;
//https://school.programmers.co.kr/learn/courses/30/lessons/1844
class Solution_Pro1844 {
    
	 public int solution(int[][] maps) {
	        int answer = -1;
	        int[] dx = {1, 0, -1, 0};
	        int[] dy = {0, 1, 0, -1};
	        
	        Queue<int[]> q = new ArrayDeque<>();
	        q.offer(new int[]{0, 0, 1});			//좌푯값 + 칸 수 세기
	        
	        while(!q.isEmpty()){
	            int ij[] = q.poll();
	            int i = ij[0];
	            int j = ij[1];
	            int count = ij[2];
	            
	            if(i == maps.length-1 && j == maps[0].length-1){
	                answer = count;
	                break;
	            }
	            
	            for(int d = 0; d < 4; d++){
	                int ni = i+dx[d];
	                int nj = j+dy[d];
	                if(0 <= ni && ni < maps.length && 0 <= nj && nj < maps[0].length && maps[ni][nj] != 0){
	                    q.offer(new int[]{ni, nj, count+1});
	                    maps[ni][nj] = 0;
	                }
	            } 
	        }
	        
	        return answer;
	    }
}
