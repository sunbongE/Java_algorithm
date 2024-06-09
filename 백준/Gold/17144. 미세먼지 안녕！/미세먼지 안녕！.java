import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T,ANS;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] matrix;
    static ArrayList<Integer> machineXY = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        matrix = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < C; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

                if (matrix[i][j] == -1) machineXY.add(i);

            }
        }
        for (int time = 0; time < T; time++) {
            Spread();
            Purify();

        }
//            showMatrix(matrix);
            ans();

    }

    private static void Purify() {
        // 위쪽
        // 방향 : 상-우-하-좌
        int[] upDirectionX = {-1, 0, 1, 0};
        int[] upDirectionY = {0, 1, 0, -1};
        int upX = (machineXY.get(0) > 0) ? machineXY.get(0) - 1 : 0;
        int upY = (machineXY.get(0) == 0) ? 1 : 0;
//        System.out.println(machineXY.get(0));
//        System.out.println("upX = " + upX);
//        System.out.println("upY = " + upY);

        int directionIdx = 0;
        while (upX <= machineXY.get(0)) {

            int nx = upX + upDirectionX[directionIdx];
            int ny = upY + upDirectionY[directionIdx];
            if (isInPurify(nx, ny, 0,(machineXY.get(0) + 1))) {  // 범위내
                if (matrix[nx][ny] == -1) {
                    matrix[upX][upY] = 0 ;
                    break;
                }
                matrix[upX][upY] = matrix[nx][ny];
                upX = nx;
                upY = ny;


            } else { // 범위를 벗어나면 방향 전환
                directionIdx++;
            }
        }
        // 아래쪽
        // 하-우-상-좌
        int[] downDirectionX = {1, 0, -1, 0};
        int[] downDirectionY = {0, 1, 0, -1};
        int downX = (machineXY.get(1) == R - 1) ? R - 1 : machineXY.get(1) + 1;
        int downY = (machineXY.get(1) == R - 1) ? 1 : 0;

        directionIdx = 0;

        while (machineXY.get(1) < R) {
            int nx = downX + downDirectionX[directionIdx];
            int ny = downY + downDirectionY[directionIdx];
            if (isInPurify(nx, ny,machineXY.get(1), R)) {  // 범위내
                if (matrix[nx][ny] == -1) {
                    matrix[downX][downY] = 0 ;
                    break;
                }
                matrix[downX][downY] = matrix[nx][ny];
                downX = nx;
                downY = ny;

            } else{ // 범위를 벗어나면 방향 전환
                directionIdx++;
            }
        }


    }


    // 먼지 확산.
    private static void Spread() {
        int[][] changeMatrix = new int[R][C];
        // 기계 위치 입력.
        changeMatrix[machineXY.get(0)][0] = -1;
        changeMatrix[machineXY.get(1)][0] = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0 || matrix[i][j] == -1) continue;
                int cnt = 0;
                for (int di = 0; di < 4; di++) {
                    int nx = i + dx[di];
                    int ny = j + dy[di];
                    if (isIn(nx, ny, R) && matrix[nx][ny] != -1) { // 범위내, 공기청정기가아니면.
                        changeMatrix[nx][ny] += matrix[i][j] / 5;
                        cnt++;
                    }
                }


                changeMatrix[i][j] += matrix[i][j] - ((matrix[i][j] / 5) * cnt); // 원래 위치값 변경
            }
        }
        matrix = changeMatrix;


    }

    private static boolean isIn(int nx, int ny, int r) {
        return 0 <= nx && nx < r && 0 <= ny && ny < C;
    }
    private static boolean isInPurify(int nx, int ny, int minR,int maxR) {
        return minR <= nx && nx < maxR && 0 <= ny && ny < C;
    }

    private static void showMatrix(int[][] changeMatrix) {
        for (int[] ints : changeMatrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
    private static void ans() {
        for (int[] ints : matrix) {
            for (int num : ints) {
                ANS+=num;
            }
        }
        System.out.println(ANS+2);
    }

}
