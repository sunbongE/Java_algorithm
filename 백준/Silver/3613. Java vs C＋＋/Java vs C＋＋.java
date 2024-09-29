import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine(); // 최대 길이 100으로 시간신경안써도됨
        String result = "";
        // 어떤 케이스인지 확인한다.
        String thisCase = whatThisCase(data);
        // System.out.println(thisCase);
        // 카멜이면 스네이크로 변경
        if (thisCase.equals("camelCase")) {
            result = changeToSnake(data);
        }
//         스네이크면 카멜로 변경
        if (thisCase.equals("snakeCase")) {
            result = changeToCamel(data);
        }

        if (thisCase.equals("ERROR")) {
            result = "Error!";
        }

        System.out.println(result);

    }

    // 스네이크 -> 카멜 : 언더바 발견하면 체크하고 다음거 대문자로,
    private static String changeToCamel(String data) {
        String result = "";

        boolean preIsUnderbar = false;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '_') {
                preIsUnderbar = true;
                continue;
            }
            if (preIsUnderbar) {
                result += Character.toUpperCase(data.charAt(i));
                preIsUnderbar = false;
            } else {
                result += data.charAt(i);
            }
        }
        return result;
    }

    // 카멜케이스 -> 스네이크케이스
    private static String changeToSnake(String data) {

        if (data.length() == 1) return data;

        String result = "";
        // 대문자가 발견되면 result에 _ 더한다음 더한다.
        for (int i = 0; i < data.length(); i++) {
            if (Character.isUpperCase(data.charAt(i))) {
                result += "_";
                result += Character.toLowerCase(data.charAt(i));
            } else {
                result += data.charAt(i);
            }

        }
        return result;

    }

    // ERROR : N_a , n__, _n_, n_F,
    private static String whatThisCase(String data) {
        // 처음이 대문자인지 확인, 마지막이 언더바인지 확인, 언더바가 연속으로 나왔는지 확인.
        boolean firstIsUpper = false,firstIsUnderbar = false, lastIsUnderbar = false, preIsUnderbar = false, isInDoubleUnderbar = false;
        // 대문자가 포함되어있는지 확인, 언더바가 포함되었는지 확인,
        boolean isInUpper = false, isInUnderbar = false;

        // 첫문자 대문자인지 확인
        if (Character.isUpperCase(data.charAt(0))) firstIsUpper = true;

        // 마지막이 언더바인지 확인
        if (data.charAt(data.length() - 1) == '_') lastIsUnderbar = true;
        if (data.charAt(0) == '_') firstIsUnderbar = true;

        if (firstIsUpper || firstIsUnderbar || lastIsUnderbar) return "ERROR";


        for (int i = 0; i < data.length(); i++) {
            // 이전이 언더바인지 확인.
            if (data.charAt(i) == '_') {
                isInUnderbar = true;
                if (preIsUnderbar) {
                    isInDoubleUnderbar = true;
                    break;
                }
                preIsUnderbar = true;
            } else {
                preIsUnderbar = false;
            }

            if (Character.isUpperCase(data.charAt(i))) isInUpper = true;

        }

        if(isInDoubleUnderbar || (isInUnderbar && isInUpper)) return "ERROR";
        // 소문자와 언더바로구성돼있으면 스네이크
        if (!isInUpper && isInUnderbar) return "snakeCase";
//        if (isInUpper && !isInUnderbar) return "camelCase";


        return "camelCase";
    }
}
