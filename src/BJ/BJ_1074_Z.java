package BJ;

import java.util.Scanner;

public class BJ_1074_Z {
	static int ans = 0;
	public static void sol(int r, int c, int N) {
		if (N ==1 ) { // base condition
			return;
		}
		int half = N/2; // 각 사분면을 나눌 사이즈 -> 반절
		// 1사분면은 반절보다 r,c모두 작음
		if (r<half&&c<half) {
			sol(r,c,N/2); // 사이즈 반으로 줄여 호출
		}else if(r<half&&c>=half) {// 2 사분면은 r은 작고 c가 더 큼
			ans += N*N/4;	// 2차원 배열이라 N제곱으로 전체 구하고 4등분한 값을 넣은다.
			sol(r,c-half,N/2);	// 반절을 뺀 이유는 해당하는 사분면을 기준으로 위치를 지정한것.
		}else if(r>=half&&c<half) {// 3 -> r이 크고 c가 작음
			ans += N*N/4*2;	// 3사분면은 4등분한 것에서 1,2사분면을 지나왔기에 2를 곱한다.
			sol(r-half,c,N/2);
		}else if(r>=half&&c>=half) {//4 -> r,c 모두 크다.
			ans += N*N/4*3;	// 1,2,3사분면을 지나와서 4등분한 것에 3을 곱한다.
			sol(r-half,c-half,N/2);
		}
		
		
		
		
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		sol(r,c,(int)Math.pow(2, N)); // N 제곱으로 넘김
		System.out.println(ans);
	}
}
