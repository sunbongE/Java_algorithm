
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 배양액을 뿌릴 수 있는 모든 경우를 따져봐서 뿌려본다.
 * 2. 배양액을 모두 뿌렸을 때 bfs를 사용해서 시뮬레이션을 돌려보고, 꽃의 개수를 찾아 리턴한다.
 * 3. 리턴받은 꽃의 개수의 최대값을 갱신한다.
 */
public class Main {
    static int N, M, R, G, ans;
    static final int RED = 3, GREEN = 4, TWO = 2;
    static ArrayList<int[]> li;
    static int[][] garden;

    public static void main(String[] args) throws IOException {
        ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        li = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        garden = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
                if (garden[i][j] == TWO) li.add(new int[]{i, j});
            }
        }

        // 가능한 모든 경우에 배양액 뿌려보기.
        back(0, G, R);
        System.out.println(ans);
    }

    private static void back(int cnt, int g, int r) {
        // 배양액을 둘 수 있는 위치에 모두 두었고, 각 색의 배양액을 모두 놓은 상황이라면.
        if (cnt == li.size()) {
            if (g == 0 && r == 0) {
//                showGarden(garden, "빽트결과");
                ans = Math.max(ans, bfs());
//                System.out.println("현재 최대 : "+ans);
            }
            return;
        }

        int[] cur = li.get(cnt);
        int ci = cur[0], cj = cur[1];
        // 초록 배양액을 놓는 경우
        if (g > 0) {
            garden[ci][cj] = GREEN;
            back(cnt + 1, g - 1, r);
        }
        if (r > 0) {
            garden[ci][cj] = RED;
            back(cnt + 1, g, r - 1);
        }
        garden[ci][cj] = TWO;
        back(cnt + 1, g, r);
        // 빨간 배양액을 놓는 경우
        // 되돌리기.

    }

    private static void showGarden(int[][] map, String des) {
        System.out.println("-------------" + des + "------------");
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private static int bfs() {
        int[] di = {-1, 1, 0, 0}, dj = {0, 0, -1, 1};
        int cnt = 0, time = 1;
        Queue<Info> q = initQ(); // 배양액이 뿌려져있는 곳 위치정보 받아옴.
        int[][] v = new int[N][M]; // 시간을 기록할것.
        int[][] tmp = copyGarden(); // 정원을 기록할것.

        // 색깔별로 정원에 퍼진 배양액 기록하기

        for (int i = 0; i < q.size(); i++) { // 현재 배양액 뿌린곳 방문체크.
            Info cur = q.poll();
            v[cur.ci][cur.cj] = -1;
            tmp[cur.ci][cur.cj] = cur.color; // 정원에 씨앗 뿌려
            q.offer(cur);
        }


        // 각 초마다 맵의 변화를 감시.
        while (!q.isEmpty()) {

            Info cur = q.poll();
            int ci = cur.ci, cj = cur.cj;
            if (tmp[ci][cj] >= RED + GREEN) continue;

            for (int dir = 0; dir < 4; dir++) {
                int ni = ci + di[dir];
                int nj = cj + dj[dir];
                // 범위내, 이동할 위치가 처음이거나 같은 시간대에 이동한거면, 이동가능이면.
                if (isIn(ni, nj) && (v[ni][nj] == 0 || v[ni][nj] == cur.time + 1) && tmp[ni][nj] != 0) {
                    // 만약 값이 v에 표시된 값이 0이거나 나랑 다른값이면
                    if (tmp[ni][nj] == cur.color) continue;

                    v[ni][nj] = cur.time + 1;
                    if (tmp[ni][nj] == 1 || tmp[ni][nj] == 2) {
                        tmp[ni][nj] = cur.color;
                    } else {
                        tmp[ni][nj] += cur.color;
                    }
                    if (tmp[ni][nj] == RED + GREEN) { // 꽃이면 개수
                        cnt++;
                    } else { // 아니면 다음에 또 퍼지도록
                        q.offer(new Info(ni, nj, cur.time + 1, cur.color));
                    }
                }
            }


        }

//        showGarden(tmp,"tmp");
        return cnt;
    }

    private static int[][] copyGarden() {
        int[][] r = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                r[i][j] = garden[i][j];
            }
        }
        return r;
    }

    private static Queue<Info> initQ() {
        Queue<Info> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (garden[i][j] == RED || garden[i][j] == GREEN) {
                    q.offer(new Info(i, j, 1, garden[i][j]));
                }
            }
        }
        return q;
    }

    private static boolean isIn(int ni, int nj) {
        return 0 <= ni && ni < N && 0 <= nj && nj < M;
    }

    private static class Info {
        int ci, cj, time, color;

        public Info(int ci, int cj, int time, int color) {
            this.ci = ci;
            this.cj = cj;
            this.time = time;
            this.color = color;
        }
    }
}
