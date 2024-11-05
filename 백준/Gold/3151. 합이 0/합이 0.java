
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input); // 배열 정렬

        long cnt = 0; // 합이 0이 되는 경우의 수를 저장할 변수

        // 투 포인터 알고리즘을 적용하여 합이 0이 되는 경우의 수를 찾음
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int sum = input[i] + input[left] + input[right];

                if (sum == 0) {
                    int leftVal = input[left];
                    int rightVal = input[right];

                    // 동일한 값이 여러 개 있을 경우를 고려하여 같은 값의 쌍을 모두 계산
                    if (leftVal == rightVal) {
                        // left와 right 사이의 모든 값이 동일할 때
                        int count = right - left + 1;
                        cnt += count * (count - 1) / 2; // 조합 수를 더함
                        break;
                    } else {
                        // 서로 다른 경우 left와 right 값의 개수를 셈
                        int leftCount = 1;
                        int rightCount = 1;

                        while (left + 1 < right && input[left + 1] == leftVal) {
                            left++;
                            leftCount++;
                        }

                        while (right - 1 > left && input[right - 1] == rightVal) {
                            right--;
                            rightCount++;
                        }

                        cnt += leftCount * rightCount; // 가능한 조합 수를 더함
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(cnt);
    }
}