import java.io.*;
import java.util.*;

/**
 * 문제 주석추가
 * 2호선
 *
 * 1. 사이클이 발생하는 부분이 순환선인데 이걸 찾는다.
 * 찾는 방법
 * dfs(이전역,현재역,방문한역들)
 * */
public class Main {

    static int n ;
    static ArrayList<Integer>[] graph;  // 무향그래프
    static LinkedList<Integer> cycleTmp; // 사이클 발생하는 과정을 기록
    static HashSet<Integer> cycleSet ; // 사이클에 포함된거만 저장
    static boolean findCycle=false;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 //        BufferedReader br = new BufferedReader(new FileReader("test.txt"));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        cycleTmp = new LinkedList<>(); 

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

        int[] vv = new int[n+1]; // 방문체크겸 거리체크함.
        Arrays.fill(vv,-1);
        for (int i = 1; i <= n; i++) {

            if(cycleSet.contains(i)){ // 순환선이면,
                vv[i]=0;
            }else{// 순환선이 아닌경우 순환선까지의 거리 찾기
                vv[i] = bfs(i);// 최단경로로 찾아야함.
            }
        }

        // 답 갱신.
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < vv.length; i++) {
            ans.append(vv[i]+" ");
        }

        System.out.println(ans);
    }

    private static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>(); // LinkedList보다 ArrayDeque 성능이 더 좋다고함..메모리도 절약한다고 함..
        int[] v = new int[n+1];
        Arrays.fill(v,-1); // 거리체크할거니까 초기화함.
        v[0] = 0; v[start]=0;

        q.offer(start); // 출발

        while(!q.isEmpty()){
            int cur = q.poll();
            if(cycleSet.contains(cur)){ // 순환선에 도착한거임.
                return v[cur];
            }
            // 인접한 곳으로 이동
            for(Integer next : graph[cur]){
                if(v[next] == -1){ // 미방문인경우 이동.
                    q.offer(next);
                    v[next] = v[cur]+1; // 이전 위치 +1 해서 이동 거리를 기록.
                }
            }
        }

        // 여기로 올 수 없음.
        return -1;
    }

    private static void dfs(int pre, int cur, boolean[] v) {

        for (int i = 0; i < graph[cur].size(); i++) {
            int next = graph[cur].get(i);
            if(pre == next) continue;
            if(v[cur]){
                // 사이클 발생한 위치를 찾아가서 거기부터 이전까지 순환선으로 확정.
                cycleSet = new HashSet<>(cycleTmp.subList(cycleTmp.indexOf(cur),cycleTmp.size()));
                findCycle=true;
                return;
            }
            v[cur] = true;
            cycleTmp.add(cur); // 이동 기록.
            dfs(cur,next,v);
            if(findCycle) return;
            cycleTmp.remove(cycleTmp.indexOf(cur));
            v[cur] = false;


        }
    }
}
