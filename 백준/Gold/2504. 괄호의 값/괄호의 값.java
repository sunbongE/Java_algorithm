
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

//        System.out.println(Arrays.toString(input));
        Stack<Character> stack = new Stack<>();
        int tmp = 1;
        int ans=0;

        stack.add(input[0]);
        if(input[0] == '('){
            tmp*=2;
        }else if(input[0] == '['){
            tmp*=3;
        }else{
            System.out.println(ans);
            return;
        }

        for (int i = 1; i < input.length; i++) {
            if(input[i] == '('){ // 열리면, tmp에 2를 곱해줌.
                tmp*=2;
            }else if(input[i] == ')'){
                // 직전이 [ 이거면 틀린거임.
                if(input[i-1] == '['){
                    ans = 0 ;
                    break;
                }else if(input[i-1] =='('){
                    // 직전이 열린거면 tmp를 ans에 더하고 tmp/=2한다.
                    ans+=tmp;
                    tmp/=2;
                }else{
                    // )이거면 tmp에서 2를 나눠준다.
                    tmp/=2;
                }
            }else if(input[i] == '['){
                tmp*=3;
            }else if(input[i] == ']'){
                if(input[i-1] == '('){
                    ans = 0 ;
                    break;
                }else if(input[i-1] =='['){
                    // 직전이 열린거면 tmp를 ans에 더하고 tmp/=3한다.
                    ans+=tmp;
                    tmp/=3;
                }else{
                    // )이거면 tmp에서 3를 나눠준다.
                    tmp/=3;
                }
            }

            // 스택에 넣고 빼는 작업으로 괄호가 알맞는 형식인지 확인
            if(input[i] == '(' || input[i] == '['){
                stack.add(input[i]);
            }else{
                if(stack.isEmpty()){
                    ans=0;
                    break;
                }
                if(input[i] == ')'){
                    if(stack.peek() != '('){
                        ans=0;
                        break;
                    }else{
                        stack.pop();
                    }
                }else if(input[i]==']'){
                    if(stack.peek() !='['){
                        ans=0;
                        break;
                    }else{
                        stack.pop();
                    }
                }
            }
        }

        System.out.println((stack.size()!=0)?0:ans);

    }
}
