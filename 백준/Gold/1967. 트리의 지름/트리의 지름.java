import java.io.*;
import java.util.*;

/**
 * 트리의 지름을 구하는 DFS 알고리즘
 * Step 1: 임의의 노드(1)에서 가장 먼 노드(farthestNode)를 찾는다
 * Step 2: farthestNode에서 가장 먼 노드까지의 거리를 구하면 그게 지름이다
 */
public class Main {
    static int n, farthestNode=1, maxCost;
    static List<Edge>[] graph;
    static boolean[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        // 그래프 초기화
        graph = new List[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to,cost));
            graph[to].add(new Edge(from,cost));
        }

        // 1번에서 가장 멀리 있는 노드 찾기.
        v = new boolean[n+1];
        maxCost=0;
        dfs(1,0);

        Arrays.fill(v, false);
//        maxCost=0;
        dfs(farthestNode,0);

        System.out.println(maxCost);
    }

    // 가장 멀리있는 노드, 비용 갱신.
    private static void dfs(int start, int cost) {
        v[start] = true;
        // 갈 수 있는 곳을 가본다.
        for(Edge edge : graph[start]){
            if(v[edge.node]) continue;
            int moveCost = cost + edge.cost;
            if(maxCost < moveCost){
                farthestNode = edge.node;
                maxCost = moveCost;
            }
            dfs(edge.node, moveCost);
        }
    }


    static class Edge{
        int node, cost;
        public Edge(int node, int cost){
            this.cost=cost;
            this.node=node;
        }
    }
}
