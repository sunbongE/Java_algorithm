import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M,nums[],result[];
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        sol(0);

        System.out.println(sb);
    }

    private static void sol(int cnt){
        if(cnt == M){
            for (int num : result) {
                sb.append(num+" ");
            }
                sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            result[cnt] = nums[i];
            sol(cnt+1);
        }
    }
}
