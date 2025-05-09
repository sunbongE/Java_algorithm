import java.io.*;
import java.util.*;

/**
 * 문제
 * 가로 : 0
 * 세로 : 1
 * 대각선 : 2
 *
 * 갈 수 있는 방향
 * 가로 : 오른쪽, 대각선 오른쪽아래
 * 세로 : 아래, 대각선 오른쪽 아래
 * 대각선 : 오른쪽, 아래, 대각 오른쪽아래
 *
 * 가는 방향이 정해져있어 사이클 걱정은 없음.
 *
 * */
public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int[][][] visitCnt = new int[N][N][3];
        // 처음 가로방향 파이프.
        visitCnt[0][1][0] = 1;

        StringTokenizer st ;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(map[N-1][N-1] == 1){
            System.out.println(0);
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                // 벽
                if(map[i][j] == 1 ) continue;

                // 가로 : 왼쪽 + 왼쪽대각위
                visitCnt[i][j][0] = visitCnt[i][j-1][0] + visitCnt[i][j-1][2];

                if(i < 1 ) continue; // 위에가 없으면
                // 세로 : 위쪽 + 왼쪽대각위
                visitCnt[i][j][1] = visitCnt[i-1][j][1] + visitCnt[i-1][j][2];

                // 대각선 : 왼쪽 + 위쪽 + 왼쪽대각위
                if(map[i-1][j] == 1 || map[i][j-1] == 1) continue; // 왼, 위에 벽이 없어야함.
                visitCnt[i][j][2] = visitCnt[i-1][j-1][0] +visitCnt[i-1][j-1][1] +visitCnt[i-1][j-1][2] ;
            }
        }

        System.out.println(visitCnt[N-1][N-1][0] +visitCnt[N-1][N-1][1] +visitCnt[N-1][N-1][2] );

    }
}
