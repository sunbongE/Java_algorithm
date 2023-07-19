package BJ;

import java.util.Scanner;

/**
 * @author 박태호
 * <pre>
 * 배열의 0에 공격력, 1에 생명을 담고
 * 둘중하나가 0이 될 때까지 계속 차감한다.
 * 만약 0이되면 게임을 끝내고 승자를 갱신한다.
 * 특이사항
 * 입력받기가 어려움
 * </pre>
 * 
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] A = new int[2];
		int[] B = new int[2];
		A[0] = Integer.parseInt(sc.next());
		A[1] = Integer.parseInt(sc.next());
		B[0] = Integer.parseInt(sc.next());
		B[1] = Integer.parseInt(sc.next());
		boolean game = true;
		String winner = null;
		while(game) {
			A[1] -= B[0];
			B[1] -= A[0];
			if (A[1]<=0 && B[1]<=0) {
				winner = "DRAW";
				game = false;
			}else if(A[1]<=0) {
				winner = "PLAYER B";
				game = false;
			}else if(B[1]<=0) {
				winner = "PLAYER A";
				game = false;
			}
		}
		System.out.println(winner);
	
	}
}
