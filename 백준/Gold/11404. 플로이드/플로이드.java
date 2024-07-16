import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * n 최대 100 (10^2)^3 으로 10^6 풀이가능
 * 플로이드 워셜 : 3번 순회함, k가 거처가는 노드임, 모든 정점의 최소비용을 알 수 있다.
 * 직통으로 가는 노드 vs k를 거처서 가는 노드 비용의 최소를 찾는다.
 * dist[i][j] vs dist[i][k]+dist[k][j]
 * i: 출발
 * j: 도착
 * k: 경유지
 *
 * 인접 리스트를 만들고
 * 인접하지않은 값은 INF으로 초기화한다.
 * 자기 자신으로 가는 비용은 0으로 초기화한다.
 *
 */
public class Main {
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());

        dist = new int[N][N];

        final int INF = 100000*100+1; // 나올수 있는 비용 최대

        // 초기화.
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i],INF);
            dist[i][i]=0; // 자기 자신으로 가는 비용 0
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = Math.min(dist[start][end],cost); // 인접한 곳 가는 비용
        }

        for (int k = 0; k < N; k++) {
            // 경유지
            for (int i = 0; i < N; i++) {
                // 출발지
                for (int j = 0; j < N; j++) {
                    // 도착지
                    if(dist[i][j] > dist[i][k]+dist[k][j]){
                        // i->j 바로 가는 것 보다
                        // i -> k -> j 경유해서 가는 것이 더 적은 비용이면 갱신한다.
                        dist[i][j] = dist[i][k]+dist[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] ints : dist) {
            for (int d : ints) {
                if(d == INF){
                    sb.append("0 ");
                }else{
                    sb.append(d+" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
