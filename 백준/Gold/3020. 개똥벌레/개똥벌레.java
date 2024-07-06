
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 입력 줄 읽기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        // 높이별로 누적합을 계산할 배열 초기화
        int[] line = new int[h + 1];

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());
            if (i % 2 == 1) {
                line[0]++;
                if (height < h) {
                    line[height]--;
                }
            } else {
                line[h - height]++;
            }
//        System.out.println(Arrays.toString(line));
        }

        // prefix 배열 계산
        int[] prefix = new int[h + 1];
        for (int i = 1; i <= h; i++) {
            prefix[i] = prefix[i - 1] + line[i - 1];
        }

        // 최소값과 최소값의 개수 찾기
        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 1; i <= h; i++) {
            if (prefix[i] < min) {
                min = prefix[i];
                count = 1;
            } else if (prefix[i] == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }
}
