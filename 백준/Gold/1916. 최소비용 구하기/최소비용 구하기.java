
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // Bus : 우선순위를 최소비용으로한다.
    private static class Bus implements Comparable<Bus> {
        int to;
        int cost;
        Bus nextBus;
        public Bus(int to, int cost, Bus nextBus){
            this.to = to;
            this.cost = cost;
            this.nextBus = nextBus;
        }

        @Override
        public int compareTo(Bus other){
            return Integer.compare(this.cost, other.cost);
        }
    }

    static int N,M;
    static int INF = Integer.MAX_VALUE;
    static int[] minCost;
    static Bus[] adjBus;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        adjBus = new Bus[N+1]; // 인접한 버스들 기록.
        minCost = new int[N + 1];
        Arrays.fill(minCost,INF);

        StringTokenizer st;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjBus[from] = new Bus(to,cost,adjBus[from]); // 인접한거 세팅
        }

        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start,end);

        System.out.println(minCost[end]);
    }

    private static void dijkstra(int start, int end){
        // 우선순위 큐 생성.
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.offer(new Bus(start,0,null)); // 출발지 넣음

        minCost[start] = 0 ; // 출발지는 비용이 0으로 세팅한다.

        while(!pq.isEmpty()){
            // 현재 버스 정보 가져옴.
            Bus curBus = pq.poll();

            int cur = curBus.to;
            int cost = curBus.cost;

            if(cur == end) break;
            
            if(minCost[cur] < cost) continue;

            for(Bus next = adjBus[cur] ; next!=null; next=next.nextBus){
                int nextCost = cost + next.cost;
                if(minCost[next.to] > nextCost){
                    minCost[next.to] = nextCost;
                    pq.offer(new Bus(next.to,nextCost,next));
                }
            }
        }
    }
}
