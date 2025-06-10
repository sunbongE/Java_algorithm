import java.io.*;
import java.util.*;

/**
 * 문제
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
 * 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
 *
 * 출력
 * 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 *
 * 둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.
 *
 * 4
 * 5 10 9 18 17
 *
 * 수빈이가 동생을 찾는 최단거리 찾고 그때의 이동 위치를 기록해야함.
 *
 * */
public class Main {
    static int N,K,time[],len= 200000,path[],ansPath[];
    static ArrayList<Integer> ansP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 //             BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        time = new int[len]; // 각 구간을 몇초에 왔는지 기록.
//        Arrays.fill(time,-1);
        path = new int[len]; // 경로 역추적용
//        path = new int[100]; // 경로 역추적용

        bfs();

        System.out.println(time[K]);
        for(Integer p : ansPath){
            System.out.print(p+" ");
        }

    }

    private static void bfs() {
        Queue<Subin> q = new ArrayDeque<>();
        q.offer(new Subin(N));

        while(!q.isEmpty()){
            Subin subin = q.poll();
            int cur = subin.posi;

            // 만약 도착한 상태면 그만 보자.
            if(cur == K ){
//                System.out.println(" --> : "+Arrays.toString(subin.path));
                int l = time[K];
                ansPath = new int[l+1];
                ansPath[l] = K;
                int target = cur;
                for (int i = 0; i < time[K]; i++) {
                    ansPath[--l] = path[target];
                    target = path[target];
                }
                return;
            }

            int next = cur*2;
            // 범위내, 최초방문이거나, 기존보다 빠른 방문인경우 이동.
            if(next != cur) moveTry(next, subin,q);

            next = cur-1;
            // 범위내, 최초방문이거나, 기존보다 빠른 방문인경우 이동.
            moveTry(next, subin,q);

            next = cur+1;
            // 범위내, 최초방문이거나, 기존보다 빠른 방문인경우 이동.
            moveTry(next, subin,q);

        }

    }

    private static void moveTry(int next, Subin subin,Queue<Subin> q ) {
//        if(next==0) return;
        int cur = subin.posi;
        if(!isOut(next) && (time[next]==0 || time[next] > time[cur]+1 )){
            time[next] = time[cur]+1;
            path[next] = cur;
            q.offer(new Subin( next));
        }
    }

    public static boolean isOut(int next){
        return 0 > next || len <= next;
    }

    static class Subin{
        int posi; // 위치.

        public Subin(int posi){
            this.posi = posi;
        }
    }
}
