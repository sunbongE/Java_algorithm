import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] ans = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if(stack.isEmpty()){
                stack.add(i);
            }else{
                while(!stack.isEmpty()){
                    int top = stack.peek();
                    if( arr[top] < arr[i]){ // 스택의 값보다 크면 오큰수로 지정한다.
                        ans[top] = arr[i];
                        stack.pop(); // 오큰수 처리된거 빼기.
                    }else{
                        break;
                    }
                }
                stack.add(i);
            }
        }

        while(!stack.isEmpty()){
            int p = stack.pop();
//            System.out.println(arr[p]);
            ans[p]=-1;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : ans) {
            sb.append(num+" ");
        }

        System.out.println(sb);

    }
}
