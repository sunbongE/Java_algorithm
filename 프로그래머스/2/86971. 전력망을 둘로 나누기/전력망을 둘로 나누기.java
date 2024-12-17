import java.util.*;
class Solution {
    ArrayList<Integer>[] adjList;
    int N;
    boolean[] v;
    public int solution(int n, int[][] wires) {
        int answer = 98760;
        this.N = n ;
        
        
        adjList = new ArrayList[n];
        
        for(int i=0; i<n; i++){
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires){
            int a = wire[0]-1;
            int b = wire[1]-1;
            adjList[a].add(b);
            adjList[b].add(a);
        }
        
        for(int[] wire : wires){
            v = new boolean[n];
            int a = wire[0]-1;
            int b = wire[1]-1;
            for(int node=0;node<n;node++){
                if(v[node]) continue;
                v[node] = true;
                int cnt =  getConnectCnt(node, a,  b);
                int cnt2 = n-cnt;
                answer = Math.min(answer, Math.abs(cnt-cnt2));
                break;
            }
        }
        
        // return adjList;
        return answer;
    }
    
    public int getConnectCnt(int node, int a, int b){
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        int cnt=0;
        while(!q.isEmpty()){
            int cur = q.poll();
            cnt++;
            for(Integer next : adjList[cur]){
                if((cur==a && next == b)||(cur==b && next == a)) continue;
                if(v[next]) continue;
                v[next] = true;
                q.offer(next);
            }
            
        }
        return cnt;
        
    }
}