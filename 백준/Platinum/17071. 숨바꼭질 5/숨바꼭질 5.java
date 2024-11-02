
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 수빈이가 도착하는 시간을 홀/짝으로 구분하여 기록한다.
 * 동생이 수빈이가 방문한 곳에 방문하면 그 시간을 반환한다.
 * 짝/홀 나누는 이유: 동시에 같은 위치에서 만나는지 확인하기 위함.
 *
 */
public class Main {
    static int n, k;
    static final int LIMIT = 500000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(n==k){
            System.out.println(0);
        }else{
            bfs();
        }

    }

    private static void bfs() {
        Queue<Info> q = new LinkedList<>();
        boolean v[][] = new boolean[500001][2];
        q.offer(new Info(n,k,1));
        int time=1;
        while(!q.isEmpty()){ //
            Info cur = q.poll();
//            System.out.println(cur.toString());
            // 제한시간에 못잡으면 끝난거.
            if(cur.kp>LIMIT || cur.np>LIMIT){
                System.out.println(-1);
                return;
            }
            if(v[cur.kp][(cur.t)%2]){
                System.out.println(cur.t-1);
                return;
            }

            // 수빈이는 순간이동을 현위치*2 만큼 이동할 수 있다.
            // 동생은 항상 현위치 + 시간 만큼 이동한다.
            if(cur.np*2 <= LIMIT && !v[cur.np*2][(cur.t+1)%2]){
                q.offer(new Info(cur.np*2,cur.kp+cur.t,cur.t+1));
                v[cur.np*2][(cur.t+1)%2] = true;
            }
            if(cur.np+1 <= LIMIT && !v[cur.np+1][(cur.t+1)%2]){
                // 혹은 수빈이가 1보 앞으로 이동
                q.offer(new Info(cur.np+1,cur.kp+cur.t,cur.t+1));
                v[cur.np+1][(cur.t+1)%2] = true;
            }
            if(cur.np-1 >=0 && !v[cur.np-1][(cur.t+1)%2]){
                // 혹은 수빈이가 1보 후퇴
                q.offer(new Info(cur.np-1,cur.kp+cur.t,cur.t+1));
                v[cur.np-1][(cur.t+1)%2] = true;
            }
        }

        System.out.println( -1);
    }

    private static boolean isIn(int np){
        return 0<= np && np<500001;
    }

    private static class Info {
        int np, kp, t;

        public Info(int np, int kp, int t) {
            this.np = np;
            this.kp = kp;
            this.t = t;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "np=" + np +
                    ", kp=" + kp +
                    ", t=" + t +
                    '}';
        }
    }
}