import java.io.*;
import java.util.*;

public class Main {
    static int n,m,s,e;
    static List<List<Dary>> li;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //      BufferedReader br = new BufferedReader(new FileReader("./test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 출발지별 PriorityQueue 준비
        li = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            li.add(new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 무향
            li.get(from).add(new Dary(to, cost));
            li.get(to).add(new Dary(from, cost));
        }


        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int ans = daik();

        System.out.println(ans);
    }

    private static int daik(){
        int ans = 0;
        PriorityQueue<Dary> q = new PriorityQueue<>((a,b)->{
            return b.cost - a.cost;
        });
        boolean[] v = new boolean[n+1];
        q.offer(new Dary(s,Integer.MAX_VALUE));

        while(!q.isEmpty()){
            Dary cur = q.poll();
            int from = cur.to;

            // 방문체크.
            if (v[from]) continue; // 이미 방문한거 건너뛰기 
            v[from] = true;

            // 도착한거.
            if(from == e){
                return cur.cost;
            }

            for(Dary nd : li.get(from)){
                int to = nd.to;
                // 비용높은거먼저, 안가본곳이면ㄱ
                if(!v[to]){ // 미방문 -> 방문.
                    q.offer(new Dary(to, Math.min(cur.cost,nd.cost) ));
                }
            }
        }
        return -1;
    }

    static class Dary{
        int to, cost;
        public Dary(int to, int cost){
            this.to=to;
            this.cost=cost;
        }
    }
}