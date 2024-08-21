import java.util.*;
class Solution {
    int[][] computers;
    boolean[] v;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        this.computers = computers;
        this.v = new boolean[computers.length];
        
        for(int i=0; i<computers.length;i++){
            for(int j=0;j < computers[0].length;j++){
                if(computers[i][j]==1 && !v[j]){
                    bfs(i);
                    answer++;
                }
            }
        }
        return answer;
    }
    
    private void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        
        while(!q.isEmpty()){
            int node = q.poll();
            v[node] = true;
            
            for(int adjNode=0;adjNode<computers.length;adjNode++){
                if(computers[node][adjNode] == 1 && !v[adjNode]){
                    q.offer(adjNode);
                }
            }
            
        }
    }
}