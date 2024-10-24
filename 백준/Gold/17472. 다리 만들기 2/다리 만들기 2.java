import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, dx[] = {-1, 1, 0, 0}, dy[] = {0, 0, -1, 1}, Num = 0, INF = 200,parents[];
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 구분하기
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0 && !v[i][j]) {
                    Num++; // 섬 번호.
                    bfs(i, j);
//                    showMap();
                }
            }
        }


//        showMap(); // 출력해보기.

        // 각 섬에서 다른 섬으로 이동하는 최단 거리를 기록하는 그래프 그리기.
        int[][] graph = getGraph();

//        showGraph(graph);
        // 인접그래프정보로 모든 섬을 연결하는 최소 비용을 구하는 쿠르스칼알고리즘 적용한다.

        parents = make(Num+1);
        int minCost = kurscal(graph);
        for (int i = 1; i <= Num; i++) {
            if(union(i,1)){
                minCost=-1;
            };
        }
        System.out.println(minCost);
    }

    private static int kurscal(int[][] graph) {
        int minCost=0;
        PriorityQueue<Land> pq = new PriorityQueue<>();

        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph[0].length; j++) {
                if(graph[i][j]!=INF){ // 갈수있는 거리곳을 모두 넣어준다.
                    pq.offer(new Land(i,j,graph[i][j]));
                }
            }
        }

        // 하나씩 꺼내면서 union해봐 모두 최소 비용으로 연결되게 만든다.
        while(!pq.isEmpty()){
            Land cur = pq.poll();

            if(union(cur.from, cur.to)){ // 서로 다른 그룹이면 true리턴함.
                minCost+=cur.cost;
            }

        }

        return (minCost==0)?-1:minCost;

    }

    private static int[][] getGraph() {
        int[][] graph = new int[Num + 1][Num + 1];

        for (int i = 0; i <= Num; i++) {
            Arrays.fill(graph[i], INF); // 각 연결 그래프 최대값으로 초기화.
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0) {// 섬인 경우.
                    int dir = 0;
                    int cnt = 0;
                    int ci = i, cj = j;
                    while (dir < 4) {
                        int ni = ci + dx[dir];
                        int nj = cj + dy[dir];

                        // 범위에 있고, 같은 섬이 아니면.
                        if (isIn(ni, nj) && map[ni][nj] != map[i][j]) {
                            if (map[ni][nj] != 0) {
                                // 다른 섬을 만났다면. 최소거리 값으로 갱신.
                                if (cnt > 1) {
                                    graph[map[i][j]][map[ni][nj]] = Math.min(graph[map[i][j]][map[ni][nj]], cnt); // 거리 갱신.
                                }
                                dir++;
                                cnt = 0;
                                ci = i;
                                cj = j;
                            } else if (map[ni][nj] == 0) { // 바다를 건너는 경우.
                                cnt++;
                                ci = ni;
                                cj = nj;
                            }
                        } else { // 범위를 벗어나면 처음 위치에서 다른 방향으로 이동.
                            dir++;
                            cnt = 0;
                            ci = i;
                            cj = j;
                        }
                    }

                }
            }
        }


        return graph;
    }

    // union find make
    private static int[] make(int n){
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i]=i;
        }
        return result;
    }

    private static int find(int child){
        if(parents[child] == child) return child;
        return parents[child] = find(parents[child]);
    }

    private static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        parents[rootB] = rootA;
        return true;

    }

    private static class Land implements Comparable<Land>{
        int from,to, cost;

        public Land(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Land o){
            return Integer.compare(this.cost, o.cost);
        }
    }


    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        v[i][j] = true;
        q.offer(new int[]{i, j});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            map[ci][cj] = Num;

            for (int k = 0; k < 4; k++) {
                int ni = ci + dx[k];
                int nj = cj + dy[k];
                if (isIn(ni, nj) && map[ni][nj] == 1 && !v[ni][nj]) {
                    v[ni][nj] = true;
                    q.offer(new int[]{ni, nj});
                }
            }
        }

    }

    private static boolean isIn(int ni, int nj) {
        return 0 <= ni && ni < N && 0 <= nj && nj < M;
    }

    private static void showMap() {
        System.out.println("------------------------");
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private static void showGraph(int[][] graph) {
        for (int[] ints : graph) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
