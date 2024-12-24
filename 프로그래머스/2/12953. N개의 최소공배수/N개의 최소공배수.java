import java.util.*;
class Solution {
    // 최대공약수(Greatest Common Divisor) 계산 함수
    private static int gcd(int a, int b) {
        // 유클리드 알고리즘을 사용하여 GCD를 계산
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 최소공배수(Least Common Multiple) 계산 함수
    private static int lcm(int a, int b) {
        // 최소공배수는 a * b / gcd(a, b)로 계산
        return a * (b / gcd(a, b));
    }

    // 배열의 최소공배수를 계산하는 함수
    public static int solution(int[] arr) {
        // 초기 최소공배수를 배열의 첫 번째 원소로 설정
        int answer = arr[0];

        // 배열의 나머지 원소와 차례로 최소공배수를 계산
        for (int i = 1; i < arr.length; i++) {
            answer = lcm(answer, arr[i]);
        }

        return answer; // 계산된 최소공배수를 반환
    }
}