package BJ;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 박태호
 * <pre>
 * 공넣기
 * </pre>
 * 
 * */
public class BJ_10810 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); int M = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append("0");
		}
		
		for (int m = 0; m < M; m++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			int k = sc.nextInt();
			for(int idx = i-1; idx<j;idx++) {
				sb.setCharAt(idx, (char) (k+'0'));
			}
		}
//		for (int i = 1; i < 2*N; i+=2) {
//			sb.insert(i, " ");
//		}
		for (int i = 0; i < N; i++) {
			System.out.print(sb.charAt(i)+" ");
		}
		System.out.println();
//		System.out.println(sb);
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt(); int M = sc.nextInt();
//		int[] arr = new int[N];
//		
//		for (int m = 0; m < M; m++) {
//			int i = sc.nextInt();
//			int j = sc.nextInt();
//			int k = sc.nextInt();
//			for(int idx = i; i<=j;i++) {
//				arr[i-1] = k;
//			}
//		}
//		for (int num : arr) {
//			System.out.print(num+" ");
//		}
//		System.out.println();
////		System.out.println(Arrays.toString(arr));
//	}

}