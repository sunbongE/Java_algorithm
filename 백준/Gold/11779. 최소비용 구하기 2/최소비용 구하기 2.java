
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static PriorityQueue<Bus> pq = new PriorityQueue<>();
    static ArrayList<Bus>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new Bus(to,cost,0,""));
        }

        st = new StringTokenizer(br.readLine());
        int a  = Integer.parseInt(st.nextToken());
        int b  = Integer.parseInt(st.nextToken());

        sol(a,b);

    }

    private static void sol(int a, int b) {
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        Bus bus = new Bus(a,0,1,""+a);
        pq.offer(bus);
        boolean v[] = new boolean[n+1];


        while(!pq.isEmpty()){
            Bus cur = pq.poll();
//            System.out.println(cur.toString());
            v[cur.to] = true;
            if(cur.to == b){
                System.out.println(cur.cost);
                System.out.println(cur.moveCnt);
                System.out.println(cur.way);
                return;
            }

            for(Bus next : adjList[cur.to]){
                if(v[next.to]) continue;
                Bus nbus = new Bus(next.to, cur.cost+next.cost, cur.moveCnt+1, cur.way+" "+next.to);
                pq.offer(nbus);
            }

        }

        return ;
    }

    private static class Bus implements Comparable<Bus> {
        int   to, cost,moveCnt;
        String way;

        public Bus( int to, int cost,int moveCnt,String way){

            this.to = to;
            this.cost=cost;
            this.moveCnt=moveCnt;
            this.way=way;
        }


        @Override
        public int compareTo(Bus o){
            return Integer.compare(this.cost, o.cost);
        }

        @Override
        public String toString() {
            return "Bus{" +
                    "to=" + to +
                    ", cost=" + cost +
                    ", moveCnt=" + moveCnt +
                    ", way='" + way + '\'' +
                    '}';
        }
    }
}
