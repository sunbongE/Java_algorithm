import java.io.*;
import java.util.*;

public class Main {
    static int n, m, s, e;
    static List<List<Dary>> graph; // 굳이 PQ로 저장할 필요 없음
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Dary(to, cost));
            graph.get(to).add(new Dary(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        System.out.println(daik());
    }

    private static int daik() {
        PriorityQueue<Dary> pq = new PriorityQueue<>((a, b) -> b.cost - a.cost);
        visited = new boolean[n + 1];

        pq.offer(new Dary(s, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Dary cur = pq.poll();
            int from = cur.to;

            if (visited[from]) continue;
            visited[from] = true;

            if (from == e) return cur.cost;

            for (Dary nd : graph.get(from)) {
                if (!visited[nd.to]) {
                    pq.offer(new Dary(nd.to, Math.min(cur.cost, nd.cost)));
                }
            }
        }
        return -1;
    }

    static class Dary {
        int to, cost;

        public Dary(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
