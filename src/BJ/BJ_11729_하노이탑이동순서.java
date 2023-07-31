package BJ;

import java.util.Scanner;

public class BJ_11729_하노이탑이동순서 {
	public static void sol(int n, int a, int b) {
		if(n==1) {
			System.out.println(a+" "+b);
			return;
		}
		sol(n-1,a,6-a-b);
		System.out.println(a+" "+b);
		sol(n-1,6-a-b,b);
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println((int)Math.pow(2, n)-1);
		sol(n,1,3);
	}
}
