package s0519;
/*
    n개의 집에 택배를 배달하기.
    택배를 배달하고, 택배를 담아 배달한 재활용 상자들은 수거하고자 함.
    i번째 집은 창고에서 i 만큼 떨어져 있음
    트럭에는 최대 cap 개 만큼 택배 상자를 실을 수 있음
    각 집에 배달할 택배 수와 수거할 상자의 개수를 알고 있을 때,
    모든 배달과 수거를 마치고 올 수 있는 최소 거리 구하기.
    *각 집의 배달 및 수거는 원하는 개수만큼 가능함
 */
public class Solution_Pro150369 {                           //택배 배달과 수거하기
    class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            Long dist = 0L;                       //총 이동거리
            int del = n-1;                        //가장 먼 배달해야할 곳
            int pick = n-1;                       //가장 먼 수거해야할 곳

            while(del >=0 || pick >= 0) {                      //배달할게 있으면 반복

                while(del >= 0 && deliveries[del] == 0){
                    del--;
                }
                while(pick >= 0 && pickups[pick] == 0){
                    pick--;
                }

                int max = Math.max(del, pick);       //배달과 수거중 더 먼 곳 찾기 (이거 인덱스라 +1 처리 잊지말기)
                if(max < 0){
                    break;                      //더 이상 배달 or 수거할게 없다는 뜻이므로 탈출
                }

                dist += 2 * (max + 1);                //먼곳까지 왕복 시작

                int truck = cap;                  //배달 시 현재 트럭 중량 (계산하기 편하게 전부 싣고 나갔다고 가정)
                for(int i = del; i >= 0; i--){
                    if(deliveries[i] == 0){
                        continue;
                    }

                    if(truck - deliveries[i] >= 0){
                        truck -= deliveries[i];
                        deliveries[i] = 0;
                    } else{
                        deliveries[i] -= truck;       //트럭에 남은 택배 다 털기
                        truck = 0;
                    }

                    if(truck == 0){                  //배달 다 했으면 끝내기
                        break;
                    }
                }

                truck = 0;                         //수거 시 현재 트럭 중량 (전부 다 비워진 상태에서 수거를 한다고 가정)
                for(int i = pick; i >= 0; i--){
                    if(pickups[i] == 0){
                        continue;
                    }

                    if(pickups[i] + truck <= cap){
                        truck += pickups[i];
                        pickups[i] = 0;
                    } else{
                        pickups[i] -= (cap - truck);       //남은 공간만큼 트럭 채우기
                        truck = cap;
                    }

                    if(truck == cap){                       //수거할 용량이 꽉차면 탈출
                        break;
                    }
                }
            }

            return dist;
        }
    }
}
