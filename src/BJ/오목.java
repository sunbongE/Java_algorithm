package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 오목{
    static int[] dx = {-1, 0, 1, 1};
    static int[] dy = {1, 1, 1, 0};
    static int n = 19;

    public static int CheckWinner(int x, int y, char[][] map) {
        char cur = map[x][y];
        int cnt;
        int cx = x;
        int cy = y;
        int nx = 0;
        int ny = 0;
        for (int i = 0; i < 4; i++) {// 4방향.
            cnt = 1; // 오목 개수 1시작.
            // 처음 위치에서 방향 바꾸기.
            nx = x + dx[i];
            ny = y + dy[i];

            while ((0 <= nx && nx < n) && (0 <= ny && ny < n) && (map[nx][ny] == cur)) { // 범위내, 다음 값이 같은 돌이면..
                cnt++;
                cx = nx;
                cy = ny; // 바뀐 위치를 현재 위치로 설정.
                // 바뀐위치에서 같은 방향으로 직진
                nx = cx + dx[i];
                ny = cy + dy[i];
            }
            // 만약 정확히 5목이 되었다면 cur을 반환한다., 그리고 반대 방향의 값이 다른지 확인해야한다.


            // 반대 방향 범위 설정
            nx = x - dx[i];
            ny = y - dy[i];
            if (cnt == 5) {
            	if((0 <= nx && nx < n) && (0 <= ny && ny < n)&&map[nx][ny] == cur) {
            		cnt++;
            	}
               if(cnt == 5) {
            	   return cur;
               }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        boolean win = false;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().replaceAll(" ", "").toCharArray();
        }
        // 순회하면서 0이 아니면 오목확인하는 메서드에 넘겨준다. 매개변수: 현재 위치, map
        loop: for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != '0') {
                    int winner = CheckWinner(i, j, map);
                    if (winner != -1) {
                        System.out.println(map[i][j]);
                        System.out.println((i + 1) + " " + (j + 1));
                        win = true;
                        break loop;
                    }
                }
            }
        }
        if (!win) {
            System.out.println(0);
        }
    }
}