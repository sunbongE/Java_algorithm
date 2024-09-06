import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] per;
    static StringBuilder sb;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        per = new int[M]; // 순열

        isSelected = new boolean[N + 1];

        sb = new StringBuilder();

        permutation(0);

        System.out.println(sb);
    }

    private static void permutation(int cnt) {
        if (cnt == M) {
//            System.out.println(Arrays.toString(per));
            for (int num : per) {
                sb.append(num+" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {        // 정해진 수의 범위 내에서 순회

            if(isSelected[i]) continue;     // 이미 선택한 숫자라면 넘어간다.

            isSelected[i] = true;           // 선택한 경우
            per[cnt] = i;                    // 선택한 숫자를 기록한다.
            permutation(cnt+1);       // 선택된 수를 늘리고 넘어간다.
            isSelected[i] = false;          // 선택 안 한 경우

        }
    }
}
