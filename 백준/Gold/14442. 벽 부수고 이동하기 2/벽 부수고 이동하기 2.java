import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K,minMoveCnt=-1;
    static int[][] matrix;

    private static class Taeho{
        int cx;
        int cy;
        int cnt;
        int moveCnt;

        public Taeho(int cx, int cy, int cnt,int moveCnt) {
            this.cx = cx;
            this.cy = cy;
            this.cnt = cnt;
            this.moveCnt = moveCnt;
        }

        @Override
        public String toString() {
            return "Taeho{" +
                    "cx=" + cx +
                    ", cy=" + cy +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];

        for(int i=0;i<N;i++){
            String inp = new StringTokenizer(bf.readLine()).nextToken();

            for (int j=0; j<M;j++){
                matrix[i][j] = inp.charAt(j)-'0';
            }
        }


        Bfs();
        System.out.println(minMoveCnt);

    }
    private static void Bfs(){
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        Queue<Taeho> que = new LinkedList<>();
        // 시작하는 태호.
        Taeho start = new Taeho(0,0,K,1);
        que.offer(start);

        boolean[][][] visited = new boolean[N][M][K+1];
        visited[start.cx][start.cy][start.cnt] = true;

        while(!que.isEmpty()){
            Taeho cur = que.poll();
            int cx = cur.cx;
            int cy = cur.cy;
            int cnt = cur.cnt;
            int moveCnt = cur.moveCnt;

            // 끝에 도착했으면 끝내자.
            if(cx==N-1 && cy==M-1){
                minMoveCnt = moveCnt;
                return;
            }

            for(int directionIdx=0;directionIdx<4;directionIdx++){
                int nx,ny;
                nx = cx+dx[directionIdx];
                ny = cy+dy[directionIdx];
                if(isIn(nx,ny)){ // 범위내인경우,

                    // 벽인경우 벽을 부술 수 있으면 부수고 이동 후 큐삽입
                    if(matrix[nx][ny]==1 && cnt > 0 && !visited[nx][ny][cnt-1]){
                        visited[nx][ny][cnt-1] = true;
                        que.offer(new Taeho(nx,ny,cnt-1,moveCnt+1));
                    }else if(matrix[nx][ny]==0 && !visited[nx][ny][cnt]){
                        // 벽이 아닌경우.
                        visited[nx][ny][cnt] = true;
                        que.offer(new Taeho(nx,ny,cnt,moveCnt+1));
                    }


                }
            }

        }
    }

    private static boolean isIn(int nx, int ny){
        return 0<=nx && nx < N && 0<= ny && ny < M;
    }
}
