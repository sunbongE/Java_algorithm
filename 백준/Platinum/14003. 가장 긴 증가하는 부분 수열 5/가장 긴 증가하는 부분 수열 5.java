import java.io.*;
import java.util.*;

public class Main {
    static int n, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // lis 값과 인덱스, dp 및 trace 초기화
        ArrayList<Integer> lis = new ArrayList<>();
        ArrayList<Integer> lisIdx = new ArrayList<>();
        int[] dp = new int[n];       // arr[i]가 LIS 몇 번째 위치에 들어갔는지
        int[] trace = new int[n];    // arr[i] 앞 원소 인덱스 저장

        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            int idx = customBS(lis, cur); // 들어갈 위치

            if (idx == lis.size()) {
                lis.add(cur);
                lisIdx.add(i); // 이 길이의 마지막 원소는 i
            } else {
                lis.set(idx, cur);
                lisIdx.set(idx, i);  // 덮어쓰기
            }

            dp[i] = idx;
            trace[i] = (idx > 0) ? lisIdx.get(idx - 1) : -1;
        }

        // 역추적 시작: dp[i] == lis.size() - 1 인 마지막 인덱스 찾기
        int cur = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == lis.size() - 1) {
                cur = i;
                break;
            }
        }

        Stack<Integer> stack = new Stack<>();
        while (cur != -1) {
            stack.push(arr[cur]);
            cur = trace[cur];
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(lis.size()).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }

    // lower_bound (>= target) 위치 찾기
    private static int customBS(ArrayList<Integer> li, int target) {
        int s = 0, e = li.size() - 1;
        while (s <= e) {
            int m = (s + e) / 2;
            if (li.get(m) >= target) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        return s;
    }
}
