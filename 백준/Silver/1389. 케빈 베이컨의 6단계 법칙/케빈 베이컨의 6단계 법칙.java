import java.util.*;
import java.io.*;

public class Main {

    static int n, m, minCnt = 1234567890, ans = 111;
    static ArrayList<Integer>[] gragh;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        gragh = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) gragh[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a, b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            gragh[a].add(b);
            gragh[b].add(a);
        }
//        for (ArrayList arrayList : gragh) {
//            System.out.println(arrayList);
//        }

        for (int i = 1; i <= n; i++) {
            int tmpCnt = 0;

            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                tmpCnt += bfs(i, j);
            }

            // 이동수가 같으면 넘버가 적은애 답으로.
            if (minCnt == tmpCnt) {
                ans = Math.min(ans, i);
            // 이동수가 적으면 답 갱신.
            } else if (minCnt > tmpCnt) {
                minCnt = tmpCnt;
                ans = i;
            }

        }
        System.out.println(ans);

    }

    private static int bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        int[] v = new int[n + 1];
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Integer next : gragh[cur]) {
                if (v[next] == 0) {
                    v[next] = v[cur] + 1;
                    // 다음 값이 목표지점이라면.
                    if (next == end) {
                        return v[next];
                    }

                    q.offer(next);
                }
            }
        }

        return -1;
    }
}