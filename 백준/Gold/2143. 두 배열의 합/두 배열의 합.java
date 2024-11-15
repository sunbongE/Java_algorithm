import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int T, n, m;
    static long ans;
    static HashMap<Integer,Integer> map;

       public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        ans = 0;

        int[] input = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // a의 부분합을 맵에 기록한다.
        map = new HashMap<>(); // k:부분합된거, v:개수
        updateMap(input);

        m = Integer.parseInt(br.readLine());
        input = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        countAns(input);
        System.out.println(ans);
    }

    private static void countAns(int[] arr) {

        for (int i = 0; i < m; i++) {
            int sum=0;
            for (int j = i; j < m; j++) {
                sum+=arr[j];
                int target = T-sum;
                if(map.containsKey(target)){
                    ans += map.get(target);
                }
            }
        }

    }

    private static void updateMap(int[] arr) {

        for (int i = 0; i < n; i++) {
            int sum=0; // key로 사용.
            for (int j = i; j < n; j++) {
                sum += arr[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
    }
}