
import java.util.*;
import java.io.*;

/**
 * 100 * 10^5 * 10^5
 * 10^12
 */
public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for (int tc = 0; tc < T; tc++) {
            boolean isError = false;
            char[] command = bf.readLine().toCharArray();
            int n = Integer.parseInt(bf.readLine());

            // "[1,2,3]" 입력받기
            // "[", "]", "," 3개의 문자열을 빼고 입력받아온다.
            StringTokenizer st = new StringTokenizer(bf.readLine(), "[],");
            ArrayDeque<Integer> q = new ArrayDeque<>();
            // 큐에 삽입
            while (st.hasMoreTokens()) {
                q.add(Integer.parseInt(st.nextToken()));
            }

            // solution
            solution(command, q);

        }
        System.out.println(sb);
    }

    private static void solution(char[] command, ArrayDeque q) {
        boolean isReverse = false;
        for (char com : command) {

            if (com == 'R') {
                isReverse = !isReverse;
            } else {// D

                // 상태에 따라 앞 혹은 뒤에서 뺀다.
                if (isReverse) {// 뒤집힌 상태
                    if (q.pollLast() == null) {// 뺀게 null이면 에러 반환.
                        sb.append("error\n");
                        return;
                    }

                } else {
                    if (q.pollFirst() == null) {// 뺀게 null이면 에러 반환.
                        sb.append("error\n");
                        return;
                    }
                }
            }
        }
        // 다 뺐으면 출력을 앞에서 할지 뒤에서 할지 결정.
        sb.append("[");

        while (!q.isEmpty()) {
            if (isReverse) {
                sb.append(q.pollLast());

                while (!q.isEmpty()) {
                    sb.append("," + q.pollLast());
                }

            } else {
                sb.append(q.pollFirst());
                while (!q.isEmpty()) {
                    sb.append("," + q.pollFirst());
                }
            }
        }

        sb.append("]\n");
    }
}