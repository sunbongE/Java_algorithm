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
       // 기저조건 : M개만큼 선택하면 리턴한다.
        if(cnt==M){
            for (int num : per) {
                sb.append(num+" ");
            }
            sb.append("\n");
            return;
        }

        // 정해진 수 중에서 선택할지 말지 결정한다.
        for (int i = 1; i <= N; i++) {

            if(isSelected[i]) continue;
            isSelected[i] = true;
            per[cnt] = i;
            permutation(cnt+1);
            isSelected[i] = false;
        }
    }
}
