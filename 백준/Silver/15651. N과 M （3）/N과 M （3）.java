import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M, per[];
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        per = new int[M];

        getPer(0);

        System.out.println(sb);
    }

    private static void getPer(int cnt) {
        if(cnt == M){
            for (int selectedNum : per) {
                sb.append(selectedNum+" ");
            }
            sb.append("\n");
            return;
        }

        // 중복되는 수열
        for (int i = 1; i <= N; i++) {
            per[cnt] = i;
            getPer(cnt+1);
        }

    }
}
