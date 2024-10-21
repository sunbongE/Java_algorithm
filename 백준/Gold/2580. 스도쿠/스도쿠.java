
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static final int SIZE = 9; // 스도쿠 보드의 크기 (9x9)
    static int[][] board; // 스도쿠 보드 상태
    static ArrayList<int[]> zeroPoint; // 0이 위치한 좌표 리스트
    static boolean stop; // 답을 찾으면 더 이상 탐색하지 않도록 멈추는 플래그

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        zeroPoint = new ArrayList<>();
        board = new int[SIZE][SIZE]; // 9x9 스도쿠 보드 초기화

        // 입력을 받아서 보드에 숫자를 채워넣고, 0의 위치는 zeroPoint 리스트에 저장
        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    zeroPoint.add(new int[]{i, j}); // 빈 칸 위치 저장
                }
            }
        }

        stop = false;
        dfs(0); // 0번째 0의 위치부터 시작

        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // dfs: 빈 칸에 숫자를 채워넣는 백트래킹
    private static void dfs(int n) {
        if (n == zeroPoint.size()) { // 모든 빈 칸을 채웠을 때
            if (checkRow() && checkCol() && checkBox()) { // 가로, 세로, 박스 검증
                stop = true; // 답을 찾았으면 멈춤
            }
            return;
        }

        // n번째 빈 칸의 좌표를 가져옴
        int[] cur = zeroPoint.get(n);
        int x = cur[0], y = cur[1];

        // 현재 빈 칸에 넣을 수 있는 숫자를 찾음
        for (int num = 1; num <= SIZE; num++) {
            if (isValid(x, y, num)) { // 해당 숫자가 들어갈 수 있는지 확인
                board[x][y] = num; // 숫자를 보드에 넣음
                dfs(n + 1); // 다음 빈 칸으로 이동
                if (stop) return; // 답을 찾았으면 더 이상 탐색하지 않음
                board[x][y] = 0; // 백트래킹, 다시 0으로 돌림
            }
        }
    }

    // 행을 체크해서 중복이 있는지 확인
    private static boolean checkRow() {
        for (int i = 0; i < SIZE; i++) {
            boolean[] used = new boolean[SIZE + 1];
            for (int j = 0; j < SIZE; j++) {
                int num = board[i][j];
                if (num == 0) continue;
                if (used[num]) return false;
                used[num] = true;
            }
        }
        return true;
    }

    // 열을 체크해서 중복이 있는지 확인
    private static boolean checkCol() {
        for (int j = 0; j < SIZE; j++) {
            boolean[] used = new boolean[SIZE + 1];
            for (int i = 0; i < SIZE; i++) {
                int num = board[i][j];
                if (num == 0) continue;
                if (used[num]) return false;
                used[num] = true;
            }
        }
        return true;
    }

    // 3x3 박스를 체크해서 중복이 있는지 확인
    private static boolean checkBox() {
        for (int i = 0; i < SIZE; i += 3) {
            for (int j = 0; j < SIZE; j += 3) {
                boolean[] used = new boolean[SIZE + 1];
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        int num = board[x][y];
                        if (num == 0) continue;
                        if (used[num]) return false;
                        used[num] = true;
                    }
                }
            }
        }
        return true;
    }

    // 현재 좌표에 숫자를 넣을 수 있는지 확인
    private static boolean isValid(int x, int y, int num) {
        // 가로에 num이 있는지 확인
        for (int i = 0; i < SIZE; i++) {
            if (board[x][i] == num) return false;
        }
        // 세로에 num이 있는지 확인
        for (int i = 0; i < SIZE; i++) {
            if (board[i][y] == num) return false;
        }
        // 3x3 박스에 num이 있는지 확인
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }
}
