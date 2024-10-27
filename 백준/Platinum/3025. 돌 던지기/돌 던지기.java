import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 초기 아이디어 -> 단순 구현
 * 시간초과
 * 첫번째 아이디어 -> 첫번째 행에 대해 특정 열에서 떨어뜨린 돌이 도착한 곳의 직전 위치를 (dp[R][0], dp[R][1]) 으로 저장하고 이후 똑같은 행에서 떨어뜨릴 경우 해당 위치로 이동
 * 해당 위치로 가는길에 다른 열에서 떨어뜨린 돌이 나타나서 정답이 틀릴 가능성 존재
 * 두번째 아이디어 -> 특정 열에서 돌이 떨어지는 경로를 저장하고 해당 경로들을 pop 하면서 돌이 없는 곳을 찾아서 거기서 부터 시뮬레이션
 *
 */
public class Main {

    static int R, C;
    static char[][] map;
    static Stack<Point>[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(in.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R + 1][C + 1];
        dp = new Stack[C + 1];

        for(int i = 1; i <= C; i++)
            dp[i] = new Stack<>();

        for (int i = 1; i <= R; i++) {
            String s = in.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = s.charAt(j - 1);
            }
        }

        int N = Integer.parseInt(in.readLine());

        for (int i = 0; i < N; i++) {

            int c = Integer.parseInt(in.readLine());

            // 현재 돌을 던질 위치 c 에 해당하는 stack 의 값이 이미 돌이 있다면 돌이 없는 곳 까지 경로를 빼준다.
            while(!dp[c].isEmpty() && map[dp[c].peek().y][dp[c].peek().x] == 'O')
                dp[c].pop();

            // 이전에 저장된 경로가 없다면 첫 위치부터 돌던지기를 실행
            if(dp[c].isEmpty())
                drop(1, c, c);

            // 이전에 저장된 경로가 있다면 그 곳 부터 돌던지기를 실행
            else
                drop(dp[c].peek().y, dp[c].peek().x, c);
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                out.write(map[i][j]);
            }
            out.write("\n");
        }

        out.flush();
    }

    /**
     * 돌을 떨어뜨리는 시뮬레이션 구현 함수 (y, x)의 위치에서 돌을 떨어뜨린다.
     * 시작한 위치에서 떨어진 곳을 'O' 로 설정한다.
     * 또한, 돌이 떨어진 경로를 c에 해당하는 stack 에 저장한다.
     * @param y 돌을 떨어뜨리기 위한 초기 값 y
     * @param x 돌을 떨어뜨리기 위한 초기 값 x
     * @param c 해당 drop 을 실시한 초기 돌 던지는 위치 
     */
    static void drop(int y, int x, int c) {

        while (y + 1 <= R && map[y + 1][x] != 'X') {

            if (map[y + 1][x] == 'O') {

                if (isInBound(y + 1, x - 1) && map[y + 1][x - 1] == '.' && map[y][x-1] == '.') {
                    y++;
                    x--;
                }

                else if (isInBound(y + 1, x + 1) && map[y + 1][x + 1] == '.' && map[y][x+1] == '.') {
                    y++;
                    x++;
                }

                else
                    break;
            }

            else
                y++;

            dp[c].push(new Point(y, x));
        }

        map[y][x] = 'O';
    }
    
    static boolean isInBound(int y, int x) {
        if (y > 0 && y <= R && x > 0 && x <= C)
            return true;
        else
            return false;
    }

    static class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }
}