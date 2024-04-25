 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 BFS
 * 벽을 부수고 이동하는 경우,
 * 벽을 안부수고 이동하는 경우로 나눠서 가야하는데
 * 벽을 부수고 이동할 수 있는 경우는 1번이다.
 *
 * 가는 길에 전부 벽을 부서보면서 이동한다고 가정하고 풀어본다.
 *
 */
public class Main {
   public static class Taeho{
       int x;
       int y;
       int cnt;
       boolean canBreak;

       @Override
       public String toString() {
           return "Taeho{" +
                   "x=" + x +
                   ", y=" + y +
                   ", cnt=" + cnt +
                   ", canBreak=" + canBreak +
                   '}';
       }

       public Taeho(int x, int y, int cnt, boolean canBreak){
           this.x = x;
           this.y=y;
           this.cnt=cnt;
           this.canBreak = canBreak;
       }
   }

   static int n,m, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        // input
        for (int i =0;i<n;i++){
            char[] inpChar = bf.readLine().toCharArray();

            for (int j =0; j<m;j++){
                map[i][j] = inpChar[j] -'0';
            }

//            System.out.println(Arrays.toString(map[i]));
        }

        if(n==1&&m==1){
            System.out.println(1);
            return;
        }
        // bfs시작.
        int result = bfs();
        // 출력.
        System.out.println(result);



   }

    private static int bfs() {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};


        // 시작데이터
        Queue<Taeho> q = new LinkedList<>();
        q.offer(new Taeho(0,0,1,true));

        //방문처리
        boolean visited[][][] = new boolean[n][m][2];
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Taeho cur = q.poll();
//            System.out.println(cur.toString());

            for (int di=0;di<4;di++){
                int nx = cur.x+dx[di];
                int ny = cur.y+dy[di];
                // 범위 밖으면 넘긴다.
                if(!isIn(nx,ny)) continue;

                if(map[nx][ny]==1 && cur.canBreak && !visited[nx][ny][1]){
                    // 부수고 이동
                    visited[nx][ny][1]=true;
                    q.offer(new Taeho(nx,ny, cur.cnt+1, false));
                    if(nx==n-1 && ny == m-1){
                        return cur.cnt+1;
                    }
                } else if (map[nx][ny] == 0) {
                    if(cur.canBreak && !visited[nx][ny][0]){
                        visited[nx][ny][0]=true;
                        q.offer(new Taeho(nx,ny, cur.cnt+1, cur.canBreak));
                        if(nx==n-1 && ny == m-1){
                            return cur.cnt+1;
                        }
                    }
                    if(!cur.canBreak && !visited[nx][ny][1]){
                        visited[nx][ny][1]=true;
                        q.offer(new Taeho(nx,ny, cur.cnt+1, cur.canBreak));
                        if(nx==n-1 && ny == m-1){
                            return cur.cnt+1;
                        }
                    }
                }


            }

        }




        return -1;
    }

    private static boolean isIn(int nx, int ny) {
        return 0<=nx && nx < n && 0<=ny && ny < m ;
    }
}
