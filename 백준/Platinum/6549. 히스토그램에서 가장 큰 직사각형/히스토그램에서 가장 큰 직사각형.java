import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());  // 직사각형의 수
            if (n == 0) break;  // 입력의 마지막 줄에 0이 주어짐

            int[] heights = new int[n];
            for (int i = 0; i < n; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(getMaxRectangleArea(heights, n));
        }
    }

    public static long getMaxRectangleArea(int[] heights, int n) {
        Stack<Integer> stack = new Stack<>();  // 인덱스를 저장하는 스택
        long maxArea = 0;  // 최대 넓이를 저장할 변수
        
        for (int i = 0; i < n; i++) {
            // 스택이 비어 있지 않고, 현재 높이가 스택의 top이 가리키는 높이보다 작으면
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int height = heights[stack.pop()];  // 스택에서 pop하여 높이 획득
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;  // 너비 계산
                maxArea = Math.max(maxArea, (long) height * width);  // 최대 넓이 갱신
            }
            stack.push(i);  // 현재 인덱스를 스택에 push
        }

        // 남은 스택을 처리하여 최대 넓이 확인
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxArea = Math.max(maxArea, (long) height * width);
        }

        return maxArea;
    }
}
