/** 
최대로 많은 기름을 뽑을 수 있는 기름 양을 추출하는 솔루션 

m개 만큼 int[] ans 생성 <-이게 답을 기록한다.

bfs으로 land를 순회하면서 기름양을 기록 후 리턴한다.
이 과정에서 방문한 열을 방문체크한다.

colCheck[j]가 true라면 ans[j] += 기름양

마지막으로 ans를 순회 후, 최대값을 리턴한다.

**/
import java.util.*;
class Solution {
    static int maximumOil = 0 ;
    static boolean[][] visited;
    static boolean[] colCheck;
    static int[] colOilCnt;
    static int n,m;
    static int[][] staticLand;
    
    public int solution(int[][] land) {  
        n = land.length;
        m = land[0].length;
        staticLand = new int[n][m];
        colOilCnt = new int[m];
        visited  = new boolean[n][m];
        
        // land복사
        for(int i=0; i<n;i++){
           for(int j=0;j<m;j++){
               staticLand[i][j] = land[i][j];
           }
       }
        
        
        
        
       for(int i=0; i<n;i++){
           for(int j=0;j<m;j++){
               if(staticLand[i][j] == 1 && !visited[i][j]){
                   visited[i][j]=true;
                   colCheck = new boolean[m];
                   int oilCnt = bfs(i,j);
                   
                   // 방문된 열에 오일양 더해주기.
                   for(int k=0;k<m;k++){
                       if(colCheck[k]){
                           colOilCnt[k] += oilCnt;
                       }
                   }
               }
           }
       }
        
        for(Integer tmp : colOilCnt){
            maximumOil = Math.max(tmp,maximumOil);
        }
        
        return maximumOil;
    }
 
    public int bfs(int i,int j) {
        int[] di = {-1,1,0,0};
        int[] dj = {0,0,-1,1};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i,j});
        int oilCnt=0;
        int[] cur;
        int ni,nj,ci,cj;
        while(!q.isEmpty()){
            cur = q.poll();
            ci = cur[0];
            cj = cur[1];
            colCheck[cj] = true;
            oilCnt++;
            for(int k=0; k<4; k++){
                ni = ci+di[k];
                nj = cj+dj[k];
                if(isIn(ni,nj) && staticLand[ni][nj] == 1 && !visited[ni][nj]){
                    visited[ni][nj]=true;
                    q.offer(new int[]{ni,nj});
                }
            }
            
        }
        
        return oilCnt;
    }
 
    public boolean isIn(int ni,int nj) {
        return 0 <= ni && ni < n && 0 <= nj && nj < m;
    }
}