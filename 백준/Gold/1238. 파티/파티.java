
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Node>> adjList;
    static int N, M, X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 인접 리스트 생성
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Node(to, time));
        }

        int maxTime = 0;
        for (int start = 1; start <= N; start++) {
            if (start == X) continue; // 출발지와 도착지가 같은 상황 무시

            // start에서 X까지의 최단 경로 시간
            int toX = dijkstra(start, X);
            // X에서 start까지의 최단 경로 시간
            int fromX = dijkstra(X, start);

            // 왕복 시간 갱신
            int roundTripTime = toX + fromX;
            if (roundTripTime > maxTime) {
                maxTime = roundTripTime;
            }
        }
        System.out.println(maxTime);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.vertex;
            int currentDist = currentNode.cost;

            if (currentDist > dist[current]) continue;

            for (Node neighbor : adjList.get(current)) {
                int next = neighbor.vertex;
                int nextDist = currentDist + neighbor.cost;

                if (nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.offer(new Node(next, nextDist));
                }
            }
        }

        return dist[end];
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}