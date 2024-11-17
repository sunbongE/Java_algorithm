import java.io.*;
import java.util.Stack;

/**
 * 여는 괄호는 스텍에 넣고, 닫는 괄호가 나왔을때 직전의 값이 여는 괄호였다면, pop()한 나머지 스택사이즈를 ans에 더해준다.
 * 이때 현재 보고있는 인덱스 뒤로 ) 가 몇번 등장하는지 세준다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in = br.readLine();

        Stack<Character> stack = new Stack<>();

        int ans=0;
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if(c == '('){
                stack.push(c);
            }else{
                stack.pop();
                if(in.charAt(i-1) != '(') continue;
//                System.out.println("size : "+stack.size());
                ans += stack.size();
                int tmpIdx = i+1;
                // 바로 뒤에 오는 닫는 괄호 개수
                while(tmpIdx < in.length() && in.charAt(tmpIdx) == ')'){
                    stack.pop();
                    ans++;
                    tmpIdx++;
                    i++;
                }
            }
        }
        System.out.println(ans);
    }
}
