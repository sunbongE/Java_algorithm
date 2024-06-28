
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] nums = new int[N + 2];
        nums[0] = 0;
        nums[N + 1] = L;

        st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
//        System.out.println(Arrays.toString(nums));


        int low = 1; // 1부터 도로끝까지
        int high = L;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cnt = 0;                // 설치할수있는 수.
            for (int i = 1; i < nums.length; i++) {
                cnt += (nums[i] - nums[i - 1]-1) / mid; // 같은 위치에 설치 불가로 -1
            }
            if (cnt > M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(low);
//        System.out.println(high);
    }
}
