import java.io.*;
import java.util.*;

/**
 * 문제
 * 2호선
 *
 * 1. 사이클이 발생하는 부분이 순환선인데 이걸 찾는다.
 * 찾는 방법
 * dfs(이전역,현재역,방문한역들)
 * */
public class Main {

    static int n ;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer> cycleTmp;
    static HashSet<Integer> cycleSet ;
    static boolean findCycle=false;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     BufferedReader br = new BufferedReader(new FileReader("test.txt"));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        cycleTmp = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // 사이클이 발생하는 역들을 찾아본다.(순환선)
        boolean[] v = new boolean[n+1];
        dfs(0,1,v); // 사이클 찾아서 set에 저장

        StringBuilder sb = new StringBuilder();

        int[] vv = new int[n+1];
        Arrays.fill(vv,-1);
        for (int i = 1; i <= n; i++) {

            if(cycleSet.contains(i)){
                vv[i]=0;
            }else{// 순환선이 아닌경우 순환선까지의 거리 찾기
                vv[i] = bfs(i);// 최단경로로 찾아야함.
            }
        }
//        System.out.println(Arrays.toString(vv));
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < vv.length; i++) {
            ans.append(vv[i]+" ");
        }
        System.out.println(ans);
    }

    private static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] v = new int[n+1];
        Arrays.fill(v,-1);
        v[0] = 0; v[start]=0;

        q.offer(start);

        while(!q.isEmpty()){
            int cur = q.poll();
            if(cycleSet.contains(cur)){
                return v[cur];
            }

            for(Integer next : graph[cur]){
                if(v[next] == -1){
                    q.offer(next);
                    v[next] = v[cur]+1;
                }
            }
        }
        return -1;
    }

    private static void dfs(int pre, int cur, boolean[] v) {

        for (int i = 0; i < graph[cur].size(); i++) {
            int next = graph[cur].get(i);
            if(pre == next) continue;
            if(v[cur]){
                // 사이클 발생함.
//                System.out.println(cur);
//                System.out.println(cycleTmp);
                cycleSet = new HashSet<>(cycleTmp.subList(cycleTmp.indexOf(cur),cycleTmp.size()));
                findCycle=true;
                return;
            }
            v[cur] = true;
            cycleTmp.add(cur);
            dfs(cur,next,v);
            if(findCycle) break;
            cycleTmp.remove(cycleTmp.indexOf(cur));
            v[cur] = false;


        }
    }
}
