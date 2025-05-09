import java.io.*;
import java.util.*;

/**
 * 문제
 * 가로 : 0
 * 세로 : 1
 * 대각선 : 2
 *
 * 갈 수 있는 방향
 * 가로 : 오른쪽, 대각선 오른쪽아래
 * 세로 : 아래, 대각선 오른쪽 아래
 * 대각선 : 오른쪽, 아래, 대각 오른쪽아래
 *
 * 가는 방향이 정해져있어 사이클 걱정은 없음.
 *
 * */
public class Main {
    //  오른, 아래, 대각
    static int[] di = {0,1,1}, dj = {1,0,1};
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //       BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st ;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(map[N-1][N-1]==1) {
            System.out.println(0);
        }else{
            System.out.println(bfs(0,1));
        }

    }

    private static int bfs(int si, int sj) {
        Queue<Pipe> q = new ArrayDeque<>();
        q.offer(new Pipe(0,new int[] {si,sj}));
        int cnt=0;
        while(!q.isEmpty()){
            Pipe cur = q.poll();
            int ci = cur.posi[0];
            int cj = cur.posi[1];
            if(ci == N-1 && cj == N-1){
                cnt++;
                continue;
            }

            if(cur.direction == 0){// 가로
                // 오른쪽
                move(0,ci,cj,q);
                // 대각
                move(2,ci,cj,q);
            }else if(cur.direction == 1){//세로
                // 아래
                move(1,ci,cj,q);
                // 대각
                move(2,ci,cj,q);
            }else if(cur.direction==2){//대각
                // 오른쪽
                move(0,ci,cj,q);
                // 아래
                move(1,ci,cj,q);
                // 대각
                move(2,ci,cj,q);
            }
 

        }


        return cnt;
    }

    private static void move(int dir, int ci, int cj, Queue<Pipe> q) {
        boolean result = false;
        int ni = ci + di[dir];
        int nj = cj + dj[dir];
        if(!isOut(ni,nj) && map[ni][nj]==0){
            if(dir == 2){ // 대각선인경우 위,왼 확인해서 벽이 있는지 확인한다.
                if(map[ni-1][nj]!=1 && map[ni][nj-1]!=1){
                    result = true;
                }
            }else{
                result = true;
            }
        }

        if(result){ // 이동 되면.
            q.offer(new Pipe(dir,new int[] {ni,nj}));
        }
    }

    static boolean isOut(int ni, int nj){
        return ni < 0 || ni >= N || nj < 0 || nj >= N;
    }

    static class Pipe{
        int direction;
        int[] posi;
        public Pipe( int direction, int[] posi){
            this.direction = direction;
            this.posi = posi;
        }
    }
}
