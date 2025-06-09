
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
    static int N,M,match[];
    static ArrayList<Integer>[] graph;
    static boolean[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        match = new int[M];

        Arrays.fill(match,-1);

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            for (int j = 0; j < c; j++) {
                graph[i].add(Integer.parseInt(st.nextToken())-1);
            }
        }

        int ans=0;

        for (int i = 0; i < N; i++) {
            v = new boolean[M];
            if(dfs(i)) ans++;
        }
        System.out.println(ans);
    }

    public static boolean dfs(int cow){

        for(Integer house: graph[cow]){
            if(v[house]) continue;
            v[house] = true;

            // 매칭 안됨 || 이전 소가 다른 축사로 이동할 수 있으면.
            if(match[house] == -1 || dfs(match[house])){
                match[house] = cow;
                return true;
            }

        }

        return false;
    }
}