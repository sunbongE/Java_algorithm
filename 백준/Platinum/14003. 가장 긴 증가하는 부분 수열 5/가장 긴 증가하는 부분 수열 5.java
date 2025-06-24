import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //    BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lisVal = new int[n]; // LIS의 끝값 저장
        int[] pos = new int[n];    // 각 길이별 마지막 인덱스 저장
        int[] pre = new int[n];    // 경로 추적용

        int len = 0;

        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            int idx = Arrays.binarySearch(lisVal, 0, len, cur);
            if (idx < 0) idx = -idx - 1;

            lisVal[idx] = cur;
            pos[idx] = i; // 이 길이의 마지막 원소는 i번째
            pre[i] = (idx > 0) ? pos[idx - 1] : -1;

            if (idx == len) len++;
        }
//        System.out.println("lisVal : "+Arrays.toString(lisVal));
//        System.out.println("pos    : "+Arrays.toString(pos));
//        System.out.println("pre    : "+Arrays.toString(pre));

        // 역추적
        int traceIdx = pos[len - 1];
        Stack<Integer> stack = new Stack<>();
        while (traceIdx != -1) {
            stack.push(arr[traceIdx]);
            traceIdx = pre[traceIdx];
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(len+"\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb);
    }
}
