import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * dfs풀이는 시간초과 발생,
 * N*M = 250000 이기 때문에 재귀로 풀 수 없음
 * java에서 재귀호출 최대 개수 = 25472번이기 때문에 StackOverflowError 발생.
 * <p>
 * 그래서 이거 dp풀이로 변환할 수 있어야하는데.. 어캐함?
 */
public class Main {
    static int M, N, matrix[][], dp[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        matrix = new int[M][N];
        dp = new int[M][N];

        for (int[] ints : dp) {
            Arrays.fill(ints,-1);
//            System.out.println(Arrays.toString(ints));
        }

        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int i, int j){
        if(i==M-1 && j == N-1) {
            return 1;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        dp[i][j]=0;

        for (int idx = 0; idx < 4; idx++) {
            int nx = i+dx[idx];
            int ny = j+dy[idx];

            if(isIn(nx,ny) && matrix[i][j]>matrix[nx][ny]){
                dp[i][j]+=dfs(nx,ny);
            }
        }

        return dp[i][j];
    }

    private static boolean isIn(int nx, int ny){
        return 0<=nx&&nx<M && 0<=ny&&ny<N;
    }

    private static void showDP(){
        System.out.println("================================================");
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
