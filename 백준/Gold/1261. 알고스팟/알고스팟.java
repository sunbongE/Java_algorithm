
import java.io.*;
import java.util.*;

public class Main {
    static int n,m,map[][];
    static boolean v[][][];
    static int[] di = {-1,1,0,0}, dj = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        v = new boolean[n][m][4];

        for (int i = 0; i < n; i++) {
            String data = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = data.charAt(j) - '0';
            }
        }

        System.out.println(sol());
    }

    private static int sol() {
        PriorityQueue<Admin> pq = new PriorityQueue<>(((o1, o2) -> {return o1.cnt - o2.cnt;}));
        pq.offer(new Admin(0,0,0));
        for (int i = 0; i < 4; i++) {
            v[0][0][i] = true;
        }

        while(!pq.isEmpty()){
            Admin cur = pq.poll();
            int ci = cur.i; int cj = cur.j; int cnt = cur.cnt;
//            System.out.println("cur -> i : "+ci+", j : "+cj+", cnt"+cnt);
            if(ci == n-1 && cj == m-1){
                return cnt;
            }

            for (int dr = 0; dr < 4; dr++) {
                int ni = ci+di[dr];
                int nj = cj+dj[dr];
                if(isOut(ni,nj) || v[ni][nj][dr]) continue;
                // 벽이면 cnt++ 한다음에 이동시키고
                v[ni][nj][dr] = true;
                if(map[ni][nj] == 1){
                    pq.offer(new Admin(ni,nj,cnt+1));
                }else{
                    pq.offer(new Admin(ni,nj,cnt));
                }
                // 아니면 그냥 이동 시킨다.


            }


        }
        return -1;
    }
    public static boolean isOut(int ni, int nj){
        return ni < 0 || ni >= n || nj < 0 || nj >= m;
    }
    private static class Admin{
        int i,j,cnt;
        public Admin(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }

    }

}