import java.io.*;
import java.util.*;

public class Main {
    static HashMap<Character, Integer> priorityMap = new HashMap<>();
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
//        System.out.println(input);

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        priorityMap.put('*',2);
        priorityMap.put('/',2);
        priorityMap.put('+',1);
        priorityMap.put('-',1);

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(Character.isAlphabetic(c)){
                sb.append(c);
            }else{
                if(c == '(' ){
                    stack.push(c);
                }else if( c==')'){
                    while(stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                }else{
                    int curP = priorityMap.get(c);
                    if(stack.isEmpty()) stack.push(c); // 비었으면 걍 넣기.
                    else{
                        while(stack.peek()!='(' && curP <= priorityMap.get(stack.peek())){ // 우선순위가 top보다 작거나 같으면 top빼서 출력.
                            sb.append(stack.pop());
                            if(stack.isEmpty()) break;
                        }
                        stack.push(c); // 넣기.
                    }
                }
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}
