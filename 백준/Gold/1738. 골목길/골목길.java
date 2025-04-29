import java.io.*;
import java.util.*;
/**
 *  첫째 줄에 골목길들이 교차하는 지점의 개수 n (2 ≤ n ≤ 100)과 골목길의 개수 m (1 ≤ m ≤ 20,000) 이 차례로 주어진다.
 *  이어지는 m개의 행에 각각의 골목길을 나타내는 세 정수 u, v, w가 차례로 주어진다.
 *  이는 u번 교차점에서 v번 교차점으로 이동할 수 있는 골목길이 나있다는 의미이다.
 *  즉, 주어지는 골목길들은 기본적으로 모두 일방통행로이다. w (0 ≤ |w| ≤ 1,000)는 이 길을 지날 때 갈취당하거나 획득하게 되는 금품의 양이다.
 *  음수는 갈취, 양수는 획득을 뜻한다.
 *
 * 골목길의 교차점 번호는 1이상 n이하의 정수이다. 민승이네 집은 1번 교차점에 있고, 이곳 코레스코 콘도는 n번 교차점에 있다.
 *
 * 최적의 경로를 구할 수 있다면 민승이네 집부터 코레스코 콘도까지 가는 동안 거치게 되는 교차점들의 번호를 공백 하나를 사이에 두고 차례로 출력하면 된다.
 * 그런데, 경우에 따라서는 최적의 경로라는 것이 존재하지 않는 상황이 발생한다.
 * 어떠한 경우에 그런 상황이 발생하는지 생각해 보자. 그러한 경우에는 -1을 출력하도록 한다.
 *
 * 최적의 경로가 여러 개 존재할 때는 아무거나 출력해도 된다.
 *
 * 1. 이번에는 사이클이 양의 사이클이 존재하는지 확인해야한다.
 * 2. 기존에 INF가 아닌 최소값으로 모든 배열을 초기화해야한다.
 * 3. 몇번 반복할건가?
 *  - 왔던길을 갔다가 목적지까지 가는 것이 최대값을 갱신할 수 있는거라면 무한히 증가한다는것. 양의 사이클
 *  - 그냥 N번 반복하는게 맞는것같음.
 *
 * */
public class Main {
    static int n,m,path[], MAX = 100000;
    static int costs[];
    static ArrayList<Way> ways;
    static boolean nInCycle=false;
    public static void main(String[] args) throws IOException {
  //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ways = new ArrayList();


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            ways.add(new Way(a,b,c));
        }

        costs = new int[n+1];

        bellmanFord();

        if(costs[n] == MAX){
            System.out.println(-1);
        }else{
            List<Integer> route = new ArrayList<>();
            int node = n;
            route.add(node);
            while (node != 1) {
                node = path[node];
                route.add(node);
            }
            Collections.reverse(route);
            for (int i = 0; i < route.size(); i++) {
                System.out.print(route.get(i));
                if (i != route.size() - 1) System.out.print(" ");
            }

        }

    }

    private static void bellmanFord() {
        Arrays.fill(costs, -MAX);
        costs[1] = 0;
        path = new int[n+1];

        for (int i = 0; i < n; i++) {
            for(Way way : ways){
                if(costs[way.from] == -MAX) continue;
                int nCost = way.cost + costs[way.from];
                if(nCost > costs[way.to]){
                    costs[way.to] = nCost;
                    path[way.to] = way.from;

                    if(i == n-1 ){
                        costs[way.to] = MAX;
                    }
                }
            }
        }
    }


    static class Way{
        int from,to, cost;
        public Way(int from,int to, int cost){
            this.from=from;
            this.to=to;
            this.cost = cost;
        }
    }
}
