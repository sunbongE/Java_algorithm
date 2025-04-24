import java.io.*;
import java.util.*;

public class Main {
    static int[] map;
    static boolean[][] v;
    static int N,M;
    static HashMap<Integer,Integer> snakeOrBridge;
    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("test.txt")); // 입력 파일 열기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행의 수
        M = Integer.parseInt(st.nextToken()); // 열의 수

        map = new int[101];
        for (int i = 0; i < map.length; i++) {
            map[i] = i;
        }

        v = new boolean[101][101];
        snakeOrBridge = new HashMap<>();
        for (int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            snakeOrBridge.put(from,to);
        }
        int start = 1, end = 100;
        // bfs 갈 수 있는 모든 방향으로 이동해보기.
        System.out.println(bfs(start));


    }

    private static int bfs(int start) {
        int cnt = -1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start,0});
        v[start][0] = true; // 현재위치, 어떤 위치에서 왔는지.
        // {현재 위치, 주사위 돌린 횟수}
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cCnt = cur[1];
            if(ci == 100) return cCnt;
            for (int i = 1; i <= 6; i++) {
                // 최대 6번 앞으로 나갈 수 있음.
                int ni = ci + i;
                // 뱀이든 사다리든 이동해야하는 위치면 이동시켜
                if(snakeOrBridge.containsKey(ni)){
                    ni = snakeOrBridge.get(ni);
                }

                if(ni <= 100 && !v[ni][ci]){
                    v[ni][ci] = true;
                    q.offer(new int[] {ni,cCnt+1});
                }

            }
        }



        return cnt;
    }
}
