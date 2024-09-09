import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M, nums[],result[];
    static StringBuilder sb;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        result = new int[M];
        isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        sol(0);

        System.out.println(sb);
    }

    private static void sol(int idx) {

        if(idx == M){
            if(isValid(result)){
                for (int selected : result) {
                    sb.append(selected+" ");
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            result[idx] = nums[i];
            sol(idx+1);
            isSelected[i] = false;

        }

    }
    // 오름차순인지를 확인한다.
    private static boolean isValid(int[] result) {
        for (int i = 1; i < result.length; i++) {
            if(result[i-1]>result[i]) return false;
        }
        return true;
    }
}
