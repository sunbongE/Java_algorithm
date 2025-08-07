import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        // 입력 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 행 수
        int M = Integer.parseInt(st.nextToken()); // 열 수

        // 원본 행렬
        int[][] map = new int[N][M];

        // 행렬 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;

        // 1. 행의 시작점(i) ~ 끝점(j)를 잡고
        for (int startRow = 0; startRow < N; startRow++) {
            int[] colSum = new int[M]; // 열 별 누적합 저장용

            for (int endRow = startRow; endRow < N; endRow++) {
                // 2. 각 열에 대해 현재 행 범위 누적합을 구함
                for (int col = 0; col < M; col++) {
                    colSum[col] += map[endRow][col];
                }

                // 3. 이 누적합 배열에 대해 1차원 카데인 알고리즘 적용
                int sum = 0;
                int tmpMax = Integer.MIN_VALUE;

                for (int k = 0; k < M; k++) {
                    sum = Math.max(colSum[k], sum + colSum[k]);
                    tmpMax = Math.max(tmpMax, sum);
                }

                // 4. 최대값 갱신
                max = Math.max(max, tmpMax);
            }
        }

        // 출력
        System.out.println(max);
    }
}
