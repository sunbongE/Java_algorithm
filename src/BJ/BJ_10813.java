package BJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author qkrxo
 * 공바꾸기. 브 2
 */
public class BJ_10813 {
	static int i = 0;
	static int j = 0;
	static int ti = 0;
	static int tj = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		ArrayList<Integer> li = new ArrayList<>();
		// 1~n까지 채우기.
		for (int n=0;n<N;n++) {
			li.add(n+1);
		}
		for (int m = 0; m < M; m++) {
			i = sc.nextInt()-1;
			j = sc.nextInt()-1;
			ti = li.get(i);
			tj = li.get(j);
			li.set(i, tj);
			li.set(j, ti);
//			System.out.println(li);
		}
		for (Integer num : li) {
			System.out.print(num+" ");
		}
		
		
	}

}
