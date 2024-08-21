/**
프림알고리즘이라고 생각했다가 시간날린 문제
프림은 한 정점을 시작으로 비용이 싼 간선으로 연결해 나가는 방식인데
이런경우는 A,B가 합승 후 각각 가는 방법이 떠오르질 않았다.
암튼 플로이드워셜 사용해야하는데, 처음부터 생각도 안했던 이유는 N^3 시간복잡도기 때문에 걍 생각 안했다

N이 최대 200이면 N^3해도 6*10^6 < 10^8 암튼 1초안에 풀리니까 이거 써도..되네?

풀이
비용을 기록할 costs[][]
전부 INF값으로 채우기~ BUT i==j면 0으로 자기자신으로 가는 비용은 없다는 뜻.
k 경유지 i 출발지 j 도착지
플로이드 사용해서 각 정점에서 다른 정점으로가는 모든 최소비용을 구한다.

마지막에 경유지를 어디로할지 모르니까 정점을 모두 확인하여 최소값을 찾아낸다.
costs[s][k]+costs[k][a]+costs[k][b] => 합승 + 합승 후 A로 가는 비용 + 합승 후 B로 가는 비용.
**/
import java.util.*;
class Solution {
    final int INF = 200*100_000; // 최대 199번 왕복
    int[][] costs;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        costs = new int[n][n];
        // 비용 초기화
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) costs[i][j]=0;
                else costs[i][j]=INF;
            }
        }
        
        // 입력받은 비용 적용.
        for(int[] vertex : fares){
            for(int i=0;i<vertex.length;i++){
                int from = vertex[0]-1;
                int to = vertex[1]-1;
                int cost = vertex[2];
                // 양방향.
                costs[from][to] = cost;
                costs[to][from] = cost;
            }
        }
        
        floyd(n);
        for(int k=0;k<n;k++){
            answer = Math.min(answer,costs[s-1][k]+costs[k][a-1]+costs[k][b-1]);
        }
        
        
        return answer;
    }
    
    private void floyd(int n){
        
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0; j<n;j++){
                    if(costs[i][j]<costs[i][k]+costs[k][j]) continue; // 바로 가는게 더 싸면 무시
                    
                    costs[i][j]=costs[i][k]+costs[k][j];
                }
            }
        }
    }
}