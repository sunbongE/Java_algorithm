
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 N을 읽고 배열 생성
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        // 배열의 요소를 입력받아 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 요소가 두 개 미만이면 좋은 수가 없으므로 0 출력 후 종료
        if (N < 2) {
            System.out.println(0);
            return;
        }

        // 배열을 오름차순 정렬하여 이진 탐색 준비
        Arrays.sort(arr);

        int cnt = 0;
        // 각 요소에 대해 좋은 수인지 검사
        for (int i = 0; i < N; i++) {
            if (isGood(arr, i)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    // arr[curIdx]가 좋은 수인지 판단하는 메서드
    private static boolean isGood(int[] arr, int curIdx) {
        int s = 0; // 시작 포인터
        int e = arr.length - 1; // 끝 포인터
        int target = arr[curIdx]; // 현재 검사 중인 수

        // 투 포인터로 탐색 진행
        while (s < e) {
            if (s == curIdx) {
                s++; // 시작 포인터가 현재 검사 인덱스와 같으면 이동
            } else if (e == curIdx) {
                e--; // 끝 포인터가 현재 검사 인덱스와 같으면 이동
            } else {
                int sum = arr[s] + arr[e];
                
                if (sum == target) {
                    return true; // 두 수의 합이 목표값과 같으면 true 반환
                } else if (sum < target) {
                    s++; // 합이 목표값보다 작으면 시작 포인터 이동
                } else {
                    e--; // 합이 목표값보다 크면 끝 포인터 이동
                }
            }
        }

        return false; // 좋은 수를 찾지 못했을 때 false 반환
    }
}
