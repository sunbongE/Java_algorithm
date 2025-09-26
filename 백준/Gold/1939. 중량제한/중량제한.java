import java.io.*;
import java.util.*;
/**
 * 풀이 이분탐색+bfs
 *
 * 이분탐색으로 1~10만중 s->e 도착할 수 있는 경우 중 최대값을 찾는다.
 * */
public class Main {
    static int n,m,s,e;
    static List<List<Dary>> li;
    static int min = 1, max =0, v[]=new int[100001],vId=1;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //         BufferedReader br = new BufferedReader(new FileReader("./test.txt"));
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
            max = Math.max(max, cost);
            // 무향
            li.get(from).add(new Dary(to, cost));
            li.get(to).add(new Dary(from, cost));
        }


        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        System.out.println(binarySol());
    }

    private static int binarySol(){

        int ans=0;
        while(min <= max){
            int w = (min+max)/2; // 탐색할 무게.
            vId++;
            if(bfs(w)){ // 통과되는 경우 더 무거운 무게 확인
                min = w+1;
                ans = w;
            }else{ // 통과 못하면, 더 가벼운거 확인
                max = w-1;
            }
        }
        return ans;
    }

    private static boolean bfs(int w){
//        System.out.println("무게 : " +w);


        Queue<Integer> q = new LinkedList<>();

        q.offer(s);

        v[s]=vId;; // 방문 체크

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == e) return true;

            // 미방문인 것 중에 통과할 수 있는 다리인 경우.
            for( Dary next : li.get(cur)){
                if(next.cost >= w && v[next.to] != vId) {
                    v[next.to] = vId;
                    q.offer(next.to);
//                    System.out.println("통과한 다리 : "+next.toString());

                }

            }

        }

        return false;
    }


    static class Dary{
        int to, cost;
        public Dary(int to, int cost){
            this.to=to;
            this.cost=cost;
        }

        @Override
        public String toString() {
            return "Dary{" +
                    "to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }
}