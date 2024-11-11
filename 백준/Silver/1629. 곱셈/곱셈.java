import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 값 a, b, c를 각각 읽음
        long a = sc.nextInt();
        long b = sc.nextInt();
        long c = sc.nextInt();

        // sol 함수를 호출하여 결과를 출력 (결과값을 c로 나눈 나머지를 출력)
        System.out.println(sol(a, b, c));

        sc.close(); // Scanner 객체를 닫아 리소스 누수를 방지
    }

    private static long sol(long a, long b, long c) {

        // b가 0일 때, 어떤 수의 0제곱은 1이므로 1을 반환
        if (b == 0) return 1;

        long half = sol(a, b / 2, c); // 지수 b를 절반으로 줄여 재귀 호출
        half = (half * half) % c; // 절반의 결과를 제곱한 후 c로 나눈 나머지를 구함

        // b가 홀수일 경우 a를 한 번 더 곱해줘야 하므로 추가 연산
        if (b % 2 != 0) {
            half = (half * a) % c; // 곱셈 후 c로 나눈 나머지를 구함
        }

        return half; // 최종 결과 반환
    }
}