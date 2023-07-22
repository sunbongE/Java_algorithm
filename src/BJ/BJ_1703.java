package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * 생장점.
 * </pre>
 * @author qkrxo
 *
 */
public class BJ_1703 {
	public static int tmp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		boolean flag = true;
		while(true) {
			tmp = 1;
			String[] arr = br.readLine().split(" ");
			// 종료조건
			if(arr.length == 1&&arr[0].equals("0")) {
				break;
			}
			for (int i = 1; i < arr.length; i++) {
				if(i%2==1) {
					tmp *= (Integer.parseInt(arr[i]));
				}else {
					tmp -= (Integer.parseInt(arr[i]));
				}
			}
			System.out.println(tmp);
		}
		br.close();
	
	
	}
	// 더 나은 풀이..
//	public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = null;
//        StringBuilder str = new StringBuilder();
//
//        while(true){
//            st = new StringTokenizer(br.readLine());
//            int A = Integer.parseInt(st.nextToken());
//            if(A == 0)
//                break;
//
//            int result = 1;
//            for(int a = 0; a < 2*A; a++){
//                int n = Integer.parseInt(st.nextToken());
//                // 생잠점
//                if(a%2 == 0)
//                    result *= n;
//                // 가지치기 수
//                else
//                    result -= n;
//            }
//            str.append(result).append("\n");
//        }
//
//        System.out.print(str);
//        br.close();
//    }

}
