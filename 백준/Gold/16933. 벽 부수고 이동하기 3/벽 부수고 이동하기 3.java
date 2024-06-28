import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 지도가 0이면 이동할수있다.
 * 처음은 낮이다.
 */
public class Main {

    private static class Taeho {
        int ci;
        int cj;
        int k;
        int moveCnt;
        boolean isMorning;

        public Taeho(int ci, int cj, int k, int moveCnt, boolean isMorning) {
            this.ci = ci;
            this.cj = cj;
            this.k = k;
            this.moveCnt = moveCnt;
            this.isMorning = isMorning;
        }

        @Override
        public String toString() {
            return "Taeho{" +
                    "ci=" + ci +
                    ", cj=" + cj +
                    ", k=" + k +
                    ", moveCnt=" + moveCnt +
                    ", isMorning=" + isMorning +
                    '}';
        }
    }

    static int N, M, K,ans = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String in = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = in.charAt(j)-'0';
            }
        }


        bfs();
        System.out.println((ans==Integer.MAX_VALUE)? -1 : ans);
    }

    private static void bfs() {
        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        Queue<Taeho> q = new LinkedList<>();

        q.offer(new Taeho(0, 0, K, 1, true));

        boolean[][][][] visited = new boolean[N][M][K+1][2]; // 위치, k개수, 낮-밤

        visited[0][0][K][0] = true; // 아침을 0으로한다.

        while (!q.isEmpty()) {
            Taeho cur = q.poll();
//            System.out.println();
//            System.out.println(cur.toString());
            int ci = cur.ci;
            int cj = cur.cj;
            int k = cur.k;
            int isMoring = (cur.isMorning) ? 0 : 1; // 지금
            int preMoring = (isMoring==1) ? 0 : 1; // 이전

            if(ci==N-1 && cj==M-1){
                ans = cur.moveCnt;
                return;
            }
            for (int dir = 0; dir < 4; dir++) {
                int ni = ci + di[dir];
                int nj = cj + dj[dir];

                if (isIn(ni, nj)) { // 범위내 미방문.
                    if(map[ni][nj]==1){
                        // 벽인경우
                        if(isMoring == 0 && k>0){ // 낮이면서 k가 0보다 크고, 미방문: 부수고이동하거나 생략한다.

                            if(visited[ni][nj][k][preMoring]) continue;

                            visited[ni][nj][k][preMoring] = true; // 방문
                            q.offer(new Taeho(ni,nj,k-1,cur.moveCnt+1,!cur.isMorning));

                        }else if(isMoring == 1 && k>0){ // 밤이면서 k가 0보다 크고, 미방문: 대기하거나 생략한다.
                            if(visited[ci][cj][k][preMoring]) continue;
                            visited[ci][cj][k][preMoring] = true; // 방문
                            q.offer(new Taeho(ci,cj,k,cur.moveCnt+1,!cur.isMorning));
                        }

                    }else{
                    // 벽이 아닌경우
                        // 미방문이면 방문한다.
                        if(visited[ni][nj][k][preMoring]) continue;

                        visited[ni][nj][k][preMoring] =true;
                        q.offer(new Taeho(ni,nj,k, cur.moveCnt+1,!cur.isMorning ));
//                        System.out.println("==============벽이아니라 이동한거=================");
//                        System.out.println(new Taeho(ni,nj,k, cur.moveCnt+1,!cur.isMorning ).toString());
//                        System.out.println("==============================="+map[ni][nj]);
                    }

                }

            }
        }
    }

    private static boolean isIn(int ni, int nj) {
        return 0 <= ni && ni < N && 0 <= nj && nj < M;
    }
}


