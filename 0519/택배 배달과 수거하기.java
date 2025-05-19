class Solution {
    
    static int cap; // 트럭 용량
    static int n; // 집의 개수
    static int[] deliveries; // 각 집에 배달할 택배 상자 개수
    static int[] pickups; // 각 집에서 수거할 택배 상자 개수
    static int dCursor;
    static int pCursor;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        init(cap, n, deliveries, pickups);
        dCursor = findNearValidLeft(deliveries, deliveries.length - 1);
        pCursor = findNearValidLeft(pickups, pickups.length - 1);
        
        long answer = 0;
        while (dCursor > -1 || pCursor > -1) {
            int dist = Math.max(dCursor, pCursor) + 1;
            answer += dist * 2;
            
            dCursor = process(deliveries, dCursor);
            pCursor = process(pickups, pCursor);
        }
        
        return answer;
    }
    
    void init(int cap, int n, int[] deliveries, int[] pickups) {
        this.cap = cap;
        this.n = n;
        this.deliveries = deliveries;
        this.pickups = pickups;
    }
    
    int findNearValidLeft(int[] arr, int start) {
        int cursor = start;
        while (cursor > -1 && arr[cursor] == 0) {
            cursor--;
        }
        
        return cursor;
    }
    
    int process(int[] arr, int cursor) {
        if (cursor == -1) {
            return -1;
        }
        
        for (int i=0; i<cap; i++) {
            arr[cursor]--;
            if (arr[cursor] == 0) {
                cursor = findNearValidLeft(arr, cursor);
                if (cursor == -1) {
                    return -1;
                }
            }
        }
        return cursor;
    }
}
