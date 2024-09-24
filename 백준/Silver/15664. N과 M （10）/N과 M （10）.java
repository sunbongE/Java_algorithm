
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M, data[], per[], number = 0;
    static boolean v[];
    static HashMap<String, Boolean> check;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        data = new int[N];
        v = new boolean[N];


        per = new int[M];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(data);
        check = new HashMap<String, Boolean>();

        getPermutation(0, 0);

//        for (Map.Entry<int[], Integer> info : check.entrySet()) {
//            System.out.println(Arrays.toString(info.getKey()));
//            System.out.println(info.getValue());
//        }
//
        System.out.println(sb);
    }

    private static void getPermutation(int cnt, int idx) {
        if (cnt == M) {
            if (isValid()) { // 오름차순만 통과.
                // 중복 검사.
                if (!distinct()) {
                    check.put(Arrays.toString(per), false);
                    for (int i : per) {
                        sb.append(i + " ");
                    }
                    sb.append("\n");
                }
                ;
            }
            return;
        }

        if (idx < N) {
            per[cnt] = data[idx];
            getPermutation(cnt + 1, idx + 1);
            getPermutation(cnt, idx + 1);

        }
    }

    private static boolean distinct() {

        String s = Arrays.toString(per);
        return check.keySet().contains(s);
    }

    private static boolean isValid() {
        if (M == 1) return true;
        for (int i = 1; i < M; i++) {
            if (per[i - 1] > per[i]) return false;
        }
        return true;
    }
}
