import java.util.*;
class Solution {
    final int INF = 500001;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        // a에서 각 마을의 소요시간.
        int[] costs = new int[N+1];
        Arrays.fill(costs,INF);
        // 출발지 0으로 세팅
        costs[1]=0;
        
        // 시작할 값 설정
        PriorityQueue<Group> pq = new PriorityQueue<>();
        pq.offer(new Group(1,0)); 
        
        // 인접리스트 생성.
        ArrayList<ArrayList<Group>> adjList = new ArrayList<>();
        for(int i = 0; i<N+1;i++){
            adjList.add(new ArrayList<>());
        }
        
        
        for(int[] info : road){
            
            // 양방향 연결.
            adjList.get(info[0]).add(new Group(info[1],info[2]));
            adjList.get(info[1]).add(new Group(info[0],info[2]));
        }
        
        while(!pq.isEmpty()){
            Group nowGroup = pq.poll();
            int now = nowGroup.to;
            int cost = nowGroup.cost;
            
            if(cost > costs[now]) continue;
            
            for(Group nextGroup : adjList.get(now)){
                int next = nextGroup.to;
                int nextCost = nextGroup.cost + cost;
                if(nextCost < costs[next]){
                    costs[next] = nextCost;
                    pq.offer(new Group(next, nextCost));
                }
            }
        }
        
        for(Integer c : costs){
            if(c<=K) answer++;
        }
        
        
        return answer;
    }
    
    class Group implements Comparable<Group> {
        int to,cost;
        Group(int to, int cost){
            this.to=to;
            this.cost=cost;
        }
        
        @Override
        public int compareTo(Group other){
            return this.cost - other.cost;
        }
    }
}