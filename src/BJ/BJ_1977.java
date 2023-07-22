package BJ;

import java.util.Scanner;

/**
 * 
 * @author qkrxo
 * 완전제곱수
 * double은 정밀도가 높아서 제곱했을 때 의도했던 것과 다르게 값이 같게나온다.
 * 그래서 float 사용
 * 
 */
public class BJ_1977 {
	public static int ans = 0;
	public static int firstNum = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		for (float num = N; num <= M; num++) {
			float temp = (float) Math.sqrt(num);
			if(num == Math.pow(temp, 2)) {
				if(firstNum==0) {
					firstNum=(int)num;
				}
				ans+=num;
			}
		}
		if (firstNum==0) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
			System.out.println(firstNum);
		}
		sc.close();
		
	}

}
