

import java.io.*;
import java.util.*;

/**
 * 시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
 * 2 초	128 MB	13133	6300	4130	48.858%
 *
 * 문제
 * 농부 존은 소 축사를 완성하였다. 축사 환경을 쾌적하게 유지하기 위해서, 존은 축사를 M개의 칸으로 구분하고, 한 칸에는 최대 한 마리의 소만 들어가게 계획했다.
 * 첫 주에는 소를 임의 배정해서 축사를 운영했으나, 곧 문제가 발생하게 되었다. 바로 소가 자신이 희망하는 몇 개의 축사 외에는 들어가기를 거부하는 것이다.
 * 농부 존을 도와 최대한 많은 수의 소가 축사에 들어갈 수 있도록 하는 프로그램을 작성하시오. 축사의 번호는 1부터 M까지 매겨져 있다.
 *
 * 입력
 * 첫째 줄에 소의 수 N과 축사의 수 M이 주어진다. (1 ≤ N, M ≤ 200)
 * 둘째 줄부터 N개의 줄에는 각 소가 들어가기 원하는 축사에 대한 정보가 주어진다. i번째 소가 들어가기 원하는 축사의 수 Si (0 ≤ Si ≤ M)이 먼저 주어지고, 이후 Si개의 축사 번호가 주어진다. 같은 축사 번호가 두 번 이상 주어지는 경우는 없다.
 *
 * 출력
 * 첫째 줄에 축사에 들어갈 수 있는 소의 최댓값을 출력한다.
 *
 * 이분매칭
 *
 * 5 5
 * 2 2 5
 * 3 2 3 4
 * 2 1 5
 * 3 1 2 5
 * 1 2
 *
 * 4
 * */

public class Main {
    // 상 하 좌 우 위오, 위왼, 아래오, 아래왼
    static int[] di = {-1,1,0,0,-1,-1,1,1}, dj = {0,0,-1,1,1,-1,1,-1};
    static int n,m,map[][];
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st;

        StringBuilder ans = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if(n==0 && m==0) break;

            map = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            v = new boolean[n][m];

            int cnt = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 방문 혹은 물이면 넘어감.
                    if(v[i][j] || map[i][j] == 0) continue;
                    bfs(i,j);
                    cnt++;
                }
            }

            ans.append(cnt+"\n");

        }

        System.out.println(ans);

    }

    private static void bfs(int si, int sj) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{si,sj});
        v[si][sj]=true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for (int i = 0; i < di.length; i++) {
                int ni = ci+di[i];
                int nj = cj+dj[i];

                if(isOut(ni,nj) || v[ni][nj] || map[ni][nj]==0) continue;
//                System.out.println(ni+", "+nj);
                v[ni][nj] = true;
                q.offer(new int[]{ni,nj});
            }
        }
    }

    private static boolean isOut(int ni, int nj){
        return ni < 0 || ni >= n || nj < 0 || nj >= m;
    }
}