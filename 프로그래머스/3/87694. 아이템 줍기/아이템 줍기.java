/** 풀이 방향
    뭔가 수학적인 방법이 있는거같지만 모르겠다.
    그래서 사각형을 직접 만들거다.
    최대 4번 만들고 최대 길이가 50만큼 차이난다면, 200번 연산 이걸 4번 800번
    크기 2500인 사각형을 최대 4번 순회하면서 경계값을 제외하고 안에 있는 값에 다른 사각형이 겹처서 0->1로 변경됐을것이다.
    이 1을 0으로 변경한다.
    2500 크기 4번 -> 10^4
    1초를 넘길일은 없어서 괜찮은 풀이같다.
    
    맵을 만들면 맵의 값이 1인거를 따라서 움직일 수 있게하는 bfs를 구현한다.
    방문한 위치가 도착점인경우 반환한다.
    
    주의 : 원래 사이즈 2배로 만들어줘야하는데 겹치면 안되는 부분이 겹치기 때문이다. 그림설명.
    
**/
import java.util.*;
class Solution {
    int MAX_SIZE = 102;
    // 시계방향으로 오 - 아래 - 왼 - 위
    int[] di = {0,1,0,-1};
    int[] dj = {1,0,-1,0};
    int[][] map;
    boolean visited[][];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int answer = 0;
 
        map = new int[MAX_SIZE][MAX_SIZE];
        visited = new boolean[MAX_SIZE][MAX_SIZE];
        
        for(int[] info : rectangle){
            mkMap(info);
        }
        for(int[] info : rectangle){
            clearInside(info);
        }
        
        
        
        for(int[] m : map){
           System.out.println(Arrays.toString(m));
        }
        
        Queue<Charac> q = new LinkedList<>();
        
        Charac start = new Charac(characterX*2,characterY*2,1);
        q.offer(start);
        visited[characterX*2][characterY*2]=true;
        
       
        while(!q.isEmpty()){
            Charac cur = q.poll();
            
            if(cur.ci == itemX*2 && cur.cj == itemY*2){
                answer = cur.moveCnt/2;
                System.out.println(cur.moveCnt);
                break;
            }
            
            for(int i = 0; i<4;i++){
                int ni = cur.ci+di[i];
                int nj = cur.cj+dj[i];
                // 범위내, 맵의 테두리 1, 미방문
                if(isIn(ni,nj) && map[ni][nj]==1 && !visited[ni][nj]){
                    visited[ni][nj]=true;
                
                    
                    q.offer(new Charac(ni,nj,cur.moveCnt+1));
                }
            }
        }
        
               
        // System.out.println(c);
        
        return answer;
    }
    
    private boolean isIn(int ni, int nj){
        return 0<=ni && ni < MAX_SIZE && 0<= nj && nj < MAX_SIZE;
    }
    
    private void clearInside(int[] info){
        
        for(int i = info[0]*2+1; i<info[2]*2; i++){
            for(int j = info[1]*2+1; j<info[3]*2; j++){
                map[i][j]=0;
            }
        }
    }
    
        private void mkMap(int[] info){
        
        for(int i = info[0]*2; i<=info[2]*2; i++){
            for(int j = info[1]*2; j<=info[3]*2; j++){
                map[i][j]=1;
            }
        }
    }
    
//     private void mkMap(int[] info){
//         int si,sj,ei,ej;
//         si = info[0]*2;
//         sj = info[1]*2+1;
//         ei = info[2]*2;
//         ej = info[3]*2+1;
        
//         // 왼쪽 위부터 그려나갈거임.
//         int direction = 0;
//         int ci,cj,ni,nj; // 현위치
//         ci = si;
//         cj = sj;
//         map[ci][cj] = 1; // 현위치 1로 변경.
//         while(direction<4){
                       
//             ni = ci+di[direction];
//             nj = cj+dj[direction];
            
//             map[ni][nj]=1;
            
//             if((ni==si && nj==sj) || (ni==si && nj == ej) || (ni==ei && nj==sj) || (ni==ei && nj==ej)){ 
//                 // 경계를 바꿔야하면 이동할 방향 바꿈.
//                 direction++;
//             }
//             ci = ni;
//             cj = nj;
            
                        
//         }
        
        
//     }
    
        
        
        
    // 움직이는 캐릭정보.
    private class Charac{
        int moveCnt, ci,cj;
        
        public Charac(int ci, int cj, int moveCnt){
            this.ci = ci;
            this.cj = cj;
            this.moveCnt = moveCnt;
        }
        
        
    }
}