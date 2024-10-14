import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long cnt = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(br.readLine());

            // 일단 비어있으면 넣어
            if (stack.isEmpty()) {
                stack.push(cur);
            } else {
                // 있으면 값을 비교해보고 현재값보다 작으면 pop해버려
                while (!stack.isEmpty() && stack.peek() <= cur) {
                    stack.pop();
                }
                cnt += stack.size();
                stack.push(cur);

            }
        }

        System.out.println(cnt);
    }
}
