import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M,per[], input[];
    static boolean[] isSelected;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isSelected = new boolean[N];
        input = new int[N];
        per = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

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

        for(int i = 0;i<input.length;i++){

            if(isSelected[i]) continue;
            isSelected[i] = true;
            per[cnt] = input[i];
            getPer(cnt+1);
            isSelected[i] = false;
        }

    }
}
