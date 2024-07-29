import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,E,INF = 800*10000+1;
    static ArrayList<ArrayList<Node>> adjList;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 노드 수
        E = Integer.parseInt(st.nextToken()); // 간선 수

        adjList = new ArrayList<>();
        for (int i = 0; i <=N; i++) {
            adjList.add(new ArrayList<>());
        }

        int a,b,cost;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a =  Integer.parseInt(st.nextToken());
            b =  Integer.parseInt(st.nextToken());
            cost =  Integer.parseInt(st.nextToken());

            // 양방향
            adjList.get(a).add(new Node(b,cost));
            adjList.get(b).add(new Node(a,cost));
        }
        st = new StringTokenizer(br.readLine());
        int stopover1 = Integer.parseInt(st.nextToken());
        int stopover2 = Integer.parseInt(st.nextToken());

        // 두 경로의 비용 계산
        int way1 = dijkstra(1, stopover1) + dijkstra(stopover1, stopover2) + dijkstra(stopover2, N);
        int way2 = dijkstra(1, stopover2) + dijkstra(stopover2, stopover1) + dijkstra(stopover1, N);

//        System.out.println(way1+", "+way2);

        int result = Math.min(way1,way2);
        System.out.println((result >=INF) ? -1 : result);

    }

    private static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] costs = new int[N+1];
        Arrays.fill(costs,INF);
        costs[start]=0;
        pq.offer(new Node(start,0));

        while (!pq.isEmpty()){
            Node curNode = pq.poll();
            int cur = curNode.destination;
            int curCost = curNode.cost;

            if(costs[cur] < curCost) continue;

            // 인접한거 최소비용 갱신하기
            for(Node nextNode : adjList.get(cur)){
                int next = nextNode.destination;
                int nextCost = nextNode.cost+curCost;

                if(costs[next]>nextCost){
                    costs[next] = nextCost;
                    pq.offer(new Node(next, nextCost));
                }

            }

        }
        return costs[end];
    }

    private static class Node implements Comparable<Node>{
        int destination,cost;
        public Node( int destination, int cost){
            this.destination=destination;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
