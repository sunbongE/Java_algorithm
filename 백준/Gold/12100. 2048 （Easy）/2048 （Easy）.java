
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[][] board;
    static int N;
    static long ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new long[N][N];
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,new int[5]);
        System.out.println(ans);

//        long[][] re3 = moveUp(getCopyBoard());
//        show(re3);
//        long[][] re33 = moveUp(re3);
//        show(re33);
//        long[][] re = moveLeft(re33);
//        show(re);
//        long[][] re2 = moveLeft(re);
//        show(re2);
//        long[][] re2 = moveRight(getCopyBoard());
//        long[][] re4 = moveDown(getCopyBoard());
//        show(re2);
//        System.out.println("============");
//        show(re4);
//        show(re4);
    }

    private static void show(long[][] ar) {
        System.out.println("=========================================");
        for (long[] ints : ar) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private static void dfs(int cnt, int[] dir) {

        if (cnt == dir.length) {
//            System.out.println(Arrays.toString(dir));
            // 여기서 시뮬레이션 돌려본다.
            long[][] copyBoard = simul(dir);
            long curMaxNum = getMaxNum(copyBoard);
            ans = Math.max(ans, curMaxNum);
            return;
        }

        dir[cnt] = 1;
        dfs(cnt + 1, dir);
        dir[cnt] = 2;
        dfs(cnt + 1, dir);
        dir[cnt] = 3;
        dfs(cnt + 1, dir);
        dir[cnt] = 4;
        dfs(cnt + 1, dir);
    }

    private static long getMaxNum(long[][] copyBoard) {
        long maxNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxNum = Math.max(maxNum, copyBoard[i][j]);
            }
        }
        return maxNum;
    }

    private static long[][] simul(int[] dir) {
        long[][] copyBoard = getCopyBoard();
        // 여기서 방향에 따라 복사보드가 변경된다.
        for (int i = 0; i < dir.length; i++) {

            switch (dir[i]) { // 방향에 따라 보낸다.

                case 1:
                    copyBoard = moveLeft(copyBoard);
                    break;
                case 2:
                    copyBoard = moveRight(copyBoard);
                    break;
                case 3:
                    copyBoard = moveUp(copyBoard);
                    break;
                case 4:
                    copyBoard = moveDown(copyBoard);
                    break;
            }

        }
        return copyBoard;
    }

    private static long[][] moveDown(long[][] copyBoard) {

        // 합치기
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j > 0; j--) {
                long cur = copyBoard[j][i];
                if(cur !=0 ){
                    int k=j-1;
                    while(k>=0){
                        if(copyBoard[k][i] == cur){
                            copyBoard[j][i] *= 2;
                            copyBoard[k][i]=0;
                            break;
                        } else if (copyBoard[k][i] != 0 && copyBoard[k][i] != copyBoard[j][i] ) {
                            // 0아니고 다른 수면 그만 탐색.
                            break;
                        }
                        k--;
                    }
                }
            }
        }

        // 옮기기
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j > 0; j--) {
                if (copyBoard[j][i] == 0) { // 당겨올 수 찾기.
                    int k = j;
                    boolean isOnlyZero = true;
                    while (k -1 >= 0) { // 0이 아닌걸 찾을때까지.
                        k--;
                        if (copyBoard[k][i] != 0) {
                            isOnlyZero = false;
                            break;
                        }
                    }
                    if (isOnlyZero) break;
                    copyBoard[j][i] = copyBoard[k][i];
                    copyBoard[k][i] = 0;
                }
            }
        }
        return copyBoard;
    }

    private static long[][] moveUp(long[][] copyBoard) {

        // 합치기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                long cur = copyBoard[j][i];
                if(cur !=0 ){
                    int k=j+1;
                    while(k<N){
                        if(copyBoard[k][i] == cur){
                            copyBoard[j][i] *= 2;
                            copyBoard[k][i]=0;
                            break;
                        } else if (copyBoard[k][i] != 0 && copyBoard[k][i] != copyBoard[j][i] ) {
                            // 0아니고 다른 수면 그만 탐색.
                            break;
                        }
                        k++;
                    }
                }
            }
        }

        // 옮기기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (copyBoard[j][i] == 0) { // 당겨올 수 찾기.
                    int k = j;
                    boolean isOnlyZero = true;
                    while (k + 1 < N) { // 0이 아닌걸 찾을때까지.
                        k++;
                        if (copyBoard[k][i] != 0) {
                            isOnlyZero = false;
                            break;
                        }
                    }
                    if (isOnlyZero) break;
                    copyBoard[j][i] = copyBoard[k][i];
                    copyBoard[k][i] = 0;
                }
            }
        }
        return copyBoard;
    }

    private static long[][] moveRight(long[][] copyBoard) {

        // 합치기
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j > 0; j--) {
                long cur = copyBoard[i][j];
                if(cur !=0 ){
                    int k=j-1;
                    while(k>=0){
                        if(copyBoard[i][k] == cur){
                            copyBoard[i][j] *= 2;
                            copyBoard[i][k]=0;
                            break;
                        } else if (copyBoard[i][k] != 0 && copyBoard[i][k] != copyBoard[i][j] ) {
                            // 0아니고 다른 수면 그만 탐색.
                            break;
                        }
                        k--;
                    }
                }
            }
        }

        // 옮기기
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j > 0; j--) {
                if (copyBoard[i][j] == 0) { // 당겨올 수 찾기.
                    int k = j;
                    boolean isOnlyZero = true;
                    while (k - 1 >= 0) { // 0이 아닌걸 찾을때까지.
                        k--;
                        if (copyBoard[i][k] != 0) {
                            isOnlyZero = false;
                            break;
                        }
                    }
                    if (isOnlyZero) break;
                    copyBoard[i][j] = copyBoard[i][k];
                    copyBoard[i][k] = 0;
                }
            }
        }
        return copyBoard;
    }


    private static long[][] moveLeft(long[][] copyBoard) {

        // 합치기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                long cur = copyBoard[i][j];
                if(cur !=0){
                    int k=j+1;
                    while(k<N){
                        if(copyBoard[i][k] == cur){
                            copyBoard[i][j] *= 2;
                            copyBoard[i][k]=0;
                            break;
                        } else if (copyBoard[i][k] != 0 && copyBoard[i][k] != copyBoard[i][j] ) {
                            // 0아니고 다른 수면 그만 탐색.
                            break;
                        }
                        k++;
                    }
                }

            }
        }

        // 옮기기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (copyBoard[i][j] == 0) { // 당겨올 수 찾기.
                    int k = j;
                    boolean isOnlyZero = true;
                    while (k + 1 < N) { // 0이 아닌걸 찾을때까지.
                        k++;
                        if (copyBoard[i][k] != 0) {
                            isOnlyZero = false;
                            break;
                        }
                    }
                    if (isOnlyZero) break;
                    copyBoard[i][j] = copyBoard[i][k];
                    copyBoard[i][k] = 0;
                }
            }
        }
        return copyBoard;
    }

    private static long[][] getCopyBoard() {
        int len = board.length;
        long[][] c = new long[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                c[i][j] = board[i][j];
            }
        }
        return c;
    }
}
