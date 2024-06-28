import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 방문체크 어떡할건지
 * <p>
 * 1. 입력 받으면서 1인거의 i,j를 new int[2] {i,j} 형태로 리스트에 넣는다.
 * 2. dfs에서 start=0으로 시작해서 하나씩 꺼내본다.
 * 3. 맵을 순회하면서 둘 수 있는 위치를 파악해서 두는데 이때 놔도되는지를 방문체크로 확인해야한다.
 * 4. 방문체크를 어떻게해야할까.
 * 5. 맵, 대각선 위, 대각선 아래 총 3개 방문체크 필요
 * 6. 대각선 사이즈 N*2로 넉넉하게함.
 * <p>
 * 절반을 봤는데 maxCnt보다 작으면 끝낸다.?
 */
public class Main {
    static int N, maxEvenCnt = 0, maxOddCnt = 0;
    static List<int[]> even = new ArrayList<>(); // 짝수
    static List<int[]> odd = new ArrayList<>(); // 홀수
    static int[][] map;

    static boolean v1[][], v2[], v3[], cv[][];

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        v1 = new boolean[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                int in = Integer.parseInt(st.nextToken());
                map[i][j] = in;
                if (in == 1) {
                    if ((i + j) % 2 == 0) {
                        even.add(new int[]{i, j});
                    } else {
                        odd.add(new int[]{i, j});
                    }
                } else {
                    v1[i][j] = true; // 못 놓는 위치 방문체크
                }
            }
        }

        v2 = new boolean[N * 2];
        v3 = new boolean[N * 2];

        dfs(0, 0, even.size(), true); // n, 개수.
        dfs(0, 0, odd.size(), false); // n, 개수.
        System.out.println(maxEvenCnt + maxOddCnt);

//        for (boolean[] booleans : cv) {
//            System.out.println(Arrays.toString(booleans));
//        }

    }

    private static void dfs(int n, int cnt, int limit, boolean isEven) {
        if (n == limit) {
            if (isEven) {
                if (cnt > maxEvenCnt) {
                    maxEvenCnt = cnt;
                }
            }else{
                if (cnt > maxOddCnt) {
                    maxOddCnt = cnt;
                }
            }
            return;
        }

//        if(n>limit/2 && cnt<maxCnt) return;

//        System.out.println(n+"번째 보는중");
        int[] cur ;
        if(isEven){
            cur =even.get(n);
        }else {
            cur = odd.get(n);
        }

        int i = cur[0];
        int j = cur[1];

        if (!v2[i + j] && !v3[N + i - j]) {

            // 놓을 수 있는 위치에 놓기
            v1[i][j] = true;
            v2[i + j] = true;
            v3[N + i - j] = true;
            dfs(n + 1, cnt + 1, limit,isEven);
            v1[i][j] = false;
            v2[i + j] = false;
            v3[N + i - j] = false;
        }
        // 그냥 지나가기.
        dfs(n + 1, cnt, limit,isEven);


    }

    private static boolean[][] copycv() {
        boolean[][] c = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                c[i][j] = v1[i][j];

            }
        }
        return c;
    }
}
