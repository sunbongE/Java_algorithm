import java.io.*;
import java.util.*;

public class Main {
    static int n, farthestNode=1,maxCost=0;
    static boolean v[];
    static List<Edge>[] graph ;
    public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new FileReader("test.txt"));
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        graph = new List[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while(true){
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                graph[from].add(new Edge(to,cost));
            }
        }

        v = new boolean[n+1];
        dfs(1,0);

        Arrays.fill(v,false);
        maxCost=0;
//        System.out.println(farthestNode);
        dfs(farthestNode,0);
//        System.out.println(farthestNode);
        System.out.println(maxCost);

    }

    private static void dfs(int cur, int cost) {
//        System.out.println("cur : "+cur+", cost : "+cost);
        v[cur]= true;
        if(cost > maxCost){
            farthestNode = cur;
            maxCost = cost;
        }

        for(Edge edge : graph[cur]){
            if(v[edge.to]) continue;
//            v[edge.to]= true;

            int moveCost = edge.cost+cost;
            dfs(edge.to, moveCost);

        }

    }

    static class Edge{
        int to,cost;
        public Edge(int to, int cost){
            this.to=to;
            this.cost=cost;
        }
    }
}
