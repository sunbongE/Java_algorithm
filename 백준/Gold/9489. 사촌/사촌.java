import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n==0&&k==0) break;

            int[] parents = new int[n + 1];
            int[] arr = new int[n + 1];
            parents[0] = -1;
            arr[0] = -1;

            int targetIdx = 0;
            int parentNo = -1;

            st = new StringTokenizer(br.readLine());
            int inp;
            for (int i = 1; i <= n; i++) {
                inp = Integer.parseInt(st.nextToken());

                if (inp - arr[i - 1] != 1) {
//                System.out.println(arr[i-1]+" - "+inp);
                    parentNo++;
                }

                if (inp == k) targetIdx = i;

                arr[i] = inp;           // 입력 값 기록
                parents[i] = parentNo;  // 부모 번호 기록
            }

            int cnt=0;
            for (int i = 1; i <= n; i++) {

                // 부모가 같지 않고, 부모의 부모가 같은거
                if(parents[targetIdx] != parents[i] && parents[parents[targetIdx]] == parents[parents[i]]){
                    cnt++;
//                    System.out.println(parents[targetIdx]+" != "+parents[i]+" => "+arr[targetIdx]+", "+arr[i]);
//                    System.out.println(parents[parents[targetIdx]]+" == "+parents[parents[i]]);
                }

            }

            sb.append(cnt+"\n");

//            System.out.println(Arrays.toString(arr));
//            System.out.println(Arrays.toString(parents));
        }
        System.out.println(sb);


    }
}
