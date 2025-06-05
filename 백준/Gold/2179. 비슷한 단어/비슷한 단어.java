
import java.io.*;
import java.util.*;

/**
 * 문제
 * N개의 영단어들이 주어졌을 때, 가장 비슷한 두 단어를 구해내는 프로그램을 작성하시오.
 *
 * 두 단어의 비슷한 정도는 두 단어의 접두사의 길이로 측정한다. 접두사란 두 단어의 앞부분에서 공통적으로 나타나는 부분문자열을 말한다. 즉, 두 단어의 앞에서부터 M개의 글자들이 같으면서 M이 최대인 경우를 구하는 것이다. "AHEHHEH", "AHAHEH"의 접두사는 "AH"가 되고, "AB", "CD"의 접두사는 ""(길이가 0)이 된다.
 *
 * 접두사의 길이가 최대인 경우가 여러 개일 때에는 입력되는 순서대로 제일 앞쪽에 있는 단어를 답으로 한다. 즉, 답으로 S라는 문자열과 T라는 문자열을 출력한다고 했을 때, 우선 S가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력하고, 그런 경우도 여러 개 있을 때에는 그 중에서 T가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력한다.
 *
 * 입력
 * 첫째 줄에 N(2 ≤ N ≤ 20,000)이 주어진다. 다음 N개의 줄에 알파벳 소문자로만 이루어진 길이 100자 이하의 서로 다른 영단어가 주어진다.
 *
 * 출력
 * 첫째 줄에 S를, 둘째 줄에 T를 출력한다. 단, 이 두 단어는 서로 달라야 한다. 즉, 가장 비슷한 두 단어를 구할 때 같은 단어는 제외하는 것이다.
 * */

public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 2~2M
        String A = null;
        String B = null;
        ArrayList<String> li = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String data = br.readLine();
            if(!li.contains(data)){
                li.add(data);
            }
        }

        int maxCnt = 0 ;
        int len =li.size();
        for(int i=0; i< len-1; i++ ){
            String a = li.get(i);
            for (int j = i+1; j < len ; j++) {
                String b = li.get(j);
                int cnt = getDuplicationCnt(a,b);
                if(cnt > maxCnt){
                    maxCnt = cnt;
                    A = a;
                    B = b;

                }
            }
        }

        System.out.println(A);
        System.out.println(B);
    }

    private static int getDuplicationCnt(String A, String B) {
        int cnt = 0 ;

        if(A.equals(B)) return -1;

        int len = Math.min(A.length(), B.length());
        for (int i = 0; i < len; i++) {
            if(A.charAt(i)!=B.charAt(i)) break;
            cnt++;
        }

        return cnt;
    }
}
