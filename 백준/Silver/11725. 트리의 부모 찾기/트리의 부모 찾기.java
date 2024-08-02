import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> adjList;
    static int n;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n =  Integer.parseInt(br.readLine());

        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        StringTokenizer st;
        int a,b;
        for (int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        sol();
        System.out.println(sb);

    }

    private static void sol() {
        int[] ans = new int[n+1];
        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[n+1];
        v[0] = true;
        q.offer(1);

        while (!q.isEmpty()){
            int parent = q.poll();
            for(Integer child: adjList.get(parent)){

                if(v[child]) continue;
                v[child] = true;
                q.offer(child);
                ans[child] = parent;
            }
        }
//        System.out.println("??");
        for(int i=2;i<ans.length;i++){
            sb.append(ans[i]+"\n");
        }

        return;
    }
}
