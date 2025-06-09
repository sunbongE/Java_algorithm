
import java.io.*;
import java.util.*;

/**
 * 문제
 *
 * */

public class Main {
    static int[][] flow, capacity;
    static int INF=987654321, size;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 2~2M

        size = 'z'-'a'+1;
        size*=2;

        flow = new int[size][size];
        capacity= new int[size][size];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int fromIdx = getIdx(st.nextToken().charAt(0));
            int toIdx = getIdx(st.nextToken().charAt(0));
            int cost = Integer.parseInt(st.nextToken());
            capacity[fromIdx][toIdx] += cost;
            capacity[toIdx][fromIdx] += cost;
        }


        maxFlow('A'-'A', 'Z'-'A');

    }

    public static void maxFlow(int start, int end){
        int totalFlow =0;
        int[] parent = new int[size];
        Queue<Integer> q;

        while(true){
            Arrays.fill(parent,-1);
            q = new LinkedList<>();

            parent[start] = start;
            q.offer(start);

            while(!q.isEmpty() && parent[end] == -1){ // 끝에 도달할 때까지
                int cur = q.poll();

                for(int next = 0; next < size; next++){
                    // 잔여 용량이 남아있는 간선을 따라 탐색.
                    if(capacity[cur][next] - flow[cur][next] > 0 && parent[next] == -1){
                        q.offer(next);
                        parent[next] = cur;
                    }
                }
            }

            // 더이상 증가 경로가 없으면 종료
            if(parent[end] == -1)break;

            // 증가 경로를 통해 유량을 얼마나 보낼지 결정한다.
             int amount = INF;
             for (int i = end; i != start; i = parent[i]){
                 amount = Math.min(amount, capacity[parent[i]][i] - flow[parent[i]][i]);
             }

             // 증가 경로를 통해 유량을 보냄.
            for (int i=end; i != start; i=parent[i]){
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount;
            }
            totalFlow += amount;
        }
        System.out.println(totalFlow);
    }

    private static int getIdx(char c) {
        if('A' <= c && c <= 'Z'){
            return c-'A';
        }else{
            return c-'A'-6;
        }
    }
}
