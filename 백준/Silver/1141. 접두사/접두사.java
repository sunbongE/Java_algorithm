import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] S = new String[n];

        for (int i = 0; i < n; i++) {
            S[i] = br.readLine();
        }
        Arrays.sort(S);

        int ans = n;

        for (int i = 0; i < n-1; i++) {
            String s1 = S[i];
            for (int j = i+1; j < n; j++) {
                String s2 = S[j];
                if(s1.length() > s2.length() ){ // 크기가 더 크면 접두어가 될수없어
//                    ans--;
                    break;
                }else{
                    if (s1.equals(s2.substring(0,s1.length()))){
//                        System.out.println(s1+" == "+s2.substring(0,s1.length()));
                        ans--;
                        break;
                    }
                }

            }
        }
//        System.out.println(Arrays.toString(S));
        System.out.println(ans);
    }
}