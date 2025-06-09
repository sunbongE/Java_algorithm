
import java.io.*;
import java.util.*;
 
public class Main {
    static int N,M, maxCnt=0, match[];
    static ArrayList<Integer>[] graph;
    static boolean[] v;
    public static void main(String[] args) throws IOException {
  //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        match = new int[M]; // 어떤 일에 어떤 직원이 매칭된건지 기록.
        Arrays.fill(match, -1); // -1이면 아직 매칭된 직원 없음

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            for (int j = 0; j < len; j++) {
                graph[i].add(Integer.parseInt(st.nextToken())-1);
            }
        }

        int ans = 0;

        // 각 직원들 매칭시켜봄
        for (int i = 0; i < N; i++) {
            v = new boolean[M];
            if(dfs(i)) ans++;
        }

        System.out.println(ans);
    }

    private static boolean dfs(int emp) {
        for(int job : graph[emp]){
            if(v[job]) continue;
            v[job] = true;

            // 일-직원 매칭 안됐거나 현재 매칭된 직원이 다른 일로 옮길 수 있으면
            if(match[job] == -1 || dfs(match[job])){
                match[job] = emp;
                return true;
            }
        }
        return false;
    }

}
