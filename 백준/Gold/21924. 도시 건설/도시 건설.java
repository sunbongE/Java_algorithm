import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 다익스트라
 * 유니온파인드 make, set, union
 *
 * 우선순위큐에 비중 오름차순 정렬
 * 유니온에서 루트가 같지않은 것을 도로설치하는 비용에 추가
 * 큐가 빌때까지? 도로를 설치하는 것을 한다.
 * 마지막에 N-1 == 도로설치 수 ? total_cost - low_cost : -1
 *
 *
 */
public class Main {
    public static class Load implements Comparable<Load>{
        int start;
        int end;
        int cost;

        public Load(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Load o) {
            return this.cost - o.cost;
        }
    }

    public static void make(int n){
        parents = new int[n+1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public static int find(int node){
        if(parents[node]==node){
            return parents[node];
        }
        return parents[node] = find(parents[node]);
    }

    // 부모가 다른 경우 참을 리턴한다.
    public static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA==rootB){
            return false;
        }

        parents[rootA] = rootB;
        return true;
    }

    static int N,M,cnt=0;
    static long  total_cost=0, low_cost=0;
    static int[] parents;
    static PriorityQueue<Load> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        make(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            total_cost += cost;
            pq.offer(new Load(start,end,cost));
        }

        while(!pq.isEmpty()){
            Load cur = pq.poll();

            if(union(cur.start, cur.end)){
                low_cost += cur.cost;
                cnt++;
//                System.out.println(Arrays.toString(parents));

            }
        }

        if(N-1==cnt){
            System.out.println(total_cost-low_cost);
        }else {
            System.out.println(-1);
        }



    }
}
