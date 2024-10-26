
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 비교하는 문자열 길이 차이가 2 이상이면 불가능, 이경우 제외한다.
 *
 * 구성이 같고, 길이 차이 1이하면, 비슷한 단어지.
 * 구성이 다르거나, 부족한경우
 * - 몇개가 차이나는지 봐야지,
 * - 차이가 1이고 길이가 같으면 가능.
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans=0;
        String first = br.readLine();
        int len = first.length();
        int[] v = new int[26];
        for (int i = 0; i < first.length(); i++) {
            char cur = first.charAt(i);
            v[cur-'A'] +=1;
        }

        int[] copy;

        for (int i = 0; i < N-1; i++) {
            String tmp = br.readLine();
            int tmpLen = tmp.length();
            int cnt=0;
            copy = Arrays.copyOf(v,v.length);
            for (int j = 0; j < tmp.length(); j++) {
                char c = tmp.charAt(j);
                if(copy[c-'A'] > 0){
                    copy[c-'A']--;
                    cnt++; // 같은거 개수
                }
            }

            if(Math.abs(len - tmpLen)>=2) continue;
//            System.out.println(len - tmpLen +" ?? "+tmp);
            // tmp기준
            // 길이가 같은경우 : 다른 문자가 없거나, 1개여야한다.
            if(len == tmpLen && len-cnt <= 1){
                ans++;
//                System.out.println(tmp);
            }else if(len > tmpLen && len - cnt == 1){
            // 길이가 더 작은경우 : 같은 문자 개수 차이가 1이어야한다.
                ans++;
//                System.out.println(tmp);
            }else if(len < tmpLen && len == cnt){
            // 길이가 더 큰경우 : 같은 문자 개수가 같아야한다.
                ans++;
//                System.out.println(tmp);
            }
        }

        System.out.println(ans);
    }
}
