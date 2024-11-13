
import java.io.*;
import java.util.*;

/**
 * 스택에는 오름차순의 높이가 올 수 있도록 한다.
 * 이유 :
 * <p>
 * 높이가 낮아지면 이전에 있던 사각형의 넓이를 파악해서 최대값을 갱신한다.
 * 갱신된 값은 버리고, 현재 값을 넣어 다음에 활용할 수 있게함.
 * 이 과정을 배열을 모든 높이를 순회할때까지 반복
 * 마지막으로 스택에 남아있는 높이들의 사각형 넓이를 구하고 최대값을 갱신한다.
 */
public class Main {
    static int n, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long cnt = 0; // 3합이 0이되는 경우의수

        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            int s = i+1;
            int e = n - 1;
            while (s < e) {
                // 같은 수를 두번 사용하는 경우 처리
                if (i == s) {
                    s++;
                    continue;
                } else if (e == i) {
                    e--;
                    continue;
                }
//                    System.out.println("i : "+i);
//                    System.out.println(s+", "+e);

                int num1 = arr[s];
                int num2 = arr[e];
                int sum = cur + num1 + num2;

                if (sum == 0) {
                    if (num1 == num2) { // 두수가 같으면 사이에 같은 값의 개수를
                        int sameCnt = e-s+1;
                        cnt+= ((long) sameCnt *(sameCnt-1))/2; // 경우의수
                        break; // 붙어있으니까 끝내버리자.
                    } else {
                        int leftNumCnt = 1;
                        int rightNumCnt = 1;
                        while (!isOut(s + leftNumCnt) && arr[s + leftNumCnt - 1] == arr[s + leftNumCnt]) {
                            leftNumCnt++;
                        }
                        s += leftNumCnt;

                        while (!isOut(e - rightNumCnt) && arr[e - rightNumCnt + 1] == arr[e - rightNumCnt]) {
                            rightNumCnt++;
                        }
                        e -= rightNumCnt;

                        cnt += ((long) leftNumCnt * rightNumCnt);
                    }

                } else if (sum < 0) {
                    s++;
                } else {
                    e--;
                }


            }
        }
        System.out.println(cnt);


    }

    private static boolean isOut(int idx) {
        return 0 > idx || n <= idx;
    }
}