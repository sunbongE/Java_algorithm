package BJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BJ_10811 {
	static int i = 0;
	static int j = 0;
	public static void main(String[] args) {
        // TODO Auto-generated method stub
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
			if (i==j) {
				continue;
			}else {
			ArrayList<Integer> subLi = new ArrayList<>(li.subList(i, j+1));
			for(int idx=subLi.size()-1;idx>=0;idx--) {
				li.set(i++, subLi.get(idx));
			}
			}
		
		}
		for (Integer num : li) {
			System.out.print(num+" ");
		}
    }
}
