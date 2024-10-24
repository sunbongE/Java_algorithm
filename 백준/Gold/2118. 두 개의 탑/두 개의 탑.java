
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main  {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 지점의 개수 N 입력 받기
        int n = Integer.parseInt(br.readLine());

        // 입력받은 두 지점 사이의 거리를 저장할 배열
        int[] distance = new int[n];

        // 거리의 누적합을 저장할 배열
        int[] sumArr = new int[n];

        // 첫 번째 거리 입력 및 누적합 배열 초기화
        distance[0] = Integer.parseInt(br.readLine());
        sumArr[0] = distance[0];

        // 나머지 거리 입력과 누적합 배열 생성
        for (int i = 1; i < n; i++) {
            distance[i] = Integer.parseInt(br.readLine());
            sumArr[i] = sumArr[i - 1] + distance[i];  // 누적합 갱신
        }

        // 전체 거리: 마지막 지점까지의 누적합이 전체 원형의 길이
        int totalDistance = sumArr[n - 1];

        // 최대 거리를 저장할 변수
        int maxDistance = 0;

        // 투 포인터 설정: start는 0, end는 1부터 시작
        int start = 0;
        int end = 1;

        // 투 포인터로 최적 거리 찾기
        while (start < n) {
            // 두 포인터 사이의 시계 방향 거리
            int clockwiseDistance = (start == 0) ? sumArr[end - 1] : sumArr[end - 1] - sumArr[start - 1];

            // 두 포인터 사이의 반시계 방향 거리
            int counterClockwiseDistance = totalDistance - clockwiseDistance;

            // 두 거리 중 작은 값이 두 탑 사이의 실제 거리
            int currentDistance = Math.min(clockwiseDistance, counterClockwiseDistance);

            // 최대 거리 갱신
            maxDistance = Math.max(maxDistance, currentDistance);

            // end 포인터를 움직여서 다음 거리를 탐색
            if (end == n) {  // end가 배열 끝에 도달한 경우
                start++;  // start를 증가시켜 새로운 구간 탐색
                end = start + 1;  // end는 start보다 항상 한 칸 앞서 있어야 함
            } else {
                end++;  // end를 증가시켜 탐색 범위 확장
            }
        }

        // 결과 출력
        System.out.println(maxDistance);
    }
}
