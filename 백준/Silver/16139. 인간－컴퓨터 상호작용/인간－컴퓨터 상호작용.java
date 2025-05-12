import java.io.*;
import java.util.*;

/**
 * 문자길이 2*10^5 * 질문수 2*10^5 = 4*10^10 (400억)
 * 질의마다 순회하면 시간안에 풀이가 불가능.
 * 입력받은 문자열 길이만큼을 배열로 생성하고, a가 나온 개수 누적합 배열 생성
 * seungjaehwang
 * 0000001111222
 * 012345678910...(인덱스)
 * arr[7-1]-arr[10] (절대값)
 * arr[6-1]-arr[10] (절대값)
 *
 * */
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));

        char[] input = br.readLine().toCharArray();

        N = Integer.parseInt(br.readLine());

        int[][] check = new int[26][input.length+1];
        
        for (int i = 'a'-'a'; i <= 'z'-'a'; i++) {
            for (int j = 1; j < check[i].length; j++) {
                check[i][j] = check[i][j-1];
                if(input[j-1] == (char) (i+'a')) check[i][j]++;
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String[] qs = br.readLine().split(" ");
            int target = qs[0].charAt(0)-'a';
            int start = Integer.parseInt(qs[1]);
            int end = Integer.parseInt(qs[2]);
            ans.append(Math.abs(check[target][start]-check[target][end+1])).append("\n");
        }
        System.out.println(ans);
    }
}
