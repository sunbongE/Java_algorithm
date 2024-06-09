import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * DFS풀이
 * 기본으로 모두 연결된 상태여야함
 * 무향으로 시도했을 때 결과가 다름
 * <p>
 * 들어오는거랑 나가는 거를 비교해서 모두 연결된거를 확인해야함.
 * <p>
 * 들어오는 노드가 저장된 리스트 adjInList
 * 나가는 노드가 저장된 리스트   adjOutList
 * <p>
 * 1~N까지 노드를 in,out모두 dfs로 방문체크하고
 * 모두 방문체크된 노드가 답이 되도록한다.
 * 방문은 각 노드마다 방문배열 생성.
 */
public class Main {
    static int N, M, ans = 0;
    static ArrayList<Integer>[] adjInList;
    static ArrayList<Integer>[] adjOutList;
    static boolean[] v;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjInList = new ArrayList[N + 1];
        adjOutList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjInList[i] = new ArrayList<>();
            adjOutList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjInList[b].add(a);
            adjOutList[a].add(b);
        }

        for (int node = 1; node <= N; node++) {
            v = new boolean[N + 1];
            boolean isAns = true;
            int cnt = 0;

            cnt+=dfs(node, true,1);
            cnt+=dfs(node, false,1);
//            System.out.println("cnt = " + cnt);
//            System.out.println("Arrays.toString(v) = " + Arrays.toString(v));
            for (int i = 1; i < v.length; i++) {
                if(!v[i]) {
                    isAns = false;
                    break;
                }
            }
            if(isAns) ans++;
        }

        System.out.println(ans);


    }

    private static int dfs(int node, boolean isIn, int cnt) {
        ArrayList<Integer> adjList = new ArrayList<>();
        if (isIn) {
            adjList = adjInList[node];
        } else {
            adjList = adjOutList[node];
        }

        v[node] = true;
        for (Integer next : adjList) {
            if (!v[next]) {
                cnt++;
                dfs(next,isIn,cnt);
            }
        }
        return cnt;
    }
}
