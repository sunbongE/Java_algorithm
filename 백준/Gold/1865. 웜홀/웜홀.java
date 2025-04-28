import java.io.*;
import java.util.*;
/**
 *  2 초	128 MB
 *  N(1 ≤ N ≤ 500)
 *  도로의 개수 M(1 ≤ M ≤ 2500)
 *  웜홀의 개수 W(1 ≤ W ≤ 200)
 *
 * 월드나라에는 N개의 지점이 있고 N개의 지점 사이에는 M개의 도로와 W개의 웜홀이 있다.
 * (단 도로는 방향이 없으며 웜홀은 방향이 있다.) 웜홀은 시작 위치에서 도착 위치로 가는 하나의 경로인데,
 * 특이하게도 도착을 하게 되면 시작을 하였을 때보다 시간이 뒤로 가게 된다.
 * 웜홀 내에서는 시계가 거꾸로 간다고 생각하여도 좋다.
 * 시간 여행을 매우 좋아하는 백준이는 한 가지 궁금증에 빠졌다.
 * 한 지점에서 출발을 하여서 시간여행을 하기 시작하여 다시 출발을 하였던 위치로 돌아왔을 때,
 * 출발을 하였을 때보다 시간이 되돌아가 있는 경우가 있는지 없는지 궁금해졌다.
 * 여러분은 백준이를 도와 이런 일이 가능한지 불가능한지 구하는 프로그램을 작성하여라.
 *
 * [벨만포드 O(VE)]
 * 음의 사이클이 존재하는지를 묻는 문제.
 * 왜? 그런식으로 해석이 되는가?
 * 최단 거리를 구하는 것 처럼 보였지만 음의 가중치가 나오는것을 알 수 있고
 * 이런 경우는 다익스트라 금지, 벨만포드 혹은 플로이드와샬을 사용할 수 있지만,
 * 정점 500 * 무향간선 2500개 * 2 + 웜홀개수 200 이게 한번 순회할 최대값임
 * 2,500,200 -> 25*10^5 이거 3번순회하면 시초
 *
 *
 * */
public class Main {
    static int tc;
    static List<int[]> li;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int testCase = 0; testCase < tc; testCase++) {
            li = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                li.add(new int[]{s,e,c});
                li.add(new int[]{e,s,c});
            }
            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                li.add(new int[]{s,e,-c});
            }

            if(bellmanFord(n)){
                sb.append("YES\n");
            }else{
                sb.append("NO\n");
            }
        }
        System.out.println(sb);

    }

    private static boolean bellmanFord(int N) {
        int INF = 10001;
        long[] times = new long[N+1];
        Arrays.fill(times,INF);
        times[1] = 0;

        // N개의 정점에서 최단거리 구하기 위해 N-1개의 간선을 이동해야함.
        // 하지만 여기서는 N번 순회하며 마지막은 음수 사이클 검증으로 함.
        // 마지막 까지도 최소값이 갱신된다면 음의 사이클이 존재한다고 생각해도된다.
        for (int i = 0; i < N; i++) { 
            for (int j = 0; j < li.size(); j++) {
                int[] data = li.get(j);
                int s = data[0];
                int e = data[1];
                int t = data[2];
                long nT = times[s]+t; // 현재 위치에서 목적지로 이동한 시간.

                if (nT < times[e]){
                    times[e] = nT;
                    // 마지막에도 값이 갱신되면, 사이클이 존재한다는 의미.
                    if(i==N-1) return true;
                }
            }
        }
        return false;
    }
}
