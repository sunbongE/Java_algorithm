import java.io.*;


public class Main {


    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringBuilder ans = new StringBuilder();
        StringBuilder reverseTarget = new StringBuilder();

        boolean isInTag = false; // < 만나면 true, >만나면 false;

        String inp = br.readLine();
//        System.out.println(inp);
        int len = inp.length();

        for (int i = 0; i < len; i++) {
            char cur = inp.charAt(i);
//            System.out.print(cur);
            if(cur == '<'){
                isInTag = true;
                if(reverseTarget.length() > 0){
                    appendReverseTarget(ans, reverseTarget,null);
                    reverseTarget = new StringBuilder();
                }
                ans.append(cur);
            }else if(cur=='>'){
                isInTag = false;
                ans.append(cur);
            }else{
                if(isInTag){ // 태그 열린상태면
                    ans.append(cur);
                }else{ // 태그가 아닌 문자.
                    if(cur == ' '){ // 연속한 다른 문자를 구분
                        appendReverseTarget(ans, reverseTarget,cur); // 이전까지 입력받은 문자는 뒤집고, 공백 추가.
                        reverseTarget = new StringBuilder();

                    }else{
                        reverseTarget.append(cur); // 한문자의 알파벳을 계속 추가함.
//                        System.out.println("reverseTarget : "+reverseTarget);
                    }
                }
            }
        }

        if (reverseTarget.length() > 0) appendReverseTarget(ans,reverseTarget,null);
        System.out.println(ans);

    }

    private static void appendReverseTarget(StringBuilder ans, StringBuilder reverseTarget, Character cur) {
        if(cur == null) ans.append(reverseTarget.reverse());
        else ans.append(reverseTarget.reverse()).append(cur);

    }
}
