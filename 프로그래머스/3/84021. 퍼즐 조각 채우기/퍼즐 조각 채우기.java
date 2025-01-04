import java.util.*;
/**
풀이
1. 게임보드에서 bfs를 이용해 빈칸(0)의 좌표를 기록하여 리스트에 담는다.
2. table 에서 bfs를 이용해 1의 좌표를 기록해 리스트에 담음
3. 게임보드 빈칸 리스트(gl)에서 하나씩 각 좌표모음을 대상으로 테이블 좌표 모음(tl) 값과 비교
4. 비교값이 다르면 회전시켜서 비교 이과정을 최대 3번 반복.
5. 값이 같다면 ans += tl.size

위치가 담긴 배열(int[2])의 기준값을 동일하게 맞춰야한다.
1. gl에서 퍼즐의 좌표들 x,y의 각 최소값을 찾고 모든 x,y에 각 최소값으로 빼주면 기준점 맞출수있다.(예를 들어 1,1 -> 0,0으로 시작점 맞춤)
2. tl에서 비교할 값도 회전을 시키고 기준점 동일하게 맞추고 비교함.

비교하는 연산은 set에 넣고 할거같음. 딱히 좋은 방법이 안떠오름

사용한 퍼즐 사용처리

**/
class Solution {
    int[][] gb, tb;
    int n,m;
    ArrayList<ArrayList<int[]>> gl, tl;
    public int solution(int[][] game_board, int[][] table) {
        int ans= 0;
        this.gb = game_board;
        this.tb = table;
        
        n = gb.length;
        m = gb[0].length;
        
        gl= new ArrayList<>(); // 게임빈칸리스트
        tl= new ArrayList<>(); // 테이블
        
        boolean[][] v = new boolean[n][m];
        
        // 게임보드의 빈칸 찾기.
        for(int i=0; i<n;i++){
            for(int j=0; j<m;j++){
                // 빈칸이고 미방문
                if(gb[i][j] == 0 && !v[i][j]) {
                    ArrayList<int[]> result = bfs(0,i,j,v);
                    gl.add(result);
                }
            }
        }
        
        v = new boolean[n][m];
        // 테이블에서 찾기
        for(int i=0; i<n;i++){
            for(int j=0; j<m;j++){
                // 빈칸이고 미방문
                if(tb[i][j] == 1 && !v[i][j]) {
                    ArrayList<int[]> result = bfs(1,i,j,v);
                    tl.add(result);
                }
            }
        }
        
        
        
        // 이미 사용된 퍼즐조각은 무시해야함
        boolean[] used = new boolean[tl.size()];
        
        // gl에서 하나씩 뽑아서 딱 맞는 배열인지 확인
        for(int i=0; i<gl.size();i++){
            ArrayList<int[]> GList = gl.get(i); 
            // 비교 대상이 되는거 기준점 맞추기
            init(GList);
            
            point1:
            for(int j=0; j<tl.size(); j++){
                ArrayList<int[]> TList = tl.get(j);
                
                if(TList.size() != GList.size() || used[j] ) continue; // 같은 크기만 확인, 미사용 퍼즐만
                
                for(int r=0; r<4;r++){
                    rotate(TList);
                    init(TList);
                    
                    boolean isEquals = areListsE(GList, TList);
                    
                    // 같으면 사용 체크, 크기만큼 답에 추가.
                    if(isEquals){
                        ans+= GList.size();
                        used[j]=true;
                        break point1;
                    }
                    
                }
                
            }
        }
        
        return ans;
    }
    
    public boolean areListsE(ArrayList<int[]> GList, ArrayList<int[]> TList){
        int size = GList.size();
        
        int cnt = size;
        
        HashSet<int[]> set = new HashSet<>();
        for(int i=0; i<size;i++){
            set.add(GList.get(i));
        }
        
        for(int[] arr : set){
            for(int i=0; i<size;i++){
                if(Arrays.equals(arr, TList.get(i))) cnt--;
            }    
        }
        
        // cnt=0 : 동일한요소가 모두 있다는거
        return cnt==0?true:false;
    }
    
    // 90도 시계방향 회전 (r,c) => (c, size-r-1)
    public void rotate(ArrayList<int[]> TList){
        // 좌표에서 최대값을 크기로 설정한다.
        int size = 0;
        for(int i=0; i<TList.size();i++){
            size = Math.max(size, Math.max(TList.get(i)[0], TList.get(i)[1]));
        }
        
        for(int i=0; i<TList.size();i++){
            int tmpR = TList.get(i)[0];
            TList.get(i)[0] = TList.get(i)[1];
            TList.get(i)[1] = size-tmpR; // 사이즈: 인덱스 최대로해서 -1 생략
        }
        
    }
    
    public void init(ArrayList<int[]> points){
        
        // 모든 배열에서 x,y 최소값을 찾는다.
        int minX = 10000, minY=10000;
        
        for(int i=0; i<points.size(); i++){
            int[] curArr = points.get(i);
            minX = Math.min(minX, curArr[0]);
            minY = Math.min(minY, curArr[1]);
        }

        for(int i=0; i<points.size(); i++){
            points.get(i)[0] -= minX;
            points.get(i)[1] -= minY;
        }
    }
    
    public ArrayList<int[]> bfs(int target, int i, int j, boolean[][] v){
        int[] di = {-1,1,0,0};
        int[] dj = {0,0,-1,1};
        
        int[][] map = target == 1? tb : gb;
        ArrayList<int[]>  result = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        v[i][j] = true;
        
        q.offer(new int[]{i,j});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            // 방문한곳의 좌표를 리턴결과에 추가
            result.add(cur);
            
            int ci = cur[0]; int cj = cur[1];
            
            // 4방향 탐색
            for(int dr=0; dr<4;dr++){
                int ni = ci+di[dr];
                int nj = cj+dj[dr];
                // 범위내, 미방문, 위치값이 타겟이면.
                if(!isOut(ni,nj) && !v[ni][nj] && map[ni][nj] == target){
                    q.offer(new int[]{ni,nj});
                    v[ni][nj] = true;
                }
            }
        }
        
        return result;
    }
    
    public boolean isOut(int ni, int nj){
        return ni < 0 || nj < 0 || ni>=n || nj >= m;
    }
}