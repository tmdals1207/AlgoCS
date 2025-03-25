package s0331;
//https://school.programmers.co.kr/learn/courses/30/lessons/12979
public class Solution_Pro12979 {					//기지국 설치
	
	class Solution {
	    public int solution(int n, int[] stations, int w) {
	        int idx = 1;
	        int count = 0;
	        
	        int i = 0;
	        while(idx <= n){
	            if(i < stations.length && idx >= stations[i] - w){      //이미 설치된 기지국 범위 안이면
	                idx = stations[i] + w + 1;   //왼쪽에서부터 출발해서 만난 기지국의 범위 밖으로
	                i++;
	            } else {                         //이미 설치된 기지국 범위 밖이면
	                idx += 2*w + 1;             //기지국 설치
	                count++;
	            }
	        }

	        return count;
	    }
	}
}
