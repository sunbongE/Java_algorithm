import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 돌을 던져 떨어지는 위치를 빠르게 찾기 위한 시뮬레이션 구현
 * 각 열에 돌을 떨어뜨릴 때 이동 경로를 스택으로 관리하여 효율적으로 경로를 추적
 */
public class Main {
    static int R, C, N;
    static char[][] board;
    static Stack<Stone>[] stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행과 열의 크기
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 각 열마다 Stack 생성 (돌이 떨어지는 위치 기록용)
        stack = new Stack[C];
        for (int i = 0; i < C; i++) {
            stack[i] = new Stack<>();
        }

        // 보드 정보 입력 받기
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = input.charAt(j);
            }
        }

        // 명령어 수 입력 받기
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int col = Integer.parseInt(br.readLine()) - 1; // 열 값 입력받기 (1부터 시작이므로 -1)

            // 해당 열의 스택 정보에서 돌 위치 확인 후 drop 함수 호출
            while (!stack[col].isEmpty() && board[stack[col].peek().ci][stack[col].peek().cj] == 'O') {
                stack[col].pop(); // 이미 돌이 존재하는 위치는 pop
            }
            if (!stack[col].isEmpty()) {
                Stone info = stack[col].peek();
                drop(info.ci, info.cj, col); // 기록된 위치에서부터 돌 떨어뜨리기
            } else {
                drop(0, col, col); // 처음부터 떨어뜨리기
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            for (char cell : row) {
                sb.append(cell);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // 돌을 떨어뜨리는 함수
    private static void drop(int ci, int cj, int col) {
        boolean flag=true;
        while (flag) {
            // 1. 현재 위치의 바로 아래가 돌이거나 범위를 벗어난 경우
            if (!isIn(ci + 1, cj) || board[ci + 1][cj] == 'X') {
                board[ci][cj] = 'O';
                flag = false;
            }
            // 2. 왼쪽 아래와 왼쪽이 비어있는 경우 왼쪽으로 이동
            else if (isIn(ci + 1, cj - 1) && board[ci + 1][cj] == 'O' && board[ci + 1][cj - 1] == '.' && board[ci][cj - 1] == '.') {
                ci++; // 아래로 이동
                cj--; // 왼쪽으로 이동
            }
            // 3. 오른쪽 아래와 오른쪽이 비어있는 경우 오른쪽으로 이동
            else if (isIn(ci + 1, cj + 1) && board[ci + 1][cj] == 'O' && board[ci + 1][cj + 1] == '.' && board[ci][cj + 1] == '.') {
                ci++; // 아래로 이동
                cj++; // 오른쪽으로 이동
            }
            // 4. 아래로만 이동 가능한 경우
            else if(isIn(ci + 1, cj ) && board[ci+1][cj] =='.'){ // 아래로 갈 수 있으면 간다.
                ci++; // 아래로만 이동
            }else{ // 이동할 수 없는 경우.
                board[ci][cj] = 'O';
                flag = false;
            }
            stack[col].push(new Stone(ci, cj));
        }
    }
//    private static void drop(int ci, int cj, int col) {
//        boolean flag = true;
//
//        while (flag) {
//            if (!isIn(ci + 1, cj) || board[ci + 1][cj] == 'X') {
//                // 돌의 아래칸이 벽이거나 가장 아래쪽 칸일 경우
//                board[ci][cj] = 'O';
//                flag = false; // 이동 중지
//            } else if (isIn(ci + 1, cj) && board[ci + 1][cj] == '.') {
//                // 아래칸이 비어 있으면 돌을 한 칸 아래로 이동
//                ci++;
//            } else if (isIn(ci + 1, cj - 1) && board[ci + 1][cj] == 'O' && board[ci + 1][cj - 1] == '.' && board[ci][cj - 1] == '.') {
//                // 왼쪽 아래와 왼쪽이 비어있다면 왼쪽으로 미끄러짐
//                ci++; // 아래로 이동
//                cj--; // 왼쪽으로 이동
//            } else if (isIn(ci + 1, cj + 1) && board[ci + 1][cj] == 'O' && board[ci + 1][cj + 1] == '.' && board[ci][cj + 1] == '.') {
//                // 오른쪽 아래와 오른쪽이 비어있다면 오른쪽으로 미끄러짐
//                ci++; // 아래로 이동
//                cj++; // 오른쪽으로 이동
//            } else {
//                // 이동할 수 없는 경우 현재 위치에 돌을 놓음
//                board[ci][cj] = 'O';
//                flag = false;
//            }
//
//            // 스택에 최종 위치를 기록하여 다음 돌이 같은 열에 떨어질 때 사용할 수 있도록 함
//            stack[col].push(new Stone(ci, cj));
//        }
//    }


    // 보드 범위 내에 있는지 확인하는 함수
    private static boolean isIn(int ni, int nj) {
        return 0 <= ni && ni < R && 0 <= nj && nj < C;
    }

    // 돌의 위치를 저장할 클래스
    private static class Stone {
        int ci, cj;
        public Stone(int ci, int cj) {
            this.ci = ci;
            this.cj = cj;
        }
    }
}
