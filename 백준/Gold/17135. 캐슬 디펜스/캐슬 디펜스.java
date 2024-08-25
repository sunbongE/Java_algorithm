
/**
 * 궁수가 갈 수 있는 모든위치의 조합을 구한 하여 조합 값으로 궁수 배치
 * 이후에, 시뮬레이션 돌린다.
 * <p>
 * 시뮬레이션
 * 왼쪽부터 가장 가까운 적을 찾는다.
 * 좌상, 상, 우상 -> 순으로 순회한다.
 * D보다 작거나 같은
 * <p>
 * 좌상 : -1, 1
 * 상 : -1, 0
 * 우상 : -1, 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, D, maxKill = 0, limit = 3, cnt = 0;
    static int[][] gameMap, tmpMap;
    static int comResult[], v[][];
    static boolean[][] isAtteck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());


        gameMap = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                gameMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comResult = new int[limit]; // 궁수는 3명만
        // 조합을 구해서 각 궁수 위치 조합에 따라 처치할 수 있는 적의 수를 받아온다.
        com(M, 3, 0, 0, 0);
        System.out.println(maxKill);
    }

    // NcR, idx: 선택된 수를 넣을 자리, n: 탐색할 수, selectCnt : 선택한 개수.
//    private static void com(int n, int r, int idx, int num, int selectCnt) {
//        if (num >= n) return;
//
//        if (selectCnt >= r) {
//            System.out.println(Arrays.toString(comResult));
//            int killCnt = 0;
//            isAtteck = new boolean[N][M]; // 공격 받은 빌런 저장.
//            tmpMap = copyGameMap();
//            simulation(comResult);
//            for (boolean[] booleans : isAtteck) {
//                for (boolean death : booleans) {
//                    if (death) killCnt++;
//                }
//            }
//            maxKill = Math.max(maxKill, killCnt);
//            System.out.println(maxKill);
//            return;
//        }
//
//        comResult[idx] = num;
//        com(n, r, idx + 1, num + 1, selectCnt + 1); // 선택
//        com(n, r, idx, num + 1, selectCnt); // 미선택
//    }
    private static void com(int n, int r, int idx, int num, int selectCnt) {
        // 궁수 3명을 다 선택한 경우
        if (selectCnt == r) {
//            System.out.println(Arrays.toString(comResult));
            int killCnt = 0;
            isAtteck = new boolean[N][M]; // 공격받은 적을 기록
            tmpMap = copyGameMap();
            simulation(comResult); // 시뮬레이션 실행
            for (boolean[] booleans : isAtteck) {
                for (boolean death : booleans) {
                    if (death) killCnt++;
                }
            }
            maxKill = Math.max(maxKill, killCnt);
//            System.out.println(maxKill);
            return;
        }

        // 현재 위치를 선택하지 않거나, 다음 위치로 이동하는 경우
        if (num < n) {
            comResult[idx] = num;
            com(n, r, idx + 1, num + 1, selectCnt + 1); // 선택한 경우
            com(n, r, idx, num + 1, selectCnt); // 미선택한 경우
        }
    }


    private static int[][] copyGameMap() {
        int[][] result = new int[gameMap.length][gameMap[0].length];
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[0].length; j++) {
                result[i][j] = gameMap[i][j];
            }
        }
        return result;
    }

    // N-1번부터 올라가면서 탐색할거임
    private static void simulation(int[] comResult) {

        // 궁수들의 위치 기록.
        int[][] archers = new int[limit][2];
        for (int i = 0; i < N; i++) {
            archers[0] = new int[]{N - i, comResult[0]};
            archers[1] = new int[]{N - i, comResult[1]};
            archers[2] = new int[]{N - i, comResult[2]};
            atteck(archers);
//            System.out.println("공격받은거");
//            for (boolean[] booleans : isAtteck){
//                System.out.println(Arrays.toString(booleans));
//            }
//            System.out.printf("궁수들의 위치 1 {%d,%d}, 2 {%d,%d}, 3{%d,%d}\n"
//                    , archers[0][0], archers[0][1], archers[1][0], archers[1][1], archers[2][0], archers[2][1]);

        }


    }

    private static void atteck(int[][] archers) {

        // 각 궁수가 자신과 가장 가까운 적을 찾아낸다.
        for (int i = 0; i < limit; i++) {
            // 각 궁수가 공격할 적을 선택 후 결과를 isAtteck에 기록한다.
            bfs(archers[i]);
            // 편의상 궁수가 위치를 1칸 위로 이동한다.
        }

        for (int i = 0; i < isAtteck.length; i++) {
            for (int j = 0; j < isAtteck[0].length; j++) {
                if (isAtteck[i][j]) {
                    tmpMap[i][j] = 0;
                }
            }
        }


    }

    private static void bfs(int[] archer) {
        // 좌상우
        int[] dx = {0, -1, 0};
        int[] dy = {-1, 0, 1};
        Queue<int[]> q = new LinkedList<>();
        q.offer(archer);
        // 출발지.
        v = new int[N + 1][M]; // 궁수 위치만큼 행 생성.
        try {

        v[archer[0]][archer[1]] = 0; // 출발위치 초기화 : 현재 궁수 위치 기준으로 탐색.
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(N+1);
            System.out.println(archer[0]);
            System.out.println(archer[1]);
        }

        while (!q.isEmpty()) {
            int[] curArcher = q.poll();
            int ci = curArcher[0], cj = curArcher[1];

            // 가장 가까운 적 찾은경우.
            if (tmpMap[ci][cj] == 1) {
                isAtteck[ci][cj] = true;
                return;
            }
            for (int dr = 0; dr < 3; dr++) {
                int ni = ci + dx[dr];
                int nj = cj + dy[dr];
                // 범위내, 미방문, 전방문+1<=D -> 궁수의 공격 범위내에 유효한 값만 큐에 넣음
                if (isIn(ni, nj, archer[0]) && v[ni][nj] == 0 && v[ci][cj] + 1 <= D) {
                    v[ni][nj] = v[ci][cj] + 1;
                    q.offer(new int[]{ni, nj});
                }
            }
        }
    }


    private static boolean isIn(int ni, int nj, int limitRow) {
        return 0 <= ni && ni < limitRow && 0 <= nj && nj < M;
    }
}
